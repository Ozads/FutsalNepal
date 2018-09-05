package com.ozads.futsalnepal.model;

import javax.persistence.GenerationType;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@SuppressWarnings("serial")
@Entity

public class Locality implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String locality;
	
	@ManyToOne
	@JoinColumn(name="district_id")
	private District district;
	
	@OneToMany(mappedBy="locality", fetch=FetchType.LAZY)
	private List<WardNumber> wardNo; 
	
	
	
	public Locality() {
		
	}
	
	public Locality(Long id) {
		this.id=id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public List<WardNumber> getWardNo() {
		return wardNo;
	}

	public void setWardNo(List<WardNumber> wardNo) {
		this.wardNo = wardNo;
	}
	
	
}
