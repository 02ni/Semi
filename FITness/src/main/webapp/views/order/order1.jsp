<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="path" value="${ pageContext.request.contextPath }"/>

<jsp:include page="/views/common/header.jsp"/>

<link rel="stylesheet" href="${ path }/resources/css/order1.css">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>order1</title>
</head>
<body>
		<div class="wrap">

        <br><br>
<%--         <form name="order1" id="order1" action="${ path }/order1/option" method="GET"> --%>
        <form name="order2" id="order2" action="${ path }/order1/voucher" method="GET">
        <div id="section">
            <div id="section1">
                <div id="section1-1">
                    <img src="${ path }/resources/images/gymimg/${ gym.img }"height="100%" width="90%" align="center">
                </div>
                <div id="section1-2">
                    <h3 style="color:steelblue; font-size: 20px;">${ gym.category }</h3>
                    <ul>
                        <li font-weight="large">${ gym.gymName }</li>
                        <li>주소: ${ gym.address }</li>
                        <li>전화 : ${ gym.gymPhone }</li> 
                    </ul>
                    <table>
                        <tr>
                            <td id="option-1" style="width: 150px; height: 40px; color: coral; font-size: large;" >옵션 선택</td>
                            <!-- <td style="font-size: larger; width: 100px; ">3개월</td> -->
                        </tr>
               <form name="order2" id="order2" action="${ path }/order1/voucher" method="GET">
               
              
                        <tr>
                            <td>
                            
                                <select id="option1" name="option" style="width: 150px; height: 40px; cursor: pointer;" >
                                    <option value="선택안 함"  name="option" checked>옵션 선택</option>
                                    <option value="1" name="option" id="locker">락카</option>
                                    <option value="2" name="option" id="cloth">헬스복</option>
                                </select>
                            </td>
                        </tr>	
                    </table>
                </div>
            </div>
            
           
            <hr>
            <div id="section2">
                <h3 style="font-family: 'GmarketSansMedium';">기간 선택</h3>
                <table  id="voucher1" name="voucher">
                    <td><input type="radio" value="3"  id="month1"name="voucher"></td>
                    <td><input type="radio" value="6" id="month2"name="voucher"></td>
                    <td><input type="radio" value="9"  id="month3"name="voucher"></td>
                    <td><input type="radio" value="12" id="month4"name="voucher"></td>
                </table>
              
            </div>
            
            
            <div id="section2-1">
                <table id="months">
                    <td id="m1" value="${ voucher.price3 }">3개월</td>
                    <td id="m2" value="${ voucher.price6 }">6개월</td>
                    <td id="m3" value="${ voucher.price9 }">9개월</td>
                    <td id="m4" value="${ voucher.price12 }">12개월</td>
                </table>
            </div>
            <br><br><br><br><br>
            <hr>
            <div id="section3" >
<!--                 <h3 style="font-family: 'GmarketSansMedium';">예상 금액</h3> -->

<!--                 <div style="border: 1px solid;"> -->
<!--                     <table id="price" > -->
<!--                         <tr> -->
<!--                             <td id="voucher_p">이용권 금액</td> -->
<%--                             <td id="v_price">${ voucher.price }</td> --%>
<!--                         </tr> -->
<!--                         <tr> -->
<!--                             <td id="option_p">옵션 금액</td> -->
<%--                             <td id="o_price">${ option.optPrice }</td> --%>
<!--                         </tr> -->
<!--                         <tr> -->
<!--                             <td id="discount_p">할인 금액</td> -->
<!--                             <td id="d_price">-10,000</td> -->
<!--                         </tr> -->
<!--                         <tr> -->
<!--                             <td id="total_p">총 금액</td> -->
<%--                             <td id="t_price">${ voucher.price + option.optPrice - 10000} </td> --%>
<!--                         </tr> -->
<!--                     </table> -->
						<img src="${ path }/resources/images/bannerimg.png"height="100%" width="100%" align="center">
                </div>
	<input type="hidden" name="gymNo" value="${ gym.no }">
       	 <table id="btn_page">
	        <td><button type=button" style="margin-left: 100px;" id="pre"><a href="${ path }/views/gym/detail.jsp">< 뒤로</a></button></td>
<%-- 	        <button id="login_join" ><a href="${ path }/member/join">회원 가입 </a> </button> --%>
	        <td><button type="submit" id="next" style="background-color: background-color:coral; width: 15ch; height: 5ch; border-radius: 10px;">다음 ></button></td>
         </table>
        </div>
    </form>
        

    <jsp:include page="/views/common/footer.jsp" />
</body>
</html>