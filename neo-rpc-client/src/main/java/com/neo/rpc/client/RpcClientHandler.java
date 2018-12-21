package com.neo.rpc.client;

import com.neo.rpc.common.bean.RpcResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentMap;

/**
 * @Auther: cp.Chen
 * @Date: 2018/12/20 17:12
 * @Description: RPC 客户端处理器（用于处理 RPC 响应）
 */
public class RpcClientHandler extends SimpleChannelInboundHandler<RpcResponse> {
    private static final Logger log = LoggerFactory.getLogger(RpcClientHandler.class);

    // 存放 请求编号(requestId) 与 响应对象(RpcResponse) 之间的映射关系
    private ConcurrentMap<String, RpcResponse> responseMap;

    public RpcClientHandler(ConcurrentMap<String, RpcResponse> responseMap) {
        this.responseMap = responseMap;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RpcResponse msg) throws Exception {
        // 建立 请求编号 与 响应对象 之间的映射关系
        responseMap.put(msg.getRequestId(), msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("client caught exception", cause);
        ctx.close();
    }
}
