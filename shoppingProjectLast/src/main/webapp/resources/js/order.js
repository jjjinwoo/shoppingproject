
//장바구니부터 주문 완료 까지


//장바구니 
 /* cartList jquery */
$(document).ready(function() {

	//총 결제 금액 계산해서 넣기
	//td의 html을 가져와서 숫자만 뽑아낸다.
	var sum = $("#orderSum").html();
	//var sum = document.getElementById("orderSum").value;
	//alert(sum);
	
	var orderSum = sum.replace(/[^0-9]/g, "");
	var discountOrigin = $("#discount").html();
	var discount = discountOrigin.replace(/[^0-9]/g, "");	
	var deliveryFeeOrigin = $("#deliveryFee").html();
	var deliveryFee = deliveryFeeOrigin.replace(/[^0-9]/g, "");	
	
	//alert(orderSum + ", " + discount + ", " + deliveryFee);
	
	//값을 계산한다.
	var totalBill = orderSum - discount + Number(deliveryFee);
	//alert(totalBill);
	
	//계산한 값에 numberFormat을 직접 지정해준다.
	totalBill = totalBill.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	totalBill = totalBill + "원"
	//alert(totalBill);
	$('#totalBillTd').append(totalBill);
	
	
	//장바구니 페이지에서 선택상품 주문하기 버튼 눌렀을 때
	$("#orderSelectedBtn").click(function() {
		
		//체크된 input
		var checkbox = document.querySelectorAll("input[name='cart']:checked"); //체크된 input
		
		 // 상품이 없으면 넘어가지 못하게 만든다.
         if(checkbox.length == 0){
	         alert("선택된 상품이 없습니다.\n상품 선택이 잘 되어있는지 확인해주세요.");
	         return false;
         }
		
		
        var str = "";
        //반복문으로 str을 세팅한다.
		for(let i = 0; i < checkbox.length; i++) {
			if(i != checkbox.length - 1 ){
				str += checkbox[i].value + "&cartNum=";
			} else {
				str += checkbox[i].value;
			}
		}
		//alert(str);
		location.href="/order/orderForm?cartNum=" + str;
	});	
	
	//장바구니 페이지에서 전체상품 주문하기 버튼 눌렀을 때
	$("#orderTotalBtn").click(function() {
		
		//모든 장바구니 번호에 해당하는 체크박스를 가져온다.
		var checkbox = document.querySelectorAll("input[name='cart']");
		
		 // 상품이 없으면 넘어가지 못하게 만든다.
         if(checkbox.length == 0){
	         alert("장바구니에 상품이 없습니다.\n상품이 있는지 확인해주세요.");
	         return false;
         }
		
		
        var str = "";
        //반복문으로 str을 세팅한다.
		for(let i = 0; i < checkbox.length; i++) {
			if(i != checkbox.length - 1 ){
				str += checkbox[i].value + "&cartNum=";
			} else {
				str += checkbox[i].value;
			}
		}
		//alert(str);
		location.href="/order/orderForm?cartNum=" + str;
	});	
	
	//체크박스 전체 선택/해제에 해당하는 체크박스를 눌렀을 때
	$('#checkAll').click(function(){ //체크 박스를 클릭 했을 때
	
		if($('#checkAll').is(':checked')) { //체크된 상태이면
			
			//name=cart인 input을 전부 체크한다.
			$("input[name='cart']").prop("checked", true);
			
	
		} else { //체크 해제 상태이면
			
			//name=cart인 input을 전부 체크 해제한다.
			$("input[name='cart']").prop("checked", false);
		}
		
	});	
	
	//장바구니 번호에 해당하는 input 체크를 해제 했을 때 전체 체크에 해당하는 input의 체크 값을 해제한다.
	$("input[name='cart']").click(function(){ //체크 박스를 클릭 했을 때
		
		if(!$(this).is(':checked')) { //체크 해제 상태이면
			
			//전체 체크에 해당하는 체크박스 체크를 해제한다.
			$('#checkAll').prop("checked", false);
			
		}
	});		
});
//선택 삭제 버튼을 눌렀을 때 사용할 함수
function fn_cartDelete() {

	// 삭제를 진행하기 전에 먼저 삭제여부를 확인한다.
	if(!confirm("\n선택 상품을 삭제하시겠습니까?\n\n삭제하려면 [확인]버튼을 누르시고, 아니면 [취소]버튼을 누르십시오.")) {
		alert("상품 삭제를 취소하셨습니다.");
	} else {	// [확인]버튼을 눌렀을 경우
	
		//체크된 input
		var checkbox = document.querySelectorAll("input[name='cart']:checked"); //체크된 박스 전부
		
		//배열 형태를 보내주기 위한 formData를 준비한다.
		let formData = new FormData();
		
        //반복문으로 formData을 세팅한다.
		for(let i = 0; i < checkbox.length; i++) {
			formData.append("cartNum", checkbox[i].value);
		}
		
		//ajax로 삭제데이터를 보낸다.
		$.ajax({
			type: "post",
			url: "/order/cartDelete",
			processData: false,
			contentType: false,
			data: formData,
			success: function(data) {
			
				alert("선택한 상품을 삭제했습니다.");
				//회원 아이디
				var userID = document.getElementById('cartListUserID').value;
				
				location.href="/order/cartForm?userID=" + userID;
			},
			error: function(data) {
				alert("장바구니 삭제에 실패했습니다.");
			}
		});
		
	}

}

//전체 삭제 버튼을 눌렀을 때 사용할 함수
function fn_cartAllDelete() {

	// 삭제를 진행하기 전에 먼저 삭제여부를 확인한다.
	if(!confirm("\n전체 상품을 삭제하시겠습니까?\n\n삭제하려면 [확인]버튼을 누르시고, 아니면 [취소]버튼을 누르십시오.")) {
		alert("상품 삭제를 취소하셨습니다.");
	} else {	// [확인]버튼을 눌렀을 경우
	
		//체크된 input
		var checkbox = document.querySelectorAll("input[name='cart']"); //체크박스 전부
		
		//배열 형태를 보내주기 위한 formData를 준비한다.
		let formData = new FormData();
		
        //반복문으로 formData을 세팅한다.
		for(let i = 0; i < checkbox.length; i++) {
			formData.append("cartNum", checkbox[i].value);
		}
		
		//ajax로 삭제데이터를 보낸다.
		$.ajax({
			type: "post",
			url: "/order/cartDelete",
			processData: false,
			contentType: false,
			data: formData,
			success: function(data) {
			
				alert("전체 상품을 삭제했습니다.");
				//회원 아이디
				var userID = document.getElementById('cartListUserID').value;
				
				location.href="/order/cartForm?userID=" + userID;
			},
			error: function(data) {
				alert("장바구니 삭제에 실패했습니다.");
			}
		});
		
	}

}

//장바구니 리스트에서 상품 사이즈 변경시 바로 정보 업데이트를 한다.
function fn_updateProduct_size(value, cart_num) { //선택된 옵션 value 값, 장바구니 번호를 받아온다.
	
	//회원 아이디
	var userID = document.getElementById("cartListUserID").value;
	
	//alert(value + cart_num + userID);
	
	if(confirm("상품 사이즈를 " + value + "로 변경하시겠습니까?")){
	
		$.ajax ({
			type: "post",
			url: "/order/cartUpdate",
			data: {cart_num:cart_num, product_size:value},
			success: function(data) {
				//alert("정보 수정 성공");
				location.href="/order/cartForm?userID=" + userID;
			}
		});
	} else { 
		
		//변경을 취소한 경우 size select 박스의 선택된 값을 다시 원래 값으로 보여주기 위해
		//장바구니 리스트 페이지로 다시 보내준다.
		location.href="/order/cartForm?userID=" + userID;
		
	}
}

//장바구니 리스트에서 상품 수량 변경시 바로 정보 업데이트를 한다.
//수량 빼기
function fn_amountMinus(product_amount, cart_num) { //현재 상품 수량을 받아온다.
	
	var userID = document.getElementById("cartListUserID").value;
	
	product_amount = product_amount -1;
	//alert(product_amount + ", " + cart_num + userID);
	
	if(confirm("상품 수량을 " + product_amount + "로 변경하시겠습니까?")){
	
		$.ajax ({
			type: "post",
			url: "/order/cartUpdate",
			data: {cart_num:cart_num, product_amount:product_amount},
			success: function(data) {
				//alert("정보 수정 성공");
				location.href="/order/cartForm?userID=" + userID;
			}
		});
	}
}
//수량 더하기
function fn_amountPlus(product_amount, cart_num) { //현재 상품 수량을 받아온다.
	
	var userID = document.getElementById("cartListUserID").value;
	
	product_amount = product_amount + 1;
	//alert(product_amount + ", " + cart_num + userID);
	
	if(confirm("상품 수량을 " + product_amount + "로 변경하시겠습니까?")){
	
		$.ajax ({
			type: "post",
			url: "/order/cartUpdate",
			data: {cart_num:cart_num, product_amount:product_amount},
			success: function(data) {
				if(data == 1){
					//alert("정보 수정 성공");
					location.href="/order/cartForm?userID=" + userID;
				}
			}
		});
	}
}

 /* orderDetail jquery */
$(document).ready(function() {

	
	
	//배송지 선택에 대한 부분
	var deliveryName = $("#deliveryName").val();
	var deliveryPhone = $("#deliveryPhone").val();
	var deliveryPostnum = $("#deliveryPostnum").val();
	var deliveryAddress1 = $("#deliveryAddress1").val();
	var deliveryAddress2 = $("#deliveryAddress2").val();
	
	$("input[name='deliveryAddress']").click(function(){ //라디오 버튼을 클릭했을때(기본배송지/새로운배송지)
		//alert("확인")
		if($('#originAdress').is(':checked')){
			//alert("확인1")
			//input 박스에 원래 값을 넣는다.
			$("#deliveryPostnum").val(deliveryPostnum);
			$("#deliveryAddress1").val(deliveryAddress1);
			$("#deliveryAddress2").val(deliveryAddress2);
			//새로운 배송지 checked를 해제한다.
			$('#newAdress').prop("checked", false);
			//우편번호 버튼에 disabled 속성을 추가한다.
			$("#postBtn").attr("disabled", true);
		} else if($('#newAdress').is(':checked')){
			$("#deliveryPostnum").val('');
			$("#deliveryAddress1").val('');
			$("#deliveryAddress2").val('');
			//기본 배송지 checked를 해제한다.
			$('#originAdress').prop("checked", false);
			//우편번호 버튼의 disabled 속성을 제거한다.
			$("#postBtn").attr("disabled", false);
		}
	
	});
	
	
	$('#deliveryCheckbox').click(function(){ //체크 박스를 클릭 했을 때
	
		if($('#deliveryCheckbox').is(':checked')) { //주문자 정보와 동일 할 때
			
			//input 박스에 원래 값을 넣는다.
			$("#deliveryName").val(deliveryName);
			$("#deliveryPhone").val(deliveryPhone);
			$("#deliveryPostnum").val(deliveryPostnum);
			$("#deliveryAddress1").val(deliveryAddress1);
			$("#deliveryAddress2").val(deliveryAddress2);
			
			//배송지 선택 checked를 바꾼다.
			$("#originAdress").prop("checked", true);
			$("#newAdress").prop("checked", false);			
			//alert("체크됨 > "  + deliveryName);
			//우편번호 버튼에 disabled 속성을 추가한다.
			$("#postBtn").attr("disabled", true);
		
		} else {	//주문자 정보와 동일하지 않을 때
			
			//input 박스 안의 값을 초기화 한다.
			$("#deliveryName").val('');
			$("#deliveryPhone").val('');
			$("#deliveryPostnum").val('');
			$("#deliveryAddress1").val('');
			$("#deliveryAddress2").val('');
			
			//배송지 선택 checked를 바꾼다.
			$("#newAdress").prop("checked", true);
			$("#originAdress").prop("checked", false);
			//alert("체크 안됨");
			
			//우편번호 버튼의 disabled 속성을 제거한다.
			$("#postBtn").attr("disabled", false);
		
		}
		
	});
	
	
	//결제수단 버튼 클릭시 Css 처리
	$(".payMethodBtn").on('click', function(e){
	    if(e.target.id === 'payMethodCash'){ //계좌이체 버튼 클릭시
	        
	        //나머지 버튼의 css를 바꿔준다.
	        $("#payMethodCard").css("background-color", "#FFF");
	        $("#payMethodCard").css("color", "#555555");
	        $("#payMethodCard").css("font-weight", "lighter");
	        $("#payMethodKakao").css("background-color", "#FFF");
	        $("#payMethodKakao").css("color", "#555555");
	        $("#payMethodKakao").css("font-weight", "lighter");
	        
	        //카드 결제 버튼의 css를 바꿔준다.
	        $("#payMethodCash").css("background-color", "#000");
	        $("#payMethodCash").css("color", "#FFF");
	        $("#payMethodCash").css("border", "1px solid #cccccc");
	        $("#payMethodCash").css("box-shadow", "none");
	        $("#payMethodCash").css("font-weight", "bold");
	        
	        //결제수단 input 값을 바꿔준다.
	        $("#payMethodInput").attr("value", "1");
	        
	        //계좌 이체 정보를 보여준다.
	        $("#payMethodInfo").css("visibility", "visible");
	        
	    } else if(e.target.id === 'payMethodCard'){ //카드 버튼 클릭시
	        
	        //나머지 버튼의 css를 바꿔준다.
	        $("#payMethodCash").css("background-color", "#FFF");
	        $("#payMethodCash").css("color", "#555555");
	        $("#payMethodCash").css("font-weight", "lighter");
	        $("#payMethodKakao").css("background-color", "#FFF");
	        $("#payMethodKakao").css("color", "#555555");
	        $("#payMethodKakao").css("font-weight", "lighter");
	        
	        //카드 결제 버튼의 css를 바꿔준다.
	        $("#payMethodCard").css("background-color", "#000");
	        $("#payMethodCard").css("color", "#FFF");
	        $("#payMethodCard").css("border", "1px solid #cccccc");
	        $("#payMethodCard").css("box-shadow", "none");
	        $("#payMethodCard").css("font-weight", "bold");
	        
	        //결제수단 input 값을 바꿔준다.
	        $("#payMethodInput").attr("value", "2");
	        
	        //계좌 이체 정보를 숨긴다.
	        $("#payMethodInfo").css("visibility", "hidden");
	    } else if(e.target.id === 'payMethodKakao'){ //카카오페이 버튼 클릭시
	        
	        //나머지 버튼의 css를 바꿔준다.
	        $("#payMethodCash").css("background-color", "#FFF");
	        $("#payMethodCash").css("color", "#555555");
	        $("#payMethodCash").css("font-weight", "lighter");
	        $("#payMethodCard").css("background-color", "#FFF");
	        $("#payMethodCard").css("color", "#555555");
	        $("#payMethodCard").css("font-weight", "lighter");
	        
	        //카카오페이 버튼의 css를 바꿔준다.
	        $("#payMethodKakao").css("background-color", "#000");
	        $("#payMethodKakao").css("color", "#FFF");
	        $("#payMethodKakao").css("border", "1px solid #cccccc");
	        $("#payMethodKakao").css("box-shadow", "none");
	        $("#payMethodKakao").css("font-weight", "bold");
	        
	        //결제수단 input 값을 바꿔준다.
	        $("#payMethodInput").attr("value", "3");
	        
	        //계좌 이체 정보를 숨긴다.
	        $("#payMethodInfo").css("visibility", "hidden");
	    }
	});


	//개인정보 수집 보기 클릭시
	$('#personalInfoCheckP').click(function(){
		
		if($("#personalInfoDetailDiv").css("display", "none")) { //안보일때
			alert("보이기");
			$("#personalInfoDetailDiv").css("display", "inline");
			
		} else if($("#personalInfoDetailDiv").css("display", "inline")){ //보일 때
			alert("숨기기");
			$("#personalInfoDetailDiv").css("display", "none");
		}
		
	});
});
 /* orderDetail javascript */
 
 //----------------------------------------------------------------------------------------------------------
//다음(카카오) API 호출 함수
//----------------------------------------------------------------------------------------------------------

function daumZipCode() {

	new daum.Postcode({
					
		oncomplete: function(data) {
			// 팝업창에서 검색한 결과에서 항목을 클릭하였을 경우에 실행할 코드를 이곳에 작성한다.
						
			// 각 주소의 노출 규칙에 따라서 주소를 조합해야 한다.
			// 내려오는 변수가 값이 없을 경우에는 공백('')값을 가지므로 이름을 참고하여 분기한다.
						
			var fullAddr = '';	// 최종 주소값을 저장할 변수
			var subAddr = '';	// 조합형 주소값을 저장할 변수
						
			// 사용자가 선택한 주소의 타입에 따라서 해당 주소 값을 가져온다.
			if(data.userSelectedType == 'R') {	// 도로명 주소를 선택한 경우	
			
				fullAddr		= data.roadAddress;
						
			}else {	// 지번 주소를 선택한 경우
			
				fullAddr		= data.jibunAddress;
				
			}	
						
			// 사용자가 선택한 주소가 도로명 타입일 때 조합한다.
			if(data.userSelectedType == 'R'){
				// 법정도명이 있을 경우에 추가한다.
				if(data.bname != ''){
					subAddr += data.bname;
				}
				// 건물명이 있을 경우에 추가한다.
				if(data.buildingName != ''){
					subAddr += (subAddr != '' ? ', ' + data.buildingName : data.buildingName);
				}
				// 조합형 주소의 유무에 따라서 양쪽에 괄호를 추가하여 최종 주소를 만든다.
				fullAddr += (subAddr != '' ? '(' + subAddr +' )' : ' ');
			}
						
			// 우편번호와 주소정보를 화면의 해당 필드에 출력시킨다.
			document.getElementById('deliveryPostnum').value	= data.zonecode;	// 5자리의 새 우편번호
			document.getElementById('deliveryAddress1').value	= fullAddr;
						
			// 커서를 상세주소 입력란으로 이동시킨다.
			document.getElementById('deliveryAddress2').focus();
		}
					
	}).open({
		//open에 이름을 부여하여 우편번호 팝업창이 여러개 뜨는 것을 방지하기 위해서 popupName을 사용한다.
		popupName:	'postcodePopup'
	});		
}
 
 //배송메모가 직접입력일 때
 function fn_orderMemo() {
 	
 	var select = document.getElementById("orderMemoSelect"); 
 	var input = document.getElementById("orderMemo"); 
 	//alert(select.value);
 	
 	
 	if(select.value == "makeDeliveryMemo") { //선택된 값이 직접입력일 때
 		//alert("직접 입력");
 		//input의 display를 block으로 바꿔준다.
 		input.style.display = 'block';
 		
 		//input의 value 값을 지워준다.
 		input.value = "";
 		
 	} else { //그외일때
 		//alert("그외");
 		
 		//input을 숨긴다.
 		input.style.display = 'none';
 		
 		//input의 value값에 select에서 선택한 텍스트를 넣어준다.
 		var order_memo = $("#orderMemoSelect option:selected").text();
 		input.value = order_memo;
 		
 		//alert(input.value);
 	}
 }
 
 //최종 결제 버튼 클릭시
function fn_orderComplete() {

	//동의 항목 검사
	if(!$('#personalInfoCheck').is(':checked')) {
		alert("개인정보 수집·이용에 동의에 채크 해주세요");
		$("#personalInfoCheck").focus();
		return false;
	}
	if(!$('#orderOkCheckbox').is(':checked')) {
		alert("구매 동의에 체크 해주세요.");
		$("#orderOkCheckbox").focus();
		return false;
	}
	
	//클릭한 버튼에 따라 결제 수단 처리를 한다.
	var payMethod = $("#payMethodInput").val(); //결제 수단 value 값
	
	if(payMethod == "1" ) {
		//alert("계좌이체");
	} else if(payMethod == "2" ) {
	
		//넘겨줄 정보를 준비한다.
		//구매 상품 기본 정보
		var cartCount = $("#cartCount").val(); //장바구니 상품 갯수
		var firstProduct = $("#firstProduct").val(); //첫번째 상품 이름
		//결제 창에서 보여줄 상품 이름을 세팅한다. (첫번째 상품... 외 몇건)
		var cartName = "";
		if(cartCount == 1 ) {
			cartName = firstProduct.substr(0, 6) + "...";
		} else {
			cartName = firstProduct.substr(0, 6) + "... 외 " + (cartCount - 1) + "건";
		}
		var sum = $("#totalBillTd").html();
		var totalBill = sum.replace(/[^0-9]/g, ""); //총 결제 금액
		
		//구매자 기본 정보
		var userID = $("#userID").val(); //주문자 아이디
		var email = $("#email").val(); //주문자 이메일
		var name = $("#name").val(); //주문자 이름
		var phone = $("#phone").val(); //주문자 휴대폰번호
		var address1 = $("#address1").val(); //주문자 주소1
		var address2 = $("#address2").val(); //주문자 주소2
		var postnum = $("#postnum").val(); //주문자 우편번호
		//alert(postnum);	
		
		IMP.init('imp80230473');
		
	  	//결제시 전달되는 정보
		IMP.request_pay({
					pg : 'html5_inicis.INIpayTest',
				    pay_method : 'card',
				    merchant_uid : 'merchant_' + new Date().getTime(),
				    name : cartName, 									/*상품명*/
				    amount : totalBill,									/*상품 가격*/
				    buyer_email : email,								/*구매자 이메일*/
				    buyer_name : name,
				    buyer_tel : phone,									/*구매자 연락처*/
				    buyer_addr : address1 + ' ' + address2,				/*구매자 주소*/
				    buyer_postcode : postnum							/*구매자 우편번호*/
				}, function(rsp) {
					var result = '';
				    if ( rsp.success ) {
				    	//alert("결제가 완료되었습니다.\n");
				        //location.href="/order/orderComplete";
				        result ='0';
				        
				        //주문 완료 테이블로 넘기기 위한 
				        //alert(userID);
				        
				    } else {
				        alert("결제에 실패하였습니다. \n 에러 내용: " + rsp.error_msg);
				        result ='1';
				    }
				    if(result=='0') {
				    	location.href= $.getContextPath()+"/Cart/Success";
				    }
				    alert(msg);
				});
				
	} else if(payMethod == "3" ) { //카카오 페이일 때
		
		//넘겨줄 정보를 준비한다.
		//구매 상품 기본 정보
		var cartCount = $("#cartCount").val(); //장바구니 상품 갯수
		var firstProduct = $("#firstProduct").val(); //첫번째 상품 이름
		//결제 창에서 보여줄 상품 이름을 세팅한다. (첫번째 상품... 외 몇건)
		var cartName = "";
		if(cartCount == 1 ) {
			cartName = firstProduct.substr(0, 6) + "...";
		} else {
			cartName = firstProduct.substr(0, 6) + "... 외 " + (cartCount - 1) + "건";
		}
		var sum = $("#totalBillTd").html();
		var totalBill = sum.replace(/[^0-9]/g, ""); //총 결제 금액
		
		//구매자 기본 정보
		var userID = $("#userID").val(); //주문자 아이디
		var email = $("#email").val(); //주문자 이메일
		var name = $("#name").val(); //주문자 이름
		var phone = $("#phone").val(); //주문자 휴대폰번호
		var address1 = $("#address1").val(); //주문자 주소1
		var address2 = $("#address2").val(); //주문자 주소2
		var postnum = $("#postnum").val(); //주문자 우편번호
		//alert(postnum);	
		
		//가맹점 식별코드 imp80230473  location.href = "/order/orderComplete";
        IMP.init('imp80230473');
        IMP.request_pay({
	        pg: 'kakaopay',
	        pay_method : 'card',
	        userID : userID + new Date().getTime(),
	        name : 'ShoppingProject' , 				//결제창에서 보여질 이름
	        amount : totalBill, 					//실제 결제되는 가격
	          
	        buyer_name : name,
	        buyer_phone : phone,
	        buyer_email : email,
	        buyer_addr : address1 + " " + address2,
	        buyer_postcode : postnum,
          
        }, 
        function(rsp) {
	        console.log(rsp);
	        
	        if ( rsp.success ) { //결제 성공시
		        //alert("결제가 완료되었습니다.");
	       		
	       		//수령인 정보, 주문 정보들을 준비한다
	       		let formData = new FormData(); 							//데이터를 담아줄 FormData
	       		var order_name = $("#deliveryName").val(); 				//수령인 이름
	       		var address1 = $("#deliveryAddress1").val(); 			//배송지1
	       		var address2 = $("#deliveryAddress2").val(); 			//배송지2
	       		var postnum = $("#deliveryPostnum").val();			 	//우편번호
	       		var phone = $("#deliveryPhone").val(); 					//전화번호
	       		var order_memo = $("#orderMemo").val() //배송메모
	       		
	       		var checkbox = document.querySelectorAll("input[name='cartNum']:checked"); //체크된 장바구니 번호 input
				var cartNumList = []; 														//넘겨줄 장바구니 배열
				for(let i = 0; i < checkbox.length; i++) {
					//alert(checkbox[i].value);
					cartNumList[i] = checkbox[i].value;										//배열 형태로 담아준다.
				}
				//alert(cartNumList);
				
				
				//데이터들을 formData에 담아준다.
				formData.append("cartNumberList", cartNumList); 			//장바구니 번호 배열
	       		formData.append("userID", userID); 							//회원 아이디
	       		formData.append("order_name", order_name); 					//수령인 이름
	       		formData.append("address1", address1);				 		//수령인 주소1
	       		formData.append("address2", address2); 						//수령인 주소2
	       		formData.append("postnum", postnum); 						//수령인 우편번호
	       		formData.append("order_phone", phone); 						//수령인 휴대폰 번호
	       		formData.append("order_memo", order_memo); 					//수령인 배송 메모
	       		formData.append("paymethod", "카카오페이");					//결제수단
	       		formData.append("totalbill", totalBill);					//총주문금액
	       		
	       		//alert(formData.get("cartNumberList"));
	       		//alert(formData.get("userID"));
	       		//alert(formData.get("address1"));
	       		//alert(formData.get("address2"));
	       		//alert(formData.get("postnum"));
	       		//alert(formData.get("order_phone"));
	       		//alert(formData.get("order_memo"));
	       		
	       		//ajax로 formData를 넘겨준다.
	       		$.ajax({
	       			type: "post",
	       			url: "/order/orderComplete",
	       			processData: false,
					contentType: false,
	       			data: formData,
	       			success: function(data){
						alert("주문 결제 완료. 주문번호: " + data);
						location.href="/order/orderCompleteForm?order_num=" + data;
																			
					},
	       			error: function(data, status, req) {
						alert("결제 완료 후 주문 완료 등록에 실패했습니다.");
					}
					
	       		}); //End - 결제 후 ajax로 데이터 등록
	       		
            } else {
	            var msg = '결제에 실패하였습니다.';
	            msg += '에러내용 : ' + rsp.error_msg;
	            alert(msg);
       		}
       		
   		}); //End - IMP카카오페이 결제
      
	} //End - 구매 방법 카카오페이일 때	
	
}
