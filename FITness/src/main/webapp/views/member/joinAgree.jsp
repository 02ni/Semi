<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<c:set var="path" value="${ pageContext.request.contextPath }"/>

<jsp:include page="/views/common/header.jsp"/>

<link rel="stylesheet" href="${ path }/resources/css/join.css">
	<style>
	 *{margin: 0px;padding: 0;box-sizing: border-box;}
ul>li{list-style: none}
a{text-decoration: none;}
.clearfix::after{content: "";display: block;clear: both;}
#joinForm{width: 600px;margin: 0 auto;}
ul.join_box{border: 1px solid #ddd;background-color: #fff;}
.checkBox,.checkBox>ul{position: relative;}
.checkBox>ul>li{float: left;}
.checkBox>ul>li:first-child{width: 85%;padding: 15px;font-weight: 600;color: #888;}
.checkBox>ul>li:nth-child(2){position: absolute;top: 50%;right: 30px;margin-top: -12px;}
.checkBox textarea{width: 96%;height: 150px; margin: 0 2%;background-color: #f7f7f7;color: #888; border: none;}
.join_box {
    height: 800px;
}

#btn {
    text-align: center;
}



#btn_join {
            height: 55px;
            padding: 10px;
            box-sizing: border-box;
            width: 200px;
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

  <div id=section>
    <form action="${ path }/member/join" id="joinForm" method="GET" onsubmit="return joinForm_submit(this);">
        <ul class="join_box">
            <h1 align="center">약관 동의</h1>
            <br><br>
            <li class="checkBox check01">
                <ul class="clearfix">
                    <li>이용약관, 개인정보 수집 및 이용,
                        위치정보 이용약관(선택), 프로모션 안내
                     메일 수신(선택)에 모두 동의합니다.</li>
                    <li class="checkAllBtn">
                        <input type="checkbox" name="agree_all" id="agree_all" class="chkAll">
                    </li>
                </ul>
            </li>
            <li class="checkBox check02">
                <ul class="clearfix">
                    <li>이용약관 동의(필수)</li>
                    <li class="checkBtn">
                        <input type="checkbox" name="agree" id="agree1"> 
                    </li>
                </ul>
                <textarea name="" id="">FITness

[1장 총칙]

① "FITness" 이라 함은 서울,경기에 있는 피트니스 센터에 대한 다양한 정보를 이용고객에게 제공하기 위하여 컴퓨터 등 정보통신설비를 이용하여 재화, 용역을 거래할 수 있도록 회사가 운영하는 웹사이트, APP 플랫폼을 말하며, 아울러”FITness”을 운영하는 사업자의 의미로도 사용합니다.
② "서비스" 라 함은, 구현되는 단말기(PC, 태블릿, 휴대용 단말기 등의 각종 유무선 장치)와 상관없이 브랜드명 “FITness”을 사용하여 “회사”가 제공하는 제반 서비스를 말합니다.
③ "이용고객" 이라 함은, 회사의 "서비스"에 접속하여 본 약관에 따라 회사가 제공하는 서비스를 받는 일반 회원과 사업자 회원, 비회원을 말합니다.
   </textarea>
            </li>
            <li class="checkBox check03">
                <ul class="clearfix">
                    <li>개인정보 수집 및 이용에 대한 안내(필수)</li>
                    <li class="checkBtn">
                        <input type="checkbox" name="agree" id="agree2">
                    </li>
                </ul>

                <textarea name="" id="">FITness 개인정보취급방침
본사(이하 회사)가 제공하는 본 홈페이지의 서비스(이하 ‘서비스)는 인터넷 사용자들의 기본권인 사생활의 비밀과 자유 및 통신의 비밀을 보장하고,개인정보를 철저히 보호하여 풍요로운 인터넷 생활을 누릴 수 있도록 노력하고 있습니다.

회원님께서 제공해 주신 모든 정보는 "신용정보의 이용 및 보호에 관한 법률 제 23조"에 의해 철저히 관리되어야 하는 사항입니다. "서비스"는 불법도청, 통신상의 정보유출 등 헌법에 명시된 기본권의 침해를 원천적으로 막고, 회원정보를 보호하는 것을 제1의 운영원칙으로 삼아 다음과 같이 개인정보 취급방침을 명시합니다. 저희의 개인정보 취급방침은 내용이 수시로 변경될 수 있으므로 자주 사이트를 방문하셔서 숙지하시기 바랍니다
   </textarea>
            </li>
            <li class="checkBox check03">
                <ul class="clearfix">
                    <li>위치정보 이용약관 동의(선택)</li>
                    <li class="checkBtn">
                        <input type="checkbox" name="agree" >
                    </li>
                </ul>

                <textarea name="" id="">제 1 조 (목적)
이 약관은 FITness 주식회사 (이하 “FITness”)가 제공하는 위치기반서비스와 관련하여 회사와 개인위치정보주체와의 권리, 의무 및 책임사항, 기타 필요한 사항을 규정함을 목적으로 합니다.
                    
제 2 조 (약관 외 준칙)
이 약관에 명시되지 않은 사항은 위치정보의 보호 및 이용 등에 관한 법률, 개인정보보호법, 정보통신망 이용촉진 및 정보보호 등에 관한 법률, 전기통신기본법, 전기통신사업법 등 관계법령과 회사의 이용약관 및 개인정보처리방침, 회사가 별도로 정한 지침 등에 의합니다.
                    
제 3 조 (서비스 내용 및 요금)
①회사는 위치정보사업자로부터 위치정보를 전달받아 아래와 같은 위치기반서비스를 제공합니다.
1. 게시물 또는 이용자가 저장하는 콘텐츠에 포함된 개인위치정보주체 또는 이동성 있는 기기의 위치정보가 게시물과 함께 저장됩니다. 저장된 위치정보는 별도의 활용없이 보관되거나, 또는 장소를 기반으로 콘텐츠를 분류하거나 검색할 수 있는 기능이 제공될 수도 있습니다.
2.위치정보를 활용한 검색결과 및 콘텐츠 제공 : 정보 검색을 요청하거나 개인위치정보주체 또는 이동성 있는 기기의 위치정보를 제공 시 본 위치정보를 이용한 검색결과, 주변결과(맛집, 주변업체, 교통수단 등), 번역결과를 제시합니다.
3.이용자 위치를 활용한 광고정보 제공: 검색결과 또는 기타 서비스 이용 과정에서 개인위치정보주체 또는 이동성 있는 기기의 위치를 이용하여 광고소재를 제시합니다.
4.이용자 보호 및 부정 이용 방지: 개인위치정보주체 또는 이동성 있는 기기의 위치를 이용하여 권한없는 자의 비정상적인 서비스 이용 시도 등을 차단합니다.
5.길 안내 등 생활편의 서비스 제공: 교통정보와 길 안내 등 최적의 경로를 지도로 제공하며, 주변 시설물 찾기, 뉴스/날씨 등 생활정보, 긴급구조 서비스, 주소 자동 입력 등 다양한 운전 및 생활 편의 서비스를 제공합니다.
②제1항 위치기반서비스의 이용요금은 무료입니다.
                </textarea>
            </li>
        </ul>
        <div id="btn">
        <input id="btn_join" onclick="location.href='${ path }'" value="비동의">
        <input type="submit" class="next-button" id="btn_join" value="동의">
        </div>
    </form>
</div>

<script>

// 약관동의 전체 체크 헤제/ 전체 체크 설정
const agreeChkAll = document.querySelector('input[name=agree_all]');
agreeChkAll.addEventListener('change', (e) => {
let agreeChk = document.querySelectorAll('input[name=agree]');
for(let i = 0; i < agreeChk.length; i++){
  agreeChk[i].checked = e.target.checked;
}
});

// 필수항목 체크 검사
function joinForm_submit(f) {

	if(document.getElementById('agree1').checked === false) {
	    alert("필수항목을 체크해 주세요.");
	    return false;
	}
	if(document.getElementById('agree2').checked === false) {
	    alert("필수항목을 체크해 주세요");
	    return false;
	}
	return true;
	}
</script>


<jsp:include page="/views/common/footer.jsp" />