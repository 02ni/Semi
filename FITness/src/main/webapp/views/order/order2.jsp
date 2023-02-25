<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="path" value="${ pageContext.request.contextPath }"/>
<jsp:include page="/views/common/header.jsp"/>

<link rel="stylesheet" href="${ path }/resources/css/order2.css">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	 <div class="wrap">
<!--         <div id="header"> -->

<!--             <div id="header1">   -->
            
<!--             </div> -->

<!--             <div id="header2" align="center" > -->
<!--                 <h1 class="logo" align="center">FITness</h1> -->
<!--                 <img src="resources/images/logotext.png" height="100px" align="center"> -->
<!--             </div> -->

<!--             <div id="header3"> -->
<!--                 <a href="#" class="mainlogin">로그인</a> -->
<!--                 <a href="#" class="mainlogin">회원가입</a>  -->
<!--             </div> -->

<!--         </div> -->
<!--         <div id="navigator"> -->
<!--             <ul id="navi"> -->
<!--                 <li><a href="#">Gym찾기</a></li> -->
<!--                 <li> -->
<!--                     <a href="#">게시판</a> -->
<!--                     <ul> -->
<!--                         <li><a href="#">일행구하기</a></li> -->
<!--                         <li><a href="#">자유게시판</a></li> -->
<!--                         <li><a href="#">질문하기</a></li> -->
<!--                     </ul> -->
<!--                 </li> -->
<!--                 <li> -->
<!--                     <a href="#">마이페이지</a> -->
<!--                     <ul> -->
<!--                         <li><a href="#">정보수정</a></li> -->
<!--                         <li><a href="#">장바구니</a></li> -->
<!--                         <li><a href="#">구매내역</a></li> -->
<!--                     </ul> -->
<!--                 </li> -->
<!--                 <li><a href="#">QnA</a></li> -->
<!--             </ul> -->
<!--         </div> -->
        
        <h2>구매 정보 확인</h2>
        <form action="${ path }/order1/save" method="POST">
        <div id="section">
            <div id="section1">
                <div id="section1-1">
                    <img src="${ path }/resources/images/gymimg/${ gym.img }"height="80%" width="85%" align="center">
                </div>
                <div id="section1-2">
                    <h3 style="color:steelblue; font-size: 20px;">${ gym.category }</h3>
                    <ul>
                        <li font-weight="large">${ gym.gymName }</li>
                        <li>주소: ${ gym.address } </li>
                        <li>전화 : ${ gym.gymPhone }</li> 
                    </ul>
                    <table  id="opt_vou">
                        <tr>
                            <th style="width: 100px; text-align: left; font-size: 18px; color: coral;" >옵션</th>
                            <td>${ option.optName }</td>
                        </tr>
                        <tr>
                            <th style="width: 100px; text-align: left; font-size: 18px; color: coral;">이용권</th>
                            <td>${ voucher.voucherNo }개월 </td>
                        </tr>
                    </table>
                    
                </div>
            </div>
<!--             <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br> -->
            
            <hr>
            
            <div id="section2">
                <h2>구매자 정보</h2>
                <br><br>
                <table id="b_info">
                <tr>
                    <th id="th1">이름</th>
                    <td><input type="text" value="${loginMember.name}" name="name"></td>
                    <th id="th1">주소</th>
                    <td><input type="text" value="${loginMember.address}" name="address"></td>
                </tr>
                <tr>
                    <th id="th1">전화번호</th>
                    <td><input type="text" value="${loginMember.phone}" name="phone"></td>
                    <th id="th1">시작일자</th>
                    <td>
						<input type="date" value="setdate" name="sDate">
                    </td>
                </tr>
                <tr>
                    <th id="th1">이메일</th>
                    <td><input type="text" value="${loginMember.email}" name="email"></td>
                    <th id="th1">종료일자</th>
                    <td>
                    	<input type="date" value="${voucher.voucherNo + setdate} " name="eDate">
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
<!--                     <th id="th1">종료일자</th> -->
<!--                     <td><input type="text"></td> -->
                </tr>
            </table>
            </div>
            
            <script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
    <script>
    function checkLeapYear(year) {
        if(year%400 == 0) {
            return true;
        }else if(year%100 == 0) {
            return false;
        }else if(year%4 == 0) {
            return  true;
        }else {
            return false;
        }
    }

    function getFirstDayOfWeek(year, month) {
        if(month < 10) month = "0" + month;

        return(new Date(year+"-"+month+"-01")).getDay();
    }

    function changeYearMonth(year, month) {
        let month_day = [31,28,31,30,31,30,31,31,30,31,30,31]

        if(month == 2) {
            if(checkLeapYear(year)) month_day[1]=29;
        }

        let first_day_of_week = getFirstDayOfWeek(year, month);
        let arr_calendar = [];
        for(let i=0; i<first_day_of_week; i++){
            arr_calendar.push("");
        }

    for(let i=1; i<=month_day[month-1]; i++) {
        arr_calendar.push(String(i))
    }

    let remain_day = 7 - (arr_calendar.length%7);
    if(remain_day<7) {
        for(let i=0; i<remain_day; i++) {
            arr_calendar.push("");
        }
    }
    renderCalender(arr_calendar);

}
    function renderCalender(data) {
        let h = [];
        for(let i =0; i<data.length; i++) {
            if(i == 0){
                h.push('<tr>');
            }else if(i % 7 == 0) {
                h.push('</tr>');
                h.push('<tr>');
            }

            h.push('<td onclick="setDate(' + data[i] + ');" style="cursor:pointer;">' + data[i] + '</th>');
        }

        h.push('</tr>');

        $("#tb_body").html(h.join(""));
    }

    function setDate(day) {
        if(day<10) day = "0" + day;
        $("#input_date").val(current_year + "-" + current_month + "-" + day); 
        $("#div_calendr").hide();
        
    }

    function changeMonth(diff) {
        if(diff == undefined) {
            current_month = parseInt($("#month").val());
        }else {
            current_month = current_month + diff;

            if(current_month == 0) {
                current_year = current_year -1;
                current_month = 12;
            }else if(current_month == 13) {
                current_year = current_year + 1;
                current_month = 1;
            }
        }

        loadCalendar();
        
    }

    function loadCalendar() {
        $("#year").val(current_year);
        $("#month").val(current_month);
        changeYearMonth(current_year,current_month);
    }

    let current_year = (new Date()).getFullYear();
    let current_month = (new Date()).getMonth() + 1;

    $("#year").val(current_year);
    $("#month").val(current_month);

    changeYearMonth(current_year,current_month);


    $("#input_date").click(function(){
        $("#div_calendar").toggle(500);
    });
    </script>
    
    <script>
    	function plusMonth(setDate, voucher) {
    		if(${ voucher.voucherNo } === 3) {
    			$("#month").val(set_month) + 3;
    		} else if(${ voucher.voucherNo } === 6){
    			$("#month").val(set_month) + 6;

    		}
    	}
    </script>
    
    
            <br><br>
            <hr>
            <div id="section3">
                <h2>결제 수단</h2>
                <fieldset>
                    <table>
                        <tr>
                            <td><input type="radio" name="pay" id="card" value="카드 결제"  style="width:30px;height:30px;border:1px; cursor:pointer;"></td>
                            <td  id="p_metho"style="font-size: 25px;">카드 결제</td>
                        </tr>
                        <tr>
                            <td><input type="radio" name="pay" id="mutong" value="무통장입금"  style="width:30px;height:30px;border:1px; cursor:pointer;"></td>
                            <td id="p_metho"  style="font-size: 25px;" >무통장 입금</td>
                        </tr>
                        <tr>
                            <td><input type="radio" name="pay" id="zig" value="직접 결제"  style="width:30px;height:30px;border:1px; cursor:pointer;"></td>
                            <td id="p_metho" style="font-size: 25px;" >직접 결제</td>
                        </tr>
                    </table>
                </fieldset>
            </div>
          

            <br><br>
            <hr>
            <div class="checkbox_group">
                <h2>약관 동의</h2>
                <input type="checkbox" id="check_all" >
  				<label for="check_all">전체 동의</label>
                
                <div id="area1">
                    취소 및 환불 규정<br><br>
                    1. 구매하신 회원권의 취소는 결제일로부터 7일 이내, 운동시작일 전일 경우에만 가능합니다. <br>
                    2. 결제일로부터 8일 이후부터는 운동시작일 이전이라 하더라도 위약금 10% 공제 후 환불됩니다. <br>
                    3. 운동시작일 이후 환불시에는 위약금 10%와 이용한 기간을 일할 계산하여 공제 후 환불됩니다. <br>
                    4. 운동시설의 폐업, 도산, 이전 등 불가피한 사정으로 인한 환불 요청에 대해서는 별도의 위약금 공제 <br>
                    없이 이용한 기간 만큼만 계산하여 차감 후 환불하면, 원하지 않으시는 경우 주변의 유사한 시설을 이용할 수 있도록 조치해드립니다. <br>
                    5. 상황에 따른 보다 자세한 환불 규정은 고객센터로 문의하시기 바랍니다.
                </div>
<!--             	<input type="checkbox" id="check_1" class="normal" > -->
<!--   				<label for="check_1">개인정보 처리방침 동의</label> -->
                <div><input type="checkbox" name="cancel" id="cancel"><strong> [필수]</strong><u style="color: lightskyblue;">취소 및 환불규정</u>에 동의합니다.</div>
                <!-- <br> -->
                <div id="area2">
                    회사는 회원님께서 결제하신 상품의 원할한 제공을 위하여 최소한의 범위 내에서 아래외 같이 제 3자에게 정보를 제공합니다. <br>

                - 개인정보를 제공받는 자: 회원이 서비스를 이용하고자 결제한 각 제휴시설 <br>
                - 제공 항목: 성명, 주소, 연락처, 성별, 운동 시작일<br>
                - 이용목적: 제휴시설 이용 및 회원 관리 목적<br>
                - 제공기간: 목적에 따른 개인정보 제공 시부터 제공 목적 달성 시까지<br>
                - 이용 목적: 결제 서비스 처리, 요금 납부, 제휴 서비스 목적<br>
                - 제공 기간: 목적에 따른 개인정보 제공 시부터 제공 목적 달성 시까지<br>

                회원님은 개인정보 제공에 동의하지 않으실 수 있으며, 동의하지 않으실 경우 결제서비스 등의 이용이 제한 될 수 있습니다.
                </div>
                <div><input type="checkbox" name="trusr" id="check_2"><strong> [필수]</strong><u style="color: lightskyblue;">개인정보 제 3자 위탁</u>에 동의합니다.</div>
<!--                 <input type="checkbox" id="check_2" class="normal" > -->
<!--   				<label for="check_2">서비스 이용약관 동의</label> -->
  
<!--                 <br>  -->
            </div>
<!--             <div class="checkbox_group"> -->
            <script>
            $(".checkbox_group").on("click", "#check_all", function () {
            	  var checked = $(this).is(":checked");

            	  if(checked){
            	  	$(this).parents(".checkbox_group").find('input').prop("checked", true);
            	  } else {
            	  	$(this).parents(".checkbox_group").find('input').prop("checked", false);
            	  }
            	});
            </script>

<!--   <input type="checkbox" id="check_all" > -->
<!--   <label for="check_all">전체 동의</label> -->
  
<!--   <input type="checkbox" id="check_1" class="normal" > -->
<!--   <label for="check_1">개인정보 처리방침 동의</label> -->
  
<!--   <input type="checkbox" id="check_2" class="normal" > -->
<!--   <label for="check_2">서비스 이용약관 동의</label> -->
  
<!--   <input type="checkbox" id="check_3" class="normal" > -->
<!--   <label for="check_3">마케팅 수신 동의</label> -->
  
</div>
            <hr>
            <div id="section5">
                <h2>결제 금액</h2>
                <div style="border: 1px solid;">
                    <table id="price" >
                        <tr>
                            <td id="voucher_p">이용권 금액</td>
                            <td id="v_price">${ voucher.price }</td>
                        </tr>
                        <tr>
                            <td id="option_p">옵션 금액</td>
                            <td id="o_price">${ option.optPrice }</td>
                        </tr>
                        <tr>
                            <td id="discount_p">할인 금액</td>
                            <td id="d_price">-10,000</td>
                        </tr>
                        <tr>
                            <td id="total_p">총 금액</td>
                            <td id="t_price">${ voucher.price + option.optPrice - 10000} </td>
                        </tr>
                    </table>
                </div>
            </div>

            <br><br>
            <table id="btn_page">
                <td><input type="button" value=" < 뒤로" id="pre" style="margin-left: 100px;" ></td>
                <td><input type="submit" value="다음 >" id="next" style="margin-left: 100px;"></td>
            </table>

        </div>
        <input type="hidden" name="gymNo" value="${ gymNo }">
<%--         <input type="hidden" name="userNo" value="${ member.no }"> --%>
        <input type="hidden" name="optNo" value="${ option.optNo }">
        <input type="hidden" name="tPrice" value="${ voucher.price + option.optPrice - 10000}"> 
        <input type="hidden" name="voucherNo" value="${ voucher.voucherNo }">
        <input type="hidden" name="payMethod" value="카드 결제">
        </form>
<!--         <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br> -->
<!--         <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br> -->
<!--         <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br> -->
<!--         <br><br><br><br><br><br><br><br><br><br><br> -->
        
       
<!--         <div id="footer" name="foot"> -->

<!--             <div id="footer1"> -->
                
<!--                 <h1 class="logo">FITness</h1> -->
<!--             </div> -->

<!--             <div id="footer2"> -->
<!--                 <p> -->
<!--                     (주) XXXX -->
<!--                     서울시 강남구 테헤란로 -->
<!--                 </p> 50 XXXX호 -->
<!--                 <p> -->
<!--                     대표 : 세미3조 -->
<!--                     사업자 번호 : 123-45-67890 -->
<!--                 </p> -->
<!--                     통신판매번호 : 제0000-서울강남-0000호 -->
<!--                     contact@xxxx.co.kr     -->
<!--             </div> -->

<!--             <div id="footer3"> -->
<!--                 <p> -->
<!--                     이용약관 -->
<!--                 </p> -->
<!--                 <p> -->
<!--                     개인정보 처리방침 -->
<!--                 </p> -->
<!--                 <p> -->
<!--                     센터등록 요청하기 -->
<!--                 </p> -->
<!--                 <p> -->
<!--                     문의사항  -->
<!--                 </p> -->
<!--             </div> -->
<!--             <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br> -->
	
<!--             <p align="center" style="font-size: small;">Copyright © semi 3. All rights reserved.</p> -->
<!--             <br> -->
<!--         </div>      -->
<!--     </div> -->
<%-- <jsp:include page="/views/common/footer.jsp" /> --%>
</body>
</html>