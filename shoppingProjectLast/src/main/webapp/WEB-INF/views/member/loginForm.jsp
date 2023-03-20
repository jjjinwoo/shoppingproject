<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<c:set var = "contextPath" value = "${pageContext.request.contextPath }"/>
<c:set var = "result" value = "${param.result }"/>
<% request.setCharacterEncoding("UTF-8"); %>
	

<!DOCTYPE html>
	<html>
		<head>
			<meta charset="UTF-8">
			<title>Login</title>
			<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
			<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
			<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
			<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>	
			<link href="${contextPath}/resources/css/menu.css" rel="stylesheet" type="text/css">
			<link href="${contextPath}/resources/css/member.css" rel="stylesheet" type="text/css">
			<script src="${contextPath}/resources/js/member.js"></script>
			
			<!-- 로그인 여부 체크 -->
				<c:choose>
					<c:when test = "${result == 'loginIdEmpty' }">
						<script>
							window.onload = function(){
								alert("\n아이디를 입력하셔야 합니다.");
							}
						</script>
					</c:when>
					<c:when test = "${result == 'loginFailed' }">
						<script>
							window.onload = function(){
								alert("\n아이디를 잘못입력하셨습니다. \n\n 다시 로그인을 해주십시오.");
							}
						</script>
					</c:when>
					<c:when test = "${result == 'PasswordFailed' }">
						<script>
							window.onload = function(){
								alert("\n비밀번호를 잘못입력하셨습니다. \n\n 다시 로그인을 해주십시오.");
							}
						</script>
					</c:when>
				</c:choose>
			<!-- 로그인 여부 체크 -->
		</head>
		<body>
			<!-- 상단 메뉴 -->
			<jsp:include page ="../common/topMenu.jsp"></jsp:include>
			<div id="mainTitle">
				<hr/>
			</div>	
					
			<!-- 내용 -->
			<div class = "container" id="loginFormBox">
				<form class = "form-horizontal" method = "post" action = "/member/login.do">
					<div class = "form-group">
						<div class ="col-sm-offset-4 col-sm-4">
							<h2 align = "center">Login</h2>
						</div>
					</div>
					<div class = "form-group">
						<label for = "id" class ="col-sm-offset-3 col-sm-2 control-label">아이디</label>
						<div class = "col-sm-3">
							<input type = "text" class = "form-control" id = "loginUserID" name ="userID" maxlength="20" placeholder="아이디"/>
						</div>
					</div>
					<div class = "form-group">
						<label for = "pwd" class ="col-sm-offset-3 col-sm-2 control-label">비밀번호</label>
						<div class = "col-sm-3">
							<input type = "password" class = "form-control" id = "pwd" name ="pwd" maxlength="20" placeholder="비밀번호"/>
						</div>
					</div>
					<div class ="form-group">
						<div class = "col-sm-offset-5 col-sm-3">
							<button type = "reset" class = "btn btn-light">Re-enter</button>
							<button type = "submit" class = "btn btn-light" id = "submit">Login</button>
							<button type = "button" class = "btn btn-light" onclick="location.href='/member/memberForm.do'">Join</button>
						</div>
					</div>
				</form>
			</div>
			
			<!-- 하단 메뉴바 -->
			<jsp:include page = "../common/footer.jsp" flush = "false"/>	
		</body>
	</html>