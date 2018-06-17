package com.ozads.futsalnepal.response;

import java.io.Serializable;


@SuppressWarnings("serial")
public class AddressResponseDto implements Serializable{
	
	private Long id;

	private String district;
	private String area;

	private String street;

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

	
	
	
	
	
}
