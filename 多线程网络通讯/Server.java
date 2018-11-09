package com.jhun;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket=new ServerSocket(8888);
        //监听8888端口，看是否有连接请求过来
        System.out.println("------服务器端------");
        System.out.println("正在监听端口-->8888");
        Socket socket=serverSocket.accept();
        System.out.println("有连接过来"+socket);

        new SendThread(socket).start();//发送线程
        new ReceiveThread(socket).start();//接收线程
    }
}
