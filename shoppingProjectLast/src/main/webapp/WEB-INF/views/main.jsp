<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<c:set var = "contextPath" value = "${pageContext.request.contextPath }"/>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
	<html>
		<head>
			<meta charset="UTF-8">
			<title>메인페이지</title>
			<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
			<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
			<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
			<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>			
			<link href="${contextPath}/resources/css/menu.css" rel="stylesheet" type="text/css">			
			<link href="${contextPath}/resources/css/front.css" rel="stylesheet" type="text/css">

		</head>
		<body>
		
			<!-- 상단 메뉴  -->
			<jsp:include page ="./common/topMenu.jsp"></jsp:include>
			<hr/>
			<br/>
			
			<!-- 슬라이더 -->
			<div class ="container mainContainer">
				<div id = "myCarousel" class ="carousel slide" data-ride = "carousel">
					<!-- indicators -->
					
					<!-- Wrapper for slides -->
					<div class ="carousel-inner">
						<div class ="item active">
							<!-- class = "img-responsive center-block" : 반응형 이미지를 가운데에 정렬한다. -->
							<img class = "img-responsive center-block" src = "${contextPath }/resources/images/main01.jpg"/>
		
						</div>
						<div class ="item">
							<!-- class = "img-responsive center-block" : 반응형 이미지를 가운데에 정렬한다. -->
							<img class = "img-responsive center-block" src = "${contextPath }/resources/images/main02.jpg"/>
		
						</div>
						<div class ="item">
							<!-- class = "img-responsive center-block" : 반응형 이미지를 가운데에 정렬한다. -->
							<img class = "img-responsive center-block" src = "${contextPath }/resources/images/main03.jpg"/>
		
						</div>
						<div class ="item">
							<!-- class = "img-responsive center-block" : 반응형 이미지를 가운데에 정렬한다. -->
							<img class = "img-responsive center-block" src = "${contextPath }/resources/images/main04.jpg"/>
		
						</div>
					</div>
					
					 
					<!-- 좌측, 우측으로 그림을 움직일 수 있도록 좌/우 버튼을 설정한다. -->
					<!--
					<a class = "left carousel-control" href = "#myCarousel" data-slide = "prev">
						<span class = "glyphicon glyphicon-chevron-left"></span>
					</a>
					
					<a class = "right carousel-control" href = "#myCarousel" data-slide = "next">
						<span class = "glyphicon glyphicon-chevron-right"></span>
					</a> 
					-->
					
				</div>
			</div>
			
			<!-- 상단 메뉴 -->
			<jsp:include page ="./common/footer.jsp"></jsp:include>
			
		</body>
	</html>