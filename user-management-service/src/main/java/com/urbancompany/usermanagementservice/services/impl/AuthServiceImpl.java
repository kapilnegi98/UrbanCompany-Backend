package com.urbancompany.usermanagementservice.services.impl;


import static com.urbancompany.commonservice.Constants.FAILURE;
import static com.urbancompany.commonservice.Constants.HTTP;
import static com.urbancompany.commonservice.Constants.SUCCESS;
import static com.urbancompany.commonservice.Constants.Services.NOTIFICATIONS;
import static com.urbancompany.commonservice.Constants.APIKEY_HEADER;
import static com.urbancompany.commonservice.Constants.EMAIL;
import static com.urbancompany.commonservice.Constants.MOBILE;
import static com.urbancompany.commonservice.Constants.PINCODE;
import static com.urbancompany.commonservice.Constants.ROLE;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.urbancompany.commonservice.config.CommonFileProperties;
import com.urbancompany.commonservice.exception.TechnicalException;
import com.urbancompany.commonservice.response.BaseResponse;
import com.urbancompany.usermanagementservice.config.FileProperties;
import com.urbancompany.usermanagementservice.entity.User;
import com.urbancompany.usermanagementservice.entity.UserOTP;
import com.urbancompany.usermanagementservice.repository.UserOTPRepository;
import com.urbancompany.usermanagementservice.repository.UserRepository;
import com.urbancompany.usermanagementservice.request.LogInRequest;
import com.urbancompany.usermanagementservice.response.LogInResponse;
import com.urbancompany.usermanagementservice.services.AuthService;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;

@Service
public class AuthServiceImpl implements AuthService {
	
	public static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

	@Autowired
	private UserOTPRepository otpRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private FileProperties fileProps;

	@Autowired
	private CommonFileProperties commonFileProps;
	
	@Autowired
	private WebClient.Builder webClient;

	@Override
	public BaseResponse generateOTP(String email) {
		logger.info("generateOTP() started: for {}", email);
		User user = userRepo.findByEmail(email);

		if (Objects.isNull(user)) {
			return new BaseResponse(FAILURE, "user not exists. Please sign up first!");
		}

		UserOTP otp = otpRepo.findByEmail(email);
		if (Objects.isNull(otp) || Duration.between(otp.getOtpGenTime(), LocalDateTime.now()).toMinutes() > Long
				.valueOf(fileProps.getOtpValidity())) {
			otp = createNewOTP(user);
			otpRepo.save(otp);
			sendOTPbyEmail(user.getEmail(), otp.getOtp());
			System.out.println(user.getEmail()+ ":" +otp.getOtp());
		} else {
			sendOTPbyEmail(user.getEmail(), otp.getOtp());
			System.out.println(user.getEmail()+ ":" +otp.getOtp());
		}

		logger.info("generateOTP() exit: for {}", email);
		return new BaseResponse(SUCCESS, String.format("otp sent to %s", email));
	}

	

	private UserOTP createNewOTP(User user) {
		String randomOTP = String.format("%04d", new Random().nextInt(10000));
		UserOTP otp = new UserOTP();
		otp.setEmail(user.getEmail());
		otp.setOtp(randomOTP);
		otp.setOtpGenTime(LocalDateTime.now());
		otp.setUserId(user.getId());
		return otp;
	}

	@Override
	public LogInResponse userLogIn(LogInRequest req) {
		logger.info("userLogIn() started: for {}", req.getEmail());
		
		UserOTP otp = otpRepo.findByEmail(req.getEmail());
		LogInResponse response = new LogInResponse();
		System.out.println(otp.getOtp()+"---------otp gen");
		if (Objects.isNull(otp)) {
			response = new LogInResponse(FAILURE, "otp not generated");
		} else if(!otp.getOtp().equals(req.getOtp())) {
			response = new LogInResponse(FAILURE, "incorrect otp");
		} 
		else if (Duration.between(otp.getOtpGenTime(), LocalDateTime.now()).toMinutes() > Long
				.valueOf(fileProps.getOtpValidity())) {
			response = new LogInResponse(FAILURE, "otp expired");
		} else {

			User user = userRepo.findByEmail(req.getEmail());
			response.setStatus(SUCCESS);
			response.setMessage("logged in");
			response.setUser(user);
			response.setToken(generateToken(user));
			otpRepo.deleteByEmail(req.getEmail());
		}
		
		logger.info("userLogIn() exit: for {}", req.getEmail());
		return response;
	}

	private String generateToken(User user) {
		logger.info("generateToken() started: for {}", user.getEmail());

		Date genTime = new Date();
		Date expTime = new Date(genTime.getTime() + (Long.valueOf(commonFileProps.getTokenValidity()) * 60 * 1000));

		JwtBuilder jwtBuilder = Jwts.builder().setIssuedAt(genTime).setIssuer("UCAUTH")
				.setSubject(String.valueOf(user.getId())).setExpiration(expTime)
				.signWith(SignatureAlgorithm.HS256, TextCodec.BASE64.decode(commonFileProps.getJwtSecret()));

		Map<String, Object> claims = new HashMap<>();
		claims.put(MOBILE, user.getMobile());
		claims.put(EMAIL, user.getEmail());
		claims.put(ROLE, user.getRole().name());
		claims.put(PINCODE, String.valueOf(user.getPincode()));
		
		jwtBuilder.addClaims(claims);

		return jwtBuilder.compact();
	}

	private void sendOTPbyEmail(String to, String otp) {
		logger.info("sendOTPbyEmail() started:  to {}", to);
		System.out.println("otp is--->"+otp);
		String response = FAILURE;
		try {
			
			String subject = "UC - Login OTP";
			String body = "Your OTP is " + otp;
			String rsrc = "/uc/notifications/v1/mail?" + "to=" + to 
					+ "&subject=" + encoded(subject);
			
			String reqURL = new StringBuilder().append(HTTP).append(NOTIFICATIONS).append(rsrc).toString();

			response = webClient.build()
					.post().uri(URI.create(reqURL))
					.header(APIKEY_HEADER, commonFileProps.getApiKey())
					.contentType(MediaType.TEXT_PLAIN)
					.body(BodyInserters.fromObject(body))
					.retrieve()
					.bodyToMono(String.class)
					.doOnError(error -> logger.error("sendOTPbyEmail() {}: Mono completed with error.", to, error))
					.block();

		} catch (Exception e) {
			logger.error("sendOTPbyEmail() error: for {}", to, e);
		}
		
		logger.info("sendOTPbyEmail() exit: for {} response {}", to, response);
		if(!response.equalsIgnoreCase(SUCCESS)) {
			throw new TechnicalException("failed to send OTP to user");
		}
	}
	
	private String encoded(String str) throws UnsupportedEncodingException {
		return URLEncoder.encode(str, "UTF-8");
	}

	
}
