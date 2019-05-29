package com.ntgclarity.smartcompound.common.dto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.ntgclarity.smartcompound.common.entity.Service;
import com.ntgclarity.smartcompound.common.entity.Tenant;

public class ServiceBillingDetailsWrapper extends ServiceWrapper {
	private Integer nrc =0;
	private Integer mrc=0;
	private Double usage;
	private Double other;
	
public ServiceBillingDetailsWrapper()
{
	
}

public ServiceBillingDetailsWrapper(Service service)
{
	super(service);
}
	
	@XmlElement
	public Integer getNrc() {
		return nrc;
	}
	public void setNrc(Integer nrc) {
		this.nrc = nrc;
	}
	@XmlElement
	public Integer getMrc() {
		return mrc;
	}
	public void setMrc(Integer mrc) {
		this.mrc = mrc;
	}
	@XmlElement
	public Double getUsage() {
		return usage;
	}
	public void setUsage(Double usage) {
		this.usage = usage;
	}
	@XmlElement
	public Double getOther() {
		return other;
	}

	public void setOther(Double other) {
		this.other = other;
	}
	
/*	public static List<ServiceBillingDetailsWrapper> wrappedServiceDetailsObject (List<Service> services){
		List<ServiceBillingDetailsWrapper> billingDetailsWrappers = new ArrayList<>();
		for (Iterator<Service> iterator = services.iterator(); iterator.hasNext();) {
			billingDetailsWrappers.add(wrappedServiceDetailObject((Service) iterator.next()));
		}
		return billingDetailsWrappers;
	}*/
	
	public ServiceBillingDetailsWrapper wrappedServiceDetailObject (Service service, Double usage){
		ServiceBillingDetailsWrapper detailsWrapper = new ServiceBillingDetailsWrapper();
		detailsWrapper.setId(service.getId());
		detailsWrapper.setServiceName(service.getServiceName());
		detailsWrapper.setFacilityRelated(service.getIsFacilityRelated());
		if(service.getMrc() == null)
			service.setMrc(0);
		detailsWrapper.setMrc(service.getMrc());
		detailsWrapper.setNrc(service.getNrc());
		if(usage == null) usage=0D;
		detailsWrapper.setUsage(usage);
		detailsWrapper.setOther(service.getInstallationPrice());
		return detailsWrapper;
	}


	
}
