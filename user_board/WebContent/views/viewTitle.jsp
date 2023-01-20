<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/main.css" />
</head>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
 $("#btnReply").click(function(){
        var replytext=$("#replytext").val(); //댓글 내용
        var bno="${dto.bno}"; //게시물 번호
        var param={ "replytext": replytext, "bno": bno};
        //var param="replytext="+replytext+"&bno="+bno;
        $.ajax({
            type: "post", //데이터를 보낼 방식
            url: "${path}/reply/insert.do", //데이터를 보낼 url
            data: param, //보낼 데이터
 
            success: function(){ //데이터를 보내는것이 성공했을시 출력되는 메시지
                alert("댓글이 등록되었습니다.");
                listReply2(); //댓글 목록 출력
            }
        });
    });
</script>

<body>
	<jsp:include page="../menu.jsp"></jsp:include>
	<table>
	<tr>
		<th>제목</th>
		<th>작성자</th>
		<th>내용</th>
		<th>이미지</th>
		<th>작성날짜</th>
	</tr>	
		<td>${ub.board_title }</td>
		<td>${ub.user_name }</td>
		<td>${ub.board_content }</td>
		<td><img src="<%=application.getContextPath() %>/${ub.picture_path }" alt="" /></td>
		<td>${ub.board_date }</td>	
</table>
	<div>
	<p>댓글</p>
	<table>
		<tr>
			<th>작성자</th>
			<th>내용</th>
		</tr>
			<c:forEach var="list" items="${list }">
			<tr>
				<td>${list.user_name }</td>
				<c:if test="${!empty user && user.user_name == list.user_name }">
				<td>
				<form action="${pageContext.request.contextPath }/board/modiComment" method="post">
				<input type="text" name="comment" value="${list.cm_content }" />
				<input type="hidden" name="board_num" value="${list.board_num }"/>
				<input type="hidden" name="comment_id" value="${list.comment_id }"/>
				<input type="submit" value="수정하기" />
				<a href="${pageContext.request.contextPath }/board/deleteComment?comment_id=${list.comment_id}&amp;board_num=${list.board_num}">삭제하기</a>
				</form>
				</td>
				</c:if>				
				<c:if test="${empty user}">
				<td>${list.cm_content }</td>
				</c:if>
				<c:if test="${!empty user && user.user_name != list.user_name}">
				<td>${list.cm_content }</td>
				</c:if>
				<c:if test="${!empty list.cm_date }">
				<td>${list.cm_date }</td>
				</c:if>
				<c:if test="${empty list.cm_date }">
				</c:if>
			</tr>
			</c:forEach>
	</table>
	</div>
	<c:if test="${!empty user }">
		<div class="comments=txt">
		<div class="comment_form">
			<form action="${pageContext.request.contextPath }/board/comment_insert" method="post"> 
				<input type="hidden" name="user_num" value="${user.user_num }"/>
				<input type="hidden" name="board_num" value="${ub.board_num }"/>
				<textarea rows="content" name="comment">댓글입력창.</textarea>
				<button type="submit">등록</button>
            </form>
		</div>
   </div>
   </c:if>
   <p>${ub.user_num }</p>
<c:if test="${!empty user && user.user_name == ub.user_name  }">
<table>
<tr>
<td>
	<form action="${pageContext.request.contextPath }/board/modiBoardForm?num=${ub.board_num }" method="post"> 
	<input type="submit" value="게시글 수정하기" />
	</form>
</td>
<td>
	<form action="${pageContext.request.contextPath }/board/deleteBoard?num=${ub.board_num }" method="post">
	<input type="submit" value="게시글 삭제하기"  />
</form>
</td>
</tr>
</table>
</c:if>
</body>
</html>