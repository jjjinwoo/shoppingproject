/**
 * 
 */
 
//로그인 상태를 확인하고 로그인 되어있지 않으면 로그인 화면으로 보내주는 전용 js

//boardList의 qna작성 버튼 눌렀을 때
function fn_isLogOnT() {

	if($("#isLogOnT").val()){
		//alert("로그인");
		var userID = $("#userIDT").val();
		location.href="/order/cartForm?userID=" + userID;
	} else {
		if(confirm("로그인이 필요합니다. 로그인 하시겠습니까?")) {
			location.href="/member/loginForm.do";
		}
	}
}

//boardList의 qna작성 버튼 눌렀을 때
function fn_isLogOnQL() {

	if($("#isLogOnT").val()){
		location.href="/board/boardRegisterForm?page=1";
	} else {
		if(confirm("로그인이 필요합니다. 로그인 하시겠습니까?")) {
			location.href="/member/loginForm.do";
		}
	}
}