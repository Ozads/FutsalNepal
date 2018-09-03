package com.ozads.futsalnepal.services;

import java.security.NoSuchAlgorithmException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;


import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ozads.futsalnepal.dto.LoginResponseDto;
import com.ozads.futsalnepal.exceptions.ExpireException;
import com.ozads.futsalnepal.exceptions.LoginFailException;
import com.ozads.futsalnepal.exceptions.LogoutFailException;
import com.ozads.futsalnepal.exceptions.NotFoundException;
import com.ozads.futsalnepal.exceptions.VerificationException;
import com.ozads.futsalnepal.model.Login;
import com.ozads.futsalnepal.model.Verification;
import com.ozads.futsalnepal.repository.LoginRepository;
import com.ozads.futsalnepal.repository.VerificationRepository;
import com.ozads.futsalnepal.request.ForgetPasswordRequest;
import com.ozads.futsalnepal.util.DateUtil;
import com.ozads.futsalnepal.util.EmailUtility;
import com.ozads.futsalnepal.util.LoginStatus;
import com.ozads.futsalnepal.util.LoginType;
import com.ozads.futsalnepal.util.Md5Hashing;
import com.ozads.futsalnepal.util.Status;
import com.ozads.futsalnepal.util.TokenGenerator;
import com.ozads.futsalnepal.util.VerificationStatus;

@Service
public class LoginService {
	private static final Logger LOG = LoggerFactory.getLogger(LoginService.class);
	@Autowired
	LoginRepository loginRepository;

	@Autowired
	VerificationRepository verificationRepository;

	@Autowired
	VerificationService verificationService;
	
	@Value("${futsalnepal.token.expire.enable}")
	private String tokenExpireEnable;

	@Value("${futsalnepal.token.expire.after}")
	private int tokenExpireAfter;

	@Value("${futsalnepal.login.password.length}")
	private int passwordLength;

	 @Autowired
	  HttpServletRequest request;

   

    private  String getClientIp() {

        String remoteAddr = "";

        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }
        return remoteAddr;
    }

	
	@Transactional
	public LoginResponseDto logInUser(String email, String password, Status active, String deviceId) {
		LOG.debug("Request for Login");
		Login login = loginRepository.findLoginByEmailAndStatusNot(email, Status.DELETED);
		if (login == null) {

			throw new LoginFailException("Sorry,Username not found !!");
		}

		Login l = loginRepository.findLoginByEmailAndStatus(email, Status.BLOCKED);
		if (l != null) {
			throw new VerificationException("Sorry Your Account is not verified Check Your Email");
		}

		try {
			if (Md5Hashing.getPw(password).equals(login.getPassword())) {
				login.setLastLogin(new Date());
				login.setLoginStatus(LoginStatus.LOGGEDIN);
				
				String ip=getClientIp();
				System.out.println("my ip="+ip);
				Client client = ClientBuilder.newClient();
				  Response response = client.target("https://api.ipdata.co/")
				    .request(MediaType.TEXT_PLAIN_TYPE)
				    .header("Accept", "application/json")
				    .get();
				  
				  System.out.println("body:" + response.readEntity(String.class));
				
				
				login.setDeviceId(deviceId);
				login.setToken(TokenGenerator.getToken());
				if (tokenExpireAfter > 0) {
					login.setTokenExpirationDateTime(
							DateUtil.currentDateTimePlusMinutes(tokenExpireAfter));
				}
				LoginResponseDto responce = getLoginResponse(login);
				LOG.debug("Login Accepted");
				return responce;
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		throw new LoginFailException("Username and Password missmatch");
	}

	/**
	 * @param login
	 * @return
	 */
	private LoginResponseDto getLoginResponse(Login login) {
		LoginResponseDto loginResponceDto=new LoginResponseDto();
		if(login.getLoginType().equals(LoginType.CUSTOMER)) {
			loginResponceDto=new LoginResponseDto.Builder().id(login.getCustomer().getId())
					.token(login.getToken()).build();
		}
		return loginResponceDto;
	}

	/**
	 * @param userId
	 * @return
	 */
	public Login logout(Long userId) {
		LOG.debug("request for logout");
		if (userId != null) {
			Login user = loginRepository.findLoginById(userId);
			if (user == null) {
				throw new LogoutFailException("User id mismatch");
			}
			user.setLoginStatus(LoginStatus.LOGOUT);
			user.setToken("");
			user.setTokenExpirationDateTime(new Date());
			loginRepository.save(user);
			LOG.debug("logout");
			return user;
		}

		return null;
	}

	/**
	 * @param login
	 */
	@Transactional
	public void saveLogin(Login login) {

		loginRepository.save(login);
	}

	/**
	 * @param email
	 */
	@Transactional
	public void resetPassword(String email) {
		LOG.debug("Request to reset Password");
		Login login = loginRepository.findLoginByEmailAndStatusNot(email, Status.DELETED);
		if (login == null) {
			throw new NotFoundException("Email Not found!!");
		}

		TokenGenerator tg = new TokenGenerator();
		String token = tg.generateToken(login.getEmail());

		Verification verification = verificationRepository.findVerificationByEmailAndStatusNot(email,
				VerificationStatus.EXPIRE);

		if (verification != null) {
			verification.setCreatedDate(new Date());
			verification.setExpireDate(DateUtil.getTokenExpireDate(new Date()));
			verification.setToken(token);
			verification.setStatus(VerificationStatus.ACTIVE);
			verificationService.saveVerification(verification);
		} else {
			Verification verifiy = new Verification();
			verifiy.setEmail(login.getEmail());
			verifiy.setCreatedDate(new Date());
			verifiy.setExpireDate(DateUtil.getTokenExpireDate(new Date()));
			verifiy.setToken(token);
			verifiy.setStatus(VerificationStatus.ACTIVE);
			verificationService.saveVerification(verifiy);
		}
		EmailUtility.sendResetLink(login.getEmail(), token);
		LOG.debug("Request to reset Password accepted");
	}

	/**
	 * @param forgetPasswordRequest
	 */
	@Transactional
	public void resetForgetPassword(String token, ForgetPasswordRequest forgetPasswordRequest) {

		LOG.debug("Acceped to reset password");

		Verification v = verificationRepository.findVerificationByTokenAndStatusNot(token, VerificationStatus.EXPIRE);
		if (v == null) {
			throw new ExpireException("The session in invallied");
		}

		if (DateUtil.compareDate(v.getCreatedDate(), v.getExpireDate()) == false) {
			v.setStatus(VerificationStatus.EXPIRE);
			verificationService.saveVerification(v);
			throw new ExpireException("Sorry !! Token is expired");
		}

		Login login = loginRepository.findLoginByEmailAndStatusNot(v.getEmail(), Status.DELETED);

		if (login == null) {
			throw new NotFoundException("Email Address Not found !!");
		}

		if (!forgetPasswordRequest.getNewPassword().equals(forgetPasswordRequest.getConfirmPassword())) {
			throw new NotFoundException("Password Did not match");
		}
		try {
			login.setPassword(Md5Hashing.getPw(forgetPasswordRequest.getConfirmPassword()));
			Login savedlogin = loginRepository.save(login);
			if (savedlogin != null) {
				v.setStatus(VerificationStatus.EXPIRE);
				verificationService.saveVerification(v);
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		LOG.debug("Password is reset");
	}

	/**
	 * @param token
	 */
	public void chekToken(String token) {
		Login login=loginRepository.findByToken(token);
		if(login==null) {
			throw new NotFoundException("Token is invallied");
		}
		if (!DateUtil.isCurrentTimeBeforeThanGivenTime(login.getTokenExpirationDateTime()))
			throw new ExpireException("token Expired");

	
}

	/**
	 * @param loginId
	 * @param token
	 * @return
	 */
	public boolean isValidToken(Long loginId, String token) {
		
		if(loginId==null || token==null) {
			return false;
		}
		Login login = loginRepository.findByIdAndToken(loginId, token);
		if (null == login) {
			return false;
		}
		LOG.debug("Login found.");
		if (null != tokenExpireEnable) {
			if (tokenExpireEnable.equalsIgnoreCase("ENABLE")) {
				if (!DateUtil.isCurrentTimeBeforeThanGivenTime(
						login.getTokenExpirationDateTime()))
					return false;
			}
		}
		return true;
	}
	}
	
//	public boolean isValidToken(Long userId, String token) {
//		if (userId.equals(0L)) {
//			throw new ServiceException("userId or loginId required in header parameter.");
//		}
//		if (null == userId || null == token) {
//			return false;
//		}
//		// Login user = userRepository.getOne(userId);
//		LOG.debug("User id {} and token {}", userId, token);
//		// Login login = loginRepository.findByIdAndToken(user.getLoginId(), token);
//		LoginToken login = loginTokenRepository.findByLoginIdAndToken(userId, token);
//		if (null == login) {
//			return false;
//		}
//		LOG.debug("Login found.");
//		if (tokenExpireEnable.equalsIgnoreCase(Constant.ENABLE)) {
//			if (!DateUtils.isCurrentTimeBeforeThanGivenTime(login.getTokenExpirationDateTime()))
//				return false;
//		}
//		return true;
//	}

