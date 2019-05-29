package com.ntgclarity.smartcompound.common.dto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.ntgclarity.smartcompound.common.entity.Service;

@XmlRootElement
public class ServiceWrapper{
	
	public ServiceWrapper()
	{
		
	}
	
	
	public ServiceWrapper(Service service)
	{
		id =service.getId();
		serviceName = service.getServiceName();
		
	}
	
	
	
	@NotNull
	private Long id;
	@NotNull
	private String serviceName;
	private Boolean facilityRelated;
	
	
	@XmlElement
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@XmlElement
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	
	@XmlElement
	public Boolean getFacilityRelated() {
		return facilityRelated;
	}


	public void setFacilityRelated(Boolean facilityRelated) {
		this.facilityRelated = facilityRelated;
	}

	
	public static List<ServiceWrapper> wrappedServicesObject(List<Service> services) {
		List<ServiceWrapper> serviceWrappers = new ArrayList<>();
		for (Iterator<Service> iterator = services.iterator(); iterator.hasNext();) {
			serviceWrappers.add(wrappedServiceObject((Service) iterator.next()));
		}
		return serviceWrappers;
	}
	
	public static ServiceWrapper wrappedServiceObject(Service service) {
		ServiceWrapper serviceWrapper = new ServiceWrapper();
		serviceWrapper.setId(service.getId());
		serviceWrapper.setServiceName(service.getServiceName());
		serviceWrapper.setFacilityRelated(service.getIsFacilityRelated());
		return serviceWrapper;
	}


}
