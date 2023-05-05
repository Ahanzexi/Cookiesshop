<%--
  Created by IntelliJ IDEA.
  User: Oui
  Date: 2023/4/21
  Time: 9:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/importExcel" method="post" enctype="multipart/form-data">
    <input type="file" name="file"><br>
    <input type="submit" value="导入">
</form>
</body>
</html>
