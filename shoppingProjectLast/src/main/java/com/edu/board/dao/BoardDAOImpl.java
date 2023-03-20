package com.edu.board.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.edu.board.dto.BoardDTO;
import com.edu.board.dto.CommentDTO;
import com.edu.common.util.SearchCriteria;

//게시글 data object
@Repository
public class BoardDAOImpl implements BoardDAO {

private static final Logger logger = LoggerFactory.getLogger(BoardDAOImpl.class);
	
	@Inject	// 의존 관계 주입(Defendency Inject, DI)
	private SqlSession sqlSession;
	
	@Inject
	private static String Namespace = "com.edu.main";
	
	
	
	//게시글 목록 불러오기
	@Override
	public List<BoardDTO> boardList(SearchCriteria sCri) throws Exception {
		
		return sqlSession.selectList(Namespace + ".boardList", sCri);
	}
	
	//게시글 총 갯수 가져오기
	@Override
	public int boardListTotalCount(SearchCriteria sCri) throws Exception {

		return sqlSession.selectOne(Namespace + ".totalCount", sCri);
	}	
	
	//전체 게시글 가져오기
	@Override
	public List<BoardDTO> boardTotalList() throws Exception {
		System.out.println("BoardDAOImpl의 boardTotalList() 구하기....");
		List<BoardDTO> boardList = sqlSession.selectList(Namespace + ".totalList");
		System.out.println(boardList);
		return boardList;
	}
	
	//qna 해당 아이디에 글 가져오기
	@Override
	public List<BoardDTO> boardUserList(String userID) throws Exception {
		System.out.println("BoardDAOImpl의 boardTotalList() 구하기....");
		List<BoardDTO> boardList2 = sqlSession.selectList(Namespace + ".boardUserList", userID);
		System.out.println(boardList2);
		return boardList2;
	}	
	
	// 게시글 등록 처리하기
	@Override
	public int boardRegister(BoardDTO boardDTO) throws Exception {

		logger.info("BoardDAOImpl 게시글 등록 처리하기() 시작");
		return sqlSession.insert(Namespace + ".boardRegister", boardDTO);

	} // End - 게시글 등록 처리하기()	
	
	// 게시글 번호에 해당하는 게시글의 조회수를 증가시키기
	@Override
	public void updateReadCount(int qna_bno) throws Exception {
		logger.info("BoardDAOImpl 게시글 번호에 해당하는 게시글의 조회수를 증가시키기() 시작");
		sqlSession.update(Namespace + ".updateReadCount", qna_bno);
	}
	
	// 게시글 번호에 해당하는 게시글 정보를 가져오기
	@Override
	public BoardDTO boardDetail(int qna_bno) throws Exception {
		logger.info("BoardDAOImpl 게시글 번호에 해당하는 게시글 정보를 가져오기() 시작");
		return sqlSession.selectOne(Namespace + ".detail", qna_bno);
	}
	
	// 게시글 번호에 해당하는 게시글 삭제하기
	@Override
	public int boardDelete(int qna_bno) throws Exception {
		logger.info("BoardDAOImpl 게시글 번호에 해당하는 게시글 삭제하기() 시작");
		
		return sqlSession.delete(Namespace + ".delete", qna_bno);
	}	

	// 게시글 번호에 해당하는 게시글 내용(제목, 글쓴이, 내용) 수정하기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int boardUpdate(BoardDTO boardDTO) throws Exception {
		logger.info("BoardDAOImpl 게시글 번호에 해당하는 게시글 내용(제목, 글쓴이, 내용) 수정하기() 시작");
		return sqlSession.update(Namespace + ".update", boardDTO);
	
	}

	// 게시글 번호에 해당하는 댓글 갯수 구하기
	//-----------------------------------------------------------------------------------------------------------	
	@Override
	public int commentListCount(int qna_bno) throws Exception {
		System.out.println("BoardDAOImpl의 commentListCount() 구하기....");
		
		return sqlSession.selectOne(Namespace + ".commentCount", qna_bno);
	}

	// 게시글 번호에 해당하는 댓글 구하기
	//-----------------------------------------------------------------------------------------------------------	

	@Override
	public List<CommentDTO> commentList(int qna_bno) throws Exception {
		System.out.println("BoardDAOImpl의 commentList() 구하기....");
		
		return sqlSession.selectList(Namespace + ".commentList", qna_bno);
	}

	// 게시글 번호에 해당하는 댓글 등록하기
	//-----------------------------------------------------------------------------------------------------------	
	@Override
	public int commentRegister(CommentDTO commentDTO) throws Exception {
		System.out.println("BoardDAOImpl의 commentRegister() 구하기....");
		
		return sqlSession.insert(Namespace + ".commentRegister", commentDTO);
	}

	// 댓글 번호에 해당하는 댓글 삭제하기
	@Override
	public int replyDelete(int reply_bno) throws Exception {
		logger.info("BoardDAOImpl 댓글 번호에 해당하는 댓글 삭제하기() 시작");
			
		return sqlSession.delete(Namespace + ".replyDelete", reply_bno);
	}
}
