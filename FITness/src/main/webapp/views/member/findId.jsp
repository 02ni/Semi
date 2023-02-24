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
                <h3 id="tit_login">아이디 찾기</h3>
                <div class="write_form">
          
                    <div><h4>가입시 입력한 휴대폰 번호를 입력하세요</h4></div>
                    <div class="write_view login_view">
                        <input type="tel" name="phone" id="phone" size="20" placeholder="번호를 입력해주세요" value="">
                    </div>
                    <br><br>
                    <div id="email"> </div>
                      <button id="login_join">아이디 찾기</button>
                    </div>
                </div>
                
        </div>
    </div>
<script>
	//입력시 null과 입력이 정확하지 않을시
// 	window.onload = function(){
//     document.getElementById("login_join").addEventListener("click", function(){ //주소입력칸을 클릭하면
//     var frm = document.findid;
    	
//     if(frm.phone.value.length < 1) {
//     	alert('번호를 입력해주세요');
//     	return;
//     }
       
//     if (frm.phone.value.length != 11) {
// 		  alert("번호를 정확하게 입력해주세요");
// 		  return;
		  
//     frm.method = "POST";
// 	frm.action = "findIdResult.jsp";
// 	frm.submit();  
// 	 }
    
   
    
    
    
    
// 	});
// };

    $('#login_join').on('click', function() {
    	let phone = $('#phone').val();
    	var frm = document.findid;
    	
    	if(phone.length < 1) {
    		alert('번호를 입력해주세요.');
    		return;
    	}
    	
    	if(phone.length != 11) {
    		alert('번호를 정확하게 입력해주세요.');
    		return;
    	}
    	
    	$.ajax({
    		type: 'POST',
    		url: '${ path }/member/findid',
    		dataType: 'json',
    		data: {
    			phone
    		},
    		success: (obj) => {
    			
    			console.log(obj)
    			if(obj.resultCode) {
					$("#email").html("회원님의 아이디는 " + obj.id + " 입니다.");
					
				} else {
					$("#email").html("조회된 아이디가 없습니다.");
				}
    		},
    		
    		error: (error) => {
    			console.log(error)
    		}

    	});
    });
    
    







</script>








<jsp:include page="/views/common/footer.jsp" />