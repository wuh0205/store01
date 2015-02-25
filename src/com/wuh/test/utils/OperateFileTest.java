package com.wuh.test.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import com.wuh.test.bean.LogLine;

public class OperateFileTest {
	/*private static String inputPath="E:\\fileTest\\";
	private static String outPath="E:\\fileTest\\export\\";
	private static String orderPath="E:\\fileTest\\order\\";
	private static String resultPath="E:\\fileTest\\result\\";*/
	private static String inputPath="E:\\logs\\";
	private static String outPath="E:\\logs\\export\\";
	private static String orderPath="E:\\logs\\order\\";
	private static String resultPath="E:\\logs\\result\\";
	
	
	public static void start(String inputPath){
		Long startTime=System.currentTimeMillis();
		deleteFile(outPath);
		deleteFile(orderPath);
		deleteFile(resultPath);
		System.out.println("开始执行"+inputPath+"目录下文件");
		File file=new File(inputPath);
		List<Integer> indexList=new ArrayList<Integer>();
		String fileNameAarray[]=file.list();
		for(int i=0;i<fileNameAarray.length;i++){
			String arr[]=fileNameAarray[i].split("[.]");
			if(arr.length==3){
				indexList.add(Integer.parseInt(arr[2]));
			}else{
				indexList.add(0);
			}
		}
		Collections.sort(indexList);
		Collections.reverse(indexList);
		for(Integer il:indexList){
			String index=String.valueOf(il);
			String filePath=inputPath+"declarationLogger.log";
			if(filePath.indexOf("declarationLogger.log")==-1){
				continue;
			}
			if(!index.equals("0")){
				filePath+="."+index;
			}
			readerAndWriter(filePath);
		}
		System.out.println("生成export下文件共耗时:"+String.valueOf(System.currentTimeMillis()-startTime)+"ms");
	}
	
	public static void readerAndWriter(String filePath){
		System.out.println(filePath+"----开始读取并拆分");
		Map<String,List<String>> writeMap=new HashMap<String,List<String>>();
		try {
			List<String> lineList=FileUtils.readLines(new File(filePath), "utf-8");
			for(String lineStr:lineList){
				List<String> subList=new ArrayList<String>();
				String outFileName=lineStr.substring(lineStr.indexOf("[Q")+1, lineStr.indexOf("],"));
				if(!writeMap.containsKey(outFileName)){
					subList.add(lineStr);
					writeMap.put(outFileName, subList);
				}else{
					List<String> oldSubList=writeMap.get(outFileName);
					subList.add(lineStr);
					subList.addAll(oldSubList);
					writeMap.remove(outFileName);
					writeMap.put(outFileName, subList);
				}
				
			}
			for(String key:writeMap.keySet()){
				List<String> outLineList=writeMap.get(key);
				FileUtils.writeLines(new File(outPath+key+".log"), outLineList, true);
				System.out.println("------文件："+key+".log"+"----输出结束");
			}
			System.out.println(filePath+"----执行完毕");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static List<LogLine> orderLines(List<String> lineList){
		List<LogLine> logLineList=new ArrayList<LogLine>();
		for(String line:lineList){
			LogLine bean=new LogLine();
			String num=line.substring(line.indexOf("],")+5, line.indexOf(",时间"));
			int type=line.substring(line.indexOf("],")+2, line.indexOf("列："+num)).equals("入")?1:0;
			bean.setNum(Integer.parseInt(num));
			bean.setType(type);
			bean.setContent(line);
			logLineList.add(bean);
		}
		Collections.sort(logLineList);
		return logLineList;
	}
	
	public static List<LogLine> checkLines(List<LogLine> logLineList){
		List<LogLine> problemList=new ArrayList<LogLine>();
		for(int i=0;i<logLineList.size();i++){
			LogLine line_1=logLineList.get(i);
			if(i==logLineList.size()-1){
				break;
			}
			if(line_1.getNum()!=logLineList.get(i+1).getNum()){
				problemList.add(line_1);
			}else{
				i+=1;
			}
		}
		return problemList;
	}
	
	public static List<String> parseStringList(List<LogLine> logLineList){
		List<String> lineList=new ArrayList<String>();
		for(LogLine bean:logLineList){
			lineList.add(bean.getContent());
		}
		return lineList;
	}
	
	public static void handleQLine(){
		 long startTime=System.currentTimeMillis();
		 Iterator<File> fileIteraor = FileUtils.iterateFiles(new File(outPath), null, false);
		 while(fileIteraor.hasNext()){
			 File qFile=fileIteraor.next();
			 String fileName=qFile.getName();
			 try {
				List<String> lineList=FileUtils.readLines(qFile, "utf-8");
				/*FileUtils.writeLines(new File(orderPath+fileName+".log"), parseStringList(orderLines(lineList)), true);
				System.out.println("##文件："+fileName+"##排序完毕");*/
				List<String> exceptionList=parseStringList(checkLines(orderLines(lineList)));
				if(exceptionList!=null&&exceptionList.size()>0){
					FileUtils.writeLines(new File(resultPath+fileName), parseStringList(checkLines(orderLines(lineList))), true);
					System.out.println("##文件："+fileName+"##异常输出完毕");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		 System.out.println("生成order和result下文件共耗时:"+String.valueOf(System.currentTimeMillis()-startTime)+"ms");
	}
	
	public static void deleteFile(String path){
		File file=new File(path);
		if(file.exists()){
			try {
				FileUtils.deleteDirectory(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("文件："+file.getName()+"已删除");
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		start(inputPath);
		handleQLine();
	}

}
