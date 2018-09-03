package com.ozads.futsalnepal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ozads.futsalnepal.dto.CourtDto;
import com.ozads.futsalnepal.model.Court;

import com.ozads.futsalnepal.util.Status;


@Repository
public interface CourtRepository extends JpaRepository<Court, Long> {

	
	Court findByPhoneNoAndStatusNot(String phoneNo, Status delete);


	
	Court findCourtByIdAndStatusNot(Long id, Status delete);


	
	Court findByEmailAndStatusNot(String email, Status delete);


	List<Court> findAllCourtByStatusNot(Status delete);


	
	Court findByIdAndStatusNot(Long storeId, Status delete);


	Court findCourtById(Long id);


	List<CourtDto> findAllCourtById(Court court);


	
	


	
	
}
