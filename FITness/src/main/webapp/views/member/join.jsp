<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<c:set var="path" value="${ pageContext.request.contextPath }"/>

<jsp:include page="/views/common/header.jsp"/>

<link rel="stylesheet" href="${ path }/resources/css/join.css">

 <section id="content">
        <h2 align="center">회원 가입</h2><br><br>
        <div id="join_wrap">	 	
            <form name="joinform" action="${ path }/member/join" method="POST">
                <table>
                    <tr>
                        <th width="25%">아이디</th>
                        <td>
                            <input type="text" name="userId" id="userId" placeholder="6자 이상의 영문과 숫자를 조합하세요." required size="40">
                            <input type="button" id="" value="중복확인">
                        </td> 			
                    </tr>
                    <tr>
                        <th>비밀번호</th>
                        <td>
                            <input type="password" name="userPwd" id="userPwd" placeholder="비밀번호를 입력해주세요"  size="40">
                        </td> 			
                    </tr>
                    <tr>
                        <th>비밀번호확인</th>
                        <td>
                            <input type="password" id="userPwd2" placeholder= "비밀번호를 한번 더 입력해주세요" size="40">
                        </td> 			
                    </tr>
                    <tr>
                        <th>이름</th>
                        <td>
                            <input type="text" name="userName" id="userName" required  size="40">				
                        </td> 			
                    </tr>
                    <tr>
                        <th>휴대폰</th>
                        <td>
                            <input type="tel" placeholder="-없이 작성해주세요" name="phone" id="phone" maxlength="11"  size="40">								
                        </td> 			
                    </tr>
                    <tr>
                        <th>이메일</th>
                        <td>
                            <input type="email" placeholder="FITness@FITness.com" name="email" id="email"  size="40">	
                            <input type="button" id="" value="중복확인">											
                        </td> 			
                    </tr>
                    <tr>
                        <th>주소</th>
                        <td class="field_address">
                            <input type="text" name="address" id="address" size="26" placeholder="주소를 입력해주세요">
                            <input type="button"id="addressNo" class="address_no" data-text="재검색" value="주소 검색">
                        </td>  
                    </tr>    	
                </table> 
                <input type="submit" id="btn_join" value="가입 하기" onclick="return validate();">	
            </form>
            <div>   
   
        </div>
     </div>
     </section>
   <script>
        function validate() {
            let userId = document.getElementById('userId').value;
            let userPwd1 = document.getElementById('userPwd').value;
            let userPwd2 = document.getElementById('userPwd2').value;
            let userName = document.getElementById('userName').value;
            let email = document.getElementById('email').value;

            if(!(/^[a-z][a-z\d]{5,19}$/.test(userId))) {
                alert('아이디 입력이 잘못 되었습니다.');
                $("#userId").focus();
                return false;
            }
            
            if(!(/^[\w!@#$%^&*-]{6,20}$/.test(userPwd))) {
                alert('유효한 비밀번호를 입력하세요.');
                $("#userPwd").focus();
                return false;
            }

            // 비밀번호 확인 검사
            if(userPwd !== userPwd2) {
                alert("동일한 비밀번호 값을 입력하세요.")
                document.getElementById('userPwd2').value = '';
                document.getElementById('userPwd2').focus();

                return false;
            }
            // 이메일
            if(!(/^[\w]+@[\w]+\.[A-Za-z\.]{2,6}$/.test(email))) {
                alert('유효한 이메일을 입력하세요.');
                $("#email").focus();
                return false;
            }
        }
    </script>
    
<jsp:include page="/views/common/footer.jsp" />
    