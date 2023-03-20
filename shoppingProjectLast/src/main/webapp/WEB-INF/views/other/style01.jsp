<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<c:set var = "contextPath" value = "${pageContext.request.contextPath }"/>
<c:set var = "result" value = "${param.result }"/>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
	<html>
		<head>
			<meta charset="UTF-8">
			<title>앤듀, ‘밸런스’ 캠페인 진행…하반기 친환경 의류 라인업 강화</title>
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
				<h1><strong>앤듀, ‘밸런스’ 캠페인 진행…하반기 친환경 의류 라인업 강화</strong></h1>
			</div>
			
			<div class="container">
				<div class = "stylecontentbox">
					<div class="styleTitles">
						<h5>2021년 10월 14일/카테고리: NEWS & EVENT /작성자: 관리자</h5>
						<h4>DS인증 다운 점퍼, 비건 원자재, 리사이클 패브릭 사용</h4>
						<img src="${contextPath}../resources/images/style01.jpg"/>
					</div>
					<div class = "stylecontent">
						<h4>
							[매일일보 최지혜 기자] 한세엠케이의 앤듀(ANDEW)는 올해 하반기 ‘밸런스(BALANCE)’ 
							캠페인을 진행하며 친환경 제품 라인업을 강화한다고 17일 밝혔다.
							밸런스는 앤듀가 올해 두번째로 전개하는 캠페인이다.
						</h4>
						<h4>
							엔듀는 지난 시즌 ‘그린 라이프’ 캠페인을 통해 환경과 윤리적인 가치에 관심이 높은 고객과 
							가치관을 공유하며 긍정적인 반응을 이끌어낸 바 있다.
							회사는 이번 캠페인을 통해서도 가치소비를 중시하는 ‘그린슈머’의 관심을 기대하고 있다.
							밸런스 캠페인은 올해 하반기 시즌 상품 컨셉인 변화된 환경 속에서 
							우리만의 균형감을 찾고자 하는 ‘BALANCE(균형)’를 바탕으로 진행된다.
							상반기에 전개된 캠페인 컨셉의 연장선으로 소비자와 함께 지속가능한 패션 환경을 조성한다는 메시지를 담고 있다.
							앤듀는 이를 실천하기 위해 ‘책임 다운 기준(RDS, Responsible Down Standard)’을 인증받은 
							다운 충전재와 동물유래 성분을 사용하지 않은 비건 원자재, 
							리사이클 패브릭(재활용 의류소재) 등 자연친화적 소재를 활용했다.
							RDS는 동물 복지시스템을 준수하면서 윤리적인 방법으로 만든 다운 제품에 부여되는 인증이다.
						</h4>
						<h4>
							이번 시즌에 새롭게 선보이는 ‘블랙 라인’은 프리미엄 캐주얼 스타일로 강화했다.
							친환경적인 브랜드 가치를 아이템에 반영하고자 리사이클 패브릭을 활용해 점퍼를 제작했다. 
							이들 제품은 경량감, 신축성 등 기능성을 갖췄다.
							한세엠케이 앤듀 마케팅 관계자는 “환경과 동물을 보호하는 가치소비를 지향하는 소비자가 늘어남에 따라
							이번 캠페인에서도 고객의 동참을 이끌어 낼 수 있을 것으로 기대하고 있다”고 밝혔다.
						</h4>
						<br>
						<h4>출처 : 매일일보(http://www.m-i.kr)</h4>
					</div>
				</div>
			</div>
		
			
			<!-- 하단 메뉴바 -->
			<jsp:include page = "../common/footer.jsp" flush = "false"/>	
				
		</body>
	</html>