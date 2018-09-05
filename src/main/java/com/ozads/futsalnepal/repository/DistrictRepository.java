package com.ozads.futsalnepal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ozads.futsalnepal.model.District;

@Repository
public interface DistrictRepository extends JpaRepository<District,Long>  {

	

	List<District> findAll();

	District findByDistrict(String district);

}
