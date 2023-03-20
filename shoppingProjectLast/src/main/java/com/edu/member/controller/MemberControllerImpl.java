package com.edu.member.controller;

import java.util.List; 

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.edu.board.dao.BoardDAO;
import com.edu.board.dao.ReviewDAO;
import com.edu.common.util.PageMaker;
import com.edu.common.util.SearchCriteria;
import com.edu.member.dto.MemberDTO;
import com.edu.member.service.MemberService;
import com.edu.product.dao.OrderDAO;
import com.edu.product.dto.OrderDTO;

@Controller("memberController")
@RequestMapping("/member")
public class MemberControllerImpl implements MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberControllerImpl.class);

	//-----------------------------------------------------------------------------------------------------------
	//	@Inject		: Java에서 지원하는 어노테이션. 특정 Framework에 종속정이기 않다.
	//	@Autowired	: Spring에서 지원하는 어노테이션.
	//-----------------------------------------------------------------------------------------------------------
	@Autowired
	private	MemberDTO	memberDTO;
	
	// MemberService memberService = new MemberService();
	@Autowired
	private	MemberService	memberService;
	
	
	@Inject
	ReviewDAO reviewDAO;
	
	@Inject
	BoardDAO boardDAO;	
	
	@Inject
	OrderDAO orderDAO;
	
	
	//-----------------------------------------------------------------------------------------------------------
	// 로그인 화면 띄우기 1
	//-----------------------------------------------------------------------------------------------------------
	@Override
	@RequestMapping(value="/loginForm.do", method=RequestMethod.GET)
	public ModelAndView loginForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("MemberControllerImpl 로그인 화면 불러오기() 시작");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/member/loginForm");
		return mav;
		
	}
	//-----------------------------------------------------------------------------------------------------------
	// 회원가입 화면 불러오기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	@RequestMapping(value="/memberForm.do", method=RequestMethod.GET)
	public ModelAndView memberForm(HttpServletRequest request, HttpServletResponse response) throws Exception {

		logger.info("MemberControllerImpl 회원가입 화면 불러오기() 시작");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/member/memberForm");	// 회원가입화면
		return mav;
	} // End - 회원가입 화면 불러오기()
	
	//-----------------------------------------------------------------------------------------------------------
	// 회원가입 처리하기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	   @RequestMapping(value = "/addMember.do", method = RequestMethod.POST)
	   public ModelAndView addMember(MemberDTO memberDTO, HttpServletRequest request, HttpServletResponse response)
	         throws Exception {

	      logger.info("MemberControllerImpl 회원가입 처리하기() 시작");
	      
	      System.out.println("memberDTO: " + memberDTO);
	      response.setContentType("text/html;charset=UTF-8");
	      
	      HttpSession session = request.getSession();
	      session.setAttribute("member1",    memberDTO);
	      session.setAttribute("isLogOn", true);
	      
	      //아이디로 t_orderProduct 테이블에서 데이터를 리스트형으로 받아와서 세션에 넣어준다(productDetail에서 사용하기 위해)
	      List<OrderDTO> orderDetailList = orderDAO.getOrderDetailById(memberDTO.getUserID());
	      session.setAttribute("member1OrderDetail",    orderDetailList);
	      
	      int result   = 0;   // 데이터 처리 완료 건수를 저장할 변수
	      
	      // 사용자가 입력한 회원정보를 서비스에게 넘겨주어서 처리하게 한다.
	      result = memberService.addMember(memberDTO);
	      
	      // 회원가입 처리후 회원전체목록 페이지로 이동한다.
	      ModelAndView mav = new ModelAndView("redirect:/main.do");

	      return mav;
	   } // End - 회원가입 처리하기   		
	
	//-----------------------------------------------------------------------------------------------------------
	// 로그인 처리
	// member => 로그인 창에서 보내온 정보, memberDTO => DB에서 가져온 정보
	//-----------------------------------------------------------------------------------------------------------
	@Override
	@RequestMapping(value="/login.do", method=RequestMethod.POST)
	public ModelAndView login(MemberDTO member, RedirectAttributes rAttr, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		System.out.println("MemberControllerImpl 로그인 처리 시작.....");
		System.out.println("로그인 정보 => " + member.getUserID() + " : " + member.getPwd());
		
		ModelAndView mav = new ModelAndView();
		
		// 아이디 값이 없이 넘어온 경우에는 돌려보낸다.
		if(member.getUserID().equals("") || member.getUserID() == null) {
			rAttr.addAttribute("result", "loginIdEmpty");
			mav.setViewName("redirect:/member/loginForm.do");
			return mav;
		}
		
		// 로그인한 정보를 가지고 데이터베이스에 존재하는지 처리를 하고, 그 결과를 가져온다.
		// member => 로그인 창에서 보내온 정보, memberDTO => DB에서 가져온 정보
		memberDTO = memberService.login(member);
		System.out.println("로그인 처리 결과 ==> " + memberDTO);
		
		// 로그인한 정보가 데이터베이스에 존재하는지에 따라 처리를 다르게 한다.
		if(memberDTO != null) {	// 로그인 정보에 해당하는 자료가 있으면
			
			if(member.getPwd().equals(memberDTO.getPwd())) {
				
				// 아이디와 비밀번호가 일치하면 세션을 발급한다.
				HttpSession session = request.getSession();
				session.setAttribute("member1", 	memberDTO);
				session.setAttribute("isLogOn", true);
				
				//아이디로 t_orderProduct 테이블에서 데이터를 리스트형으로 받아와서 세션에 넣어준다(productDetail에서 사용하기 위해)
			    List<OrderDTO> orderDetailList = orderDAO.getOrderDetailById(memberDTO.getUserID());
			    session.setAttribute("member1OrderDetail",    orderDetailList);
			    System.out.println("로그인한 회원의 주문 리스트: " + orderDetailList);
				
				mav.setViewName("redirect:/main.do");	// 메인화면으로 이동한다.
				
			} else { 	// 아이디는 있는데 비밀번호가 틀린 경우
						// 메시지를 가지고 로그인 화면으로 이동한다.
				rAttr.addAttribute("result", "PasswordFailed");
				mav.setViewName("redirect:/member/loginForm.do");
			}
			
		} else {	// 로그인한 아이디가 존재하지 않으면 
					// 로그인 실패 메시지를 가지고 로그인 화면으로 이동한다.
			rAttr.addAttribute("result", "loginFailed");
			mav.setViewName("redirect:/member/loginForm.do");
		}
		
		return mav;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 로그아웃 처리
	//-----------------------------------------------------------------------------------------------------------
	@Override
	@RequestMapping(value="/logout.do", method=RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 로그아웃 버튼을 누르면 세션정보를 없애고, 메인페이지로 이동하게 한다.
		HttpSession session = request.getSession();
		session.removeAttribute("member1");
		session.removeAttribute("isLogOn");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/main.do");	// 메인페이지로 이동
		
		return mav;
		
	} // End - 로그아웃 처리
	
	//-----------------------------------------------------------------------------------------------------------
	//  mypage 화면 불러오기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	@RequestMapping(value="/mypageForm.do", method=RequestMethod.GET)
	public ModelAndView mypageForm(HttpServletRequest request, HttpServletResponse response) throws Exception {

		logger.info("MemberControllerImpl  mypage 화면 불러오기() 시작");
		
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/member/mypageForm");	//  mypage 화면 
		return mav;
		
	} // End - 회원가입 화면 불러오기()	
	
	//-----------------------------------------------------------------------------------------------------------
	// 아이디 중복 검사 (Ajax) => return  값을 0과 1로 받기 떄문에 정수형(int)로 설정
	//-----------------------------------------------------------------------------------------------------------
	@Override
	@ResponseBody	// JSON 관련 jar 파일이 없으면 @ResponseBody가 응답을 하지 않는다. 
	@RequestMapping(value = "/idCheck", method = RequestMethod.POST)
	public int idCheck(MemberDTO memberDTO) throws Exception {
		
		System.out.println("MemberControllerImpl 아이디 중복 검사 (Ajax) 시작");
		System.out.println("회원 아이디: " + memberDTO.getUserID());
		
		int result = memberService.idCheck(memberDTO);
		
		logger.info("MemberControllerImpl 아이디 중복 검사 후 받은 값 : " + result);
		
		return result;
	}
	

	

	
	
	//-----------------------------------------------------------------------------------------------------------
	// 회원탈퇴 화면 불러오기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	@RequestMapping(value="/memberDelete.do", method=RequestMethod.GET)
	public ModelAndView memberDelete(HttpServletRequest request, HttpServletResponse response) throws Exception {

		logger.info("MemberControllerImpl  마이페이지 수정 화면 불러오기() 시작");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/member/memberDelete");	//  mypage 화면 
		return mav;
	} // End - 회원가입 화면 불러오기()

	//-----------------------------------------------------------------------------------------------------------
	// 회원탈퇴 화면 불러오기2
	//-----------------------------------------------------------------------------------------------------------
	@Override
	@RequestMapping(value="/memberDelete2.do", method=RequestMethod.GET)
	public ModelAndView memberDelete2(HttpServletRequest request, HttpServletResponse response) throws Exception {

		logger.info("MemberControllerImpl  마이페이지 수정 화면 불러오기() 시작");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/member/memberDelete2");	//  mypage 화면 
		return mav;
	} // End - 회원가입 화면 불러오기()
	
	//-----------------------------------------------------------------------------------------------------------
	// 아이디에 해당하는 회원 정보 삭제하기 - 개인계정
	//-----------------------------------------------------------------------------------------------------------
	@Override
	@RequestMapping(value = "/removeMember.do", method = RequestMethod.GET)
	public ModelAndView removeMember(String userID, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		logger.info("MemberControllerImpl 아이디에 해당하는 회원 정보 삭제하기기() 시작");
		logger.info("JSP에서 넘겨준 회원아이디 : " + userID);
		
		
		// 탈퇴하면 세션을 없앤다
		HttpSession session = request.getSession();
		session.removeAttribute("member1");
		session.removeAttribute("isLogOn");
		
		request.setCharacterEncoding("UTF-8");

		int result = memberService.removeMember(userID);
		
		ModelAndView mav = new ModelAndView("/member/unregisterForm");
		
		return mav;
	}//End - 아이디에 해당하는 회원 정보 삭제하기

	
	//-----------------------------------------------------------------------------------------------------------
	// 아이디에 해당하는 회원 정보 삭제하기 - 관리자 화면
	//-----------------------------------------------------------------------------------------------------------
	@Override
	@RequestMapping(value = "/removeMember2.do", method = RequestMethod.GET)
	public ModelAndView removeMember2(String userID, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		logger.info("MemberControllerImpl 아이디에 해당하는 회원 정보 삭제하기기() 시작");
		logger.info("JSP에서 넘겨준 회원아이디 : " + userID);
		
		
		request.setCharacterEncoding("UTF-8");

		int result = memberService.removeMember(userID);
		
		ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
		return mav;
	}//End - 아이디에 해당하는 회원 정보 삭제하기 -- 관리자 계정
	
	
	//-----------------------------------------------------------------------------------------------------------
	// 회원 전체 목록 조회하기 + 페이징 처리 - 관리자 화면
	//-----------------------------------------------------------------------------------------------------------
	@Override
	@RequestMapping(value = "/listMembers.do", method = RequestMethod.GET)
	public ModelAndView listMembers(SearchCriteria sCri, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		logger.info("MemberControllerImpl 회원 전체 목록 조회() 시작");
		
		ModelAndView mav = new ModelAndView("/member/listMembers");
		
		mav.addObject("keyword", sCri.getKeyword());
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(sCri);
		
		// 조건에 해당하는 데이터 건수를 알아낸다.
		pageMaker.setTotalCount(memberService.memberListTotalCount3(sCri));
		
		// 조건에 해당하는 데이터를 가져온다.
		List<MemberDTO> memberLists = memberService.listMembers(sCri);
		
		
		// mav.addObject("뷰에서 넘겨받을 이름", 뷰에 넘겨줄 값);
		mav.addObject("memberLists", memberLists);
		mav.addObject("pageMaker", pageMaker);	
		
		return mav;
	
	}// end - 회원 전체 목록 조회하기

	//-----------------------------------------------------------------------------------------------------------
	// 마이페이지 수정 화면 불러오기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	@RequestMapping(value="/memberUpdate.do", method=RequestMethod.GET)
	public ModelAndView memberUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {

		logger.info("MemberControllerImpl  마이페이지 수정 화면 불러오기() 시작");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/member/memberUpdate");	//  mypage 화면 
		return mav;
	} // End - 회원가입 화면 불러오기()		
	
	
	//----------------------------------------------------------------------------------------------------------
	// 마이페이지 아이디에 해당하는 마이페이지의 내용(비밀번호, 이름 등)을 수정 요청하기
	//----------------------------------------------------------------------------------------------------------
	@ResponseBody
	@RequestMapping(value = "/memberUpdate", method = RequestMethod.POST)
	public ModelAndView memberUpdate(@ModelAttribute("memberDTO") MemberDTO memberDTO,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 세션값 받기
		HttpSession session = request.getSession();
		session.setAttribute("member1", 	memberDTO);
		session.setAttribute("isLogOn", true);
		logger.info("MemberControllerImpl 아이디에 해당하는 회원 정보 수정하기() 시작");
		
		
		int result = memberService.modifyMember(memberDTO);
		ModelAndView mav = new ModelAndView("redirect:/member/mypageForm.do");
		return mav;
		
		
	} // End - 게시글 번호에 해당하는 게시글의 내용(제목, 글쓴이, 내용)을 수정 요청하기

	
	//-----------------------------------------------------------------------------------------------------------
	// 아이디에 해당하는 회원 정보 조회 + 폼출력 - 관리자 화면
	//-----------------------------------------------------------------------------------------------------------
	@Override
	@RequestMapping(value = "/updateMemberForm.do", method = RequestMethod.GET)
	public ModelAndView updateMemberForm(String userID, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		logger.info("MemberControllerImpl 아이디에 해당하는 회원 정보 조회 + 폼출력() 시작");
		
		// 회원 전체 목록 화면에서 수정을 요청한 userID에 해당하는 정보를 찾는 일을 서비스에게 부탁한다.
		memberDTO = memberService.selectMember(userID);
		logger.info("회원정보조회결과 : " + memberDTO);
		
		// 찾아온 회원 정보를 가지고 회원 정보 수정화면으로 넘어간다.
		ModelAndView mav = new ModelAndView("/member/memberUpdate2");
		mav.addObject("member", memberDTO);
		return mav;
		
	} // End - 아이디에 해당하는 회원 정보 조회 + 폼출력()

	//-----------------------------------------------------------------------------------------------------------
	// 아이디에 해당하는 회원 정보 수정하기 - 관리자 화면
	//-----------------------------------------------------------------------------------------------------------
	@Override
	@RequestMapping(value = "/modifyMember.do", method = RequestMethod.POST)
	public ModelAndView modifyMember(@ModelAttribute("memberDTO") MemberDTO memberDTO, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.info("MemberControllerImpl 아이디에 해당하는 회원 정보 수정하기() 시작");
		logger.info("JSP에서 넘겨준 회원정보 : " + memberDTO);
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		int result = memberService.modifyMember(memberDTO);
		ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
		return mav;
	} // End - 아이디에 해당하는 회원 정보 수정하기()
	
	//-----------------------------------------------------------------------------------------------------------
	// 마이페이지 메인 임시화면
	//-----------------------------------------------------------------------------------------------------------
	@Override
	@RequestMapping(value = "/mypageMain", method = RequestMethod.GET)
	public ModelAndView mypageMain(MemberDTO memberDTO, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ModelAndView mav = new ModelAndView("/member/mypageMain");
		return mav;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 나의 구매 현황
	//-----------------------------------------------------------------------------------------------------------
		@RequestMapping(value="/myOrderPage", method=RequestMethod.GET)
		public ModelAndView myOrderPage(String userID) throws Exception {
			System.out.println("ProductController의 productMenu 불러오기.... 회원아이디: " + userID);
			
			ModelAndView mav = new ModelAndView();
			
			//주문 번호를 해당 아이디에 속한 것만 가지고 온다.
			List<Long> orderNumList = orderDAO.getOrderNum(userID);
			
			//주문 번호를 가지고 orderDTO리스트를 구해 model에 담는다.
			mav.addObject("orderList", memberService.getOrderList(orderNumList));
			
			//qna 해당아이디에 속한 것만 가지고 온다
			mav.addObject("boardList", boardDAO.boardUserList(userID));
			System.out.println(boardDAO.boardUserList(userID));
			
			//review 해당아이디에 속한 것만 가지고 온다
			mav.addObject("reviewList", reviewDAO.reviewUserList(userID));
			System.out.println(reviewDAO.reviewUserList(userID));
			
			//주소를 세팅한다.
			mav.setViewName("/member/myOrderPage");
			return mav;
			
		}// end - 나의 구매 현황
	
	
	
}
