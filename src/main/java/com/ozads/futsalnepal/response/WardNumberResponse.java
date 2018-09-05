package com.ozads.futsalnepal.response;

import java.io.Serializable;

@SuppressWarnings("serial")
public class WardNumberResponse implements Serializable {
	private Long wardNumber;

	public Long getWardNumber() {
		return wardNumber;
	}

	public void setWardNumber(Long wardNumber) {
		this.wardNumber = wardNumber;
	}
	
	
}
