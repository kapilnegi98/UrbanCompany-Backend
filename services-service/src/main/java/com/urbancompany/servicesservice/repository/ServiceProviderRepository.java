package com.urbancompany.servicesservice.repository;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.urbancompany.servicesservice.entity.ServiceProvider;

@Repository
public class ServiceProviderRepository {

	private Long seqId;
	private List<ServiceProvider> serviceProviders;

	@PostConstruct
	public void initialize() {
		seqId = 4001l;
		serviceProviders = new LinkedList<>();
		ServiceProvider provider = new ServiceProvider();
		provider.setServiceId(4000l);
		provider.setServiceProviderId(1002l);
		provider.setServiceTypeId(3001l);
		provider.setPincode("110089");
		provider.setFees(500l);
		provider.setEmail("kapil@gmail.com");
		provider.setComment("I want you to know something about me");
		serviceProviders.add(provider);
	}
	
	public List<ServiceProvider> findbyProviderId(Long providerId) {
		return serviceProviders.stream()
				.filter(serviceProvider -> serviceProvider.getServiceProviderId().equals(providerId))
				.collect(Collectors.toList());
	}
	
	public ServiceProvider findById(Long id) {
		return serviceProviders.stream().filter(serviceProvider -> serviceProvider.getServiceId().equals(id)).findAny()
				.orElse(null);
	}

	public ServiceProvider findByIdAndServiceProviderId(Long id, Long serviceProviderId) {
		return serviceProviders.stream().filter(serviceProvider -> serviceProvider.getServiceId().equals(id)
				&& serviceProvider.getServiceProviderId().equals(serviceProviderId))
				.findAny()
				.orElse(null);
	}

	public Long save(ServiceProvider serviceProvider) {
		serviceProvider.setServiceId(nextId());
		serviceProviders.add(serviceProvider);
		return serviceProvider.getServiceId();
	}
	
	public boolean delete(Long id, Long serviceProviderId) {
		return serviceProviders.removeIf(serviceProvider -> serviceProvider.getServiceId().equals(id) 
				&& serviceProvider.getServiceProviderId().equals(serviceProviderId));
	}

	public List<ServiceProvider> getServiceProviders() {
		return serviceProviders;
	}

	private Long nextId() {
		return seqId++;
	}

}
