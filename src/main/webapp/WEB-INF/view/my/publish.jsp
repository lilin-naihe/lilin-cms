<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
String htmlData = request.getParameter("content1") != null ? request.getParameter("content1") : "";
%>
<!doctype html>
<html>
<head>
	<meta charset="utf-8" />
	<title>KindEditor JSP</title>
		<link rel="stylesheet" href="/resource/kindeditor/themes/default/default.css" />
	<link rel="stylesheet" href="/resource/kindeditor/plugins/code/prettify.css" />
	<script charset="utf-8" src="/resource/kindeditor/kindeditor.js"></script>
	<script charset="utf-8" src="/resource/kindeditor/lang/zh-CN.js"></script>
	<script charset="utf-8" src="/resource/kindeditor/plugins/code/prettify.js"></script>
	<script>
		KindEditor.ready(function(K) {
			window.editor1 = K.create('textarea[name="content1"]', {
				cssPath : '/resource/kindeditor/plugins/code/prettify.css',
				uploadJson : '/resource/kindeditor/jsp/upload_json.jsp',
				fileManagerJson : '/resource/kindeditor/jsp/file_manager_json.jsp',
				allowFileManager : true,
				afterCreate : function() {
					var self = this;
					K.ctrl(document, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
					K.ctrl(self.edit.doc, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
				}
			});
			prettyPrint();
		});
	</script>
</head>
<body>
	<form id="form1">
	    <div class="form-group">
	       <label for="title">文章标题</label>
	       <input type="text" id="title" name="title" class="form-control form-control-sm col-sm-10" style="width: 858px">
	    </div>
	    <div class="form-group form-inline">
	       <label for="title">所属栏目</label>
	       <select name="channelId" class="form-control form-control-sm" id="channels">
	         <option value="-1">请选择</option>
	       </select>
	       
	       <label for="title">所属分类</label>
	       <select name="categoryId" class="form-control form-control-sm" id="categorys">
	         <option>请选择</option>
	       </select>
	    </div>
	    <div class="form-group">
	               文章标题图片：<input type="file" name="file">
	    </div>
		<textarea name="content1" cols="100" rows="8" style="width:930px;height:200px;visibility:hidden;"><%=htmlspecialchars(htmlData)%></textarea>
		<br />
		<input type="button" onclick="publish()" name="button" class="btn btn-primary" value="发布文章" />
	</form>
</body>
<script type="text/javascript">
//发布文章
function publish(){
	
	var formData=new FormData($("#form1")[0]);
	formData.set("content",editor1.html())
	$.ajax({
		type: "post",
		url: "/my/publish",
		processData :false,
		contentType :false,
		data:formData,
		success:function(flag){
			if(flag){
				alert("发布成功");
				location.href="/my"
			}
		}
		
	})
}
//文档就绪函数
$(function(){
	$.get("/channel/channels",function(list){
		for ( var i in list) {
			$("#channels").append("<option value='"+list[i].id+"'>"+list[i].name+"</option>")
		}
	
	//栏目添加点击事件
	$("#channels").change(function(){
		var channelId=$(this).val();
		$("#categorys").empty();
		$.get("/channel/selectByChannelId",{channelId:channelId},function(list){
			for ( var i in list) {
				$("#categorys").append("<option value='"+list[i].id+"'>"+list[i].name+"</option>")
			}
		})
	})
})
})




</script>
</html>
<%!
private String htmlspecialchars(String str) {
	str = str.replaceAll("&", "&amp;");
	str = str.replaceAll("<", "&lt;");
	str = str.replaceAll(">", "&gt;");
	str = str.replaceAll("\"", "&quot;");
	return str;
}
%>