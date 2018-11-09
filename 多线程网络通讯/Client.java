package com.jhun;

import java.net.Socket;

public class Client {
    public static void main(String[] args) throws Exception {
        System.out.println("------客户端------");
        Socket socket = new Socket("localhost", 8888);
        new SendThread(socket).start();//发送线程
        new ReceiveThread(socket).start();//接收线程
    }
}
