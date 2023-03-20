package com.edu.board.dao;

import java.util.List;

import com.edu.board.dto.ReviewDTO;
import com.edu.common.util.ReviewCriteria;
import com.edu.product.dto.OrderDTO;

//-----------------------------------------------------------------------------------------------------------
// public interface ReviewDAO
//-----------------------------------------------------------------------------------------------------------
public interface ReviewDAO {

	//-----------------------------------------------------------------------------------------------------------
	// 리뷰 등록하기
	//-----------------------------------------------------------------------------------------------------------
	public int reviewRegister(ReviewDTO reviewDTO);
	
	//-----------------------------------------------------------------------------------------------------------
	// 상품 코드에 해당하는 리뷰 목록 보기
	//-----------------------------------------------------------------------------------------------------------
	public List<ReviewDTO> reviewProductList(ReviewCriteria rCri) throws Exception;
	
	//상품 코드에 해당하는 리뷰 전체 갯수 구하기
	public int reviewTotalCount(ReviewCriteria rCri) throws Exception;
	
	//-----------------------------------------------------------------------------------------------------------
	// 리뷰 전체 목록 보기 + paging
	//-----------------------------------------------------------------------------------------------------------
	public List<ReviewDTO> reviewList(ReviewCriteria rCri) throws Exception;	
	
	//-----------------------------------------------------------------------------------------------------------
	// 리뷰 해당아이디에 속한것만 전체 목록 보기
	//-----------------------------------------------------------------------------------------------------------
	public List<ReviewDTO> reviewUserList(String userID) throws Exception;
	
	//-----------------------------------------------------------------------------------------------------------
	// 리뷰 삭제하기
	//-----------------------------------------------------------------------------------------------------------
	public int reviewDelete(int review_bno);	
	
	//리뷰 전체 목록 보기
	public List<ReviewDTO> reviewTotalList() throws Exception;	
	
	//회원 아이디 + 상품코드에 대한 리뷰 가져오기
	public ReviewDTO getReviewDTO(OrderDTO orderDTO)  throws Exception;
	
} // end public interface ReviewDAO