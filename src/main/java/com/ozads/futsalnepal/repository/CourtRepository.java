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


	/**
	 * @param id
	 * @param delete
	 * @return
	 */
	Court findCourtByIdAndStatusNot(Long id, Status delete);


	/**
	 * @param email
	 * @param delete
	 * @return
	 */
	Court findByEmailAndStatusNot(String email, Status delete);


	/**
	 * @param delete
	 * @return
	 */
	List<Court> findAllCourtByStatusNot(Status delete);


	/**
	 * @param storeId
	 * @param delete
	 * @return
	 */
	Court findByIdAndStatusNot(Long storeId, Status delete);


	/**
	 * @param id
	 * @return
	 */
	Court findCourtById(Long id);


	/**
	 * @param store
	 * @return
	 */
	List<CourtDto> findAllCourtById(Court court);


	/**
	 * @param address
	 * @return
	 */
	


	
	
}
