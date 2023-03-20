package com.edu.board.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.edu.board.dto.ReviewDTO;
import com.edu.common.util.ReviewCriteria;
import com.edu.product.dto.OrderDTO;
import com.mysql.cj.protocol.x.SyncFlushDeflaterOutputStream;

//-----------------------------------------------------------------------------------------------------------
// public class ReviewDAOImpl implements ReviewDAO
//-----------------------------------------------------------------------------------------------------------
@Repository("reviewDAO")
public class ReviewDAOImpl implements ReviewDAO {

	private static final Logger logger = LoggerFactory.getLogger(ReviewDAOImpl.class);
	
	@Inject	// 의존 관계 주입(Defendency Inject, DI)
	private SqlSession sqlSession;
	
	// Namespace 조심하자 : xml의 namespace와 동일해야 한다.(대소문자 주의할 것)
	private static String Namespace = "com.edu.review";
	
	//-----------------------------------------------------------------------------------------------------------
	// 리뷰 등록하기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int reviewRegister(ReviewDTO reviewDTO) {
		
		System.out.println("ReviewDAOImpl reviewRegister() 시작 리뷰 정보: " + reviewDTO);
		
		//리뷰 데이터를 등록 하고 성공하면
		if(sqlSession.insert(Namespace + ".insertReview", reviewDTO) == 1) {
			
			//해당 상품 리뷰 건수, 별점 합계 항목을 업데이트 한다.
			if(sqlSession.update(Namespace + ".updateStar", reviewDTO) == 1) {
				System.out.println("별점 업데이트 성공");
				//성공시 해당 상품 평균 별점을 계산한다.(데이터 업데이트 후 계산 해야 함)
				if(sqlSession.update(Namespace + ".updateStarAvg", reviewDTO) == 1) {
					System.out.println("평균 별점 업데이트 성공");
					
					int num = sqlSession.update(Namespace + ".updateReviewYN", reviewDTO);
					System.out.println("리뷰등록유무 결과 값: " + num);
					
					//성공시 주문완료 상품 테이블에서 리뷰 유무 상태 업데이트
					if(num == 1) {
						System.out.println("리뷰 등록 유무 업데이트 성공");
						return 1; //최종 성공 시 1반환		
					} else {
						return 2;
					}
				} else {
					return 2;
				}
			} else {
				return 2;
			}
			
		} else {
			return 2;
		}
		
	} // end public int reviewRegister(ReviewDTO reviewDTO)
	
	//상품 코드에 해당하는 리뷰 리스트 구하기 + paging
	@Override
	public List<ReviewDTO> reviewProductList(ReviewCriteria rCri) throws Exception {
		System.out.println("ReviewDAOImpl reviewProductList() 시작");
		
		return sqlSession.selectList(Namespace + ".reviewProductList", rCri);
	}
	
	//리뷰 전체 목록 갯수 구하기
	@Override
	public int reviewTotalCount(ReviewCriteria rCri) throws Exception {
		System.out.println("ReviewDAOImpl reviewTotalCount() 시작");
		
		return sqlSession.selectOne(Namespace + ".totalCount", rCri);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 리뷰 전체 목록 보기 + paging
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public List<ReviewDTO> reviewList(ReviewCriteria rCri) throws Exception {
		System.out.println("ReviewDAOImpl reviewList() 시작");
		
		return sqlSession.selectList(Namespace + ".reviewList", rCri);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 해당 아이디에 속한것 리뷰 전체 목록 보기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public List<ReviewDTO> reviewUserList(String userID) throws Exception {
		System.out.println("ReviewDAOImpl reviewUserList() 시작");
		
		return sqlSession.selectList(Namespace + ".reviewUserList", userID);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 리뷰 삭제하기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int reviewDelete(int review_bno) {
		System.out.println("ReviewDAOImpl reviewDelete() 시작");
		return sqlSession.delete(Namespace + ".reviewDelete", review_bno);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 리뷰 전체 목록 가져오기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public List<ReviewDTO> reviewTotalList() throws Exception {
		System.out.println("ReviewDAOImpl reviewTotalList() 시작");
		
		return sqlSession.selectList(Namespace + ".reviewTotalList");
	}

	@Override
	public ReviewDTO getReviewDTO(OrderDTO orderDTO) throws Exception {
		System.out.println("ReviewDAOImpl getReviewDTO() 시작");
		
		return sqlSession.selectOne(Namespace + ".getReviewDTO", orderDTO);
	}
	
	
} // end public class ReviewDAOImpl implements ReviewDAO