<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
	<html>
		<head>
			<meta charset="UTF-8">
			<title>주문 통계 페이지</title>
			<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
			<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
			<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
			<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>	
			<link href="${contextPath}/resources/css/menu.css" rel="stylesheet" type="text/css">	
			<link href="${contextPath}/resources/css/order.css" rel="stylesheet" type="text/css">
			<!-- 구글 차트 api -->	
			<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
			<script src="${contextPath}/resources/js/googleAPI.js"></script>
			<script>
			drawChart();
			drawChart2();
			drawChart3();
			</script>
		</head>
		<body>
			<!-- 상단 메뉴 -->
			<jsp:include page="../common/topMenu.jsp" flush="false"/>
			
			<div id="mainTitle">
				<hr/>
				<h1>주문 통계</h1>
			</div>
			
			<div class="container">
				<div class="row">
					<nav class="col-sm-2" id="myScrollspy">
						<ul class="nav nav-pills nav-stacked">
							<li><a href="/order/orderManagement">최근 등록 상품</a></li>
							<li><a href="/order/orderManagement">최근 등록 Q&A</a></li>
							<li><a href="/order/orderManagement">최근 등록 리뷰</a></li>
							<li><a href="/order/orderManagement">상품 주문 현황</a></li>
							<li class="active"><a href="#">주문 통계</a></li>
						</ul>
					</nav>
					<div class="col-sm-offset-1 col-sm-9">
						<div id="barchart_values"></div>
						<div id="barchart_values2"></div>
					</div>
				</div>
			</div>
			<!-- 차트 스크립트에서 사용할 hidden 요소 -->
			<div>
				<input type="hidden" id="TMBill" value="${TMBill}"/>
				<input type="hidden" id="LMBill" value="${LMBill}"/>
				<input type="hidden" id="LMBill_10" value="${LMBill_10}"/>
				<input type="hidden" id="LMBill_20" value="${LMBill_20}"/>
				<input type="hidden" id="LMBill_30" value="${LMBill_30}"/>
				<input type="hidden" id="LMBill_40" value="${LMBill_40}"/>
				<input type="hidden" id="TMBill_10" value="${TMBill_10}"/>
				<input type="hidden" id="TMBill_20" value="${TMBill_20}"/>
				<input type="hidden" id="TMBill_30" value="${TMBill_30}"/>
				<input type="hidden" id="TMBill_40" value="${TMBill_40}"/>
				<input type="hidden" id="Tenter_count" value="${TMStats.enter_count}"/>
				<input type="hidden" id="Tjoin_count" value="${TMStats.join_count}"/>
				<input type="hidden" id="Tunregister_count" value="${TMStats.unregister_count}"/>
				<input type="hidden" id="Lenter_count" value="${LMStats.enter_count}"/>
				<input type="hidden" id="Ljoin_count" value="${LMStats.join_count}"/>
				<input type="hidden" id="Lunregister_count" value="${LMStats.unregister_count}"/>
			</div>
			
			<!-- 하단 메뉴 -->
			<jsp:include page="../common/footer.jsp" flush="false"/>
		</body>
	</html>