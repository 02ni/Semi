<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>    
<c:set var="path" value="${ pageContext.request.contextPath }"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰 수정</title>
<link rel="stylesheet" href="${ path }/resources/css/style.css">
<style>
    div#updatecontainer{
        background:beige;
    }
    div#updatecontainer table {
        margin:0 auto;
        border-spacing: 20px;
    }
    div#updatecontainer table tr:last-of-type td {
        text-align:center;
    }
</style>
<script src="${ path }/resources/js/jquery-3.6.3.js"></script>
</head>
<body>
<div id="updatecontainer">
		<form action="${ path }/review/update" method="POST">
			<input type="hidden" name="gymNo" value="${ review.gymNo }">
			<input type="hidden" name="reviewNo" value="${ review.no }">
			<table>
				<tr>
					<th>리뷰 수정</th>
				</tr>
				<tr>
					<td>
						<select name="grade" id="grade" style="color: rgba(236, 202, 4, 0.815)">
                                <option value="5" selected>★★★★★</option>
                                <option value="4">★★★★☆</option>
                                <option value="3">★★★☆☆</option>
                                <option value="2">★★☆☆☆</option>
                                <option value="1">★☆☆☆☆</option>
                            </select>
						<textarea name="content" id="replyContent" cols="70" rows="4">${review.content}</textarea>
					</td>
					
				</tr>
				<tr>
					<td>
						<input type="submit"  value="수정완료">
						<button type="button" onclick="self.close();">닫기</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>

</html>

