<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fm" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文章审核</title>
<link  href="<%=request.getContextPath() %>/resource/bootstrap.min.css"   rel="stylesheet"   type="text/css">
<script type="text/javascript"  src="<%=request.getContextPath() %>/resource/jquery-3.2.1.js"></script>
<script type="text/javascript"  src="<%=request.getContextPath() %>/resource/bootstrap.min.js"></script>
</head>
<body>
<div class="form-group form-inline">
文章标题:
<input type="text" name="title" class="form-control form-control-sm" value="${article.title }">&nbsp;&nbsp;
审核状态:
   <select name="status" class="form-control form-control-sm col-sm-1">
    <option value="0" ${article.status=="0"?"selected":"" }>待审</option>
    <option value="1" ${article.status=="1"?"selected":"" }>已审</option>
    <option value="-1" ${article.status=="-1"?"selected":"" }>驳回</option>
   </select>
    &nbsp;&nbsp;
   <button type="button" onclick="query()" class="btn btn-warning btn-sm">查询</button>
    </div>
   <div style="background-color: yellow">
       <table class="table table-bordered table-hover table-sm">
      <tr align="center">
         <td>序号</td>
         <td>标题</td>
         <td>作者</td>
         <td>发布时间</td>
         <td>所属栏目</td>
         <td>所属分类</td>
         <td>是否热门</td>
         <td>点击量</td>
         <td>其他</td>
      </tr>
      <c:forEach items="${info.list }" var="article" varStatus="i">
        <tr align="center">
          <td>${i.count }</td>
          <td width="300px">${article.title }</td>
          <td>${article.user.username }</td>
          <td><fm:formatDate value="${article.created }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
          <td>${article.channel.name }</td>
          <td>${article.category.name }</td>
          <td>
            <c:if test="${article.hot==0 }">
              <button type="button" class="btn btn-info btn-sm" onclick="update(${article.id},this)">否</button>
            </c:if>
            <c:if test="${article.hot==1 }">
              <button type="button" class="btn btn-danger btn-sm" onclick="update(${article.id},this)">是</button>
            </c:if>
          </td>
          <td>${article.hits }</td>
          <td><button type="button" onclick="articleDetail(${article.id})" class="btn btn-link" data-toggle="modal" data-target="#exampleModalLong">
                              详情
          </button></td>
        </tr>
      </c:forEach>
      
   </table>

   </div>
   <!-- Modal -->
<div class="modal fade" id="exampleModalLong" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLongTitle"><span id="title"></span></h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body" id="content">

      </div>
      <div class="modal-footer">
       <span id="msg"></span>
        <button type="button" class="btn btn-primary" onclick="updateStatus(1)">通过</button>
        <button type="button" class="btn btn-danger" onclick="updateStatus(-1)">驳回</button>
        <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
        
      </div>
    </div>
  </div>
</div>
<jsp:include page="/WEB-INF/view/common/pages.jsp"></jsp:include>
<script type="text/javascript">
//审核
function updateStatus(status) {
	$.post("/admin/update",{id:articleId,status:status},function(flag){
		if(flag){
			$("#msg").append("操作成功")
		}else{
			$("#msg").append("操作失败")
		}
	})
}


var articleId;
function articleDetail(id) {
	articleId=id;
	$("#msg").empty();
	$.get("/admin/articleDetail",{id:id},function(article){
		$("#title").append(article.title);
		$("#content").append(article.content);
	})
}




function update(id,obj) {
	var hot=$(obj).text()=="是"?0:1
	$.post("/admin/update",{id:id,hot:hot},function(flag){
		if(flag){
			$(obj).text($(obj).text()=="是"?"否":"是");
			$(obj).attr("class",hot==0?"btn btn-info btn-sm":"btn btn-danger btn-sm")
		}
	})
}




//查询
  function query() {
	var status=$("[name='status']").val();
	var title=$("[name='title']").val();
	$("#center").load("/admin/articles?status="+status+"&title="+title);
}



  function goPage(page) {
	$("#center").load("/admin/articles?page="+page);
}


</script>
</body>


</html>