package com.ozads.futsalnepal.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;

import com.ozads.futsalnepal.response.WardNumberResponse;
import com.ozads.futsalnepal.services.WardNumberService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/api/va/vdc")
public class WardNumberController {

	private static final Logger LOG = LoggerFactory.getLogger(WardNumberController.class);
	@Autowired
	WardNumberService wardNumberService;
	
	@ApiOperation(value="List all wardNumber by locality",notes="Api to list wardNumber")
	@RequestMapping(value="/{locality}",method=RequestMethod.GET)
	public ResponseEntity<Object> listAllLoclity(@PathVariable("locality") String locality){
		LOG.debug("Request Accepted to List a Vdc");
		 List<WardNumberResponse> wardNumberResponse=wardNumberService.getWardNumber(locality);
		Map<Object,Object> response=new HashMap<>();
		response.put("wardNumber",wardNumberResponse);
		return new ResponseEntity<Object>(response,HttpStatus.OK);
		
	}
}
