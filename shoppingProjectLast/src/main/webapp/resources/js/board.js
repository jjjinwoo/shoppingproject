/**
 *	게시글 관련 함수
 */
 
 //----------------------------------------------------------------------------------------------------------
 // 게시글 등록 하기
 //----------------------------------------------------------------------------------------------------------
 function fn_boardRegister() {
	

	// 게시글 등록 화면에서 입력한 값을 가져온다.
	let	qna_subject	= $("#qna_subject").val();
	let	userID	= $("#userID").val();
	let	qna_content	= $("#qna_content").val();
	
	//alert(qna_subject + ":" + userID + ":" + qna_content);
	
	// 제목 항목에 값이 없으면 입력하도록 한다.
	if($("#qna_subject").val() == "") {
		alert("제목은 필수 입력 항목입니다.");
		$("#qna_subject").focus();
		return false;
	}
	
	// 아이디 항목에 값이 없으면 입력하도록 한다.
	if($("#userID").val() == "") {
		alert("아이디는 필수 입력 항목입니다.");
		$("#userID").focus();
		return false;
	}
	
	// 내용 항목에 값이 없으면 입력하도록 한다.
	if($("#qna_content").val() == "") {
		alert("내용은 필수 입력 항목입니다.");
		$("#qna_content").focus();
		return false;
	}
	
	$.ajax({
		type:		"POST",
		url:		"/board/boardRegister",
		data:		{qna_subject:qna_subject, userID:userID, qna_content:qna_content},
		success:	function(data) {
			if(data == "Y") {
				alert("게시글을 등록하였습니다.");
				// 게시글 등록이 완료되면, 게시글 목록 화면으로 이동한다.
				location.href = "/board/boardList?page=1";
			}
		},
		error:		function(data) {
			alert("게시글을 등록하는데 실패하였습니다!");
		}
	});
	
} // End - function fn_boardRegister()

 
//----------------------------------------------------------------------------------------------------------
// 게시글 번호에 해당하는 게시글 삭제하기
//----------------------------------------------------------------------------------------------------------
function fn_boardDelete(qna_bno) {

	//게시글에 달린 댓글도 삭제해야하기 때문에 댓글 번호를 준비한다.
	var reply_bno = document.getElementsByClassName("reply_bno");
	//alert(reply_bno[0].value);
	
	//배열 형태를 보내주기 위한 FormData
	let formData = new FormData();
	
	//반복문으로 댓글 번호를 담아준다.
	for(let i = 0; i < reply_bno.length; i++){
		formData.append("reply_bno", reply_bno[i].value);
	}
	
	//게시글 번호도 담아준다.
	formData.append("qna_bno", qna_bno);
	
	
	// 삭제를 진행하기 전에 먼저 삭제여부를 확인한다.
	if(!confirm("\n게시글을 삭제하시겠습니까?\n\n삭제하려면 [확인]버튼을 누르시고, 아니면 [취소]버튼을 누르십시오.")) {
		alert("게시글 삭제를 취소하셨습니다.");
	} else {	// [확인]버튼을 눌렀을 경우
		$.ajax({
			type:			"POST",
			url:			"/board/boardDelete",
			processData: false,
			contentType: false,
			data: formData,
			success:		function(data) {
				if(data == "Y") {
					alert("게시글의 삭제가 완료되었습니다.");
					location.href = "/board/boardList";
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

//----------------------------------------------------------------------------------------------------------
// 게시글 번호에 해당하는 게시글의 내용(제목, 글쓴이, 내용)을 수정 요청하기
//----------------------------------------------------------------------------------------------------------
function fn_boardUpdate() {
	
	let	qna_bno		= $("#qna_bno").val();
	let	qna_subject	= $("#qna_subject").val();
	let	userID	= $("#userID").val();
	let	qna_content	= $("#qna_content").val();
	
	// alert(qna_bno + ":" + qna_subject + ":" + userID + ":" + qna_content);
	
	$.ajax({
		type:			"POST",
		url:			"/board/boardUpdate",
		data:			{qna_bno:qna_bno, qna_subject:qna_subject, userID:userID, qna_content:qna_content},
		success:		function(data) {
			if(data == "Y") {
				alert("게시글 수정이 완료되었습니다.");
				location.href="/board/boardList?page=1";
			} else {
				alert("게시글 수정이 되지 않았습니다.\n\n잠시 후에 다시 해주십시오.");
			}
		},
		error:			function(data) {
			alert("실패");
			console.log(data);
		}
	});
	
} // End - function fn_boardUpdate()

//----------------------------------------------------------------------------------------------------------
// 검색 버튼을 눌렀을 경우( jquery)
//----------------------------------------------------------------------------------------------------------
$(document).ready(function() {
			
	var formObj = $("#formList");
	
	// 검색 버튼을 눌렀을 경우
	$("#searchBtn").click(function() {
		var typeStr		= $("#searchType").find(":selected").val();
		var keywordStr	= $("#searchKeyword").val();
		//alert(typeStr + ":" + keywordStr);
		
		// 서버로 전송하기 전에, name 속성에 값을 넣어준다.
		formObj.find("[name='searchType']").val(typeStr);
		formObj.find("[name='keyword']").val(keywordStr);
		formObj.find("[name='page']").val("1");
		formObj.submit();
	});
	
});

//----------------------------------------------------------------------------------------------------------
//댓글 등록하기
//----------------------------------------------------------------------------------------------------------
function fn_commentRegister(qna_bno) {
	
	let	reply_content	= $("#reply_content").val();
	//alert(qna_bno + reply_content);
	
	// 내용 항목에 값이 없으면 입력하도록 한다.
	if($("#reply_content").val() == "") {
		alert("댓글 내용을 입력해주세요.");
		$("#reply_content").focus();
		return false;
	}
	
	$.ajax({
		type: "POST",
		url: "/board/commentRegister",
		data: {qna_bno:qna_bno, reply_content:reply_content},
		success: function(data) {
			if(data == "Y") {
				alert("댓글을 등록하였습니다.");
				location.href="/board/boardDetail?qna_bno=" + qna_bno + "&flag=1";
			}
		},
		error: function(data){
			alert("댓글 등록에 실패했습니다.");
		}
	});
} // End - function comments

//----------------------------------------------------------------------------------------------------------
//댓글 삭제하기
//----------------------------------------------------------------------------------------------------------
function fn_deleteComment(reply_bno, qna_bno) {

	$.ajax({
		type: "POST",
		url: "/board/commentDelete",
		data: {qna_bno:qna_bno, reply_bno:reply_bno},
		success: function(data) {
			if(data == "Y") {
				location.href="/board/boardDetail?qna_bno=" + qna_bno + "&flag=1";
			}
		},
		error: function(data){
			alert("댓글 삭제에 실패했습니다.");
		}
	});

}









