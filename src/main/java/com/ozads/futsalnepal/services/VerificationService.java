package com.ozads.futsalnepal.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ozads.futsalnepal.model.Verification;
import com.ozads.futsalnepal.repository.VerificationRepository;


@Service
public class VerificationService {
	
	@Autowired
	VerificationRepository  verificationRepository;
	
	@Transactional
	public Verification saveVerification(Verification verification) {
		return verificationRepository.save(verification);
		
	}

}
