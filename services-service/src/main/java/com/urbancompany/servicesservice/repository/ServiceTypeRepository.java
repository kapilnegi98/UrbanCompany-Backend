package com.urbancompany.servicesservice.repository;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.urbancompany.servicesservice.entity.ServiceType;

@Repository
public class ServiceTypeRepository {

	private Long seqId;
	private List<ServiceType> serviceTypes;
	
	@PostConstruct
	public void initialize() {
		seqId = 3001l;
		serviceTypes = new LinkedList<>();
		ServiceType acSerice = new ServiceType();
		acSerice.setId(nextId());
		acSerice.setName("Air Conditioner Service");
		acSerice.setDescription("service description");
		serviceTypes.add(acSerice);
		
		ServiceType plumber = new ServiceType();
		plumber.setId(nextId());
		plumber.setName("Plumbing Service");
		plumber.setDescription("service description");
		serviceTypes.add(plumber);

		ServiceType haircut = new ServiceType();
		haircut.setId(nextId());
		haircut.setName("Haircut Service");
		haircut.setDescription("service description");
		serviceTypes.add(haircut);
		
	}
	
	public ServiceType getServiceTypeById(Long id) {
		return serviceTypes.stream().filter(serviceType -> serviceType.getId().equals(id)).findAny().orElse(null);
	}
	
	public Long save(ServiceType serviceType) {
		if(Objects.isNull(serviceType.getId())) {
			serviceType.setId(nextId());
		}
		serviceTypes.add(serviceType);
		return serviceType.getId();
	}
	
	private Long nextId() {
		return seqId++;
	}

	public List<ServiceType> getServiceTypes() {
		return serviceTypes;
	}
	
}
