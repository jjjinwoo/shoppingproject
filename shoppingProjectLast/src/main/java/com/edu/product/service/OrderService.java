package com.edu.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.edu.product.dto.CartDTO;
import com.edu.product.dto.OrderDTO;


public interface OrderService {

	//회원 아이디에 해당하는 장바구니 리스트 가져오기
	public List<CartDTO> cartList(String userID) throws Exception;
	
	//장바구니 정보 등록하기
	public int addCart(CartDTO cartDTO) throws Exception;
	
	//장바구니 번호에 해당하는 장바구니 정보 가져오기
	public List<CartDTO> cartNumList(int[] cartNum) throws Exception;
	
	//회원 아이디, 상품 코드에 해당하는 장바구니 번호 가져오기
	public int getCartNum(CartDTO cartDTO) throws Exception;
	
	//회원 아이디, 상품 코드에 해당하는 장바구니 갯수 가져오기
	public int cartCount(CartDTO cartDTO) throws Exception;
	
	//장바구니 번호에 해당하는 장바구니 정보 지우기
	public int cartDelete(int cartNum) throws Exception;
	
	//장바구니 번호에 해당하는 정보로 최종 금액 계산하기
	public int orderSum(int[] cartNum) throws Exception;
	
	//장바구니 번호 배열을 리스트 형태로 바꿔주기
	public List<Integer> cartNumberList(int[] cartNum) throws Exception;
	
	//주문 완료 테이블 번호 만들기
	public long getOrder_num() throws Exception;
	
	//주문 완료 정보 등록하고 해당 장바구니 삭제하기
	public int addOrder(int[] cartNumberList, OrderDTO orderDTO) throws Exception;
	
	//주문완료 번호에 해당하는 orderDTO 객체 가져오기
	public OrderDTO getOrderDTO(long order_num) throws Exception;
	
	//주문 완료 번호에 해당하는 상품 상세 정보를 oderDTO객체 리스트로 가져오기
	public List<OrderDTO> getOrderDetail(long order_num) throws Exception;
	
	//상품 디테일 리스트에 해당하는 정보로 최종 금액 계산하기
	public int orderListSum(List<OrderDTO> orderDetailList) throws Exception;
	
	//장바구니 번호에 해당하는 상품 사이즈, 수량 수정하기
	public int cartUpdate(CartDTO cartDTO) throws Exception;
	
	//월별 매출액 구하기
	public long monthBill(String month) throws Exception;
	
	//연령별 해당 달 매출액 구하기
	public long ageMonthBill(String month, int age) throws Exception; 
}
