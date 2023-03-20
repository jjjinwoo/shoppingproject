<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 로그인 확인용 -->
<script src="${contextPath}/resources/js/isLogOn.js"></script>
<div>
	<input type="hidden" id="isLogOnT" value="${isLogOn}"/>
	<input type="hidden" id="userIDT" value="${member1.userID}"/>
</div>

<div class="navbar" id="myNav">
	<!-- 로고 네브바  -->
	<div class="container-fluid" id="navbar1">
		<a href="/main.do"><img src="${contextPath}../resources/images/logo.png" style = "width: 40px; height: 40px;"/><strong>&nbsp;N D E W</strong></a>
	</div>	

	<!-- 로그인 네브바 -->
	<div class="container-fluid">
		<div class="collapse navbar-collapse navbar-right" id="navbar2">
			<c:choose>
				<c:when test="${isLogOn == true && member1 != null}">
					<ul class="nav navbar-nav"  id="navbar2Ul">
						<li>
							<p class="navbar-text"><b>${member1.name}님, 환영합니다.&nbsp;&nbsp;</b></p>
						</li>
						<li>
							<a href="/member/logout.do" class="btn btn-light">
								<span class="glyphicon glyphicon-log-out"></span> 로그아웃
							</a>
						</li>
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">
								<span class="glyphicon glyphicon-user" ></span> 마이페이지 <span class="caret"></span>
							</a>
							<ul class="dropdown-menu">
								<li>
									<a href="/member/mypageForm.do">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 회&nbsp; 원&nbsp; 정&nbsp; 보 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
								</li>
								<li>
									<a href="/member/myOrderPage?userID=${member1.userID }" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 활&nbsp; 동&nbsp; 내&nbsp; 역 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
								</li>
								<!-- 관리자 로그인시 보이는 부분 -->
								<c:choose>
									<c:when test="${member1.grade == 7}">
										<li>
											<a href="/member/listMembers.do">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 회&nbsp; 원&nbsp; 목&nbsp; 록 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
										</li>
										<li>
											<a href="/order/orderManagement">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 운&nbsp; 영&nbsp; 관&nbsp; 리 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
										</li>
									</c:when>
								</c:choose>
							</ul>
						</li>
					</ul>
				</c:when>
				<c:otherwise>
					<div class="form-group">
						<a href="/member/loginForm.do">&nbsp;&nbsp;Login&nbsp;&nbsp;</a>
						<a href="/member/memberForm.do">&nbsp;&nbsp;Join&nbsp;&nbsp;</a>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	
	<!-- 창이 작아졌을 때 생기는 버튼 부분 -->
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
		</button>
	</div>
	
	<!-- 메뉴 부분 -->
	<nav class="navbar" id="navbar3">
			<!-- 실질적인 메뉴를 나열한다. -->
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav">
					<li class="dropdown menu2Li">
						<a class="dropdown-toggle" data-toggle="dropdown" href="#" >&nbsp;&nbsp;&nbsp;&nbsp; ANDEW &nbsp;&nbsp;&nbsp;&nbsp;</a>
						<ul class="dropdown-menu">
							<li><a href="/other/conceptForm.do">CONCEPT</a></li>
						</ul>
					</li>
					<li class="dropdown menu2Li">
						<a class="dropdown-toggle" data-toggle="dropdown" href="#">&nbsp;&nbsp;&nbsp;&nbsp; Product &nbsp;&nbsp;&nbsp;&nbsp; </a>
						<ul class="dropdown-menu">
							<li><a href="/product/productTypeList?product_type=top&page=1&array_type=r">Top</a></li>
							<li><a href="/product/productTypeList?product_type=bottom&page=1&array_type=r">Bottom</a></li>
							<li><a href="/product/productTypeList?product_type=outer&page=1&array_type=r">Outer</a></li>
							<li><a href="/product/productTypeList?product_type=acc&page=1&array_type=r">Acc</a></li>
						</ul>
					</li>
					<li class="dropdown menu2Li">
						<a class="dropdown-toggle" data-toggle="dropdown" href="#">&nbsp;&nbsp;&nbsp;&nbsp; StyleLife &nbsp;&nbsp;&nbsp;&nbsp;</a>
						<ul class="dropdown-menu">
							<li><a href="/other/styleLifeForm.do">News & Event</a></li>
						</ul>
					</li>
					<li class="dropdown menu2Li">
						<a class="dropdown-toggle" data-toggle="dropdown" href="#">&nbsp;&nbsp;&nbsp;&nbsp; Customer Service &nbsp;&nbsp;&nbsp;&nbsp;</a>
						<ul class="dropdown-menu">
							<li><a href="/board/boardList?page=1">&nbsp;&nbsp;&nbsp;&nbsp;Q&nbsp;&nbsp;&nbsp; & &nbsp;&nbsp;&nbsp; A&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
							<li><a href="/other/useForm.do">&nbsp;&nbsp;&nbsp;&nbsp;이 용 약 관&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
							<li><a href="/other/informationForm.do">&nbsp;&nbsp;&nbsp;&nbsp;개 인 정 보 취 급 방 침&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
						</ul>
					</li>
					<li>
						<a href = "/product/searchForm?product_type=outer&page=1&array_type=r"><span class = "glyphicon glyphicon-search" >&nbsp;&nbsp;</span></a>
					</li>
					<li>
						<a href = "#" id="isLogOnTA" onclick="fn_isLogOnT();"><span class = "glyphicon glyphicon-shopping-cart">&nbsp;&nbsp;</span></a>
					</li>
					
				</ul>
			</div>	
		
	</nav>
</div>