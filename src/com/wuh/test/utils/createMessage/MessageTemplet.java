package com.wuh.test.utils.createMessage;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MessageTemplet {
	
	private int headsequenceNo=10000;
	
	private int orderNo=10000000;
	
    private String messageContent;
    
    private String messageHead;
    
    private String messageEnd;
    
    public MessageTemplet(){
    	initMessageTemplet();
    }
    
	public void initMessageTemplet(){
		System.out.println("==开始初始化报文模板");
		Properties pros = new Properties();
        InputStream in = MessageTemplet.class.getResourceAsStream("/socket.properties");
        try {
			pros.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        StringBuffer contentSbr=new StringBuffer();
        StringBuffer headSbr=new StringBuffer();
        headSbr.append("# 交易日初始化\r\n").append(pros.get("str1")+"\r\n\r\n\r\n");
        headSbr.append("# 盘前静态文件生成应答-成功\r\n").append(pros.get("str2")+"\r\n\r\n\r\n");
        headSbr.append("# 设置间隔时间为100ms\r\n"+"interval 100\r\n");
        contentSbr.append("1 "+pros.get("str3")).append("\r\n1  ").append(pros.get("str4"));
        
        messageHead=headSbr.toString();
        messageContent=contentSbr.toString();
        messageEnd="\r\n# 暂停 1000s\r\nsleep 1000\r\nquit";
        System.out.println("==初始化报文模板结束,模板为：["+messageContent+"]");
	}

	public String getMessageContent(InputBean inputBean) {
		String messageNew=messageContent.replaceAll("#tid#", inputBean.getTid())
				.replaceAll("#clientID#", inputBean.getClientID())
				.replaceAll("#instID#", inputBean.getInstID())
				.replaceAll("#memberID#", inputBean.getMemberID())
				.replaceAll("#buyOrSell#", inputBean.getBuyOrSell())
				.replaceAll("#headsequenceNo#",
						String.valueOf(headsequenceNo))
				.replaceAll("#orderNo#", String.valueOf(orderNo));
		headsequenceNo++;
		orderNo++;
		return messageNew;
	}

	public String getMessageHead() {
		return messageHead;
	}

	public String getMessageEnd() {
		return messageEnd;
	}

	
}
