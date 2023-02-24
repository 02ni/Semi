<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${ pageContext.request.contextPath }"/>

<jsp:include page="/views/common/header.jsp"/>

<style>
  .answer {
    display: none;
    padding-bottom: 30px;
  }
  #faq-title {
    font-size: 25px;
  }
  .faq-content {
    border-bottom: 1px solid #e0e0e0;
  }
  .question {
    font-size: 19px;
    padding: 30px 0;
    cursor: pointer;
    border: none;
    outline: none;
    background: none;
    width: 100%;
    text-align: left;
  }
  .question:hover {
    color: #2962ff;
  }
  [id$="-toggle"] {
    margin-right: 15px;
  }
  
	/*바*/
        #contentguide{
            background-color: white;
            margin: 10px 0 30px 0;
        }
        #divboardguide{
            width:  940px;
            display: flex;
            margin: 5px auto;
            height: 70px;
        }
        .boardguide{
            border-radius: 3px;
            width: 30%;
            height: 60px;  
            text-align : center;
            padding : 20px 0;
            
            margin: 15px;
            text-align: center;
            justify-content: center;
            cursor: pointer;
            border: 1px solid #E3E3E3;
        	font-size: 18px;
        }
        
        .boardguide:active{
			color: black;
			background-color: orange;
		}
		
		.boardguide.color{
			color: black;
			background-color: orange;
		}
		
        #boardguide1{
        }
        #boardguide2{
        }
        #boardguide3{
        
        }
        #boardguide4{
        }
        .icon{
            margin: 20px 10px 10px 10px;
        }
        .boardguide  p {
            font-size: 10pt;
            font-weight:bold;
            color: rgb(79, 79, 79);

        }	
	
	
</style>

<link rel="stylesheet" href="${ path }/resources/css/board.css">

            <section id="contentguide">
                <div id="divboardguide">

                    <div class="boardguide" id="boardguide1" onclick="location.href='${ path }/notice/list'">
                        공지사항
                    </div>
                            
                    <div class="boardguide color" id="boardguide2"  onclick="location.href='${ path }/faq/list'">
                        자주묻는질문
                    </div>

                    <div class="boardguide" id="boardguide3" onclick="location.href='${ path }/board/list'">
                        자유게시판
                    </div>

                    <div class="boardguide" id="boardguide4" onclick="location.href='${ path }/qna/list?no=${ loginMember.no }'">
                        1:1 문의
                    </div>
                </div>
            </section>

		
	<div class="clear"></div>
	
	<hr>

	<h2></h2>
	
	<div id="board-list-container">
		
		<span id="faq-title">자주 묻는 질문(FAQ)</span>
		<div class="faq-content">
			<button class="question" id="que-1"><span id="que-1-toggle">▶</span><span>'관리자 문의'는 어떻게 하나요?</span></button>
			<div class="answer" id="ans-1">궁금하신 점은 언제든지 FITness 1:1 문의 게시판을 통해 문의할 수 있습니다. <br> 로그인 후 문의 부탁드립니다. </div>
		</div>
		
		<div class="faq-content">
			<button class="question" id="que-2"><span id="que-2-toggle">▶</span><span>'내 지역 운동시설 찾기'란 무엇인가요?</span></button>
			<div class="answer" id="ans-2">Gym 찾기 검색을 통해 내 주변 운동 시설 찾아보세요! <br> 검색한 Gym의 카테고리를 한 눈에 볼 수 있습니다.</div>
		</div>
		
		<div class="faq-content">
		<button class="question" id="que-3"><span id="que-3-toggle">▶</span><span>'이용권 할인'이란 무엇인가요?</span></button>
			<div class="answer" id="ans-3">Gym 선택 후 결제 시, 이용권 개월 수에 따라 할인 받을 수 있습니다.</div>
		</div>

		<script>
		  const items = document.querySelectorAll('.question');
		
		  function openCloseAnswer() {
		    const answerId = this.id.replace('que', 'ans');
		
		    if(document.getElementById(answerId).style.display === 'block') {
		      document.getElementById(answerId).style.display = 'none';
		      document.getElementById(this.id + '-toggle').textContent = '▶';
		    } else {
		      document.getElementById(answerId).style.display = 'block';
		      document.getElementById(this.id + '-toggle').textContent = '▼';
		    }
		  }
		
		  items.forEach(item => item.addEventListener('click', openCloseAnswer));
		</script>
		
		
		
		
		
		
		
	</div>
</section>

<jsp:include page="/views/common/footer.jsp" />