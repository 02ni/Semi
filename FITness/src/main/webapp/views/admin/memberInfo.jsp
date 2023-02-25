<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
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
              div {
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
        <h2 align="center">${member.name } 님의 상세 정보</h2><br><br>
        <div id="join_wrap">	 	
                <table height="400px">
                
                    <tr>
                        <th width="25%">이름</th>
                        <td>
                            <input type="text" name="" id=""  required size="20"
                            value="${member.name }">
                         </td>
                        	 
                    </tr>
                    <tr>
                        <th>아이디</th>
                        <td>
                            <input type="text" id=""  size="20" value="${member.id }">
                        </td> 						
                    </tr>
                    <tr>
                        <th>연락처</th>
                        <td>
                            <input type="tel" id=""  size="20"  value="${member.phone }">
                        </td> 			
                    </tr>
                    <tr>
                        <th>회원 주소</th>
                        <td class="field_address">
                            <input type="text" name="" id="" size="40" value="${member.address}">
                            
                        </td> 		
                    </tr>
                  
     
                </table> 
          
                <button id="btn_join" onclick="location.href='${ path }/admin/memberlist'">목록으로</button>
                	
          	
   
        </div>
  
    </section>
    
     <p align="center" style="font-size: small;">Copyright © semi 3. All rights reserved.</p>
     <br>
 </div>     

</body>
</html>