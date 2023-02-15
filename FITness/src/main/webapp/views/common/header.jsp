<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>COMPANDA</title>
<style type="text/css">
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans&family=Open+Sans&display=swap');
    div * {
        /* border: 2px solid; */
    }

    
    /* #content {
        /* border-top: 2px solid red; */
    

    

         #navi{
            list-style-type: none;
            margin:0;
            padding:0;
            height:100%;
        }

        #navi>li{
            float:left;
            width:25%;
            height:100%;
            text-align:center;
            font-size:18px;
            line-height:35px;
        }

        #navi a{
            text-decoration: none;
            color: black;
            font-size: 1.5em;
            font-weight: 900;
            height: 100%;
            line-height: 35px;
        }

        #navi a:hover{
            color: orangered;
        }

        #navi>li>ul{
            list-style-type: none;
            padding: 0;
            display: none;
        }

        #navi>li>a:hover+ul{
            display: block;
        }

        #navi>li>ul:hover {
            display: block;
        }

        #navi>li>ul a{
            font-size: 1.4em;
        }

body{
   font-family: 'Noto Sans', sans-serif;
}
a{   
   font-size:13px; 
   color: #999999; 
   text-decoration: none;
    letter-spacing: 0.01em;
}
a:hover {
   color: #00FF00; 
   text-decoration : none;
   font-size: 13px;
}



.common.center{
   padding-top: 15px;
    padding-bottom: 15px;
   border: 1px; 
   float: left; 
   width: 47%;

}
.common.left{
   padding-top: 35px;
    padding-bottom: 15px;
   border: 1px; 
   float: left; 
   width: 26%;

}
.common.right{
      padding-top: 37px;
    padding-bottom: 15px;
   border: 1px; 
   float: right; 
   width: 26%;
}
#header{
   max-width: 1400px;
   margin: auto;
   text-align: center;
   position: sticky;
   top: 0px; /* 도달했을때 고정시킬 위치 */
   padding: 5px;
   z-index: 10;
   
}

</style>

</head>
<body>
<div id="header">
   <div class="companda_header">
      <div class="common left">
         <div>
         </div>
      </div>
      <div class="common center">
         <div class="hcontent">
            <h1>FITness</h1>
         </div>
      </div>
      <div class="common right">
         <div class="hcontent">
            <!--클릭시 페이지 이동하도록 href 설정해주기 -->

                <a href="#">Log In</a>&nbsp;&nbsp;&nbsp;
                <a href="#">Sign Up</a>&nbsp;&nbsp;&nbsp;     

              
         </div>
      </div>
   </div>
</div>
</body>
</html>
<br><br><br><br><br><br>
<hr>
<div id="navigator">
    <ul id="navi">
        <li><a href="#">Gym찾기</a></li>
        <li>
            <a href="#">게시판</a>
            <ul>
                <li><a href="#">일행구하기</a></li>
                <li><a href="#">자유게시판</a></li>
                <li><a href="#">질문하기</a></li>
            </ul>
        </li>
        <li>
            <a href="#">마이페이지</a>
            <ul>
                <li><a href="#">정보수정</a></li>
                <li><a href="#">장바구니</a></li>
                <li><a href="#">구매내역</a></li>
            </ul>
        </li>
        <li><a href="#">QnA</a></li> 
    </ul>
</div>
