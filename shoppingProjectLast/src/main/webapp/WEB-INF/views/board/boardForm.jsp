<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%	request.setCharacterEncoding("UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>게시글 등록 화면</title>
	<script src="${contextPath}/resources/js/board.js"></script>
</head>
<body>

<!-- 상단 메뉴 -->
<jsp:include page="../common/topMenu.jsp" flush="false"/>

<div class="container">
	<form class="form-horizontal" name="boardForm" id=" boardForm" method="post" action="/board/boardRegist" > <!-- id="boardForm" method="post" action="/board/boardRegist" -->
		<div class="form-group">
			<div class="col-sm-12">
				<h2 align="center">게시판</h2>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">아이디</label>
			<div class="col-sm-3">
				<input type="text" class="form-control" id="userID" name="userID" maxlength="20" placeholder="아이디 입력"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">제  목</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="qna_subject" name="qna_subject" maxlength="200" placeholder="제목 입력"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">내  용</label>
			<div class="col-sm-8">
				<textarea class="form-control" id="qna_content" name="qna_content" rows="10" cols="160"  placeholder="내용 입력"></textarea>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-4 left">
				<button type="reset"  class="btn btn-warning">다시 입력</button>
				<button type="button" class="btn btn-primary" onclick="fn_boardRegist();">게시글 등록</button>
			</div>
		</div>
	</form>
</div>


<script>
function fn_boardRegist() {
	
	//alert("게시글 등록 버튼을 눌렀습니다.");

	// 게시글 등록 화면에서 입력한 값을 가져온다.
	let	subject	= $("#qna_subject").val();
	let	userID	= $("#userID").val();
	let	content	= $("#qna_content").val();
	
	//alert(subject + ":" + userID + ":" + content);
	
	// 제목 항목에 값이 없으면 입력하도록 한다.
	if($("#qna_subject").val() == "") {
		alert("제목은 필수 입력 항목입니다.");
		$("#qna_subject").focus();
		return false;
	}
	
	// 글쓴이 항목에 값이 없으면 입력하도록 한다.
	if($("#userID").val() == "") {
		alert("아이디는 필수 입력 항목입니다.");
		$("#userID").focus();
		return false;
	}
	
	// 내용 항목에 값이 없으면 입력하도록 한다.
	if($("#qna_content").val() == "") {
		alert("내용은 필수 입력 항목입니다.");
		$("#qna_content").focus();
		return false;
	}
	
   
    
	$.ajax({
		type:		"post",
		url:		"/board/boardRegist",
		data:		{qna_subject:subject, userID:userID, qna_content:content},
		success:	function(data) {
			if(data == "Y") {
				alert("게시글을 등록하였습니다.");
				// 게시글 등록이 완료되면, 게시글 목록 화면으로 이동한다.
				location.href = "/board/boardList";
			}
		},
		error:		function(data) {
			alert("게시글을 등록하는데 실패하였습니다!");
		}
	});
    
	 
} // End - function fn_boardRegister()


</script>

</body>
</html>








