package com.ozads.futsalnepal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ozads.futsalnepal.model.Address;


 
@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

	Address findAddressById(Long id);


}
