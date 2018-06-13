package com.eli.netty.customcodec;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by elizhou on 2018/6/13.
 */
public class MyClientHandler extends SimpleChannelInboundHandler<PersonProtocol> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PersonProtocol msg) throws Exception {
        System.out.println("MyClientHandler channelRead0 length : " + msg.getLength());
        System.out.println("MyClientHandler channelRead0 content : " + new String(msg.getContent(), "UTF-8"));
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);

        PersonProtocol p = new PersonProtocol();
        p.setLength("nihao".getBytes().length);
        p.setContent("nihao".getBytes());
        ctx.writeAndFlush(p);
    }
}
