package com.ozads.futsalnepal.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ozads.futsalnepal.dto.LoginResponseDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
@SuppressWarnings("serial")
public class LoginResponse implements Serializable {

	private LoginResponseDto user;

	/**
	 * @param user
	 */
	public LoginResponse(LoginResponseDto user) {
		super();
		this.user = user;
	}

	/**
	 * @return the user
	 */
	public LoginResponseDto getUser() {
		return user;
	}
	
	
	
	
}
