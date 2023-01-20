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
<jsp:include page="../menu.jsp"></jsp:include>
<table>
	<tr>
		<th>제목</th>
		<th>작성자</th>
		<th>사진</th>
		<th>작성날짜</th>
	</tr>	
		<c:forEach var="board" items="${list}">
		
		<tr>
			<td><a href="${pageContext.request.contextPath }/board/selectTitle?board_num=${board.board_num }">${board.board_title }</a></td>
			<td>${board.user_name }</td>
			<c:if test="${empty board.picture_path}">
			<td></td>
			</c:if>
			<c:if test="${not empty board.picture_path }">
			<td><img src="<%=application.getContextPath() %>/${board.picture_path }" alt="" /></td>
			</c:if>
			<td>${board.board_date }</td>
			</tr>
		</c:forEach>
</table>
</body>
</html>