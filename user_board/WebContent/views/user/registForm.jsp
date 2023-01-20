<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/main.css"/>
</head>
<body>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"/> 
<form action="${pageContext.request.contextPath }/user/regist" method="post">
<table>
<tr>
	<th>ID</th>
	<td><input type="text" name="user_id" id="user_id" /></td>
</tr>
<tr>
<th>비밀번호</th>
<td><input type="password" name="user_pw" id="user_pw" /></td>
</tr>
<tr>
	<th>이름</th>
	<td><input type="text" name="user_name" id="user_name" /></td>
</tr>
<tr>
	<th>나이</th>
	<td><input type="text" name="user_age" id="user_age" /></td>
</tr>
<tr>
	<th>휴대전화</th>
	<td><input type="text" name="user_phone" id="user_phone" /></td>
</tr>
<tr>
	<th>성별</th>
	<td><input type="radio" name="user_gender" value="m" />m 
	<input type="radio" name="user_gender" value="f" />f</td>
</tr>
<tr>	
	<td><input type="submit" value="가입하기" /></td>
	<td><a href="${pageContext.request.contextPath }/menu.jsp">돌아가기</a></td>
</tr>
</table>
</form>

</body>
</html>