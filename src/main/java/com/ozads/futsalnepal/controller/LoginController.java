package com.ozads.futsalnepal.controller;


import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ozads.futsalnepal.dto.LoginDto;
import com.ozads.futsalnepal.dto.LoginResponseDto;
import com.ozads.futsalnepal.request.ForgetPasswordRequest;
import com.ozads.futsalnepal.response.LoginResponse;
import com.ozads.futsalnepal.services.LoginService;
import com.ozads.futsalnepal.util.Status;

import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/api/v1")
public class LoginController {

	private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private LoginService loginService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ApiOperation(value="Login",notes="Api to Login")
	public ResponseEntity<Object> login(@Valid @RequestBody LoginDto loginDto) {
		LOG.debug("Login Request", loginDto.getUsername());
		LoginResponseDto loginResponceDto = loginService.logInUser(loginDto.getUsername(), loginDto.getPassword(),
				Status.ACTIVE, loginDto.getDeviceId());
		return new ResponseEntity<Object>(new LoginResponse(loginResponceDto), HttpStatus.OK);
	}
	@ApiOperation(value="Logout",notes="Api to Logout")
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public ResponseEntity<Object> logout(@RequestHeader Long userId) {
		loginService.logout(userId);
		return new ResponseEntity<Object>("You are logged out from the system",HttpStatus.OK);
	}
	@ApiOperation(value="Forgot Password",notes="Api to forget password")
	@RequestMapping(value = "/forgotPassword", method = RequestMethod.POST)
	public ResponseEntity<Object> forgetPassword(@RequestBody String Email){
		LOG.debug("Request Accepted for Reset password");
		loginService.resetPassword(Email);
		return new ResponseEntity<Object>("Check Email for changing password",HttpStatus.OK);
	}
	@ApiOperation(value="Reset Password",notes="Api to reset password")
	@RequestMapping(value="/resetPassword/{value}",method=RequestMethod.POST)
	public ResponseEntity<Object> resetPassword(@PathVariable ("value") String token,@RequestBody ForgetPasswordRequest forgetPasswordRequest){
		LOG.debug("Request Accepted to reset password");
		loginService.resetForgetPassword(token,forgetPasswordRequest);
		return new ResponseEntity<Object>("Password Reset",HttpStatus.OK);
	}
	
	@ApiOperation(value="Check Token")
	@RequestMapping(value="/checkToken",method=RequestMethod.POST)
	public ResponseEntity<Object> checkToken(@RequestHeader String token){
		LOG.debug("Token checking");
		loginService.chekToken(token);
		return new ResponseEntity<Object>("Token is valid",HttpStatus.OK);
	}
	
}
