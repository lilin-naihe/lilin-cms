<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的文章内容</title>
<link  href="<%=request.getContextPath() %>/resource/bootstrap.min.css"   rel="stylesheet"   type="text/css">
<script type="text/javascript"  src="<%=request.getContextPath() %>/resource/jquery-3.2.1.js"></script>
<script type="text/javascript"  src="<%=request.getContextPath() %>/resource/bootstrap.min.js"></script>
</head>
<body>
<c:forEach items="${info.list }" var="article">
  <div class="media">
     <img alt="无" src="/pic/${article.picture }" class="mr-3 rounded" style="height: 80px;width: 100px">
     <div class="media-body">
        <h5 class="mt-0">${article.title }</h5>
        <div style="float: right;padding-top: 10px;padding-right: 50px">
          <button type="button" onclick="articleDetail(${article.id})" class="btn btn-link" data-toggle="modal" data-target="#exampleModalLong">
                              详情
          </button>
        </div>
        
     </div>
  </div>
  <hr>
</c:forEach>
<jsp:include page="/WEB-INF/view/common/pages.jsp"/>
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
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        
      </div>
    </div>
  </div>
</div>

</body>
<script type="text/javascript">
//文章详情
  function articleDetail(id) {
	$.get("/my/articleDetail",{id:id},function(article){
		$("#title").append(article.title);
		$("#content").append(article.content);
	})
}
  function goPage(page) {
	$("#center").load("/my/articles?page="+page);
}


</script>



</html>