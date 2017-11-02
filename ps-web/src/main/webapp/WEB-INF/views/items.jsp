<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>ITEMS</title>
</head>
<body>
	<table>
		<c:forEach items="${items}" var="item">
		${item.id}
	</c:forEach>


		<c:forEach items="${items}" var="item">
			<tr>
				<td>${item }.id</td>
				<td>${item }.itemName</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>