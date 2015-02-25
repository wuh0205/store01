package com.wuh.test.utils;

import java.io.BufferedReader;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;

public class OperateFile {
	private static String encoding = "GB2312";
	private static String outPath = "E:\\export\\";
	private static Map qMap = new HashMap();
	private static List<String> Q3001List;
	private static List<String> Q3002List;
	private static List<String> Q3003List;
	private static List<String> Q3004List;

	public static void start(String inputPath) {
		// File file = new File(inputPath);
		// String fileNameArray[]=file.list();
		// String fileNameOrder[]=new String[10000];
		// for(int i=0;i<fileNameArray.length;i++){
		// String fileName=fileNameArray[i];
		// String filr[]=fileName.split("[.]");
		// int indes=Integer.parseInt(fileName.split("[.]")[2]);
		//
		// fileNameOrder[indes]=fileName;
		// }

		// for(int i=fileNameOrder.length;i>=0;i--){
		// String filePath=inputPath+fileNameOrder[i];
		// System.out.println("开始读取文件："+filePath);
		// readerAndWriter(filePath);

		// }
		// readerAndWriter("E:\\test\\declarationLogger.log.15");
		readerAndWriter2("E:\\test\\declarationLogger.log.15");
	}

	public static void readerAndWriter(String filePath) {
		try {
			String line = null;
			LineIterator lineIterator = FileUtils.lineIterator(new File(
					filePath), "utf-8");
			while (lineIterator.hasNext()) {
				line = lineIterator.next() + "\n";
				String outFileName = line.substring(line.indexOf("[Q") + 1,
						line.indexOf("],"));
				FileUtils.writeStringToFile(new File(outPath + outFileName
						+ ".log"), line, true);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void readerAndWriter2(String filePath) {
		try {
			String line = null;
			LineIterator lineIterator = FileUtils.lineIterator(new File(
					filePath), "utf-8");
			while (lineIterator.hasNext()) {
				line = lineIterator.next() + "\n";
				String outFileName = line.substring(line.indexOf("[Q") + 1,
						line.indexOf("],"));
				FileUtils.writeStringToFile(new File(outPath + outFileName
						+ ".log"), line, true);
				saveLine(line);
			}

			// for(int i=0;i<4;i++){
			// qMap.put(key, value)
			// }
			//
			// Set<String> keys = qMap.keySet();
			// for (String key : keys) {
			// FileUtils.writeLines(new File(key), (List<String>)
			// qMap.get(key));
			// }

			// for(int i=0;i<4;i++){
			// int n=3001;
			// String s=String.valueOf(n);
			// FileUtils.writeLines(new File(outPath+"Q"+s+".log"),
			// (List<String>) qMap.get(key));
			// }
			FileUtils.writeLines(new File(outPath + "Q3001" + ".log"),
					Q3001List);
			FileUtils.writeLines(new File(outPath + "Q3002" + ".log"),
					Q3002List);
			FileUtils.writeLines(new File(outPath + "Q3003" + ".log"),
					Q3003List);
			FileUtils.writeLines(new File(outPath + "Q3004" + ".log"),
					Q3004List);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void saveLine(String line) {
		String outFileName = line.substring(line.indexOf("[Q") + 1,
				line.indexOf("],"));
		if (outFileName.equals("Q3001")) {
			Q3001List.add(line);
		}
		if (outFileName.equals("Q3002")) {
			Q3002List.add(line);
		}
		if (outFileName.equals("Q3003")) {
			Q3003List.add(line);
		}
		if (outFileName.equals("Q3004")) {
			Q3004List.add(line);
		}

	}

	// public static void readerAndWriter1(String filePath){
	// File file = new File(filePath);
	// try {
	// BufferedReader reader = new BufferedReader(new InputStreamReader(new
	// FileInputStream(file),encoding));
	// LineIterator line2 = FileUtils.lineIterator(new File(filePath), "utf-8");
	// List<String> list=FileUtils.readLines(file, "utf-8");
	// String line = reader.readLine();
	// String outFileName=line.substring(line.indexOf("[Q"),
	// line.indexOf("],"));
	// while(line!=null){
	// FileUtils.writeStringToFile(new File(outPath+outFileName+".log"),
	// line,true);
	// line = reader.readLine();
	// }
	// reader.close();
	// System.out.println("开始读取文件："+filePath);
	// } catch (FileNotFoundException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }

	public static void readerAndWriter_2(String inputPath, String outPath) {
		File file = new File(inputPath);
		File dest = new File(outPath);
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			BufferedWriter writer = new BufferedWriter(new FileWriter(dest));
			String line = reader.readLine();
			while (line != null) {
				writer.write(line);
				line = reader.readLine();
			}
			writer.flush();
			reader.close();
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File dest = new File("E:\\test1.text");
		int c = 0;
		try {
			OutputStreamWriter out = new OutputStreamWriter(
					new FileOutputStream(dest), "UTF-8");
			out.write(String.valueOf(c));
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// start("E:\\test");
		InputStream is;
		try {
			is = new FileInputStream(new File("D:/Test.txt"));
			InputStreamReader fr = new InputStreamReader(is);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			FileWriter fw = new FileWriter("D:/Test.txt");
			String s = "hello world";
			fw.write(s, 0, s.length());
			fw.flush();
			
			File fi=new File("D:/Test.txt");
			FileOutputStream fos=new FileOutputStream(fi,true);//加true可以追加，不会覆盖
			fos.write(0);

			OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(fi,true),"utf-8");//增加""参数可以转字符集
			osw.write(s, 0, s.length());
			osw.flush();

			PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream("D:/Test2.txt")), true);
			pw.println(s);

			fos.close();
			fw.close();
			osw.close();
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		File file = new File("D:/Test.txt");  
		File dest1 = new File("D:/new.txt");  
		try {  
		    BufferedReader reader = new BufferedReader(new FileReader(file));  
		    BufferedWriter writer  = new BufferedWriter(new FileWriter(dest1));  
		    String line = reader.readLine();  
		    while(line!=null){  
		        writer.write(line);  
		        line = reader.readLine();  
		    }  
		    writer.flush();  
		    reader.close();  
		    writer.close();  
		} catch (FileNotFoundException e) {  
		    e.printStackTrace();  
		} catch (IOException e) {  
		    e.printStackTrace();  
		}  

	}

}
