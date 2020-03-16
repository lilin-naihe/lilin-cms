<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 视窗 -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>登录</title>
<link  href="<%=request.getContextPath() %>/resource/bootstrap.min.css"   rel="stylesheet"   type="text/css">
<link  href="<%=request.getContextPath() %>/resource/index.css"   rel="stylesheet"   type="text/css">
<link  href="<%=request.getContextPath() %>/resource/jquery/screen.css"   rel="stylesheet"   type="text/css">
<script type="text/javascript"  src="<%=request.getContextPath() %>/resource/jquery-3.2.1.js"></script>
<script type="text/javascript"  src="<%=request.getContextPath() %>/resource/jquery.validate.js"></script>
<script type="text/javascript"  src="<%=request.getContextPath() %>/resource/bootstrap.min.js"></script>
</head>
<body>
   <div class="container">
      <span style="color: red">${msg}</span>
      <form id="form1">
          
          <div class="form-group">
             <label for="username">用户名</label><input id="username" type="text"
              class="form-control" name="username">
          </div>
          <div class="form-group">
             <label for="password">密码</label>
             <input id="password" type="password" class="form-control" name="password">
          </div>
          <div class="form-group">
            <button type="submit" class="btn btn-info">登录</button> 
            <button type="reset" class="btn btn-warning">重置</button>  
            <span id="msg"></span>
          </div>
      </form>
   
   </div>
<script type="text/javascript">

   $(function() {
	 $("#form1").validate({
		 //定义规则
		 rules: {
			 username:{
				 required: true,
			 },
			 password:{
				 required: true,
			 },
		 },
		//定义消息提示
		 messages: {
			 username:{
			    required: "用户名字不能为空",
		 },
		 password:{
			 required: "密码不能为空",
		 },
	 },submitHandler:function(flag){
		 $.post("/passport/login",$("#form1").serialize(),function(result){
			 if(result.code==200){
				 $("#msg").html("<font color='red'>登录成功</font>")
				 location.href="/";
			 }else{
				 $("#msg").html("<font color='red'>"+result.msg+"</font>")
			 }
		 })
	   }
   })
})

















</script>
</body>
</html>