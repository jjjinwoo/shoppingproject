<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<%	request.setCharacterEncoding("UTF-8"); %>

<!DOCTYPE html>
	<html>
		<head>
			<meta charset="UTF-8">
			<title>관리자 수정 화면</title>
			<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
			<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
			<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
			<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>	
			<!-- 제이쿼리 ui css -->
            <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
            <!-- 제이쿼리  style css -->
            <link rel="stylesheet" href="/resources/demos/style.css">
            <!-- 제이쿼리 js -->
            <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
            <!-- 제이쿼리 ui js-->
            <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
			<!-- 다음 API -->
			<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
			<link href="${contextPath}/resources/css/menu.css" rel="stylesheet" type="text/css">
			<link href="${contextPath}/resources/css/member.css" rel="stylesheet" type="text/css">
			<script src="${contextPath}/resources/js/member.js"></script>			
		</head>
		<body>
			<!-- 상단 메뉴 -->
			<jsp:include page ="../common/topMenu.jsp"></jsp:include>
			<div id="mainTitle">
				<hr/>
				<h1>관리자용 회원 정보 수정</h1>
			</div>
			
			<!-- 내용 -->
			<div class = "container">
				<form class = "form-horizontal" id = "memModifyForm" method="post" action="${contextPath}/member/modifyMember.do">
					<div class = "form-group">
						<label for = "userID" class = "col-sm-4 control-label">아이디</label>
						<div class = "col-sm-4">
							<input type = "text" class = "form-control" id = "userID" name = "userID" maxlength = "200" value = "${member.userID }" readonly>
						</div>
					</div>
					
					<div class="form-group">
						<label for="pwd" class="col-sm-4 control-label">비밀번호</label>
						<div class="col-sm-4">
							<input type="password" class="form-control" id="pwd" name="pwd" maxlength="20" value = "${member.pwd }"/>
						</div>
					</div>
					<div class="form-group">
						<label for="repwd" class="col-sm-4 control-label">비밀번호 확인</label>
						<div class="col-sm-4">
							<input type="password" class="form-control" id="repwd" name="repwd" maxlength="20" placeholder="비밀번호 확인" onkeyup="passConfirm()" > 
							<strong><span id ="confirmMsg" style = "font-size: 15px; padding: 5px;"></span></strong>
						</div>
					</div>
					<div class = "form-group">
						<label for = "name" class = "col-sm-4 control-label">이  름</label>
						<div class = "col-sm-4">
							<input type = "text" class = "form-control" id = "name" name = "name" maxlength = "200" value = "${member.name }">
						</div>
					</div>
					<div class = "form-group">
						<label for = "postnum" class = "col-sm-4 control-label">우편번호</label>
						<div class = "col-sm-4">
							<input type = "text" class = "form-control" id = "postnum" name = "postnum" maxlength = "200" value = "${member.postnum }"  onclick = "daumZipCode()">
						</div>
					</div>
					<div class = "form-group">
						<label for = "address1" class = "col-sm-4 control-label">주  소</label>
						<div class = "col-sm-4">
							<input type = "text" class = "form-control" id = "address1" name = "address1" maxlength = "200" value = "${member.address1 }">
						</div>
					</div>
					<div class = "form-group">
						<label for = "address2" class = "col-sm-4 control-label">상세주소</label>
						<div class = "col-sm-4">
							<input type = "text" class = "form-control" id = "address2" name = "address2" maxlength = "200" value = "${member.address2 }">
						</div>
					</div>
					<div class = "form-group">
						<label for = "phone" class = "col-sm-4 control-label">phone</label>
						<div class = "col-sm-4">
							<input type = "text" class = "form-control" id = "phone" name = "phone" maxlength = "200" value = "${member.phone }"
							oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" onKeyup="inputPhoneNumber();" maxlength="13"/>
						</div>
					</div>
					<div class="form-group">
						<label for = "subject" class="col-sm-4 control-label">Gender</label>
						<div class="col-sm-4">
							<input type='radio' name='gender' value='female' <c:if test="${member.gender=='female'}">checked</c:if>/>&nbsp;여성&nbsp;&nbsp;
		  					<input type='radio' name='gender' value='male' <c:if test="${member.gender=='male'}">checked</c:if>/>&nbsp;남성
						</div>
					</div>
					<div class = "form-group">
						<label for = "age" class = "col-sm-4 control-label">Birth-day</label>
						<div class = "col-sm-4">
							<input type = "text" class = "form-control" id = "age" name = "age" maxlength = "200" value = "${member.age }" readonly>
						</div>
					</div>
					<div class = "form-group">
						<label for = "email" class = "col-sm-4 control-label">이메일</label>
						<div class = "col-sm-4">
							<input type = "text" class = "form-control" id = "email" name = "email" maxlength = "200" value = "${member.email }">
							<input type = "hidden" class = "form-control" id = "grade" name = "grade" maxlength = "200" value = "${member.grade }">
						</div>
					</div>
					<br/>
					<div class = "form-group">
						<p align = "center">
							<button type = "reset" class = "btn btn-light">
								<span class = "glyphicon glyphicon-erase"></span> 다시 입력
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type = "submit"  id="Updatesubmit" name="Updatesubmit" class = "btn btn-light">
								<span class = "glyphicon glyphicon-pencil"></span> 수정 완료
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type = "button" class = "btn btn-light" onclick="location.href='/main.do'">
								<span class = "glyphicon glyphicon-home"></span>
								&nbsp;Home
							</button>
						</p>
					</div>
				</form>
			</div>
			
			<!-- 하단 메뉴바 -->
			<jsp:include page = "../common/footer.jsp" flush = "false"/>
		</body>
	</html>