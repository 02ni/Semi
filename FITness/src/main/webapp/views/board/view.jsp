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
		<h3>자유게시판</h3>
		<table id="tbl-board" class="se">
			<colgroup>
                    <col style="width:150px">
                    <col style="width:850px">
            </colgroup>
			<tr>
				<th>글번호</th>
				<td>${ board.no }</td>
			</tr>
			<tr>
				<th>제 목</th>
				<td>${ board.title }</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>${ board.writerId }</td>
			</tr>
			<tr>
				<th>조회수</th>
				<td>${ board.readCount }</td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td>
					<c:if test="${ empty board.originalFileName }">
						<span> - </span>
					</c:if>
					<c:if test="${ not empty board.originalFileName }">
						<a href="${ path }/resources/upload/board/${board.renamedFileName}">
							<span> ${ board.originalFileName } </span>
						</a>
					</c:if>
				</td>
			</tr>
			<tr>
				<th>내 용</th>
				<td>${ board.content }</td>
			</tr>
		</table>
		
		<h2></h2>
		
			<div class="se">
			<tr>
				<th colspan="10">
					<c:if test="${ not empty loginMember && loginMember.id == board.writerId }">
						<input type="submit"  value="수정" onclick="location.href='${ path }/board/update?no=${ board.no }'">
						<input type="submit"  value="삭제" id="btnDelete">
					</c:if>
					<input type="submit"  value="목록으로" onclick="location.href='${ path }/board/list'">
				</th>
			</tr>
			</div>
			
			
	
		<h2></h2>
		
		<div id="comment-container" class="se">
	    	<div class="comment-editor">
	    		<form action="${ path }/board/reply" method="POST">
	    			<input type="hidden" name="boardNo" value="${ board.no }">
					
				<div>
					<textarea style="height:60px; resize: none;" maxlength="200" name="content" id="replyContent" cols="75" rows="3" placeholder="텍스트를 입력하세요."></textarea>
					<input type="submit"  value="등록" id="btn-insert">
				    <p><span id="counter">0</span>/<span id="maxLength">200자</span></p>
				</div>	  
				
				
				  			
	    		</form>
	    	</div>
	    </div>	
	        
	    <table id="tbl-comment" class="se">
	    	<c:forEach var="reply" items="${ board.replies }">
	    
	    	   	<tr class="level1">
		    		<td>
		    			<sub class="comment-writer">${ reply.writerId }</sub>
		    			<sub class="comment-date">${ reply.createDate }</sub>
		    			<br>
		    			<span>${ reply.content }</span>
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
				location.replace('${ path }/board/delete?no=${ board.no }');
			}
		});
		
		$('#btnReplyDelete').on('click', () => {
			if(confirm('정말로 댓글을 삭제 하시겠습니까?')) {
				location.replace('${ path }/board/replydelete');
			}
		});
		
		
		
		<%--$('#btnReplyUpdate').on('click', () => {			
			
				let url = '${ path }/board/replyupdate?no=${ board.no }';	
				let status = "width = 490px, height=490px, top=300px, left=300px, scrollbars=yes";	
				
				open(url,"replyUpdate",status);	
			}
		});
		--%>
		
		
		$('#fileDown').on('click', () => {
			let oname = encodeURIComponent('${ board.originalFileName }');
			let rname = encodeURIComponent('${ board.renamedFileName }');
			
			location.assign('${ path }/board/fileDown?oname=' + oname + '&rname=' + rname);
		});
		
		$('#replyContent').on('click', () => {
			if(${ empty loginMember}) {
				alert('로그인 후 이용해 주세요.')	;
				location.replace('${ path }/member/login');
			}
		});
	
		
		
	    $('#replyContent').on('keyup', (event) => {
	        let target = $(event.target);
	        let currentLength = target.val().length;
	        let maxlength = parseInt($('#maxLength').text());

	        console.log(currentLength);
	        console.log(maxlength);

	        if(currentLength > maxlength){
	            target.val(target.val().substr(0, maxlength));
	        }else {
	            $('#counter').text(currentLength);
	       }

	    });
		
		
	});
		
</script>

<jsp:include page="/views/common/footer.jsp" /> 