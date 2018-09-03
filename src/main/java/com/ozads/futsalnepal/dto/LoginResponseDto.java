package com.ozads.futsalnepal.dto;

import java.io.Serializable;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import com.ozads.futsalnepal.util.LoginType;
import com.ozads.futsalnepal.util.Status;
import com.ozads.futsalnepal.util.UserRole;

@SuppressWarnings("serial")
@JsonInclude(Include.NON_NULL)
public class LoginResponseDto implements Serializable {
	
	private Long id;
	private String fullName;
	private String email;
	private String phoneNo;
	
	private String deviceId;
	private Status status;
	private UserRole userRole;
	private LoginType loginType;
	private String token;
	
	public LoginResponseDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
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



	public String getDeviceId() {
		return deviceId;
	}



	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}



	public Status getStatus() {
		return status;
	}



	public void setStatus(Status status) {
		this.status = status;
	}



	public UserRole getUserRole() {
		return userRole;
	}



	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}



	public LoginType getLoginType() {
		return loginType;
	}



	public void setLoginType(LoginType loginType) {
		this.loginType = loginType;
	}



	public String getToken() {
		return token;
	}



	public void setToken(String token) {
		this.token = token;
	}

	
	public static class Builder {
		private Long id;
		private String token;
		
		
		public Builder id(Long id) {
			this.id = id;
			return this;
		}
		
		public Builder token(String token) {
			this.token = token;
			return this;
		}

		
			public LoginResponseDto build() {
			return new LoginResponseDto(this);
		}
	}

	private LoginResponseDto(Builder builder) {
		this.id = builder.id;
		this.token = builder.token;
	}
	
}
