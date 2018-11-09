
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @ServerEndpoint 注解是一个类层次的注解，它的功能主要是将目前的类定义成一个websocket服务器端,
 *                 注解的值将被用于监听用户连接的终端访问URL地址,客户端可以通过这个URL来连接到WebSocket服务器端
 */
@ServerEndpoint("/webSocket/Server")
public class JavaWebSocket {
	// concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
	private static CopyOnWriteArraySet<JavaWebSocket> webSocketSet = new CopyOnWriteArraySet<JavaWebSocket>();
	// 与某个客户端的连接会话，需要通过它来给客户端发送数据
	private Session session;

	/**
	 * 连接建立成功调用的方法
	 * 
	 * @param session
	 *            可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
	 */
	@OnOpen
	public void onOpen(Session session) {
		synchronized (this) {//保证线程安全
			this.session = session;
			webSocketSet.add(this);
			System.out.println("有新连接加入！当前在线人数为：" + webSocketSet.size());
		}
	}

	/**
	 * 连接关闭调用的方法
	 */
	@OnClose
	public void onClose() {
		synchronized (this) {//保证线程安全 
			webSocketSet.remove(this);
			System.out.println("有一连接关闭！当前在线人数为：" + webSocketSet.size());
		}
	}

	/**
	 * 收到客户端消息后调用的方法
	 * 
	 * @param message
	 *            客户端发送过来的消息
	 * @param session
	 *            可选的参数
	 */
	@OnMessage
	public void onMessage(String message, Session session) throws IOException {
		System.out.println("来自客户端的消息：" + message);
		for (JavaWebSocket bitCoinServer : webSocketSet) {
			bitCoinServer.sendMessage(message);
		}
	}

	@OnError
	public void onError(Session session, Throwable error) {
		System.out.println("发生错误");
		error.printStackTrace();
	}

	public void sendMessage(String message) throws IOException {
		this.session.getBasicRemote().sendText(message);
	}
}
