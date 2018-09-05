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


import com.ozads.futsalnepal.response.LocalityResponse;
import com.ozads.futsalnepal.services.LocalityService;

@RestController
@RequestMapping(value = "/api/va/locality")
public class LocalityController {
	
	private static final Logger LOG = LoggerFactory.getLogger(DistrictController.class);
	@Autowired
	LocalityService localityService;
	
	@RequestMapping(value="/{district}",method=RequestMethod.GET)
	public ResponseEntity<Object> listAllLocality(@PathVariable("district") String district){
		LOG.debug("Request Accepted to List a locality");
		List<LocalityResponse> localityResponse=localityService.getLocality(district);
		Map<Object,Object> response=new HashMap<>();
		response.put("district",localityResponse);
		return new ResponseEntity<Object>(response,HttpStatus.OK);
		
	}

}
