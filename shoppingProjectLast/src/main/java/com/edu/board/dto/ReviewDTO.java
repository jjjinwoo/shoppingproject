package com.edu.board.dto;

import java.sql.Timestamp;

import org.springframework.stereotype.Component; 



@Component("reviewDTO")
public class ReviewDTO {

	private int		review_bno;
	private String	userID;
	private String	product_code;
	private String	review_subject;
	private String	review_content;
	private Timestamp	review_date;
	private int		review_star;
	private long order_num;
	
	public long getOrder_num() {
		return order_num;
	}

	public void setOrder_num(long order_num) {
		this.order_num = order_num;
	}

	public int getReview_bno() {
		return review_bno;
	}
	
	public void setReview_bno(int review_bno) {
		this.review_bno = review_bno;
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
	
	public String getReview_subject() {
		return review_subject;
	}
	
	public void setReview_subject(String review_subject) {
		this.review_subject = review_subject;
	}
	
	public String getReview_content() {
		return review_content;
	}
	
	public void setReview_content(String review_content) {
		this.review_content = review_content;
	}
	
	
	


	public Timestamp getReview_date() {
		return review_date;
	}

	public void setReview_date(Timestamp review_date) {
		this.review_date = review_date;
	}

	public int getReview_star() {
		return review_star;
	}
	
	public void setReview_star(int review_star) {
		this.review_star = review_star;
	}

	@Override
	public String toString() {
		return "ReviewDTO [review_bno=" + review_bno + ", userID=" + userID + ", product_code=" + product_code
				+ ", review_subject=" + review_subject + ", review_content=" + review_content + ", review_date="
				+ review_date + ", review_star=" + review_star + "]";
	}

	
	
} // end public class ReviewDTO