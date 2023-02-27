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
		<h3>1:1 문의</h3>
		<table id="tbl-board" class="se">
			<colgroup>
                    <col style="width:150px">
                    <col style="width:850px">
            </colgroup>
			<tr>
				<th>글번호</th>
				<td>${ qnaboard.no }</td>
			</tr>
			<tr>
				<th>제 목</th>
				<td>${ qnaboard.title }</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>${ qnaboard.writerId }</td>
			</tr>
			<tr>
				<th>조회수</th>
				<td>${ qnaboard.readCount }</td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td>
					<c:if test="${ empty qnaboard.originalFileName }">
						<span> - </span>
					</c:if>
					<c:if test="${ not empty qnaboard.originalFileName }">
						<a href="${ path }/resources/upload/qna/${qnaboard.renamedFileName}">
							<span> ${ qnaboard.originalFileName } </span>
						</a>
					</c:if>
				</td>
			</tr>
			<tr>
				<th>내 용</th>
				<td>${ qnaboard.content }</td>
			</tr>
		</table>
		
		<h2></h2>
		
			<div class="se">
			<tr>
				<th colspan="10">
					<c:if test="${ not empty loginMember && loginMember.id == qnaboard.writerId }">
						<input type="submit"  value="수정" onclick="location.href='${ path }/qna/update?no=${ qnaboard.no }'">
						<input type="submit"  value="삭제" id="btnDelete">
					</c:if>
					<input type="submit"  value="목록으로" onclick="location.href='${ path }/qna/list'">
				</th>
			</tr>
			</div>
	
		<h2></h2>
		
		
		<c:if test="${ not empty loginMember && loginMember.role.equals('ROLE_ADMIN')}">
			<div id="comment-container" class="se">
		    	<div class="comment-editor">
		    		<form action="${ path }/admin/qnareply" method="POST">
		    			<input type="hidden" name="qnaboardNo" value="${ qnaboard.no }">
						<textarea name="content" id="replyContent" cols="75" rows="3"></textarea>
						<input type="submit"  value="등록" id="btn-insert">	    			
		    		</form>
		    	</div>
		    </div>	
		</c:if>
		
		
		
		<table id="tbl-comment" class="se">
	    	<c:forEach var="qnareply" items="${ qnaboard.replies }">
	    
	    	   	<tr class="level1">
		    		<td>
		    			<sub class="comment-writer">${ qnareply.writerId }</sub>
		    			<sub class="comment-date">${ qnareply.createDate }</sub>
		    			<br>
		    			<span>${ qnareply.content }</span>
		    		</td>
		    		<td>
		    	
		    			<c:if test="${ not empty loginMember && loginMember.id == reply.writerId }">
			    			<!-- 
			    			<input type="submit" name="replyUpdate" value="수정" id="btnReplyUpdate">

		    				 -->
		    				
		    				<button type="button" onclick="javascrit:window.open('${ path }/board/replyupdate?no=${ reply.no }','popup_1','width=500px,height=400px,toolbar=no,location=no,status=no,menubar=no,scrollbars=no,resizable=no,left=300px,top=100px')">
		    				수정</button>
		    			</c:if>
		    			<form action="${ path }/board/replydelete" method="POST">
		    			<input type="hidden" name="replyNo" value="${ reply.no }">
		    			<input type="hidden" name="boardNo" value="${ board.no }">
			    			<c:if test="${ not empty loginMember && loginMember.id == reply.writerId }">
			    				<input type="submit"  value="삭제" id="btnReplyDelete">
			    			</c:if>
		    			</form>
		    			<!-- 
		    			<c:if test="${ not empty loginMember && loginMember.id == board.writerId }">
		    				<input type="submit"  value="답글" id="btnAgainReply">
		    			</c:if>
		    			 -->
		    		</td>
		    	</tr>
	    	</c:forEach>
	    </table>
		
    </div>
</section>

<script>
	$(document).ready(() => {
		$('#btnDelete').on('click', () => {
			if(confirm('정말로 게시글을 삭제 하시겠습니까?')) {
				location.replace('${ path }/board/delete?no=${ qnaboard.no }');
			}
		});
		
		$('#fileDown').on('click', () => {
			let oname = encodeURIComponent('${ qnaboard.originalFileName }');
			let rname = encodeURIComponent('${ qnaboard.renamedFileName }');
			
			location.assign('${ path }/qna/fileDown?oname=' + oname + '&rname=' + rname);
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