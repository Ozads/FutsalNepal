package com.ozads.futsalnepal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ozads.futsalnepal.model.Locality;
import com.ozads.futsalnepal.model.WardNumber;

@Repository
public interface WardNumberRepository extends JpaRepository<WardNumber,Long>  {

	List<WardNumber> findAllWardNumberByLocality(Locality locality);

}
