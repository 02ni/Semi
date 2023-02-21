<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${ pageContext.request.contextPath }"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FITness</title>
    <link rel="stylesheet" href="${ path }/resources/css/style.css">
    <script src="${ path }/resources/js/jquery-3.6.3.js"></script>
</head>
<body>
    <div class="wrap">
        <div id="header">

            <div id="header1">  
            
            </div>

            <div id="header2" align="center" >
	          <%-- <a href="${ path }/"> <h1 class="logo" align="center">FITness(로고 수정중)</h1></a> --%>
	           
	          <a href="${ path }/"><img src="${ path }/resources/images/logotext.png" height="100px" align="center"></a>
	          
            </div>

		<c:if test="${ empty loginMember }">
            <div id="header3">
                <a href="${ path }/member/login" class="mainlogin">로그인</a>
                <a href="${ path }/member/join" class="mainlogin">회원가입</a> 
            </div>	
		</c:if>
		
		<c:if test="${ not empty loginMember }">
				<table>
					<tr>
						<td colspan="2">
							${ loginMember.name}님 안녕하세요.
						</td>
					</tr>
					<tr>
						<td>
							<button onclick="location.href='${ path }/member/myPage'">내 정보</button>
						</td>
						<td>
							<button onclick="location.replace('${ path }/logout')">로그아웃</button>
						</td>
						<td>
						<c:if test="${ not empty loginMember && loginMember.role.equals('ROLE_ADMIN')}">
		    				<button>ADMIN</button>
		    			</c:if>
						</td>
					</tr>
				</table>
			</c:if>
      </div>

        <div id="navigator">
            <ul id="navi">
            		<li><a href="${ path }/">Home</a></li>
	                <li><a href="${ path }/gym/list">Gym찾기</a></li>
	                <li id="board">
	                    <a href="${ path }/notice/list">게시판</a>
	                    <ul>
		                    <li id="board"><a href="${ path }/notice/list">공지사항</a></li>
	                        <li><a href="${ path }/faq/list">FAQ</a></li>
	                        <li><a href="${ path }/board/list">자유게시판</a></li>
	                        <li><a href="${ path }/qna/list">1:1 문의</a></li>
	                    </ul>
	                </li>
	                <li>
	                    <a href="#">마이페이지</a>
	                    <ul>
	                        <li><a href="#">정보수정</a></li>
	                        <li><a href="#">장바구니</a></li>
	                        <li><a href="#">구매내역</a></li>
	                    </ul>
	                </li>
	                
            </ul>
        </div>
        
