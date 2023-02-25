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
                <table height="500px">
                    <tr>
                        <th width="25%">아이디</th>
                        <td>
                            <input type="text" name="userId" id="userId" placeholder="6자 이상의 영문과 숫자를 조합하세요" required size="40">
                            <input type="button" id="checkId" value="중복확인">
                        </td> 			
                    </tr>
                    <tr>
                        <th>비밀번호</th>
                        <td>
                            <input type="password" name="userPwd" id="userPwd" placeholder="영문자,숫자,특수문자가 포함된 6~20 범위로 입력하세요"  size="40">
                        </td> 			
                    </tr>
                    <tr>
                        <th>비밀번호확인</th>
                        <td>
                            <input type="password" name="userPwd2" id="userPwd2" placeholder= "비밀번호를 한번 더 입력해주세요" size="40">
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
                            <input type="tel" placeholder="-없이 작성해주세요" name="phone" id="phone"   size="40">								
                        </td> 			
                    </tr>
                    <tr>
                        <th>이메일</th>
                        <td>
                            <input type="email" placeholder="FITness@FITness.com" name="email" id="email"  size="40">	
                           											
                        </td> 			
                    </tr>
                    <tr>
                        <th>주소</th>
                        <td class="field_address">
                            <input type="text" name="address" id="address" size="29" placeholder="주소를 입력해주세요">
                            <input type="button"id="addressNo" class="address_no" data-text="재검색" value="주소 검색">
                        </td>  
                    </tr>  
                    <tr>
                        <th></th>
                        <td><input type="text" name="addressdetail" id="address2" size="40" placeholder="상세주소를 입력해주세요"></td>
                    </tr>  	  	
                </table> 
                <input type="submit" id="btn_join" value="가입 하기" onclick="return validate();">	
            </form>
            <div>   
   
        </div>
     </div>
     </section>
     
   <script>
  
//    		유효성 검사
        function validate() {
            let userId = document.getElementById('userId').value;
            let userPwd = document.getElementById('userPwd').value;
            let userPwd2 = document.getElementById('userPwd2').value;
            let userName = document.getElementById('userName').value;
            let email = document.getElementById('email').value;
			let phone = document.getElementById('phone').value;
            
			
			
			
            
            if(!(/^[a-z][a-z\d]{5,19}$/.test(userId))) {
                alert('아이디 형식이 맞지 않습니다');
                $("#userId").focus();
                return false;
            }
            
            if(!(/^[\w!@#$%^&*-]{6,20}$/.test(userPwd))) {
                alert('비밀번호 형식이 맞지 않습니다');
                $("#userPwd").focus();
                return false;
            }

          
            if(userPwd !== userPwd2) {
                alert("동일한 비밀번호 값을 입력하세요")
                document.getElementById('userPwd2').value = '';
                document.getElementById('userPwd2').focus();

                return false;
            }
          
            if(!(/^[\w]+@[\w]+\.[A-Za-z\.]{2,6}$/.test(email))) {
                alert('이메일 형식에 맞지 않습니다');
                $("#email").focus();
                return false;
            }
            
            if(!(/01[016789][^0][0-9]{2,3}[0-9]{3,4}$/.test(phone))) {
            	alert("전화번호를 -없이 입력해주세요");
            	return false;
            }
   		} 
        // 아이디 중복 검사 
        $(document).ready(() => {
        	$('#checkId').on('click', () => {
        		let userId = $('#userId').val();
        		
        	
        		
        		$.ajax({
        			type: 'POST',
        			url: '${ path }/member/checkid',
        			dataType: 'json',
        			data: {
        				userId
        			},
        			success: (obj) => {
        				console.log(obj);
        				
        				if(obj.isChecked){	
        					alert("이미 사용중인 아이디 입니다");
        				} else {
        					alert("사용 가능한 아이디 입니다");
        				}
        			},
        			
        			error: (error) => {
        				console.log(error);
        			}
        		});
        	});
        });
        
    </script>
   
        
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>   
    <script> 
         window.onload = function(){
            document.getElementById("addressNo").addEventListener("click", function(){ 
                new daum.Postcode({
                    oncomplete: function(data) { 
                        document.getElementById("address").value = data.address; 
                        document.querySelector("input[name=addressdetail]").focus(); 
                    }
                }).open();
            });
        }
    </script>
         
       
   
    
<jsp:include page="/views/common/footer.jsp" />
    