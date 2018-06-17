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
	private String firstName;
	private String lastName;
	private String email;
	private String phonrNo;
	private String username;
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
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhonrNo() {
		return phonrNo;
	}
	public void setPhonrNo(String phonrNo) {
		this.phonrNo = phonrNo;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
		token = token;
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
