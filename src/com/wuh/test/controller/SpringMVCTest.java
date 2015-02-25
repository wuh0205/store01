package com.wuh.test.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.wuh.test.bean.User;
import com.wuh.test.dao.impl.Test;
import com.wuh.test.service.LoginService;

@Controller
@RequestMapping(value="/springmvc/test")
public class SpringMVCTest {
	static Log log = LogFactory.getLog(SpringMVCTest.class);
	@Autowired
	LoginService loginService;
//	@Resource(name="testDao")
	private Test testService;
	
	@RequestMapping(value="/showPage")
	public String showPage(Model model){
		return "login_test";
	}
	
	@RequestMapping(value = "/easyLogin")  
	public void easyLogin(@ModelAttribute User user,HttpServletRequest request,HttpServletResponse response){
		log.info("---------easyLogin begin!-------");
//		testService.queryUser((long) 1);
		Map<String, String> map=new HashMap<String, String>();
		String name=user.getName();
		String msg=loginService.login(user);
		
		if(msg.equals("success")){
			map.put("status", msg);
			map.put("name", name);
			request.getSession().setAttribute("USER_INFO", map);
		}else{
			map.put("status", msg);
		}
		String jsonStr=JSON.toJSONString(map);
		writerData(response,jsonStr);
		log.info("---------easyLogin end!-------");
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/successPage")
	public String successPage(HttpServletRequest request,Model model){
		String age=(String) request.getParameter("age");
		String address=(String)request.getParameter("address");
		Map<String, String> map=(Map<String, String>) request.getSession().getAttribute("USER_INFO");
		if(map!=null&&!map.isEmpty()){
			model.addAttribute("name",map.get("name"));
			model.addAttribute("status",map.get("status"));
		}
		request.setAttribute("age", age);
		request.setAttribute("address", address);
		return "success_test";
	}
	
	@RequestMapping(value = "/returnDataTest")  
	public ModelAndView returnDataTest(HttpServletRequest request,HttpServletResponse respons){
		User user=new User();
		user.setName("wuh");
		user.setAge("26");
		Map<String,User> map=new HashMap<String,User>();
		map.put("user", user);
		return new ModelAndView("requestTest",map);
	}
	
	/**
	 * 转发（共享request，response）
	 * @param request
	 * @param respons
	 * @return
	 */
	@RequestMapping(value = "/forwardTest")
	public String forwardTest(HttpServletRequest request,HttpServletResponse respons){
		return "forward:/springmvc/test/successPage";
	}
	
	/**
	 * 重定向
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "/redirectTest")
	public String redirectTest(RedirectAttributes redirectAttributes){
		redirectAttributes.addFlashAttribute("age", "25");
		redirectAttributes.addFlashAttribute("address", "dalian");
		return "redirect:/springmvc/test/successPage";
	}
	
	@RequestMapping(value = "/modelAndViewTest")
	public ModelAndView modelAndViewTest(HttpServletRequest request,HttpServletResponse respons){
		ModelMap mmap = new ModelMap();  //普通Map也可
		mmap.addAttribute("age", "26");
		mmap.addAttribute("address", "shanghai");
		return new ModelAndView("redirect:/springmvc/test/successPage",mmap);
	}
	
	/**
	 * 通过PrintWriter将响应数据写入response，ajax可以接受到这个数据
	 * @param response
	 * @param jsonStr
	 */
	public void writerData(HttpServletResponse response,String jsonStr){
		PrintWriter printWriter = null;
		try {
			printWriter=response.getWriter();
			printWriter.print(jsonStr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(printWriter!=null){
				printWriter.flush();
				printWriter.close();
			}
		}

	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
