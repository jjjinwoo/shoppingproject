package com.edu.product.dto;

public class ImagesDTO {
	
	String product_code;
	String images01;
	String images02;
	String images03;
	String images04;
	String images05;
	
	
	
	public String getProduct_code() {
		return product_code;
	}
	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}
	public String getImages01() {
		return images01;
	}
	public void setImages01(String images01) {
		this.images01 = images01;
	}
	public String getImages02() {
		return images02;
	}
	public void setImages02(String images02) {
		this.images02 = images02;
	}
	public String getImages03() {
		return images03;
	}
	public void setImages03(String images03) {
		this.images03 = images03;
	}
	public String getImages04() {
		return images04;
	}
	public void setImages04(String images04) {
		this.images04 = images04;
	}
	public String getImages05() {
		return images05;
	}
	public void setImages05(String images05) {
		this.images05 = images05;
	}
	@Override
	public String toString() {
		return "ImagesDTO [product_code=" + product_code + ", images01=" + images01 + ", images02=" + images02
				+ ", images03=" + images03 + ", images04=" + images04 + ", images05=" + images05 + "]";
	}
	
	
}
