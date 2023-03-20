package com.edu.common.util;

public class ReviewCriteria extends Criteria {

	private String product_code; //상품 코드
	private String searchType; //검색 조건
	private String keyword; //검색 키워드	

	public String getProduct_code() {
		return product_code;
	}

	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@Override
	public String toString() {
		return "ReviewCriteria [product_code=" + product_code + ", searchType=" + searchType + ", keyword=" + keyword
				+ "]";
	}

	
	
}
