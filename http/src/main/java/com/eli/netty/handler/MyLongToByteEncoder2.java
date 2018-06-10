package com.eli.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Created by zhouyilin on 2018/6/9.
 */
public class MyLongToByteEncoder2 extends MessageToByteEncoder<Long> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Long msg, ByteBuf out) throws Exception {
        System.out.println("encoder2 invoke");
        System.out.println("encoder2 msg : " + msg);

        out.writeLong(msg);

    }
}
