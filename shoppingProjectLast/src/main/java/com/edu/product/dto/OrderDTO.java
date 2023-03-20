package com.edu.product.dto;

import java.sql.Timestamp;

public class OrderDTO {

	private long order_num; //주문번호
	private String userID;//회원 아이디
	private String address1; //주문 주소1
	private String address2; //주문 주소2
	private String postnum; //우편번호
	private String order_name; //받는 사람 이름
	private String order_phone; //받는 사람 번호
	private Timestamp order_date; //주문 날짜
	private String order_memo; //배송 메모
	private int orderProduct_bno; 
	private String product_code; //상품 코드
	private int product_amount; //상품 수량
	private String product_size; //상품 사이즈
	private int product_price; //상품 가격
	private String product_name; //상품 이름
	private String paymethod; //결제수단
	private String review_yn = "N"; //리뷰 작성 유무 (기본적으로는 N)
	private long totalbill; //총 주문 가격
	
	
	
	public long getTotalbill() {
		return totalbill;
	}
	public void setTotalbill(long totalbill) {
		this.totalbill = totalbill;
	}
	public int getOrderProduct_bno() {
		return orderProduct_bno;
	}
	public void setOrderProduct_bno(int orderProduct_bno) {
		this.orderProduct_bno = orderProduct_bno;
	}
	public long getOrder_num() {
		return order_num;
	}
	public void setOrder_num(long order_num) {
		this.order_num = order_num;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getProduct_code() {
		return product_code;
	}
	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public int getProduct_amount() {
		return product_amount;
	}
	public void setProduct_amount(int product_amount) {
		this.product_amount = product_amount;
	}
	public String getProduct_size() {
		return product_size;
	}
	public void setProduct_size(String product_size) {
		this.product_size = product_size;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	
	
	public String getOrder_phone() {
		return order_phone;
	}
	public void setOrder_phone(String order_phone) {
		this.order_phone = order_phone;
	}
	
	public int getProduct_price() {
		return product_price;
	}
	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}
	public String getPostnum() {
		return postnum;
	}
	public void setPostnum(String postnum) {
		this.postnum = postnum;
	}
	public String getOrder_name() {
		return order_name;
	}
	public void setOrder_name(String order_name) {
		this.order_name = order_name;
	}
	public Timestamp getOrder_date() {
		return order_date;
	}
	public void setOrder_date(Timestamp order_date) {
		this.order_date = order_date;
	}
	
	public String getOrder_memo() {
		return order_memo;
	}
	public void setOrder_memo(String order_memo) {
		this.order_memo = order_memo;
	}
	public String getPaymethod() {
		return paymethod;
	}
	public void setPaymethod(String paymethod) {
		this.paymethod = paymethod;
	}
	
	public String getReview_yn() {
		return review_yn;
	}
	public void setReview_yn(String review_yn) {
		this.review_yn = review_yn;
	}
	@Override
	public String toString() {
		return "OrderDTO [order_num=" + order_num + ", userID=" + userID + ", address1=" + address1 + ", address2="
				+ address2 + ", postnum=" + postnum + ", order_name=" + order_name + ", order_phone=" + order_phone
				+ ", order_date=" + order_date + ", order_memo=" + order_memo + ", orderProduct_bno=" + orderProduct_bno
				+ ", product_code=" + product_code + ", product_amount=" + product_amount + ", product_size="
				+ product_size + ", product_price=" + product_price + ", product_name=" + product_name + ", paymethod="
				+ paymethod + ", review_yn=" + review_yn + ", totalbill=" + totalbill + "]";
	}
	
	
}
