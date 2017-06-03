package com.eli.netty.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;


/**
 * Created by zhouyilin on 2017/5/21.
 */
public class TestHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {

    //读取客户端的请求并发送响应
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {

        System.out.println(msg.getClass().getName());
        System.out.println(ctx.channel().remoteAddress());
        Thread.sleep(8000);


        if(msg instanceof HttpRequest){

            System.out.println("channelRead0");

            System.out.println("method name : " + ((HttpRequest) msg).method().name());

            ByteBuf content = Unpooled.copiedBuffer("周虹虹大美女", CharsetUtil.UTF_8);//向客户端返回的数据
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);

            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());

            ctx.writeAndFlush(response);
        }
    }
}
