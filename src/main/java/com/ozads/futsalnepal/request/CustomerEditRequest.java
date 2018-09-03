package com.ozads.futsalnepal.request;

import java.io.Serializable;
import java.util.List;



@SuppressWarnings("serial")
public class CustomerEditRequest implements Serializable {
	private Long id;
	private String fullName;
	
	private String email;
	private String phoneNo;
	
	List<AddressEditRequest> address;

	
	
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



	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<AddressEditRequest> getAddress() {
		return address;
	}

		public void setAddress(List<AddressEditRequest> address) {
		this.address = address;
	}

}
