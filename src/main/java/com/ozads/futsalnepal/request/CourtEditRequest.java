package com.ozads.futsalnepal.request;

import java.io.Serializable;
import java.util.List;


@SuppressWarnings("serial")
public class CourtEditRequest implements Serializable {
	private Long id;
	private String courtName;
	private String phoneNo;
	
	private String email;
	
	private List<CourtAddressEditRequest> courtAddressEdit;
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
	
	public List<CourtAddressEditRequest> getCourtAddressEdit() {
		return courtAddressEdit;
	}
	public void setCourtAddressEdit(List<CourtAddressEditRequest> courtAddressEdit) {
		this.courtAddressEdit = courtAddressEdit;
	}
	
	
	
	
	
	
}
