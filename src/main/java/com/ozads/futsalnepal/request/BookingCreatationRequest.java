package com.ozads.futsalnepal.request;

import java.io.Serializable;


import com.fasterxml.jackson.annotation.JsonIgnore;


@SuppressWarnings("serial")
public class BookingCreatationRequest implements Serializable {

	@JsonIgnore
	private Long id;
	private double price;
	private String timeSlot;
	private String bookingName;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getTimeSlot() {
		return timeSlot;
	}
	public void setTimeSlot(String timeSlot) {
		this.timeSlot = timeSlot;
	}
	public String getBookingName() {
		return bookingName;
	}
	public void setBookingName(String bookingName) {
		this.bookingName = bookingName;
	}
	
	
	
	
	
	

}
