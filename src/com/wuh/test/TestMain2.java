package com.wuh.test;

import java.lang.reflect.InvocationTargetException;

import javax.swing.JButton;
import javax.swing.JFrame;

import org.apache.commons.beanutils.MethodUtils;

public class TestMain2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*JFrame frame=new JFrame();
		JButton button=new JButton("click me");
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(button);
		frame.setSize(300, 300);
		frame.setVisible(true);*/
//		System.out.println("a..");
		for(;;){

		System.out.println("b..");
		throw new RuntimeException("异常");
		}
		
	}

}
