package com.ozads.futsalnepal.request;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;




@SuppressWarnings("serial")
public class CourtCreatationRequest implements Serializable {

	@JsonIgnore
	private Long id;
	private String courtName;
	private String phoneNo;
	
	private String email;
	private String username;
	private List<CourtAddressCreatationRequest> courtAddress;
	
	
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the storeName
	 */
	public String getCourtName() {
		return courtName;
	}
	/**
	 * @param storeName the storeName to set
	 */
	public void setCourtName(String courtName) {
		this.courtName = courtName;
	}
	/**
	 * @return the phoneNo
	 */
	public String getPhoneNo() {
		return phoneNo;
	}
	/**
	 * @param phoneNo the phoneNo to set
	 */
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	/**
	 * @return the panNo
	 */
	
	
	/**
	 * @return the storeAddress
	 */
	public List<CourtAddressCreatationRequest> getCourtAddress() {
		return courtAddress;
	}
	/**
	 * @param storeAddress the storeAddress to set
	 */
	public void setStoreAddress(List<CourtAddressCreatationRequest> courtAddress) {
		this.courtAddress = courtAddress;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
}
