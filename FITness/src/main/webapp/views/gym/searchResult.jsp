<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="path" value="${ pageContext.request.contextPath }"/>


<jsp:include page="/views/common/header.jsp"/>
<style>
#title{
            width: 100%;
            height: 80px;
        }
        
        .none{
            width: 100%;
            height: 20px;
        }
        #sortarea{
            width: 100%;
            height: 60px;
        }

        /* 버튼 */
        #bthsearch{
            margin: 5px 0 0 0;
            width: 200px;
            height: 50px;
        }
        #titletext{
            font-size: 20pt;
            font-weight: 700;

        }
        /* sorting ul */
        .sortul{
            list-style-type: none;
            margin: 0;
            padding: 0 0 0 0 ;
            height:100%;
        }

        .sortul>li{
            float:left;
            font-size:15px;
            line-height:55px;
            width: 13%;
        }
        #sortspan{
            margin: 0 50px 0 20px;
            float:left;
            line-height:55px;
            
        }
        /* 센터 목록 */
        #listarea{
        margin: 25px;
        height: 100%;
        }
        .layout {
        width: 100%;
        margin: 25px;
        }
        .layout>div{
            padding: 20px;
            margin: 10px;
            width: 30%;
            height: 270px;
            float:left;
            border: 3px solid rgb(255,151,0);
        }
        .layout>div:hover{
            padding: 20px;
            margin: 10px;
            width: 30%;
            height: 270px;
            float:left;
            border: 3px solid rgb(255,151,0);
            background-color: rgb(255,151,0, 0.7);
        }
        .listtbl{
            width: 100%;
            height: 100%;
        }
        .gymtitle{
            font-size: 15pt;
            font-weight: 700;
        }
        .cate{
            color: rgb(255,151,0);
            font-weight: 700;
        }
</style>

<section>
        <div id="title" class="search" style="text-align:center;">
        <br>
            <p id="titletext"><span style="color:red"><c:if test="${ not empty regionBig }">'${ regionBig }'</c:if> <c:if test="${ not empty region }">'${ region }' </c:if> </span><span style="color:orange">
            <c:forEach var="cate" items="${ cate }"><c:if test="${ not (cate=='default') }">'${ cate }' </c:if></c:forEach></span> 에 대한 검색결과</p>
        <br>
        </div>
        <div class="none"></div>
        <div id="sortarea" class="sort">
            <span id="sortspan">검색된센터 ${ count }개</span>
        </div>

             <div id="listarea" class="sort">
            <div class="layout" id="container">
                <c:if test="${ empty list }">
                    <p>조회된 목록이 없습니다.</p>
                </c:if>
                <c:if test="${ not empty list }">
                    <c:forEach var="gym" items="${ list }" >
                        <div class="item" onclick="location.href ='${ path }/gym/detail?gym=${ gym.no }'" style="cursor:pointer;" >
                            <table class="listtbl">
                                <tr>
                                    <td class="gymtitle" colspan="2">${ gym.gymName }</td>
                                </tr>
                                <tr>
                                    <td colspan="2" style="color:rgb(150,150,150);">${ gym.address }</td>
                                </tr>
                                <tr>
                                    <td><p>FIT회원가<br><sub>3개월기준</sub></td>
                                    
                                    <td style="font-size:15pt; color:red; font-weight:800; ">
                                    <fmt:parseNumber var="price" integerOnly="true" value="${ gym.price*3*0.95}" pattern="#,###"/>
               			 			<fmt:formatNumber value="${price}" pattern="#,###"/>원
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="2">
	                                    <c:forTokens var="category" items="${ gym.category }" delims="," varStatus="num">
	    									<span class="cate">${ category }&nbsp;&nbsp;</span>
	    									
                                    		<c:if test="${num.count==4}"><br></c:if>                                    		
										</c:forTokens>
									</td>
                                </tr>
                            </table>
                        </div>
                    </c:forEach>
                </c:if>
                
            </div>
        </div>

    </section>
     </div> 
     <br/><br/><br/><br/><br/><br/><br/><br/><br/><br/> <br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/> <br/><br/><br/><br/><br/><br/><br/>
	
    </body>
</html>