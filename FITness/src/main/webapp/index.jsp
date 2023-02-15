<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/views/common/header.jsp" />
  <style>
       #content1{
          height:200px;
          margin-top: 30px;
          margin-bottom: 100px;
          padding:0px;
          }
       
       #content2{
       margin: 24px auto;
       display: flex;
       background-color: rgb(52, 73, 94);
       }
       
       .guide{
        border-radius: 55px;
        width: 25%;
        height: 80%;
        margin: 15px;
        text-align: center;
       }
       
       #guide1{
       background-color: rgb(243, 156, 18);
       }
        #guide2{
       background-color: rgb(241, 196, 15);
       }
        #guide3{
       background-color: rgb(241, 196, 15);
       }
        #guide4{
       background-color: rgb(243, 156, 18);
       }
       .guide  h2 {
          font-size: 23pt;
          color: white;
          margin: 0px;
       }
       .guide  h4 {
          font-size: 15pt;
          margin: 3px;
       }
       .guide  p {
          font-size: 10pt;
          font-weight:bold;
          color: rgb(79, 79, 79);
       }
      #content2 button {
      background-color: rgb(94, 94, 94);
          color: white;
          border-radius: 5px;
          width: 75px;
          height: 30px;
          border: none;
      }

    </style>
 <section id="content">


                <div id="content1">
                    <img src="resources/images/maintest.png" width="100%">
                </div>

                 <div id="content2">
                        <div id="guide1" class="guide">
                        <img src="resources/images/alarm.png" width="40" height="40">
                           <h4>Guide 1</h4>
                           <h2>공지사항</h2>
                           <p>신규 회원을 위한 안내</p>
                           <button>바로 가기</button>
                        </div>
                        <div id="guide2" class="guide">
                        <img src="resources/images/human.png" width="40" height="40">
                           <h4>Guide 2</h4>
                           <h2>개인 운동</h2>
                           <p>home - training 운동법 소개</p>
                           <button>바로 가기</button>
                        
                        </div>
                        <div id="guide3" class="guide">
                        <img src="resources/images/group.png" width="40" height="40">
                            <h4>Guide 3</h4>
                           <h2>그룹 운동</h2>
                           <p>함께 운동하고싶은 사람 모집</p>
                           <button>바로 가기</button>
                        
                        </div>
                        <div id="guide4" class="guide">
                        <img src="resources/images/phone.png" width="40" height="40">
                           <h4>Guide 4</h4>
                           <h2>상담 문의</h2>
                           <p>1:1 유선 문의, QnA 문의</p>
                           <button>바로 가기</button>
                        </div>
                </div>
                

           
 </section>

<jsp:include page="/views/common/footer.jsp" />