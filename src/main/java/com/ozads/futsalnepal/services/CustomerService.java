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

import com.ozads.futsalnepal.dto.CustomerDto;
import com.ozads.futsalnepal.exceptions.AlreadyExistException;
import com.ozads.futsalnepal.exceptions.ExpireException;
import com.ozads.futsalnepal.exceptions.NotFoundException;
import com.ozads.futsalnepal.exceptions.NotValidExpection;
import com.ozads.futsalnepal.exceptions.RequiredException;
import com.ozads.futsalnepal.exceptions.ValidationException;
import com.ozads.futsalnepal.model.Address;
import com.ozads.futsalnepal.model.Customer;
import com.ozads.futsalnepal.model.Login;
import com.ozads.futsalnepal.model.Verification;
import com.ozads.futsalnepal.repository.AddressRepository;
import com.ozads.futsalnepal.repository.CustomerRepository;
import com.ozads.futsalnepal.repository.LoginRepository;
import com.ozads.futsalnepal.repository.VerificationRepository;
import com.ozads.futsalnepal.request.AddressEditRequest;
import com.ozads.futsalnepal.request.CustomerAddressCreationRequest;
import com.ozads.futsalnepal.request.CustomerCreationRequest;
import com.ozads.futsalnepal.request.CustomerEditRequest;
import com.ozads.futsalnepal.request.PasswordEditRequest;
import com.ozads.futsalnepal.response.AddressResponseDto;
import com.ozads.futsalnepal.response.CustomerResponseDto;
import com.ozads.futsalnepal.util.DateUtil;
import com.ozads.futsalnepal.util.EmailUtility;
import com.ozads.futsalnepal.util.LoginStatus;
import com.ozads.futsalnepal.util.LoginType;
import com.ozads.futsalnepal.util.Md5Hashing;
import com.ozads.futsalnepal.util.Status;
import com.ozads.futsalnepal.util.TokenGenerator;
import com.ozads.futsalnepal.util.VerificationStatus;
import com.ozads.futsalnepal.dto.AddressDto;


@Service
public class CustomerService {

	private static final Logger LOG = LoggerFactory.getLogger(CustomerService.class);

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	LoginRepository loginRepository;

	@Autowired
	LoginService loginService;

	@Autowired
	AddressRepository addressRepository;

	@Autowired
	VerificationRepository verificationRepository;

	@Autowired
	VerificationService verificationService;

	/**
	 * @param customerCreationRequest
	 */
	@Transactional
	public Customer saveCustomer(CustomerCreationRequest customerCreationRequest) {
		LOG.debug("Customer Creation started..");
		Login l = loginRepository.findByUsernameAndStatusNot(customerCreationRequest.getUsername(), Status.DELETED);
		if (l != null) {
			throw new AlreadyExistException("Username Already Exits");
		}

		Customer c = customerRepository.findByEmailAndStatusNot(customerCreationRequest.getEmail(), Status.DELETED);
		if (c != null) {
			throw new AlreadyExistException("Email already Exits !!");
		}
		System.out.println(customerCreationRequest.getEmail().toString());
		Customer customer = new Customer();
		customer.setFirstName(customerCreationRequest.getFirstName());
		customer.setLastName(customerCreationRequest.getLastName());
		customer.setEmail(customerCreationRequest.getEmail());
		customer.setGender(customerCreationRequest.getGender());
		customer.setPhoneNo(customerCreationRequest.getPhoneNo());
		customer.setStatus(Status.ACTIVE);
		customer.setUsername(customerCreationRequest.getUsername());
		customer.setCreatedDate(new Date());
		
		TokenGenerator tg = new TokenGenerator();
		String token = tg.generateToken(customerCreationRequest.getUsername());

		Verification verification = verificationRepository
				.findVerificationByEmailAndStatusNot(customerCreationRequest.getEmail(), VerificationStatus.EXPIRE);

		if (verification == null) {
			Verification verifiy = new Verification();
			verifiy.setEmail(customerCreationRequest.getEmail());
			verifiy.setCreatedDate(new Date());
			verifiy.setExpireDate(DateUtil.getTokenExpireDate(new Date()));
			verifiy.setToken(token);
			verifiy.setStatus(VerificationStatus.ACTIVE);
			EmailUtility.sendVerification(customerCreationRequest.getEmail(), token);
			verificationService.saveVerification(verifiy);
		}
		
	

		LOG.debug("Added.");
		LOG.debug("Customer Adding..");
		Customer savedCustomer = customerRepository.save(customer);
		LOG.debug("Customer Added");
		if (savedCustomer != null) {
			Login login = new Login();
			login.setLoginStatus(LoginStatus.LOGOUT);
			try {
				login.setPassword(Md5Hashing.getPw(customerCreationRequest.getPassword()));
				login.setCreatedDate(new Date());
				login.setEmail(customerCreationRequest.getEmail());
				login.setUsername(customerCreationRequest.getUsername());
				login.setCustomer(savedCustomer);
				login.setLoginType(LoginType.CUSTOMER);
				login.setStatus(Status.BLOCKED);
				loginService.saveLogin(login);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}

			
			List<CustomerAddressCreationRequest> address = customerCreationRequest.getAddress();
			if (address != null) {
				for (CustomerAddressCreationRequest add : address) {
					Address addresses = new Address();
					addresses.setDistrict(add.getDistrict());
					addresses.setArea(add.getArea());
					addresses.setStreet(add.getStreet());
					
					addresses.setCustomer(savedCustomer);

					addressRepository.save(addresses);
					LOG.debug("Address Added");
				}
			}

		}

		return customer;
	}

	/**
	 * @param id
	 */
	public void deleteCustomer(Long id) {
		LOG.debug("Deleting Customer..");
		Customer c = customerRepository.findCustomerById(id);
		if (c == null) {
			throw new NotFoundException("Customer Not found !!");
		}

		Login l = loginRepository.findLoginByEmailAndStatusNot(c.getEmail(), Status.DELETED);
		if (l == null) {
			throw new NotFoundException("Customer Not found !!");
		}
		l.setStatus(Status.DELETED);
		c.setStatus(Status.DELETED);
		LOG.debug("Customer Deleted");
		customerRepository.save(c);
		loginRepository.save(l);
	}

	/**
	 * @param editRequest
	 */
	@Transactional
	public Customer editCustomer(CustomerEditRequest editRequest) {
		LOG.debug("Request for Customer edit");
		if (editRequest.getId() == null) {
			throw new RequiredException("User id is needed");

		}

		Customer customer = customerRepository.findCustomerById(editRequest.getId());
		if (customer == null) {
			throw new NotFoundException("User not foud");
		}
		
		if(!customer.getId().equals(editRequest.getId())) {
			throw new NotValidExpection("you are not authorized");
		}

		if (editRequest.getEmail() != null) {
			emailDuplication(editRequest.getEmail(), customer);
		}



		if (editRequest.getGender() != null) {
			customer.setGender(editRequest.getGender());
		}
		if (editRequest.getEmail() != null) {
			customer.setEmail(editRequest.getEmail());
		}

		if (editRequest.getPhoneNo() != null) {
			customer.setPhoneNo(editRequest.getPhoneNo());
		}

//		if (editRequest.getUsername() != null) {
//			customer.setUsername(editRequest.getUsername());
//		}

		if(editRequest.getFirstName()!=null) {
			customer.setFirstName(editRequest.getFirstName());
		}
		
		if(editRequest.getLastName()!=null) {
			customer.setLastName(editRequest.getLastName());
		}

		if (editRequest.getAddress() != null) {
			List<AddressEditRequest> addressEditRequests = editRequest.getAddress();
			for (AddressEditRequest address : addressEditRequests) {

				Address add = null;
				if (address.getId() == null) {
					add = new Address();
				}

				else {
					add = addressRepository.findAddressById(address.getId());
				}

				if (null != address.getDistrict()) {
					add.setDistrict(address.getDistrict());
				}
				if (null != address.getArea()) {
					add.setArea(add.getArea());
				}
				if (null != address.getStreet()) {
					add.setStreet(address.getStreet());
				}
				

				add.setCustomer(customer);
				addressRepository.save(add);
				LOG.debug("Added address.");

			}
		}

		customer.setModifiedDate(new Date());
		return customer;
	}

	private void emailDuplication(String email, Customer customer) {
		LOG.debug("Check for Email dublication");

		Customer c = customerRepository.findByEmailAndStatusNot(email, Status.DELETED);
		if (c != null && customer.getId().equals(c.getId())) {

			throw new AlreadyExistException("Email Already Exit");

		}
	}
//
//	private void usernameDuplication(String username, Customer customer) {
//		LOG.debug("Check for Username dublication");
//		Customer c = customerRepository.findByUsernameAndStatusNot(username, Status.DELETE);
//		if (c != null && customer.getId().equals(c.getId())) {
//
//			throw new AlreadyExitException("Username Already Exit");
//
//		}

//	}

	/**
	 * @param customerId
	 * @param passwordEditRequest
	 */
	@Transactional
	public void changePassword(Long customerId, PasswordEditRequest passwordEditRequest) {
		LOG.debug("Request Acccepted to change password");
		if (!passwordEditRequest.getNewPassword().equals(passwordEditRequest.getConfirmNewPassword())) {
			throw new ValidationException("New password and confrom password doesnt match");

		}

		Login login = loginRepository.findByUsername(passwordEditRequest.getUsername());

		try {
			if (login == null) {
				throw new ValidationException("Username not found");

			}
			if (!customerId.equals(login.getCustomer().getId())) {
				throw new ValidationException("You are not authorized");
			}

			if (!Md5Hashing.getPw((passwordEditRequest.getOldPassword()))
					.equals(login.getPassword())) {
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
	 * @param customerId
	 * @return
	 */
	public CustomerResponseDto getCustomer(Long customerId) {
		LOG.debug("Request to get customer");
		Customer customer = customerRepository.findByIdAndStatusNot(customerId, Status.DELETED);
		if (customer == null) {
			throw new NotFoundException("Customer Not found");
		}
		CustomerResponseDto customerResponseDto = new CustomerResponseDto();
	    customerResponseDto.setFirstName(customer.getFirstName());
	    customerResponseDto.setLastName(customer.getLastName());
		customerResponseDto.setGender(customer.getGender());
		customerResponseDto.setEmail(customer.getEmail());
		customerResponseDto.setUsername(customer.getUsername());
		customerResponseDto.setPhoneNo(customer.getPhoneNo());;

		List<AddressResponseDto> adddresss = new ArrayList<>();
		List<Address> add = customer.getAddress();
		if (add != null) {
			add.stream().forEach(u -> {
				AddressResponseDto dd = new AddressResponseDto();
				dd.setId(u.getId());
				dd.setDistrict(u.getDistrict());
				dd.setArea(u.getArea());;
				dd.setStreet(u.getStreet());
				
				adddresss.add(dd);
			});
		}
		customerResponseDto.setAddress(adddresss);
		LOG.debug("Customer Obtain");
		return customerResponseDto;
	}

	/**
	 * @return
	 */
	public List<CustomerDto> listAllCustomer() {
		LOG.debug("Request to get All customer");
		List<Customer> customer = customerRepository.findAllCustomerByStatusNot(Status.DELETED);
		List<CustomerDto> customers = new ArrayList<>();
		if (customer == null) {
			throw new NotFoundException("Customer not found");
		}
		customer.stream().forEach(u -> {
			CustomerDto customerDto = new CustomerDto();
			customerDto.setId(u.getId());
			customerDto.setFirstName(u.getFirstName());
			customerDto.setLastName(u.getLastName());
			customerDto.setEmail(u.getEmail());
			customerDto.setGender(u.getGender());
			customerDto.setUsername(u.getUsername());
			customerDto.setPhoneNo(u.getPhoneNo());
			List<AddressDto> adddresss = new ArrayList<>();
			List<Address> add = u.getAddress();
			if (add != null) {
				add.stream().forEach(a -> {
					AddressDto dd = new AddressDto();
					dd.setId(a.getId());
					dd.setDistrict(a.getDistrict());
					dd.setArea(a.getArea());;
					dd.setStreet(a.getStreet());
					
					adddresss.add(dd);
				});
			}
			customerDto.setAddress(adddresss);
			customers.add(customerDto);

		});
		LOG.debug("All customer Obtain");
		return customers;
	}

	/**
	 * @param token
	 */
	public void getVerify(String token) {

		Verification v = verificationRepository.findVerificationByTokenAndStatusNot(token, VerificationStatus.EXPIRE);
		if (v == null) {
			throw new ExpireException("The session in invallied");
		}

		if (DateUtil.compareDate(v.getCreatedDate(), v.getExpireDate()) == false) {
			v.setStatus(VerificationStatus.EXPIRE);
			verificationService.saveVerification(v);
			throw new ExpireException("Sorry !! Token is expired");
		}

		Login l = loginRepository.findLoginByEmailAndStatus(v.getEmail(), Status.BLOCKED);
		if (l != null) {
			l.setStatus(Status.ACTIVE);
			v.setStatus(VerificationStatus.EXPIRE);
			verificationService.saveVerification(v);
			loginRepository.save(l);
		}
	}

}
