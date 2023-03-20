package com.edu.product.dto;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

@Component("productDTO")
public class ProductDTO {

	private String product_code; //상품 코드
	private String product_type; //상품 타입
	private int product_price; //상품 가격
	private Timestamp product_date; //상품 등록 날짜
	private String product_name; //상품 이름
	private int star_sum; //상품 별점 합계
	private int review_count; //리뷰 등록 갯수
	private float star_avg; //상품 별점 평균
	private String imgae; //상품 대표 이미지
	
	
	public String getProduct_code() {
		return product_code;
	}
	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}
	public String getProduct_type() {
		return product_type;
	}
	public void setProduct_type(String product_type) {
		this.product_type = product_type;
	}
	public int getProduct_price() {
		return product_price;
	}
	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}
	public Timestamp getProduct_date() {
		return product_date;
	}
	public void setProduct_date(Timestamp product_date) {
		this.product_date = product_date;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public int getStar_sum() {
		return star_sum;
	}
	public void setStar_sum(int star_sum) {
		this.star_sum = star_sum;
	}
	public int getReview_count() {
		return review_count;
	}
	public void setReview_count(int review_count) {
		this.review_count = review_count;
	}
	
	
	public float getStar_avg() {
		return star_avg;
	}
	public void setStar_avg(float star_avg) {
		this.star_avg = star_avg;
	}
	public String getImgae() {
		return imgae;
	}
	public void setImgae(String imgae) {
		this.imgae = imgae;
	}
	@Override
	public String toString() {
		return "ProductDTO [product_code=" + product_code + ", product_type=" + product_type + ", product_price="
				+ product_price + ", product_date=" + product_date + ", product_name=" + product_name + ", star_sum="
				+ star_sum + ", review_count=" + review_count + ", star_avg=" + star_avg + ", imgae=" + imgae + "]";
	}
	
	
	
	
	
	
	
	
}
