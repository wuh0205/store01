package mina.test.protocol;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;


import com.alibaba.fastjson.JSON;

/**
 * 
 * <b>Application name:</b>上海黄金交易所风险监控系统<br>
 * <b>Application describing:</b> 请求消息类<br>
 * <b>Copyright:</b>Copyright &copy; 2014 东软 金融事业部版权所有。<br>
 * <b>Company:</b>Neusoft<br>
 * <b>Date:</b>2014-7-23<br>
 * @author zhengyan
 * @version $Revision: 1.0 $
 */
public class RequestMessage{
    
    private byte[] buffer;
    
    private static final String ENGINE_ID = "1";
    
    public RequestMessage(String lastReceivedSeqNo){
    	Map<String,String> bodyMap = new HashMap<String,String>();
    	bodyMap.put("seId", ENGINE_ID);
    	if(StringUtils.isEmpty(lastReceivedSeqNo)){
    		lastReceivedSeqNo = "0";
    	}
    	bodyMap.put("lastReceivedSeqNo", lastReceivedSeqNo);
    	byte[] body = JSON.toJSONString(bodyMap).getBytes();
    	
        this.buffer = new byte[Head.HEAD_SIZE + body.length];
        Head head = new Head(Head.CONNECT_REQ_MSGID, body.length, 0);
        System.arraycopy(head.getBuffer(), 0, buffer, 0, Head.HEAD_SIZE);
        System.arraycopy(body, 0, buffer, Head.HEAD_SIZE, body.length);
    }
    
    public RequestMessage(String lastReceivedSeqNo,int msgId,int bodySize){
    	Map<String,String> bodyMap = new HashMap<String,String>();
    	bodyMap.put("seId", ENGINE_ID);
    	if(StringUtils.isEmpty(lastReceivedSeqNo)){
    		lastReceivedSeqNo = "0";
    	}
    	bodyMap.put("lastReceivedSeqNo", lastReceivedSeqNo);
    	byte[] body = JSON.toJSONString(bodyMap).getBytes();
    	
    	this.buffer = new byte[Head.HEAD_SIZE + body.length];
    	Head head = new Head(msgId, bodySize, 0);
    	System.arraycopy(head.getBuffer(), 0, buffer, 0, Head.HEAD_SIZE);
    	System.arraycopy(body, 0, buffer, Head.HEAD_SIZE, body.length);
    }
    
    public String getMessage(String lastReceivedSeqNo){
    	Map<String,String> bodyMap = new HashMap<String,String>();
    	bodyMap.put("seId", ENGINE_ID);
    	if(StringUtils.isEmpty(lastReceivedSeqNo)){
    		lastReceivedSeqNo = "0";
    	}
    	bodyMap.put("lastReceivedSeqNo", lastReceivedSeqNo);
    	String body = JSON.toJSONString(bodyMap);
    	byte[] bodyArr = JSON.toJSONString(bodyMap).getBytes();
    	int msgId=0x000001;
    	int stats=0;
    	int bodySize=bodyArr.length;
    	int seqNo=0;
    	String head=String.valueOf(msgId)+String.valueOf(stats)+String.valueOf(bodySize)+String.valueOf(seqNo);
		return head+body;
    }

    public byte[] getBuffer() {
        return buffer;
    }
    
}
