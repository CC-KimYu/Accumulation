import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) throws Exception{
        ServerSocket serverSocket=new ServerSocket(8888);
        //监听8888端口，看是否有连接请求过来
        System.out.println("------服务器端------");
        System.out.println("正在监听端口-->8888");
        Socket socket=serverSocket.accept();
        Scanner scanner=new Scanner(System.in);
        System.out.println("有连接过来"+socket);

        InputStream inputStream = socket.getInputStream();
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        OutputStream outputStream = socket.getOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        while (true) {
            //发送数据
            System.out.print("我说:");
            String message1 = scanner.nextLine();
            dataOutputStream.writeUTF(message1);

            //接收数据
            String message2 = dataInputStream.readUTF();
            System.out.println("客户端说：" + message2);
        }

//        关闭对应流
//        dataOutputStream.close();
//        outputStream.close();
//        dataInputStream.close();
//        inputStream.close();
//        socket.close();
//        serverSocket.close();
    }
}
