package com.ozads.futsalnepal.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ozads.futsalnepal.exceptions.AlreadyExistException;
import com.ozads.futsalnepal.model.TimeSlot;
import com.ozads.futsalnepal.model.User;
import com.ozads.futsalnepal.repository.TimeSlotRepository;
import com.ozads.futsalnepal.request.TimeSlotCreatationRequest;



@Service
public class TimeSlotService {

	private static final Logger LOG = LoggerFactory.getLogger(TimeSlotService.class);

	@Autowired
	TimeSlotRepository timeSlotRepository;

	
	public TimeSlot create(Long userID, TimeSlotCreatationRequest request) {
		LOG.debug("TimeSlot uploded by admin");
		TimeSlot timeSlot = timeSlotRepository.findTimeSlotByTimeSlotName(request.getTimeSlotName());
		if (timeSlot != null) {
			throw new AlreadyExistException("TimeSlot Alredy Exist");

		}
		
		TimeSlot it = new TimeSlot();
		
		
		it.setTimeSlotName(request.getTimeSlotName());
		it.setUser(new User(userID));

		
		timeSlotRepository.save(it);
		return it;

	}

}
