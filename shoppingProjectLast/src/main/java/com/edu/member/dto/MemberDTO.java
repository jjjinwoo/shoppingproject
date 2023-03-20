package com.edu.member.dto;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("memberDTO")
public class MemberDTO {
	
	private String 	userID;			// 사용자 아이디
	private String 	pwd;		// 사용자 비밀번호
	private String 	address1;
	private String 	address2;
	private String 	postnum;
	private String 	name;		// 사용자 이름
	private	String	age;
	private String	phone;
	private String  gender;
	private String 	email;		// 사용자 이메일
	private Date 	joinDate;	// 가입일자
	private int  grade;
	
	public MemberDTO() {}		// 기본생성자

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
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

	public String getPostnum() {
		return postnum;
	}

	public void setPostnum(String postnum) {
		this.postnum = postnum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return "MemberDTO [userID=" + userID + ", pwd=" + pwd + ", address1=" + address1 + ", address2=" + address2
				+ ", postnum=" + postnum + ", name=" + name + ", age=" + age + ", phone=" + phone + ", gender=" + gender
				+ ", email=" + email + ", joinDate=" + joinDate + ", grade=" + grade + "]";
	}
	
	

	
	
}
