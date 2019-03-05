package com.eli.netty.customcodec;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * Created by elizhou on 2018/6/13.
 */
public class MyServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast(new MyByteToPersonProtocolDecoder());
        pipeline.addLast(new MyPersonProtocolToByteEncoder());
        pipeline.addLast(new MyServerHandler());

    }
}
