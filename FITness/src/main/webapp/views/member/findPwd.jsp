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
			<h3 id="tit_login">비밀번호 찾기</h3>
                <div class="write_form">
          
                    <div><h4>가입시 입력한 아이디를 입력하세요</h4></div>
                    
                    <div class="write_view login_view">
                        <input type="text" name="id" id="id" size="20" placeholder="아이디를 입력해주세요." value="">
                    </div>
                    
                    
                    <div>
<!--                     	<input type="email" name="email" id="email" size ="20" placeholder="이메일을 입력해주세요." value=""> -->
                    </div>
                    
                    <br><br>
                    <div id="password"> </div>
                      <button id="login_join">비밀번호 찾기</button>
                   
                    </div>
                    
                </div>
                
        </div>
    </div>
    
 <script>
 $('#login_join').on('click', function() {
 	let id = $('#id').val();
 	
 	if(id.length < 1) {
 		alert('아이디를 입력해주세요.');
 		return;
 	}
 	

 
 $.ajax({
		type: 'POST',
		url: '${ path }/member/findpwd',
		dataType: 'json',
		data: {
			id
		},
		success: (obj) => {
			
			console.log(obj)
			if(obj.resultCode) {
				if(obj.password == null) {
					$("#password").html("가입된 정보가 없습니다.");
				} else if(obj.resultCode) {
				$("#password").html("회원님의 비밀번호는 " + obj.password + " 입니다.");
				}
			}
		},
		
		error: (error) => {
			console.log(error)
		}


	});
 
 
 });
 
 </script>   

<jsp:include page="/views/common/footer.jsp" />
	
	
	
	