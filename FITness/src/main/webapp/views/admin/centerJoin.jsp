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
    <title>Document</title>
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Noto+Sans&family=Open+Sans&display=swap');
              @font-face {
                  font-family: 'GmarketSansMedium';
                  src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansMedium.woff') format('woff');
                  font-weight: normal;
                  font-style: normal;
              }
              * {
                  font-family: 'GmarketSansMedium';
              }
              body {
                  font-family: 'Noto Sans', sans-serif;
              }
              div * {
                  /* border: 2px solid red; */
                  box-sizing: border-box;
              }
              .wrap{
                  width:1000px;
                  height:1400px;
                  margin:auto;
              }
              .logo {
                  line-height: 2;
                  color: yellowgreen;
              }
      
      /* 헤더 */
              #header{
                  height: 10%
              }
      
              #header1{
                  width: 30%;  
              }
      
              #header2{
                  width: 40%;
              }
      
              #header3 {
                  padding-top: 37px;
                  padding-bottom: 15px;
                  border: 1px;
                  text-align: right;
                  width: 26%;
              }
              #header>div{
                  float: left;
                  height: 100%;
              }
      
              .mainlogin{  
              font-size:13px;
              color: #999999;
              text-decoration: none;
              letter-spacing: 0.01em;
              margin: 5px;
              line-height: 6;
              }
      
              .mainlogin:hover {
              color: #00FF00;
              text-decoration : none;
              font-size: 13px;
              }
      
      /* 메뉴바 */
              #navigator {
                  height: 3%;
                  border-bottom: 1px solid;
                  z-index:999;           
              }
      
              #navi{
                  list-style-type: none;
                  margin:0;
                  padding:0;
                  height:100%;
                  position: relative;
                  z-index: 9999;
              }
      
      
              #navi> li{
                  float:left;
                  width:25%;
                  height:100%;
                  text-align:center;
                  font-size:13px;
                  line-height:5px;
              }
              
              #navi> li:hover{
                  background-color: rgba(246, 139, 0, 0.917);
              }
              
      
              #navi a{
                  text-decoration: none;
                  color: black;
                  font-size: 1.5em;
                  font-weight: 900;
                  height: 100%;
                  line-height: 35px;
                  vertical-align: middle;
              }
      
              #navi>li>ul a:hover{
                  color: orangered;
                  
              }
      
              #navi>li>ul{
                  list-style-type: none;
                  display: none;
                  background-color: rgb(221, 215, 215, 0.8);
                  padding: 5px 0 0 0 ;
                  margin: 10px 0 0 0 ; 
              }
      
              #navi>li:hover>ul{
                  display: block;
              }
      
              #navi>li>ul>li:hover {
                  display: block;
                  transition: ease 1s;
              }
      
              #navi>li>ul a{
                  font-size: 1.4em;
              }


            section {
            height: 87%;
            font-family: 'GmarketSansMedium';
            z-index:1;
         }


              #join_wrap {
            border-top: 2px solid #333;
            

            }

    #content, * {
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

    #join_wrap textarea,
    #join_wrap input[type="text"],
    #join_wrap input[type="email"],
    #join_wrap input[type="password"],
    #join_wrap input[type="tel"],
    #join_wrap input[type="url"],
    
    #join_wrap input[type="button"]
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
    
    #join_wrap input[type="button"] {
    color: #da4104;
    background-color: #fff;
    font-weight: bold;  
    cursor: pointer;
    border:1px solid #da4104
    }

    #btn_join {
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
            background-color: yellowgreen;
            color: #fff;
            font-size: 15px;
            line-height: 54px;
        }
        
        

      </style>
</head>
<body>
    <div class="wrap">
        <div id="header">
            <div id="header1"> 
            <img src="${ path }/resources/images/logotext.png" height="100px" align="center">
            </div>
            
            <div id="header3">
                
            </div>
        </div>
        <div id="navigator">
            <ul id="navi">
               
                <li>
                    <a href="${ path }/admin/memberlist">회원 관리</a>
                </li>
                <li>
                    <a href="${ path }/admin/boardnotice">게시판 관리</a>
                
                </li>
                <li><a href="${ path }/admin/centerlist">센터 관리</a></li>
            </ul>
        </div>

    <section id="content">
        <h2 align="center">센터 등록</h2><br><br>
        <div id="join_wrap">	 	
            <form name="joinform" action="${ path }/admin/centerjoin" method="POST">
                <table height="600px">
                    <tr>
                        <th width="25%">센터 명</th>
                        <td>
                            <input type="text" name="centerId" id="centerId" placeholder="센터 명을 입력해주세요" required size="40">
                          
                        </td> 			
                    </tr>

                 

                    <tr>
                        <th height="80">카테고리</th>
                        <td width="3">
                            <label><input type="checkbox" name="cate" id="cate1" value="헬스">헬스</label>
                            <label><input type="checkbox" name="cate" id="cate2" value="PT">PT</label>
                            <label><input type="checkbox" name="cate" id="cate3" value="G.X">G.X</label>
                            <label><input type="checkbox" name="cate" id="cate4" value="스피닝">스피닝</label>
                            <label><input type="checkbox" name="cate" id="cate5" value="크로스핏">크로스핏</label>
                            <label><input type="checkbox" name="cate" id="cate5" value="골프">골프</label>
                            <label><input type="checkbox" name="cate" id="cate5" value="수영">수영</label>
                            <label><input type="checkbox" name="cate" id="cate5" value="요가">요가</label>
                            <label><input type="checkbox" name="cate" id="cate5" value="필라테스">필라테스</label>
                           
                        </td> 			
                    </tr>
             
                    <tr>
                        <th>대표 연락처</th>
                        <td>
                            <input type="tel" id="ceoPhone" name="ceoPhone" placeholder= "연락처를 입력해주세요" size="40">
                        </td> 			
                    </tr>
                    <tr>
                        <th>대표 이메일</th>
                        <td>
                            <input type="email" name="ceoEmail" id="ceoEmail" placeholder="이메일을 입력해주세요"  required  size="40">				
                        </td> 			
                    </tr>
                
                    <tr>
                        <th>센터 연락처</th>
                        <td>
                            <input type="tel" placeholder="-없이 입력해주세요" name="gymPhone" id="gymPhone"  size="40">	
                    
                        </td> 			
                    </tr>
                    <tr>
                        <th>센터 주소</th>
                        <td class="field_address">
                            <input type="text" name="address" id="address" size="26" placeholder="주소를 입력해주세요">
                            <input type="button"id="addressNo" class="address_no" data-text="재검색" value="주소 검색">
                        </td>  
                    </tr>  
                    <tr>
                        <th></th>
                        <td ><input type="text" name="addressdetail" id="address2" size="40" placeholder="상세주소를 입력해주세요"></td>
                    </tr>  	  	  	
                </table> 
                <input type="submit" id="btn_join" value="등록 하기">	
            </form>
            <div>   
   
        </div>
    </div>
    </section>
    
     <p align="center" style="font-size: small;">Copyright © semi 3. All rights reserved.</p>
     <br>
 </div>     
</div>
</body>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>   
    <script> 
         window.onload = function(){
            document.getElementById("addressNo").addEventListener("click", function(){ //주소입력칸을 클릭하면
                //카카오 지도 발생
                new daum.Postcode({
                    oncomplete: function(data) { //선택시 입력값 세팅
                        document.getElementById("address").value = data.address; // 주소 넣기
                        document.querySelector("input[name=addressdetail]").focus(); //상세입력 포커싱
                    }
                }).open();
            });
        }
    </script>



</html>