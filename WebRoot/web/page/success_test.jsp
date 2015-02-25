<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path; 
%>
<%
     String age=(String)request.getAttribute("age");//如果不强转报：cannot convert from Object to String
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
   <h1>用户名：${name}</h1>
   <h1>登录状态：${status}</h2>
   <h1>年龄：<%=age %> ${age}</h1>
   <h1>地址：<%=request.getAttribute("address") %></h1>
</body>
</html>