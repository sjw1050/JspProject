<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div>
<form action="${pageContext.request.contextPath }/board/searchDate">
	<table>
		<tr>
			<th><input type="date" name="date" /></th>
		</tr>
		<tr>
			<td><input type="submit" value="검색하기" /></td>
		</tr>
	</table>
</form>

<form action="${pageContext.request.contextPath }/board/searchWriter">
	<table>
		<tr>
			<th>작성자 검색: <input type="text" name="writer" /></th>
		</tr>
		<tr>
			<td><input type="submit" value="검색하기" /></td>
		</tr>
	</table>
</form>

<form action="${pageContext.request.contextPath }/board/searchTC">
	<table>
		<tr>
			<th>제목 및 내용 검색 : <input type="text" name="tc" /></th>
		</tr>
		<tr>
			<td><input type="submit" value="검색하기" /></td>
		</tr>
	</table>
</form>
</div>
</body>
</html>