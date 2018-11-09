package com.jhun;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Scanner;

public class ReceiveThread extends Thread{
    private Socket socket;

    public ReceiveThread(Socket socket){
        this.socket=socket;
    }

    @Override
    public void run() {
        try {
            InputStream inputStream=socket.getInputStream();
            DataInputStream dataInputStream=new DataInputStream(inputStream);
            while (true) {
                String message = dataInputStream.readUTF();
                System.out.println("他说:"+message);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
