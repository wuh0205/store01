package com.wuh.test.utils;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import com.wuh.test.bean.Bill;
import com.wuh.test.bean.Customer;

public class BookstoreTest {
	private static String recordsFilePath ="E:\\practice\\records.txt";
	private static String billFilePath ="E:\\practice\\bill.txt";
	private static String resultFilePath ="E:\\result.txt";
	private static Map<String, double[]> ruleMap = null;
	private static Map<String,Customer> customerInfoMap=new HashMap<String,Customer>();
	
	private static void initRuleMapping(){
		ruleMap=new HashMap<String, double[]>();
		ruleMap.put("A", new double[]{2,500,100});
		ruleMap.put("B", new double[]{1.5,500,100});
		ruleMap.put("C", new double[]{1.5,200,50});
		ruleMap.put("D", new double[]{1,300,100});
		ruleMap.put("E", new double[]{0.5,200,100});
	}
	
	private  static void readerCustomerInfo(){
		List<String> customerLineList=readerFile(recordsFilePath);
		for(String line:customerLineList){
			if(StringUtils.isEmpty(line)){
				continue;
			}
			Customer customer=new Customer();
			String [] lineArr=line.split(",");
			String name=lineArr[0];
			customer.setName(name);
			customer.setTotal(Double.parseDouble(lineArr[1]));
			customer.setIntegral(Integer.parseInt(lineArr[2]));
			customerInfoMap.put(name, customer);
		}
	}
	
	private static List<Bill> readerBill(){
		List<Bill> billInfoList=new ArrayList<Bill>();
		List<String> billLineList=readerFile(billFilePath);
		for(String line:billLineList){
			Bill bill=new Bill();
			String [] lineArr=line.split(",");
			bill.setCustomerName(lineArr[0]);
			bill.setBookName(lineArr[1]);
			bill.setBookType(lineArr[2]);
			bill.setCount(Integer.parseInt(lineArr[3]));
			bill.setPrice(Double.parseDouble(lineArr[4]));
			billInfoList.add(bill);
		}
		return billInfoList;
	}
	
	private static void compute(Bill bill){
		Customer customer=customerInfoMap.get(bill.getCustomerName());
		String name=customer.getName();
		double total=customer.getTotal();
		double integral=customer.getIntegral();
		
		String bookType=bill.getBookType();
		if(!ruleMap.containsKey(bookType)){
			return;
		}
		double[] ruleArr=ruleMap.get(bookType);
		
		double newTotal=bill.getPrice()*bill.getCount();
		int newIntegral=(int) (Math.floor(newTotal)*ruleArr[0]); 
		if(newTotal>ruleArr[1]){
			newIntegral+=ruleArr[2];
		}
		total+=newTotal;
		integral+=newIntegral;
		
		customer.setTotal(formatDouble(total));
		customer.setIntegral(integral);
		customerInfoMap.remove(name);
		customerInfoMap.put(name, customer);
	}
	
	private static List<String> readerFile(String path){
		List<String> lineList=null;
		try {
			lineList = FileUtils.readLines(new File(path), "utf-8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lineList;
	}
	
	
	private static void writerFile(List<String> writeList,String path){
		File file=new File(path); 
		if(file.exists()){
			file.delete();
			System.out.println("删除文件："+path);
		}
		try {
			FileUtils.writeLines(file, writeList, true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private static double formatDouble(double d){
		BigDecimal bg = new BigDecimal(d);
		return bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	private static void writerResultFile(){
		List<String> writerList=new ArrayList<String>();
		List<Customer> sortList=new ArrayList<Customer>();
		Customer customer=null;
		for(String key:customerInfoMap.keySet()){
			customer=customerInfoMap.get(key);
			sortList.add(customer);
		}
		Collections.sort(sortList);
		for(Customer ctr:sortList){
			writerList.add(ctr.toString());
		}
		writerFile(writerList,resultFilePath);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Long startTime=System.currentTimeMillis();
		initRuleMapping();
		readerCustomerInfo();
		System.out.println("----读取客户信息完毕---");
		List<Bill> billInfoList=readerBill();
		System.out.println("----读取订单信息完毕---");
		for(Bill bill:billInfoList){
			if(customerInfoMap.containsKey(bill.getCustomerName())){
				System.out.println("-计算-");
				compute(bill);
			}
		}
		
		writerResultFile();
		System.out.println("共耗时:"+String.valueOf(System.currentTimeMillis()-startTime)+"ms");
	}

}
