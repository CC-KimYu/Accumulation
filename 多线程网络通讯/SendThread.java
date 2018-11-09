package com.jhun;

import javax.xml.crypto.Data;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class SendThread extends Thread{
    private Socket socket;

    public SendThread(Socket socket) {
        this.socket=socket;
    }

    @Override
    public void run(){
        try {
            OutputStream outputStream=socket.getOutputStream();
            DataOutputStream dataOutputStream=new DataOutputStream(outputStream);
            Scanner scanner=new Scanner(System.in);
            while (true) {
                String message = scanner.nextLine();
                dataOutputStream.writeUTF(message);
            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
