package com.edu.board.dto;

import java.sql.Timestamp;

//qna 게시글의 댓글DTO
public class CommentDTO {

	private int reply_bno;
	private int qna_bno;
	private String reply_content;
	private Timestamp reply_regDate;
	

	public int getReply_bno() {
		return reply_bno;
	}
	public void setReply_bno(int reply_bno) {
		this.reply_bno = reply_bno;
	}
	public int getQna_bno() {
		return qna_bno;
	}
	public void setQna_bno(int qna_bno) {
		this.qna_bno = qna_bno;
	}
	public String getReply_content() {
		return reply_content;
	}
	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}
	public Timestamp getReply_regDate() {
		return reply_regDate;
	}
	public void setReply_regDate(Timestamp reply_regDate) {
		this.reply_regDate = reply_regDate;
	}
	@Override
	public String toString() {
		return "CommentDTO [reply_bno=" + reply_bno + ", qna_bno=" + qna_bno + ", reply_content=" + reply_content
				+ ", reply_regDate=" + reply_regDate + ", getReply_bno()=" + getReply_bno() + ", getQna_bno()="
				+ getQna_bno() + ", getReply_content()=" + getReply_content() + ", getReply_regDate()="
				+ getReply_regDate() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
	
	
}
