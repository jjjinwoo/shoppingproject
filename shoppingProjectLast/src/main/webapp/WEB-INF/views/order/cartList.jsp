<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var = "contextPath" value = "${pageContext.request.contextPath }"/>
<!DOCTYPE html>
	<html>
		<head>
			<meta charset="UTF-8">
			<title>장바구니 목록</title>
			<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
			<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
			<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
			<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>	
			<link href="${contextPath}/resources/css/menu.css" rel="stylesheet" type="text/css">	
			<link href="${contextPath}/resources/css/order.css" rel="stylesheet" type="text/css">
			<script src="${contextPath}/resources/js/order.js"></script>	
		</head>
		<body>
	
			<!-- 상단 메뉴 -->
			<jsp:include page="../common/topMenu.jsp" flush="false"/>
			<div id="mainTitle">
				<hr/>
				<h1>장바구니</h1>
			</div>	
			
			<div class = "container">
				<!-- 장바구니 삭제 버튼 -->
				<div id="cartDeleteBtn">
					<button type="button" id="orderDeleteBtn" onclick="fn_cartDelete();">선택삭제</button>
					<button type="button" id="orderAllDeleteBtn" onclick="fn_cartAllDelete();">전체삭제</button>
				</div>
				<!-- 주문 상품 정보 -->
				<table class="orderTable" id="orderTableProduct">
					<thead>
						<tr id="orderTableProductTh">
							<th><input type="checkbox" id="checkAll" style = "accent-color: #3c3c3c;" checked /></th>
							<th colspan="2" id="orderTableProductName">상품/옵션 정보</th>
							<th>수량</th>
							<th>상품 가격</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${cartList}" var="list">
							<tr>
								<td>
									<input type="checkbox" class="cartCheckbox" name="cart" value="${list.cart_num}" style = "accent-color: #3c3c3c;" checked/>
								</td>
								<td>
									<img src="/image/displayImage?name=${list.product_code}"/>
								</td>
								<td class="orderTableProductNameTd">
									<a href="/product/productDetail?product_code=${list.product_code}&reviewPageNum=1">${list.product_name}</a>
									<div>
										<c:choose>
											<c:when test="${list.product_size == 'free'}">
												<select id="cartProduct_size" onchange="fn_updateProduct_size(this.value, ${list.cart_num});">
													<option disabled selected>S I Z E</option>
													<option value="free" <c:if test="${list.product_size=='free'}">selected</c:if>>F R E E</option>
												</select>
											</c:when>
											<c:otherwise>
												<select id="cartProduct_size" onchange="fn_updateProduct_size(this.value, ${list.cart_num});">
													<option disabled selected>S I Z E</option>
													<option value="s" <c:if test="${list.product_size=='s'}">selected</c:if>>S</option>
													<option value="m" <c:if test="${list.product_size=='m'}">selected</c:if>>M</option>
													<option value="l" <c:if test="${list.product_size=='l'}">selected</c:if>>L</option>
													<option value="xl" <c:if test="${list.product_size=='xl'}">selected</c:if>>XL</option>
												</select>
											</c:otherwise>
										</c:choose>
									</div>
								</td>
								<td>
									<div id="changAmountBtnBox">
										<c:choose>
											<c:when test="${list.product_amount == 1}">
												<div style="visibility: hidden;">
													<input type='button' id="amountMinus" value="━" readonly>
												</div>	
											</c:when>
											<c:otherwise>
												<input type='button' id="amountMinus" onclick="fn_amountMinus(${list.product_amount}, ${list.cart_num});" value="━">
											</c:otherwise>
										</c:choose>
										<p id="cartProduct_amount"><strong>${list.product_amount}</strong></p>
										<input type="button" id="amountPlus" onclick="fn_amountPlus(${list.product_amount}, ${list.cart_num});" value="╋"/>
									</div>
								</td>
								<td>
									<fmt:formatNumber value="${list.product_price}" pattern="#,###원"/>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<!-- 최종 결제 금액 정보 -->
				<table class="orderTable" id="orderTableBill">
					<tr>
						<td>
							<table>
								<tr>
									<td>총 상품 금액</td>
									<td class="orderTableTotalMoney" id="orderSum"><fmt:formatNumber value="${orderSum}" pattern="#,###원"/></td>
								</tr>
							</table>
						</td>
						<td>
							<table>
								<tr>
									<td>총 할인 금액</td>
									<td class="orderTableTotalMoney" id="discount"><fmt:formatNumber value="0" pattern="#,###원"/></td>
								</tr>
							</table>
						</td>
						<td>
							<table>
								<tr>
									<td>총 배송비</td>
									<td class="orderTableTotalMoney" id="deliveryFee">
										<c:choose>
											<c:when test="${orderSum > 50000}">
												<fmt:formatNumber value="0" pattern="#,###원"/>
											</c:when>
											<c:otherwise>
												<fmt:formatNumber value="5000" pattern="#,###원"/>
											</c:otherwise>
										</c:choose>
									</td>
								</tr>
							</table>
						</td>
						<td>
							<table>
								<tr>
									<td>결제 예정 금액</td>
									<td class="orderTableTotalMoney" id="totalBillTd"></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<!-- 최종 주문 버튼 -->
				<div id="cartListBtn">
					<button type="button" class="btn btn-light" id="orderSelectedBtn">선택상품 주문하기</button>
					<button type="button" class="btn btn-light" id="orderTotalBtn">전체상품 주문하기</button>
				</div>
			
			</div>
			<!-- 스크립트에서 사용할 hidden input -->
			<div>
				<input type="hidden" id="cartListUserID" value="${member1.userID}"/>
			</div>
	
			<jsp:include page="../common/footer.jsp" flush="false"/>
			
		</body>
	</html>