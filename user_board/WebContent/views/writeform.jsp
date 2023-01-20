<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="${pageContext.request.contextPath }/board/writeBoard" method="post" enctype="multipart/form-data">
<table>
	<tr>
		<th>제목 : </th>
		<td><input type="text" name="title" /></td>
	</tr>
	<tr>
		<th>내용 : </th>
		<td><textarea name="content" cols="80" rows="20"></textarea></td>
	</tr>
	<tr>
		<th>사진 : </th>
			<td><input type="file" name="file"/></td>
	</tr>
</table>
<input type="hidden" name="user_num" value="${board.user_num }"/>
<input type="submit" value="작성하기" />
</form>

</body>
</html>