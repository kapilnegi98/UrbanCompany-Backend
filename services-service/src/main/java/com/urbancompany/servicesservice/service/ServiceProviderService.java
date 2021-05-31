package com.urbancompany.servicesservice.service;

import java.util.List;

import com.urbancompany.servicesservice.entity.ServiceProvider;
import com.urbancompany.servicesservice.request.ProvideServiceRequest;

public interface ServiceProviderService {
	
	public List<ServiceProvider> getAllProvidedService(String providerId);

	public ServiceProvider getProvidedService(String id);

	public Long provideService(ProvideServiceRequest req, String userId, String pincode, String email);
	
	public boolean stopProvidedService(String serviceId, String userId);

	public boolean updateProvidedService(String serviceId, String userId, String fees, String comment);
}
