<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>testuser</title>
</head>
<body>
<table>
    请输入你要查询的id
    <form action="/PsUserController/hello" method="post">
    <input type="text" id="id" name="id">
<input type="submit" >
    </form>
    <%--<c:forEach items="${students}" var="student">--%>
        <%--${student.id}--%>
    <%--</c:forEach>--%>


    <%--<c:forEach items="${students}" var="student">--%>
        <%--<tr>--%>
            <%--<td>${student }.id</td>--%>
            <%--<td>${student }.name</td>--%>
        <%--</tr>--%>
    <%--</c:forEach>--%>
</table>
</body>
</html>