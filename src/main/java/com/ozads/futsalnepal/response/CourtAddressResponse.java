package com.ozads.futsalnepal.response;

import java.io.Serializable;


@SuppressWarnings("serial")
public class CourtAddressResponse implements Serializable {

	private Long id;
	
	private String longitude;
	private String latitude;
	
	

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	
	
	
	
	
}
