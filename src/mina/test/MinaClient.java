package mina.test;

import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import mina.keepAlive.ClientKeepAliveFilter;
import mina.keepAlive.MyKeepAliveFilter;
import mina.test.filter.CharsetCodecFactory;
import mina.test.handler.ClientHandler;
import mina.test.protocol.RequestMessage;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

public class MinaClient {
	
	private static final String IP="127.0.0.1";//10.1.123.235
	private static final int PORT=3005;//7100
	
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// 创建客户端连接器.
        NioSocketConnector connector = new NioSocketConnector();
        connector.getFilterChain().addLast("keep-alive", new ClientKeepAliveFilter());
        connector.getFilterChain().addLast("logger", new LoggingFilter());
//        connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));
        connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new CharsetCodecFactory()));
        
        // 设置连接超时检查时间
        connector.setConnectTimeoutCheckInterval(30);
        connector.setHandler(new ClientHandler());
        
        // 建立连接
        ConnectFuture cf = connector.connect(new InetSocketAddress(IP, PORT));
        // 等待连接创建完成
        cf.awaitUninterruptibly();
        String recString="";
//        recString=new RequestMessage("0").getMessage("0");
//       try {
//			recString=new String( new RequestMessage("0").getBuffer() ,"UTF-8");
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        
//        cf.getSession().write(recString);
        // 等待连接断开
        cf.getSession().getCloseFuture().awaitUninterruptibly();
        // 释放连接
        connector.dispose();
	}

}
