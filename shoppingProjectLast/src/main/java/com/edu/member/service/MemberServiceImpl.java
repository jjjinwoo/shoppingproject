package com.edu.member.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.edu.common.util.SearchCriteria;
import com.edu.member.dao.MemberDAO;
import com.edu.member.dto.MemberDTO;
import com.edu.product.dao.OrderDAO;
import com.edu.product.dto.OrderDTO;

//-----------------------------------------------------------------------------------------------------------
//회원 정보 서비스
//-----------------------------------------------------------------------------------------------------------
@Service("memberService")
public class MemberServiceImpl implements MemberService {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);

	@Autowired
	private MemberDAO memberDAO;
	
	@Inject
	OrderDAO orderDAO;

	//-----------------------------------------------------------------------------------------------------------
	// 로그인 처리
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public MemberDTO login(MemberDTO memberDTO) throws DataAccessException {
		
		logger.info("MemberServiceImpl login() 시작");

		return memberDAO.loginByID(memberDTO);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 회원가입 처리하기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int addMember(MemberDTO memberDTO) throws DataAccessException {

		logger.info("MemberServiceImpl 회원가입 처리하기() 시작 : " + memberDTO);
		return memberDAO.addMember(memberDTO);

	} // End - 회원가입 처리하기()	

	//-----------------------------------------------------------------------------------------------------------
	// 아이디 중복 검사 => return  값을 0과 1로 받기 떄문에 정수형(int)로 설정
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int idCheck(MemberDTO memberDTO) throws Exception {
		
		logger.info("MemberServiceImpl 아이디에 해당하는  아이디 중복 검사 시작");
		
		int result = memberDAO.idCheck(memberDTO);
		
		return result;
	}	
	
	//----------------------------------------------------------------------------------------------------------
	// 마이페이지 아이디에 해당하는 마이페이지의 내용(비밀번호, 이름 등)을 수정 요청하기
	//----------------------------------------------------------------------------------------------------------
	@Override
	public int memberUpdate(MemberDTO memberDTO){
		
		logger.info("MemberServiceImpl 게시글 번호에 해당하는 게시글의 내용(제목, 글쓴이, 내용)을 수정 요청하기() 시작 ");
		
		
		return memberDAO.memberUpdate(memberDTO);
	
	} // End - 게시글 번호에 해당하는 게시글의 내용(제목, 글쓴이, 내용)을 수정 요청하기	

	//-----------------------------------------------------------------------------------------------------------
	// 아이디에 해당하는 회원 정보 삭제하기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int removeMember(String userID) throws DataAccessException {

		logger.info("MemberServiceImpl 아이디에 해당하는 회원 정보 삭제하기() 시작");
		return memberDAO.deleteMember(userID);

	} // End - 아이디에 해당하는 회원 정보 삭제하기()
	
	//-----------------------------------------------------------------------------------------------------------
	// 전체 게시글 수 가져오기 
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int memberListTotalCount3(SearchCriteria sCri) throws Exception {
		logger.info("MemberServiceImpl 전체 게시글 수 가져오기==> " + sCri);
		return memberDAO.memberListTotalCount3(sCri);
	}//end - 전체 게시글 수 가져오기 

	//-----------------------------------------------------------------------------------------------------------
	// 검색 조건에 맞는 게시글 가져오기 
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public List<MemberDTO> listMembers(SearchCriteria sCri) throws Exception {
		logger.info("MemberServiceImpl 검색 조건에 맞는 게시글 가져오기  ==> " + sCri);
		return memberDAO.listMembers(sCri);
	}// end -  검색 조건에 맞는 게시글 가져오기 

	//-----------------------------------------------------------------------------------------------------------
	// 아이디에 해당하는 회원 정보 조회 + 폼출력 - 관리자화면
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public MemberDTO selectMember(String userID) throws DataAccessException {

		logger.info("MemberServiceImpl 아이디에 해당하는 회원 정보 조회 + 폼출력() 시작");

		MemberDTO memberDTO = memberDAO.selectMember(userID);
		return memberDTO;

	} // End - 아이디에 해당하는 회원 정보 조회 + 폼출력()
	
	//-----------------------------------------------------------------------------------------------------------
	// 아이디에 해당하는 회원 정보 수정하기 - 관리자 화면
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int modifyMember(MemberDTO memberDTO) throws DataAccessException {

		logger.info("MemberServiceImpl 아이디에 해당하는 회원 정보 수정하기() 시작");

		return memberDAO.updateMember(memberDTO);

	} // End - 아이디에 해당하는 회원 정보 수정하기()

	
	//주문 번호 리스트를 가지고  주문 완료 데이터 리스트 가져오기
	@Override
	public List<OrderDTO> getOrderList(List<Long> orderNumList) throws Exception {

		logger.info("MemberServiceImpl 주문번호에 해당하는 orderDTO List 만들기() 시작");
		
		List<OrderDTO> orderList = new ArrayList<OrderDTO>();
		//반복문으로 orderDTO를 구해서 리스트에 담는다.
		for(int i = 0; i < orderNumList.size(); i++) {
			OrderDTO orderDTO = orderDAO.getOrderDTO(orderNumList.get(i));
			orderList.add(orderDTO);
		}
		
		return orderList;
	}

	
	
	
}
