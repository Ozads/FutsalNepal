package com.ozads.futsalnepal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ozads.futsalnepal.model.Login;
import com.ozads.futsalnepal.util.Status;

public interface LoginRepository extends JpaRepository<Login,Long> {

	
	Login findByUsernameAndStatusNot(String username, Status delete);
	
	Login findLoginByUsername(String username);
	
	Login findLoginById(Long userId);
	
	Login findLoginByEmailAndStatusNot(String email, Status delete);
	
	Login findLoginByEmailAndStatus(String email, Status blocked);
	
	Login findByUsernameAndStatus(String username, Status blocked);
	
	Login findByToken(String token);
	
	Login findByIdAndToken(Long loginId, String token);

	Login findByUsername(String username);
}
