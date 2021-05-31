package com.urbancompany.servicesservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.urbancompany.servicesservice.entity.ServiceType;
import com.urbancompany.servicesservice.repository.ServiceTypeRepository;
import com.urbancompany.servicesservice.request.AddServiceTypeRequest;
import com.urbancompany.servicesservice.service.ServiceTypeService;

@Service
public class ServiceTypeImpl implements ServiceTypeService {

	@Autowired
	private ServiceTypeRepository serviceTypeRepo;
	
	
	@Override
	public List<ServiceType> getAllServiceTypes() {
		return serviceTypeRepo.getServiceTypes();
	}
	
	
	@Override
	public Long addServiceType(AddServiceTypeRequest req) {
		ServiceType serviceType = new ServiceType();
		serviceType.setName(req.getName());
		serviceType.setDescription(req.getDescription());
		return serviceTypeRepo.save(serviceType);
	}
	
	@Override
	public ServiceType getServiceTypeById(String id) {
		return serviceTypeRepo.getServiceTypeById(Long.valueOf(id));
	}
	
}
