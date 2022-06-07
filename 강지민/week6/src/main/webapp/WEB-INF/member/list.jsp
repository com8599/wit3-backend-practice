<%--
  Created by IntelliJ IDEA.
  User: jimin
  Date: 2022-05-30
  Time: 오전 10:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="container">
    <table>
        <tr>
            <th>Id</th>
            <th>email</th>
            <th>password</th>
        </tr>

        <c:forEach items="${list}" var="post">
            <tr>
                <td>${post.id}</td>
                <td>${post.email}</td>
                <td>${post.password}</td>
                <td><button type="button" onclick="location.href='mod/${post.id}'">수정</button></td>
                <form name="delete" action="/members/del/${post.id}" method="POST">
                    <input type="hidden" name="_method" value="delete" />
                    <td><button type="submit">삭제</button></td>
                </form>
            </tr>
        </c:forEach>
    </table>
    <button type="button" onclick="location.href='add'">등록</button>
</div>
</body>
</html>
