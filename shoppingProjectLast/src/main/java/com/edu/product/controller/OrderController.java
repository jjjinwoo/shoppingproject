package com.edu.product.controller;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.edu.board.dao.BoardDAO;
import com.edu.board.dao.ReviewDAO;
import com.edu.product.dao.OrderDAO;
import com.edu.product.dao.ProductDAO;
import com.edu.product.dto.CartDTO;
import com.edu.product.dto.OrderDTO;
import com.edu.product.service.OrderService;

@Controller
@RequestMapping(value="/order/*")
public class OrderController {

	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@Inject
	OrderService orderService;
	@Inject
	OrderDAO orderDAO;
	@Inject
	ProductDAO productDAO;
	@Inject
	BoardDAO boardDAO;
	@Inject
	ReviewDAO reviewDAO;
	
	//장바구니 화면 이동하기 + 해당 유저의 장바구니 데이터 보내주기
	@RequestMapping(value="/cartForm", method=RequestMethod.GET)
	public ModelAndView cartForm(String userID) throws Exception {
		logger.info("OrderController의 cartForm 불러오기....");
		System.out.println("OrderController의 cartForm 불러오기.... 회원ID: " + userID);
		
		ModelAndView mav = new ModelAndView();
		
		//회원 아이디에 해당하는 장바구니 리스트를 가져와서 model에 담는다.
		List<CartDTO> cartList = orderService.cartList(userID);
		mav.addObject("cartList", cartList);
		

		//장바구니 리스트의 상품의 합계를 구해 model에 담아준다.
		//장바구니 리스트의 장바구니 번호를 배열로 만든다.
		int[] cartNum = new int[cartList.size()];
		for(int i = 0; i < cartList.size(); i++) {
			cartNum[i] = cartList.get(i).getCart_num();
		}
		mav.addObject("orderSum", orderService.orderSum(cartNum));
		
		//장바구니 페이지 주소를 세팅한다.
		mav.setViewName("/order/cartList");
		
		return mav;
	}
	
	//장바구니에 상품 담기(ajax 사용)
	//장바구니를 거치지 않고 바로 주문하기로 넘어갈 때 ajax로 장바구니에 해당 정보를 담고, 주문 상세페이지로 바로 보내준다. 
	//이때 장바구니 번호가 필요하기 때문에 등록 완료 후 장바구니 번호를 리턴 해주고 실패 했을 때는 -1을 리턴한다.
	@RequestMapping(value="/addCart", method=RequestMethod.POST)
	@ResponseBody
	public int addCart(CartDTO cartDTO) throws Exception {
		logger.info("OrderController의 addCart....");
		System.out.println("OrderController의 addCart.... CartDTO: " + cartDTO);
		
		//넘어온 유저 아이디가 있는지 확인한다.
		if(cartDTO.getUserID().equals("")) {
			return -1;
		} else {
			//해당 유저의 장바구니에 해당 상품이 있으면 더하면 안된다.
			if(orderService.cartCount(cartDTO) == 0 ) { //없을 때 더해준다.
				if(orderService.addCart(cartDTO) == 1) {
					//장바구니 등록 후 해당 유저의 해당상품의 장바구니번호를 가져와서 리턴
					return orderService.getCartNum(cartDTO);
				} else {
					return -3;
				}
			} else { //이미 있으면
				return -2;
			}
		}
		
		
		
	}
	
	//선택된 장바구니 번호를 배열로 받아서 장바구니 리스트를 주문 상세 페이지로 보내주기
	@ResponseBody
	@RequestMapping(value="/orderForm", method=RequestMethod.GET)
	public ModelAndView orderForm(int[] cartNum) throws Exception {
		logger.info("OrderController의 orderForm....");
		System.out.println("OrderController의 orderForm.... cartNum: " + cartNum[0]);
		
		ModelAndView mav = new ModelAndView();
		
		//넘어온 장바구니 번호로 List를 구해서 model에 담아준다
		mav.addObject("cartList", orderService.cartNumList(cartNum));
		System.out.println(orderService.cartNumList(cartNum));
		
		//상세페이지에서 보여줄 합계금액을 모델에 담아서 보내준다.
		mav.addObject("orderSum", orderService.orderSum(cartNum));
		
		//상세페이지 스크립트에서 이용할 장바구니 갯수를 구해 model에 담는다.
		mav.addObject("cartCount", cartNum.length);
		//상세페이지 스크립트에서 이용할 장바구니 번호 배열을 List 형태로 model에 담는다.
		mav.addObject("cartNumberList", orderService.cartNumberList(cartNum));
		
		//model에 주소를 세팅해준다.
		mav.setViewName("/order/orderDetail");
		return mav;
	}
	
	//상품 상세페이지에서 바로 주문하기를 누를 때 장바구니 정보를 가지고 주문 상세페이지로 이동
	@RequestMapping(value="/preOrderForm", method=RequestMethod.GET)
	public ModelAndView preOrderForm(int cartNum) throws Exception {
		System.out.println("OrderController의 preOrderForm.... cartNum: " + cartNum);
		
		ModelAndView mav = new ModelAndView();
		
		//넘어온 장바구니 번호를 배열 형태로 만든다.
		int[] num = {cartNum};
		
		//장바구니 번호로 리스트를 구해 model에 담는다.
		mav.addObject("cartList", orderService.cartNumList(num));
		
		//상세페이지에서 보여줄 합계금액을 model에 담는다.
		mav.addObject("orderSum", orderService.orderSum(num));
		
		//상세페이지에서 이용할 장바구니 갯수를 구해 model에 담는다.
		mav.addObject("cartCount", num.length);
		
		//상세페이지 스크립트에서 이용할 장바구니 번호 배열을 List 형태로 model에 담는다.
		mav.addObject("cartNumberList", orderService.cartNumberList(num));
		
		//주소를 세팅한다.
		mav.setViewName("/order/orderDetail");
		
		return mav;
	}
	
	//장바구니에서 상품 삭제하기
	@ResponseBody
	@RequestMapping(value="/cartDelete", method=RequestMethod.POST)
	public String cartDelete(int[] cartNum, String userID) throws Exception {
		System.out.println("OrderController의 cartDelete.... cartNum: " + cartNum[0]);
		
		//장바구니 번호만큼 반복문으로 삭제를 진행한다.
		int result = 0;
		for(int i = 0; i < cartNum.length; i++) {
			result += orderService.cartDelete(cartNum[i]);
		}
		
		if(result == cartNum.length) { //전부 삭제에 성공 했으면
			return "Y";
		} else {
			return "N";
		}
	}
	
	//주문 완료 처리후 주문 번호 리턴 (t_cart 데이터 +알파 를 t_order 테이블로 옮기기)
	@ResponseBody
	@RequestMapping(value="/orderComplete", method=RequestMethod.POST)
	public long orderComplete(int[] cartNumberList, OrderDTO orderDTO, HttpServletRequest request) throws Exception {
		System.out.println("OrderController의 orderComplete.... 회원 아이디: " + orderDTO.getUserID() + "장바구니 번호: " + cartNumberList[0]);	
		
		//orderDTO에 order_num을 계산해서 세팅한다.
		orderDTO.setOrder_num(orderService.getOrder_num());
		System.out.println("주문번호: " + orderDTO.getOrder_num());
		
		//orderDTO로 주문 완료 테이블에 데이터를 등록한다.
		if(orderService.addOrder(cartNumberList, orderDTO) == cartNumberList.length) { //성공시
			
			//아이디로 t_orderProduct 테이블에서 데이터를 리스트형으로 받아와서 세션에 넣어준다(productDetail에서 사용하기 위해)
			HttpSession session = request.getSession();
		    List<OrderDTO> orderDetailList = orderDAO.getOrderDetailById(orderDTO.getUserID());
		    session.setAttribute("member1OrderDetail",    orderDetailList);
		    System.out.println("로그인한 회원의 주문 리스트: " + orderDetailList);
		    
			return orderDTO.getOrder_num();
			
		} else {
			return 0;
		}
		
		
	}
	
	//주문 성공 페이지 이동하기
	@RequestMapping(value="/orderCompleteForm", method=RequestMethod.GET)
	public ModelAndView orderCompleteForm(long order_num) throws Exception {
		System.out.println("OrderController의 orderCompleteForm.... 주문번호: " + order_num);	
		
		ModelAndView mav = new ModelAndView();
		
		//주문번호에 해당하는 orderDTO 객체를 가져와서 model에 담는다.
		mav.addObject("orderDTO", orderService.getOrderDTO(order_num));
		
		//주문번호에 해당하는 상품 상세가 담긴 orderDTO 리스트를 가져와서 model에 담는다.
		List<OrderDTO> orderDetailList = orderService.getOrderDetail(order_num);
		mav.addObject("orderDetailList", orderDetailList);
		
		//주문 완료 페이지에서 사용할 합계 금액을 구해서 model에 담는다.
		mav.addObject("orderSum", orderService.orderListSum(orderDetailList));
		
		//주소를 세팅한다.
		mav.setViewName("/order/orderComplete");
		
		return mav;
	}
	
	//주문 완료 상세 페이지 이동하기
	@RequestMapping(value="/orderCompleteDetail", method=RequestMethod.GET)
	public ModelAndView orderCompleteDetail(long order_num) throws Exception {
		System.out.println("OrderController의 orderCompleteForm.... 주문번호: " + order_num);
		
		ModelAndView mav = new ModelAndView();
		
		//주문번호에 해당하는 orderDTO 객체를 가져와서 model에 담는다.
		mav.addObject("orderDTO", orderService.getOrderDTO(order_num));
		
		//주문번호에 해당하는 상품 상세가 담긴 orderDTO 리스트를 가져와서 model에 담는다.
		List<OrderDTO> orderDetailList = orderService.getOrderDetail(order_num);
		mav.addObject("orderDetailList", orderDetailList);
		
		//주문 완료 페이지에서 사용할 합계 금액을 구해서 model에 담는다.
		mav.addObject("orderSum", orderService.orderListSum(orderDetailList));
		
		//주소를 세팅한다.
		mav.setViewName("/order/orderCompleteDetail");
		
		return mav;
	}
	
	//장바구니 리스트에서 데이터 수정하기
	@ResponseBody
	@RequestMapping(value="/cartUpdate", method=RequestMethod.POST)
	public int cartUpdate(CartDTO cartDTO) throws Exception {
		System.out.println("OrderController의 cartUpdate.... 장바구니 정보 " + cartDTO);
		
		//장바구니 번호에 해당하는 데이터를 수정한다.
		int result = orderService.cartUpdate(cartDTO);
		
		return result;
	}
	//상품 관리 페이지 이동
	@RequestMapping(value="/orderManagement", method=RequestMethod.GET)
	public ModelAndView productMenu() throws Exception {
		System.out.println("ProductController의 orderManagement 불러오기....");
		
		ModelAndView mav = new ModelAndView();
		
		//상품 전체 리스트를 가져와서 모델에 담는다.
		mav.addObject("productList", productDAO.productList());
		
		//qna 전체 리스트를 가져와서 모델에 담는다.
		mav.addObject("boardList", boardDAO.boardTotalList());
		System.out.println(boardDAO.boardTotalList());
		
		//review 전체 리스트를 가져와서 모델에 담는다.
		mav.addObject("reviewList", reviewDAO.reviewTotalList());
		
		//주문 완료 전체 리스트를 가져와서 모델에 담는다.
		mav.addObject("orderList", orderDAO.orderTotalList());
		System.out.println();
		
		//주소를 세팅한다.
		mav.setViewName("/order/orderManagement");
		return mav;
	}
	
	//쇼핑몰 통계 페이지 이동하기(데이터 함께 보내주기)
	@RequestMapping(value="/orderStats", method=RequestMethod.GET)
	public ModelAndView orderStats() throws Exception {
		System.out.println("OrderController의 orderStats() 불러오기....");
		
		ModelAndView mav = new ModelAndView();
		
		//이번달과 지난달을 구한다.
		Calendar cal = Calendar.getInstance(); //날짜 정보를 얻기 위한 캘린더 인스턴스
		String thisMonth = cal.get(Calendar.YEAR) + "-" + new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1);
		String lastMonth = cal.get(Calendar.YEAR) + "-" + new DecimalFormat("00").format(cal.get(Calendar.MONTH));
		
		//전월 매출액과 이번달 매출액을 계산해서 모델에 담아준다.
		mav.addObject("TMBill", orderService.monthBill(thisMonth));
		mav.addObject("LMBill", orderService.monthBill(lastMonth));

		//연령별 매출액을 지난달과 이번달에 따라 구한다.
		mav.addObject("TMBill_10", orderService.ageMonthBill(thisMonth, 10));
		mav.addObject("LMBill_10", orderService.ageMonthBill(lastMonth, 10));
		mav.addObject("TMBill_20", orderService.ageMonthBill(thisMonth, 20));
		mav.addObject("LMBill_20", orderService.ageMonthBill(lastMonth, 20));
		mav.addObject("TMBill_30", orderService.ageMonthBill(thisMonth, 30));
		mav.addObject("LMBill_30", orderService.ageMonthBill(lastMonth, 30));
		mav.addObject("TMBill_40", orderService.ageMonthBill(thisMonth, 40));
		mav.addObject("LMBill_40", orderService.ageMonthBill(lastMonth, 40));
		
		System.out.println(mav);
		//주소를 세팅한다.
		mav.setViewName("/order/orderStats");
		
		return mav;
	}
	
}

