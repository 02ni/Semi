<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<jsp:include page="/views/common/header.jsp"/>
<c:set var="path" value="${ pageContext.request.contextPath }"/>
<link rel="stylesheet" href="${ path }/resources/css/complete2.css">
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
                        <td><img src="resources/images/23795E3B583E7B5E0E.jpg" id="img"></td>
                        <td id="com2">구매가 정상적으로 <br>완료되었습니다!</td>
                    </tr>
                </table>
                
                <br><br><br>
            </div>
            <div id="section2">
                <p align="center"  font-size: 30px;>무통장 입금 부탁드려요!</p>
                <table align="center">
                    <tr>
                        <th>구매자</th>
                        <td><input type="text" id="info"></td>
                    </tr>
                    <tr>
                        <th>가상 계좌</th>
                        <td><input type="text" id="info"></td>
                    </tr>
                    <tr>
                        <th>은행</th>
                        <td><input type="text" id="info"></td>
                    </tr>
                </table>
            </div>

            <div id="section3">
                <table  id="btn_2">
                    <tr>
                        <td><input type="button" value="마이페이지로 이동" id="my_btn"></td>
                    </tr>
                </table>
            </div>
            <br><br>
            <div id="section4">
                <table  id="btn_3">
                    <tr>
                        <td><input type="button" value="메인페이지로 이동" id="main_btn"></td>
                    </tr>
                </table>
            </div>
            
        
        </div>
	
	
	
	    <jsp:include page="/views/common/footer.jsp" />
	
</body>
</html>