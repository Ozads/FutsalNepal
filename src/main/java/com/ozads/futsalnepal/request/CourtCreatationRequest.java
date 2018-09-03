package com.ozads.futsalnepal.request;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;




@SuppressWarnings("serial")
public class CourtCreatationRequest implements Serializable {

	@JsonIgnore
	private Long id;
	private String courtName;
	private String phoneNo;
	
	private String email;
	
	private List<CourtAddressCreatationRequest> courtAddress;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	
	public List<CourtAddressCreatationRequest> getCourtAddress() {
		return courtAddress;
	}
	public void setCourtAddress(List<CourtAddressCreatationRequest> courtAddress) {
		this.courtAddress = courtAddress;
	}
	
	
	
}