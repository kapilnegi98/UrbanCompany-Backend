package com.urbancompany.bookingmanagementservice.repository;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.urbancompany.bookingmanagementservice.entity.Booking;
import com.urbancompany.bookingmanagementservice.enums.BookingStatus;

@Repository
public class BookingRepository {

	private List<Booking> bookings;
	private Long seqId;
	
	@PostConstruct
	public void initialize() {
		seqId = 6001l;
		bookings = new LinkedList<>();
		Booking booking = new Booking();
		booking.setId(6000l);
		booking.setServiceId(4000l);
		booking.setDate("20-02-2021");
		booking.setTime("13:00");
		booking.setProviderId(1002l);
		booking.setRecieverId(1003l);
		booking.setPaymentId(8000l);
		booking.setRecieverEmail("mehtageeta1811@gmail.com");
		booking.setStatus(BookingStatus.BOOKED);
		booking.setInstructions("please come wearing gloves and masks");
		bookings.add(booking);
	}
	
	public Long nextId() {
		return seqId++;
	}
	
	public Long save(Booking booking) {
		booking.setId(nextId());
		bookings.add(booking);
		return booking.getId();
	}
	
	public List<Booking> findAll() {
		return bookings;
	}
	
	public Booking findById(Long id) {
		return bookings.stream().filter(booking -> booking.getId().equals(id)).findAny().orElse(null);
	}
}
