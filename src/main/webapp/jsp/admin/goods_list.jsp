<%@ page language="java" contentType="text/html;charset=UTF-8"	 pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<title>商品列表</title>
	<meta charset="utf-8"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css"/>
</head>
<body>
<div class="container-fluid">

	<jsp:include page="/jsp/admin/header.jsp"></jsp:include>

	<div class="text-right"><a class="btn btn-warning" href="${pageContext.request.contextPath}/jsp/admin/goods_add.jsp">添加商品</a></div>

	<br>

	<ul role="tablist" class="nav nav-tabs">
		<li <c:if test="${recommend==0}">class="active"</c:if> role="presentation"><a href="goods_list?recommend=0">全部商品</a></li>
		<li <c:if test="${recommend==1}">class="active"</c:if> role="presentation"><a href="goods_list?recommend=1">条幅推荐</a></li>
		<li <c:if test="${recommend==2}">class="active"</c:if> role="presentation"><a href="goods_list?recommend=2">热销推荐</a></li>
		<li <c:if test="${recomment==3}">class="active"</c:if> role="presentation"><a href="goods_list?recommend=3">新品推荐</a></li>
	</ul>

	<br>

	<table class="table table-bordered table-hover">

		<tr>
			<th width="5%">ID</th>
			<th width="10%">图片</th>
			<th width="10%">名称</th>
			<th width="20%">介绍</th>
			<th width="10%">价格</th>
			<th width="10%">类目</th>
			<th width="25%">操作</th>
		</tr>

		<c:forEach items="${page.list}" var="g">
			<tr>
				<td><p>${g.id }</p></td>
				<td><p><a href="../goods_detail?id=${g.id}" target="_blank"><img src="../${g.cover}" width="100px" height="100px"></a></p></td>
				<td><p><a href="../goods_detail?id=${g.id}" target="_blank">${g.name}</a></p></td>
				<td><p>${g.intro}</p></td>
				<td><p>${g.price}</p></td>
				<td><p>${g.type.name}</p></td>
				<td>
					<p>
						<c:choose>
							<c:when test="${g.isScroll }">
								<a class="btn btn-info" href="goods_recommend?id=${g.id }&method=remove&typeTarget=1&pageNum=${page.pageNum}&recommend=${recommend}">移出条幅</a>
							</c:when>
							<c:otherwise>
								<a class="btn btn-primary" href="goods_recommend?id=${g.id }&method=add&typeTarget=1&pageNum=${page.pageNum}&recommend=${recommend}">加入条幅</a>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${g.isHot }">
								<a class="btn btn-info" href="goods_recommend?id=${g.id }&method=remove&typeTarget=2&pageNum=${page.pageNum}&recommend=${recommend}">移出热销</a>
							</c:when>
							<c:otherwise>
								<a class="btn btn-primary" href="goods_recommend?id=${g.id }&method=add&typeTarget=2&pageNum=${page.pageNum}&recommend=${recommend}">加入热销</a>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${g.isNew }">
								<a class="btn btn-info" href="goods_recommend?id=${g.id }&method=remove&typeTarget=3&pageNum=${page.pageNum}&recommend=${recommend}">移出新品</a>
							</c:when>
							<c:otherwise>
								<a class="btn btn-primary" href="goods_recommend?id=${g.id }&method=add&typeTarget=3&pageNum=${page.pageNum}&recommend=${recommend}">加入新品</a>
							</c:otherwise>
						</c:choose>

					</p>
					<a class="btn btn-success" href="${pageContext.request.contextPath}/jsp/admin/goods_editshow.jsp?id=${g.id }&pageNum=${page.pageNum}&recommend=${recommend}">修改</a>
					<a class="btn btn-danger" href="${pageContext.request.contextPath}/admin/goods_delete?id=${g.id }&pageNum=${page.pageNum}&recommend=${recommend}">删除</a>
				</td>
			</tr>
		</c:forEach>


	</table>

	<br>

	<div>
				<jsp:include page="/jsp/page.jsp">
				<jsp:param value="admin/goods_list" name="url"/>
			    </jsp:include>

	</div>

</div>
</body>
</html>