package com.urbancompany.paymentservice.request;

import static com.urbancompany.commonservice.Constants.Regex.NUMBER;

import javax.validation.constraints.Pattern;

public class PaymentRequest {

	@Pattern(regexp = NUMBER, message = "booking id is invalid")
	private String bookingId;

	@Pattern(regexp = NUMBER, message = "offer id is invalid")
	private String offerId;

	@Pattern(regexp = NUMBER, message = "amount is invalid")
	private String amount;

	@Pattern(regexp = "^(UPI|WALLET|CREDIT_CARD|DEBIT_CARD)$", message = "mode of payment is invalid")
	private String modeOfPayment;

	private String details;

	public String getBookingId() {
		return bookingId;
	}

	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}

	public String getOfferId() {
		return offerId;
	}

	public void setOfferId(String offerId) {
		this.offerId = offerId;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getModeOfPayment() {
		return modeOfPayment;
	}

	public void setModeOfPayment(String modeOfPayment) {
		this.modeOfPayment = modeOfPayment;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

}
