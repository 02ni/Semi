<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${ pageContext.request.contextPath }"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${ path }/resources/css/style.css">
<script src="${ path }/resources/js/jquery-3.6.3.js"></script>
</head>
<body>
	<header>
		<h1>Hello MVC</h1>
				
		<div class="login-container">
			<c:if test="${ empty loginMember }">		
				<form id="loginFrm" action="${ path }/login" method="post">
					<table>
						<tr>
							<td>
								<input type="text" name="userId" id="userId" placeholder="아이디" 
									value="${ empty cookie.saveId ? '' : cookie.saveId.value}" required>
									<!-- 쿠키가 있으면 아이디가 유지가 된다. 
										cookie.saveId에 해당하는게 잇는지 확인 하고 있으면 value 값을 가져온다.
									-->
							</td>
							<td></td>
						</tr>
						<tr>
							<td>
								<input type="password" name="userPwd" id="userPwd" placeholder="비밀번호" required>
							</td>
							<td>
								<input type="submit" value="로그인">						
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<label><input type="checkbox" name="saveId"
										${ empty cookie.saveId ? "" : "checked" }>아이디 저장</label>
								<input type="button" value="회원가입" onclick="location.href = '${ path }/member/enroll';"> 
							</td>
						</tr>
					</table>
				</form>
			</c:if>
			
			<c:if test="${ not empty loginMember }">
				<table>
					<tr>
						<td colspan="2">
							${ loginMember.name } 님 안녕하세요.
						</td>
					</tr>
					<tr>
						<td>
							<button onclick="location.href='${ path }/member/myPage'">내 정보</button>
						</td>
						<td>
							<button onclick="location.replace('${ path }/logout')">로그아웃</button>
						</td>
					</tr>
				</table>
			</c:if>
			
			
		</div>
		<nav>
			<ul class="main-nav">
				<li class="home"><a href="${ path }/">Home</a></li>
				<li id="board"><a href="${ path }/board/list">게시판</a></li>
				<li id="admin-member"><a href="${ path }/">회원관리</a></li>
			</ul>
		</nav>
	</header> 