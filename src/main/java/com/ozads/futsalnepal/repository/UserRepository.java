package com.ozads.futsalnepal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ozads.futsalnepal.model.User;
import com.ozads.futsalnepal.util.Status;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {

	
	User findByEmailAndStatusNot(String email, Status Status);

	
	User findUserById(Long id);


	
	List<User> findAllUserByStatusNot(Status delete);

	
	User findUserByIdAndStatusNot(Long userId, Status delete);

}
