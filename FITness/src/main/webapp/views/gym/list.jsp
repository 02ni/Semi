<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${ pageContext.request.contextPath }"/>


<jsp:include page="/views/common/header.jsp"/>
<script src="${ path }/resources/js/infiniteScroll.js"></script>
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
            background-color: rgb(255,151,0);
           	color : white;
        }
        /* 경기,서울 높이 같게 */
        #search_region1{
            height: 180px;
            
        }
        
        #search_region2{
            height: 180px;
        }
        
         #search_region3{
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
            height: 150x;
            border: 3px solid rgb(255,151,0);
        }
        
        #region2 {  
            padding: 15px;
             border: 3px solid rgb(255,151,0);
        } 
        
        #region3 {  
            padding: 15px;
            border: 3px solid rgb(255,151,0);
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
           	border: 3px solid rgb(255,151,0);
        
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
            height: 40px;
        }
        #sortarea{
            width: 100%;
            height: 60px;
            background-color: rgb(255,151,0, 0.5);
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
            
            background-color: #eaf818;
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
        width: 100%;
        }
        #container{
        height: 100%;
        
        width: 100%;
        margin: 25px;
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
            font-weight: 900;
        }
        .cate{
            color: rgb(255,151,0);
            font-weight: 700;
        }
		/*페이지바*/
		div#pageBar{margin-top:10px; text-align:center; height: 100px; }
		div#pageBar>button{
		 background: rgb(255,151,0);
		border: none;
		color: white;
		}
		
	.bthsearch {
	  background: rgb(255,151,0);
	  border: none;
	  z-index: 1;
	  
	   width: 130px;
	  height: 40px;
	  border-radius: 5px;
	  padding: 10px 25px;
	  font-weight: 800;
	  cursor: pointer;
	  transition: all 0.3s ease;
	  position: relative;
	  display: inline-block;
	   box-shadow:inset 2px 2px 2px 0px rgba(255,255,255,.5),
	   7px 7px 20px 0px rgba(0,0,0,.1),
	   4px 4px 5px 0px rgba(0,0,0,.1);
	  outline: none;
	}
	.bthsearch:after {
	  position: absolute;
	  content: "";
	  width: 100%;
	  height: 0;
	  top: 0;
	  left: 0;
	  z-index: -1;
	  border-radius: 5px;
	  background-color: #eaf818;
	  background-image: linear-gradient(315deg, #eaf818 0%, #f6fc9c 74%);
	  box-shadow:inset 2px 2px 2px 0px rgba(255,255,255,.5)
	   7px 7px 20px 0px rgba(0,0,0,.1),
	   4px 4px 5px 0px rgba(0,0,0,.1);
	  transition: all 0.3s ease;
	}
	.bthsearch:hover {
	  background-color: #eaf818;
	  color: #ffffff;
	}
	.bthsearch:hover:after {
	  top: auto;
	  bottom: 0;
	  height: 100%;
	}
	.bthsearch:active {
	  top: 2px;
	}
</style>

<section>
        <div id="title" class="search">
            <p id="titletext">Gym 찾기</p>
        </div>
	<form  action="${ path }/gym/search" method="GET" >
        <div id="search_regionbar" class="search">
            <div class="left1">
                <h2>지역</h2>
            </div>
             <div class="right1">
                <div id="regionbar">
                    <ul class="select">
                        <li id="ckall">전체</li>
                        <li id="ckregin1">서울</li>
                        <li id="ckregin2">경기도</li>
                       
                    </ul>
                </div>
            </div>
		<input type="hidden" name="bigregion" id="bigregion" value="전체">
        </div>
        
		<div id="search_region1" class="search">
            <div class="left1">
            </div>
            <div  class="right1" id="region1">
                <div>
                    <ul class="select">
                        <li>
                        <label><input type="radio" name="region" value="전체" checked>전체</label>
                        </li>
                        
                    </ul>
                </div>
        	</div>
        </div>
        
        <div id="search_region2" class="search"  style="display:none;">
            <div class="left1">
            </div>
            <div  class="right1" id="region2">
                <div>
                    <ul class="select">
                        <li>
                        <label><input type="radio" name="region" value="서울" >서울 전체</label>
                        </li>
                        <li>
                        <label><input type="radio" name="region" value="강남구">강남구</label>
                        </li>
                        <li>
                        <label><input type="radio" name="region" value="강북구">강북구</label>
                        </li>
                        <li>
                        <label><input type="radio" name="region" value="강서구">강서구</label>
                        </li>
                        <li>
                        <label><input type="radio" name="region" value="관악구">관악구</label>
                        </li>
                        <li>
                        <label><input type="radio" name="region" value="광진구">광진구</label>
                        </li>
                        <li>
                        <label><input type="radio" name="region" value="구로구">구로구</label>
                        </li>
                        <li>
                        <label><input type="radio" name="region" value="금천구">금천구</label>
                        </li>
                        <li>
                        <label><input type="radio" name="region" value="노원구">노원구</label>
                        </li>
                        <li>
                        <label><input type="radio" name="region" value="도봉구">도봉구</label>
                        </li>
                        <li>
                        <label><input type="radio" name="region" value="동대문구">동대문구</label>
                        </li>
                        <li>
                        <label><input type="radio" name="region" value="동작구">동작구</label>
                        </li>
                        <li>
                        <label><input type="radio" name="region" value="송파구">송파구</label>
                        </li>
                    </ul>
                </div>
            </div>
        </div>

        <div id="search_region3" class="search" style="display:none;">
            <div class="left1">
            </div>
            <div  class="right1" id="region3" >
                <div>
                    <ul class="select">
                        <li>
                        <label><input type="radio" name="region" value="경기도" >경기도 전체</label>
                        </li>
                        <li>
                        <label><input type="radio" name="region" value="과천시">과천시</label>
                        </li>
                        <li>
                        <label><input type="radio" name="region" value="광주시">광주시</label>
                        </li>
                        <li>
                        <label><input type="radio" name="region" value="성남시 분당구">성남시 분당구</label>
                        </li>
                        <li>
                        <label><input type="radio" name="region" value="성남시 수정구">성남시 수정구</label>
                        </li>
                        <li>
                        <label><input type="radio" name="region" value="성남시 중원구">성남시 중원구</label>
                        </li>
                        <li>
                        <label><input type="radio" name="region" value="수원시 권선구">수원시 권선구</label>
                        </li>
                        <li>
                        <label><input type="radio" name="region" value="수원시 팔달구">수원시 팔달구</label>
                        </li>
                        <li>
                        <label><input type="radio" name="region" value="수원시 장안구">수원시 장안구</label>
                        </li>
                        <li>
                        <label><input type="radio" name="region" value="안성시">안성시</label>
                        </li>
                        <li>
                        <label><input type="radio" name="region" value="파주시">파주시</label>
                        </li>
                        <li>
                        <label><input type="radio" name="region" value="화성시">화성시</label>
                        </li>
                    </ul>
                </div>
            </div>
        </div>

        <div class="none"></div>


        <div id="search_category" class="search">
            <div id="left2">
                <h2>GYM</h2>
            </div>
            <div id="right2">
                <div id="category">
                    <input type="hidden" name="cate" id="default" value="default">
                    <ul class="select">
                    <li><label><input type="checkbox" name="cateall" value="전체" checked onclick='selectAll(this)'>전체</label></li>
                    <li><label><input type="checkbox" name="cate" value="헬스" onclick='checkSelectAll()'>헬스</label></li>
                    <li><label><input type="checkbox" name="cate" value="PT" onclick='checkSelectAll()'>PT</label></li>
                    <li><label><input type="checkbox" name="cate" value="G.X" onclick='checkSelectAll()'>G.X</label></li>
                    <li><label><input type="checkbox" name="cate" value="스피닝" onclick='checkSelectAll()'>스피닝</label></li>
                    <li><label><input type="checkbox" name="cate" value="크로스핏" onclick='checkSelectAll()'>크로스핏</label></li>
                    <li><label><input type="checkbox" name="cate" value="골프" onclick='checkSelectAll()'>골프</label></li>
                    <li><label><input type="checkbox" name="cate" value="수영" onclick='checkSelectAll()'>수영</label></li>
                    <li><label><input type="checkbox" name="cate" value="요가" onclick='checkSelectAll()'>요가</label></li>
                    <li><label><input type="checkbox" name="cate" value="필라테스" onclick='checkSelectAll()'>필라테스</label></li>
                    </ul>
                </div>
            </div>
        </div>
        
        <div id="btnarea" class="search">

            <input type="submit" id="bthsearch" class="bthsearch" value="검색하기">
        </div>
			</form>
        <div class="none"></div>
        <div id="sortarea" class="sort">
            <span id="sortspan">전체센터 ${ count }개</span>

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
                                    <td><p>FIT회원가</b><br><sub>3개월기준</sub></td>
                                    
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
  	<div style="height: 500px; "></div>
  <div style="height: 0px; ">
		  <div id="pageBar" style="height: 10px;">
		  <br><br> <br><br>
					<!-- 맨 처음으로 -->
					<button onclick="location.href='${ path }/gym/list?page=1'">&lt;&lt;</button>
		
					<!-- 이전 페이지로 -->
					<button onclick="location.href='${ path }/gym/list?page=${ pageInfo.prevPage }'">&lt;</button>
		
					<!--  12개 페이지 목록 -->
					<c:forEach begin="${ pageInfo.startPage }" end="${ pageInfo.endPage }" varStatus="status">
						<c:choose>
							<c:when test="${ status.current == pageInfo.currentPage }">
								<button disabled>${ status.current }</button>
							</c:when>
							<c:otherwise>
								<button onclick="location.href='${ path }/gym/list?page=${ status.current }'">${ status.current }</button>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					
		
					<!-- 다음 페이지로 -->
					<button onclick="location.href='${ path }/gym/list?page=${ pageInfo.nextPage }'">&gt;</button>
		
					<!-- 맨 끝으로 -->
					<button onclick="location.href='${ path }/gym/list?page=${ pageInfo.maxPage }'">&gt;&gt;</button>
				</div>
		</div>
    </section>
    <br/><br/><br/><br/>
     <script>
    $(function(){
   		/* 전체클릭 */
      $('#ckall').click(function(){
      if($("#search_region1").css("display") == "none"){
         $("#search_region1").show();
         }
      if($("#search_region2").css("display") != "none"){
         $("#search_region2").hide();
      }
      if($("#search_region3").css("display") != "none"){
          $("#search_region3").hide();
       }
   });
      /* 서울클릭 */
      $('#ckregin1').click(function(){
          if($("#search_region1").css("display") != "none"){
             $("#search_region1").hide();
             }
          if($("#search_region2").css("display") == "none"){
             $("#search_region2").show();
          }
          if($("#search_region3").css("display") != "none"){
             $("#search_region3").hide();
          }
          $('#bigregion').val('서울');
      });
      /* 경기클릭 */
      	 $('#ckregin2').click(function(){
              if($("#search_region1").css("display") != "none"){
                 $("#search_region1").hide();
                 }
              if($("#search_region2").css("display") != "none"){
                 $("#search_region2").hide();
                 }
             	if($("#search_region3").css("display") == "none"){
                 $("#search_region3").show();
              }
         $('#bigregion').val('경기');
          
       });
    });
       </script>
       <script>
      
       function checkSelectAll()  {
    	   // 전체 체크박스
    	   const checkboxes 
    	     = document.querySelectorAll('input[name="cate"]');
    	   // 선택된 체크박스
    	   const checked 
    	     = document.querySelectorAll('input[name="cate"]:checked');
    	   // select all 체크박스
    	   const selectAll 
    	     = document.querySelector('input[name="cateall"]');
    	   
    	   if(checkboxes.length === checked.length)  {
    	     selectAll.checked = true;
    	   }else {
    	     selectAll.checked = false;
    	   }
    	   if(checked.length === 0)  {
      	     selectAll.checked = true;
      	   }

    }

    	 function selectAll(selectAll)  {
    	   const checkboxes 
    	      = document.getElementsByName('cate');
    	   
    	   checkboxes.forEach((checkbox) => {
    	     checkbox.checked = selectAll.checked
    	   })
    	}
      
    </script>
    <br/><br/><br/><br/><br/><br/><br/><br/><br/><br/> <br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/> <br/><br/><br/><br/><br/><br/><br/>
	<jsp:include page="/views/common/footer.jsp" />