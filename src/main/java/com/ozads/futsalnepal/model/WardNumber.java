package com.ozads.futsalnepal.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

@SuppressWarnings("serial")
@Entity
public class WardNumber {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private Long wardNumber;
	
	@JoinColumn(name="locality_id")
	private Locality locality;
	
	

	public Long getWardNumber() {
		return wardNumber;
	}

	public void setWardNumber(Long wardNumber) {
		this.wardNumber = wardNumber;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Locality getLocality() {
		return locality;
	}

	public void setLocality(Locality locality) {
		this.locality = locality;
	}
	
	
}
