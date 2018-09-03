package com.ozads.futsalnepal.request;

import java.io.Serializable;


@SuppressWarnings("serial")
public class TimeSlotCreatationRequest implements Serializable {
	
	private String timeSlotName;
	private double price;
	
	

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getTimeSlotName() {
		return timeSlotName;
	}

	public void setTimeSlotName(String timeSlotName) {
		this.timeSlotName = timeSlotName;
	}
	
	
	
	
	
	

	

}
