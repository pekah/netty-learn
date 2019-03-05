package com.eli.netty.customcodec;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;

/**
 * Created by elizhou on 2018/6/13.
 */
public class MyServerHandler extends SimpleChannelInboundHandler<PersonProtocol> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PersonProtocol msg) throws Exception {
        System.out.println("MyServerHandler channelRead0 length : " + msg.getLength());
        System.out.println("MyServerHandler channelRead0 content : " + new String(msg.getContent(), "UTF-8"));

        String respString = UUID.randomUUID().toString();
        PersonProtocol p = new PersonProtocol();
        p.setLength(respString.getBytes().length);
        p.setContent(respString.getBytes());

        ctx.writeAndFlush(p);

    }



}
