<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path; 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%=basePath%>/web/common/jquery-1.9.1.min.js"></script>
<style type="text/css">
   .div_1{margin-bottom: 5px;}
   label[class="ps_l"] {padding-left: 16px;}
   button{width: 91px;height: 27px;margin-top: 9px;}
   .msg_s{font-family: monospace;margin: 8px 0px 0px 64px;color: red;}
</style>
</head>
<body>
   <div class="div_1">
     <label>用户名：</label>
     <input id='user_id' type='text'>
   </div>
   <div class="div_2">
     <label class='ps_l'>密码：</label>
     <input id='user_ps' type='password'>
   </div>
   <div class='msg'>
   </div>
   <button id='btn_login' onclick="login();">登录</button>
   <button id='btn_kan' onclick="kankan();">随便看看</button>
</body>
<script type="text/javascript">
function login(){
	var id=$('#user_id').val().trim();
	var ps=$('#user_ps').val().trim();
    if(id==""||ps==""){
    	alert("账号或密码不能为空！");
    	return;
    }
	$.ajax({
		url : '<%=basePath %>/springmvc/test/easyLogin',
		data : {uId:id,password:ps},
		//type: "POST",
		dataType : 'json',
		success : function(data) {
			var status=data.status;
			if(status =='success'){
				window.location.href='<%=basePath%>/springmvc/test/successPage?age=25&address=china';
			}else{
				if(status=="error.password"){
				  $('.msg').html('<span class="msg_s">密码不正确！</span>');
				}else if(status=="error.notuser"){
				  $('.msg').html('<span class="msg_s">用户不存在！</span>');
				}
			}
			//alert('success！');
		},
		error : function(jqXHR) {
			alert('error！');
		},
		complete : function() {
			//alert('complete！');
		}
	});
	
}

function kankan(){
	window.location.href='<%=basePath%>/springmvc/test/forwardTest?age=25&address=china';
}

</script>
</html>