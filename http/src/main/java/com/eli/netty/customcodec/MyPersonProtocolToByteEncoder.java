package com.eli.netty.customcodec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Created by elizhou on 2018/6/13.
 */
public class MyPersonProtocolToByteEncoder extends MessageToByteEncoder<PersonProtocol> {

    @Override
    protected void encode(ChannelHandlerContext ctx, PersonProtocol msg, ByteBuf out) throws Exception {
        out.writeInt(msg.getLength());
        out.writeBytes(msg.getContent());


    }
}
