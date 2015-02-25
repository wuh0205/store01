package mina.test.filter;

import java.io.UnsupportedEncodingException;

import mina.test.protocol.Head;
import mina.test.protocol.RequestMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

public class NewDataDecoder extends CumulativeProtocolDecoder{
	private static Log log = LogFactory.getLog(NewDataDecoder.class);
	private static final int HEAD_SIZE	= 16;
	private  int bodySize=0;

	@Override
	protected boolean doDecode(IoSession session, IoBuffer in,
			ProtocolDecoderOutput out) throws Exception {
		// TODO Auto-generated method stub
		  if (dataAvailable(in)) {
			  String bodyStr=getBody(in,bodySize);
			  out.write(bodyStr);
			  in.mark();
              return true;
          } else {
        	  in.reset();
              return false;	
          }
		  
	}
	
	public boolean dataAvailable(IoBuffer in){
		int remaining=in.remaining();
		if(HEAD_SIZE>remaining){
			return false;
		}
		bodySize=getHead(in).getBodySize();
		if(bodySize==0){
			throw new RuntimeException("从缓存区读取报文体长度时发现异常");
		}
		
		return remaining-HEAD_SIZE>=bodySize;
	}
	
	/**
	 * 读取报文头信息
	 * @param in
	 * @return
	 */
	public Head getHead(IoBuffer in){
		byte[] headBytes=new byte[HEAD_SIZE];
		in.get(headBytes, 0, HEAD_SIZE);
		String headstr="";
		try {
			headstr = new String(headBytes,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info("从缓存区获取报文头为"+headstr);
		return new Head(headBytes);
	}
	
	/**
	 * 读取报文体信息
	 * @param in
	 * @param bodySize
	 * @return
	 */
	public String getBody(IoBuffer in,int bodySize){
		byte[] bodyBytes=new byte[bodySize];
		in.get(bodyBytes, 0, bodySize);
		String body="";
		try {
			body = new String(bodyBytes,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info("从缓存区获取报文体为"+body+"");
		return body;
	}
	
	
	public static void main(String[] args) {
		IoBuffer buffer=IoBuffer.allocate(100,false);
		byte[] msg = new RequestMessage("0", 2, 28).getBuffer();
		buffer.put(msg);
//		buffer.putInt(4);
		buffer.flip();
//		byte[] bytes=new byte[4];
//		buffer.get(bytes);
		int n=buffer.getInt();
		System.out.println(n);
		buffer.prefixedDataAvailable(4,6);
	}

}


