package com.eli.netty.protobuf;

import com.google.protobuf.MessageLite;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by zhouyilin on 2017/6/8.
 */
public class ProtobufServerHandler extends SimpleChannelInboundHandler<MyDataInfo.MyMessage>{

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MyDataInfo.MyMessage msg) throws Exception {

        MyDataInfo.MyMessage.DataType dataType = msg.getDataType();

        if(dataType == MyDataInfo.MyMessage.DataType.PersonType){
            MyDataInfo.Person person = msg.getPerson();

            System.out.println(person.getName());
            System.out.println(person.getAge());
            System.out.println(person.getAddress());
        }else if(dataType == MyDataInfo.MyMessage.DataType.DogType){
            MyDataInfo.Dog dog = msg.getDog();

            System.out.println(dog.getName());
            System.out.println(dog.getAge());
        }else {
            MyDataInfo.Cat cat = msg.getCat();

            System.out.println(cat.getName());
            System.out.println(cat.getCity());
        }



    }
}
