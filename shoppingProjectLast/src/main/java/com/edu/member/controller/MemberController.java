package com.edu.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.edu.common.util.SearchCriteria;
import com.edu.member.dto.MemberDTO;

public interface MemberController {
	
	//-----------------------------------------------------------------------------------------------------------
	// 로그인 폼
	//-----------------------------------------------------------------------------------------------------------
	public ModelAndView loginForm(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	//-----------------------------------------------------------------------------------------------------------
	// 회원가입 화면 불러오기
	//-----------------------------------------------------------------------------------------------------------
	public ModelAndView memberForm(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	//-----------------------------------------------------------------------------------------------------------
	// 회원가입 처리하기
	//-----------------------------------------------------------------------------------------------------------
	public ModelAndView addMember(@ModelAttribute("member") MemberDTO memberDTO,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception;
	
	//-----------------------------------------------------------------------------------------------------------
	// 로그인 처리
	//-----------------------------------------------------------------------------------------------------------
	public ModelAndView login(@ModelAttribute("member") MemberDTO member,
								RedirectAttributes rAttr, 
								HttpServletRequest request, HttpServletResponse response)
			throws Exception;
			
	//-----------------------------------------------------------------------------------------------------------
	// 로그아웃 처리
	//-----------------------------------------------------------------------------------------------------------
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception;

	//-----------------------------------------------------------------------------------------------------------
	// mypage 화면 불러오기
	//-----------------------------------------------------------------------------------------------------------
	public ModelAndView mypageForm(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	//-----------------------------------------------------------------------------------------------------------
	// 아이디 중복 검사  => return  값을 0과 1로 받기 떄문에 정수형(int)로 설정
	//-----------------------------------------------------------------------------------------------------------	
	public int idCheck(MemberDTO memberDTO) throws Exception;

	
	//-----------------------------------------------------------------------------------------------------------
	// 회원탈퇴 화면 불러오기
	//-----------------------------------------------------------------------------------------------------------
	public ModelAndView memberDelete(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	//-----------------------------------------------------------------------------------------------------------
	// 회원탈퇴 화면 불러오기2
	//-----------------------------------------------------------------------------------------------------------
	public ModelAndView memberDelete2(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	//-----------------------------------------------------------------------------------------------------------
	// 아이디에 해당하는 회원 정보 삭제하기 -- 개인계정
	//-----------------------------------------------------------------------------------------------------------
	public ModelAndView removeMember(@RequestParam("userID") String userID, 	
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	//-----------------------------------------------------------------------------------------------------------
	// 아이디에 해당하는 회원 정보 삭제하기 -- 관리자계정
	//-----------------------------------------------------------------------------------------------------------
	public ModelAndView removeMember2(@RequestParam("userID") String userID, 	
			HttpServletRequest request, HttpServletResponse response) throws Exception;

	//-----------------------------------------------------------------------------------------------------------
	// 회원 전체 목록 조회하기 - 관리자 화면
	//-----------------------------------------------------------------------------------------------------------
	public ModelAndView listMembers(SearchCriteria sCri, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	//-----------------------------------------------------------------------------------------------------------
	// 마이페이지 수정 화면 불러오기
	//-----------------------------------------------------------------------------------------------------------
	public ModelAndView memberUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	//----------------------------------------------------------------------------------------------------------
	// 마이페이지 아이디에 해당하는 마이페이지의 내용(비밀번호, 이름 등)을 수정 요청하기
	//----------------------------------------------------------------------------------------------------------
	public ModelAndView memberUpdate(@ModelAttribute("memberDTO") MemberDTO memberDTO,
			HttpServletRequest request, HttpServletResponse response) throws Exception;

	//-----------------------------------------------------------------------------------------------------------
	// 아이디에 해당하는 회원 정보 조회 + 폼출력 - 관리자화면
	//-----------------------------------------------------------------------------------------------------------
	public ModelAndView updateMemberForm(@RequestParam("userID") String userID, HttpServletRequest request, HttpServletResponse response) throws Exception;

	//-----------------------------------------------------------------------------------------------------------
	// 아이디에 해당하는 회원 정보 수정하기 - 관리자화면
	//-----------------------------------------------------------------------------------------------------------
	public ModelAndView modifyMember(@ModelAttribute("memberDTO") MemberDTO memberDTO,
			HttpServletRequest request, HttpServletResponse response) throws Exception;

	//-----------------------------------------------------------------------------------------------------------
	// 마이페이지 메인 임시화면
	//-----------------------------------------------------------------------------------------------------------
	public ModelAndView mypageMain(@ModelAttribute("memberDTO") MemberDTO memberDTO,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	//-----------------------------------------------------------------------------------------------------------
	// 나의 구매 페이지
	//-----------------------------------------------------------------------------------------------------------
	public ModelAndView myOrderPage(String userID) throws Exception;
	
	
}
