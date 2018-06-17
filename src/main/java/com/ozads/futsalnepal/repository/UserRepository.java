package com.ozads.futsalnepal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ozads.futsalnepal.model.User;
import com.ozads.futsalnepal.util.Status;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {

	/**
	 * @param email
	 * @param delete
	 * @return
	 */
	User findByEmailAndStatusNot(String email, Status Status);

	/**
	 * @param id
	 * @return
	 */
	User findUserById(Long id);


	/**
	 * @param username
	 * @param delete
	 * @return
	 */
	User findByUsernameAndStatusNot(String username, Status delete);

	/**
	 * @param delete
	 * @return
	 */
	List<User> findAllUserByStatusNot(Status delete);

	/**
	 * @param userId
	 * @param delete
	 * @return
	 */
	User findUserByIdAndStatusNot(Long userId, Status delete);

}
