package com.urbancompany.bookingmanagementservice.controller;

import static com.urbancompany.commonservice.Constants.Regex.NUMBER;
import static com.urbancompany.commonservice.Constants.UCRequestAttributes.USER_EMAIL;
import static com.urbancompany.commonservice.Constants.UCRequestAttributes.USER_ID;
import static com.urbancompany.commonservice.Constants.UCRequestAttributes.USER_ROLE;

import java.net.URI;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.urbancompany.bookingmanagementservice.entity.Booking;
import com.urbancompany.bookingmanagementservice.request.BookingRequest;
import com.urbancompany.bookingmanagementservice.response.BookingDetailsReponse;
import com.urbancompany.bookingmanagementservice.response.BookingResponse;
import com.urbancompany.bookingmanagementservice.service.BookingService;
import com.urbancompany.commonservice.enums.UserRole;
import com.urbancompany.commonservice.response.BaseResponse;
import com.urbancompany.commonservice.response.validators.IsAuthorized;

@Validated
@RestController
@RequestMapping("booking")
public class BookingController {

	@Autowired
	private BookingService bookingService;

	@PostMapping
	public ResponseEntity<BookingResponse> bookService(@RequestAttribute(USER_ID) String userId,
			@RequestAttribute(USER_EMAIL) String userEmail,
			@IsAuthorized(authorizedRoles = UserRole.SERVICE_RECIEVER) @RequestAttribute(USER_ROLE) String role,
			@RequestBody @Valid BookingRequest req) {
		BookingResponse response = bookingService.bookService(userId, userEmail, req);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(response.getBookingId()).toUri();

		return ResponseEntity.created(location).body(response);
	}

	@GetMapping
	public ResponseEntity<List<Booking>> getBookings(@RequestAttribute(USER_ID) String userId,
			@RequestAttribute(USER_ROLE) String role) {

		return ResponseEntity.ok(bookingService.getBookings(userId, role));
	}

	@GetMapping("{id}")
	public ResponseEntity<BookingDetailsReponse> getBookingDetails(
			@Pattern(regexp = NUMBER, message = "booking id is invalid") @PathVariable String id) {

		BookingDetailsReponse response = bookingService.getBookingDetails(id);
		
		return Objects.nonNull(response) ? ResponseEntity.ok(response) 
				: ResponseEntity.notFound().build();
	}
	
	@PutMapping("{id}/confirm")
	public ResponseEntity<BaseResponse> bookingConfirmation(
			@Pattern(regexp = NUMBER, message = "booking id is invalid") @PathVariable String id,
			@RequestParam boolean confirmation,
			@IsAuthorized(authorizedRoles = UserRole.SERVICE_PROVIDER) @RequestAttribute(USER_ROLE) String role) {
		
		bookingService.confirmBooking(id, confirmation);
		return ResponseEntity.ok().body(new BaseResponse("success", "status changed"));
	}
	
	@GetMapping("{id}/complete")
	public ResponseEntity<BaseResponse> bookingCompetionRequest(
			@Pattern(regexp = NUMBER, message = "booking id is invalid") @PathVariable String id,
			@IsAuthorized(authorizedRoles = UserRole.SERVICE_PROVIDER) @RequestAttribute(USER_ROLE) String role,
			@RequestAttribute(USER_ID) String userId) {
		bookingService.requestBookingCompletion(id, userId);
		return ResponseEntity.ok().body(new BaseResponse("success", "booking completion requested"));
	}

	@PostMapping("{id}/complete")
	public ResponseEntity<BaseResponse> bookingCompetionVerify(
			@Pattern(regexp = NUMBER, message = "booking id is invalid") @PathVariable String id,
			@IsAuthorized(authorizedRoles = UserRole.SERVICE_PROVIDER) @RequestAttribute(USER_ROLE) String role,
			@RequestParam String otp, @RequestAttribute(USER_ID) String userId) {
		bookingService.requestBookingVerify(id, otp, userId);
		return ResponseEntity.ok().body(new BaseResponse("success", "booking completed"));
	}
}
