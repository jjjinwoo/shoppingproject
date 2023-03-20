<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%	request.setCharacterEncoding("UTF-8"); %>

<!DOCTYPE html>
	<html>
		<head>
			<meta charset="UTF-8">
			<title>리뷰 등록 화면</title>
			<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
			<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
			<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
			<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
			<script src="${contextPath}/resources/js/review.js"></script>	
			<link href="${contextPath}/resources/css/menu.css" rel="stylesheet" type="text/css">
			<link href="${contextPath}/resources/css/board.css" rel="stylesheet" type="text/css">
		</head>
		<body>
			<!-- 상단 메뉴 -->
			<jsp:include page="../common/topMenu.jsp" flush="false"/>
			<div id="mainTitle">
				<hr/>
				<h1>리뷰 작성</h1>
			</div>
			
			<div class="container">
				<div id="boardDetailListA">
					<a href="/order/orderCompleteDetail?order_num=${orderDetail.order_num}">주문 내역 보기</a>
				</div>
				
				<table id="boardDetailTable">
					<tr>
						<td colspan="2">
							<div id="reviewSubjectBox">
								제   목 <input type="text" class="form-control" id="review_subject" name="review_subject" maxlength="200" placeholder="제목 입력"/>
							</div>						
							<div id="reviewStarBox">
								별   점
								<select id="review_star" class="form-control">
									<option disabled selected>별 점</option>
									<option value="1">★</option>
									<option value="2">★★</option>
									<option value="3">★★★</option>
									<option value="4">★★★★</option>
									<option value="5">★★★★★</option>
								</select>
							</div>
						</td>
					</tr>
					<tr id="reviewOPDetail">
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
							<div>
								<label class="control-label">내   용</label>
								<textarea class="form-control" id="review_content" name="review_content" rows="10" cols="160"  placeholder="내용 입력"></textarea>
							</div>
						</td>
					</tr>
				</table>
				
				<div id="boardDetailBtn">
					<button type="reset"  class="btn btn-light">다시 입력</button>
					<button type="button" class="btn btn-light" onclick="fn_reviewRegister('${orderDetail.product_code}');">리뷰 등록</button>
				</div>
			
			</div>
			
			<!-- script에서 쓸 hidden input -->
			<div>
				<input type="hidden" id="userID" value="${member1.userID}"/>
				<input type="hidden" id="orderNum" value="${orderDetail.order_num}"/>
			</div>
			<!-- 하단 메뉴 -->
			<jsp:include page="../common/footer.jsp" flush="false"/>
		</body>
	</html>