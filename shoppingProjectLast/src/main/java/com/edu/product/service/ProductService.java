package com.edu.product.service;

import java.util.List;

import com.edu.common.util.ArrayCriteria;
import com.edu.common.util.SearchCriteria;
import com.edu.product.dto.ImagesDTO;
import com.edu.product.dto.ProductDTO;

public interface ProductService {
	
	public List<ProductDTO> productList() throws Exception; //상품 전체 리스트

	public List<ProductDTO> productTypeList(ArrayCriteria aCri) throws Exception; //타입에 해당하는 상품 리스트 + 정렬 + 검색
	
	public int totalCount(ArrayCriteria aCri) throws Exception; //타입에 해당하는 상품 전체 갯수
	
	public ProductDTO productDetail(String pdCode) throws Exception; //상품 상세 정보
	
	public List<String> getImagesName(String pdCode) throws Exception; //상품 이미지 이름 리스트
	
	public List<ProductDTO> productSearchList(SearchCriteria sCri) throws Exception; //검색 조건에 해당하는 상품 리스트
	
	public int searchCount(SearchCriteria sCri) throws Exception; //검색 조건에 해당하는 상품 전체 갯수
	
	public String productRegister(ProductDTO productDTO) throws Exception; //상품을 등록하고 상품 코드를 반환한다.
	
	public int productDelete(String product_code) throws Exception; //상품 코드에 해당하는 상품 데이터 삭제학기
	
	public String productUpdate(ProductDTO productDTO) throws Exception; //상품 코드에 해당하는 상품 정보 수정하기
}
