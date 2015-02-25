package mina.demo;

import java.util.Date;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

public class ServiceHandler extends IoHandlerAdapter{

	/* (当接口中其他方法抛出异常未被捕获时触发此方法)
	 * @see org.apache.mina.core.service.IoHandlerAdapter#exceptionCaught(org.apache.mina.core.session.IoSession, java.lang.Throwable)
	 */
	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("服务端抛出异常为:"+cause);
	}

	@Override
	public void inputClosed(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		super.inputClosed(session);
	}

	/* (当接收到客户端的请求信息后触发此方法.)
	 * @see org.apache.mina.core.service.IoHandlerAdapter#messageReceived(org.apache.mina.core.session.IoSession, java.lang.Object)
	 */
	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		// TODO Auto-generated method stub
		String msg=message.toString();
		System.out.println("服务端接收数据为"+msg);
		if("bye".equals(msg)){
			session.close();
		}
		Date date=new Date();
		session.write(date);
	}

	/* ( 当信息已经传送给客户端后触发此方法.)
	 * @see org.apache.mina.core.service.IoHandlerAdapter#messageSent(org.apache.mina.core.session.IoSession, java.lang.Object)
	 */
	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("服务器端发送信息成功!");
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		super.sessionClosed(session);
	}

	/* ( 当一个新客户端连接后触发此方法.)
	 * @see org.apache.mina.core.service.IoHandlerAdapter#sessionCreated(org.apache.mina.core.session.IoSession)
	 */
	@Override
	public void sessionCreated(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("服务端与客户端创建连接...");
	}

	/* (当连接空闲时触发此方法)
	 * @see org.apache.mina.core.service.IoHandlerAdapter#sessionIdle(org.apache.mina.core.session.IoSession, org.apache.mina.core.session.IdleStatus)
	 */
	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("服务器进入空闲状态...");
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("服务端与客户端连接打开...");
	}
	
	


}
