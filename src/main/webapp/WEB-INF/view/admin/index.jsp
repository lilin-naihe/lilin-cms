<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 视窗 -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>管理员中心</title>
<link  href="<%=request.getContextPath() %>/resource/bootstrap.min.css"   rel="stylesheet"   type="text/css">
<script type="text/javascript"  src="<%=request.getContextPath() %>/resource/jquery-3.2.1.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resource/popper.min.js"></script>
<script type="text/javascript"  src="<%=request.getContextPath() %>/resource/bootstrap.min.js"></script>
</head>
<body>
    <div class="container-fluid">
       <!-- 头 -->
        <div class="row" style="background-color: #009FD9;height: 55px">
            <div class="col-md-12">
             <img alt="" src="/resource/images/11.jpg" width="55px" height="55px" class="rounded-circle">
             <font color="white">今日头条-管理员中心</font>
             
					

					<div class="btn-group dropleft" style="float: right">
					<button type="button" class="btn btn-link dropdown-toggle"
						data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						<font color="white" size="2px">登录信息</font>
					</button>
					<div class="dropdown-menu">
						<a class="dropdown-item" href="#">${sessionScope.admin.username }</a>
						<a class="dropdown-item" href="/passport/logout">注销</a>
					</div>
				</div>
            </div>
        
        </div>
        
        <div class="row" style="padding-top: 10px;height: 550px">
            <!-- 左侧菜单 -->
            <div class="col-md-2 rounded" style="background-color: skyblue;text-align: center;margin-left: 15px">
                 <nav class="nav flex-column">
					<a class="list-group-item active" href="#" data="/admin/articles">文章审核</a>
					<a class="list-group-item" href="#" data="/admin/users">用户管理</a>
					<a class="list-group-item" href="#">栏目管理</a> 
					<a class="list-group-item" href="#">分类管理</a>
					<a class="list-group-item" href="#">系统设置</a>
				</nav>
            
            
            </div>
            <!-- 右侧菜单 -->
            <div class="col-md-9" id="center"></div>
        </div>
    </div>
<script type="text/javascript">
//li点击事件
$(function() {
	
	$("#center").load("/admin/articles")
	
	
	$("a").click(function() {
		var url=$(this).attr("data");
		//去除样式
		$("a").removeClass("active");
		//选中
		$(this).addClass("list-group-item active")
		//显示url内容
		$("#center").load(url);
	})
})

</script>
</body>
</html>