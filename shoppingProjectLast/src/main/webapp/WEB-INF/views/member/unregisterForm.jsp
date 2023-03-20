<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<c:set var = "contextPath" value = "${pageContext.request.contextPath }"/>
<c:set var = "result" value = "${param.result }"/>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
			<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
			<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
			<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
			<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>	
			<link href="${contextPath}/resources/css/menu.css" rel="stylesheet" type="text/css">
			<link href="${contextPath}/resources/css/member.css" rel="stylesheet" type="text/css">
			<script src="${contextPath}/resources/js/member.js"></script>
</head>
<body>
		<!-- 상단 메뉴 -->
		<jsp:include page ="../common/topMenu.jsp"></jsp:include>

		<hr/>
		<div class = "container">
			<br/>
			<p style = "text-align: center;">
			<img src="${contextPath}../resources/images/check.png" style = "width: 100px; height: 90px;"/>
			</p>
			<h2 style = "text-align:center;">회원탈퇴가 완료되었습니다.</h2>
			<br/>
			<div class = "jumbotron" style = "text-align: center; border-top: 1px solid #dcdcdc; padding: 20px;">
				<h4><strong>그동안 ANDEW 서비스를 이용해주셔서 감사합니다</strong></h4>
				<br/>
				<p style = "font-size: 15px;">보다 나은 서비스로 다시 찾아뵙겠습니다.</p>	
				<button type = "button" class = "btn btn-light mypageBtn" onclick="location.href='/main.do'" style = "background-color: black; color: white;">
				<span class = "glyphicon glyphicon-home" style = "background-color: black; color: white;"></span> Home
				</button>
			</div>
		</div>

		<!-- 하단 메뉴바 -->
		<jsp:include page = "../common/footer.jsp" flush = "false"/>
</body>
</html>