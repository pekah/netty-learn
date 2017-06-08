package com.eli.netty.protobuf;

import com.google.protobuf.MessageLite;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by zhouyilin on 2017/6/8.
 */
public class ProtobufServerHandler extends SimpleChannelInboundHandler<MyDataInfo.Person>{

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MyDataInfo.Person person) throws Exception {
        System.out.println(person.getName());
        System.out.println(person.getAge());
        System.out.println(person.getAddress());

    }
}
