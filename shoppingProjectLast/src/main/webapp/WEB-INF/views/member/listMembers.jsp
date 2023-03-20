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

		</head>
	<body>
		<!-- 상단 메뉴 -->
		<jsp:include page ="../common/topMenu.jsp"></jsp:include>
		<div id="mainTitle">
			<hr/>
			<h1>회원 전체 목록</h1>
		</div>
		
		<!-- 내용 -->
		<div class="container">
			<table class="table table-hover" id="memberListTable">
				<thead>
					<tr>
						<th><b>아이디</b></th>
						<th><b>비밀번호</b></th>
						<th><b>이  름</b></th>
						<th><b>나	이</b></th>
						<th><b>성	별</b></th>
						<th><b>이메일</b></th>
						<th><b>가입일자</b></th>
						<th><b>수정</b></th>
						<th><b>삭제 (Yes/No)</b></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="member" items="${memberLists}">
					<tr>
						<td>${member.userID}</td>
						<td>${member.pwd}</td>
						<td>${member.name}</td>
						<td>${member.age}</td>
						<td>${member.gender}</td>
						<td>${member.email}</td>
						<td>${member.joinDate}<input type="hidden" name="grade"		value="${member.grade}"/></td>	
						<td><button type="button" id="listUpdateBtn" class="btn btn-sm" onclick="location.href='/member/updateMemberForm.do?userID=${member.userID}'" >수정</button></td>
						<td><button type="button" id="listRemoveBtn" class="btn btn-sm" onclick="fn_removeMember('${member.userID}');" >삭제 (Yes/No)</button></td>
					</tr>
					</c:forEach>	
				</tbody>
			</table>
			
			<table id="membaerListSearchTable">
				<tr>
					<td>
						<div>
							<select id="searchType" class="form-control">
								<option value="not">검색종류</option>
								<option value="s" <c:if test="${searchType == 's'}">selected</c:if>>아이디</option>					
								<option value="a" <c:if test="${searchType == 'a'}">selected</c:if>>나 이</option>					
								<option value="g" <c:if test="${searchType == 'g'}">selected</c:if>>성 별</option>					
							</select>
						</div>
						<div>	
							<input type="text" id="searchKeyword" value="${keyword}"/>
							<button id="searchBtn" class = "glyphicon glyphicon-search"></button>
						</div>
					</td>
				</tr>
				
				<tr>
					<td>
						<ul class="btn-group pagination">
							<c:if test="${pageMaker.prev}">
								<li>
									<a href='<c:url value="/member/listMembers.do?page=${pageMaker.startPage-1}&searchType=${searchType}&keyword=${keyword}"/>'><span class="glyphicon glyphicon-chevron-left"></span></a>
								</li>
							</c:if>
				
							<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="pageNum">
								<li>
									<a href='<c:url value="/member/listMembers.do?page=${pageNum}&searchType=${searchType}&keyword=${keyword}"/>'><i>${pageNum}</i></a>
								</li>
							</c:forEach>
				
							<c:if test="${pageMaker.next}">
								<li>
									<a href='<c:url value="/member/listMembers.do?page=${pageMaker.endPage+1}&searchType=${searchType}&keyword=${keyword}"/>'><span class="glyphicon glyphicon-chevron-right"></span></a>
								</li>
							</c:if>
						</ul>
					</td>
				</tr>
			</table>
			
			<!-- 스크립트에서 사용하는 hidden 요소 -->
			<form id="formList" action="/member/listMembers.do" method="get">
				<input type='hidden' name='searchType'	value='${searchType}'/>
				<input type="hidden" name="keyword"		value="${keyword}"/>
			</form>
		</div>
		
		<!-- 하단 메뉴바 -->
		<jsp:include page = "../common/footer.jsp" flush = "false"/>
		
	</body>
</html>





