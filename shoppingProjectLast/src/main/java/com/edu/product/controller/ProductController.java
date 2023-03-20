package com.edu.product.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.edu.board.dao.ReviewDAO;
import com.edu.board.dto.ReviewDTO;
import com.edu.board.service.ReviewService;
import com.edu.common.util.ArrayCriteria;
import com.edu.common.util.PageMaker;
import com.edu.common.util.ReviewCriteria;
import com.edu.product.dao.OrderDAO;
import com.edu.product.dto.ProductDTO;
import com.edu.product.service.ProductService;

@Controller
@RequestMapping(value="/product/*")
public class ProductController {

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@Inject
	ProductService productService;
	
	@Inject
	ReviewDAO reviewDAO;
	
	@Inject
	ReviewService reviewService;
	
	@Inject
	OrderDAO orderDAO;
	
	//카테고리에 따른(상품 타입) 상품 리스트 페이지 이동 + 정렬 방식에 따른 상품 리스트 데이터 함께 넘기기
	@RequestMapping(value="/productTypeList", method=RequestMethod.GET)
	public ModelAndView productTypeList(ArrayCriteria aCri) throws Exception {
		logger.info("ProductController의 productTypePage 불러오기....");
		System.out.println("상품타입: " + aCri.getProduct_type() + ", 현재페이지: " + aCri.getPage() + ", 정렬타입: " + aCri.getArray_type());
		
		ModelAndView mav = new ModelAndView(); //modelandview 준비
		
		//상품타입에 해당하는 상품 리스트를 가져와서 model에 담는다.
		List<ProductDTO> productList = productService.productTypeList(aCri);
		mav.addObject("productList", productList);
		System.out.println("productList mav: " + productList);
		
		//넘어온 현재 페이지와 해당 상품 전체 데이터 갯수를 pageMaker에 세팅하고 model담는다.
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(aCri);
		pageMaker.setTotalCount(productService.totalCount(aCri));
		System.out.println("totalCount: " + pageMaker.getTotalCount());
		mav.addObject("pageMaker", pageMaker);
		
		//들어온 정렬 타입을 다시 모델에 담아준다.(화면에서 처리를 위해)
		mav.addObject("array_type", aCri.getArray_type());
		
		//상품 타입, 검색 키워들를 담아서 보내준다(script 처리를 위해)
		mav.addObject("type", aCri.getProduct_type());
		if(aCri.getKeyword() != null) {
			mav.addObject("keyword", aCri.getKeyword());
		}
		
		//상품 타이틀 텍스트(상품타입)를 보내준다
		String first = aCri.getProduct_type().substring(0, 1).toUpperCase();
		String last = aCri.getProduct_type().substring(1);
		mav.addObject("title", first + last);
		
		System.out.println("searchType: " + aCri.getSearchType());
		
		//주소를 세팅해준다
		if(aCri.getSearchType() != null) { //검색창에서 넘어왔으면
			mav.setViewName("/product/productSearchResult");
		} else {
			mav.setViewName("/product/productList");
		}
	
		
		
		return mav;
	}
	
	//상품 상세페이지 이동 + 상품 상세 정보 데이터 함께 넘겨주기
	@RequestMapping(value="/productDetail", method=RequestMethod.GET)
	public ModelAndView productDetail(String product_code, int reviewPageNum) throws Exception {
		logger.info("ProductController의 productDetail 불러오기....");
		System.out.println("ProductController의 productDetail 불러오기.... 상품코드: " + product_code);
		
		ModelAndView mav = new ModelAndView();
		
		//상품 코드에 해당하는 상세정보를 model에 담는다
		ProductDTO productDTO = productService.productDetail(product_code);
		mav.addObject("productDTO", productDTO);
		
		//상품 코드에 해당하는 이미지리스트를 model에 담아준다.
		mav.addObject("imageList", productService.getImagesName(product_code));
		System.out.println(productService.getImagesName(product_code));
		
		//리뷰 리스트를 가져오기 위한 criteria를 세팅한다.
		ReviewCriteria rCri = new ReviewCriteria();
		rCri.setProduct_code(product_code);
		rCri.setPage(reviewPageNum);
		//해당 상품의 리뷰 리스트를 가져온다.
		List<ReviewDTO> reviewList =  reviewDAO.reviewList(rCri);
		//리뷰 리스트의 회원ID를 익명화 한다.
		reviewList = reviewService.userIDAnony(reviewList);
		//model에 담아준다.
		mav.addObject("reviewList", reviewList);
		System.out.println(reviewDAO.reviewList(rCri));
		
		//리뷰 리스트 paging을 위한 설정을 한다.
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(rCri); //cri 세팅
		pageMaker.setTotalCount(reviewService.reviewTotalCount(rCri)); //상품 코드에 해당하는 리뷰 전체 갯수 세팅
		mav.addObject("pageMaker", pageMaker);
		
		//해당 상품 리뷰 건수를 구해서 모델에 담아준다.
		mav.addObject("reviewCount" , reviewDAO.reviewTotalCount(rCri));
		
		//상품 타이틀 텍스트(상품타입)를 보내준다
		String first = productDTO.getProduct_type().substring(0, 1).toUpperCase();
		String last = productDTO.getProduct_type().substring(1);
		mav.addObject("title", first + last);
		
		//보내줄 주소를 세팅한다
		mav.setViewName("/product/productDetail");
		
		return mav;
	}
	
	//상품 검색 페이지 이동 (추천 상품 리스틀를 같이 보내준다.)
	@RequestMapping(value="/searchForm", method=RequestMethod.GET)
	public ModelAndView productSearchForm(ArrayCriteria aCri) throws Exception {
		System.out.println("검색타입: " + aCri.getSearchType() + ", 현재페이지: " + aCri.getPage()
		+ ", 정렬타입: " + aCri.getArray_type() + "상품 타입: " + aCri.getProduct_type());
		
		ModelAndView mav = new ModelAndView(); //modelandview 준비
		
		//상품타입에 해당하는 상품 리스트를 가져와서 model에 담는다.
		List<ProductDTO> productList = productService.productTypeList(aCri);
		
		//상품리스트에 상품 타입을 세팅해준다.
		for(int i = 0; i < productList.size(); i++) {
			productList.get(i).setProduct_type(aCri.getProduct_type());
		}
		
		mav.addObject("productList", productList);
		System.out.println("productList mav: " + productList);
		
		//들어온 정렬 타입과 상품 타입을 다시 모델에 담아준다.(화면에서 처리를 위해)
		mav.addObject("array_type", aCri.getArray_type());
		mav.addObject("type", aCri.getProduct_type());
		
		mav.setViewName("/product/productSearchForm");
	
		return mav;
	}
	
	
	//상품 등록 페이지 이동
	@RequestMapping(value="/productRegisterForm", method=RequestMethod.GET)
	public String productRegisterForm() throws Exception {
		System.out.println("ProductController의 productRegisterForm 불러오기.... ");
		
		return "/product/productRegisterForm";
	}
	
	//상품 등록 처리 (상품 코드 발급 후 리턴)
	@ResponseBody
	@RequestMapping(value="/productRegister", method=RequestMethod.POST)
	public String productRegister(ProductDTO productDTO) throws Exception {
		System.out.println("ProductController의 productRegister 불러오기.... ");		
		
		//서비스를 통해 상품을 등록하고 상품 코드를 리턴받는다.
		String product_code = productService.productRegister(productDTO);
	
		return product_code;
	}
	
	//상품 등록 페이지에서 이미지 업로드 실패시 상품 삭제 하는 메소드
	@RequestMapping(value="/productRegDelete", method=RequestMethod.GET)
	public String productRegDelete(String product_code) throws Exception {
		System.out.println("ProductController의 productRegDelete 불러오기.... ");	
		
		int result = productService.productDelete(product_code);
		
		return "/product/productRegisterForm";
	}
	
	//상품 수정페이지 이동
	@RequestMapping(value="/productUpdateForm", method=RequestMethod.GET)
	public ModelAndView productUpdateForm(String product_code) throws Exception {
		System.out.println("ProductController의 productUpdateForm 불러오기.... ");	
		
		ModelAndView mav = new ModelAndView();
		
		//상품 코드에 해당하는 상품DTO를 함께 보내준다.
		mav.addObject("productDTO", productService.productDetail(product_code));
		
		//주소를 세팅한다.
		mav.setViewName("/product/productUpdateForm");
		
		return mav;
	}
	
	//상품 수정 처리하기
	@ResponseBody
	@RequestMapping(value="/productUpdate", method=RequestMethod.POST)
	public String productUpdate(ProductDTO productDTO) throws Exception {
		System.out.println("ProductController의 productRegister 불러오기.... ");		
		
		//서비스를 통해 상품을 수정하고 상품 코드를 리턴받는다.
		String product_code = productService.productUpdate(productDTO);
		System.out.println("상품수정 후 코드: " + product_code);
	
		return product_code;		
	}
	
	//상품 코드에 해당하는 데이터 삭제하기
	@ResponseBody
	@RequestMapping(value="/productDelete", method=RequestMethod.POST)
	public int productDelete(String product_code) throws Exception {
		System.out.println("ProductController의 productRegister 불러오기.... ");
		
		//해당 상품의 장바구니 데이터를 먼저 삭제한다.
		int result = orderDAO.cartDeleteByCode(product_code);
		
		return productService.productDelete(product_code);
		
	}
	
}
