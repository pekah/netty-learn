package com.eli.netty.tcp;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by elizhou on 2017/8/30.
 */
public class TcpClient {

    public static void main(String[] args) throws Exception{
        Socket socket = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;

        try {
            socket = new Socket("localhost", 8899);
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();

            //请求服务器
            outputStream.write("第一次请求".getBytes("UTF-8"));
            outputStream.flush();

            //获取服务器响应，输出
            byte[] bytes = new byte[1024];
            int length = inputStream.read(bytes);
            System.out.println(new String(bytes, 0, length, "UTF-8"));

            Thread.sleep(5000);


            //请求服务器
            outputStream.write("第二次请求".getBytes("UTF-8"));
            outputStream.flush();

            //获取服务器响应，输出
            bytes = new byte[1024];
            length = inputStream.read(bytes);
            System.out.println(new String(bytes, 0, length, "UTF-8"));



        } finally {
            inputStream.close();
            outputStream.close();
            socket.close();
        }
    }
}
