package com.ozads.futsalnepal.request;

import java.io.Serializable;
import java.util.List;



@SuppressWarnings("serial")
public class CustomerCreationRequest implements Serializable {
	
	private String fullName;
	
	private String email;
	private String phoneNo;
	
	private String password;
	
	List<CustomerAddressCreationRequest> address;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<CustomerAddressCreationRequest> getAddress() {
		return address;
	}

	public void setAddress(List<CustomerAddressCreationRequest> address) {
		this.address = address;
	}


	
	
	
	
	
	
	

}
