package com.ozads.futsalnepal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ozads.futsalnepal.model.Customer;
import com.ozads.futsalnepal.util.Status;


@Repository
public interface CustomerRepository extends JpaRepository<Customer ,Long> {

	
	
	List<Customer> findAllCustomerByStatusNot(Status delete);

	
	Customer findCustomerByIdAndStatusNot(Long customerId, Status delete);

	Customer findByEmailAndStatusNot(String email, Status delete);

	
	Customer findCustomerById(Long id);

	
	
	Customer findByIdAndStatusNot(Long customerId, Status delete);

	
	Customer findCustomerById(Customer customer);

}
