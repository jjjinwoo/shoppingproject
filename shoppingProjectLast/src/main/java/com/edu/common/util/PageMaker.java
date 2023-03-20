package com.edu.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PageMaker {
	
	private Criteria cri;
	private int totalCount; //전체 게시글 갯수
	private int startPage; //화면 하단에 보여지는 시작 페이지 번호
	private int endPage; //화면 하단에 보여지는 마지막 페이지 번호
	private boolean prev; //이전
	private boolean next; //다음
	private int displayPageNum = 10; //화면 하단에 보여질 페이지 번호 갯수
	
	private static final Logger logger = LoggerFactory.getLogger(PageMaker.class);

	public Criteria getCri() {
		return cri;
	}

	public void setCri(Criteria cri) {
		this.cri = cri;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		
		logger.info("****** setTotalCount(int totalCount) ==>" + totalCount);
		calculatePages(); //총 데이터 건수를 가지고 페이지 관련하여 필요한 계산을 한다.
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getDisplayPageNum() {
		return displayPageNum;
	}

	public void setDisplayPageNum(int displayPageNum) {
		this.displayPageNum = displayPageNum;
	}

	public static Logger getLogger() {
		return logger;
	}

	@Override
	public String toString() {
		return "PageMaker [cri=" + cri + ", totalCount=" + totalCount + ", startPage=" + startPage + ", endPage="
				+ endPage + ", prev=" + prev + ", next=" + next + ", displayPageNum=" + displayPageNum + "]";
	}
	
	private void calculatePages() {
		//페이지 계산 : 총 데이터 건수를 가지고 페이지를 계산한다.
		logger.info("****** calculatePages()==>" + cri.getPage() + ", " + displayPageNum);
		
		//Math.ceil(숫자): 입력 받은 숫자보다 크거나 같은 정수 중에서 가장 작은 정수를 리턴한다.
		endPage = (int)(Math.ceil(cri.getPage() / (double)displayPageNum) * displayPageNum);
		startPage = (endPage - displayPageNum) + 1;
		
		//endPage가 displayPageNum 보다 작거나 같으면 startPage는 무조건 1이 된다.
		if(endPage <= displayPageNum) startPage = 1;
		//시작 페이지가 0 이하이면 1로 설정해준다.
		if(startPage <= 0) startPage = 1;
		
		//realEndPage: 실제 가지고 있는 전체 페이지의 마지막 페이지의 수를 구해서 저장할 변수 = 보여질 총 페이지의 갯수(전체 게시글 / 한 페이지당 보여질 게시글 수)
		int realEndPage = (int)(Math.ceil(totalCount / (double)cri.getPerPageNum()));
		
		//realEndPage가 10의 단위가 아니라면 endPage는 realPage가 된다.
		if(endPage > realEndPage) {
			endPage = realEndPage;
		}
		
		//시작 페이지가 1이 아닌 경우에만 prev 버튼을 보여준다. (true 일때만 보여주기 위해 boolean 값을 지정한다.)
		prev = startPage == 1 ? false : true;
		
		//endPage * perPageNum < totalCount 이면 next 버튼을 보여준다. (아직 보여줄 데이터가 남아 있음)
		next = endPage * cri.getPerPageNum() < totalCount ? true : false;
	}
	

}
