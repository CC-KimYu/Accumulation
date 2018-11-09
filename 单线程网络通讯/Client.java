import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception{
        Socket socket=new Socket("localhost",8888);
        System.out.println(socket);
        System.out.println("------客户端------");
        Scanner scanner=new Scanner(System.in);


        OutputStream outputStream = socket.getOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        InputStream inputStream = socket.getInputStream();
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        while(true) {
            //接收数据
            String message1 = dataInputStream.readUTF();
            System.out.println("服务器说：" + message1);


            //发送数据
            System.out.print("我说：");
            String message2 = scanner.nextLine();
            dataOutputStream.writeUTF(message2);
        }

//        关闭对应流
//        dataOutputStream.close();
//        outputStream.close();
//        dataInputStream.close();
//        inputStream.close();
//        socket.close();
    }
}
