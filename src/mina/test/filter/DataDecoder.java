package mina.test.filter;

import java.io.UnsupportedEncodingException;

import mina.test.protocol.Head;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

public class DataDecoder extends CumulativeProtocolDecoder {
	private static Log log = LogFactory.getLog(DataDecoder.class);
	private static final int HEAD_SIZE	= 16;
	private static boolean existHead=false;
	private static int bodySize=0;
	

	@Override
	protected boolean doDecode(IoSession session, IoBuffer in,
			ProtocolDecoderOutput out) throws Exception {
		// TODO Auto-generated method stub
		int start=in.position();//获取buffer当前位置
		in.position(start);//设置buffer读取位置
		
		log.info("## positionStart:"+start+"## limit:"+in.limit()+"##capacity: "+in.capacity());
		//读取报文头
		if(!existHead){//在黏包情况下，如果报文头已被读取，则无需再次读取，否则读取
			if(in.remaining()<HEAD_SIZE){//如果缓冲区剩余数据不足以完整读取报文头，重置数据（重置后加载进来的数据为：之前缓冲区剩余数据+父类接收的新数据）
				return false;//如果本次数据还有未读取完的，就等新数据到了后，将含有剩余数据与新数据整合，再次调用doDecode，如果数据全都读取完毕，则清空IoBuffer 缓冲区
			}else{
				bodySize=getHead(in).getBodySize();
				log.info("=== bodySize:"+bodySize);
			}
		}
		//读取报文体
		if(bodySize>in.remaining()){//在黏包情况下,如果缓冲区剩余数据不足以读出报文体，重置数据
			existHead=true;
			return false;
		}else{
			if(!existHead){//在黏包情况下，如果不存在报文头，设置buffer的读取位置，有报文头则从起始位置读取
				in.position(start+HEAD_SIZE);
			}
			String body=body(in,bodySize);
			existHead=false;
			out.write(body);
			log.info("## position:"+in.position()+"## limit:"+in.limit());
			if(in.remaining()>0){//如果缓冲区还有数据，将本次读取后剩余数据重新加载
				return true;//将本次读取后buffer中剩余数据重新加载
			}
		}
		
		return false;//此处标示buffer中数据完整处理完毕，让父类接收下一个数据包
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
	public String body(IoBuffer in,int bodySize){
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
	

}
