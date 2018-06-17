package com.ozads.futsalnepal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ozads.futsalnepal.model.TimeSlot;


@Repository
public interface TimeSlotRepository  extends JpaRepository<TimeSlot, Long>{

	/**
	 * @param itemsName
	 * @return
	 */
	TimeSlot findTimeSlotByTimeSlotName(String timeSlotName);

}
