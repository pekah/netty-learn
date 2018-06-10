package com.eli.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * Created by zhouyilin on 2018/6/9.
 */
public class MyByteToLongDecoder extends ByteToMessageDecoder {


    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("deoder invoke");
        System.out.println(in.readableBytes());

        if(in.readableBytes() >= 8) {
            out.add(in.readLong());
        }

    }
}
