package com.eli.netty.customcodec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * Created by elizhou on 2018/6/13.
 */
public class MyByteToPersonProtocolDecoder extends ReplayingDecoder<PersonProtocol> {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        int length = in.readInt();

        ByteBuf contentByteBuf = in.readBytes(length);
        byte[] content = new byte[contentByteBuf.readableBytes()];
        contentByteBuf.readBytes(content);

        PersonProtocol protocol = new PersonProtocol();
        protocol.setLength(length);
        protocol.setContent(content);

        out.add(protocol);
    }
}
