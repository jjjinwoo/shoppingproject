/**
 *	리뷰 관련 함수
 */
 
 //----------------------------------------------------------------------------------------------------------
 // 리뷰 등록
 //----------------------------------------------------------------------------------------------------------
 function fn_reviewRegister(product_code) {
	
	//alert("리뷰 등록 버튼을 눌렀습니다.");

	// 리뷰 등록 화면에서 입력한 값을 가져온다.
	let	review_subject	= $("#review_subject").val();
	let	review_content	= $("#review_content").val();
	let	review_star	= $("#review_star").val();
	let userID = $("#userID").val();

	// 회원 정보가 없으면 로그인하도록 한다.
	if($("#userID").val() == "") {
		alert("로그인을 하셔야 리뷰를 등록 하실 수 있습니다.");
		location.href="/member/loginForm.do";
		return false;
	}
	
	// 제목 항목에 값이 없으면 입력하도록 한다.
	if($("#review_subject").val() == "") {
		alert("제목은 필수 입력 항목입니다.");
		$("#review_subject").focus();
		return false;
	}
	
	// 내용 항목에 값이 없으면 입력하도록 한다.
	if($("#review_content").val() == "") {
		alert("내용은 필수 입력 항목입니다.");
		$("#review_content").focus();
		return false;
	}
	
	// 별점 항목에 값이 없으면 입력하도록 한다.
	if($("#review_star").val() == null) {
		alert("별점은 필수 입력 항목입니다.");
		$("#review_star").focus();
		return false;
	}
	
	//주문 번호를 가져온다.
	var order_num = $("#orderNum").val();
	
	$.ajax({
		type:		"POST",
		url:		"/review/reviewRegister",
		data:		{order_num:order_num, product_code:product_code, review_subject:review_subject, review_content:review_content, review_star:review_star, userID:userID},
		success:	function(data) {
			if(data != "N") {
				alert("리뷰를 등록하였습니다.");
				// 게시글 등록이 완료되면, 마이페이지-활동내역페이지로 이동한다.
				location.href = "/member/myOrderPage?userID=" + data;
			}
		},
		error:		function(data) {
			alert("리뷰를 등록하는데 실패하였습니다!");
		}
	});
	
} // End - function fn_reivewRegister()

 
//----------------------------------------------------------------------------------------------------------
// 리뷰 번호에 해당하는 리뷰 삭제하기
//----------------------------------------------------------------------------------------------------------
function fn_reviewDelete(review_bno, grade) {
	
	// 삭제를 진행하기 전에 먼저 삭제여부를 확인한다.
	if(!confirm("\n리뷰를 삭제하시겠습니까?\n\n삭제하려면 [확인]버튼을 누르시고, 아니면 [취소]버튼을 누르십시오.")) {
		alert("게시글 삭제를 취소하셨습니다.");
	} else {	// [확인]버튼을 눌렀을 경우
		$.ajax({
			type:			"POST",
			url:			"/review/reviewDelete",
			data:			{review_bno : review_bno},
			success:		function(data) {
				if(data == "Y") {
					alert("리뷰 삭제가 완료되었습니다.");
					if(grade != 7) { //관리자가 아닐 경우
						location.href = "/member/myOrderPage?userID=" + data;
					} else { //관리자일경우
						location.href= "/order/orderManagement";
					}
				}
			},
			error:			function(data) {
				alert("게시글을 삭제하는데 문제가 발생하였습니다.");
			},
			done:			function(data) {
				alert("요청 성공");
			},
			fail:			function(data) {
				alert("요청 실패");
			},
			always:			function(data) {
				alert("요청 완료");
			}
		});
	}
	
} // End - function fn_boardDelete(bno)

 









