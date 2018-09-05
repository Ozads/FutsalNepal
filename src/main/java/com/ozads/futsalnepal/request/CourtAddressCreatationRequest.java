package com.ozads.futsalnepal.request;

import java.io.Serializable;


@SuppressWarnings("serial")
public class CourtAddressCreatationRequest implements Serializable {


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
	
	
	
	
	
	
	
	
	
	
}
