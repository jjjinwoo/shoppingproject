package com.edu.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.edu.common.util.SearchCriteria;
import com.edu.member.dto.MemberDTO;

@Repository("memberDAO")
public class MemberDAOImpl implements MemberDAO {
	
	@Autowired
	private SqlSession sqlSession;

	private static final Logger logger = LoggerFactory.getLogger(MemberDAOImpl.class);
	
	private	static final String Namespace = "com.edu.member";
	
	//-----------------------------------------------------------------------------------------------------------
	// 로그인 처리
	//-----------------------------------------------------------------------------------------------------------	
	@Override
	public MemberDTO loginByID(MemberDTO memberDTO) throws DataAccessException {
		
		MemberDTO memDTO = sqlSession.selectOne(Namespace + ".loginByID", memberDTO);
		return memDTO;
	}

	//-----------------------------------------------------------------------------------------------------------
	// 회원가입 처리하기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int addMember(MemberDTO memberDTO) throws DataAccessException {

		logger.info("MemberDAOImpl 회원가입 처리하기() 시작 : " + memberDTO);
		
		System.out.println("MemberDAOImpl 회원가입 처리하기() 시작 : " + memberDTO);
		
		int result = sqlSession.insert(Namespace + ".addMember", memberDTO);
		System.out.println("MemberDAOImpl 회원가입 처리하기() 결과 : " + result);
		return result;
		
	} // End - 회원가입 처리하기
	
	//-----------------------------------------------------------------------------------------------------------
	// 아이디 중복 검사 => return  값을 0과 1로 받기 떄문에 정수형(int)로 설정
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int idCheck(MemberDTO memberDTO) throws DataAccessException {
		
		logger.info("MemberDAOImpl 아이디 중복 검사시작하기");
		
		return sqlSession.selectOne(Namespace + ".idCheck", memberDTO);
	}
	
	//----------------------------------------------------------------------------------------------------------
	// 마이페이지 아이디에 해당하는 마이페이지의 내용(비밀번호, 이름 등)을 수정 요청하기
	//----------------------------------------------------------------------------------------------------------	
	@Override
	public int memberUpdate(MemberDTO memberDTO) {
		
		logger.info("MemberDAOImpl 게시글 번호에 해당하는 게시글 내용(제목, 글쓴이, 내용) 수정하기() 시작");
		
		return sqlSession.update(Namespace + ".update", memberDTO);
	
	} // End - 게시글 번호에 해당하는 게시글의 내용(제목, 글쓴이, 내용)을 수정 요청하기	

	//-----------------------------------------------------------------------------------------------------------
	// 아이디에 해당하는 회원 정보 삭제하기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int deleteMember(String userID) throws DataAccessException {
		int result = sqlSession.delete(Namespace + ".deleteMember", userID);
		return result;
	} // End - // 아이디에 해당하는 회원 정보 삭제하기
	
	
	//-----------------------------------------------------------------------------------------------------------
	// 전체 게시글 수 가져오기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int memberListTotalCount3(SearchCriteria sCri) throws Exception {
		logger.info("MemberAOImpl 전체 게시글 수 가져오기 ==> " + sCri);
		return sqlSession.selectOne(Namespace + ".memberListTotalCountSearchType", sCri);
	}// end - 전체 게시글 수 가져오기
	
	//-----------------------------------------------------------------------------------------------------------
	//  검색 조건에 맞는 게시글 가져오기 
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public List<MemberDTO> listMembers(SearchCriteria sCri) throws Exception {
		logger.info("MemberDAOImpl  검색 조건에 맞는 게시글 가져오기 ) 시작 ==> " + sCri);
		return sqlSession.selectList(Namespace + ".memberListPagingSearchType", sCri);
	}// end -  검색 조건에 맞는 게시글 가져오기 
	
	//-----------------------------------------------------------------------------------------------------------
	// 아이디에 해당하는 회원 정보 조회 + 폼출력 - 관리자 화면
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public MemberDTO selectMember(String userID) throws DataAccessException {

		System.out.println("MemberDAOImpl 아이디에 해당하는 회원 정보 조회 + 폼출력()");
		
		MemberDTO memberDTO = sqlSession.selectOne(Namespace + ".selectMember", userID);
		return memberDTO;
		
	} // End - 아이디에 해당하는 회원 정보 조회 + 폼출력

	//-----------------------------------------------------------------------------------------------------------
	// 아이디에 해당하는 회원 정보 수정하기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int updateMember(MemberDTO memberDTO) throws DataAccessException {

		System.out.println("MemberDAOImpl 아이디에 해당하는 회원 정보 수정하기()");
		
		int result = sqlSession.update(Namespace + ".updateMember", memberDTO);
		return result;
		
	} // End - 아이디에 해당하는 회원 정보 수정하기()

	
}
