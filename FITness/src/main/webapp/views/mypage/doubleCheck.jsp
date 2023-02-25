<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<c:set var="path" value="${ pageContext.request.contextPath }"/>

<jsp:include page="/views/common/header.jsp" />

<style>


#content {
            margin: auto;
            width: 350px;
            height: 460px;
            border-radius: 5px;
            text-align: center;
            padding: 20px;
            position: absolute;
            left: 50%;
            top:40%;
            transform: translate(-50%);
        }
    
    
        input {
            width: 100%;
            height: 50px;
            padding: 10px;
            box-sizing: border-box;
            
        }

        .checkbox_save {
            text-align: right;
        }

        #login_login {
            height: 50px;
            padding: 10px;
            box-sizing: border-box;
            width: 100%;
            background-color: yellow;
            font-weight: bold;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        
        #login_join {
            height: 50px;
            padding: 10px;
            box-sizing: border-box;
            width: 100%;
            margin-top: 10px;
            background-color: orange;
            font-weight: bold;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
</style>
<div id="section">
            <div id="content">
                <div class="section_login">
                    <h3 id="tit_login">회원님의 정보보호를 위해<br> 로그인 정보 인증이 필요합니다.</h3>
                    <div class="write_form">
                        <div class="write_view login_view">
                            <form name="form" id="form" action="${ path }/mypage/doubleCheck" method="POST">
                                <input type="text" name="userId" id="userId" size="20" placeholder="아이디를 입력해주세요" value="">
                                <input type="password" name="userPwd" id="userPwd" size="20" placeholder="비밀번호를 입력해주세요">
                                <div class="checkbox_save">
                                    <div class="login_search">
                                        <a class="link" href="index.jsp?folder=login&amp;category=Find_id"> 아이디 찾기 </a> <span class="bar"></span> 
                                        <a class="link" href="index.jsp?folder=login&amp;category=Find_pw"> 비밀번호 찾기 </a>
                                        
                                    </div>
                                <div id="message"></div>
                                </div>
                                <div>
                                    <button type="submit" id="login_login">로그인</button>
                                </div>
                                </div>
                            </form>
                        </div>
                    </div>
            </div>
        </div>
<jsp:include page="/views/common/footer.jsp" /> 