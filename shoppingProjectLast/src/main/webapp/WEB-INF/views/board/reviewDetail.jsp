<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<%	request.setCharacterEncoding("UTF-8"); %>

<!DOCTYPE html>
	<html>
		<head>
			<meta charset="UTF-8">
			<title>게시글 상세 정보</title>
			<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
			<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
			<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
			<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>	
			<link href="${contextPath}/resources/css/menu.css" rel="stylesheet" type="text/css">
			<link href="${contextPath}/resources/css/board.css" rel="stylesheet" type="text/css">
			<script src="${contextPath}/resources/js/review.js"></script>		
		</head>
		<body>
			<!-- 상단 메뉴 -->
			<jsp:include page="../common/topMenu.jsp" flush="false"/>
			<div id="mainTitle">
				<hr/>
				<h1>작성한 리뷰</h1>
			</div>	
			
			<div class="container">
				<div id="boardDetailListA">
					<a href="/order/orderCompleteDetail?order_num=${orderDetail.order_num}">주문 내역 보기</a>
				</div>
				
				<table id="boardDetailTable">
					<tr>
						<th colspan="2">
							${reviewDTO.review_subject}
						</th>
					</tr>
					<tr>
						<td colspan="2">
							<!-- 관리자가 아니면 회원 아이디는 익명 처리해준다. -->
							<c:choose>
								<c:when test="${member1.grade == 7}">
									<a href="#">작성자 ${reviewDTO.userID} |</a>
								</c:when>
								<c:otherwise>
									<p>작성자 ${reviewDTO.userID.substring(0, 2)}****** |</p>
								</c:otherwise>
							</c:choose>
							<p><fmt:formatDate value='${reviewDTO.review_date}' pattern='yyyy.MM.dd hh:mm'/></p>
							<p>| 별점: ${reviewDTO.review_star }
						</td>
					</tr>
					<tr>
						<td id="reviewDetailOPImg">
							<img src="/image/displayImage?name=${orderDetail.product_code}"/>
						</td>
						<td id="reviewDetailOP">
							<a href="/product/productDetail?product_code=${orderDetail.product_code}">${orderDetail.product_name}</a>
							<div>	
								<p>옵션: ${orderDetail.product_size}</p>
								<p>수량: ${orderDetail.product_amount}</p>
							</div>	
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<textarea rows="10" cols="160" class="form-control" id="review_content" name="qna_content" readonly>
								<c:choose>
									<c:when test="${reviewDTO.review_content eq null}">삭제된 리뷰입니다.</c:when>
									<c:otherwise>${reviewDTO.review_content}</c:otherwise>		
								</c:choose>	
							</textarea>
						</td>
					</tr>
				</table>
				
				<div id="boardDetailBtn">
					<!-- 작성자와 로그인한 사람의 아이디가 같거나 관리자일 때 나오는 부분 -->
					<c:choose>
						<c:when test="${reviewDTO.userID == member1.userID or member1.grade == 7}">
							<button type="button" class="btn btn-light" onclick="location.href = '/review/reiviewUpdateForm?review_bno=' + ${reviewDTO.review_bno}">
								<span class="glyphicon glyphicon-pencil"> 리뷰 수정</span>
							</button>
							<button type="button" class="btn btn-light" onclick="fn_reviewDelete(${reviewDTO.review_bno})">
								<span class="glyphicon glyphicon-trash"> 리뷰 삭제</span>
							</button>
						</c:when>
					</c:choose>
				</div>
			</div>
			
			<jsp:include page="../common/footer.jsp" flush="false"/>
		
		</body>
	</html>






