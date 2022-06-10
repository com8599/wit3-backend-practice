<%--
  Created by IntelliJ IDEA.
  User: jimin
  Date: 2022-05-30
  Time: 오전 10:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form name="f" action="/members/mod/${data.id}" method="POST">
        <input type="hidden" name="_method" value="put">
        Email: <input type="text" name="email" value="${data.email}">
        <br>
        Password: <input type="password" name="password" value="${data.password}">
        <br>
        <input type="submit" role="button" value="수정">
    </form>
</body>
</html>
