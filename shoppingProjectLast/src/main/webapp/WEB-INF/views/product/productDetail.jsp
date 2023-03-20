<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<c:set var = "contextPath" value = "${pageContext.request.contextPath }"/>
<c:set var = "result" value = "${param.result }"/>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<% request.setCharacterEncoding("UTF-8"); %>

<!DOCTYPE html>
	<html>
		<head>
			<meta charset="UTF-8">
			<title>${productDTO.product_name}</title>
			<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
			<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
			<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
			<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>	
			<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
			
			<script src="https://kit.fontawesome.com/2367b3dda5.js" crossorigin="anonymous"></script>
			<link href="${contextPath}/resources/css/menu.css" rel="stylesheet" type="text/css">	
			<link href="${contextPath}/resources/css/product.css" rel="stylesheet" type="text/css">
			<link href="${contextPath}/resources/css/board.css" rel="stylesheet" type="text/css">
			<script src="${contextPath}/resources/js/product.js"></script>			
			<script src="${contextPath}/resources/js/review.js"></script>			
		</head>
		<body>
			
			<!-- 상단 메뉴  -->
			<jsp:include page="../common/topMenu.jsp" flush="false"/>
			
			<div id="mainTitle">
				<hr/>
				
			<c:forEach items="${productDTO.product_type}" var="type">
               	<c:choose>
                  <c:when test="${productDTO.product_type == 'top' }"> 
                     <h1><img src="${contextPath}../resources/images/top.png" style = "width: 40px; height: 40px;"/>&nbsp;&nbsp;${title}</h1>
                  </c:when>
               </c:choose>
               <c:choose>
                  <c:when test="${productDTO.product_type == 'bottom' }"> 
                     <h1><img src="${contextPath}../resources/images/bottom.png" style = "width: 40px; height: 40px;"/></i>&nbsp;&nbsp;${title}</h1>
                  </c:when>
               </c:choose>
               <c:choose>
                  <c:when test="${productDTO.product_type == 'outer' }"> 
                     <h1><img src="${contextPath}../resources/images/outer.png" style = "width: 40px; height: 40px;"/></i>&nbsp;&nbsp;${title}</h1>
                  </c:when>
               </c:choose>
               <c:choose>
                  <c:when test="${productDTO.product_type == 'acc' }"> 
                     <h1><img src="${contextPath}../resources/images/acc.png" style = "width: 50px; height: 50px;"/>&nbsp;&nbsp;${title}</h1>
                  </c:when>
               </c:choose>
            </c:forEach>
            

			</div>
			
			<div class="container" style="font-weight:bold;">
				<div class="row">
				<!-- 슬라이드 이미지넣기 -->
					<div id="myCarousel" class="carousel slide col-sm-6">
					
						<!-- Wrapper for slides -->
						<div class="carousel-inner">
						
							<!-- 파일 숫자가 상품마다 다르기 때문에 forEach문으로 반복넣기 -->
							<c:forEach var="list" items="${imageList}" varStatus="status">
								<c:choose>
									<c:when test="${status.count == 1}">
										<div class="item active">
											<!-- class="img-responsive center-block" : 반응형 이미지를 가운데에 정렬한다. -->
											<img class="img-responsive center-block" src="/image/displayImage?name=${list}" style = "height: 700px; border: 1px solid #aaaaaa;" />
										</div>
									</c:when>
								</c:choose>
								<c:choose>
									<c:when test="${status.count != 1}">
										<div class="item">
											<!-- class="img-responsive center-block" : 반응형 이미지를 가운데에 정렬한다. -->
											<img class="img-responsive center-block" src="/image/displayImage?name=${list}" style = "height: 700px; border: 1px solid #aaaaaa;" />
										</div>
									</c:when>
								</c:choose>		
							</c:forEach>
							<!-- 좌측, 우측으로 그림을 움직일 수 있도록 좌/우버튼을 설정한다. -->
							<a class="left  carousel-control" href="#myCarousel" data-slide="prev">
								<span class="glyphicon glyphicon-chevron-left"></span>
							</a>
							<a class="right carousel-control" href="#myCarousel" data-slide="next">
								<span class="glyphicon glyphicon-chevron-right"></span>
							</a>
						</div>
					</div>
					
					<!-- 상품 정보 부분 -->
					<div class="col-sm-6">
						<table id="pdDetailTable">
							<tr>
								<th colspan="2">
									<h3><strong>Product Info </strong></h3>
									<p> 상품 정보</p>
									<c:choose>
										<c:when test="${member1.grade == 7}">
											<button type="button" onclick="location.href='/product/productUpdateForm?product_code=' + '${productDTO.product_code}'" style = "background-color: #a0a0a0; color: white; height: 30px; width : 100px; border-radius:5px; border:none; margin-left: 60px;">정보 수정</button>
											<button type="button" onclick="fn_productDelete('${productDTO.product_code}', '${productDTO.product_type}');" style = "background-color: #505050; color: white; height: 30px; width : 100px; border-radius:5px; border:none;">상품 삭제</button>
										</c:when>
									</c:choose>
								</th>
							</tr>
							<tr>
								<td>
									<br/> <!-- 간격 벌리기 -->
								</td>
							</tr>
							<tr class="pdDetailTr">
								<td class="tdTitle">상품명</td>
								<td>${productDTO.product_name}</td>
							</tr>
							<tr class="pdDetailTr">
								<td class="tdTitle">등록 일자</td>
								<td><fmt:formatDate value="${productDTO.product_date}" pattern="yyyy년 MM월 dd일"/></td>
							</tr>
							<tr class="pdDetailTr">
								<td class="tdTitle">구매 후기</td>
								<td>
									<c:choose>
										<c:when test="${productDTO.star_avg == 0 }">
											아직 구매 후기가 없어요!
										</c:when>
										<c:otherwise>
											<c:choose>
			                                    <c:when test="${productDTO.star_avg / 1 != 0}">
			                                       <c:forEach begin="1" end="${productDTO.star_avg / 1}">
			                                          <span class="fa fa-star checked"></span>
			                                       </c:forEach>
			                                    </c:when>
			                                 </c:choose>
			                                 <c:choose>
			                                    <c:when test="${productDTO.star_avg % 1 != 0}">
			                                       <span class="fa fa-star-half-o checked"> </span>
			                                    </c:when>
			                                 </c:choose>
			                                 <c:choose>
			                                 <c:when test="${productDTO.star_avg / 1 != 0}">
			                                    <c:forEach begin="1" end="${5-(productDTO.star_avg / 1)}">
			                                          <span class="fa fa-star-o" style="color: orange;"></span>
			                                       </c:forEach>
			                                    </c:when>   
			                                 </c:choose>
											<p>${productDTO.star_avg}</p>
											<a href="#reviewcontainer" id="showreview">후기 ${reviewCount}개 보기</a>
										</c:otherwise>
									</c:choose>
								</td>
							</tr>
							<tr class="pdDetailTr">
								<td class="tdTitle">상품 가격</td>
								<td>
									<fmt:formatNumber value="${productDTO.product_price}" pattern="#,###원"/>
								</td>
							</tr>
							<tr>
								<td>
									<br/> <!-- 간격 벌리기 -->
								</td>
							</tr>
							<tr class="pdDetailTrSelect">
								<!-- 악세사리일때는 사이즈를 free로 한다. -->
								<c:choose>
									<c:when test="${productDTO.product_type == 'acc'}">
										<td>
											사이즈  &nbsp;&nbsp;
										</td>
										<td>
											<select id="product_size">
												<option value="free" selected>F R E E</option>
											</select>
										</td>	
									</c:when>
									<c:otherwise>
										<td>
											사이즈  &nbsp;&nbsp;
										</td>
										<td>	
											<select id="product_size">
												<option disabled selected>S I Z E</option>
												<option value="s">S</option>
												<option value="m">M</option>
												<option value="l">L</option>
												<option value="xl">XL</option>
											</select>
										</td>
									</c:otherwise>
								</c:choose>
							</tr>
							<tr class="pdDetailTrSelect">
								<td>
									수량
								</td>
								<td style="float:left; padding-right:118px;">
									<input type='button' id="amountMinus"  onclick="fn_amountMinus();" value="━">
									<p id="product_amount"><strong>1</strong></p>
									<input type="button" id="amountPlus"  onclick="fn_amountPlus();"value="╋"/>
								</td>
							</tr>
							<tr id="totalline">
								<c:choose>
									<c:when test="${product_amount !='0'}">
										<td id="totaltext" style = "font-size : 18px;"></td>
										<td id="total" style = "font-size : 18px;"></td>
									</c:when>
									<c:otherwise>
										<td></td>
										<td></td>
									</c:otherwise>
								</c:choose>
							</tr>
							<tr style="border-bottom:2px solid black; padding-bottom:30px;">
								<td>
									<br/> <!-- 간격 벌리기 -->
								</td>
							</tr>	
							<tr>
								<td>
									<br/>
								</td>
							</tr>					
							<tr id="btnline">
								<td colspan="2">
									<button class="orderbtn btn btn-secondary" id="addcartBtn" style = "background-color: #d2d2d2; color: white; width : 500px;"><strong>ADD TO SHOPPING CART</strong></button>			
								</td>
								<td></td>
							</tr>
							<tr>
								<td colspan="2">
									<button class="orderbtn btn btn-secondary" id="nocartBtn" style = "background-color: #787878; color: white; width : 500px;"><strong>ORDER PRODUCT</strong></button>	
								</td>
							</tr>
							<tr>
								<td>
									<br/> <!-- 간격 벌리기 -->
								</td>
							</tr>
							<c:choose>
								<c:when test="${productDTO.product_type == 'top' }">					
									<tr>
										<th colspan="2">
											<h4><strong>Size Info </strong></h4>
											<p> 사이즈 정보</p>
										</th>
									</tr>
									<tr>
										<td>
											<br/> <!-- 간격 벌리기 -->
										</td>
									</tr>							
									<tr>
										<td colspan="2">
											<img src="${contextPath }../resources/images/top_size.jpg"/>
										</td>
									</tr>
								</c:when>
							</c:choose>
							<c:choose>
								<c:when test="${productDTO.product_type == 'bottom' }">					
									<tr>
										<th colspan="2">
											<h4><strong>Size Info </strong></h4>
											<p> 사이즈 정보</p>
										</th>
									</tr>
									<tr>
										<td>
											<br/> <!-- 간격 벌리기 -->
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<img src="${contextPath }../resources/images/bottom_size.jpg"/>
										</td>
									</tr>
								</c:when>
							</c:choose>
							<c:choose>
								<c:when test="${productDTO.product_type == 'outer' }">					
									<tr>
										<th colspan="2">
											<h4><strong>Size Info </strong></h4>
											<p> 사이즈 정보</p>
										</th>
									</tr>
									<tr>
										<td>
											<br/> <!-- 간격 벌리기 -->
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<img src="${contextPath }../resources/images/outer_size.png"/>
										</td>
									</tr>
								</c:when>
							</c:choose>
							<c:choose>
								<c:when test="${productDTO.product_type == 'acc' }">					
									<tr>
										<th colspan="2">
											<h4><strong>Size Info </strong></h4>
											<p> 사이즈 정보</p>
										</th>
									</tr>
									<tr>
										<td>
											<br/> <!-- 간격 벌리기 -->
											<br/>
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<img src="${contextPath }../resources/images/none_img.png"/ style = "height: 100px;">
										</td>
									</tr>
								</c:when>
							</c:choose>
						</table>
						
						<!-- script에서 사용하기 위한 hidden input박스 -->
						<div>
							<input type="hidden" id="cartPCode" value="${productDTO.product_code}"/>
							<input type="hidden" id="cartUserID" value="${member1.userID}"/>
							<input type="hidden" id="isLogOn" value="${isLogOn}"/>
							<input type="hidden" id="userID" value="${member1.userID}"/>
							<a id="product_totalprice"><fmt:formatNumber value="${productDTO.product_price}"/></a>
						</div>
					</div>
				</div>	
				
				<!-- 리뷰게시글 테이블 보여주기-->
				<div id="reviewcontainer">
					<!-- 해당 상품 주문 후 리뷰 작성을 하지 않은 회원일 경우 보여지는 리뷰 작성 창 -->
					<!-- 세션에 담긴 해당 회원의 주문 내역과 상품 코드를 비교한다. -->
					<c:forEach items="${member1OrderDetail}" var="orderDetail">
						<c:if test="${orderDetail.product_code == productDTO.product_code and orderDetail.review_yn == 'N'}">
							<!-- 해당 상품 중 리뷰를 작성하지 않은 건수가 있으면 count에 +1을 해준다. -->
							<c:set var="count" value="0"/><!-- 변수 count 선언 -->
							<c:set var="count" value="${count + 1}"/>
							<c:if test="${count == 1 }">
								<input type="hidden" id="orderNum" value="${orderDetail.order_num}"/>
							</c:if>
						</c:if>
					</c:forEach>
					
					<!-- 해당 상품에 리뷰를 작성하지 않았다면 작성창을 보여준다. -->
					<br/>
                  	<h3 style="text-align:center;"><strong><span style="font-size:1.1em;" class="glyphicon glyphicon-thumbs-up"></span>&nbsp;&nbsp;Review</strong></h3>
					<c:if test="${count > 0 }">	
						<table>
							<tr>
								<td id="detailReviewRegTd">
									<input type="text" class="form-control" id="review_subject" name="review_subject" maxlength="200" placeholder="제목 입력"/>
									<select id="review_star" class="form-control" style = "width: 130px;">
										<option disabled selected>별 점</option>
										<option value="1" style = "color: orange;">★</option>
										<option value="2" style = "color: orange;">★★</option>
										<option value="3" style = "color: orange;">★★★</option>
										<option value="4" style = "color: orange;">★★★★</option>
										<option value="5" style = "color: orange;">★★★★★</option>
									</select>
									<button style="position:relative; width: 55px; left:19%; background-color: white; color: black; border-radius:5px; padding:5px; font-size:1.0em;" type="button" class="btn-light" onclick="fn_reviewRegister('${productDTO.product_code}');">등록</button>
								</td>
							</tr>
							<tr>
								<td>
									<textarea class="form-control" id="review_content" name="review_content" rows="3" cols="160"  placeholder="내용 입력"></textarea>
								</td>	
							</tr>
						</table>
					</c:if>	
					
					<!-- 해당 상품의 리뷰 리스트 -->
					<table class="table table-striped table-bordered table-hover" id="boardTable">
						<thead>
							<tr>
								<th class="boardTitle" id="detailReviewTh10">유저아이디</th>
								<th class="boardTitle">제목</th>
								<th class="boardTitle" id="detailReviewTh20">날짜</th>
								<th class="detailReviewTh5">평점</th>		
							</tr>
						</thead>
						<tbody>
							<c:forEach var="reviewlist" items="${reviewList}">
								<tr id="productDetailReviewTitle">
									<td class="boardTitle">${reviewlist.userID}</td>
									<td class="boardTitle"><a class="reviewSubjectA">${reviewlist.review_subject}</a></td>
									<td class="boardTitle">${reviewlist.review_date}</td>
									<td class="detailReviewTh5">${reviewlist.review_star}</td>
								</tr>
								<tr class="productDetailReviewContent">
									<td  class="boardTitle" colspan="4">${reviewlist.review_content}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				
				<!-- pageMaekr -->
				<div id="pagingunderbar">
					<div align="center">
						<ul class="btn-group pagination">
							<c:if test="${pageMaker.prev}">
								<li>
									<a href="<c:url value='/product//productDetail?product_code=${productDTO.product_code}&reviewPageNum=${pageMaker.startPage-1}'/>"><span class="glyphicon glyphicon-chevron-left"></span></a>
								</li>
							</c:if>
							<c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="pageNum">
								<li>
									<a href='<c:url value="/product/productDetail?product_code=${productDTO.product_code}&reviewPageNum=${pageNum }"/>'><i>${pageNum}</i></a>
								</li>
							</c:forEach>
							<c:if test="${pageMaker.next}">
								<li>
									<a href="<c:url value='/product//productDetail?product_code=${productDTO.product_code}&reviewPageNum=${pageMaker.endPage+1}'/>"><span class="glyphicon glyphicon-chevron-right"></span></a>
								</li>
							</c:if>
						</ul>
					</div>
				</div>
			</div>
			<!-- 하단 메뉴바 -->
			<jsp:include page = "../common/footer.jsp" flush = "false"/>
		
	
		</body>
	</html>