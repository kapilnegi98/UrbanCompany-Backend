package com.urbancompany.servicesservice.service;

import java.util.List;

import com.urbancompany.servicesservice.entity.ServiceType;
import com.urbancompany.servicesservice.request.AddServiceTypeRequest;

public interface ServiceTypeService {

	public Long addServiceType(AddServiceTypeRequest req);
	
	public List<ServiceType> getAllServiceTypes();
	
	public ServiceType getServiceTypeById(String id);
}
