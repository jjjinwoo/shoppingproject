package com.edu.common.util;

public class Criteria { //Criteria(기준) : 검색의 기준을 마련한다.

	private int page; //현재 페이지 번호
	private int perPageNum; //한 페이지당 보여줄 게시글의 갯수
	
	public Criteria() {
		this.page = 1; //목록은 1페이지부터 시작하도록 한다.(기본 페이지 = 1)
		this.perPageNum = 12; //기본 한 페이지 당 보여줄 게시글의 갯수 = 12
	}
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		if(page <= 0) { //현재 페이지가 0이하이면 1로 설정해준다.
			this.page = 1;
		} else {
			this.page = page;			
		}
	}
	public int getPerPageNum() {
		return perPageNum;
	}
	public void setPerPageNum(int perPageNum) { //생성자에서 설정된 perPageNum과 다른 값으로 변경 요청이 오면 초기 값을 유지하도록 한다.
		int originalPerPageNum = this.perPageNum;
		
		if(perPageNum != originalPerPageNum) {
			this.perPageNum = originalPerPageNum;
		} else {
			this.perPageNum = perPageNum;
		}
	}
	@Override
	public String toString() {
		return "Criteria [page=" + page + ", perPageNum=" + perPageNum + "]";
	}
	
	
}
