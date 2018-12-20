package com.neo.rpc.common.codec;

import com.neo.rpc.common.util.SerializationUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @Auther: cp.Chen
 * @Date: 2018/12/20 15:41
 * @Description: RPC 编码器
 */
public class RpcEncoder extends MessageToByteEncoder {
    private Class<?> genericClass;

    public RpcEncoder(Class<?> genericClass) {
        this.genericClass = genericClass;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        if (genericClass.isInstance(msg)) {
            byte[] data = SerializationUtil.serialize(msg); //序列化
            out.writeInt(data.length);
            out.writeBytes(data);
        }
    }
}
