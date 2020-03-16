<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 视窗 -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>今日头条</title>
<link href="<%=request.getContextPath()%>/resource/bootstrap.min.css"
	rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/resource/index.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resource/jquery-3.2.1.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resource/popper.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resource/bootstrap.min.js"></script>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<!-- 头 -->
			<div class="col-md-12"
				style="background-color: #222222; height: 34px">
				<font color="white" size="2px" style="margin-left: 20px;">下载APP</font>
				<!-- 注册 -->
				<div style="float: right">
					<c:if test="${null==sessionScope.user}">
						<button type="button" class="btn btn-link" data-toggle="modal"
							onclick="reg()" data-target="#exampleModal">注册</button>
						<button type="button" class="btn btn-link" data-toggle="modal"
							onclick="login()" data-target="#exampleModal">登录</button>
					</c:if>
					<c:if test="${null!=sessionScope.user}">
						<div class="btn-group dropleft">
							<button type="button" class="btn btn-link dropdown-toggle"
								data-toggle="dropdown" aria-haspopup="true"
								aria-expanded="false">
								<font color="white" size="2px">登录信息</font>
							</button>
							<div class="dropdown-menu">
								<a class="dropdown-item" href="#">${sessionScope.user.username }</a>
								<a class="dropdown-item" href="/my">个人中心</a> <a
									class="dropdown-item" href="/passport/logout">注销</a>
							</div>
						</div>
					</c:if>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-2" style="padding-top: 10px">
				<ul>
					<li style="margin-bottom: 10px"><a href="/"> <img alt=""
							src="/resource/images/logo-index.png"
							style="width: 108px; height: 25px">
					</a></li>
					<!-- 热点 -->
					<li><a href="/?hot=1"
						class="channel-item ${article.channelId==null?"active":"" }">热点</a></li>
					<c:forEach items="${channels }" var="channel" varStatus="i">
						<li><a href="/?channelId=${channel.id}"
							class="channel-item ${article.channelId==channel.id?"active":"" }">${channel.name }</a></li>
					</c:forEach>
				</ul>

			</div>
			<div class="col-md-7">
				<!-- 栏目ID为空显示轮播图 -->
				<c:if test="${article.channelId==null }">
					<div style="margin: 5px 5px 5px 5px">
						<div id="carouselExampleCaptions" class="carousel slide"
							data-ride="carousel">
							<ol class="carousel-indicators">
								<c:forEach items="${slides }" var="slide" varStatus="i">
									<li data-target="#carouselExampleCaptions"
										data-slide-to="${i.index }" class="active"></li>
								</c:forEach>
							</ol>
							<div class="carousel-inner">
								<c:forEach items="${slides }" var="slide" varStatus="i">
									<div class="carousel-item ${i.index==0?"active":"" }">
										<img src="/pic/${slide.url }" class="d-block w-100 rounded"
											alt="..." style="width: 350px; height: 320px">
										<div class="carousel-caption d-none d-md-block">
											<h5>${slide.title }</h5>
										</div>
									</div>
								</c:forEach>
							</div>
							<a class="carousel-control-prev" href="#carouselExampleCaptions"
								role="button" data-slide="prev"> <span
								class="carousel-control-prev-icon" aria-hidden="true"></span> <span
								class="sr-only">Previous</span>
							</a> <a class="carousel-control-next" href="#carouselExampleCaptions"
								role="button" data-slide="next"> <span
								class="carousel-control-next-icon" aria-hidden="true"></span> <span
								class="sr-only">Next</span>
							</a>
						</div>
					</div>
				</c:if>
				<!-- 子分类 -->
				<c:if test="${article.channelId!=null }">
					<div class="subchannel">
						<ul>
							<li class="sub-item ${article.categoryId==null?"sub-selected":""}"><a
								href="/?channelId=${article.channelId }">全部</a></li>
							<c:forEach items="${categorys }" var="category">
								<li class="sub-item ${article.categoryId==category.id?"sub-selected":"" }"><a
									href="/?channelId=${article.channelId }&categoryId=${category.id}">${category.name }</a></li>
							</c:forEach>
						</ul>
					</div>
				</c:if>
				<!-- 分类的文章 -->
				<div>
					<c:forEach items="${info.list }" var="article">
						<div class="media">
							<img src="/pic/${article.picture }" class="mr-3 rounded"
								alt="..." style="width: 190px; height: 124px">
							<div class="media-body">
								<h5 class="mt-0">
									<a href="/articleDetail?id=${article.id }" target="_blank">${article.title }</a>
								</h5>
								<p>${article.user.username }.
									${article.hits } 浏览量
									<fmt:formatDate value="${article.created}"
										pattern="yyyy-MM-dd HH:mm:ss" />
								</p>

							</div>
						</div>
						<hr>
					</c:forEach>
					<jsp:include page="/WEB-INF/view/common/pages.jsp"></jsp:include>
				</div>
			</div>
			<div class="col-md-3">
				<div class="card"
					style="width: 18rem; margin-top: 10px; background-color: skyblue">
					<div class="card-header">最新文章</div>
					<div class="card-body">
						<c:forEach items="${lastArticles.list}" var="lastArticle">
							<div class="media">
								<img src="/pic/${lastArticle.picture}" class="mr-3 rounded"
									alt="..." style="width: 50px; height: 50px">
								<div class="media-body">
									<p><a href="/articleDetail?id=${lastArticle.id }" target="_blank">${lastArticle.title }</a></p>
								</div>
							</div>
							<hr>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
		<!-- Modal -->
		<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">
							<span id="title"></span>
						</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body" id="passport"></div>

				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function reg() {
			$("#title").append("用户注册")
			$("#passport").load("/passport/reg")
		}
		function login() {
			$("#title").append("用户登录")
			$("#passport").load("/passport/login")
		}

		function goPage(page) {
			var channelId = '${article.channelId}';
			var categoryId = '${article.categoryId}';
			var url = "/?channelId=" + channelId + "&page=" + page;
			if (categoryId != "")
				url = url + "&categoryId=" + categoryId;
			location.href = url;
		}
	</script>
</body>
</html>