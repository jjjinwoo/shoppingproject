<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<c:set var = "contextPath" value = "${pageContext.request.contextPath }"/>
<c:set var = "result" value = "${param.result }"/>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
	<html>
		<head>
			<meta charset="UTF-8">
			<title>Concept</title>
			<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
			<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
			<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
			<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>	
			<link href="${contextPath}/resources/css/menu.css" rel="stylesheet" type="text/css">			
			<link href="${contextPath}/resources/css/front.css" rel="stylesheet" type="text/css">
		</head>
		<body>
			<!-- 상단 메뉴 -->
			<jsp:include page ="../common/topMenu.jsp"></jsp:include>
			
			<div id="mainTitle">
				<hr/>
				<h1>CONCEPT</h1>
			</div>
			
			<!-- 내용-->
			<div class="container">
				<div class="conceptbox">
					<h3><strong>BRAND CONCEPT</strong></h3>
					<h4>EVERYDAY STYLISH, SUSTAINABLE, GENDER NEUTRAL</h4>
					<h4>앤듀는 성별과 스타일의 경계를 넘어 스스로의 가치를 추구하는 현대인에게 편안하게 입을 수 있는 스타일을 제안합니다.</h4>
				</div>
				
				<div class="conceptbox">
					<h3><strong>BRAND TARGET</strong></h3>
					<h4>삶 전체의 질적 변화를 추구하며 어제보다 더 나은 나를 만들어 나가고자 하는 현대인</h4>
				</div>
				
					
				<div class="conceptbox">
					<h3><strong>BRAND LOGO & SYMBOL</strong></h3>
					<h1><strong>A&nbsp;&nbsp;&nbsp; N&nbsp;&nbsp;&nbsp; D&nbsp;&nbsp;&nbsp; E&nbsp;&nbsp;&nbsp; W</strong></h1>
				</div>
			</div>	
			
			<!-- 하단 메뉴바 -->
			<jsp:include page = "../common/footer.jsp" flush = "false"/>
		</body>
	</html>