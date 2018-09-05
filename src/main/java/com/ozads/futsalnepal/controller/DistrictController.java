package com.ozads.futsalnepal.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;


import com.ozads.futsalnepal.response.DistrictResponse;
import com.ozads.futsalnepal.services.DistrictService;

import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping(value="api/va/district")
public class DistrictController {
	private static final Logger LOG = LoggerFactory.getLogger(DistrictController.class);
	@Autowired
	DistrictService districtService;
	
	@RequestMapping(value="/listDistricts",method=RequestMethod.GET)
	@ApiOperation(value="List All Districts",notes="Api to List all Districts")
	public ResponseEntity<Object> listAllDistrict(){
	LOG.debug("request Accepted to List All District");
	List<DistrictResponse> districtResponse=districtService.listAllDistrict();
	Map<Object, Object> response=new HashMap<>();
	response.put("district", districtResponse);
	return new ResponseEntity<Object>(response,HttpStatus.OK);
	}
}
