<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<c:set var = "contextPath" value = "${pageContext.request.contextPath }"/>
<c:set var = "result" value = "${param.result }"/>
<% request.setCharacterEncoding("UTF-8"); %>

<!DOCTYPE html>
	<html>
		<head>
			<meta charset="UTF-8">
			<title>Mypage</title>
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
			<div id="mainTitle">
				<hr/>
				<h1>회원 정보</h1>
			</div>
			
			<!-- 내용 -->
			<div class = "container">
				<div class="row">
					<nav class="col-sm-2">
						<ul class="nav nav-pills nav-stacked">
							<li class="active"><a href="/member/mypageForm.do">회원 정보 보기</a></li>
							<li><a href="/member/memberUpdate.do">회원 정보 수정</a></li>
							<li><a href="/member/memberDelete.do">회원 탈퇴</a></li>
						</ul>
					</nav>
					<div class="col-sm-offset-1 col-sm-9">
						<form class = "form-horizontal" id = "frm">
							<div class = "form-group">
								<label for = "userID" class = "col-sm-4 control-label">아이디</label>
								<div class = "col-sm-4">
									<input type = "text" class = "form-control" id = "userID" name = "userID" maxlength = "200" value = "${member1.userID }" readonly>
								</div>
							</div>
							<div class = "form-group">
								<label for = "pwd" class = "col-sm-4 control-label">비밀번호</label>
								<div class = "col-sm-4">
									<input type = "password" class = "form-control" id = "pwd" name = "pwd" maxlength = "200" value = "${member1.pwd }" readonly>
								</div>
							</div>
							<div class = "form-group">
								<label for = "name" class = "col-sm-4 control-label">이  름</label>
								<div class = "col-sm-4">
									<input type = "text" class = "form-control" id = "name" name = "name" maxlength = "200" value = "${member1.name }" readonly>
								</div>
							</div>
							<div class = "form-group">
								<label for = "postnum" class = "col-sm-4 control-label">우편번호</label>
								<div class = "col-sm-4">
									<input type = "text" class = "form-control" id = "postnum" name = "postnum" maxlength = "200" value = "${member1.postnum }" readonly>
								</div>
							</div>
							<div class = "form-group">
								<label for = "address1" class = "col-sm-4 control-label">주  소</label>
								<div class = "col-sm-4">
									<input type = "text" class = "form-control" id = "address1" name = "address1" maxlength = "200" value = "${member1.address1 }" readonly>
								</div>
							</div>
							<div class = "form-group">
								<label for = "address2" class = "col-sm-4 control-label">상세주소</label>
								<div class = "col-sm-4">
									<input type = "text" class = "form-control" id = "address2" name = "address2" maxlength = "200" value = "${member1.address2 }" readonly>
								</div>
							</div>
							<div class = "form-group">
								<label for = "phone" class = "col-sm-4 control-label">phone</label>
								<div class = "col-sm-4">
									<input type = "text" class = "form-control" id = "phone" name = "phone" maxlength = "200" value = "${member1.phone }" readonly>
								</div>
							</div>
							<div class="form-group">
								<label for = "subject" class="col-sm-4 control-label">Gender</label>
								<div class="col-sm-4">
									<input type='radio' name='gender' value='female' onclick="return false;" <c:if test="${member1.gender=='female'}">checked</c:if>/>&nbsp;여성&nbsp;&nbsp;
				  					<input type='radio' name='gender' value='male' onclick="return false;" <c:if test="${member1.gender=='male'}">checked</c:if>/>&nbsp;남성
								</div>
							</div>
							<div class = "form-group">
								<label for = "age" class = "col-sm-4 control-label">Birth-day</label>
								<div class = "col-sm-4">
									<input type = "text" class = "form-control" id = "age" name = "age" maxlength = "200" value = "${member1.age }" readonly>
								</div>
							</div>
							<div class = "form-group">
								<label for = "email" class = "col-sm-4 control-label">이메일</label>
								<div class = "col-sm-4">
									<input type = "text" class = "form-control" id = "email" name = "email" maxlength = "200" value = "${member1.email }" readonly>
								</div>
							</div>
							
							<br/>
							<br/>
							<div class = "form-group" id="myPageBtn">
								<button type = "button" class = "btn btn-light mypageBtn" onclick="location.href='/main.do'">
									<span class = "glyphicon glyphicon-home"></span> Home
								</button>
							</div>
						</form>
					</div>
				</div>
			</div>
			<!-- 하단 메뉴바 -->
			<jsp:include page = "../common/footer.jsp" flush = "false"/>
		</body>
	</html>