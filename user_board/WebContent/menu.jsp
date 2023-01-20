<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<ul>
	<c:if test="${empty user }">
	<li><a href="${pageContext.request.contextPath }/user/loginForm">로그인</a></li>
	<li><a href="${pageContext.request.contextPath }/user/registForm">회원가입</a></li>
	</c:if>
	<c:if test="${!empty user }">
	<li><a href="${pageContext.request.contextPath }/board/listAll">리스트보기</a></li>
	<li><a href="${pageContext.request.contextPath }/board/writeBoardForm?user_num=${user.getUser_num()}">게시글 작성하기</a></li>
	<li><a href="${pageContext.request.contextPath }/views/searchForm.jsp">검색하기</a></li>
	<li><a href="${pageContext.request.contextPath }/user/logout">로그아웃</a></li>
	</c:if>
	
</ul>
</body>
</html>