package mina.test.filter;

import java.nio.charset.Charset;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

public class DataEncoder extends ProtocolEncoderAdapter{
	private static Log log = LogFactory.getLog(DataEncoder.class);

	@Override
	public void encode(IoSession session, Object message,
			ProtocolEncoderOutput out) throws Exception {
		// TODO Auto-generated method stub
		log.info("#############字符编码############");
        IoBuffer buff = IoBuffer.allocate(100).setAutoExpand(true);
        String str=String.valueOf(message);
        buff.putInt(str.getBytes(Charset.forName("utf-8")).length);
        buff.putString(str,Charset.forName("utf-8").newEncoder());
        buff.flip();
        out.write(buff);
	}

}
