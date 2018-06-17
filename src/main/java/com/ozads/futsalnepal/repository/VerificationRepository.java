package com.ozads.futsalnepal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ozads.futsalnepal.model.Verification;
import com.ozads.futsalnepal.util.VerificationStatus;


@Repository
public interface VerificationRepository extends JpaRepository<Verification,Long> {

	
	Verification findVerificationByTokenAndStatusNot(String token, VerificationStatus expire);

	
	Verification findVerificationByEmailAndStatusNot(String email, VerificationStatus expire);


}
