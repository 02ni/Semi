<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="path" value="${ pageContext.request.contextPath }"/>

<jsp:include page="/views/common/header.jsp" />

<style>

    #view-container {
    border-top: 2px solid #333;
    }

    #content, * {
            margin: 10px;
            text-align: center;
            box-sizing: border-box;
            /* border: 1px solid red; */
            margin-left:auto;
            margin-right:auto;
    }

    th {
    text-align: left;
    font-size: 12px;
    } 

    td {
    text-align: left;
    }

    td > input {
    text-align: left;
    }

    th > input {
        font-size: small;
    }

    .field_address input[type="text"]{
        text-align: left;
    }

    #content {width: 600px;}

    #view-container textarea,
    #view-container input[type="text"],
    #view-container input[type="email"],
    #view-container input[type="password"],
    #view-container input[type="tel"],
    #view-container input[type="button"]
    {
    height:44px;padding:0 14px;
    border:1px solid #ccc;
    font-size:12px;
    color:#333;
    line-height:20px;
    outline:none;
    border-radius:4px;
    vertical-align:top;
    }
    
    #view-container input[type="button"] {
    color: #da4104;
    background-color: #fff;
    font-weight: bold;  
    cursor: pointer;
    border:1px solid #da4104
    }

    #btnInUpdate {
            height: 55px;
            padding: 10px;
            box-sizing: border-box;
            width: 150px;
            margin-top: 15px;
            font-weight: bold;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            padding: 0;
            letter-spacing: -0.05em;
            cursor: pointer;
            border-radius: 4px;
            text-align: center;
            outline: none;
            margin-left: 5px;
            background-color: #da4104;
            color: #fff;
            font-size: 15px;
            line-height: 54px;
        }
        
        #backToMain {
            height: 55px;
            padding: 10px;
            box-sizing: border-box;
            width: 150px;
            margin-top: 15px;
            font-weight: bold;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            padding: 0;
            letter-spacing: -0.05em;
            cursor: pointer;
            border-radius: 4px;
            text-align: center;
            outline: none;
            margin-left: 5px;
            background-color: #da4104;
            color: #fff;
            font-size: 15px;
            line-height: 54px;
        }
        
        #btnDelete {
            height: 55px;
            padding: 10px;
            box-sizing: border-box;
            width: 150px;
            margin-top: 15px;
            font-weight: bold;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            padding: 0;
            letter-spacing: -0.05em;
            cursor: pointer;
            border-radius: 4px;
            text-align: center;
            outline: none;
            margin-left: 5px;
            background-color: #da4104;
            color: #fff;
            font-size: 15px;
            line-height: 54px;
        }
    </style>
<section id="content">
        <h2 align="center">회원 정보 수정</h2><br>
        <div id="view-container">       
            <form id="update_form" action="${ path }/member/update" method="POST">
                <table>
                    <tr>
                        <th width="25%">아이디</th>
                        <td>
                            <input type="text" name="userId" id="userId" value="${ loginMember.id }" readonly required size="40">
                        </td>          
                    </tr>
                    <tr>
                        <th>비밀번호</th>
                        <td>
                            <input type="password" name="userPwd" id="userPwd" placeholder="변경할 비밀번호를 입력해주세요"  size="40">
                        </td>          
                    </tr>
                    <tr>
                        <th>비밀번호확인</th>
                        <td>
                            <input type="password" name="userPwd2" id="userPwd2" placeholder= "변경할 비밀번호를 한번 더 입력해주세요" size="40">
                        </td>          
                    </tr>
                    <tr>
                        <th>이름</th>
                        <td>
                            <input type="text" name="userName" id="userName" value="${ loginMember.name }" readonly size="40">            
                        </td>          
                    </tr>
                    <tr>
                        <th>휴대폰</th>
                        <td>
                            <input type="tel" name="phone" value = "${ loginMember.phone }" id="phone" maxlength="11"  size="40">                        
                        </td>          
                    </tr>
                    <tr>
                        <th>이메일</th>
                        <td>
                            <input type="email" name="email" value = "${ loginMember.email }" id="email"  size="40">   
                                                          
                        </td>          
                    </tr>
                    <tr>
                        <th>주소</th>
                        <td class="field_address">
                            <input type="text" name="address" id="address" value = "${ loginMember.address }" size="40">
                            
                        </td>  
                    </tr>       
                </table> 
                  
               <input type="button" id="btnInUpdate" value="수정하기" onclick="updateCheck();">
               <input type="button" id="btnDelete" value="회원탈퇴">
               <input type="button" id="backToMain" value="메인으로">
                   
            </form>
        </div>	
    </section>
<script>

	$('#btnDelete').on('click', () => {
		if(confirm('정말로 탈퇴하시겠습니까?')) {
			location.replace('${ path }/member/delete');
		}
	});
	
	$('#backToMain').on('click', () => {
		location.replace('${path}/');
	});
	
	
	function updateCheck() {
	
 		let userPwd = $('#userPwd').val();
        let pwd2 = $('#userPwd2').val();
        let phone = $('#phone').val(); 
        let email = $('#email').val();
        /*
        변수명 짧은것보다 길더라도 처음보는사람도 보자마자 알수있게 지어주는게 더 중요함, 언더바 웬만하면 쓰지말아라 옛날사람들이 많이 쓴다
        객체지향 프로그램인데 이따위로하면 자바쓰는 의미가 없음 멍청한거임
        member클래스에 메소드 추출해서 정리 좀 해라.
        지금 코드 가독성 매우 떨어짐 같은 팀이였으면 뒤졌음 더 열심히 해야 함 
        실용주의프로그래머 책 정독 - 상하 피드백
        */
 		
        //정규식 선언부
        const passwordReg = /^[\w!@#$%^&*-]{8,15}$/; //비밀번호 
        const phoneReg = /^[0-9]+$/; //숫자만 
        const emailReg = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;//이메일
 		
        
        if(!passwordReg.test(userPwd)) {
            alert('영문자, 숫자, 특수문자가 반드시 포함된 8 ~ 15자 암호를 입력해 주세요.');  
            return false;
        }
        
		if(userPwd !== pwd2) {
            alert("비밀번호가 일치하지 않습니다.")
            return false;
        } 
        
        if(!phoneReg.test(phone)) {
            alert('숫자만 입력할 수 있습니다.');
            return false;
        }
        if(!emailReg.test(email)) {
            alert('이메일 형식을 확인해주세요');
            return false;
        }
        
        $('#update_form').submit();
    }
</script>

<jsp:include page="/views/common/footer.jsp" /> 