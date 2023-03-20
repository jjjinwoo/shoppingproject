<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- 상단 메뉴 -->
<jsp:include page="../common/topMenu.jsp" flush="false"/>

<div class="container">
	<form class="form-horizontal" name="boardRegist">
		<div class="form-group">
			<div class="col-sm-12">
				<h2 align="center">게시글 쓰기</h2>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">제  목</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="qna_subject" name="qna_subject" maxlength="200" placeholder="제목 입력"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">아이디</label>
			<div class="col-sm-3">
				<input type="text" class="form-control" id="userID" name="userID" maxlength="20" placeholder="글쓴이 입력"/>
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

</script>
</body>
</html>