package com.ozads.futsalnepal.dto;

import java.io.Serializable;


@SuppressWarnings("serial")
public class CourtAddressDto implements Serializable {

	private Long id;

	private String latitude;
	private String longitude;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	
	
	
}
