package com.urbancompany.servicesservice.service;

import com.urbancompany.servicesservice.response.ServiceSearchReponse;

public interface SearchService {

	public ServiceSearchReponse searchServices(String serviceTypeId, String pincode);
	
}
