package com.ozads.futsalnepal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ozads.futsalnepal.model.Court;
import com.ozads.futsalnepal.model.CourtAddress;


@Repository
public interface CourtAddressRepository extends JpaRepository<CourtAddress,Long> {


	CourtAddress findCourtAddressById(Long id);
	
	List<Court> findAllCourtByCourt(String courtName);

	List<CourtAddress> findAddressByDistrictAndLocalityAndWardNo(String district, String locality, Long wardNo);


}
