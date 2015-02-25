package mina.test.protocol;

import java.io.Serializable;
import java.util.Arrays;


/**
 * 
 * <b>Application name:</b>上海黄金交易所风险监控系统<br>
 * <b>Application describing:</b>消息头 <br>
 * <b>Copyright:</b>Copyright &copy; 2014 东软 金融事业部版权所有。<br>
 * <b>Company:</b>Neusoft<br>
 * <b>Date:</b>2014-7-23<br>
 * @author zhengyan
 * @version $Revision: 1.0 $
 */
public class Head implements Serializable{
    
    /**
     * Create date:2014-11-8
     * Describe   :[字段描述]
     */
    private static final long serialVersionUID = -8859826780777858733L;

    
    public static final int HEAD_SIZE = 16;
    
    //建立socket连接后，引擎向前置发送报文的MsgId
    public static final int CONNECT_REQ_MSGID = 0x000001;
    
    //建立socket连接后，前置向引擎回应答报文的MsgId
    public static final int CONNECT_RES_MSGID = 0x000002;
    
    private byte[] buffer;
    
    private int msgId;
    
    private int bodySize;
    
    private int seqNo;
    
    private int status;

    public Head(int msgId, int bodySize,int seqNo) {
        this.msgId = msgId;
        this.bodySize = bodySize;
        this.seqNo = seqNo;
        byte[] temp = null;
        try {
            buffer = new byte[16];
            temp = new byte[4];
            temp = this.toLH(msgId);
            System.arraycopy(temp, 0, buffer, 0, temp.length);//msgId
            
            temp = this.toLH(0);
            System.arraycopy(temp, 0, buffer, 4, temp.length); //status
            
            temp = this.toLH(bodySize);
            System.arraycopy(temp, 0, buffer, 8, temp.length); //msgSize
            
            temp = this.toLH(seqNo);
            System.arraycopy(temp, 0, buffer, 12, temp.length);//seqNo
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Head(byte[] bytes) {
        byte[] bufferMsgId = new byte[4];
        System.arraycopy(bytes, 0, bufferMsgId, 0, 4);
        this.msgId = unsigned4BytesToInt(bufferMsgId);
        
        byte[] bufferStatus = new byte[4];
        System.arraycopy(bytes, 4, bufferStatus, 0, 4);
        this.status = unsigned4BytesToInt(bufferStatus);
        
        byte[] bufferBodySize = new byte[4];
        System.arraycopy(bytes, 8, bufferBodySize, 0, 4);
        this.bodySize = unsigned4BytesToInt(bufferBodySize);
        
        byte[] bufferSeqNo = new byte[4];
        System.arraycopy(bytes, 12, bufferSeqNo, 0, 4);
        this.seqNo = unsigned4BytesToInt(bufferSeqNo);
        
    }
    
    /**
     * 将int转为低字节在前，高字节在后的byte数组
     */
    public byte[] toLH(int n) {
        byte[] b = new byte[4];
        b[0] = (byte) (n & 0xFF);
        b[1] = (byte) (n >> 8 & 0xFF);
        b[2] = (byte) (n >> 16 & 0xFF);
        b[3] = (byte) (n >> 24 & 0xFF);
        return b;
    }
    
    /**
     * 将int转为高字节在前，低字节在后的byte数组
     */
    public byte[] toBH(int n) {
        byte[] b = new byte[4];
        b[3] = (byte) (n & 0xFF);
        b[2] = (byte) (n >> 8 & 0xFF);
        b[1] = (byte) (n >> 16 & 0xFF);
        b[0] = (byte) (n >> 24 & 0xFF);
        return b;
    }

    
    public int unsigned4BytesToInt(byte[] buf) {  
        int firstByte = 0;  
        int secondByte = 0;  
        int thirdByte = 0;  
        int fourthByte = 0;  
        int index = 0;  
        firstByte = (0x000000FF & ((int) buf[index+ 3]));  
        secondByte = (0x000000FF & ((int) buf[index + 2]));  
        thirdByte = (0x000000FF & ((int) buf[index + 1]));  
        fourthByte = (0x000000FF & ((int) buf[index ]));  
        return (firstByte << 24 | secondByte << 16 | thirdByte << 8 | fourthByte) & 0xFFFFFFFF;  
    }  
    


    public byte[] getBuffer() {
        return buffer;
    }
    public int getBodySize() {
        return bodySize;
    }

    public int getMsgId() {
        return msgId;
    }

    public int getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(int seqNo) {
		this.seqNo = seqNo;
	}

	public int getStatus() {
        return status;
    }
	
	
	
	public String toHexString(byte[] b){
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i <b.length-1 ; i++){
            buffer.append(toHexString(b[i]));
        }
        return buffer.toString();
    }

    
    public String toHexString(byte b){
        String s = Integer.toHexString(b & 0xFF);
        if (s.length() == 1){
            return "0" + s;
        }else{
            return s;
        }
    }

	@Override
	public String toString() {
		return "Head [buffer=" + Arrays.toString(buffer) + ", msgId=" + msgId
				+ ", bodySize=" + bodySize + ", seqNo=" + seqNo + ", status="
				+ status + "]";
	}
	
	static private int makeInt(byte b3, byte b2, byte b1, byte b0) {
        return (((b3       ) << 24) |
                ((b2 & 0xff) << 16) |
                ((b1 & 0xff) <<  8) |
                ((b0 & 0xff)      ));
    }
    
	public static void main(String[] args) {
		Head head =new Head(2,28,0);
		byte[] bytes=head.toLH(28);
		int num=makeInt(bytes[3],bytes[2],bytes[1],bytes[0]);
		System.out.println(num);
			
	}
    
}
