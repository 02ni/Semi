<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${ pageContext.request.contextPath }"/>

<jsp:include page="/views/common/header.jsp"/>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">

<style>
	section#board-list-container{width:600px; margin:0 auto; text-align:center;}
	section#board-list-container h2{margin:10px 0;}
	
	
	/*글쓰기버튼*/
	#btn-add{float:right; margin: 0 0 15px;}
	
	/*페이지바*/
	ul.pagination{margin: 0 auto}
	div#pageBar{margin-top:10px; text-align:center;}
	
	/*글 클릭 전에만 호버링*/
	a:link {
  		text-decoration: none;
	}
 
	a:visited {
  		text-decoration: none;
	}
	
	a:hover {
  		text-decoration: underline;
	}
	
	a:active {
  		text-decoration: underline;
	}
	
    div #btn-add {
         height: 30px;
         padding: 10px;
         box-sizing: border-box;
         width: 100px;
         background-color: yellow;
         font-weight: bold;
         border: none;
         border-radius: 5px;
         cursor: pointer;
     }
	
</style>

<link rel="stylesheet" href="${ path }/resources/css/board.css">

<section id="content">
		<div id="btn1">
			<ul>
				<li class="notice_select" onclick="location.href='${ path }/notice/list'">
					<span>공지사항</span>
				</li>
				<li class="faq_select" onclick="location.href='${ path }/faq/list'">
					<span>FAQ</span>
				</li>
				<li class="freeBoard_select" onclick="location.href='${ path }/board/list'">
					<span>자유게시판</span>
				</li>
				<li class="qna_select active" onclick="location.href='${ path }/qna/list?no=${ loginMember.no }'">
					<span>1:1문의</span>
				</li>
			</ul>
		</div>
		
	<div class="clear"></div>
	
	<hr>
	
	
	<h2></h2>
	
	<div id="board-list-container">
		<c:if test="${ not empty loginMember }">
			<button id="btn-add" type="button" onclick="location.href='${path}/qna/write'">글쓰기</button>
		</c:if>
		
		<c:if test="${ not empty loginMember && loginMember.id == qa.writerId}">
	    	<button id="btn-add" type="button" onclick="location.href='${path}/qna/list'">1:1 문의하기</button>
		</c:if>

		<table id="tbl-board" class="table table-hover table-striped text-center">
			<tr>
				<th>번호</th>
				<th width = 50%>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>첨부파일</th>
				<th>관리자 답변</th>
			</tr>
			<c:if test="${ empty list }">
				<tr>
					<td colspan="6"> 
						작성한 1:1 문의 게시글이 없습니다.
					</td>
				</tr>	
			</c:if>
			<c:if test="${ not empty list }">
				<c:forEach var="qnaboard" items="${ list }">
					<tr>
						<td>${ qnaboard.rowNum }</td>
						<td>
							<a href="${ path }/qna/view?no=${ qnaboard.no }">
								${ qnaboard.title }</a> 
						</td>
						<td>${ qnaboard.writerId }</td>
						<td>${ qnaboard.createDate }</td>
						<td>
							<c:if test="${ empty qnaboard.originalFileName }">
								<span> - </span>
							</c:if>
							<c:if test="${ not empty qnaboard.originalFileName }">
								<img width="20px" src="${ path }/resources/images/file.png">
							</c:if>
						</td>
						<td>
							<c:if test="${ qnaboard.secretCheck =='Y'}">
								<button>답변 전</button>
							</c:if>
							<c:if test="${ qnaboard.secretCheck =='N'}">
								<button>답변 완료</button>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
		
		
		<div id="pageBar">
			<nav aria-label="...">
  				<ul class="pagination justify-content-center">
    				<li class="page-item">
      					<a class="page-link" href='${ path }/qna/list?page=${ pageInfo.prevPage }'>previous</a>
   					 </li>
   					 
   					 <!--  10개 페이지 목록 -->
					<c:forEach begin="${ pageInfo.startPage }" end="${ pageInfo.endPage }" varStatus="status">
						<c:choose>
							<c:when test="${ status.current == pageInfo.currentPage }">
								<li class="page-item active" aria-current="page">
									<a class="page-link">${ status.current }</a>
								</li>
							</c:when>
							<c:otherwise>
								<li class="page-item">
									<a class="page-link" href='${ path }/qna/list?page=${ status.current }'>${ status.current }</a>
								</li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					
					
    				<li class="page-item">
      					<a class="page-link" href='${ path }/qna/list?page=${ pageInfo.nextPage }'>next</a>
				    </li>
				 </ul>
			</nav>
		</div>
	</div>
		

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
</section>

<jsp:include page="/views/common/footer.jsp" />