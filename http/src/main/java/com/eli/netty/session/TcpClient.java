package com.eli.netty.session;

import io.netty.util.CharsetUtil;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.UUID;

/**
 * Created by elizhou on 2017/8/31.
 */
public class TcpClient {
    public static void main(String[] args) throws Exception{

        for (int i = 0; i < 3; i++){

            String uuid = UUID.randomUUID().toString();

            OutputStream outputStream = null;
            Socket socket = null;

            try {
                socket = new Socket("localhost", 8899);
                outputStream = socket.getOutputStream();

                // 第一次请求
                String line = "uuid " + uuid + "hello\r\n";

                outputStream.write(line.getBytes(CharsetUtil.UTF_8));
                outputStream.flush();

                // 第二次请求
                line = "uuid " + uuid + "world\r\n";

                outputStream.write(line.getBytes(CharsetUtil.UTF_8));
                outputStream.flush();



            } finally {
                socket.close();
                outputStream.close();
            }

            Thread.sleep(1000);


        }
    }
}
