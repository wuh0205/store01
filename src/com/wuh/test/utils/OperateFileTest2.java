package com.wuh.test.utils;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.io.FileUtils;

public class OperateFileTest2 {
	private static Map<String,List<String>> typeTaskMap=new ConcurrentHashMap<String,List<String>>();
	private static Map<String, String[]> typeQMap = null;
	private static void initMapping() {
        typeQMap = new HashMap<String, String[]>();
        //现货
        //报单
        typeQMap.put("SPOT_ENTRUST", new String[] {"Q1001","Q1002","Q1003","Q1004","Q1005","Q1006","Q5001"});
        //撤单
        typeQMap.put("SPOT_CANCEL_ENTRUST", new String[] {"Q1001","Q1002","Q1003","Q1005","Q1006","Q5002"});
        //成交
        typeQMap.put("SPOT_TRANSACTION", new String[] {"Q1001","Q1002","Q1003","Q1005","Q1006","Q1007","Q1008","Q1009","Q5003"});
        //现货T+N
        //报单
        typeQMap.put("SPOT_TN_ENTRUST", new String[] {"Q1006","Q2001","Q2002","Q2003","Q2004","Q5001"});
        //撤单
        typeQMap.put("SPOT_TN_CANCEL_ENTRUST", new String[] {"Q1006","Q2001","Q2002","Q2003","Q5002"});
        //成交
        typeQMap.put("SPOT_TN_TRANSACTION", new String[] {"Q1006","Q2001","Q2002","Q2003","Q2005","Q2006","Q2007","Q2008","Q2009","Q2010","Q2011","Q5003"});
        //现货T+D
        //报单
        typeQMap.put("SPOT_TD_ENTRUST", new String[] {"Q1006","Q3001","Q3002","Q3003","Q3004","Q4005","Q5001"});
         //撤单
        typeQMap.put("SPOT_TD_CANCEL_ENTRUST", new String[] {"Q1006","Q3001","Q3002","Q3003","Q4003","Q4004","Q5002"});
        //成交
        typeQMap.put("SPOT_TD_TRANSACTION", new String[] {"Q1006","Q3001","Q3002","Q3003","Q3005","Q3006","Q3007","Q3008","Q3009","Q3010","Q3011","Q4001","Q4002","Q5003"});
    }

	private static String inputPath="D:\\trading-logs\\logs\\";
	private static String qPath="E:\\logs\\export\\";
	
	public static void handleTaskFile(String inputPath){
		Long startTime=System.currentTimeMillis();
		Iterator<File> fileIteraor = FileUtils.iterateFiles(new File(inputPath), null, false);
		 while(fileIteraor.hasNext()){
			 File qFile=fileIteraor.next();
			 String fileName=qFile.getName();
			 if(fileName.indexOf("task.log")==-1){
				 continue;
			 }
			 String path=inputPath+fileName;
			 saveTaskMap(path);
		 }
		 System.out.println("加载typeTaskMap共耗时:"+String.valueOf(System.currentTimeMillis()-startTime)+"ms");
	}
	
	public static void saveTaskMap(String filePath){
		System.out.println(filePath+"----开始读取并拆分");
		List<String> subList=null;
		String line=null;
		try {
			List<String> lineList=FileUtils.readLines(new File(filePath), "utf-8");
			for(int i=0;i<lineList.size();i++){
				subList=new ArrayList<String>();
				line=lineList.get(i);
				String type=null;
				if(line.indexOf("type")>-1){
					type=line.substring(line.indexOf("type:")+5, line.indexOf("||")-1);
				}else{
					continue;
				}
				if(i>0){
					String sqlNoLine=lineList.get(i-1);
					subList.add(sqlNoLine);
					if(!typeTaskMap.containsKey(type)){
						typeTaskMap.put(type, subList);
					}else{
						List<String> oldSubList=typeTaskMap.get(type);
						subList.addAll(oldSubList);
						typeTaskMap.remove(type);
						typeTaskMap.put(type, subList);
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(filePath+"----taskMap装载完毕----");
	}
	
	public static void checkQ(){
		Long startTime=System.currentTimeMillis();
		for(String type:typeTaskMap.keySet()){
			String qFileName="";
			String qTypeArr[]=typeQMap.get(type);
			if(qTypeArr==null||qTypeArr.length==0){
				continue;
			}else{
				for(int i=0;i<qTypeArr.length;i++){
					qFileName+=qTypeArr[i]+",";
				}
			}
			Set<String> linesSet=readerQFile(qTypeArr);
			String sqlNo=null;
			for(String task:typeTaskMap.get(type)){
				sqlNo=task.substring(task.indexOf("seqNo:")+7, task.indexOf("|| bodySize")-1);
				if(!linesSet.contains(sqlNo)){
					System.out.println("sqlNo:"+sqlNo+"不在"+qFileName+"中");
				}
			}
			System.out.println("type:"+type+"查询完毕");
		}
		System.out.println("对比sqlNo共耗时:"+String.valueOf(System.currentTimeMillis()-startTime)+"ms");
	}
	
	public static Set<String> readerQFile(String[] qTypeArr){
		Set<String> linesSet=new HashSet<String>();
		List<String> linesList=new ArrayList<String>();
		String qFilePath=null;
		for(int i=0;i<qTypeArr.length;i++){
			qFilePath=qPath+qTypeArr[i]+".log";
			try {
				linesList=FileUtils.readLines(new File(qFilePath), "GB2312");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String sqlNo=null;
			for(String line:linesList){
				sqlNo=line.substring(line.indexOf("列：")+2, line.indexOf("时间：")-1);
				linesSet.add(sqlNo);
			}
		}
		return linesSet;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		OperateFileTest test=new OperateFileTest();
//		test.start(inputPath);
		
		initMapping();
		handleTaskFile(inputPath);
		checkQ();
		
	}



}
