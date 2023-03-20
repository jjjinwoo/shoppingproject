package com.edu.board.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.edu.board.dao.BoardDAO;
import com.edu.board.dto.BoardDTO;
import com.edu.board.dto.CommentDTO;
import com.edu.board.service.BoardService;
import com.edu.common.util.PageMaker;
import com.edu.common.util.SearchCriteria;

//-----------------------------------------------------------------------------------------------------------
// 게시글 관리 컨트롤러
//-----------------------------------------------------------------------------------------------------------
@Controller		// Bean의 대상으로 인식시키기 위해서 servlet-context.xml에 등록한다.
@RequestMapping(value = "/board/*")
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Inject
	private BoardService boardService;
	
	@Inject
	private	BoardDAO boardDAO;
	
	//-----------------------------------------------------------------------------------------------------------
	// 게시글 목록 보여주기 (Paging 처리 + 검색)
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/boardList", method = RequestMethod.GET)
	public ModelAndView boardList(SearchCriteria sCri) throws Exception {
		
		System.out.println("BoardController boardList 시작 현재페이지: " + sCri.getPage());

		ModelAndView mav = new ModelAndView("/board/boardListQna");		
		
		//페이징 + 검색 처리와 함께 게시글 리스트를 가져와서 model에 넣어준다.
		List<BoardDTO> boardList = boardService.boardList(sCri);
		mav.addObject("boardList", boardList);
		
		//페이징 처리를 위한 pageMaker객체를 만들어서 sCri를 세팅해준다.
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(sCri);
		
		//전체 게시글 갯수를 구해서 pageMaker에 세팅해준다.
		pageMaker.setTotalCount(boardService.boardListTotalCount(sCri));
		
		//pageMaker를 model에 담는다.
		mav.addObject("pageMaker", pageMaker);
		
		return mav;
		
	} // End - 게시글 목록 보여주기 (Paging 1 처리)
	
	//-----------------------------------------------------------------------------------------------------------
	// 게시글 등록 화면 불러오기
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/boardRegisterForm", method = RequestMethod.GET)
	public ModelAndView boardRegisterForm(SearchCriteria sCri) throws Exception {
		
		logger.info("BoardController 게시글 등록 화면 불러오기() 시작");
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("boardList", boardService.boardList(sCri));
		
		mav.setViewName("/board/boardRegisterForm");
		
		return mav;
		
	} // End - 게시글 화면 불러오기()
	
	//-----------------------------------------------------------------------------------------------------------
	// 게시글 등록 처리 하기
	//-----------------------------------------------------------------------------------------------------------
	@ResponseBody
	@RequestMapping(value = "/boardRegister", method = RequestMethod.POST)
	// request 객체를 통해 데이터를 가져온다.
	// 메서드의 파라미터에 객체를 전달하면 자동으로 데이터가 set가 된 상태로 전달하게 된다.
	public String boardRegister(BoardDTO boardDTO) throws Exception {
		
		logger.info("BoardController 게시글 등록하기() 시작");
		System.out.println("BoardDTO 값 : " + boardDTO);
		
		if(boardService.boardRegister(boardDTO) == 1) {	// 게시글 등록 완료
			return "Y";
		} else {	// 게시글 등록 실패
			return "N";
		}
		
	} // End - 게시글 등록하기()
	
	//-----------------------------------------------------------------------------------------------------------
	// 게시글 상세 조회 (댓글 포함) + 조회수 처리
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/boardDetail", method = RequestMethod.GET)
	public ModelAndView boardDetail(int qna_bno, Integer flag) throws Exception {
		
		System.out.println("BoardControllerImpl의 boardDetail() 불러오기 qna_bno: " + qna_bno);
		
		ModelAndView mav = new ModelAndView();
		
		//flag값이 없는 경우만 조회수를 증가시킨다.(boardList에서 조회했을때)
		if(flag == null) {
			boardDAO.updateReadCount(qna_bno);
		}
		//게시글 번호에 해당하는 BoardDTO를 가져와서 model에 담는다.
		mav.addObject(boardService.boardDetail(qna_bno));
		
		//게시글 번호에 해당하는 CommentDTO가 있을 경우, 리스트 형태로 model에 담는다.
		if(boardService.commentListCount(qna_bno) != 0) {
			mav.addObject("commentList", boardService.commentList(qna_bno));
		}
		
		System.out.println(mav.getModel());
		return mav;
		
	} // End - 게시글 상세 조회()
	
	//-----------------------------------------------------------------------------------------------------------
	// 게시글 번호에 해당하는 게시글 삭제하기
	//-----------------------------------------------------------------------------------------------------------
	@ResponseBody
	@RequestMapping(value = "/boardDelete", method = RequestMethod.POST)
	public String boardDelete(int qna_bno, int[] reply_bno) throws Exception {
		
		System.out.println("BoardController 게시글 번호에 해당하는 게시글 삭제하기 번호: " + qna_bno + " 댓글 번호: " + reply_bno);
		
		//댓글이 있으면 반복문으로 댓글을 먼저 삭제해준다.
		int count = 0;
		if(reply_bno != null) {
			for(int i = 0; i < reply_bno.length; i++) {
				count += boardService.replyDelete(reply_bno[i]);
			}
			
			if(count == reply_bno.length) { //댓글을 모두 삭제했으면
				//게시글을 삭제한다.
				if(boardService.boardDelete(qna_bno) == 1) {
					return "Y";
				} else {
					return "N";
				}
			} else {
				return "N";
			}
		} else { //댓글이 없으면 바로 게시글을 삭제한다.
			
			if(boardService.boardDelete(qna_bno) == 1) {
				return "Y";
			} else {
				return "N";
			}
			
		}
		
		
	} // End - 게시글 번호에 해당하는 게시글 삭제하기()

	//-----------------------------------------------------------------------------------------------------------
	// 게시글 수정화면 불러오기 - 조회수가 증가되면 안된다.
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/boardUpdateForm", method = RequestMethod.GET)
	public ModelAndView boardUpdateForm(int qna_bno) throws Exception {
		
		logger.info("BoardController 게시글 수정화면 불러오기() 시작");
		
		ModelAndView mav = new ModelAndView();
		
		//service의 게시글 상세 정보 가져오는 method를 이용해서 boardDTO를 가져와서 model에 담는다.
		BoardDTO boardDTO = boardService.boardDetail(qna_bno);
		mav.addObject("boardDTO", boardDTO);
		
		//주소를 세팅해준다.
		mav.setViewName("/board/boardUpdateForm");
	
		return mav;
		
	} // End - 게시글 수정화면 불러오기

	//-----------------------------------------------------------------------------------------------------------
	// 게시글 번호에 해당하는 게시글 내용(제목, 글쓴이, 내용) 수정하기
	//-----------------------------------------------------------------------------------------------------------
	@ResponseBody
	@RequestMapping(value = "/boardUpdate", method = RequestMethod.POST)
	public String boardUpdate(BoardDTO boardDTO) throws Exception {
		
		logger.info("BoardController 게시글 번호에 해당하는 게시글 내용(제목, 아이디, 내용) 수정하기() 시작");
		
		if(boardService.boardUpdate(boardDTO) == 1) {
			return "Y";
		} else {
			return "N";
		}
		
	} // End - 게시글 번호에 해당하는 게시글 내용(제목, 아이디, 내용) 수정하기()

	//-----------------------------------------------------------------------------------------------------------
	// 게시글 번호에 해당하는 댓글 등록하기
	//-----------------------------------------------------------------------------------------------------------
	@ResponseBody
	@RequestMapping(value = "/commentRegister", method = RequestMethod.POST)
	public String commentRegister(CommentDTO commentDTO) throws Exception {
		System.out.println("BoardController commentRegister() 시작");
	
		if(boardService.commentRegister(commentDTO) == 1) {
			return "Y";
		} else {
			return "N";
		}
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 댓글 번호에 해당하는 댓글 삭제하기
	//-----------------------------------------------------------------------------------------------------------
	@ResponseBody
	@RequestMapping(value = "/commentDelete", method = RequestMethod.POST)
	public String replyDelete(int reply_bno, int qna_bno) throws Exception {
				
	
		System.out.println("BoardController 댓글 번호에 해당하는 댓글 삭제하기 번호: " + reply_bno);
				
		if(boardService.replyDelete(reply_bno) == 1) {
				return "Y";
			} else {
				return "N";
			}
			
	} // End - 게시글 번호에 해당하는 게시글 삭제하기()
	
	
} // End - public class BoardController
