package mina.test.handler;

import mina.test.protocol.Head;
import mina.test.protocol.RequestMessage;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;


public class ServerHandler extends IoHandlerAdapter {

	/**
     * 连接创建事件
     */
    @Override
    public void sessionCreated(IoSession session){
        // 显示客户端的ip和端口
        System.out.println(session.getRemoteAddress().toString());
    }
    
    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        cause.printStackTrace();
    }
    
    /**
     * 消息接收事件
     */
    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        String strMsg = message.toString();
        if (strMsg.trim().equalsIgnoreCase("quit")) {
            session.close(true);
            return;
        }
        String body="{\"sgwId\":1, \"isMasterSgw\":1}";
        byte[] buffer = new RequestMessage("0", 2, body.length()).getBuffer();
        String msg=new String(buffer,"UTF-8");
        // 返回消息字符串
        session.write(msg);
        // 打印客户端传来的消息内容
        System.out.println("Message written : " + msg+"字节数为"+buffer.length);
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        System.out.println("IDLE" + session.getIdleCount(status));
    }

}
