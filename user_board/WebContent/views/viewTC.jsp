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
	<tr>
		<th>제목</th>
		<th>작성자</th>
		<th>작성날짜</th>
	</tr>	
		<c:forEach var="list" items="${list}">
		<tr>
			<td><a href="${pageContext.request.contextPath }/board/selectTitle?board_num=${list.board_num }">${list.board_title }</a></td>
			<td>${list.user_name }</td>
			<td>${list.board_date }</td>
			</tr>
		</c:forEach>
	
</table>

</body>
</html>