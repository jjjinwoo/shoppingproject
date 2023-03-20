package com.edu.board.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.edu.board.dao.ReviewDAO;
import com.edu.board.dto.ReviewDTO;
import com.edu.common.util.ReviewCriteria;
import com.edu.product.dto.OrderDTO;

//-----------------------------------------------------------------------------------------------------------
// public class ReviewServiceImpl implements ReviewService
//-----------------------------------------------------------------------------------------------------------
@Service("reviewService")
public class ReviewServiceImpl implements ReviewService {

	private static final Logger logger = LoggerFactory.getLogger(ReviewServiceImpl.class);
	
	@Inject
	private ReviewDAO reviewDAO;
	
	//-----------------------------------------------------------------------------------------------------------
	// 리뷰 등록하기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int reviewRegister(ReviewDTO reviewDTO) throws Exception {
		System.out.println("BoardServiceImpl reviewRegister() 시작");
		
		return reviewDAO.reviewRegister(reviewDTO);
		
	} // end public int boardRegister(ReviewDTO reviewDTO) throws Exception
	
	//-----------------------------------------------------------------------------------------------------------
	// 상품 코드에 해당하는 리뷰 목록 보기 + paging 처리
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public List<ReviewDTO> reviewProductList(ReviewCriteria rCri) throws Exception {
		System.out.println("BoardServiceImpl reviewProductList() 시작");
		
		return reviewDAO.reviewProductList(rCri);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 리뷰 전체 목록 보기 + paging
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public List<ReviewDTO> reviewList(ReviewCriteria rCri) throws Exception {
		System.out.println("ReviewDAOImpl reviewList() 시작");
		
		return reviewDAO.reviewList(rCri);
	}	
	
	//리뷰 리스트 작성자 익명화하기
	@Override
	public List<ReviewDTO> userIDAnony(List<ReviewDTO> reviewList) throws Exception {
		System.out.println("BoardServiceImpl userIDAnony() 시작");
		
		for(int i = 0; i < reviewList.size(); i++) {
			String userID = reviewList.get(i).getUserID().substring(0, 2); //회원 아이디 앞 두글자만 자른다.
			userID = userID + "*****"; //뒤에 별을 붙여준다.
			reviewList.get(i).setUserID(userID); //새로 만든 회원 아이디를 세팅해준다.
		}
		
		return reviewList;
	}

	//-----------------------------------------------------------------------------------------------------------
	// 리뷰 목록에서 리뷰 제목 보이는 글자 수 제한 후 목록 반환
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public List<ReviewDTO> reviewSubAnony(List<ReviewDTO> reviewList) throws Exception {
		System.out.println("BoardServiceImpl reviewSubAnony() 시작");
		
		for(int i = 0; i < reviewList.size(); i++) {
			String review_subject = reviewList.get(i).getReview_subject();
			if(review_subject.length() > 10) {
				review_subject = review_subject.substring(0, 10);
				review_subject = review_subject + "...";
				reviewList.get(i).setReview_subject(review_subject);
			}
		}
		
		return reviewList;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 리뷰 목록에서 리뷰 내용 보이는 글자 수 제한 후 목록 반환
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public List<ReviewDTO> reviewConAnony(List<ReviewDTO> reviewList) throws Exception {
		System.out.println("BoardServiceImpl reviewConAnony() 시작");
		
		for(int i = 0; i < reviewList.size(); i++) {
			String review_content = reviewList.get(i).getReview_content();
			if(review_content.length() > 10) {
				review_content = review_content.substring(0, 10);
				review_content = review_content + "...";
				reviewList.get(i).setReview_content(review_content);
			}
		}
		
		return reviewList;
	}	
	
	//리뷰 전체 리스트 갯수 구하기
	@Override
	public int reviewTotalCount(ReviewCriteria rCri) throws Exception {
		System.out.println("BoardServiceImpl reviewTotalCount() 시작");
		
		return reviewDAO.reviewTotalCount(rCri);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 리뷰 삭제하기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int reviewDelete(int review_bno) {
		System.out.println("BoardServiceImpl reviewDelete() 시작");
		return reviewDAO.reviewDelete(review_bno);
	}
	
	//회원 아이디 + 상품코드에 대한 리뷰 가져오기
	@Override
	public ReviewDTO getReviewDTO(OrderDTO orderDTO) throws Exception {
		System.out.println("BoardServiceImpl getReviewDTO() 시작");
		
		return reviewDAO.getReviewDTO(orderDTO);
	}

} // end public class ReviewServiceImpl implements ReviewService