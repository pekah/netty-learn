package com.eli.netty.pubsubcri;

import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by elizhou on 2017/8/31.
 */
public class PublishClient {
    public static void main(String[] args) throws Exception{
        Socket socket = null;
        OutputStream outputStream = null;

        try {
            socket = new Socket("localhost", 8899);
            outputStream = socket.getOutputStream();
            outputStream.write("hello\r\n".getBytes());// 发布信息到服务器
            outputStream.flush();
        } finally {
            socket.close();
            outputStream.close();
        }
    }
}
