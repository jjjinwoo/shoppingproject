package com.edu.board.service;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.edu.board.dao.BoardDAO;
import com.edu.board.dto.BoardDTO;
import com.edu.board.dto.CommentDTO;
import com.edu.common.util.SearchCriteria;

// 게시글 서비스
@Service	// Bean으로 인식시키기 위해서 사용한다.
public class BoardServiceImpl implements BoardService {
private static final Logger logger = LoggerFactory.getLogger(BoardServiceImpl.class);
	
	@Inject
	private BoardDAO boardDAO;
	
	//게시글 리스트 가져오기
	@Override
	public List<BoardDTO> boardList(SearchCriteria sCri) throws Exception {
		
		return boardDAO.boardList(sCri);
	}
	//-----------------------------------------------------------------------------------------------------------	
	//게시글 총 갯수 가져오기
	//-----------------------------------------------------------------------------------------------------------	
	@Override
	public int boardListTotalCount(SearchCriteria sCri) throws Exception {
		
		return boardDAO.boardListTotalCount(sCri);
	}
	//-----------------------------------------------------------------------------------------------------------
	// 게시글 등록 처리하기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int boardRegister(BoardDTO boardDTO) throws Exception {

		logger.info("BoardServiceImpl 게시글 등록 처리하기() 시작 " + boardDTO);
		return boardDAO.boardRegister(boardDTO);
		
	} // End - 게시글 등록 처리하기()


	//-----------------------------------------------------------------------------------------------------------
	// 게시글 상세 조회
	//-----------------------------------------------------------------------------------------------------------
	@Override
	 public BoardDTO boardDetail(int qna_bno) throws Exception {

		logger.info("BoardServiceImpl 게시글 상세 조회() 시작");
		
		return boardDAO.boardDetail(qna_bno);
		
	} // End - 게시글 상세 조회

	//-----------------------------------------------------------------------------------------------------------
	// 게시글 번호에 해당하는 게시글 삭제하기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int boardDelete(int qna_bno) throws Exception {

		logger.info("BoardServiceImpl 게시글 번호에 해당하는 게시글 삭제하기() 시작");
		return boardDAO.boardDelete(qna_bno);
	}

	//-----------------------------------------------------------------------------------------------------------
	// 게시글 번호에 해당하는 게시글 내용(제목, 글쓴이, 내용) 수정하기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int boardUpdate(BoardDTO boardDTO) throws Exception {

		logger.info("BoardServiceImpl 게시글 번호에 해당하는 게시글 내용(제목, 글쓴이, 내용) 수정하기() 시작");
		return boardDAO.boardUpdate(boardDTO);
		
	} // End - 게시글 번호에 해당하는 게시글 내용(제목, 글쓴이, 내용) 수정하기

	//-----------------------------------------------------------------------------------------------------------
	// 게시글 번호에 해당하는 댓글 갯수 구하기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int commentListCount(int qna_bno) throws Exception {
		System.out.println("BoardServiceImpl commentListCount() 구하기....");
		
		return boardDAO.commentListCount(qna_bno);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 게시글 번호에 해당하는 댓글 리스트 불러오기
	//-----------------------------------------------------------------------------------------------------------	
	@Override
	public List<CommentDTO> commentList(int qna_bno) throws Exception {
		System.out.println("BoardServiceImpl commentList() 구하기....");
		
		return boardDAO.commentList(qna_bno);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 게시글 번호에 해당하는 댓글 등록하기
	//-----------------------------------------------------------------------------------------------------------	
	@Override
	public int commentRegister(CommentDTO commentDTO) throws Exception {
		System.out.println("BoardServiceImpl commentRegister() 구하기....");
		
		return boardDAO.commentRegister(commentDTO);
	}

	//-----------------------------------------------------------------------------------------------------------
	// 게시글 번호에 해당하는 댓글 삭제하기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int replyDelete(int reply_bno) throws Exception {

		logger.info("BoardServiceImpl 댓글 번호에 해당하는 댓글 삭제하기() 시작");
		return boardDAO.replyDelete(reply_bno);
	}

}
