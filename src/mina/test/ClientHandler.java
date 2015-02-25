package mina.test;

import java.io.UnsupportedEncodingException;

import mina.test.protocol.RequestMessage;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

public class ClientHandler extends IoHandlerAdapter {

	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		// TODO Auto-generated method stub
		String content = message.toString();
		System.out.println("客户端接收数据为 : " + content);
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		// TODO Auto-generated method stub
		super.exceptionCaught(session, cause);
		System.out.println("客户端抛出异常为：" + cause);
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("客户端发送数据为 -> ：" + message);
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		System.out.println("-----------sessionOpened----------");
		// TODO Auto-generated method stub
//		String recString = "";
//		try {
//			recString = new String(new RequestMessage("0").getBuffer(), "UTF-8");
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		session.write(new RequestMessage("0").getBuffer());
	}

}
