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
        /* 지역으로 찾기 */
        #search_regionbar{
            height: 50px;
            
           ;
            
        }
        #regionbar{
            width: 100%;
            height: 100%;
            padding: 0 15px;
            background-color: rgb(216, 197, 197);
        }
        /* 경기,서울 높이 같게 */
        #search_region1{
            height: 180px;
            
        }
        
        #search_region2{
            height: 180px;
        }

        .left1{
            width: 10%;
            height: 100%;
            float: left;
            text-align: center;
        }
        .right1{
            width: 90%;
            height: 100%;
            float: left;
        }
        #region1 {
            padding: 15px;
            background-color: rgb(248, 248, 248);
        }
        
        #region2 {  
            padding: 15px;
            background-color: rgb(248, 248, 248);
        }

        /* 카테고리선택 */
        #search_category {
            height: 180px;
        }

        #left2 {
            width: 10%;
            height: 100%;
            float: left;
            text-align: center;
        }
        
        #right2{
            width: 90%;
            height: 100%;
            float: left;
        }

        #category{
            padding: 15px;
            width: 100%;
            height: 130px;
            background-color: rgb(248, 248, 248);
        
        }

        #onlycheck{
            width: 100%;
            height: 50px;
            text-align: right;
        }

        #btnarea{
            width: 100%;
            height: 60px;
            text-align: center;
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
        /* ul */
        .select{
            list-style-type: none;
            margin: 0;
            padding: 0 0 0 0 ;
            height:100%;
        }

        .select>li{
            float:left;
            width:20%;
            text-align:center;
            font-size:18px;
            line-height:45px;
        }

        .select>li:hover{
            
            background-color: rgb(245, 218, 66);
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
        #category > ul> li{
            font-size: 15pt;
            font-weight: 700;
        }
        #regionbar > ul> li{
            font-size: 15pt;
            font-weight: 700;
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
            border: 2px solid rgb(255, 246, 246);
            background-color: rgb(248, 237, 224);
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
            color: red;
            font-weight: 700;
        }
</style>

<section>
        <div id="btnarea" class="search">
			
        </div>
        <div class="none"></div>
        <div id="sortarea" class="sort">
            <span id="sortspan">즐겨찾기 목록</span>

        </div>

        <div id="listarea" class="sort">
            <div class="layout" id="container">
                <c:if test="${ empty list }">
                    <p>조회된 목록이 없습니다.</p>
                </c:if>
                <c:if test="${ not empty list }">
                    <c:forEach var="gym" items="${ list }">
                        <div onclick="location.href ='${ path }/gym/detail?gym=${ gym.no }'" style="cursor:pointer;" >
                            <table class="listtbl">
                                <tr>
                                    <td class="gymtitle" colspan="2">${ gym.gymName }</td>
                                </tr>
                                <tr>
                                    <td colspan="2">${ gym.address }</td>
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
	<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
    </section>
    
	<jsp:include page="/views/common/footer.jsp" />