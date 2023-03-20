package com.edu.product.dao;

import java.util.HashMap;
import java.util.List;

import com.edu.product.dto.CartDTO;
import com.edu.product.dto.OrderDTO;

public interface OrderDAO {

	//회원 아이디에 해당하는 장바구니 리스트 가져오기
	public List<CartDTO> cartList(String userID) throws Exception;
	
	//장바구니 정보 등록하기
	public int addCart(CartDTO cartDTO) throws Exception;
	
	//장바구니 번호에 해당하는 장바구니 정보 가져오기
	public CartDTO cartNumList(int cartNum) throws Exception;
	
	//회원 아이디, 상품 코드에 해당하는 장바구니 번호 가져오기
	public int getCartNum(CartDTO cartDTO) throws Exception;
	
	//회원 아이디, 상품 코드에 해당하는 장바구니 갯수 가져오기
	public int cartCount(CartDTO cartDTO) throws Exception;	
	
	//장바구니 번호에 해당하는 장바구니 정보 지우기
	public int cartDelete(int cartNum) throws Exception;	
	
	//상품코드에 해당하는 장바구니 정보 지우기
	public int cartDeleteByCode(String product_code) throws Exception;
	
	//해당 날짜의 주문건수 구하기
	public int orderDateCount(String date) throws Exception;
	
	//가장 큰 주문번호 구하기
	public long getMaxOrderNum() throws Exception;
	
	//주문 완료 정보 등록하기
	public int addOrder(OrderDTO orderDTO) throws Exception;	

	//주문 완료 상품 등록하기
	public int addOrderProduct(OrderDTO orderDTO) throws Exception;
	
	//주문완료 번호에 해당하는 orderDTO 객체 가져오기(t_order)
	public OrderDTO getOrderDTO(long order_num) throws Exception;
	
	//주문 완료 번호와 상품 코드에 해당하는 orderDTO 객체 가져오기 (t_orderProduct)
	public OrderDTO getOrderDetailDTO(OrderDTO orderDTO) throws Exception;
	
	//주문 완료 번호에 해당하는 상품 상세 정보를 oderDTO객체 리스트로 가져오기(t_orderProduct)
	public List<OrderDTO> getOrderDetail(long order_num) throws Exception;	
	
	//회원 아이디에 해당하는 주문번호 리스트 구하기
	public List<Long> getOrderNum(String userID) throws Exception;
	
	//주문 완료 번호 리스트에 해당하는 orderDTO리스트 구하기
	public List<OrderDTO> getOrderList(List<Long> orderNumList) throws Exception;
	
	//장바구니 번호에 해당하는 상품 사이즈, 수량 수정하기
	public int cartUpdate(CartDTO cartDTO) throws Exception;	
	
	//회원 아이디에 해당하는 모든 t_orderProduct 데이터 가져오기
	public List<OrderDTO> getOrderDetailById(String userID) throws Exception;
	
	//전체 t_order 테이블 데이터 리스트 구하기
	public List<OrderDTO> orderTotalList() throws Exception;
	
	//월별 매출액 구하기
		public List<Long> monthBill(String month) throws Exception;	

	//연령대별 해당 월 매출액 구하기
	public Long ageMonthBill(HashMap<String, String> dataList) throws Exception;
}
