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
		</head>
		<body>
			<!-- 상단 메뉴 -->
			<jsp:include page="../common/topMenu.jsp" flush="false"/>
			<div id="mainTitle">
				<hr/>
				<div id="boardTitle">
					<a href="/board/boardList?page=1">Q & A</a>
					<a href="/other/boardListFqa" id="selectedA">FQA</a>
				</div>
			</div>
			
			<div class="container">
				<ul class="nav navbar navbar-left" id="fqaUl">
					<li class="dropdown">
						<a class="dropdown-toggle glyphicon glyphicon-collapse-down" data-toggle="dropdown" href="#"> Q1. 일반 배송 상품은 언제 배송되나요?</a>
						<ul class="dropdown-menu dropdown-menufaq">
							<li>
								<br/>
								<p>일반 배송은 브랜드마다 일정이 다르고 평일 기준으로 출고 됩니다.</p>
								<br>
								<p>출고 일정은 상품의 상세페이지 출고 정보에서 확인 가능합니다.</p>
								<p>※ 평일 기준 출고로 연휴 및 공휴일은 배송일에서 제외됩니다.</p>
								<p>※ 전 상품 100% 무료 배송입니다. </p>
								<p>※ 배송 지연 상품의 경우 상품명에 지연으로 아이콘이 표시됩니다.</p> 
								<p>※ 출고 지연 발생 시에는 알림톡 또는 문자를 통해 안내해 드립니다.</p>
								<p>※ 주문 시 배송 메모에 배송 희망 일자를 작성하셔도 해당일에 지정 배송은 어렵습니다.</p>
								<br>
							</li>
						</ul>
					</li>
					<li class="dropdown">
						<a class="dropdown-toggle glyphicon glyphicon-collapse-down fqaUlLiA" data-toggle="dropdown" href="#"> Q2. 배송조회는 어떻게 하나요?</a>
						<ul class="dropdown-menu dropdown-menufaq">
							<li>
								<br/>
								<p>배송조회 메뉴에서 배송진행 상황을 확인할 수 있습니다.</p> 
								<br/>
								<p>■ 배송조회 경로</p>
								<p class="fqaColorP"><strong>마이페이지 > 배송 중/픽업 대기 > 배송조회</strong></p>
								<br/>
								<p>※ 출고 후 송장 조회까지는 평일 기준 1일 소요됩니다. </p>
								<p>※ 출고 처리 중 (상품 포장 및 확인하는) 단계부터는 주소(옵션) 변경 및 취소가 가능하지 않습니다.</p>
								<p>1. 상품 문제시 구입매장 또는 인근지역 매장을 방문하시어 의뢰하여 주십시오.</p>
								<br>
							</li>
						</ul>
					</li>
					<li class="dropdown">
						<a class="dropdown-toggle glyphicon glyphicon-collapse-down fqaUlLiA" data-toggle="dropdown" href="#"> Q3. 회원탈퇴는 어떻게 하나요?</a>
						<ul class="dropdown-menu dropdown-menufaq">
							<li>
								<br/>
								<p>탈퇴는 마이페이지 > 내 정보 관리에서 직접 신청해 주셔야 합니다. </p>
								<p>■ 탈퇴 경로</p>
								<p class="fqaColorP"><strong>PC(웹) : 마이페이지 >  회원 탈퇴 </strong></p>
								<p>■ 회원 탈퇴 주의사항</p>
								<p>- 진행 중인 주문 건이 있는 경우, 탈퇴가 가능하지 않습니다. </p>
								<p>- 탈퇴 후 재가입 시 가입 혜택으로 제공되는 쿠폰 발급이 가능하지 않습니다.</p> 
								<p>- 탈퇴 신청일로부터 5일 내 다시 로그인하면 탈퇴 신청이 취소됩니다.</p>
								<p>- 회원 탈퇴 시 동일한 휴대폰 명의로 재가입은 탈퇴 30일 이후 가능합니다.</p>
								<br/>
							</li>
						</ul>
					</li>
					<li class="dropdown">
						<a class="dropdown-toggle glyphicon glyphicon-collapse-down fqaUlLiA" data-toggle="dropdown" href="#"> Q4. 회원 정보 수정은 어디서 하나요?</a>
						<ul class="dropdown-menu dropdown-menufaq">
							<li>
								<br/>
								<p>회원 정보 수정은 아래 경로로 직접 변경 가능합니다. </p>
								<br/>
								<p>■ 회원 정보 수정</p> 
								<p class="fqaColorP"><strong>PC(웹) : 마이페이지 > 회원정보 변경</strong></p>
								<p>※ 원활한 주문 및 배송을 위해 회원 정보를 정확하게 기재해 주세요.</p>
								<br/>
								<p>■ 이름 개명 시 수정</p>
								<p class="fqaColorP"><strong>PC(웹) : 마이페이지 > 회원정보 변경 > 이름수정</strong></p>
								<br/>
							</li>
						</ul>
					</li>
					<li class="dropdown">
						<a class="dropdown-toggle glyphicon glyphicon-collapse-down fqaUlLiA" data-toggle="dropdown" href="#"> Q5. 상품문의는 어디서 작성하나요?</a>
						<ul class="dropdown-menu dropdown-menufaq">
							<li>
								<br/>
								<p>문의 하는 상품의<strong class="fqaColorP"> Q & A 페이지 > 상단의 게시글 작성</strong>에서 작성할 수 있습니다.</p>
								<br/>
								<p>※ 재입고, 사이즈, 배송 등 상품과 관련된 문의를 할 수 있습니다.</p>
								<p>※ 상품과 관련 없는 욕설, 비방, 회원 간 거래 글, 명예훼손, 타 쇼핑몰 언급, 허위사실 유포, 광고성 등의 문의는 숨김 처리됩니다.</p>
								<p>※ 주문번호, 연락처, 계좌번호 등의 개인 정보 관련 내용은 노출이 되지 않도록 비밀글로 문의해 주세요.</p>
								<p>※ 개인정보 노출된 공개 글은 비밀글로 전환될 수 있고, 개인 정보 노출로 인한 피해는 책임지지 않습니다.</p>
								<br/>
							</li>
						</ul>
					</li>
					<li class="dropdown">
						<a class="dropdown-toggle glyphicon glyphicon-collapse-down fqaUlLiA" data-toggle="dropdown" href="#"> Q6. 교환/반품(환불) 비용은 무료인가요?</a>
						<ul class="dropdown-menu dropdown-menufaq">
							<li>
								<br/>
								<p>회원 사유의 반품 접수 시 교환/반품(환불) 배송비가 부과됩니다.</p>
								<p>반품(환불) 접수 시 차감, 교환의 경우 결제해 주셔야 반품 접수가 정상적으로 완료됩니다.</p>
								<br/>
								<p>■ 반품 접수 시 주의 사항</p>
								<p>- 휴대전화 결제 또는 환불 배송비가 반품 배송비보다 적을 경우 반품 신청 시 배송비를 결제해 주셔야 합니다.</p>
								<p>- 도서산간지역의 경우 반품 배송비 추가 비용이 발생됩니다. </p>
								<p>- 안내서에 배송비 동봉 등에 대한 내용이 있더라도, 동봉하면 안 됩니다.</p>
								<p>- 해외 배송 상품일 경우 왕복 택배 비용 및 관세/통관 비용을 부담해 주셔야 합니다. </p>
								<p>- 브랜드 및 주문한 내용에 따라 반품 배송비는 상품 각각 청구 될 수 있고 계약된 택배사가 아닌 다른 택배사 이용 시 초과운임이 발생할 수 있습니다.</p>
								<p>- 브랜드 계약된 택배사로 반품 신규 접수 시 초과운임이 청구되니 받으셨던 운송장 번호로 반품 접수해 주세요.</p>
								<p>- 비용은 상품별로 달라 주문 내역의 판매자 정보, 교환/환불 접수 페이지 또는 각 상품 페이지 하단 교환/환불 안내에서 확인 가능합니다.</p>
								<br/>
							</li>
						</ul>
					</li>
					<li class="dropdown">
						<a class="dropdown-toggle glyphicon glyphicon-collapse-down fqaUlLiA" data-toggle="dropdown" href="#"> Q7. 출고 처리중 상품을 취소하고 싶어요.</a>
						<ul class="dropdown-menu dropdown-menufaq">
							<li>
								<br/>
								<p>출고 처리중 상품은 아래와 같이 취소 요청이 가능합니다.</p>
								<br>
								<p>■ 출고 처리중 취소</p>
								<p class="fqaColorP"><strong>- PC(웹) : 홈페이지 > 마이페이지 > 주문 내역 조회 > 주문 취소 요청 버튼 클릭</strong></p>
								<br/>
								<p>※ 배송 준비가 완료된 경우 취소 요청이 거절될 수 있으며, 거절 시 알림톡이 발송됩니다.</p>
								<p>※ 주문 제작 상품은 출고 처리중 주문취소 대상에서 제외됩니다.</p>
								<p>※ 주문취소 요청 철회 시 재 취소 요청은 가능하지 않습니다.</p>
								<br/>
							</li>
						</ul>
					</li>
				</ul>	
			</div>
			<br/>
			<br/>	
			<!-- 하단 메뉴바 -->
			<jsp:include page = "../common/footer.jsp" flush = "false"/>
				
			
		</body>
	</html>









