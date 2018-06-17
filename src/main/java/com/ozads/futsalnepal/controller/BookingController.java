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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ozads.futsalnepal.request.BookingCreatationRequest;
import com.ozads.futsalnepal.response.BookingResponseDto;
import com.ozads.futsalnepal.response.CourtBookingResponse;
import com.ozads.futsalnepal.services.BookingService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping(value="/api/v1/booking")
public class BookingController {
	
	@Autowired
	BookingService bookingService;
	
	private static final Logger LOG = LoggerFactory.getLogger(BookingController.class);
	@ApiImplicitParams({
		@ApiImplicitParam(name="token",required=true,dataType="String",paramType="header")})
	@ApiOperation(value="Save Booking")
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Object> saveBooking(@RequestHeader Long customerId,@RequestHeader Long courtId,
			@RequestBody BookingCreatationRequest bookingRequest ){
		LOG.debug("Request Accepted To book");
		bookingService.saveBooking(customerId,courtId,bookingRequest);
		return new ResponseEntity<Object>("Booking added",HttpStatus.CREATED);
	}
	@ApiImplicitParams({
		@ApiImplicitParam(name="token",required=true,dataType="String",paramType="header")})
	@ApiOperation(value="List All Bookings")
	@RequestMapping(value="/listAllBookingAvaliable",method=RequestMethod.GET)
	public ResponseEntity<Object> listAllBooking(){
		LOG.debug("Request to list all users");
		List<BookingResponseDto> responseDto= bookingService.listAllBooking();
		Map<Object, Object> response=new HashMap<>();
		response.put("booking", responseDto);
		return new ResponseEntity<Object>(response,HttpStatus.OK);
	}
	@ApiImplicitParams({
		@ApiImplicitParam(name="token",required=true,dataType="String",paramType="header")})
	@ApiOperation(value="List Bookings By Court")
	@RequestMapping(value="/listBookingByCourt",method=RequestMethod.GET)
	public ResponseEntity<Object> listBookingByCourt(@RequestHeader Long courtId){
		LOG.debug("Request accepted to List all booking by court");	
		List<CourtBookingResponse> responseDto= bookingService.listAllbookingByCourt(courtId);
		Map<Object, Object> response=new HashMap<>();
		response.put("court", responseDto);
		return new ResponseEntity<Object>(response,HttpStatus.OK);
	}
	
	
	
	@ApiImplicitParams({
		@ApiImplicitParam(name="token",required=true,dataType="String",paramType="header")})
	@ApiOperation(value="Delete Booking")
	@RequestMapping(value="/{bookingId}",method=RequestMethod.DELETE)
	public ResponseEntity<Object> deleteBooking(@PathVariable ("bookingId") Long bookingId){
		LOG.debug("Request Accepted to Delete booking");
		bookingService.deleteBooking(bookingId);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	

}
