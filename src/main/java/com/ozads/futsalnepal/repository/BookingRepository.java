package com.ozads.futsalnepal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ozads.futsalnepal.model.Booking;
import com.ozads.futsalnepal.model.Court;
import com.ozads.futsalnepal.util.BookingStatus;



@Repository
public interface BookingRepository extends JpaRepository<Booking,Long> {

	
	List<Booking> findAllOrderByCourtId(Long courtId);




	Booking findBookingByIdAndBookingStatusNot(Long bookingId, BookingStatus BLOCKED);



	List<Booking> findBookingByBookingStatusNot(BookingStatus BLOCKED);



	
	List<Booking> findBookingByCourtAndBookingStatus(Court court, BookingStatus AVAILABLE);



}
