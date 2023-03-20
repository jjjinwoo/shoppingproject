<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%	request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
	<html>
		<head>
			<meta charset="UTF-8">
			<title>상품 검색 결과 페이지</title>
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
            <c:forEach items="${type}" var="type">
               <c:choose>
                  <c:when test="${type == 'top' }"> 
                     <h1><img src="${contextPath}../resources/images/top.png" style = "width: 40px; height: 40px;"/>&nbsp;&nbsp;${title}</h1>
                  </c:when>
               </c:choose>
               <c:choose>
                  <c:when test="${type == 'bottom' }"> 
                     <h1><img src="${contextPath}../resources/images/bottom.png" style = "width: 40px; height: 40px;"/>&nbsp;&nbsp;${title}</h1>
                  </c:when>
               </c:choose>
               <c:choose>
                  <c:when test="${type == 'outer' }"> 
                     <h1><img src="${contextPath}../resources/images/outer.png" style = "width: 40px; height: 40px;"/>&nbsp;&nbsp;${title}</h1>
                  </c:when>
               </c:choose>
               <c:choose>
                  <c:when test="${type == 'acc' }"> 
                     <h1><img src="${contextPath}../resources/images/acc.png" style = "width: 50px; height: 50px;"/>&nbsp;&nbsp;${title}</h1>
                  </c:when>
               </c:choose>
            </c:forEach>
         </div>   	
				
			<!-- 상품 정렬 -->
			<div class = "arraybox">	
				<div class = "listbox">
					<select class = "form-control" id="arrayOptionS">
						<option value = "r" <c:if test="${array_type=='r'}">selected</c:if>>최신순</option>
						<option value = "o" <c:if test="${array_type=='o'}">selected</c:if>>오래된 순</option>
						<option value = "p" <c:if test="${array_type=='p'}">selected</c:if>>인기순</option>
					</select>
				</div>	
			</div>
			
			<br/>
			<br/>
			
			<!-- 상품 출력 -->
			<div class = "container producbox">
				<!-- 검색된 상품이 없을 때 -->
				<c:if test="${productList[0].product_code eq null}">
					<div>
						<h4>'${keyword}'에 해당하는 상품이 없습니다.</h4>
					</div>
				</c:if>
			
				<table>
					<tr class="productList">
						<c:forEach items="${productList}" var="product" varStatus="status">
							<c:choose>
								<c:when test="${status.count % 4 != 0 and !status.last}">
									<td>
										<div class = "pl" style = "white-space: nowrap; overflow: hidden; text-overflow: ellipsis; width: 300px;">
										<a href="/product/productDetail?product_code=${product.product_code}&reviewPageNum=1"><img src = "${contextPath }/image/displayImage?name=${product.product_code}" /></a><br/><br/>
										<a href="/product/productDetail?product_code=${product.product_code}&reviewPageNum=1"><strong>상 품 명 : ${product.product_name}</strong></a><br/>
										<a href="/product/productDetail?product_code=${product.product_code}&reviewPageNum=1">가  격 : <fmt:formatNumber value="${product.product_price}" pattern="#,###원"/></a><br/>
										<a href="/product/productDetail?product_code=${product.product_code}&reviewPageNum=1" > 
			                              <c:choose>
			                              <c:when test="${product.star_avg == 0 }">
			                                 아직 구매 후기가 없어요!
			                              </c:when>
			                              <c:otherwise>
			                                 <c:choose>
			                                    <c:when test="${product.star_avg / 1 != 0}">
			                                       <c:forEach begin="1" end="${product.star_avg / 1}">
			                                          <span class="fa fa-star checked"></span>
			                                       </c:forEach>
			                                    </c:when>
			                                 </c:choose>
			                                 <c:choose>
			                                    <c:when test="${product.star_avg % 1 != 0}">
			                                       <span class="fa fa-star-half-o checked"> </span>
			                                    </c:when>
			                                 </c:choose>
			                                 <c:choose>
			                                 <c:when test="${product.star_avg / 1 != 0}">
			                                    <c:forEach begin="1" end="${5-(product.star_avg / 1)}">
			                                          <span class="fa fa-star-o" style="color: orange;"></span>
			                                       </c:forEach>
			                                    </c:when>   
			                                 </c:choose>
			                                 <strong>${product.star_avg }</strong>
			                              </c:otherwise>
			                              </c:choose>
			                              </a> 					
										</div>
									</td>
								</c:when>
							</c:choose>
							<!-- 4개씩 줄바꿈 해준다. -->
							<c:choose>
								<c:when test="${status.count % 4 == 0 and !status.last}">
										<td>
											<div class = "pl" style = "white-space: nowrap; overflow: hidden; text-overflow: ellipsis; width: 300px;">
										<a href="/product/productDetail?product_code=${product.product_code}&reviewPageNum=1"><img src = "${contextPath }/image/displayImage?name=${product.product_code}" /></a><br/><br/>
										<a href="/product/productDetail?product_code=${product.product_code}&reviewPageNum=1"><strong>상 품 명 : ${product.product_name}</strong></a><br/>
										<a href="/product/productDetail?product_code=${product.product_code}&reviewPageNum=1">가  격 : <fmt:formatNumber value="${product.product_price}" pattern="#,###원"/></a><br/>
										<a href="/product/productDetail?product_code=${product.product_code}&reviewPageNum=1" > 
			                              <c:choose>
			                              <c:when test="${product.star_avg == 0 }">
			                                 아직 구매 후기가 없어요!
			                              </c:when>
			                              <c:otherwise>
			                                 <c:choose>
			                                    <c:when test="${product.star_avg / 1 != 0}">
			                                       <c:forEach begin="1" end="${product.star_avg / 1}">
			                                          <span class="fa fa-star checked"></span>
			                                       </c:forEach>
			                                    </c:when>
			                                 </c:choose>
			                                 <c:choose>
			                                    <c:when test="${product.star_avg % 1 != 0}">
			                                       <span class="fa fa-star-half-o checked"> </span>
			                                    </c:when>
			                                 </c:choose>
			                                 <c:choose>
			                                 <c:when test="${product.star_avg / 1 != 0}">
			                                    <c:forEach begin="1" end="${5-(product.star_avg / 1)}">
			                                          <span class="fa fa-star-o" style="color: orange;"></span>
			                                       </c:forEach>
			                                    </c:when>   
			                                 </c:choose>
			                                 <strong>${product.star_avg }</strong>
			                              </c:otherwise>
			                              </c:choose>
			                              </a> 					
										</div>	
										</td>
									</tr>
									<tr class="productList">					
								</c:when> 
							</c:choose>
							<c:choose>
								<c:when test="${status.last}">
										<td>
											<div class = "pl" style = "white-space: nowrap; overflow: hidden; text-overflow: ellipsis; width: 300px;">
										<a href="/product/productDetail?product_code=${product.product_code}&reviewPageNum=1"><img src = "${contextPath }/image/displayImage?name=${product.product_code}" /></a><br/><br/>
										<a href="/product/productDetail?product_code=${product.product_code}&reviewPageNum=1"><strong>상 품 명 : ${product.product_name}</strong></a><br/>
										<a href="/product/productDetail?product_code=${product.product_code}&reviewPageNum=1">가  격 : <fmt:formatNumber value="${product.product_price}" pattern="#,###원"/></a><br/>
										<a href="/product/productDetail?product_code=${product.product_code}&reviewPageNum=1" > 
			                              <c:choose>
			                              <c:when test="${product.star_avg == 0 }">
			                                 아직 구매 후기가 없어요!
			                              </c:when>
			                              <c:otherwise>
			                                 <c:choose>
			                                    <c:when test="${product.star_avg / 1 != 0}">
			                                       <c:forEach begin="1" end="${product.star_avg / 1}">
			                                          <span class="fa fa-star checked"></span>
			                                       </c:forEach>
			                                    </c:when>
			                                 </c:choose>
			                                 <c:choose>
			                                    <c:when test="${product.star_avg % 1 != 0}">
			                                       <span class="fa fa-star-half-o checked"> </span>
			                                    </c:when>
			                                 </c:choose>
			                                 <c:choose>
			                                 <c:when test="${product.star_avg / 1 != 0}">
			                                    <c:forEach begin="1" end="${5-(product.star_avg / 1)}">
			                                          <span class="fa fa-star-o" style="color: orange;"></span>
			                                       </c:forEach>
			                                    </c:when>   
			                                 </c:choose>
			                                 <strong>${product.star_avg }</strong>
			                              </c:otherwise>
			                              </c:choose>
			                              </a> 					
										</div>
										</td>
									</tr>
								</c:when>
							</c:choose>
						</c:forEach>
				</table>
			</div>
			
			<div><!-- 상품 타입을 받아서 script에서 사용하기 위한 input -->
				<input type="hidden" id="ptype" value="${type}"/>
				<input type="hidden" id="keyword" value="${keyword}"/>
			</div>
			
			<!-- 하단 메뉴바 -->
			<jsp:include page = "../common/footer.jsp" flush = "false"/>	
				
		</body>
	</html>