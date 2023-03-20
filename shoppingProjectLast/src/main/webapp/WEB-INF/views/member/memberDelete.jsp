<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mypage삭제화면</title>
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
			<h1>회원 탈퇴</h1>
		</div>

		<!-- 내용 -->
		<div class= "container">
			<div class="row">
				<nav class="col-sm-2">
					<ul class="nav nav-pills nav-stacked">
							<li><a href="/member/mypageForm.do">회원 정보 보기</a></li>
							<li><a href="/member/memberUpdate.do">회원 정보 수정</a></li>
							<li class="active"><a href="/member/memberDelete.do">회원 탈퇴</a></li>
					</ul>
				</nav>
				<div class="col-sm-offset-1 col-sm-9">
					<form class = "form-horizontal" id = "frm">
						<div class = "form-group">
							<label for ="userID" class="col-sm-4 control-label">아이디</label>
							<div class = "col-sm-4">
								<input type = "text" class = "form-control" id="userID" name = "userID" maxlength = "200" value = "${member1.userID }" readonly>
							</div>
						</div>
						<div class = "form-group">
							<label for = "pwd" class = "col-sm-4 control-label">비밀번호</label>
							<div class = "col-sm-4">
								<input type = "password" class = "form-control" id = "pwd" name = "pwd" maxlength = "200" value = "${member1.pwd }" readonly>
							</div>
						</div>
						<div class="form-group">
							<label for="repwd" class="col-sm-4 control-label">비밀번호 확인</label>
							<div class="col-sm-4">
								<input type="password" class="form-control" id="repwd" name="repwd" maxlength="20" value = "" onkeyup="passConfirm()"> 
								<br>
								<strong><span id ="confirmMsg" style = "font-size: 15px; padding: 5px;"></span></strong>
							</div>
						</div>
						
						<br/>
						<br/>
						<div class = "form-group" id="myPageBtn">
							<button type = "button" class = "btn btn-light mypageBtn" onclick="location.href='/main.do'">
								<span class = "glyphicon glyphicon-home"></span> Home
							</button>
							
							<button type = "button" class = "btn btn-light mypageBtn" onclick = "fn_removeMyMember('${member1.userID}')">
								<span class = "glyphicon glyphicon-trash"></span> Unregister
							</button>
						</div>
					</form>
				</div>
			</div>
		</div>

</body>
</html>