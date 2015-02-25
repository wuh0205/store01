package socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

import mina.test.protocol.Head;
import mina.test.protocol.RequestMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;




public class ClientSocket {
	private static Log log = LogFactory.getLog(ClientSocket.class);
	private String IP="10.1.123.235";//10.1.123.235
	private int PORT=7100;//7100
	private boolean isLoop;
    private Selector selector;// 管道管理器
	
	public ClientSocket connect(){
		this.close();
        this.isLoop = true;
        try {
        	//打开套接字管道
			SocketChannel channel = SocketChannel.open();
			//调整此通道的阻塞模式，true:	通道将被置于阻塞模式,false:通道将被置于非阻塞模式 
			channel.configureBlocking(false);
			// 打开一个通道管理器
			selector = Selector.open();
			// 客户端连接服务器，需要调用channel.finishConnect();才能实际完成连接。
			channel.connect(new InetSocketAddress(IP, PORT));
			// 向给定的选择器注册此通道,为该通道注册SelectionKey.OP_CONNECT事件
			channel.register(selector, SelectionKey.OP_CONNECT);
		} catch (IOException e) {
			log.error("引擎连接前置服务端失败，3秒后重连.  服务端地址：" + IP + "  端口号：" + PORT);
            try { 
                Thread.sleep(3000); 
                this.connect();
            } catch (InterruptedException e1) { 
                log.error(e1);
            } 
		}
		return this;
	}
	
	public void listen() {
		while(isLoop){
			try {
				// 选择注册过的io操作的事件(第一次为SelectionKey.OP_CONNECT)
				selector.select();
				Iterator<SelectionKey> ite = selector.selectedKeys().iterator();
				while (ite.hasNext()) {
					SelectionKey key = ite.next();
					ite.remove();
					if (key.isConnectable()) {
						SocketChannel channel = (SocketChannel) key.channel();
						// 如果正在连接，则完成连接
						if (channel.isConnectionPending()) {
							channel.finishConnect();
						}
						channel.configureBlocking(false);
						 channel.write(ByteBuffer.wrap(new RequestMessage("0").getBuffer()));
						 log.info("客户端发送请求报文为："+new String(new RequestMessage("0").getBuffer(),"UTF-8"));
						// 连接成功后，注册接收服务器消息的事件
						channel.register(selector, SelectionKey.OP_READ);
						// if (!esperComponent.isListened()) {
						// esperComponent.addListeners();
						// }
						log.info("socket客户端连接成功.  服务端地址：" + IP + "  端口号：" + PORT);
					}else if(key.isReadable()){
						SocketChannel channel = (SocketChannel) key.channel();
						Head head = this.receiveResponseMessageHead(channel);
						log.info("++++++++++++++++++++++++报文头为："+head.toString());
						if(log.isInfoEnabled()){
							log.info("execute message:||"+ head.getMsgId()+" || seqNo: "+head.getSeqNo()+" || bodySize:"+head.getBodySize());
						}
						String body = this.receiveResponseMessageBody(channel, head);
						log.info("++++++++++++++++++++++++报文体为："+body);
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	
	public void close(){
        try {
            this.isLoop = false;
            if(selector!=null && selector.isOpen()){
                selector.close();
            }
        } catch (IOException e) {
            log.error("关闭前置机Socket连接失败！");
        }
    }
	
	private Head receiveResponseMessageHead(SocketChannel channel){
    	Head head = null;
    	byte[] headbytes = new byte[Head.HEAD_SIZE];
    	int headSize = Head.HEAD_SIZE;
    	int begin = 0;
    	ByteBuffer headBuffer=null;
    	try{
    		while(headSize>0){
    			headBuffer = ByteBuffer.allocate(headSize); // 读报文体
    			headBuffer.clear();
        		int tempLength = channel.read(headBuffer);
        		byte[] bytes = headBuffer.array();
        		log.info("###头byte："+new String(bytes,"UTF-8"));
        		System.arraycopy(bytes, 0, headbytes, begin, tempLength);
        		headSize = headSize-tempLength;
        		begin = begin+tempLength;
        		headBuffer.clear();
        		headBuffer=null;
        	}
    		log.info("###包头byte字节数:"+headbytes.length);
			head = new Head(headbytes);
    	}catch(IOException e){
    		e.printStackTrace();
      		log.error(e);
      		log.error(e,e.getCause());
    	}finally{
    		if(headBuffer!=null){
    			headBuffer.clear();
    			headBuffer = null;
    		}
    	}
    	return head;
    }
    
    private String receiveResponseMessageBody(SocketChannel channel, Head head){
    	if(head==null){
    		return null;
    	}
    	String body = null;
    	byte[] bodybytes = new byte[head.getBodySize()];
    	int bodySize = head.getBodySize();
    	int begin = 0;
    	ByteBuffer bodyBuffer = null;
    	try{
    		while(bodySize>0){
    		    bodyBuffer = ByteBuffer.allocate(bodySize); // 读报文体
        		bodyBuffer.clear();
        		int tempLength = channel.read(bodyBuffer);
        		byte[] bytes = bodyBuffer.array();
        		System.arraycopy(bytes, 0, bodybytes, begin, tempLength);
        		bodySize = bodySize-tempLength;
        		begin = begin+tempLength;
        		bodyBuffer.clear();
        		bodyBuffer=null;
        	}
    		log.info("###包体byte字节数:"+bodybytes.length);
    		body = new String(bodybytes,"UTF-8").trim();
    	}catch(IOException e){
    	    e.printStackTrace();
    		log.error(e);
    		log.error(e,e.getCause());
    	}finally{
    		if(bodyBuffer!=null){
    			bodyBuffer.clear();
        		bodyBuffer=null;
    		}
    	}
    	if(body!=null){
    		return body;
    	}
    	return null;
    }

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClientSocket cs=new ClientSocket();
		cs.connect().listen();

	}

}
