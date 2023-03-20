package com.edu.board.controller;

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

import com.edu.board.dto.ReviewDTO;
import com.edu.board.service.ReviewService;
import com.edu.common.util.PageMaker;
import com.edu.common.util.ReviewCriteria;
import com.edu.product.dao.OrderDAO;
import com.edu.product.dto.OrderDTO;

//-----------------------------------------------------------------------------------------------------------
// public class ReviewController
//-----------------------------------------------------------------------------------------------------------
@Controller
@RequestMapping(value = "/review")
public class ReviewController {

	private static final Logger logger = LoggerFactory.getLogger(ReviewController.class);
	
	@Inject
	private ReviewService reviewService;
	
	@Inject
	private OrderDAO orderDAO;
	
	
	//-----------------------------------------------------------------------------------------------------------
	// 주문 내역에서 리뷰 작성페이지로 이동 (상품 코드와 주문 번호를 받아서 t_orderProduct 테이블에서 해당하는 데이터를 보내준다.
	//-----------------------------------------------------------------------------------------------------------	
	@RequestMapping(value="/reviewRegisterForm", method=RequestMethod.GET)
	public ModelAndView reviewRegisterForm(OrderDTO orderDTO) throws Exception {
		System.out.println("ReviewController reviewRegisterForm 불러오기...");	
		
		ModelAndView mav = new ModelAndView();
		
		//상품 등록 화면에서 필요한 데이터를 model에 담아준다.
		mav.addObject("orderDetail", orderDAO.getOrderDetailDTO(orderDTO));
		
		//주소를 세팅한다.
		mav.setViewName("/board/reviewRegisterForm");
		
		return mav;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 리뷰 등록 처리하기
	//-----------------------------------------------------------------------------------------------------------
	@ResponseBody
	@RequestMapping(value="/reviewRegister", method=RequestMethod.POST)
	public String reviewRegister(ReviewDTO reviewDTO, HttpServletRequest request) throws Exception {
		System.out.println("ReviewController reviewRegister 처리 시작...");			
		
		if(reviewService.reviewRegister(reviewDTO) == 1) { //등록에 성공하면
			
			//세션 상태를 다시 업데이트 해준다.(productDetail에서 사용하기 위해)
			HttpSession session = request.getSession();
		    List<OrderDTO> orderDetailList = orderDAO.getOrderDetailById(reviewDTO.getUserID());
		    session.setAttribute("member1OrderDetail",    orderDetailList);
		    System.out.println("로그인한 회원의 주문 리스트: " + orderDetailList);
			
			return reviewDTO.getUserID(); //유저 아이디를 반환한다.
			
		} else {
			return "N";
		}
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 리뷰 상세페이지 이동
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value="/reviewDetail", method=RequestMethod.GET)
	public ModelAndView reviewDetail(OrderDTO orderDTO) throws Exception {
		System.out.println("ReviewController 리뷰 상세 보기() 주문번호: " + orderDTO.getOrder_num());	
		
		ModelAndView mav = new ModelAndView();
		
		//상품 코드와 주문번호에 대한 주문 완료 상품 객체를 model에 담아준다.
		mav.addObject("orderDetail", orderDAO.getOrderDetailDTO(orderDTO));
		
		//작성한 리뷰에 대한 정보를 model에 담아준다.
		mav.addObject("reviewDTO", reviewService.getReviewDTO(orderDTO));
		System.out.println("reviewDTO: " + reviewService.getReviewDTO(orderDTO));
		
		//주소를 세팅한다.
		mav.setViewName("/board/reviewDetail");
		
		return mav;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 리뷰 삭제하기
	//-----------------------------------------------------------------------------------------------------------
	@ResponseBody
	@RequestMapping(value = "/reviewDelete", method = RequestMethod.POST)
	public String reviewDelete(int review_bno) throws Exception {
		System.out.println("ReviewController reviewDelete 리뷰 bno: " + review_bno);
		
		if(reviewService.reviewDelete(review_bno) == 1) {
			return "Y";
		} else {
			return "N";
		}
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 리뷰 삭제하기
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/reviewDelete", method = RequestMethod.GET)
	public String reviewDeleteGET(int review_bno) throws Exception {
		System.out.println("ReviewController reviewDeleteGET 리뷰 bno: " + review_bno);
		
		if(reviewService.reviewDelete(review_bno) == 1) {
			return "Y";
		} else {
			return "N";
		}
	}
	
} // end public class ReviewController