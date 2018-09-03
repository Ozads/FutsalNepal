package com.ozads.futsalnepal.dto;

import java.io.Serializable;



@SuppressWarnings("serial")
public class UserDto implements Serializable {

	private Long id;
	private String fullName;
	
	private String email;
	private String phoneNo;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	

	

}
