package com.ozads.futsalnepal.dto;

import java.io.Serializable;
import java.util.List;



@SuppressWarnings("serial")
public class CourtDto implements Serializable {

	private String courtName;
	private String phoneNo;
	
	private String email;
	private String username;
	List<CourtAddressDto> address;
	
	public String getCourtName() {
		return courtName;
	}
	
	public void setCourtName(String courtName) {
		this.courtName = courtName;
	}
	
	public String getPhoneNo() {
		return phoneNo;
	}
	
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public List<CourtAddressDto> getAddress() {
		return address;
	}
	
	public void setAddress(List<CourtAddressDto> address) {
		this.address = address;
	}
	
	
}
