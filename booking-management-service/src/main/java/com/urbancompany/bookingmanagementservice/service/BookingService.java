package com.urbancompany.bookingmanagementservice.service;

import java.util.List;

import com.urbancompany.bookingmanagementservice.entity.Booking;
import com.urbancompany.bookingmanagementservice.request.BookingRequest;
import com.urbancompany.bookingmanagementservice.response.BookingDetailsReponse;
import com.urbancompany.bookingmanagementservice.response.BookingResponse;
import com.urbancompany.commonservice.dto.BookingStatusChangeDto;

public interface BookingService {

	public BookingResponse bookService(String userId, String userEmail, BookingRequest req);
	
	public List<Booking> getBookings(String userId, String role);
	
	public BookingDetailsReponse getBookingDetails(String bookingId);
	
	public void confirmBooking(String bookingId, boolean confirmation);
	
	public void changeBookingStatus(BookingStatusChangeDto dto);
	
	public void requestBookingCompletion(String bookingId, String providerId);

	public void requestBookingVerify(String bookingId, String providerId, String otp);
}
