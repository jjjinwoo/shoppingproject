<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%	request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
	<html>
		<head>
			<meta charset="UTF-8">
			<title>상품 검색 페이지</title>
			<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
			<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
			<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
			<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>	
			<link href="${contextPath}/resources/css/menu.css" rel="stylesheet" type="text/css">	
			<link href="${contextPath}/resources/css/product.css" rel="stylesheet" type="text/css">
			<script src="${contextPath}/resources/js/product.js"></script>		
		</head>
		
		<body>
			<!-- 상단 메뉴 -->
			<jsp:include page="../common/topMenu.jsp" flush="false"/>
			
			<div id="mainTitle">
				<hr/>
				<h1 id="searchTitle"><span class="glyphicon glyphicon-search"></span>&nbsp;Search</h1>
			</div>
			
			<div class="container">
				<div id="searchSubTitle">
					<h4>검색 종류에 해당하는 상품의 이름을 검색해주세요.</h4>
				</div>
				<!--  -->
				
				<!-- 검색 영역 -->
				<div id="searchBox">
					<div>
						<select id="searchType">
							<option value="none">검색종류</option>
							<option value="top">Top</option>
							<option value="bottom">Bottom</option>
							<option value="outer">Outer</option>
							<option value="acc">Acc</option>
						</select>
					</div>
					<div>
						<input type="text" id="searchKeyword"/>
						<button type="button" id="searchBtn" class = "glyphicon glyphicon-search"></button>
					</div>
				</div>
				<hr/>
				<!-- 추천 상품 -->
				<div>
					<h3 style="text-align:center;"><img src="${contextPath}../resources/images/crown.png" style = "width: 40px; height: 40px;"/><strong>&nbsp;&nbsp;추천 상품</strong></h3>
				</div>
				
				<div id="recommendOption">
					<!-- 추천 상품 타입  -->
					<div>
						<select class = "form-control" id="typeOption">
							<option disabled selected>추천상품타입</option>
							<option value="top" <c:if test="${type=='top'}">selected</c:if>>TOP</option>
							<option value="bottom" <c:if test="${type=='bottom'}">selected</c:if>>BOTTOM</option>
							<option value="outer" <c:if test="${type=='outer'}">selected</c:if>>OUTER</option>
							<option value="acc" <c:if test="${type=='acc'}">selected</c:if>>ACC</option>
						</select>
					</div>
					<div id="recommendBlank"></div>
					<!-- 정렬 조건 -->
					<div>
						<select class = "form-control" id="arrayOptionR">
							<option value = "r" <c:if test="${array_type=='r'}">selected</c:if>>최신순</option>
							<option value = "o" <c:if test="${array_type=='o'}">selected</c:if>>오래된 순</option>
							<option value = "p" <c:if test="${array_type=='p'}">selected</c:if>>인기순</option>
						</select>
					</div>	
				</div>
				
				<!-- 추천 상품 나열 슬라이드-->
				<!-- 가로1열로 상품 나열 하기 -->
				<table id="recommendSlide">
					<tr>
						<c:forEach items="${productList}" var="product" varStatus="status">
					    	<c:choose>
					    		<c:when test="${status.count % 3 == 1}">
									<td>
										<div id="myCarousel" class="carousel slide" data-ride="carousel">
										    <div class="carousel-inner">
								    			<div class="item active" >
							        				<a href="/product/productDetail?product_code=${product.product_code}&reviewPageNum=1" class="imgblock"><img src = "/image/displayImage?name=${product.product_code}" /></a>	
							      				</div>
								    		</c:when>
								    	</c:choose>
								    	<c:choose>
								    		<c:when test="${status.count % 3 == 2}">
								    			<div class="item" >
							        				<a href="/product/productDetail?product_code=${product.product_code}&reviewPageNum=1" class="imgblock"><img src = "/image/displayImage?name=${product.product_code}" /></a>	
							      				</div>
								    		</c:when>
								    	</c:choose>
								    	<c:choose>
								    		<c:when test="${status.count % 3 == 0}">
								    			<div class="item" >
							        				<a href="/product/productDetail?product_code=${product.product_code}&reviewPageNum=1" class="imgblock"><img src = "/image/displayImage?name=${product.product_code}" /></a>	
							      				</div>
									      	</div>		
										</div>
									</td>			
								</c:when>
							</c:choose>
						</c:forEach>
					</tr>
				</table>
			</div>	
			
			
				
				<!-- 검색 조건 정보를 보내주기 위한 hidden 요소 -->
				<form id="formList" action="/product/productTypeList" method="get">
					<input type='hidden' name='searchType'/>
					<input type="hidden" name="keyword"	/>
					<input type="hidden" name="page"	/>
					<input type="hidden" name="array_type"	/>
					<input type="hidden" name="product_type"	/>
				</form>
				
			<!-- 하단 메뉴바 -->
			<jsp:include page = "../common/footer.jsp" flush = "false"/>	
		</body>
	</html>