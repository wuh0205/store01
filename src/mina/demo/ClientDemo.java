package mina.demo;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.RuntimeIoException;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

public class ClientDemo {

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		// 创建一个非阻塞的客户端连接器.
		NioSocketConnector connector = new NioSocketConnector();
		connector.getFilterChain().addLast("logger", new LoggingFilter());
		connector.getFilterChain().addLast("codec",new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));
		// 设置连接超时检查时间
		connector.setConnectTimeoutCheckInterval(30);
		connector.setHandler(new ClientHandler());
		IoSession session;

		for (;;) {
			try {
				System.out.println("----开始连接");
				// 建立连接
				ConnectFuture future = connector.connect(new InetSocketAddress("10.1.25.2", 3005));
				// 等待连接创建完成
				future.awaitUninterruptibly();
				session = future.getSession();
				break;
			} catch (RuntimeIoException e) {
				// TODO: handle exception
				e.printStackTrace();
				System.out.println("连接失败");
				Thread.sleep(1000);
			}

		}
		// 等待连接断开
		session.getCloseFuture().awaitUninterruptibly();
		// 释放连接
		connector.dispose();
	}

}
