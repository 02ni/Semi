<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

  <!-- 서머노트 스타일 -->
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  
  <!-- 서머노트를 위해 추가해야할 부분 -->
  <script src="${pageContext.request.contextPath}/resources/summernote/summernote-lite.js"></script>
  <script src="${pageContext.request.contextPath}/resources/summernote/lang/summernote-ko-KR.js"></script>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/summernote/summernote-lite.css">
  <!--  -->

<style>
    h3 { text-align: center; }
</style>


<section id="content">
    <div class="formBox">
	
	<h1></h1>
	
        <h3>자유게시판</h3>
        <form action="${ path }/board/write" method="POST"  enctype="multipart/form-data">
			<table id='tbl-board'>
			    <colgroup>
                    <col style="width:150px">
                    <col style="width:850px">
                </colgroup>
				<tr>
					<th>제목</th>
					<td><input type="text" name="title" id="title" style="width:780px;"> </td>
				</tr>
				<tr>
					<th>작성자</th>
					<td>${ loginMember.id }</td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td><input type="file" name="upfile"></td>
				</tr>
				<tr>
					<th>내용</th>
					 
					<td>	 
					 <!--<input name="content" type="text" class="tf_comm" id="summernote" name="ord_receiver_memo" style="width: 50rem;" maxlength="46"> -->
					 <textarea name="content" id="summernote" cols="40" rows="15" maxlength="2000"></textarea>
					</td>
				</tr>
            </table>
            <div class="btns">
                <input type="submit" value="등록" id="join">
                <input type="reset" value="취소" id="cancel">
            </div>
        </form>
        </div>
</section>
   
<script>
$('#summernote').summernote({
	  height: 450,
	  lang: "ko-KR"
	});
	

</script>

<jsp:include page="/views/common/footer.jsp" /> 