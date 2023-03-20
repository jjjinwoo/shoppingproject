<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<%	request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
	<html>
		<head>
			<meta charset="UTF-8">
			<title>게시글 등록 화면</title>
			<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
			<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
			<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
			<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>	
			<link href="${contextPath}/resources/css/menu.css" rel="stylesheet" type="text/css">
			<link href="${contextPath}/resources/css/board.css" rel="stylesheet" type="text/css">
			<script src="${contextPath}/resources/js/board.js"></script>	
		</head>
		<body>
			<!-- 상단 메뉴 -->
			<jsp:include page="../common/topMenu.jsp" flush="false"/>
			<hr/><br/>
			<h2 style = "text-align: center;">Q & A RegisterForm</h2>
			<br/>
			<br/>
			<div class="container">

				<!-- 게시글 작성 부분 -->
				<form class="form-horizontal">
					<div class="form-group">
						<label class="col-sm-1 control-label">제  목</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="qna_subject" name="qna_subject" maxlength="200" placeholder="제목 입력"/>
						</div>
						<label class="col-sm-1 control-label">아이디</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" id="userID" name="userID" maxlength="20" value = "${member1.userID }" readonly/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-1 control-label">내  용</label>
						<div class="col-sm-10">
							<textarea class="form-control" id="qna_content" name="qna_content" rows="10" cols="160"  placeholder="내용 입력"></textarea>
						</div>
					</div>
					<br/>
					<div class="form-group">
						<div id="registerFormBtnDiv">
							<button type="button" class="btn btn-light" onclick="location.href='/board/boardList?page=1'" style = "width: 80px;"><strong>Back</strong></button>
							<button type="reset"  class="btn btn-light"><strong>다시 입력</strong></button>
							<button type="button" class="btn btn-light" onclick="fn_boardRegister();"><strong>게시글 등록</strong></button>
						</div>
					</div>
				</form>
			</div>
			
			 <!-- 하단 메뉴바 -->
			<jsp:include page = "../common/footer.jsp" flush = "false"/>
		</body>
	</html>
	
	
	
	
	
	
	
	
	
	
	
