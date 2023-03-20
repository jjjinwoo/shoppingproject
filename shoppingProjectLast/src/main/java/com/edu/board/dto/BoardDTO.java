package com.edu.board.dto;

import java.sql.Timestamp;

public class BoardDTO {

	private int qna_bno; // 게시글 번호
	private String qna_subject;  //게시글 제목
	private String userID;  // 사용자 아이디
	private String qna_content; // 게시글 내용
	private Timestamp qna_regDate; // 게시글 작성일자
	private int qna_readCount; //게시글 조회수
	
	public int getQna_bno() {
		return qna_bno;
	}
	public void setQna_bno(int qna_bno) {
		this.qna_bno = qna_bno;
	}
	public String getQna_subject() {
		return qna_subject;
	}
	public void setQna_subject(String qna_subject) {
		this.qna_subject = qna_subject;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getQna_content() {
		return qna_content;
	}
	public void setQna_content(String qna_content) {
		this.qna_content = qna_content;
	}
	public Timestamp getQna_regDate() {
		return qna_regDate;
	}
	public void setQna_regDate(Timestamp qna_regDate) {
		this.qna_regDate = qna_regDate;
	}
	public int getQna_readCount() {
		return qna_readCount;
	}
	public void setQna_readCount(int qna_readCount) {
		this.qna_readCount = qna_readCount;
	}
	@Override
	public String toString() {
		return "BoardDTO [qna_bno=" + qna_bno + ", qna_subject=" + qna_subject + ", userID=" + userID + ", qna_content="
				+ qna_content + ", qna_regDate=" + qna_regDate + ", qna_readCount=" + qna_readCount + "]";
	}
	
	
	
}
