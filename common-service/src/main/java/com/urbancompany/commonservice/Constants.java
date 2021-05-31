package com.urbancompany.commonservice;

public class Constants {
	private Constants() {}
	
	public static final String SUCCESS = "success";
	public static final String FAILURE = "failure";
	public static final String AUTHORIZATION = "Authorization";
	public static final String X_ = "X-";
	public static final String APIKEY_HEADER = "X-apiKey";	
	public static final String HTTP = "http://";
	public static final String MOBILE = "mobile";
	public static final String PINCODE = "pincode";
	public static final String ROLE = "role";
	public static final String EMAIL = "email";
	public static final String SUB = "sub";
	
	public static class Services {
		private Services() {}
		
		public static final String NOTIFICATIONS = "uc-notifications";
		public static final String USER_MANAGEMENT = "uc-user-management";
		public static final String FEEDBACKS = "uc-feedbacks";
		public static final String SERVICES = "uc-services";
		public static final String BOOKINGS = "uc-booking-management";
		public static final String OFFERS = "uc-offers";
	}
	
	public static class UCRequestAttributes {
		private UCRequestAttributes() {}
		
		public static final String USER_ROLE = X_ + ROLE;
		public static final String USER_ID = X_ + SUB;
		public static final String USER_PINCODE = X_ + PINCODE;
		public static final String USER_EMAIL = X_ + EMAIL;
	}
	
	public static class Regex {
		private Regex() {}
		
		public static final String NUMBER = "^[1-9]\\d*$";
		public static final String PERCENTAGE = "^[1-9][0-9]?$|^100$";
		public static final String VALID_ATTRIBUTE_REGEX = "^(sub|role|mobile|pincode|email)$";


	}
	
}
