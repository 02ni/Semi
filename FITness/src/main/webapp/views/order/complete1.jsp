<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<jsp:include page="/views/common/header.jsp"/>
<c:set var="path" value="${ pageContext.request.contextPath }"/>
<link rel="stylesheet" href="${ path }/resources/css/complete1.css">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Complete1</title>
</head>
<body>
<br><br><br><br><br><br><br><br><br><br><br>
	<div id="section">
            <div id="section1">
                <table id="com">
                    <tr>
<%--                     <img src="${ path }/resources/images/check.png"height="100%" width="100%" align="center"> --%>
                        <td> <img src="${ path }/resources/images/check.png" id="img"></td>
                        <td id="com2">구매가 정상적으로 <br>완료되었습니다!</td>
                    </tr>
                </table>
            </div>
            <br><br><br><br><br><br><br><br><br><br>
            <div id="section2">
                <table  id="btn_2">
                    <tr>
                        <td><input type="button" onclick="location.href='${ path }/purchaseHistory'" value="구매내역으로 이동" id="my_btn"></td>
                    </tr>
                </table>
            </div>
            <br><br>
            <div id="section3">
                <table  id="btn_3">
                    <tr>
                        <td><input type="button" onclick="location.href='${path}/'" value="메인페이지로 이동" id="main_btn"></td>
                    </tr>
                </table>
            </div>
            
        
        </div>
	
	
	
	    <jsp:include page="/views/common/footer.jsp" />
	
</body>
</html>