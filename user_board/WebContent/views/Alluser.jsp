<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/main.css" />
</head>
<body>
<table>
		<c:forEach var="list" items="${user}">
		<tr>
			<td><a href="">${list.user_id }</a></td>
			<td>${list.user_pw }</td>
			<td>${list.user_name }</td>
			<td>${list.user_age }</td>
			<td>${list.user_gender }</td>
			<td>${list.user_phone }</td>
			<td>${list.user_num }</td>
			</tr>
		</c:forEach>
	
</table>

</body>
</html>