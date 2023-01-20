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
<form action="${pageContext.request.contextPath }/board/modiBoard" method="post" enctype="multipart/form-data">
	<table>
		<tr>
			<th></th>
			<th>작성자 </th>
			<th>제목</th>	
			<th>내용</th>			
			<th>사진</th>
					
		</tr>
		<tr>
			<td><input type="hidden" name="num" value="${ub.board_num }"/></td>
			<td><input type="text" name="user_name" value="${ub.user_name }" readonly/></td>			
			<td><input type="text" name="title" value="${ub.board_title }"/></td>			
			<td><textarea name="content" cols="5" rows="30">${ub.board_content }</textarea></td>
			<td><input type="file" name="file" id="" /></td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="수정하기" /></td>
			<td colspan="3"><a href="${pageContext.request.contextPath }/menu.jsp">처음으로 돌아가기</a></td>
		</tr>
	</table>
</form>

</body>
</html>