<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:set var="path" value="${ pageContext.request.contextPath }"/>
<jsp:include page="/views/common/header.jsp" />

<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
      rel="stylesheet">
      
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<link href="${ path }/resources/css/layout.css" rel="stylesheet">
 
<script src="js/jquery-3.6.0.min.js"></script> 
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<style>
    h3 { text-align: center; }
    table#tbl-board{width:800px; margin:0 auto; border-collapse:collapse; clear:both; } 
    table#comment-container{width:800px; margin:0 auto; border-collapse:collapse; clear:both; } 
    table#tbl-board{width:800px; margin:0 auto; border-collapse:collapse; clear:both; } 
    div.se{width:800px; margin:0 auto; border-collapse:collapse; clear:both; } 
    
        /*댓글테이블*/
    table#tbl-comment{width:800px; margin:0 auto; border-collapse:collapse; clear:both; } 
    table#tbl-comment button.btn-delete{display:none;}
    table#tbl-comment tr:hover {background:lightgray;}
    table#tbl-comment tr:hover button.btn-delete{display:inline;}
    table#tbl-comment tr td:last-of-type {text-align:right; width: 100px;}
    table#tbl-comment sub.comment-writer {color:navy; font-size:14px}
</style>


<section id="content">   
	<div id="board-write-container">
		<h3>공지사항</h3>
		<table id="tbl-board" class="se">
			<colgroup>
                    <col style="width:150px">
                    <col style="width:850px">
            </colgroup>
			<tr>
				<th>글번호</th>
				<td>${ notice.no }</td>
			</tr>
			<tr>
				<th>제 목</th>
				<td>${ notice.title }</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>${ notice.writerId }</td>
			</tr>
			<tr>
				<th>조회수</th>
				<td>${ notice.readCount }</td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td>
					<c:if test="${ empty notice.originalFileName }">
						<span> - </span>
					</c:if>
					<c:if test="${ not empty notice.originalFileName }">
						<a href="${ path }/resources/upload/notice/${board.renamedFileName}">
							<span> ${ notice.originalFileName } </span>
						</a>
					</c:if>
				</td>
			</tr>
			<tr>
				<th>내 용</th>
				<td>${ notice.content }</td>
			</tr>
		</table>
		
		<h2></h2>
		
			<div class="se">
			<tr>
				<th colspan="10">
					<c:if test="${ not empty loginMember && loginMember.role.equals('ROLE_ADMIN') }">
						<input type="submit"  value="수정" onclick="location.href='${ path }/notice/update?no=${ notice.no }'">
						<input type="submit"  value="삭제" id="btnDelete">
					</c:if>
					<input type="submit"  value="목록으로" onclick="location.href='${ path }/notice/list'">
				</th>
			</tr>
			</div>
			
	
		<h2></h2>
		
    </div>
</section>

<script>
	$(document).ready(() => {
		$('#btnDelete').on('click', () => {
			if(confirm('정말로 게시글을 삭제 하시겠습니까?')) {
				location.replace('${ path }/notice/delete?no=${ notice.no }');
			}
		});
		
		
		$('#fileDown').on('click', () => {
			let oname = encodeURIComponent('${ board.originalFileName }');
			let rname = encodeURIComponent('${ board.renamedFileName }');
			
			location.assign('${ path }/notice/fileDown?oname=' + oname + '&rname=' + rname);
		});
		
		$('#replyContent').on('click', () => {
			if(${ empty loginMember}) {
				alert('로그인 후 이용해 주세요.')	;
				location.replace('${ path }/member/login');
			}
		});
		
        
	});
		
</script>

<jsp:include page="/views/common/footer.jsp" /> 