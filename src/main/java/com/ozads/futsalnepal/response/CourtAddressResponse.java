package com.ozads.futsalnepal.response;

import java.io.Serializable;


@SuppressWarnings("serial")
public class CourtAddressResponse implements Serializable {

	private Long id;
	
	private String district;
	private String locality;
	private Long wardNo;
	
	

	

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	
	
	
	
	
}
