package com.edu.product.dto;

import java.sql.Timestamp;

public class StatsDTO {

	private String stats_date;	//날짜(년-월)
	private int join_count; //회원가입 수 
	private int unregister_count; //회원 탈퇴 수
	private int enter_count; //방문 수(home이 로딩 되는 수)
	
	
	public String getStats_date() {
		return stats_date;
	}
	public void setStats_date(String stats_date) {
		this.stats_date = stats_date;
	}
	public int getJoin_count() {
		return join_count;
	}
	public void setJoin_count(int join_count) {
		this.join_count = join_count;
	}
	public int getUnregister_count() {
		return unregister_count;
	}
	public void setUnregister_count(int unregister_count) {
		this.unregister_count = unregister_count;
	}
	public int getEnter_count() {
		return enter_count;
	}
	public void setEnter_count(int enter_count) {
		this.enter_count = enter_count;
	}
	@Override
	public String toString() {
		return "StatsDTO [stats_date=" + stats_date + ", join_count=" + join_count + ", unregister_count="
				+ unregister_count + ", enter_count=" + enter_count + "]";
	}
	
	
	
	
	
}
