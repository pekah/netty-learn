package com.eli.netty.heartbeat;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * Created by zhouyilin on 2017/6/3.
 */
public class HeartBeatInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();

        pipeline.addLast(new IdleStateHandler(5, 7, 10, TimeUnit.SECONDS));//在一定时间内没有读／写／读写，会触发这个事件。空闲状态检测
        pipeline.addLast(new HeartBeatHandler());

    }
}
