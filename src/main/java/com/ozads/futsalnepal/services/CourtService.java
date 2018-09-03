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
import com.ozads.futsalnepal.dto.CourtAddressDto;
import com.ozads.futsalnepal.dto.CourtDto;
import com.ozads.futsalnepal.exceptions.AlreadyExistException;
import com.ozads.futsalnepal.exceptions.NotFoundException;
import com.ozads.futsalnepal.exceptions.RequiredException;
import com.ozads.futsalnepal.model.Address;
import com.ozads.futsalnepal.model.Customer;
import com.ozads.futsalnepal.model.Login;
import com.ozads.futsalnepal.model.Court;
import com.ozads.futsalnepal.model.CourtAddress;
import com.ozads.futsalnepal.model.User;
import com.ozads.futsalnepal.repository.CustomerRepository;
import com.ozads.futsalnepal.repository.LoginRepository;
import com.ozads.futsalnepal.repository.CourtAddressRepository;
import com.ozads.futsalnepal.repository.CourtRepository;
import com.ozads.futsalnepal.repository.UserRepository;
import com.ozads.futsalnepal.request.CourtAddressCreatationRequest;
import com.ozads.futsalnepal.request.CourtAddressEditRequest;
import com.ozads.futsalnepal.request.CourtCreatationRequest;
import com.ozads.futsalnepal.request.CourtEditRequest;
import com.ozads.futsalnepal.response.CourtAddressResponse;
import com.ozads.futsalnepal.response.CourtByAddressDto;
import com.ozads.futsalnepal.response.CourtResponseDto;
import com.ozads.futsalnepal.util.EmailUtility;
import com.ozads.futsalnepal.util.LoginStatus;
import com.ozads.futsalnepal.util.LoginType;
import com.ozads.futsalnepal.util.Md5Hashing;
import com.ozads.futsalnepal.util.RandomPassword;
import com.ozads.futsalnepal.util.Status;


@Service
public class CourtService {

	private static final Logger LOG = LoggerFactory.getLogger(CourtService.class);

	@Autowired
	LoginRepository loginRepository;

	@Autowired
	LoginService loginService;

	@Autowired
	CourtRepository courtRepository;

	@Autowired
	CourtAddressRepository courtAddressRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	CustomerRepository customerRepository;

	
	@Transactional
	public Court saveCourt(Long userId, CourtCreatationRequest courtCreatationRequest) {
		LOG.debug("Message For Court Creatation");
		
		User u=userRepository.findUserById(userId);
		if(u==null) {
			throw new NotFoundException("User is not found");
		}

		

		Court s = courtRepository.findByPhoneNoAndStatusNot(courtCreatationRequest.getPhoneNo(), Status.DELETED);
		if (s != null) {
			throw new AlreadyExistException("Phone no Already Exist");
		}

		Court court = new Court();
		court.setCourtName(courtCreatationRequest.getCourtName());
		
		court.setPhoneNo(courtCreatationRequest.getPhoneNo());
		court.setStatus(Status.ACTIVE);
		court.setCreatedDate(new Date());
		court.setCreatedBy(userId);
		
		court.setEmail(courtCreatationRequest.getEmail());
		LOG.debug("Adding Court....");
		Court ss = courtRepository.save(court);
		LOG.debug("Court Added");
		if (ss != null) {
			List<CourtAddressCreatationRequest> address = courtCreatationRequest.getCourtAddress();
			if (address != null) {
				LOG.debug("Address Adding");
				for (CourtAddressCreatationRequest add : address) {
					CourtAddress addresses = new CourtAddress();
					addresses.setLatitude(add.getLatitude());
					addresses.setLongitude(add.getLongitude());
					
					addresses.setCourt(ss);

					courtAddressRepository.save(addresses);
					LOG.debug("Address Added");
				}
			}

			Login login = new Login();
			try {
				login.setPassword(Md5Hashing.getPw(RandomPassword.newPassword()));
				login.setEmail(courtCreatationRequest.getEmail());
				login.setLoginStatus(LoginStatus.LOGOUT);
				
				login.setCourt(ss);
				login.setCreatedDate(new Date());
				login.setStatus(Status.ACTIVE);
				login.setLoginType(LoginType.COURT);
				Login ll = loginRepository.save(login);
				if (ll != null) {
					EmailUtility.sendNewPassword(courtCreatationRequest.getEmail(), ll.getPassword());
				}

				LOG.debug("Login Added");
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return court;
	}

	
	@Transactional
	public void deleteCourt(Long userId, Long id) {
		LOG.debug("Deleteing ..");

		Court court = courtRepository.findCourtByIdAndStatusNot(id, Status.DELETED);

		if (court == null) {
			throw new NotFoundException("Court Not found");

		}

		User user = userRepository.findUserByIdAndStatusNot(userId, Status.DELETED);

		if (user == null) {
			throw new NotFoundException("User Not found");

		}

		Login l = loginRepository.findLoginByEmailAndStatusNot(court.getEmail(), Status.DELETED);
		if (l == null) {
			throw new NotFoundException("Court Not found !!");
		}
		l.setStatus(Status.DELETED);
		court.setStatus(Status.DELETED);
		LOG.debug("Court Deleted..");
		courtRepository.save(court);

	}

	/**
	 * @param courtEditRequest
	 */
	@Transactional
	public Court editCourt(CourtEditRequest courtEditRequest) {

		LOG.debug("Request Accepted to Edit court..");
		if (courtEditRequest.getId() == null) {
			throw new RequiredException("User id is needed");
		}

		Court court = courtRepository.findCourtByIdAndStatusNot(courtEditRequest.getId(), Status.DELETED);
		if (court == null) {
			throw new NotFoundException("User not foud");
		}

		if (courtEditRequest.getEmail() != null) {
			emailDuplication(courtEditRequest.getEmail(), court);
		}

		if (courtEditRequest.getCourtName() != null) {
			court.setCourtName(courtEditRequest.getCourtName());
		}

		if (courtEditRequest.getEmail() != null) {
			court.setEmail(courtEditRequest.getEmail());
		}

		

		

		if (courtEditRequest.getPhoneNo() != null) {
			court.setPhoneNo(courtEditRequest.getPhoneNo());
		}

		if (courtEditRequest.getCourtAddressEdit() != null) {
			List<CourtAddressEditRequest> addressEditRequests = courtEditRequest.getCourtAddressEdit();
			for (CourtAddressEditRequest address : addressEditRequests) {

				CourtAddress add = null;
				if (address.getId() == null) {
					add = new CourtAddress();
				}

				else {
					add = courtAddressRepository.findCourtAddressById(address.getId());
				}

				if (null != address.getLatitude()) {
					add.setLatitude(address.getLatitude());
				}
				
				if (null != address.getLongitude()) {
					add.setLongitude(address.getLongitude());
				}
				

				
				

				add.setCourt(court);
				courtAddressRepository.save(add);
				LOG.debug("Added address.");

			}
		}

		return court;

	}

	private void emailDuplication(String email, Court court) {
		LOG.debug("Check for Email duplication");

		Court s = courtRepository.findByEmailAndStatusNot(email, Status.DELETED);
		if (s != null && court.getId().equals(s.getId())) {

			throw new AlreadyExistException("Email Already Exit");

		}
	}

	
	public List<CourtDto> listAllCourts() {
		LOG.debug("Request Accepted to list all courts");
		List<Court> court = courtRepository.findAllCourtByStatusNot(Status.DELETED);
		List<CourtDto> courtDto = new ArrayList<>();

		court.stream().forEach(u -> {
			CourtDto dto = new CourtDto();
			dto.setCourtName(u.getCourtName());
			dto.setEmail(u.getEmail());
			dto.setPhoneNo(u.getPhoneNo());
			
			
			List<CourtAddressDto> courtAddress = new ArrayList<>();
			List<CourtAddress> addresses = u.getCourtAddress();
			if (addresses != null) {
				addresses.stream().forEach(a -> {
					CourtAddressDto courtAddressDto = new CourtAddressDto();
					courtAddressDto.setId(a.getId());
					courtAddressDto.setLatitude(a.getLatitude());
					courtAddressDto.setLongitude(a.getLongitude());
					
					courtAddress.add(courtAddressDto);
				});
			}
			dto.setAddress(courtAddress);
			courtDto.add(dto);
		});
		LOG.debug("All Court List is obtain");

		return courtDto;
	}

	
	public CourtResponseDto getCourt(Long courtId) {
		LOG.debug("Request to get Court");
		Court court = courtRepository.findByIdAndStatusNot(courtId, Status.DELETED);
		if (court == null) {
			throw new NotFoundException("Court not found");
		}
		CourtResponseDto courtResponseDto = new CourtResponseDto();
		courtResponseDto.setId(court.getId());
		courtResponseDto.setCourtName(court.getCourtName());
		courtResponseDto.setEmail(court.getEmail());
		
		
		courtResponseDto.setPhoneNo(court.getPhoneNo());
		List<CourtAddressResponse> addressResponses = new ArrayList<>();
		List<CourtAddress> addresses = court.getCourtAddress();
		if (addresses != null) {
			addresses.stream().forEach(u -> {
				CourtAddressResponse dd = new CourtAddressResponse();
				dd.setId(u.getId());
				dd.setLatitude(u.getLatitude());
				dd.setLongitude(u.getLongitude());
				
				addressResponses.add(dd);
			});
		}
		courtResponseDto.setAddress(addressResponses);
		LOG.debug("Court Obatin");
		return courtResponseDto;
	}

	
	public List<CourtByAddressDto> getCourtAddress(Long customerId) {

		Customer customer=customerRepository.findByIdAndStatusNot(customerId, Status.DELETED);
		if(customer==null) {
			throw new NotFoundException("Customer Not found !!");
		}
		List<CourtByAddressDto> courtDto=new ArrayList<>();
		List<Address> add = customer.getAddress();
		if (add != null) {
			add.stream().forEach(u -> {
				String latitude=u.getLatitude();
				String longitude=u.getLongitude();
				
				System.out.println(latitude+""+longitude);
				
				List<CourtAddress> address=courtAddressRepository.
						findAddressByLatitudeAndLongitude(latitude, longitude);

						address.stream().forEach(a->{
							CourtByAddressDto dto=new CourtByAddressDto();
							dto.setCourtName(a.getCourt().getCourtName());
							dto.setPhoneNo(a.getCourt().getPhoneNo());
							dto.setEmail(a.getCourt().getEmail());
						
							courtDto.add(dto);
						});
			});
		}
		
		
		
		return courtDto;

	}
}
