package com.ozads.futsalnepal.response;

import java.io.Serializable;
import java.util.List;



@SuppressWarnings("serial")
public class CustomerResponseDto implements Serializable {

	private String fullName;
	
	
	private String email;
	private String phoneNo;
	

	List<AddressResponseDto> address;


	public String getFullName() {
		return fullName;
	}


	public void setFullName(String fullName) {
		this.fullName = fullName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhoneNo() {
		return phoneNo;
	}


	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}


	public List<AddressResponseDto> getAddress() {
		return address;
	}


	public void setAddress(List<AddressResponseDto> address) {
		this.address = address;
	}

	

	

	

}
