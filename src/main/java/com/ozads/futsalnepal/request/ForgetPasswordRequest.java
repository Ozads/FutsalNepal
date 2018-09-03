package com.ozads.futsalnepal.request;

import java.io.Serializable;


@SuppressWarnings("serial")
public class ForgetPasswordRequest implements Serializable {
	
	private String newPassword;
	private String confirmPassword;
	
	public String getNewPassword() {
		return newPassword;
	}
	
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	public String getConfirmPassword() {
		return confirmPassword;
	}
	
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	
	

}
