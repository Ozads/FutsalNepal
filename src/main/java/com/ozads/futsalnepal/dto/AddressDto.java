package com.ozads.futsalnepal.dto;

import java.io.Serializable;


@SuppressWarnings("serial")
public class AddressDto implements Serializable {
	
	private Long id;

	private String district;
	private String locality;
	private Long wardNumber;
	
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
	public Long getWardNumber() {
		return wardNumber;
	}
	public void setWardNumber(Long wardNumber) {
		this.wardNumber = wardNumber;
	}
	
	
	
	

	
}