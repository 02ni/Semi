<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/views/common/header.jsp" />
  <div id="section">

            <section id="banner1">
                <img src="resources/images/mainbanner.png" id="bannerimg1">
            </section>

            <section id="contentguide">
                <div id="divguide">

                    <div id="guide1" class="guide">
                        <img src="resources/images/alarm.png" width="40" height="40" class="icon">
                        <h4>Guide 1</h4>
                        <h2>공지사항</h2>
                        <p>신규 회원을 위한 안내</p>
                        <button id="mbtn1" onclick="location.href='/FITness/notice/list'">바로 가기</button>
                    </div>
                            
                    <div id="guide2" class="guide">
                        <img src="resources/images/human.png" width="40" height="40" class="icon">
                        <h4>Guide 2</h4>
                        <h2>개인운동</h2>
                        <p>홈트레이닝 운동법 소개</p>
                        <button id="mbtn2">바로 가기</button>
                    </div>

                    <div id="guide3" class="guide">
                        <img src="resources/images/group.png" width="40" height="40" class="icon">
                        <h4>Guide 3</h4>
                        <h2>그룹운동</h2>
                        <p>함께 운동하고싶은 사람 모집</p>
                        <button id="mbtn3" onclick="location.href='/FITness/board/list'">바로 가기</button>
                    </div>

                    <div id="guide4" class="guide">
                        <img src="resources/images/phone.png" width="40" height="40" class="icon">
                        <h4>Guide 4</h4>
                        <h2>상담문의</h2>
                        <p>1:1 문의, QnA 문의</p>
                        <button id="mbtn4" onclick="location.href='/FITness/qna/list'">바로 가기</button>
                    </div>
                </div>
            </section>

            <section id="banner2">
                <img src="resources/images/bannerimg.png" id="bannerimg2">
            </section>
        </div>

<script>
   $('#mbtn2').on('click', () => {
      alert("실패지점까지 열심히 하도록 합니다.");
   });
</script>
<jsp:include page="/views/common/footer.jsp" />