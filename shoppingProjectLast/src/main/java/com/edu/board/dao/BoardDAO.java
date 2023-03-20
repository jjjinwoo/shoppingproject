package com.edu.board.dao;

import java.util.List;

import com.edu.board.dto.BoardDTO;
import com.edu.board.dto.CommentDTO;
import com.edu.common.util.SearchCriteria;

// 게시글 data object
public interface BoardDAO {

	//게시글 목록 불러오기
	public List<BoardDTO> boardList(SearchCriteria sCri) throws Exception;
	
	//게시글 총 갯수 가져오기
	public int boardListTotalCount(SearchCriteria sCri) throws Exception;
	
	//게시글 전체 리스트 가져오기
	public List<BoardDTO> boardTotalList() throws Exception;
	
	//qna 해당아이디에 속한 것만 가지고 온다
	public List<BoardDTO> boardUserList(String userID) throws Exception;
	
	// 게시글 등록 처리한다
	public int boardRegister(BoardDTO boardDTO) throws Exception;	
	
	// 게시글 번호에 해당하는 게시글의 조회수를 증가시킨다
	public void updateReadCount(int qna_bno) throws Exception;	
	
	// 게시글 번호에 해당하는 게시글 정보 가져오기
	public BoardDTO boardDetail(int qna_bno) throws Exception;
	
	// 게시글 번호에 해당하는 게시글 삭제하기
	public int boardDelete(int qna_bno) throws Exception;
	
	//게시글 번호에 해당하는 게시글 내용(제목, 글쓴이, 내용) 수정하기
	public int boardUpdate(BoardDTO boardDTO) throws Exception;	
	
	//게시글 번호에 해당하는 댓글 수 구하기
	public int commentListCount(int qna_bno) throws Exception;
	
	//게시글 번호에 해당하는 댓글 리스트 불러오기
	public List<CommentDTO> commentList(int qna_bno) throws Exception;
	
	//게시글 번호에 해당하는 댓글 등록하기
	public int commentRegister(CommentDTO commentDTO) throws Exception;
	
	// 댓글 번호에 해당하는 댓글 삭제하기
	public int replyDelete(int reply_bno) throws Exception;	
}// End- public interface BoardDAO
