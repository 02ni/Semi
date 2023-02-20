<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${ pageContext.request.contextPath }"/>

<jsp:include page="/views/common/header.jsp"/>

<style>
	section#board-list-container{width:600px; margin:0 auto; text-align:center;}
	section#board-list-container h2{margin:10px 0;}
	table#tbl-board{width:100%; margin:0 auto; border: 1px black; border-collapse:collapse; clear:both;}
	table#tbl-board tr>th {border: 0.3px solid grey; padding: 5px 0; text-align:center; background-color: rgb(181,181,181);} 
	table#tbl-board th, table#tbl-board td {border: 0.3px solid grey; padding: 5px 0; text-align:center;} 
	
	/*글쓰기버튼*/
	input#btn-add{float:right; margin: 0 0 15px;}
	
	/*페이지바*/
	div#pageBar{margin-top:10px; text-align:center; background-color:rgba(246, 139, 0, 0.917);}
</style>

<link rel="stylesheet" href="${ path }/resources/css/board.css">

<section id="content">
		<div id="btn1">
			<ul>
				<li class="notice_select" onclick="location.href='${ path }/notice/list'">
					<span>공지사항</span>
				</li>
				<li class="faq_select active" onclick="location.href='${ path }/faq/list'">
					<span>FAQ</span>
				</li>
				<li class="freeBoard_select" onclick="location.href='${ path }/board/list'">
					<span>자유게시판</span>
				</li>
				<li class="teamBoard_select" onclick="location.href='${ path }/teamboard/list'">
					<span>1:1문의</span>
				</li>
			</ul>
		</div>
		
	<div class="clear"></div>
	
	<hr>

	<h2></h2>
	
	<div id="board-list-container">
		<c:if test="${ not empty loginMember }">
			<button type="button" onclick="location.href='${path}/board/write'">글쓰기</button>
		</c:if>

		<table id="tbl-board">
			<tr>
				<th>번호</th>
				<th width = 60%>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>첨부파일</th>
				<th>조회수</th>
			</tr>
			<c:if test="${ empty list }">
				<tr>
					<td colspan="6"> 
						조회된 게시글이 없습니다.
					</td>
				</tr>	
			</c:if>
			<c:if test="${ not empty list }">
				<c:forEach var="board" items="${ list }">
					<tr>
						<td>${ board.rowNum }</td>
						<!-- 식별자 역할을 하는 boardno 값을 불러오도록 -->
						<td>
							<a href="${ path }/board/view?no=${ board.no }">
								${ board.title }
							</a>
						</td>
						<td>${ board.writerId }</td>
						<td>${ board.createDate }</td>
						<td>
							<c:if test="${ empty board.originalFileName }">
								<span> - </span>
							</c:if>
							<c:if test="${ not empty board.originalFileName }">
								<img width="20px" src="${ path }/resources/images/file.png">
							</c:if>
						</td>
						<td>${ board.readCount }</td>
					</tr>
				</c:forEach>
			</c:if>
			
		</table>
	</div>
</section>

<jsp:include page="/views/common/footer.jsp" />