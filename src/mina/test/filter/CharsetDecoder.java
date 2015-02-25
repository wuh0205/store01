package mina.test.filter;

import java.nio.charset.Charset;

import mina.test.protocol.Head;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

public class CharsetDecoder implements ProtocolDecoder{
    
    private final static Charset charset = Charset.forName("UTF-8");    
    // 可变的IoBuffer数据缓冲区
//    private IoBuffer buff = IoBuffer.allocate(5000).setAutoExpand(true);
	@Override
	public void decode(IoSession session, IoBuffer in, ProtocolDecoderOutput out)
			throws Exception {
		IoBuffer buff = IoBuffer.allocate(100).setAutoExpand(true);
		// TODO Auto-generated method stub
		int num=0;
		System.out.println("#########解码#########");
		System.out.println(in.limit());
		// 如果有消息
		while (in.hasRemaining()) {
			byte b = in.get();
			buff.put(b);
			int bodySize=0;
			if(num==15){
				buff.flip();
				byte[] headBytes = new byte[16];
				buff.get(headBytes);
				Head head=new Head(headBytes);
				bodySize=head.getBodySize();
			}
			if(num==16+bodySize){
				buff.flip();
				byte[] bodyBytes = new byte[bodySize];
			}
			num++;
		}
//		System.out.println(num);
//		System.out.println(buff.limit());
//		buff.flip();
//		byte[] bytes = new byte[buff.limit()];
//		buff.get(bytes);
//        String message = new String(bytes, charset);
//        System.out.println("解码后数据message: " + message);
//        while (in.hasRemaining()) {
//            // 判断消息是否是结束符，不同平台的结束符也不一样；
//            // windows换行符（\r\n）就认为是一个完整消息的结束符了； UNIX 是\n；MAC 是\r
//            byte b = in.get();
//            if (b == '\n') {
//                buff.flip();
//                byte[] bytes = new byte[buff.limit()];
//                buff.get(bytes);
//                String message = new String(bytes, charset);
//                
//                buff = IoBuffer.allocate(16).setAutoExpand(true);
//                
//                // 如果结束了，就写入转码后的数据
//                out.write(message);
//                System.out.println("解码后数据message: " + message);
//            } else {
//                buff.put(b);
//            }
//        }
	}

	@Override
	public void finishDecode(IoSession session, ProtocolDecoderOutput out)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("#########完成解码#########");
	}

	@Override
	public void dispose(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("#########dispose#########");
	}

}
