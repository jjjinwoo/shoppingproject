package com.edu.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/other/*")
public class OtherController {

	//concept 화면 불러오기
	@RequestMapping(value="/conceptForm.do", method=RequestMethod.GET)
	public ModelAndView conceptForm() throws Exception {

		System.out.print("OtherController 화면 불러오기() 시작");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/other/conceptForm");	// concept 화면
		return mav;
		
	} // End - concept 화면 불러오기
	
	//information 화면 불러오기
	@RequestMapping(value="/informationForm.do", method=RequestMethod.GET)
	public ModelAndView informationForm() throws Exception {

		System.out.print("OtherController 개인정보취급방침 화면 불러오기() 시작");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/other/informationForm");	// concept 화면
		return mav;
		
	} // End - information 화면 불러오기
	
	//stylelife 화면 불러오기
	@RequestMapping(value="/styleLifeForm.do", method=RequestMethod.GET)	
	public ModelAndView styleLifeForm() throws Exception {
		
		System.out.print("OtherController StyleLifeForm 화면 불러오기() 시작");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/other/styleLifeForm");	// concept 화면
		
		return mav;
	}
	
	//stylelife 세부 화면 불러오기
	@RequestMapping(value="/styleDetail", method=RequestMethod.GET)
	public ModelAndView styleDetail(int num) throws Exception {
		
		System.out.print("OtherController styleDetail 화면 불러오기() 시작");
		
		ModelAndView mav = new ModelAndView();
		
		if(num == 1) mav.setViewName("/other/style01");	
		if(num == 2) mav.setViewName("/other/style02");	
		if(num == 3) mav.setViewName("/other/style03");	
		if(num == 4) mav.setViewName("/other/style04");	
		
		return mav;
	}
	
	//use 화면 불러오기
	@RequestMapping(value="/useForm.do", method=RequestMethod.GET)
	public ModelAndView useForm(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.print("UseControllerImpl 이용약관 화면 불러오기() 시작");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/other/useForm");	// concept 화면
		return mav;
		
	}
	
	//boardListFqa 화면 불러오기
	@RequestMapping(value = "/boardListFqa", method = RequestMethod.GET)
	public String boardListFqa() throws Exception {
		
		System.out.println("OtherController boardListFqa 불러오기() 시작");
		return "/board/boardListFqa";
		
	}
}
