package com.ozads.futsalnepal.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;


import com.ozads.futsalnepal.request.TimeSlotCreatationRequest;
import com.ozads.futsalnepal.services.TimeSlotService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "api/v1/items")
public class TimeSlotController {

	@Autowired
	TimeSlotService timeSlotService;

	private static final Logger LOG = LoggerFactory.getLogger(TimeSlotController.class);
	//@ApiImplicitParams({
		//@ApiImplicitParam(name="token",required=true,dataType="String",paramType="header")})
	@ApiOperation(value="Save Timeslot")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Object> saveTimeSlot(@RequestHeader Long courtID, @RequestBody TimeSlotCreatationRequest request ) {
		LOG.debug("items cratation by user");
		timeSlotService.create(courtID, request);
		return new ResponseEntity<Object>("TimeSlot added",HttpStatus.CREATED);
	}

}
