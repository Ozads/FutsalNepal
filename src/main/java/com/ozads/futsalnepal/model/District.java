package com.ozads.futsalnepal.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@SuppressWarnings("serial")
@Entity
public class District {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String district;
	
	@OneToMany(mappedBy= "district", fetch=FetchType.LAZY)
	private List<Locality> locality;
	
	public District(Long id) {
		this.id=id;
	}
	
	
	public District() {
		
	}


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


	public List<Locality> getLocality() {
		return locality;
	}


	public void setLocality(List<Locality> locality) {
		this.locality = locality;
	}
	
	
}
