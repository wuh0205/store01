package mina.demo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;


import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

public class ServiceDemo {

	// 定义监听端口
    private static final int PORT = 3005;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 创建服务端监控线程
        IoAcceptor acceptor = new NioSocketAcceptor();
        //设置读取数据的缓冲区大小
        acceptor.getSessionConfig().setReadBufferSize(2048);
        //读写通道10秒内无操作进入空闲状态
        acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
        // 设置日志记录器
        acceptor.getFilterChain().addLast("logger", new LoggingFilter());
        // 设置编码过滤器
        acceptor.getFilterChain().addLast("codec",
            new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));
        // 指定业务逻辑处理器
        acceptor.setHandler(new ServiceHandler());
        try {
        	// 绑定端口
			acceptor.bind(new InetSocketAddress(PORT));
			System.out.println("服务器端启动成功...     端口为："+PORT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("服务器端启动异常...     端口为："+e);
			e.printStackTrace();
		}

	}

}
