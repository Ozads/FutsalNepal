package com.ozads.futsalnepal.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.ozads.futsalnepal.util.Status;

@SuppressWarnings("serial")
@Entity
public class Court implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="court_name")
	private String courtName;
	
	@Column(name="court_address")
	@OneToMany(mappedBy="court", fetch=FetchType.LAZY)
	private List<CourtAddress> courtAddress;
	
	@Column(name="phone_No")
	private String phoneNo;
	
	private String email;
	
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date")
	private Date createdDate;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="modified_date")
	private Date modifiedDate;
	
	@Column(name="created_by")
	private Long createdBy;
	
	@Enumerated(EnumType.STRING)
	protected Status status;
	
	@OneToMany(mappedBy="court", fetch=FetchType.LAZY)
	private List<Booking> bookings;
	
	@OneToMany(mappedBy="court", fetch=FetchType.LAZY)
	private List<TimeSlot> timeSlots;
	
	@Temporal(TemporalType.DATE)
	private Date date;
	
	
	
	@Column(name="image_url")
	private String courtPicture;
	

	
	
	

	

	public Court() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Court(Long courtId) {
		this.id=courtId;
	}

	

	public List<CourtAddress> getCourtAddress() {
		return courtAddress;
	}

	public void setCourtAddress(List<CourtAddress> courtAddress) {
		this.courtAddress = courtAddress;
	}

	public void setCourtPicture(String courtPicture) {
		this.courtPicture = courtPicture;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	

	public String getCourtPicture() {
		return courtPicture;
	}

	public void setCourtPictures(String courtPicture) {
		this.courtPicture = courtPicture;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCourtName() {
		return courtName;
	}

	public void setCourtName(String courtName) {
		this.courtName = courtName;
	}

	

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	public List<TimeSlot> getTimeSlots() {
		return timeSlots;
	}

	public void setTimeSlots(List<TimeSlot> timeSlots) {
		this.timeSlots = timeSlots;
	}
	
	
	
	
}
