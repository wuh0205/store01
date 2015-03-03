package mina.test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import mina.keepAlive.MyKeepAliveFilter;
import mina.test.handler.ServerHandler;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

public class MinaService {
	 // 定义监听端口
    private static final int PORT = 3005;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 创建服务端监控线程
        IoAcceptor acceptor = new NioSocketAcceptor();
        acceptor.getSessionConfig().setReadBufferSize(2048);
        acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
        // 设置日志记录器
        DefaultIoFilterChainBuilder chain = acceptor.getFilterChain();  
        chain.addLast("keep-alive", new MyKeepAliveFilter());
        chain.addLast("logger", new LoggingFilter());
        // 设置编码过滤器
        chain.addLast("codec",new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));
        // 指定业务逻辑处理器
        acceptor.setHandler(new ServerHandler());
        // 设置端口号
        try {
			acceptor.bind(new InetSocketAddress(PORT));
			// 启动监听线程
			acceptor.bind();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
