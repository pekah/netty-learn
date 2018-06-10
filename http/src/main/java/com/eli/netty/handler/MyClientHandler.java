package com.eli.netty.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.LocalDateTime;

/**
 * Created by zhouyilin on 2018/6/9.
 */
public class MyClientHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("client channelRead0 invoke remoteAddress:" + ctx.channel().remoteAddress());
        System.out.println("client output : " + msg);
        ctx.writeAndFlush("from client : " + LocalDateTime.now());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        System.out.println("client channelActive invoke");
        ctx.writeAndFlush(123456L);
        ctx.writeAndFlush(12345L);
        ctx.writeAndFlush(1234L);
        ctx.writeAndFlush(123L);

    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        super.channelUnregistered(ctx);
        System.out.println("client channelUnregistered invoke");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        System.out.println("client exceptionCaught invoke");
        cause.printStackTrace();
        ctx.close();
    }
}
