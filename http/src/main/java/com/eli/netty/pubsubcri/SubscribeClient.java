package com.eli.netty.pubsubcri;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by elizhou on 2017/8/31.
 */
public class SubscribeClient {
    public static void main(String[] args) throws Exception{

        Socket socket = null;
        BufferedReader bufferedReader = null;

        try {
            socket = new Socket("localhost", 8899);
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while (true){
                String line = bufferedReader.readLine();// 阻塞等待服务器发布的消息
                if(line == null){
                    break;
                }
                System.out.println(line);
            }

        } finally {
            socket.close();
            bufferedReader.close();
        }
    }
}
