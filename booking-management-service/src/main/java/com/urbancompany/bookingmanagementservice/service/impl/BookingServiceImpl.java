package com.urbancompany.bookingmanagementservice.service.impl;

import static com.urbancompany.commonservice.Constants.APIKEY_HEADER;
import static com.urbancompany.commonservice.Constants.FAILURE;
import static com.urbancompany.commonservice.Constants.HTTP;
import static com.urbancompany.commonservice.Constants.SUCCESS;
import static com.urbancompany.commonservice.Constants.Services.NOTIFICATIONS;
import static com.urbancompany.commonservice.Constants.Services.SERVICES;
import static com.urbancompany.commonservice.Constants.Services.USER_MANAGEMENT;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.urbancompany.bookingmanagementservice.dto.FetchUsersReponse;
import com.urbancompany.bookingmanagementservice.dto.ServiceProvider;
import com.urbancompany.bookingmanagementservice.dto.User;
import com.urbancompany.bookingmanagementservice.entity.Booking;
import com.urbancompany.bookingmanagementservice.enums.BookingStatus;
import com.urbancompany.bookingmanagementservice.producers.NotificationProducer;
import com.urbancompany.bookingmanagementservice.repository.BookingCompletionOTPRepository;
import com.urbancompany.bookingmanagementservice.repository.BookingRepository;
import com.urbancompany.bookingmanagementservice.request.BookingRequest;
import com.urbancompany.bookingmanagementservice.response.BookingDetailsReponse;
import com.urbancompany.bookingmanagementservice.response.BookingResponse;
import com.urbancompany.bookingmanagementservice.service.BookingService;
import com.urbancompany.commonservice.config.CommonFileProperties;
import com.urbancompany.commonservice.dto.BookingStatusChangeDto;
import com.urbancompany.commonservice.dto.NotificationDto;
import com.urbancompany.commonservice.enums.UserRole;
import com.urbancompany.commonservice.exception.AuthorizationError;
import com.urbancompany.commonservice.exception.BusinessException;
import com.urbancompany.commonservice.exception.TechnicalException;

@Service
public class BookingServiceImpl implements BookingService {

	public static final Logger logger = LoggerFactory.getLogger(BookingServiceImpl.class);

	@Autowired
	private BookingCompletionOTPRepository bookingCompletionOTPRepo;
	
	@Autowired
	private BookingRepository bookingRepo;
	
	@Autowired
	private CommonFileProperties commonFileProps;
	
	@Autowired
	private WebClient.Builder webClient;

	@Autowired
	private NotificationProducer notificationProducer;
	
	public BookingResponse bookService(String userId, String userEmail, BookingRequest req) {
		logger.info("bookService() started");
		
		ServiceProvider provider = fetchServiceProviderForRequestedService(req.getServiceId());
		Booking booking = createNewBooking(userId, userEmail, provider, req);
		Long id = bookingRepo.save(booking);
		notifyBookingToProvider(provider, booking);
		
		return new BookingResponse(SUCCESS, "booking initiated", id);
	}

	private Booking createNewBooking(String userId, String userEmail, ServiceProvider provider, BookingRequest req) {
		logger.info("createNewBooking() started");
		
		Booking booking = new Booking();
		booking.setServiceId(Long.valueOf(req.getServiceId()));
		booking.setProviderId(Long.valueOf(provider.getServiceProviderId()));
		booking.setRecieverId(Long.valueOf(userId));
		booking.setRecieverEmail(userEmail);
		booking.setDate(req.getDate());
		booking.setTime(req.getTime());
		booking.setInstructions(req.getInstructions());
		booking.setStatus(BookingStatus.PAYMENT_PENDING);
		return booking;
	}

	private ServiceProvider fetchServiceProviderForRequestedService(String serviceId) {
		logger.info("fetchServiceProviderForRequestedService() start: for {}", serviceId);

		try {
			
			String rsrc = new StringBuilder().append("/uc/services/v1/provider/").append(serviceId).toString();

			String reqURL = new StringBuilder().append(HTTP).append(SERVICES).append(rsrc).toString();

			ServiceProvider response = webClient.build().get()
					.uri(URI.create(reqURL))
					.header(APIKEY_HEADER, commonFileProps.getApiKey())
					.retrieve().bodyToMono(ServiceProvider.class)
					.doOnError(error -> logger.error("fetchServiceProviderForRequestedService() {}: Mono completed with error.", serviceId))
					.block();
			
			logger.info("fetchServiceProviderForRequestedService() response: {}", response);
			return response;

		} catch (Exception e) {
			logger.error("fetchServiceProviderForRequestedService() error: for {}", serviceId, e);
			throw new BusinessException("sorry! we couldn't find the requested service");
		}

	}
	
	private void notifyBookingToProvider(ServiceProvider provider, Booking booking) {
		logger.info("notifyBookingToProvider() started");
		
		NotificationDto notification = new NotificationDto();
		notification.setTo(provider.getEmail());
		notification.setSubject("New Booking from UC");
		String body = "Dear user, \n You have a new booking. Please provide your confirmation for the booking from our site in time.\n BookingID: %s \n Service ID: %s \n By: %s \n Date: %s \n Time: %s \n \n Thanks, \n Admin Team UC";
		notification.setBody(String.format(body, booking.getId().toString(), booking.getServiceId().toString(), booking.getRecieverEmail(), booking.getDate(), booking.getTime()));
		notificationProducer.sendNotification(notification);
	}

	@Override
	public List<Booking> getBookings(String userId, String role) {
		Long reqUserId = Long.valueOf(userId);
		UserRole reqUserRole = UserRole.valueOf(role);
		
		return bookingRepo.findAll().stream().filter(booking -> {
			return ((reqUserRole.equals(UserRole.SERVICE_PROVIDER) && booking.getProviderId().equals(reqUserId))
					|| (reqUserRole.equals(UserRole.SERVICE_RECIEVER) && booking.getRecieverId().equals(reqUserId)));
	
		}).collect(Collectors.toList());
	}

	@Override
	public BookingDetailsReponse getBookingDetails(String bookingId) {
		Booking booking = bookingRepo.findById(Long.valueOf(bookingId));
		
		return Objects.nonNull(booking) ? createBookingDetails(booking)
				: null;
	}

	private BookingDetailsReponse createBookingDetails(Booking booking) {
		BookingDetailsReponse details = new BookingDetailsReponse();
		BeanUtils.copyProperties(booking, details);
		ServiceProvider service = fetchServiceProviderForRequestedService(booking.getServiceId().toString());
		Map<String, User> users = fetchUserDetailsOfProviders(Arrays.asList(String.valueOf(booking.getProviderId()), String.valueOf(booking.getRecieverId())));
		details.setService(service);
		details.setReciever(users.get(String.valueOf(booking.getRecieverId())));
		details.setProvider(users.get(String.valueOf(booking.getProviderId())));
		return details;
	}
	
	private Map<String, User> fetchUserDetailsOfProviders(List<String> providerIds) {
		logger.info("fetchUserDetailsOfProviders() sarted: for {}", providerIds);
		Map<String, User> users = new HashMap<>();
		
		if(providerIds.isEmpty()) {
			return users;
		}
		
		try {
			String rsrc = new StringBuilder().append("/uc/user-management/v1/user/fetch")
					.append("?ids=").append(encoded(String.join(",", providerIds)))
					.toString();
			
			String reqURL = new StringBuilder().append(HTTP).append(USER_MANAGEMENT).append(rsrc).toString();
			
			FetchUsersReponse response = webClient.build()
					.get().uri(URI.create(reqURL))
					.header(APIKEY_HEADER, commonFileProps.getApiKey())
					.retrieve()
					.bodyToMono(FetchUsersReponse.class)
					.doOnError(error -> logger.error("fetchUserDetailsOfProviders() {}: Mono completed with error.", providerIds))
					.block();
			
			users =  response.getUsers();
		} catch (Exception e) {
			logger.error("fetchUserDetailsOfProviders() error: for {}", providerIds, e);
		}
		
		
		
		logger.info("fetchUserDetailsOfProviders() exit: fetched {}", users.size());
		return users;
	}
	
	private String encoded(String str) throws UnsupportedEncodingException {
		return URLEncoder.encode(str, "UTF-8");
	}

	@Override
	public void confirmBooking(String bookingId, boolean confirmation) {
		logger.info("confirmBooking() started");
		
		
		Booking booking = bookingRepo.findById(Long.valueOf(bookingId));
		
		if(booking.getStatus().equals(BookingStatus.PROVIDER_CONFIRMATION_PENDING)) {
			booking.setStatus(confirmation ? BookingStatus.BOOKED 
					: BookingStatus.PROVIDER_DECLINED);
			notifyReciever(booking);
			return;
		} 
		
		throw new BusinessException("invalid action! booking not in confirmation status");
	}

	private void notifyReciever(Booking booking) {
		NotificationDto dto = new NotificationDto();
		dto.setTo(booking.getRecieverEmail());
		dto.setSubject("UC Booking status update");
		String body = "Dear user, \n Status of your booking has changed. \n Booking ID: %s \n Service ID: %s \n Status: %s \n Details: %s \n \n Thanks,\n Admin Team UC";
		dto.setBody(String.format(body, booking.getId().toString(), booking.getServiceId().toString(), booking.getStatus().name(), booking.getStatus().getStatusDescription()));
		notificationProducer.sendNotification(dto);
	}

	@Override
	public void changeBookingStatus(BookingStatusChangeDto dto) {
		Booking booking = bookingRepo.findById(Long.valueOf(dto.getBookingId()));
		
		if(Objects.nonNull(booking)) {
			BookingStatus newBookingStatus = BookingStatus.valueOf(dto.getStatus().toUpperCase());
			booking.setStatus(newBookingStatus);
			booking.setPaymentId(Long.valueOf(dto.getPaymentId()));
			notifyReciever(booking);
		}
		
	}

	@Override
	public void requestBookingCompletion(String bookingId, String providerId) {
		Booking booking = bookingRepo.findById(Long.valueOf(bookingId));
		
		if(Objects.isNull(booking)) {
			throw new BusinessException("booking does not exists");
		}
		
		if(booking.getProviderId().equals(Long.valueOf(providerId))) {
			String otp = String.format("%04d", new Random().nextInt(10000));
			bookingCompletionOTPRepo.save(Long.valueOf(bookingId), otp);
			sendBookingCompletionOTP(booking.getRecieverEmail(), otp);
		} else {
			throw new AuthorizationError();
		}
		
	}
	
	private void sendBookingCompletionOTP(String to, String otp) {
		logger.info("sendBookingCompletionOTP() started: to {}", to);
		
		String response = FAILURE;
		try {
			
			String subject = "UC - Booking Completion OTP";
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
					.doOnError(error -> logger.error("sendBookingCompletionOTP() {}: Mono completed with error.", to, error))
					.block();

		} catch (Exception e) {
			logger.error("sendBookingCompletionOTP() error: for {}", to, e);
		}
		
		logger.info("sendBookingCompletionOTP() exit: for {} response {}", to, response);
		if(!response.equalsIgnoreCase(SUCCESS)) {
			throw new TechnicalException("failed to send OTP to user");
		}
	}

	@Override
	public void requestBookingVerify(String bookingId, String otp, String providerId) {
		Booking booking = bookingRepo.findById(Long.valueOf(bookingId));
		
		if(Objects.isNull(booking)) {
			throw new BusinessException("booking does not exists");
		}
		if (booking.getProviderId().equals(Long.valueOf(providerId))) {
			String actualOTP = bookingCompletionOTPRepo.getOTP(booking.getId());
			if (Objects.nonNull(actualOTP) && !actualOTP.equals(otp)) {
				throw new BusinessException("otp does not match");
			}

			booking.setStatus(BookingStatus.COMPLETED);
			notifyReciever(booking);
		} else {
			throw new AuthorizationError();
		}
	}

	

}
