package com.ozads.futsalnepal.request;

import java.io.Serializable;

@SuppressWarnings("serial")
public class AddressEditRequest implements Serializable {

	
	private String district;
	private String area;

	private String street;
	
	private Long id;

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	
	

}
