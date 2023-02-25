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
        
        a:hover {
        color: #00FF00;
        text-decoration : none;
        font-size: 13px;
        }
       
		 a{  
        font-size:13px;
        color: black;
        text-decoration: none;
        
 
        }
</style>
<div id="section">
            <div id="content">
                <div class="section_login">
                    <h3 id="tit_login">로그인</h3>
                    <div class="write_form">
                        <div class="write_view login_view">
                            <form name="form" id="form" action="${ path }/member/login" method="POST">
                                <input type="text" name="userId" id="userId" size="20" placeholder="아이디를 입력해주세요" value="">
                                <input type="password" name="userPwd" id="userPwd" size="20" placeholder="비밀번호를 입력해주세요">
                                <div class="checkbox_save">
                                    <div class="login_search">
                                        <a class="link" href="${ path }/member/findid"> 아이디 찾기 </a> <span class="bar"></span> 
                                        <a class="link" href="${ path }/member/findpwd"> 비밀번호 찾기 </a>
                                    </div>
                                <div id="message"></div>
                                </div>
                                <div>
                                    <button type="submit" id="login_login">로그인</button>
                                </div>
                            </form>
                                </div>
                          <button id="login_join" onclick="location.href='${ path }/member/joinagree'">회원 가입</button>
                
                        </div>
                    </div>
            </div>
        </div>
        
        <br/><br/><br/><br/><br/><br/><br/> <br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/> <br/><br/><br/><br/><br/><br/><br/>
        
<jsp:include page="/views/common/footer.jsp" /> 