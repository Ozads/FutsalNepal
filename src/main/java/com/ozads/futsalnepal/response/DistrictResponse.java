package com.ozads.futsalnepal.response;

import java.io.Serializable;

@SuppressWarnings("serial")
public class DistrictResponse implements Serializable {
	
	private Long id;
	private String District;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDistrict() {
		return District;
	}
	public void setDistrict(String district) {
		District = district;
	}
	
	

}
