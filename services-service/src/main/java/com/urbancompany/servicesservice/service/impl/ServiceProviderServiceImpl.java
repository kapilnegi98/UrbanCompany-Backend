package com.urbancompany.servicesservice.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.urbancompany.commonservice.exception.BusinessException;
import com.urbancompany.servicesservice.entity.ServiceProvider;
import com.urbancompany.servicesservice.entity.ServiceType;
import com.urbancompany.servicesservice.repository.ServiceProviderRepository;
import com.urbancompany.servicesservice.repository.ServiceTypeRepository;
import com.urbancompany.servicesservice.request.ProvideServiceRequest;
import com.urbancompany.servicesservice.service.ServiceProviderService;

@Service
public class ServiceProviderServiceImpl implements ServiceProviderService {

	@Autowired
	private ServiceProviderRepository serviceProviderRepo;

	@Autowired
	private ServiceTypeRepository serviceTypeRepo;

	@Override
	public Long provideService(ProvideServiceRequest req, String userId, String pincode, String email) {
		ServiceType serviceType = serviceTypeRepo.getServiceTypeById(Long.valueOf(req.getServiceTypeId()));
		
		if(Objects.nonNull(serviceType)) {
			ServiceProvider serviceProvider = new ServiceProvider();
			serviceProvider.setServiceTypeId(serviceType.getId());
			serviceProvider.setServiceProviderId(Long.valueOf(userId));
			serviceProvider.setPincode(pincode);
			serviceProvider.setFees(Long.valueOf(req.getFees()));
			serviceProvider.setComment(req.getComment());
			serviceProvider.setEmail(email);
			
			
			return serviceProviderRepo.save(serviceProvider);
			
		} else {
			throw new BusinessException("UC do not provide given service");
		}
		
	}

	@Override
	public boolean stopProvidedService(String serviceId, String userId) {
		return serviceProviderRepo.delete(Long.valueOf(serviceId), Long.valueOf(userId));
	}

	@Override
	public boolean updateProvidedService(String serviceId, String userId, String fees, String comment) {
		ServiceProvider serviceProvider = serviceProviderRepo.findByIdAndServiceProviderId(Long.valueOf(serviceId),
				Long.valueOf(userId));
		
		if (Objects.nonNull(serviceProvider)) {
			serviceProvider.setFees(Long.valueOf(fees));
			serviceProvider.setComment(comment);
			return true;
		}
		return false;
	}

	@Override
	public ServiceProvider getProvidedService(String id) {
		return serviceProviderRepo.findById(Long.valueOf(id));
	}

	@Override
	public List<ServiceProvider> getAllProvidedService(String providerId) {
		return serviceProviderRepo.findbyProviderId(Long.valueOf(providerId));
	}
}
