package com.edu.product.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.edu.product.dao.OrderDAO;
import com.edu.product.dto.CartDTO;
import com.edu.product.dto.OrderDTO;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

	@Inject
	OrderDAO orderDAO;
	
	@Override
	public List<CartDTO> cartList(String userID) throws Exception {
		System.out.println("OrderServiceImpl의 cartList()....");
		
		return orderDAO.cartList(userID);
	}

	@Override
	public int addCart(CartDTO cartDTO) throws Exception {
		System.out.println("OrderServiceImpl의 addCart()....");
		
		return orderDAO.addCart(cartDTO);
	}

	@Override
	public List<CartDTO> cartNumList(int[] cartNum) throws Exception {
		System.out.println("OrderServiceImpl의 cartNumList()....");
		List<CartDTO> cartList = new ArrayList<CartDTO>();
		
		//넘어온 장바구니 번호 배열만큼 cartDTO를 가져와서 List에 담아준다.
		for(int i = 0; i < cartNum.length; i++) {
			cartList.add(i, orderDAO.cartNumList(cartNum[i]));
		}
		
		
		return cartList;
	}

	@Override
	public int getCartNum(CartDTO cartDTO) throws Exception {
		System.out.println("OrderServiceImpl의 cartNumList()....");
		
		return orderDAO.getCartNum(cartDTO);
	}

	@Override
	public int cartCount(CartDTO cartDTO) throws Exception {
		System.out.println("OrderServiceImpl의 cartCount()....");
		
		return orderDAO.cartCount(cartDTO);
	}

	@Override
	public int cartDelete(int cartNum) throws Exception {
		System.out.println("OrderServiceImpl의 cartDelete()....");
		
		return orderDAO.cartDelete(cartNum);
	}
	
	//주문 상세 페이지에서 보여줄 합계 금액을 반환하는 메소드
	@Override
	public int orderSum(int[] cartNum) throws Exception {
		System.out.println("OrderServiceImpl의 orderSum()....");
		
		//계산해줄 합계 변수 준비
		int result = 0;
		
		//반복문을 통해 장바구니에 해당하는 정보를 가져온 다음 계산해서 result에 더해준다.
		for(int i = 0; i < cartNum.length; i++) {
			CartDTO cartDTO = orderDAO.cartNumList(cartNum[i]); //번호에 해당하는 장바구니 정보
			
			int price = cartDTO.getProduct_price(); //해당 상품 가격
			int amount = cartDTO.getProduct_amount(); //해당 상품 주문 수량
			
			result += (price * amount); //상품 가격과 수량을 곱해서 result에 누적한다.
		}
		
		return result;
	}

	@Override
	public List<Integer> cartNumberList(int[] cartNum) throws Exception {
		System.out.println("OrderServiceImpl의 cartNumberList()....");
		List<Integer> cartNumberList = new ArrayList<Integer>();
		
		//반복문으로 리스트 만들기
		for(int i = 0; i < cartNum.length; i++) {
			cartNumberList.add(cartNum[i]);
		}
		
		return cartNumberList;
	}

	@Override
	public long getOrder_num() throws Exception {
		System.out.println("OrderServiceImpl의 getOrder_num()....");
		
		//현재 날짜를 구해서 DB의 날짜 형태로 만든다. ex)1999-01-01
		Calendar cal = Calendar.getInstance(); //날짜 정보를 얻기 위한 캘린더 인스턴스
		String date = cal.get(Calendar.YEAR) + "-";
		date += new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1) + "-";
		date += new DecimalFormat("00").format(cal.get(Calendar.DATE));
		System.out.println("현재 날짜: " + date);
		
		//현재 날짜에 해당하는 주문 건수가 없으면
		if(orderDAO.orderDateCount(date) == 0) {
			System.out.println("주문 건수 없음");
			
			//날짜뒤에 001을 붙이고 '-'를 제거한 뒤 숫자로 바꿔서 리턴한다.
			date = date + "001";
			date = date.replaceAll("[-]", "");
			long result = Long.parseLong(date);
			return result;
			
		} else { //건수가 있으면
			System.out.println("주문 건수 있음");
			
			//가장 큰 주문번호 + 1 한 값을 가져와 리턴한다.\
			return orderDAO.getMaxOrderNum();
			
		}
	}
	
	@Override
	public int addOrder(int[] cartNumberList, OrderDTO orderDTO) throws Exception {
		System.out.println("OrderServiceImpl의 addOrder().... 장바구니 번호: " + cartNumberList[0]);
		
		//배송 메모가 있을 경우 배송메모의 공백을 "_"로 치환해서 세팅해준다.
		if(orderDTO.getOrder_memo() != null) {
			orderDTO.setOrder_memo(orderDTO.getOrder_memo().replaceAll(" ", "_"));
		}
		
		//t_order 테이블에 먼저 데이터를 등록한다.
		if(orderDAO.addOrder(orderDTO) == 1) { //성공하면
			
			int count = 0;
			//반복문을 통해 장바구니 번호에 해당하는 cartDTO를 가져와서 해당 데이터로 orderDTO를 세팅한 후
			for(int i = 0; i < cartNumberList.length; i++) {
				
				//장바구니 번호에 해당하는 cartDTO를 가져온다.
				//System.out.println("장바구니번호: " + cartNumberList[i]);
				CartDTO cartDTO = orderDAO.cartNumList(cartNumberList[i]);
				
				//orderProductDTO에 값을 세팅한다.
				orderDTO.setProduct_code(cartDTO.getProduct_code());
				orderDTO.setProduct_amount(cartDTO.getProduct_amount());
				orderDTO.setProduct_size(cartDTO.getProduct_size());
				
				//t_orderProduct 테이블에 등록한다.
				count += orderDAO.addOrderProduct(orderDTO);
			}
			
			int result = 0;
			//모두 등록에 성공하면
			if(count == cartNumberList.length) {
				
				//반복문을 통해 장바구니 데이터를 삭제한다.
				for(int i = 0; i < cartNumberList.length; i++) {
					result += orderDAO.cartDelete(cartNumberList[i]);
				}
				
				if(result == cartNumberList.length) {
					return result;
				} else {
					return 0;
				}
			} else {
				return 0;
			}
		} else {
			return 0;
		}
		
	}


	@Override
	public OrderDTO getOrderDTO(long order_num) throws Exception {
		System.out.println("OrderServiceImpl의 getOrderDTO()....");
		OrderDTO orderDTO = orderDAO.getOrderDTO(order_num);
		
		//컨트롤러로 넘어가기 전에 배송메모를 수정해준다.
		String order_memo = orderDTO.getOrder_memo();
		order_memo = order_memo.replaceAll("[_]", " ");//_를 없앤다
		if(order_memo.contains("선택")) { //배송 메모를 선택해주세요 일때는 공백 처리
			order_memo = "&nbsp;";
		}
		System.out.println(order_memo);
		orderDTO.setOrder_memo(order_memo);
		
		return orderDTO;
	}

	@Override
	public List<OrderDTO> getOrderDetail(long order_num) throws Exception {
		System.out.println("OrderServiceImpl의 getOrderDetail()....");
		
		return orderDAO.getOrderDetail(order_num);
	}

	@Override
	public int orderListSum(List<OrderDTO> orderDetailList) throws Exception {
		System.out.println("OrderServiceImpl의 orderListSum()....");
		
		int result = 0;
		//반복문을 통해 합계를 구해준다.
		for(int i = 0; i < orderDetailList.size(); i++) {
			
			int price = orderDetailList.get(i).getProduct_price(); //상품의 가격
			int amount = orderDetailList.get(i).getProduct_amount(); //상품 수량
			
			result += (price * amount);
			
		}
		
		return result;
	}

	@Override
	public int cartUpdate(CartDTO cartDTO) throws Exception {
		System.out.println("OrderServiceImpl의 cartUpdate()....");
		
		return orderDAO.cartUpdate(cartDTO);
	}

	@Override
	public long monthBill(String month) throws Exception {
		System.out.println("OrderServiceImpl의 lastMonthBill()....");
		
		//DAO를 통해 월별 주문 금액 리스트를 구한다.
		List<Long> monthBillList = orderDAO.monthBill(month);
		
		//반복문을 통해 값을 더해준다.
		long result = 0;
		for(int i = 0; i < monthBillList.size(); i++) {
			result += monthBillList.get(i);
		}
		
		return result;
	}
	
	@Override
	public long ageMonthBill(String month, int age) throws Exception {
		System.out.println("OrderServiceImpl의 ageMonthBill()....");
		
		//month로 올해를 구한다.
		String year = month.substring(0, 4);
		
		//age에 따른 연도 범위를 구한다.
		String firstYear = (Integer.parseInt(year) - age - 8) + "-12-31";
		String lastYear = (Integer.parseInt(year) - age + 1) + "-01-01";
		
		//해당 값들을 hashmap에 담아준다.
		HashMap<String, String> dataList = new HashMap<String, String>();
		dataList.put("month", month);
		dataList.put("firstAge", firstYear);
		dataList.put("lastAge", lastYear);
		
		System.out.println("hashmap 값: " + dataList);
		
		
		return orderDAO.ageMonthBill(dataList);
	}
}

