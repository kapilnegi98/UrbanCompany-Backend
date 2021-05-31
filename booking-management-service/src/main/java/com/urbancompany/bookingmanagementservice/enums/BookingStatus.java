package com.urbancompany.bookingmanagementservice.enums;


public enum BookingStatus {

	PAYMENT_PENDING("Booking Initiated! Please complete the payment."),
	PAYMENT_FAILED("Payment failed! Please try again."),
	PROVIDER_CONFIRMATION_PENDING("Payment done! waiting for provider's confirmation."),
	PROVIDER_DECLINED("Refund Initiated! Provider has declined the booking."),
	BOOKED("Booked! Please contact the provider once before the scheduled date and time."),
	COMPLETED("completed! Thanks for doing business with BookingStatus.javaus. Please do provide the feedback.");
	
	private String statusDescription;
	
	private BookingStatus(String statusDescription) {
		this.statusDescription = statusDescription;
	}
	
	public String getStatusDescription() {
		return this.statusDescription;
	}
}