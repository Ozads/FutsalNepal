package com.ozads.futsalnepal.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ozads.futsalnepal.dto.UserDto;
import com.ozads.futsalnepal.request.PasswordEditRequest;
import com.ozads.futsalnepal.request.UserCreationRequest;
import com.ozads.futsalnepal.request.UserEditRequest;
import com.ozads.futsalnepal.response.UserResponseDto;
import com.ozads.futsalnepal.services.UserService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping(value="/api/v1/user")
public class UserController {
	
	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserService userService;
	@ApiImplicitParams({
		@ApiImplicitParam(name="token",required=true,dataType="String",paramType="header")})
	@ApiOperation(value="Create User",notes="Api to create user")
	@RequestMapping( method = RequestMethod.POST)
	public ResponseEntity<Object> saveUser(@RequestHeader Long userId ,@Validated @RequestBody UserCreationRequest userDto) {
		LOG.debug("Request for user creation in accepted");
		userService.saveUser(userId,userDto);
		LOG.debug("user is created");
		return new ResponseEntity<Object>("User Created",HttpStatus.CREATED);
	}
	

	@ApiImplicitParams({
		@ApiImplicitParam(name="token",required=true,dataType="String",paramType="header")})
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	@ApiOperation(value="Delete User",notes="Api to delete users")
	public ResponseEntity<Object> deletuser(@PathVariable("id") Long id){
		LOG.debug("Request accepted to delete user.");
		userService.deleteUser(id);
		return new ResponseEntity<Object>("User Deleted",HttpStatus.OK);
		
	}
	@ApiImplicitParams({
		@ApiImplicitParam(name="token",required=true,dataType="String",paramType="header")})
	@ApiOperation(value="Edit User",notes="Api to edit users")
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<Object> editUser(@RequestHeader Long userId,@RequestBody UserEditRequest userEditRequest){
		LOG.debug("Request accepted to edit user.");
		userService.editUser(userId,userEditRequest);
		
		return new ResponseEntity<Object>(HttpStatus.OK);
		
		
		
	}
	@ApiImplicitParams({
		@ApiImplicitParam(name="token",required=true,dataType="String",paramType="header")})
	@ApiOperation(value=" Change Password",notes="Api to change password")
	@RequestMapping(value="/changePassword",method=RequestMethod.PUT)
	public ResponseEntity<Object> changePassword(@RequestHeader Long userId,
			@RequestBody PasswordEditRequest passwordEditRequest){
		LOG.debug("Request accepted to change password.");
		userService.changePassword(userId,passwordEditRequest);
		return new ResponseEntity<Object>("Password changed",HttpStatus.OK);
		
	}
	
	@ApiImplicitParams({
		@ApiImplicitParam(name="token",required=true,dataType="String",paramType="header")})
	@RequestMapping(method=RequestMethod.GET)
	@ApiOperation(value="List all users",notes="Api to List all users")
	public ResponseEntity<Object> listAllUser(){
		List<UserDto> user=userService.listAllUsers();
		Map<Object, Object> response = new HashMap<Object, Object>();
		response.put("user", user);
		return new ResponseEntity<Object>(response,HttpStatus.OK);
	}
	@ApiImplicitParams({
		@ApiImplicitParam(name="token",required=true,dataType="String",paramType="header")})
	@ApiOperation(value="Get user",notes="Api to List a users")
	@RequestMapping(value="/{userId}",method=RequestMethod.GET)
	public ResponseEntity<Object> getUser(@RequestHeader Long userId){
		UserResponseDto user=userService.getUser(userId);
		Map<Object, Object> response = new HashMap<Object, Object>();
		response.put("user", user);
		return new ResponseEntity<Object>(response,HttpStatus.OK);
	}
	
}	


