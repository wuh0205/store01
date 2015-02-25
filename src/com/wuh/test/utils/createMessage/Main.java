package com.wuh.test.utils.createMessage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

public class Main {
	
	private static String readerPath="D:\\createMessage\\outputList.txt";
	private static String writePath="D:\\createMessage\\message\\message.txt";
	
	public void handler(){
		System.out.println("----生成报文开始----");
		List<String> lineList=readerFile(readerPath);
		List<String> writeList=new ArrayList<String>();
		MessageTemplet msgtemp=new MessageTemplet();
		writeList.add(msgtemp.getMessageHead());
		for(String str:lineList){
			if(StringUtils.isEmpty(str)){
				continue;
			}
			InputBean bean=PackagingObj(str);
			writeList.add("#----------------------------      "+bean.getMessageName()+" (数量："+bean.getNum()+")    ----------------------------");
			for(int i=0;i<Integer.parseInt(bean.getNum());i++){
				writeList.add(msgtemp.getMessageContent(bean));
			}
		}
		writeList.add(msgtemp.getMessageEnd());
		writerFile(writeList,writePath);
		System.out.println("----生成报文结束---");
	}
	
	public InputBean PackagingObj(String str){
		String[] arr=str.split(",");
		if(arr.length!=7){
			throw new RuntimeException("清单中数据不符合规范！");
		}
		InputBean bean=new InputBean();
		bean.setMessageName(arr[0]);
		bean.setNum(arr[1]);
		bean.setTid(arr[2].split(":")[1]);
		bean.setClientID(arr[3].split(":")[1]);
		bean.setInstID(arr[4].split(":")[1]);
		bean.setMemberID(arr[5].split(":")[1]);
		bean.setBuyOrSell(arr[6].split(":")[1]);
		System.out.println("==封装清单对象： "+bean.toString());
		return bean;
	}
	
	private  List<String> readerFile(String path){
		System.out.println("==读取清单");
		List<String> lineList=null;
		try {
			lineList = FileUtils.readLines(new File(path), "utf-8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lineList;
	}
	
	private  void writerFile(List<String> writeList,String path){
		System.out.println("==写报文");
		File file=new File(path); 
		if(file.exists()){
			file.delete();
			System.out.println("文件已存在，删除文件："+path);
		}
		try {
			FileUtils.writeLines(file, writeList, true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Main main=new Main();
		main.handler();

	}

}
