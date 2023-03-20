<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<%	request.setCharacterEncoding("UTF-8"); %>

<!DOCTYPE html>
	<html>
		<head>
			<meta charset="UTF-8">
			<title>게시글 목록 보기</title>
			<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
			<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
			<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
			<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>	
			<link href="${contextPath}/resources/css/board.css" rel="stylesheet" type="text/css">
			<link href="${contextPath}/resources/css/menu.css" rel="stylesheet" type="text/css">
			<script src="${contextPath}/resources/js/board.js"></script>	
			
		</head>
		<body>
			<!-- 로그인 확인용 -->
			<script src="${contextPath}/resources/js/isLogOn.js"></script>
			<div>
				<input type="hidden" id="isLogOnT" value="${isLogOn}"/>
			</div>
			
			<!-- 상단 메뉴 -->
			<jsp:include page="../common/topMenu.jsp" flush="false"/>
			<div id="mainTitle">
				<hr/>
				<div id="boardTitle">
					<a href="/board/boardList?page=1" id="selectedA">Q & A</a>
					<a href="/other/boardListFqa">FQA</a>
				</div>
			</div>
			
			<div class="container">
				<!-- 게시글 작성 버튼 -->
				<div id="registerBtnBox">
					<button type="button" class="btn btn-secondary" onclick="fn_isLogOnQL();"><strong>QnA 작성하기</strong></button>
				</div>
				
				<div>
					<!-- 글 목록 -->
					<table id="boardTable">
						<thead>
							<tr>
								<th class="boardNo">No</th>
						    	<th class="boardTitle">제목</th>
						    	<th class="boardTitle">내용</th>
						    	<th>작성자</th>
						    	<th class="boardTitle">작성일자</th>
						    	<th class="boardNo">조회수</th>
							</tr>
						</thead>
						<tbody>	
							<c:forEach var="board" items="${boardList}">
								<tr>
									<td class="boardNo">${board.qna_bno}</td>
									<td class="boardTitle">
										<a href="/board/boardDetail?qna_bno=${board.qna_bno}">
											<c:choose>
				                                <c:when test="${board.qna_subject.length() > 10}">${board.qna_subject.substring(0, 11)} ⋯</c:when>
				                                <c:otherwise>${board.qna_subject}</c:otherwise>
			                                </c:choose>
										</a>
									</td>
									<td class="boardTitle">
										<c:choose>
			                                <c:when test="${board.qna_content.length() > 15}">${board.qna_content.substring(0, 16)} ⋯</c:when>
			                                <c:otherwise>${board.qna_content}</c:otherwise>
		                                </c:choose>
									</td>
									<td>${board.userID}</td>
									<td class="boardTitle"><fmt:formatDate value="${board.qna_regDate}" pattern="yyyy년 MM월 dd일 a hh시 mm분 ss초"/></td>
									<td class="boardNo"><fmt:formatNumber value="${board.qna_readCount}" pattern="#,###"/></td>
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
								<option value="u" <c:if test="${searchType == 'u'}">selected</c:if>>글쓴이</option>
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
									<a href='<c:url value="/board/boardList?page=${pageMaker.startPage-1}&searchType=${searchType}&keyword=${keyword}"/>'><span class="glyphicon glyphicon-chevron-left"></span></a>
								</li>
							</c:if>
				
							<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="pageNum">
								<li>
									<a href='<c:url value="/board/boardList?page=${pageNum}&searchType=${searchType}&keyword=${keyword}"/>'><i>${pageNum}</i></a>
								</li>
							</c:forEach>
				
							<c:if test="${pageMaker.next}">
								<li>
									<a href='<c:url value="/board/boardList?page=${pageMaker.endPage+1}&searchType=${searchType}&keyword=${keyword}"/>'><span class="glyphicon glyphicon-chevron-right"></span></a>
								</li>
							</c:if>
						</ul>
					</div>
				</div>
			</div>
			
			<!-- 스크립트에서 사용할 hidden 요소 -->
			<form id="formList" action="/board/boardList?page=1" method="get">
				<input type='hidden' name='searchType'	value='${searchType}'/>
				<input type="hidden" name="keyword"		value="${keyword}"/>
			</form>
		
			<!-- 하단 메뉴바 -->
			<jsp:include page = "../common/footer.jsp" flush = "false"/>
		</body>
	</html>









