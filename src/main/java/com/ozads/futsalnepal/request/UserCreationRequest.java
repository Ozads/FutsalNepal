package com.ozads.futsalnepal.request;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ozads.futsalnepal.util.UserRole;


@SuppressWarnings("serial")
public class UserCreationRequest implements Serializable {
	
	private String fullName;
	
	private String email;
	private String phoneNo;
	
	private String password;
	@JsonIgnore
	private UserRole userRole;
	
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
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public UserRole getUserRole() {
		return userRole;
	}
	
	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}
	
	

}
