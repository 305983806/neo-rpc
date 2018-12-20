package com.neo.rpc.server;

import com.neo.rpc.common.bean.RpcRequest;
import com.neo.rpc.common.bean.RpcResponse;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @Auther: cp.Chen
 * @Date: 2018/12/20 16:28
 * @Description: RPC 服务端处理器（用于处理 RPC 请求）
 */
public class RpcServerHandler extends SimpleChannelInboundHandler<RpcRequest> {
    private static final Logger log = LoggerFactory.getLogger(RpcServerHandler.class);

    // 存放 服务名称 与 服务实例 之间的映射关系
    private final Map<String, Object> handlerMap;

    public RpcServerHandler(Map<String, Object> handlerMap) {
        this.handlerMap = handlerMap;
    }

    /**
     * 用于从 Channel 中读取数据，该方法的参数列表中带有一个 RpcRequest 参数，它就是所继承的抽象类中指定的 RpcRequest 泛型参数
     * 说明：从 RpcRequest 参数中获相关属性，并执行 Java 反射调用，最终将生成一个 RpcResponse 对象，并将其写入 Channel 中，最终才
     *      能被客户端获取应该 RpcRequest 对象。
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RpcRequest msg) throws Exception {
        // 创建 RPC 响应对象
        RpcResponse response = new RpcResponse();
        response.setRequestId(msg.getRequestId());
        try {
            // 处理 RPC 请求成功
            Object result = handle(msg);
            response.setResult(result);
        } catch (Exception e) {
            // 处理 RPC 请求失败
            response.setException(e);
            log.error("handle result failure", e);
        }
        // 写入 PRC 响应对象（写入完毕后立即关闭与客户端的连接
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }

    /**
     * 用于捕获 RPC 通信过程中所出现的异常,
     * 向日志对象里输出一句错误信息。
     * ctx.close()，关闭 ChannelHandlerContext 对象，实际上此时关闭的是服务端与客户端的 Channel 连接。
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("server caught exception", cause);
        ctx.close();
    }

    private Object handle(RpcRequest request) throws Exception{
        // 获取服务实例
        String serviceName = request.getInterfaceName();
        Object serviceBean = handlerMap.get(serviceName);
        if (serviceBean == null) {
            throw new RuntimeException(String.format("can not find service bean by key: %s", serviceName));
        }
        // 获取反射调用所需的变量
        Class<?> serviceClass = serviceBean.getClass();
        String methodName = request.getMethodName();
        Class<?>[] parameterTypes = request.getParameterTypes();
        Object[] parameters = request.getParameters();
        // 执行反射调用
        Method method = serviceClass.getMethod(methodName, parameterTypes);
        method.setAccessible(true);
        return method.invoke(serviceBean, parameters);
    }
}
