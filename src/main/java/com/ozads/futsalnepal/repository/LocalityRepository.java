package com.ozads.futsalnepal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ozads.futsalnepal.model.District;
import com.ozads.futsalnepal.model.Locality;

@Repository
public interface LocalityRepository extends JpaRepository<Locality,Long> {

	List<Locality> findAllLocalityByDistrict(District district);

	Locality findByLocality(String locality);

}
