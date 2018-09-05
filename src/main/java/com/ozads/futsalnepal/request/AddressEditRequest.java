package com.ozads.futsalnepal.request;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

@SuppressWarnings("serial")
public class AddressEditRequest implements Serializable {

	@JsonIgnore
	private Long id;
	
	private String district;
	private String locality;
	private Long wardNo;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getLocality() {
		return locality;
	}
	public void setLocality(String locality) {
		this.locality = locality;
	}
	public Long getWardNo() {
		return wardNo;
	}
	public void setWardNo(Long wardNo) {
		this.wardNo = wardNo;
	}
	

	
}
