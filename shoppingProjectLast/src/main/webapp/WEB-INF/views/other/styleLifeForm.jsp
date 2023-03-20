<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<c:set var = "contextPath" value = "${pageContext.request.contextPath }"/>
<c:set var = "result" value = "${param.result }"/>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
	<html>
		<head>
			<meta charset="UTF-8">
			<title>News & Event</title>
			<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
			<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
			<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
			<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>	
			<link href="${contextPath}/resources/css/menu.css" rel="stylesheet" type="text/css">			
			<link href="${contextPath}/resources/css/front.css" rel="stylesheet" type="text/css">
		</head>
		<body>
			<!-- 상단 메뉴 -->
			<jsp:include page ="../common/topMenu.jsp"></jsp:include>
			<div id="mainTitle">
				<hr/>
				<h1>News & Event</h1>
			</div>
			
			<!-- stylelife 리스트 -->
			<div class = "container stylebox">
				<table>
					<tr>
						<th><a href="/other/styleDetail?num=1"><img src = "${contextPath }../resources/images/style01.jpg"/></a></th>
						<th><a href="/other/styleDetail?num=2"><img src = "${contextPath }../resources/images/style02.jpeg"/></a></th>
						<th><a href="/other/styleDetail?num=3"><img src = "${contextPath }../resources/images/style03.jpg"/></a></th>
						<th><a href="/other/styleDetail?num=4"><img src = "${contextPath }../resources/images/style04.jpg"/></a></th>
					</tr>
					<tr>
						<td><a href="/other/styleDetail?num=1"><strong>앤듀, ‘밸런스’ 캠페인 진행…하반기 친환경 의류</strong></a></td>
						<td><a href="/other/styleDetail?num=2"><strong>앤듀, 비건 패션으로 친환경 행보 이어간다</strong></a></td>
						<td><a href="/other/styleDetail?num=3"><strong>앤듀, 코튼계의 캐시미어 ‘수피마’ 스타일링 소개</strong></a></td>
						<td><a href="/other/styleDetail?num=4"><strong>앤듀, 항균∙땀 흡수 모두 잡은 여름맞이 ‘컴포트</strong></a></td>
					</tr>
					<tr>
						<th><img src = "${contextPath }../resources/images/style05.jpg"/></th>
						<th><img src = "${contextPath }../resources/images/style06.jpg"/></th>
						<th><img src = "${contextPath }../resources/images/style07.jpg"/></th>
						<th><img src = "${contextPath }../resources/images/style08.jpg"/></th>
					</tr>
					<tr>
						<td><strong>앤듀, 그린슈머∙MZ세대 마음 사로잡을 ‘제로 웨</strong></td>
						<td><strong>“3초만에 가입 끝” 앤듀, 새로운 멤버십 서비스</strong></td>
						<td><strong>앤듀, 2021 봄에도 지속가능 패션 ‘그린라이프’</strong></td>
						<td><strong>실패없이 입는 모델픽 ‘데일리룩’with앤듀</strong></td>
					</tr>
					<tr>
						<th><img src = "${contextPath }../resources/images/style09.jpg"/></th>
						<th><img src = "${contextPath }../resources/images/style10.jpg"/></th>
						<th><img src = "${contextPath }../resources/images/style11.jpg"/></th>
						<th><img src = "${contextPath }../resources/images/style12.jpg"/></th>
					</tr>
					<tr>
						<td><strong>앤듀, 재생 섬유로 태어난 친환경 제품 출시</strong></td>
						<td><strong>앤듀, 글로벌 모델과 함께 젠더 뉴트럴 제안</strong></td>
						<td><strong>2019 한아세안 특별정상회의, 추위 녹인 ‘다</strong></td>
						<td><strong>앤듀 크리스마스 럭키드러우 이벤트</strong></td>
					</tr>
					<tr>
						<th><img src = "${contextPath }../resources/images/style13.jpg"/></th>
						<th><img src = "${contextPath }../resources/images/style14.jpg"/></th>
						<th><img src = "${contextPath }../resources/images/style15.jpg"/></th>
						<th><img src = "${contextPath }../resources/images/style16.jpg"/></th>
					</tr>
					<tr>
						<td><strong>샤이니 민호, 앤듀 2017 S/S 시즌 모델 발탁</strong></td>
						<td><strong>샤이니 민호와 함께 댄디한 수트 룩 제안</strong></td>
						<td><strong>샤이니 민호와 함께 봄을 알리는 자켓 스타일</strong></td>
						<td><strong>봄을 닮은 내추럴컬러 Type-A자켓 출시</strong></td>
					</tr>
				</table>
			</div>
			
			
			<!-- 하단 메뉴바 -->
			<jsp:include page = "../common/footer.jsp" flush = "false"/>	
				
		</body>
	</html>