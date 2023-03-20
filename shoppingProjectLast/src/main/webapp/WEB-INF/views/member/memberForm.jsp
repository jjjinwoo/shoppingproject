<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<c:set var = "contextPath" value = "${pageContext.request.contextPath }"/>
<% request.setCharacterEncoding("UTF-8"); %>

<!DOCTYPE html>
	<html>
		<head>
			<meta charset="UTF-8">
			<title>Join</title>
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
				<h1>Join</h1>
			</div>
			
			<div class="container">
				<form class="form-horizontal" method="post" id="loginForm" name="memInsForm" action="${contextPath}/member/addMember.do">
					<div class="form-group">
						<label for="id" class="col-sm-3 control-label">아이디</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" id="userID" name="userID" maxlength="20" placeholder="아이디 입력"/>
							<strong style = "color: red;">&nbsp;&nbsp;ID 중복 여부를 확인해주세요.</strong>
						</div>
						<div class = "col-sm-4">
							<!-- 아이디 중복검사 -->
							<button class="btn btn-light idCheck" type="button" id="idCheck" onClick="fn_idCheck();" value="N">아이디 중복 검사</button>
						</div>
					</div>
					
					<!-- 비밀번호 일치 여부 -->
					<div class="form-group">
						<label for="pwd" class="col-sm-3 control-label">비밀번호</label>
						<div class="col-sm-3">
							<input type="password" class="form-control" id="pwd" name="pwd" maxlength="20" placeholder="비밀번호 입력"/>
						</div>
					</div>
					<div class="form-group">
						<label for="repwd" class="col-sm-3 control-label">비밀번호 확인</label>
						<div class="col-sm-3">
							<input type="password" class="form-control" id="repwd" name="repwd" maxlength="20" placeholder="비밀번호 확인" onkeyup="passConfirm()"> 
							<br>
							<strong><span id ="confirmMsg"></span></strong>
						</div>
					</div>
					
					
					<div class="form-group">
						<label for="name" class="col-sm-3 control-label">이  름</label>
						<div class="col-sm-2">
							<input type="text" class="form-control" id="name" name="name" maxlength="20" placeholder="이름 입력"/>
						</div>
					</div>
					<div class="form-group">
						<label for="postnum" class="col-sm-3 control-label">우편번호</label>
						<div class="col-sm-2">
							<input type = "text" class = "form-control" name = "postnum" id = "postnum" readonly/>
							<input type = "button" class = "form-control postBtn" onclick = "daumZipCode()" value = "우편번호 검색"/>
						</div>
					</div>
					<div class="form-group">
						<label for="address1" class="col-sm-3 control-label">주  소</label>
						<div class="col-sm-5">
							<input type = "text" class = "form-control" id = "address1" name = "address1" readonly/>
						</div>
					</div>
					<div class = "form-group">
						<label for="address2" class = "col-sm-3 control-label">상 세 주 소</label>
						<div class = "col-sm-5">
							<input type = "text" class = "form-control" id = "address2" name = "address2"/>
						</div> 
					</div>
					<div class="form-group">
						<label for="phone" class="col-sm-3 control-label">Phone</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" id="phone" name="phone" placeholder="휴대폰 번호"
							oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" onKeyup="inputPhoneNumber();" maxlength="13"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">Gender</label>
						<div class="col-sm-3">
							<input type='radio' name='gender' value='female' />&nbsp;여성&nbsp;&nbsp;
		  					<input type='radio' name='gender' value='male' checked/>&nbsp;남성
						</div>
					</div>
					<div class="form-group">
						<label for="age" class="col-sm-3 control-label">Birth-Day</label>
						<div class="col-sm-3">
							<input type = "text" class = "form-control" id = "age"  name="age" placeholder = "생년월일을 입력해주세요." readonly/>
						</div>
					</div>
					<!-- 성별  -->
					<div class="form-group">
						<label for="email" class="col-sm-3 control-label">이메일</label>
						<div class="col-sm-5">
							<input type="email" class="form-control" id="email" name="email" maxlength="100" placeholder="이메일 입력"/>
						</div>
					</div>
					<div class="form-group">
						<label for="name" class="col-sm-3 control-label">회원가입 동의</label>
						<div class="col-sm-2">
							<label class="radio-inline">
								<input type="radio" class="radio-value" id="register_Yn" name="register_Yn" value="yes" checked> 동의&nbsp;&nbsp;
							</label>
							<label class="radio-inline">
								<input type="radio" class="radio-value" id="register_Yn" name="register_Yn" value="no"> 동의 안함
							</label>
						</div>
					</div>
				</form>
				<div class="alert alert-info" id="joinInfo">
					<strong>[Andew 쇼핑몰 사용자 개인 정보 수집 및 이용 안내]</strong>
					<h5>
					정보통신망법 제50조와 동법시행령 61조
					<br>
					정보통신망 이용촉진 및 정보보호 등에 관한 법률  제50조(영리목적의 광고성 정보 전송 제한) 
					<br>① 누구든지 전자적 전송매체를 이용하여 영리목적의 광고성 정보를 전송하려면 그 수신자의 명시적인 사전 동의를 받아야 한다. 
					<br>다만, 다음 각 호의 어느 하나에 해당하는 경우에는 사전 동의를 받지 아니한다. <개정 2016. 3. 22., 2020. 6. 9.>
					<br>1. 재화등의 거래관계를 통하여 수신자로부터 직접 연락처를 수집한 자가 대통령령으로 정한 기간 이내에 자신이 처리하고 수신자와 거래한 것과 같은 종류의 재화등에 대한 영리목적의 광고성 정보를 전송하려는 경우
					<br>정보통신망 이용촉진 및 정보보호 등에 관한 법률 시행령 제61조 (영리목적의 광고성 정보 전송기준) ① 법 제50조제1항제1호에서 “대통령령으로 정한 기간”이란 해당 재화등의 거래가 종료된 날부터 6개월을 말한다. <개정 2014. 11. 28.>
					
					</h5>
					<div class="joinCheckbox">
						<label>
							<input type="checkbox" id="agreements" name="agreements" >
						</label> 정보통신망법 제50조와 동법시행령 61조의 개인정보 수집 및 이용에 동의합니다.
					</div>
				</div>
				<div id="joinButtonBox">
					<button type="reset" form="loginForm"  class="btn btn-light">re-enter</button>
					<button type="button" class="btn btn-light cancel">cancel</button>
					<button type="submit" form="loginForm" id="memberFormsubmit" name="checkButton" class="btn btn-light"  >submit</button>
				</div>
			</div>	
			
			<!-- 하단 메뉴바 -->
			<jsp:include page = "../common/footer.jsp" flush = "false"/>
		
			
		</body>
	</html>