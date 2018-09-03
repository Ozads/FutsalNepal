package com.ozads.futsalnepal.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class LoginDto {
	@JsonIgnore
	
	private String email;
	
	private String password;
	private String deviceId;
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	
	
	
}
