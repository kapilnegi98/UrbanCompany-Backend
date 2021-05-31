package com.urbancompany.bookingmanagementservice.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.urbancompany.bookingmanagementservice.service.BookingService;
import com.urbancompany.commonservice.dto.BookingStatusChangeDto;

@Component
public class BookingStatusChangeListener {

	@Autowired
	private BookingService bookingService;
	
	@RabbitListener(queues = "${rabbit.booking.status.change.queue.name}")
	public void bookingStatusChangeLitsener(BookingStatusChangeDto dto) {
		bookingService.changeBookingStatus(dto);
	}
	
}
