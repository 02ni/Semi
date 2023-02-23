<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:set var="path" value="${ pageContext.request.contextPath }"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>댓글 변경</title>
<style>
    div#updatePassword-container{
        background:lightgrey;
    }
    div#updatePassword-container table {
        margin:0 auto;
        border-spacing: 20px;
    }
    div#updatePassword-container table tr:last-of-type td {
        text-align:center;
    }
</style>
<script src="${ path }/resources/js/jquery-3.6.3.js"></script>
</head>
<body>
	<div id="updatePassword-container">
		<form action="${ path }/board/replyupdate" method="POST">
			<input type="hidden" name="boardNo" value="${ reply.boardNo }">
			<input type="hidden" name="replyNo" value="${ reply.no }">
			<table>
				<tr>
					<th>댓글 수정</th>
					<td>
						<textarea name="content" id="replyContent" cols="75" rows="3">${reply.content}</textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit"  value="수정">
						&nbsp;
						<input type="button" value="닫기" onclick="self.close();">
					</td>
				</tr>
			</table>
			<input	type="hidden" name="userId">	
		</form>
	</div>
	<script>
	$(document).ready(() => {
		$('#replyContent').on('click', () => {
			if(${ empty loginMember}) {
				alert('로그인 후 이용해 주세요.')	;
				location.replace('${ path }/member/login');
			}
		});
	});
		
	</script>
</body>
</html>



