package com.urbancompany.servicesservice.response;

import java.util.List;

import com.urbancompany.servicesservice.entity.ServiceType;

public class ServiceSearchReponse {

	private ServiceType serviceType;
	private List<DetailedServiceProvider> serviceProviders;

	public ServiceSearchReponse(ServiceType serviceType, List<DetailedServiceProvider> serviceProviders) {
		super();
		this.serviceType = serviceType;
		this.serviceProviders = serviceProviders;
	}

	public ServiceType getServiceType() {
		return serviceType;
	}

	public void setServiceType(ServiceType serviceType) {
		this.serviceType = serviceType;
	}

	public List<DetailedServiceProvider> getServiceProviders() {
		return serviceProviders;
	}

	public void setServiceProviders(List<DetailedServiceProvider> serviceProviders) {
		this.serviceProviders = serviceProviders;
	}

}
