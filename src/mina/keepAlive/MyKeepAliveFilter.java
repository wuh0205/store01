package mina.keepAlive;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;
import org.apache.mina.filter.keepalive.KeepAliveRequestTimeoutHandler;

public class MyKeepAliveFilter extends KeepAliveFilter {
	private static final int INTERVAL = 30; // in seconds
	private static final int TIMEOUT = 10; // in seconds

	public MyKeepAliveFilter(KeepAliveMessageFactory messageFactory) {
		super(messageFactory, IdleStatus.BOTH_IDLE, new ExceptionHandler(),
				INTERVAL, TIMEOUT);
	}

	public MyKeepAliveFilter() {
		super(new KeepAliveMessageFactoryImpl(), IdleStatus.BOTH_IDLE,
				new ExceptionHandler(), INTERVAL, TIMEOUT);
		this.setForwardEvent(false); // 此消息不会继续传递，不会被业务层看见
	}
}

class ExceptionHandler implements KeepAliveRequestTimeoutHandler {
	public void keepAliveRequestTimedOut(KeepAliveFilter filter,
			IoSession session) throws Exception {
		System.out.println("Connection lost, session will be closed!!!");
		session.close(true);
	}
}

/**
 * 继承于KeepAliveMessageFactory，当心跳机制启动的时候，需要该工厂类来判断和定制心跳消息
 * 
 * @author Liu Liu
 * 
 */
class KeepAliveMessageFactoryImpl implements KeepAliveMessageFactory {
	private static final byte int_req = -1;
	private static final byte int_rep = -2;
	private static final IoBuffer KAMSG_REQ = IoBuffer
			.wrap(new byte[] { int_req });
	private static final IoBuffer KAMSG_REP = IoBuffer
			.wrap(new byte[] { int_rep });

	/* (返回一个（新）保活请求消息。)
	 * @see org.apache.mina.filter.keepalive.KeepAliveMessageFactory#getRequest(org.apache.mina.core.session.IoSession)
	 */
	public Object getRequest(IoSession session) {
		System.out.println("服务端==返回一个（新）保活请求消息");
		return KAMSG_REQ.duplicate();
	}

	/* (返回一个（新）为指定的保活请求响应消息。)
	 * @see org.apache.mina.filter.keepalive.KeepAliveMessageFactory#getResponse(org.apache.mina.core.session.IoSession, java.lang.Object)
	 */
	public Object getResponse(IoSession session, Object request) {
		System.out.println("服务端==返回一个（新）为指定的保活请求响应消息");
		return KAMSG_REP.duplicate();
	}

	/* (如果指定的消息是保持请求消息返回true。)
	 * @see org.apache.mina.filter.keepalive.KeepAliveMessageFactory#isRequest(org.apache.mina.core.session.IoSession, java.lang.Object)
	 */
	public boolean isRequest(IoSession session, Object message) {
		if (!(message instanceof IoBuffer))
			return false;
		IoBuffer realMessage = (IoBuffer) message;
		if (realMessage.limit() != 1)
			return false;

		boolean result = (realMessage.get() == int_req);
		realMessage.rewind();
		System.out.println("服务端==指定的消息是否是保持请求消息："+result);
		return result;
	}

	/* (如果指定的消息是一个保活响应消息返回true)
	 * @see org.apache.mina.filter.keepalive.KeepAliveMessageFactory#isResponse(org.apache.mina.core.session.IoSession, java.lang.Object)
	 */
	public boolean isResponse(IoSession session, Object message) {
		if (!(message instanceof IoBuffer))
			return false;
		IoBuffer realMessage = (IoBuffer) message;
		if (realMessage.limit() != 1)
			return false;

		boolean result = (realMessage.get() == int_rep);
		realMessage.rewind();
		System.out.println("服务端==如果指定的消息是否是一个保活响应消息"+result);
		return result;
	}
}
