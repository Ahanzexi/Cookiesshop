<%--
  Created by IntelliJ IDEA.
  User: 湛泽希
  Date: 2023/4/26
  Time: 9:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach items="${files}" var="filename">
    <div>
        <a href="${pageContext.request.contextPath}/user/download?filename=${filename}">${filename}</a>
    </div>
</c:forEach>
</body>
</html>
