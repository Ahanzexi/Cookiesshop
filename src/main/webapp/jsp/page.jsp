<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--PageInfo中的方法：（要先创建PageIngo对象 ，再调用方法）--%>
<%--PageNum：获取当前页--%>
<%--Pages：获取总页数；--%>
<%--PageSize；每页条数；--%>
<%--Total:总记录数；--%>
<%--NavigateFirstPage:获取第一页--%>
<%--NavigateLastPage:获取最后一页--%>
<%--PrePage:上一页--%>
<%--NextPage:下一页--%>
<div align="center">
	<a class='btn btn-info'
	   <c:if test="${page.pageNum==1 }">disabled</c:if>
	   <c:if test="${page.pageNum!=1 }">href="${pageContext.request.contextPath }/${param.url }?pageNum=1${param.param }"</c:if>>首页</a>
	<a class='btn btn-info'
	   <c:if test="${page.pageNum==1 }">disabled</c:if>
	   <c:if test="${page.pageNum!=1 }">href="${pageContext.request.contextPath }/${param.url }?pageNum=${page.pageNum-1}${param.param }"</c:if>>上一页</a>
	<h3 style='display:inline;'>[第${page.pageNum }页/共${page.pages }页]</h3>
	<h3 style='display:inline;'>[${page.total}]</h3>
	<a class='btn btn-info'
	   <c:if test="${page.pages==0 || page.pageNum==page.pages}">disabled</c:if>
	   <c:if test="${page.pageNum!=page.pages }">href="${pageContext.request.contextPath }/${param.url }?pageNum=${page.pageNum+1}${param.param }"</c:if>>下一页</a>
	<a class='btn btn-info'
	   <c:if test="${page.pages==0 || page.pageNum==page.pages }">disabled</c:if>
	   <c:if test="${page.pageNum!=page.pages }">href="${pageContext.request.contextPath }/${param.url }?pageNum=${page.pages}${param.param }"</c:if>>尾页</a>
	<input type='text' class='form-control' style='display:inline;width:60px;' value='' id="in"/>
	<a class='btn btn-info' href='javascript:void(0);' onclick='location.href="${pageContext.request.contextPath }/${param.url}?pageNum="+(document.getElementById("in").value)+"${param.param}"'>GO</a>
</div>
<%--	当前是第${page.pageNum} 页，共${page.pages}页<br>--%>
<%--	<c:if test="${page.pageNum!=1}">--%>
<%--	<a href="${pageContext.request.contextPath }/${param.url }?pageNum=1">首页</a>--%>
<%--	</c:if>--%>
<%--	<c:if test="${page.hasPreviousPage}">--%>
<%--		<a href="${pageContext.request.contextPath }/${param.url }?pageNum=${page.prePage}">上一页</a>--%>
<%--	</c:if>--%>
<%--	<c:if test="${page.hasNextPage}">--%>
<%--		<a href="${pageContext.request.contextPath }/${param.url }?pageNum=${page.nextPage}">下一页</a>--%>
<%--	</c:if>--%>
<%--	<c:if test="${page.pageNum!=page.pages}">--%>
<%--	<a href="${pageContext.request.contextPath }/${param.url }?pageNum=${page.pages}">尾页</a>--%>
<%--	</c:if>--%>