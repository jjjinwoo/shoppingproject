<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<%	request.setCharacterEncoding("UTF-8"); %>

<!DOCTYPE html>
	<html>
		<head>
			<meta charset="UTF-8">
			<title>게시글 목록</title>
			<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
			<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
			<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
			<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
			<script src="${contextPath}/resources/js/review.js"></script>	
			<link href="${contextPath}/resources/css/board.css" rel="stylesheet" type="text/css">
			<link href="${contextPath}/resources/css/menu.css" rel="stylesheet" type="text/css">
		</head>
		<body>
			<!-- 상단 메뉴 -->
			<jsp:include page="../common/topMenu.jsp" flush="false"/>
			<div id="mainTitle">
				<hr/>
				<div id="boardTitle">
					<a href="/board/boardList?page=1">Q & A</a>
					<a href="/review/reviewList?page=1" id="selectedA">Review</a>
					<a href="/other/boardListFqa">FQA</a>
				</div>
				<hr/>
			</div>
			
			<div class="container">
				<!-- 글 목록 -->
				<table id="boardTable">
					<thead>
						<tr>
							<th class="boardNo">No</th>
					    	<th class="boardTitle">제목</th>
					    	<th class="boardTitle">내용</th>
					    	<th>작성자</th>
					    	<th class="boardTitle">작성일자</th>
					    	<th class="boardNo">별점</th>
						</tr>
					</thead>
					<tbody>	
						<c:forEach var="list" items="${reviewList}">
							<tr>
								<td class="boardNo">${list.review_bno}</td>
								<td class="boardTitle"><a href="/review/reviewDetail?review_bno=${list.review_bno}">${list.review_subject}</a></td>
								<td class="boardTitle">${list.review_content}</td>
								<td>${list.userID}</td>
								<td class="boardTitle"><fmt:formatDate value="${list.review_date}" pattern="yyyy년 MM월 dd일 a hh시 mm분 ss초" /></td>
								<td class="boardNo">${list.review_star}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				
				<!-- 검색종류  -->
				<div id="boardSearchBox">
					<div>
						<select id="searchType" class="form-control">
							<option disabled selected>검색종류</option>
							<option value="s" <c:if test="${searchType == 's'}">selected</c:if>>제목</option>
							<option value="c" <c:if test="${searchType == 'c'}">selected</c:if>>내용</option>
							<option value="w" <c:if test="${searchType == 'w'}">selected</c:if>>글쓴이</option>
						</select>
					</div>
					<div id="boardSearchBoxDiv">
						<input type="text" id="searchKeyword" value="${keyword}"/>
						<button id="searchBtn" class="glyphicon glyphicon-search"></button>
					</div>
				</div>
				
				<br/>
				<div class="col-sm-12" align="center">
					<ul class="btn-group pagination">
						<c:if test="${pageMaker.prev}">
							<li>
								<a href='<c:url value="/review/reviewList?page=${pageMakerf.startPage-1}&searchType=${searchType}&keyword=${keyword}"/>'><span class="glyphicon glyphicon-chevron-left"></span></a>
							</li>
						</c:if>
			
						<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="pageNum">
							<li>
								<a href='<c:url value="/review/reviewList?page=${pageNum}&searchType=${searchType}&keyword=${keyword}"/>'><i>${pageNum}</i></a>
							</li>
						</c:forEach>
			
						<c:if test="${pageMaker.next}">
							<li>
								<a href='<c:url value="/review/reviewList?page=${pageMaker.endPage+1}&searchType=${searchType}&keyword=${keyword}"/>'><span class="glyphicon glyphicon-chevron-right"></span></a>
							</li>
						</c:if>
					</ul>
				</div>
			</div>
			
			<!-- 스크립트에서 사용할 hidden 요소 -->
			<form id="formList" action="/review/reviewList" method="get">
				<input type='hidden' name='searchType'	value='${searchType}'/>
				<input type="hidden" name="keyword"		value="${keyword}"/>
			</form>
			
			
			<!-- 하단 메뉴 -->
			<jsp:include page="../common/footer.jsp" flush="false"/>
		</body>
	</html>