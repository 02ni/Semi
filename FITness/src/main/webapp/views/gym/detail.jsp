<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${ pageContext.request.contextPath }"/>
<jsp:include page="/views/common/header.jsp" />
<style>
	#detailmain{
	font-size: 20px;
	margin: 50px 0 0 0;
	}
	#gymtitle{
    height: 100px;
    font-size: 20px;
	}
	#gymcon{
	    height: 500px;
	}
	#gymleft{
	width: 50%;
	height: 100%;
	float: left;
	padding: 10px 0px 30px 0;
	}
	#gymright{
	width: 50%;
	height: 100%;
	float: left;
	}
	#gymbar{
		border: 6px solid rgb(255,151,0, 0.5);
	    height: 80px;
	    margin: 10px 0px 10px 0;
	}
	#gymmenu{
	    list-style-type: none;
	            margin:0;
	            padding:0;
	            height:100%;
	            position: relative;
	}
	#gymmenu> li{
	            width:33%;	
	            text-align:center;
	            float:left;
	            line-height:-10px;
	        }
	
	
	#detail1{
		
	    height: 300px;
	}
	/* 가격테이블 */
	#detail2{
	    height: 100%;
	    margin: 10px 0px 10px 0;
	}
	
	/* 운영시간 */
	#detail3{
	    height: 100%;
	    }
	
	#detail4{
	    height: 200px;
	    margin: 10px 0px 10px 0;
	}
	
	#detail5{
	    height: 500px;
	}
	#btn-close{
	    float: right;
	}
	
	#buydiv {
	    width: 100%;
	
	}
	/* 리뷰테이블 */
	#comment-container{
	    text-align: center;
	    width: 100%;
	}
	#btn-insert{
	    background: rgb(255,151,0);
		border: none;
		color: white;
		height: 40px;
		width: 80px;
	}
	#riviewtext{
		width: 900px;
	}
	#grade{
	    width: 450px;
	}
	.detail{
		border: 6px solid rgb(255,151,0, 0.5);
		padding : 5px 30px;
	}
	a {
	text-decoration: none;
	color: black;
	}
	table.vouchertbl{width:100%; margin:0 auto; border: 1px solid gray; border-collapse:collapse; clear:both;}
	table.vouchertbl tr>th {border: 0.3px solid grey; padding: 5px 0; text-align:center; background-color: rgb(250,210,100);} 
	table.vouchertbl th, table.vouchertbl td {border: 0.3px solid grey; padding: 5px 0; text-align:center;} 
	
	table#tbl-comment{ 
		border-top: 1px solid gray;
		 width:800px; 
		 margin:20px auto; 
		 border-collapse:collapse; 
		 clear:both; } 
		       
    table#tbl-comment tr{background:white;}
    table#tbl-comment input.btn-delete{display:none;
		 background: rgb(255,151,0);}   
    table#tbl-comment button.btn-update{display:none;
		 background: rgb(255,151,0);}         
    table#tbl-comment tr:hover {background:lightgray;}
    table#tbl-comment tr:hover input.btn-delete{display:inline;}
    table#tbl-comment tr:hover button.btn-update{display:inline;}
    table#tbl-comment tr td:last-of-type {text-align:right; width: 100px;}
    table#tbl-comment sub.review-writer {color:navy; font-size:14px; padding: 5px;}
    table#tbl-comment p.review-content { padding: 5px;}
    table#tbl-comment p.review-good { padding: 5px;}
    table#tbl-comment p.review-star { padding: 0px; margin: 0px;}
	
.dbtn {
    margin: 10%;
    text-align: center;
}

.dbtn {
    width: 190px;
    font-size: 16px;
    font-weight: 600;
    color: #fff;
    cursor: pointer;
    margin: 20px;
    height: 55px;
    text-align:center;
    border: none;
    background-size: 300% 100%;

    border-radius: 50px;
    moz-transition: all .4s ease-in-out;
    -o-transition: all .4s ease-in-out;
    -webkit-transition: all .4s ease-in-out;
    transition: all .4s ease-in-out;
}

.dbtn:hover {
    background-position: 100% 0;
    moz-transition: all .4s ease-in-out;
    -o-transition: all .4s ease-in-out;
    -webkit-transition: all .4s ease-in-out;
    transition: all .4s ease-in-out;
}

.dbtn:focus {
    outline: none;
}

#cart-btn, #cart-btn2 {
    background-image: linear-gradient(to right, #25aae1, #40e495, #30dd8a, #2bb673);
    box-shadow: 0 4px 15px 0 rgba(49, 196, 190, 0.75);
}
#buy-btn, #buy-btn2 {
    background-image: linear-gradient(to right, #f5ce62, #e43603, #fa7199, #e85a19);
    box-shadow: 0 4px 15px 0 rgba(229, 66, 10, 0.75);
}
#like-btn2 {	
 background-color: #e74c3c;
  width: 100px;
  font-weight: 600;
  text-align: center;
  height: 50px;
  color: #FFF;
  font-size: 15pt;
  border-radius: 5px;
   margin: 0px;
   padding: 0px;
   border: none;
   
}
form {
}
</style>
			
<!-- Main -->
	<section id="detailmain">
            <div id="gymtitle">
            
                <h1>${ gym.gymName }<br>
                	<span style="color: rgba(236, 202, 4, 0.815)"><c:choose>
						<c:when test="${grade > 4.5}">★★★★★</c:when>
						<c:when test="${grade <= 4.5 && grade > 3.5}">★★★★☆</c:when>
						<c:when test="${grade <= 3.5 && grade > 2.5}">★★★☆☆</c:when>
						<c:when test="${grade <= 2.5 && grade > 1.5}">★★☆☆☆</c:when>
						<c:when test="${grade <= 1.5 && grade > 0}">★☆☆☆☆</c:when>
						<c:when test="${grade == 0}">☆☆☆☆☆</c:when>
						<c:otherwise> - </c:otherwise>
					</c:choose></span>
					<span style="font-size:15px; color:gray;">${ grade }</span>
				</h1>
            </div>

            <div id="gymcon">
                <div id="gymleft">
                
                <img src="${ path }/resources/images/gymimg/${ gym.img }" height="100%" width="90%" align="center">
                
                </div>
                <div id="gymright">
                	<c:forTokens var="category" items="${ gym.category }" delims="," varStatus="num">
    					<span style="color:red">${ category }&nbsp;&nbsp;&nbsp;</span>
                        <c:if test="${num.count==6}"><br></c:if>                                    		
					</c:forTokens>
					
                    <p>${ gym.address }</p>
                    <p>전화 ${ gym.gymPhone }</p>
                    <p>좋아요 
                   <c:if test="${ (empty loginMember) or (favorite == 0) }">
	                        <form action="${ path }/favorite/update" method="POST" class="like-form">
									&nbsp;&nbsp;<button type="submit"  id="like-btn2" >♡</button><span style="color:red">  (${ favoriteCount })</span>	
				    				<input type="hidden" name="gymNo" value="${ gym.no }">
				    		</form>
                        </c:if>
                        
                        <c:if test="${ (not empty loginMember) and (favorite != 0) }">
	                        <form action="${ path }/favorite/delete" method="POST" class="like-form">
								&nbsp;&nbsp;<button type="submit"  id="like-btn2">♥</button>	    			
	                      		<input type="hidden" name="gymNo" value="${ gym.no }"><span style="color:red">  (${ favoriteCount })</span>	
				    		</form>
			    		</c:if></p>
                    <form name="buy" id="buy"  method="GET" >
                    		
                    	<!-- 	<span>이용권</span> -->
                          		<label for="opselect">이용권</label> <c:if test="${not empty gym.vouchers}" >    
                             			<select name="voucher" id="opselect" onchange="create_Tag();" style="width:400px; height: 30px;">
	                                 <c:forEach var="opvoucher" items="${ gym.vouchers }">
		                                <option value="${ opvoucher.voucherNo }">${ opvoucher.cate }</option>
                                	</c:forEach>
                           		</select>
                           		</c:if>
                           		<c:if test="${empty gym.vouchers}" > 
                           			<p>업데이트 중입니다.</p>
                           		</c:if>
                                	
                                <%-- 	<c:if test="${not empty gym.vouchers}" >    
                                	<c:forEach var="opvoucher" items="${ gym.vouchers }" varStatus="status">
                                		<c:if test="${ status.count==1 }">
                                			<label><input type="checkbox" name="voucher" value="${ opvoucher.voucherNo }" checked>${ opvoucher.cate }</label>
                                		</c:if>
                                		<c:if test="${ status.count!=1 }">
                                			<label><input type="checkbox" name="voucher" value="${ opvoucher.voucherNo }">${ opvoucher.cate }</label>
                                		</c:if>
                                	
                                	</c:forEach>
                                	
                                </c:if> --%>
                            
                            
                            
                            <!-- <input type="hidden" name="size" value="big">
                            <div id="buydiv">
                           	상품#1. {}
                            <button id="btn-close" type="button" >X</button>
                            </div> -->
                            <input type="hidden" name="gymno" value="${ gym.no }">
                            <br> <br>
                           <c:if test="${ not empty loginMember}">
                            <input type="submit" class="dbtn" id="cart-btn" value="장바구니" formaction="${ path }/bucket/insert">
                            <input type="submit" class="dbtn" id="buy-btn" value="바로구매" formaction="${ path }/order/order1">
 							</c:if>
                     </form>
                    <br><c:if test="${ empty loginMember}">
                            <button class="dbtn" id="cart-btn2" >장바구니</button>
                            <button class="dbtn" id="buy-btn2" >바로구매</button>
 							
                           </c:if>
                     	
                </div>
            </div>

            <div id="gymbar">
                <ul id="gymmenu">
                  <li><h2><a href="#detail1" style="line-height: 0px">이용권 정보</a></h2></li>
                  <li> <h2><a href="#detail4">위치</a></h2></li>
                  <li><h2><a href="#detail5">리뷰(${ reviewCount })</a></h2></li>
                </ul>
            </div>
            

            <div id="detail1" class="detail">
                <h1>소개</h1>
                <p>${ gym.content }</p>
            </div>

            <div id="detail2" class="detail">
                <h1>이용권 정보</h1>
                <c:forEach var="voucher" items="${ gym.vouchers }">
                <p>${ voucher.cate }</p>
               	<table class=vouchertbl>
               		<tr>
               			<th><th>
               			<th>1개월<th>
               			<th>3개월<th>
               			<th>6개월<th>
               			<th>12개월<th>
               		<tr>
               		<tr>
               			<td>정상가<td>
               			<td><fmt:formatNumber value="${voucher.price}" pattern="#,###"/>원<td>
               			<td><fmt:formatNumber value="${voucher.price3}" pattern="#,###"/>원<td>
               			<td><fmt:formatNumber value="${voucher.price6}" pattern="#,###"/>원<td>
               			<td><fmt:formatNumber value="${voucher.price12}" pattern="#,###"/>원<td>
               		<tr> 
               		 <tr style="color:red;">
               			<td>FITness 회원가<td>
               			 <td>
               			 <fmt:parseNumber var="price1" integerOnly="true" value="${ voucher.price*0.9}" />
               			 	<fmt:formatNumber value="${price1}" pattern="#,###"/>원
               			 <td>
               			<td>
               			 <fmt:parseNumber var="price3" integerOnly="true" value="${ voucher.price3*0.9}" />
               			 	<fmt:formatNumber value="${price3}" pattern="#,###"/>원<td>
               			<td>
               			 <fmt:parseNumber var="price6" integerOnly="true" value="${ voucher.price6*0.9}" />
               			 	<fmt:formatNumber value="${price6}" pattern="#,###"/>원<td>
               			<td>
               			 <fmt:parseNumber var="price12" integerOnly="true" value="${ voucher.price12*0.9}" />
               			 	<fmt:formatNumber value="${price12}" pattern="#,###"/>원<td>
               		<tr> 
               	</table>
                
                </c:forEach>
                <br>
            </div>
            <div id="detail3" class="detail">
                <h1>운영시간</h1>
                <c:if test="${ fn:contains(gym.time, '[') }">
                <c:forTokens var="time" items="${ gym.time }" delims="[" varStatus="num">
    					<p>[${ time }</p>                                    		
					</c:forTokens>
				</c:if>
				<c:if test="${ !fn:contains(gym.time, '[') }">
                <c:forTokens var="time" items="${ gym.time }" delims="[" varStatus="num">
    					<p>${ time }</p>                                    		
					</c:forTokens>
				</c:if>
            </div>
            <div id="detail4" class="detail">
                <h1>위치</h1>
                <p></p>
                <p>${ gym.address }</p>
            </div>
            <div id="detail5" class="detail">
                <h1>리뷰(${ reviewCount })</h1>
                <div id="comment-container">
                    
                        <form id="insert-review" action="${ path }/gym/review" method="POST" >
                            <select name="grade" id="grade" style="color: rgba(236, 202, 4, 0.815);">
                                <option value="5" selected>★★★★★</option>
                                <option value="4">★★★★☆</option>
                                <option value="3">★★★☆☆</option>
                                <option value="2">★★☆☆☆</option>
                                <option value="1">★☆☆☆☆</option>
                            </select>
                            <br>
                            <input type="hidden" name="no" value="${ gym.no }">
                            <textarea name="content" id="reviewtext" cols="85" rows="3"></textarea>
                            <p style="text-align:right; padding-right:80px; margin-top:0px">
                            	<button type="submit" id="btn-insert">리뷰 등록</button>
                            	</p>          
                        	</form>
                </div>
                
                <table id="tbl-comment">
	                <c:forEach var="review" items="${ gym.reviews }">
	                    <tr class="level1">
	                        <td>
	       
	                            <sub class="review-writer">${ review.writerId }</sub>
	                            <sub class="review-date">${ review.createDate }</sub>
	                            <sub class="review-date" style="color: rgba(236, 202, 4, 0.815)">
									<c:choose>
									<c:when test="${review.grade > 4.5}">★★★★★</c:when>
									<c:when test="${review.grade <= 4.5 && review.grade > 3.5}">★★★★☆</c:when>
									<c:when test="${review.grade <= 3.5 && review.grade > 2.5}">★★★☆☆</c:when>
									<c:when test="${review.grade <= 2.5 && review.grade > 1.5}">★★☆☆☆</c:when>
									<c:when test="${review.grade <= 1.5}">★☆☆☆☆</c:when>
									<c:otherwise> - </c:otherwise>
									</c:choose>
	                            </sub><sub class="review-grade" style="font-size:12px; color:gray;">(${ review.grade }.0)</sub>
	                           
	                            <p class="review-content">${ review.content }</p>
	                            <!-- <p class="review-good" style="font-size:12px; color: gray;">이 리뷰가 도움이 돼요! &nbsp;&nbsp;<button class="btn-good">♡{#}</button></p>
	                         --></td>
	                        <td>
	                        	<c:if test="${ not empty loginMember && loginMember.id == review.writerId }">
	                        	
	                        		<button type="button" class="btn-update" onclick="javascrit:window.open('${ path }/review/update?no=${ review.no }','popup_1','width=500px,height=300px,toolbar=no,location=no,status=no,menubar=no,scrollbars=no,resizable=no,left=300px,top=100px')">
	                        		수정</button>
	                        		<form name="deleteform" id="deleteform" action="${ path }/review/delete" method="post">
	                        		<input type="hidden" name="no" value="${ review.no }">
	                        		<input type="hidden" name="gno" value="${ gym.no }">
	                            	<input type="submit" class="btn-delete" value="삭제">
	                            	</form>
	                            </c:if>
	                        </td>
	                    </tr>
                    </c:forEach>
                </table>
            </div>
        </section>

	<script>
	$(document).ready(() => {
			$('#reviewtext').on('focus', () => {
				if(${ empty loginMember}) {
					alert('로그인 후 이용해 주세요.')	;
					
					$('.mainlogin').focus();
				}
			});

			$('#cart-btn2').on('click', () => {
				if(${ empty loginMember}) {
					alert('로그인 후 이용해 주세요.')	;
					
					$('.mainlogin').focus();
				}
			});
			$('#buy-btn2').on('click', () => {
				if(${ empty loginMember}) {
					alert('로그인 후 이용해 주세요.')	;
					 	
					$('.mainlogin').focus();
				}
			});
		 

			
	});
	</script>
		 
<jsp:include page="/views/common/footer.jsp" />