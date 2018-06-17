package com.ozads.futsalnepal.services;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ozads.futsalnepal.dto.UserDto;
import com.ozads.futsalnepal.exceptions.AlreadyExistException;
import com.ozads.futsalnepal.exceptions.NotFoundException;
import com.ozads.futsalnepal.exceptions.RequiredException;
import com.ozads.futsalnepal.exceptions.ValidationException;
import com.ozads.futsalnepal.model.Login;
import com.ozads.futsalnepal.model.User;
import com.ozads.futsalnepal.repository.LoginRepository;
import com.ozads.futsalnepal.repository.UserRepository;
import com.ozads.futsalnepal.request.PasswordEditRequest;
import com.ozads.futsalnepal.request.UserCreationRequest;
import com.ozads.futsalnepal.request.UserEditRequest;
import com.ozads.futsalnepal.response.UserResponseDto;
import com.ozads.futsalnepal.util.LoginStatus;
import com.ozads.futsalnepal.util.LoginType;
import com.ozads.futsalnepal.util.Md5Hashing;
import com.ozads.futsalnepal.util.Status;
import com.ozads.futsalnepal.util.UserRole;


@Service
public class UserService {

	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
	@Autowired
	UserRepository userRepository;

	@Autowired
	LoginRepository loginRepository;

	@Autowired
	LoginService loginService;

	/**
	 * @param userDto
	 */
	@Transactional
	public User saveUser(Long userId, UserCreationRequest userDto) {
		LOG.debug("User Creation...");
		Login l = loginRepository.findLoginByUsername(userDto.getUsername());
		if (l != null ) {
			throw new AlreadyExistException("Username Already Exits");
		}

		User u = userRepository.findByEmailAndStatusNot(userDto.getEmail(), Status.DELETED);

		if (u != null) {
			throw new AlreadyExistException("Email already Exits !!");
		}
		User user = new User();
		user.setFullName(userDto.getFullName());
		user.setEmail(userDto.getEmail());
		user.setGender(userDto.getGender());
		user.setPhoneNo(userDto.getPhoneNo());
		user.setCreatedDate(new Date());
		user.setCreatedBy(userId);
		user.setUsername(userDto.getUsername());
		user.setUserRole(UserRole.ADMIN);
		user.setStatus(Status.ACTIVE);

		LOG.debug("User Adding");
		User savedUser = userRepository.save(user);
		LOG.debug("User Added");

		if (user != null) {
			Login login = new Login();

			
			try {
				login.setPassword(Md5Hashing.getPw(userDto.getPassword()));
				login.setLoginStatus(LoginStatus.LOGOUT);
				login.setCreatedDate(new Date());
				login.setUsername(userDto.getUsername());
				login.setEmail(userDto.getEmail());
				login.setUser(savedUser);
				login.setLoginType(LoginType.ADMIN);
				login.setStatus(Status.ACTIVE);
				loginService.saveLogin(login);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			

		}

		return user;
	}

	/**
	 * @param id
	 */
	@Transactional
	public void deleteUser(Long id) {
		LOG.debug("Deleting user");
		User user = userRepository.findUserById(id);
		if (user == null) {
			throw new NotFoundException("User not found");

		}
		Login l=loginRepository.findLoginByEmailAndStatusNot(user.getEmail(),Status.DELETED);
		if(l==null) {
			throw new NotFoundException("Customer Not found !!");
		}
		l.setStatus(Status.DELETED);
		user.setStatus(Status.DELETED);
		LOG.debug("User Deleted");
		userRepository.save(user);
	}

	/**
	 * @param userEditRequest
	 */
	@Transactional
	public User editUser(Long userId,UserEditRequest userEditRequest) {

		LOG.debug("Request For user Edit");
		if (userEditRequest.getId() == null) {
			throw new RequiredException("User id is needed");
		}
		User user = userRepository.findUserById(userId);
		if(user==null) {
			throw new NotFoundException("User not found");
		}
		
		
		if (userEditRequest.getEmail() != null) {
			emailDuplication(userEditRequest.getEmail(), user);
		}

		if (userEditRequest.getFullName() != null) {
			user.setFullName(userEditRequest.getFullName());
		}

		if (userEditRequest.getEmail() != null) {
			user.setEmail(userEditRequest.getEmail());
		}

		if (userEditRequest.getGender() != null) {
			user.setGender(userEditRequest.getGender());
		}

		

		if (userEditRequest.getPhoneNo() != null) {
			user.setPhoneNo(userEditRequest.getPhoneNo());
		}


		user.setModifiedDate(new Date());
		User userSave = userRepository.save(user);
		LOG.debug("User Edited");
		return userSave;

	}

	/**
	 * @param userId
	 * @param passwordEditRequest
	 */

	private void emailDuplication(String email, User user) {

		User u = userRepository.findByEmailAndStatusNot(email, Status.DELETED);
		if (u != null && user.getId().equals(u.getId())) {

			throw new AlreadyExistException("Email Already Exit");

		}
	}

	@Transactional
	public void changePassword(Long userId, PasswordEditRequest passwordEditRequest) {

		LOG.debug("Request Acccepted to change password");
		if (!passwordEditRequest.getNewPassword().equals(passwordEditRequest.getConfirmNewPassword())) {
			throw new ValidationException("New password and confrom password doesnt match");

		}

		Login login = loginRepository.findByUsername(passwordEditRequest.getUsername());
		if (login == null) {
			throw new ValidationException("Username not found");

		}
		if (!userId.equals(login.getUser().getId())) {
			throw new ValidationException("You are not authorized");
		}

		try {
			if (!Md5Hashing.getPw(passwordEditRequest.getOldPassword()).equals(login.getPassword())) {
				throw new ValidationException("Old Password not match");
			}
			login.setPassword(Md5Hashing.getPw(passwordEditRequest.getNewPassword()));
			loginRepository.save(login);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		LOG.debug("Password Changed");
	}

	
	/**
	 * @return
	 */
	public List<UserDto> listAllUsers() {
		LOG.debug("Request Accepted to list all Users");
		List<User> users=userRepository.findAllUserByStatusNot(Status.DELETED);
		List<UserDto> user=new ArrayList<>();
		if(users==null) {
			throw new NotFoundException("User Not found!!");
		}
		users.parallelStream().forEach(u->{
			UserDto userDto=new UserDto();
			userDto.setId(u.getId());
			userDto.setFullName(u.getFullName());
			userDto.setEmail(u.getEmail());
			userDto.setGender(u.getGender());
			userDto.setPhoneNo(u.getPhoneNo());
			userDto.setUsername(u.getUsername());
			user.add(userDto);
		});
		LOG.debug("All User Are obtain");
		
		return user;
	}

	/**
	 * @param userId
	 * @return
	 */
	public UserResponseDto getUser(Long userId) {
		LOG.debug("Request Accepted for List A user");
		User users=userRepository.findUserByIdAndStatusNot(userId,Status.DELETED);
		if(users==null) {
			throw new NotFoundException("User not found ");
		}
		UserResponseDto userResponceDto=new UserResponseDto();
		userResponceDto.setId(users.getId());
		userResponceDto.setFullName(users.getFullName());
		userResponceDto.setEmail(users.getEmail());
		userResponceDto.setGender(users.getGender());
		userResponceDto.setPhoneNo(users.getPhoneNo());
		userResponceDto.setUsername(users.getUsername());
		LOG.debug("User obtain");
		return userResponceDto;
	}

	

}
