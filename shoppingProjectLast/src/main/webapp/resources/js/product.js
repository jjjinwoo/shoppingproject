
//상품 상세페이지까지

/* jquery */
$(document).ready(function() {
						
	//alert("hi");
				
	//일반 상품 리스트에서selectbox option 선택이 바뀔 때 이벤트 처리
	$("#arrayOption").on("change", function() {
		
		//넘겨받은 상품 타입을 input으로 가져온다
		var ptype = $("#ptype").val();
					
		//selected 된 value 값
		var option = $(this).val();
							
		//선택된 값에 따라 처리 해준다
		if(option == "r"){
			location.href="/product/productTypeList?product_type=" + ptype + "&page=1&array_type=r";
		} else if(option == "o"){
			location.href="/product/productTypeList?product_type=" + ptype + "&page=1&array_type=o";
		} else {
			location.href="/product/productTypeList?product_type=" + ptype + "&page=1&array_type=p";
		}
	});
	
	//추천 상품 리스트에서 selectbox option 선택이 바뀔 때 이벤트 처리
	$("#arrayOptionR").on("change", function() {
		
		//상품 타입을 가져온다
		var ptype = $("#typeOption").val();
					
		//selected 된 value 값
		var option = $(this).val();
							
		//선택된 값에 따라 처리 해준다
		if(option == "r"){
			location.href="/product/searchForm?product_type=" + ptype + "&page=1&array_type=r";
		} else if(option == "o"){
			location.href="/product/searchForm?product_type=" + ptype + "&page=1&array_type=o";
		} else {
			location.href="/product/searchForm?product_type=" + ptype + "&page=1&array_type=p";
		}
	});
	
	//검색된 상품 리스트에서selectbox option 선택이 바뀔 때 이벤트 처리
	$("#arrayOptionS").on("change", function() {
		
		//넘겨받은 상품 타입을 input으로 가져온다
		var ptype = $("#ptype").val();
		var keyword = $("#keyword").val();
					
		//selected 된 value 값
		var option = $(this).val();
							
		//선택된 값에 따라 처리 해준다
		if(option == "r"){
			location.href="/product/productTypeList?product_type=" + ptype + "&searchType=" + ptype + "&page=1&array_type=r&keyword=" + keyword;
		} else if(option == "o"){
			location.href="/product/productTypeList?product_type=" + ptype + "&searchType=" + ptype + "&page=1&array_type=o&keyword=" + keyword;
		} else {
			location.href="/product/productTypeList?product_type=" + ptype + "&searchType=" + ptype + "&page=1&array_type=p&keyword=" + keyword;
		}
	});
	
	//검색 페이지에서 검색 버튼 눌렀을 때 처리
		
	var formObj = $("#formList");
	
	$("#searchBtn").click(function() {
	
		var typeStr		= $("#searchType").find(":selected").val();
		var keywordStr	= $("#searchKeyword").val();
		
		//검색 종류 값이 없을 때 선택하도록 한다.
		if(typeStr == "none") {
			alert("검색 종류를 선택해주세요");
			$("#searchType").focus();
			return false;
		}
		
		//검색 키워드가 없을 때 입력하도록 한다.
		if(keywordStr == "") {
			alert("검색 키워드를 입력해주세요");
			$("#searchKeyword").focus();
			return false;
		}
		
		//서버로 전송하기 전에, name 속성에 값을 넣어준다.
		formObj.find("[name='searchType']").val(typeStr);
		formObj.find("[name='keyword']").val(keywordStr);
		formObj.find("[name='page']").val("1");
		formObj.find("[name='array_type']").val("r");	//기본값 최신순
		formObj.find("[name='product_type']").val(typeStr);
		formObj.submit();
	});
	
	//상품 상세페이지 -> 장바구니 담기 버튼 눌렀을 때 처리 (장바구니로 이동 하거나 안하거나)
	$("#addcartBtn").click(function() {
	
		var product_size		= $("#product_size").find(":selected").val();
		var product_amount		= $("#product_amount").text();
		product_amount = parseInt(product_amount); //숫자로 바꿔준다.
		
		//alert(product_code + ", " + product_size + ", " + product_amount);
		
		//사이즈에 선택된 값이 없을 때
		if(product_size == "S I Z E"){
			alert("사이즈를 선택해주세요");
			return false;
		}
		//수량에서 선택된 값이 없을 때
		if(product_amount == "0"){
			alert("수량을 선택해주세요");
			return false;
		}
		
		var product_code	= $("#cartPCode").val();
		var userID	= $("#cartUserID").val();
		
		//alert("아이디: " + userID);
		
		$.ajax ({
			type: "POST",
			url: "/order/addCart",
			data: {product_code:product_code, product_size:product_size, product_amount:product_amount, userID:userID},
			success: function(data) {
			
				//오류 데이터 부터 처리
				if(data == -1) { //로그인 안됨
					if(confirm("로그인이 필요합니다. 로그인 하시겠습니까?")) {
						location.href="/member/loginForm.do";
					}
				} else if (data == -2) { //해당 상품이 이미 장바구니에 있으면
					if(confirm("장바구니 해당 상품이 있습니다. 장바구니에서 상품 정보를 수정하시겠습니까?")) {
						//장바구니 페이지로 보내준다.
						location.href="/order/cartForm?userID=" + userID;
					}
				} else if (data == -3) { //장바구니 등록 실패
					alert("장바구니 등록에 실패했습니다.");
				} else { //장바구니 등록 성공
					if(confirm("장바구니에 상품을 담았습니다. 장바구니로 이동하시겠습니까?")) {
						//장바구니 페이지로 이동한다.
						location.href="/order/cartForm?userID=" + userID;
					} 
				}
			},
			error: function(data) {
				alert("통신에 실패했습니다. 데이터: ");
			}
		
		});
		
	});		
	
	
	//상품 상세페이지 -> 주문하기 버튼 눌렀을 때 처리 (해당 상품 장바구니에 담은 후 주문 상세 페이지로 이동)
	$("#nocartBtn").click(function() {
	
		var product_code	= $("#cartPCode").val();
		var userID	= $("#cartUserID").val();
		var product_size		= $("#product_size").find(":selected").val();
		var product_amount		= $("#product_amount").text();
		product_amount = parseInt(product_amount); //숫자로 바꿔준다.
		
		//alert(product_code + ", " + product_size + ", " + product_amount);
		
		//사이즈에 선택된 값이 없을 때
		if(product_size == "S I Z E"){
			alert("사이즈를 선택해주세요");
			return false;
		}
		//수량에서 선택된 값이 없을 때
		if(product_amount == "C O U N T"){
			alert("수량을 선택해주세요");
			return false;
		}
		
		$.ajax ({
			type: "POST",
			url: "/order/addCart",
			data: {product_code:product_code, product_size:product_size, product_amount:product_amount, userID:userID},
			success: function(data) {
				if(data == -1) { //로그인안됨
					if(confirm("로그인이 필요합니다. 로그인 하시겠습니까?")) {
						location.href="/member/loginForm.do";
					}
				} else if(data == -2) {
					if(confirm("장바구니 해당 상품이 있습니다. 장바구니에서 상품 정보를 수정하시겠습니까?")) {
						//장바구니 페이지로 보내준다.
						location.href="/order/cartForm?userID=" + userID;
					}
				} else if (data == -3) { //장바구니 등록 실패
					alert("장바구니 등록에 실패했습니다.");
				} else { //장바구니 등록 성공
					alert("주문 페이지로 이동합니다.");
					
					location.href="/order/preOrderForm?cartNum=" + data;
				}
			},
			error: function(data) {
				alert("통신에 실패했습니다. 데이터: " + data);
			}
		
		});
		
	});
	
	
	
	//리뷰 테이블에서 제목을 눌렀을 때 밑에 내용이 나오게 하기
	$(".reviewSubjectA").click(function() {
		
		//alert("제목을 누름");
		var subject = $(this);
		
		//누른 요소의 td위 tr의 바로 다음 tr을 보여준다.
		subject.parent().parent().next().toggle(); 
		
	});
				
});

//상품 디테일 카운트증가에따른 가격 총합
		
	function fn_amountMinus() { 
		//count 에서 value값을 가져온다
		var product_amount		= $("#product_amount").text();
		
		//숫자값만 product_amount에 저장한다.
		var product_amount = parseInt(product_amount); 
		
		//id product_totalprice html을 가져온다
		var pdprice = $("#product_totalprice").html();
		
		//숫자값만 product_total에 저장한다.
		var product_totalprice = pdprice.replace(/[^0-9]/g, "");
		var product_totalprice = parseInt(product_totalprice); 
		
		//alert(product_totalprice);
		//alert(product_amount);
		//alert(product_amount*product_totalprice);
		
		//버튼을 누를시 값 -1
		if(product_amount >1) {
		product_amount = product_amount -1;
		$("#product_amount").text('');
		$("#product_amount").text(product_amount);
		
		var total=product_totalprice*product_amount;
		const total1 = String(total.toLocaleString('ko-KR'));
		
		
		$("#total").text('');
		$("#total").text(total1+'원');
		
		var totaltext="Total \u00a0\u00a0\u00a0:\u00a0\u00a0\u00a0 ";
		$("totaltext").text('');
		$("#totaltext").text(totaltext);
		
		
		}else{
		alert("1보다작은 수량을 주문할수없습니다");
		return false;
		}
		
	}	
	//상품 디테일 카운트증가에따른 가격 총합
		
	function fn_amountPlus() {
		//count 에서 value값을 가져온다
		var product_amount		= $("#product_amount").text();
		
		//숫자값만 product_amount에 저장한다.
		var product_amount = parseInt(product_amount); 
		
		
		//id product_totalprice html을 가져온다
		var pdprice = $("#product_totalprice").html();
		
		//숫자값만 product_total에 저장한다.
		var product_totalprice = pdprice.replace(/[^0-9]/g, "");
		var pdprice = parseInt(product_totalprice); 
		
		//alert(product_totalprice);
		//alert(product_amount);
		//alert(product_amount*product_totalprice);
		
		//버튼을 누를시 값 +1
		product_amount = product_amount +1;
		$("#product_amount").text('');
		$("#product_amount").text(product_amount);
		
		//제품수에따른 토탈값 
		var total=product_totalprice*product_amount;
		const total1 = String(total.toLocaleString('ko-KR'));
		
		$("#total").text('');
		$("#total").text(total1+'원');
		
		var totaltext="Total \u00a0\u00a0\u00a0:\u00a0\u00a0\u00a0 ";
		$("totaltext").text('');
		$("#totaltext").text(totaltext);
		
	}	

//상품 등록 함수
function fn_productRegister() {
	
	//input 항목들 가져오기
	var product_name = document.getElementById("product_name").value;
	var product_type = document.getElementById("product_type").value;
	var product_price = document.getElementById("product_price").value;
	var file = document.getElementById("file");
	
	//상품 이름 입력을 확인한다.
	if(product_name.length == 0) { //이름이 없으면
		alert("상품 이름은 필수 등록 항목입니다. 상품 이름을 입력해주세요.");
		product_name.focus();
		return false;
	}	

	//상품 가격 입력을 확인한다.
	if(product_price.length == 0) { //가격이 없으면
		alert("상품 가격은 필수 등록 항목입니다. 상품 가격을 입력해주세요.");
		product_price.focus();
		return false;
	}
	
	//이미지 파일이 있는지 확인한다.
	if(file.files.length == 0) { //파일이 없으면
		alert("상품 이미지는 필수 등록 항목입니다. 이미지 파일을 등록해주세요.");
		file.focus();
		return false;
	} else { //파일이 있으면 이미지파일이 맞는지 함수로 확인한다.
		let count = 0;
		for(let i = 0; i < file.files.length; i++) { //들어온 파일 갯수만큼  반복문으로 확인한다.
			if(!checkImageType(file.files[i].name)) {
				count++;
			}
		}
		if(count > 0) { //이미지가 아닌 파일이 하나라도 있으면
			alert("업로드 할 수 있는 파일 형식은 jpg, png, jpeg, gif 입니다.");
			file.focus();
			return false;
		}
	
	}
	
	//상품 정보를 먼저 등록한 후 상품 코드를 리턴 받아 이미지를 등록한다.
	if(confirm("상품 이름: " + product_name + "\n상품 타입: " + product_type + "\n상품 가격: " + product_price + "\n\n해당 상품을 등록하시겠습니까?")) {
		
		$.ajax ({
			type: "post",
			url: "/product/productRegister",
			data: {product_name:product_name, product_type:product_type, product_price:product_price},
			success : function(data) {
				
				if(data != "0") {
					//이미지 등록을 위한 FormData() 준비
					let formData = new FormData();
					
					//업로드 된 이미지만큼 반복문으로 FormData에 넣어준다.
					for(let i = 0; i < file.files.length; i++) {
						formData.append("files", file.files[i]);
						//alert("formData:" + formData.get("files").name);
					}
					
					//formData에 상품 코드를 넣어준다.
					formData.append("product_code", data);
					
					//ajax로 업로드 한다.
					$.ajax({
						type: "post",
						url: "/image/uploadImage",
						processData: false,
						contentType: false,
						data: formData,
						dataType: "json",
						success: function(data, status, req){
							//alert("상품 등록 성공" + data.product_code);
							location.href="/product/productDetail?product_code=" + data.product_code + "&reviewPageNum=1";
																				
						},
						error: function(data, status, req) {
							alert("상품 이미지 등록에 실패하였습니다.");
							
							//상품은 등록이 된 상태이기 때문에 등록된 상품 정보를 지워준다.
							location.href="/product/productRegDelete?product_code=" + data.product_code;
						}
					});	//end - 이미지 등록			
				}
			},
			error: function(data) {
				alert("상품 등록에 실패하였습니다.");
			}
		}); //end - 상품 등록
	}

}

//파일 확장자 검사 함수
function checkImageType(fileName) {

	var pattern = /jpg|png|jpeg|gif/i; //정규표현식 (i: ignore case()(대소문자 무시), |: or)
	
	return fileName.match(pattern); //파일 이름 규칙에 맞는지 확인 후 참/거짓을 반환한다.
	
} //End - checkImageType();	



//상품 수정 함수
function fn_productUpdate() {
	
	//input 항목들 가져오기
	var product_name = document.getElementById("product_name").value;
	var product_type = document.getElementById("product_type").value;
	var product_price = document.getElementById("product_price").value;
	var product_code = document.getElementById("product_code").value;
	var file = document.getElementById("file");
	
	//상품 이름 입력을 확인한다.
	if(product_name.length == 0) { //이름이 없으면
		alert("상품 이름은 필수 등록 항목입니다. 상품 이름을 입력해주세요.");
		product_name.focus();
		return false;
	}	

	//상품 가격 입력을 확인한다.
	if(product_price.length == 0) { //가격이 없으면
		alert("상품 가격은 필수 등록 항목입니다. 상품 가격을 입력해주세요.");
		product_price.focus();
		return false;
	}
	
	//이미지 파일인지 확인한다.
	if(file.files.length != 0) { //파일이 있으면
		let count = 0;
		for(let i = 0; i < file.files.length; i++) { //들어온 파일 갯수만큼  반복문으로 확인한다.
			if(!checkImageType(file.files[i].name)) {
				count++;
			}
		}
		if(count > 0) { //이미지가 아닌 파일이 하나라도 있으면
			alert("업로드 할 수 있는 파일 형식은 jpg, png, jpeg, gif 입니다.");
			file.focus();
			return false;
		} else { //모두 이미지 파일이면
		
			//상품 정보를 먼저 수정한 후 상품 코드를 리턴 받아 이미지를 수정한다.
			if(confirm("상품 이름: " + product_name + "\n상품 타입: " + product_type + "\n상품 가격: " + product_price + "\n\n해당 정보로 수정하시겠습니까?")) {
				
				$.ajax ({
					type: "post",
					url: "/product/productUpdate",
					data: {product_code:product_code, product_name:product_name, product_type:product_type, product_price:product_price},
					success : function(data) {
						
						if(data != "0") {
							//이미지 등록을 위한 FormData() 준비
							let formData = new FormData();
							
							//업로드 된 이미지만큼 반복문으로 FormData에 넣어준다.
							for(let i = 0; i < file.files.length; i++) {
								formData.append("files", file.files[i]);
								//alert("formData:" + formData.get("files").name);
							}
							
							//formData에 상품 코드를 넣어준다.
							formData.append("product_code", data);
							
							//ajax로 업로드 한다.
							$.ajax({
								type: "post",
								url: "/image/updateImage",
								processData: false,
								contentType: false,
								data: formData,
								dataType: "json",
								success: function(data, status, req){
									//alert("상품 수정 성공" + data.product_code);
									location.href="/product/productDetail?product_code=" + data.product_code + "&reviewPageNum=1";
																						
								},
								error: function(data, status, req) {
									alert("상품 이미지 수정에 실패하였습니다.");
									
									//상품은 등록이 된 상태이기 때문에 등록된 상품 정보를 다시 바꾼다.
										var originType = document.getElementById("originPdType").value;
										var originPrice = document.getElementById("originPdPrice").value;
										var originName = document.getElementById("originPdName").value;
										var product_code = document.getElementById("product_code").value;
									//location.href="/product/productRegDelete?product_code=" + data.product_code;
								}
							});	//end - 이미지 수정		
						}
					},
					error: function(data) {
						alert("상품 등록에 실패하였습니다.");
					}
				}); //end - 상품 수정
			}
		}
		
	} else { //이미지 파일이 없으면
		if(!confirm("이미지 파일이 없습니다. 이미지 수정 없이 상품 정보만 수정하시겠습니까?")) { //취소를 누르면
			file.focus();
			return false;
		} else { //확인을 누르면
		
			if(confirm("상품 이름: " + product_name + "\n상품 타입: " + product_type + "\n상품 가격: " + product_price + "\n\n해당 정보로 수정하시겠습니까?")) {
			
				$.ajax ({
					type: "post",
					url: "/product/productUpdate",
					data: {product_code:product_code, product_name:product_name, product_type:product_type, product_price:product_price},
					success : function(data) {
						
						if(data != "0") {
							alert("상품 정보를 수정했습니다.");
							location.href="/product/productDetail?product_code=" + data + "&reviewPageNum=1";		
						}
					},
					error: function(data) {
						alert("상품 정보 수정에 실패하였습니다.");
					}
				}); //end - 상품 수정
			}
		}
	}

}


//상품 삭제 함수
function fn_productDelete(product_code, product_type) {

	if(confirm("상품코드 : " + product_code + " 에 해당하는 상품을 삭제하시겠습니까?")) {
		$.ajax ({
			type: "post",
			url: "/product/productDelete",
			data: {product_code:product_code},
			success : function(data) {
				
				if(data != "0") {
					alert("상품 정보를 삭제했습니다.");
					location.href="/product/productTypeList?product_type=" + product_type + "&page=1&array_type=r";		
				}
			},
			error: function(data) {
				alert("상품 정보 삭제에 실패하였습니다.");
			}
		}); //end - 상품 삭제
	}
}