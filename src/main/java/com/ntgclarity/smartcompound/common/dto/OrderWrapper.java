package com.ntgclarity.smartcompound.common.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;

import com.ntgclarity.smartcompound.common.entity.Facility;
import com.ntgclarity.smartcompound.common.entity.Lookup;
import com.ntgclarity.smartcompound.common.entity.Order;
import com.ntgclarity.smartcompound.common.entity.Service;
import com.ntgclarity.smartcompound.common.entity.Tenant;
import com.ntgclarity.smartcompound.common.utils.Utils;

public class OrderWrapper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6489027984427925289L;
	@NotNull
	private Long facilityId;
	@NotNull
	private Long serviceId;
	
	private String note;
	@NotNull
	private Long tenantId;

	@XmlElement
	public Long getFacilityId() {
		return facilityId;
	}
	public void setFacilityId(Long facilityId) {
		this.facilityId = facilityId;
	}
	
	@XmlElement
	public Long getServiceId() {
		return serviceId;
	}
	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}
	
	@XmlElement
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	@XmlElement
	public Long getTenantId() {
		return tenantId;
	}
	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}
	public static Order unWrappedOrderObject(OrderWrapper orderWrapper) {
		Order order =new Order();
		if(orderWrapper.getFacilityId()!= null){
			Facility facility = new Facility();
			facility.setId(orderWrapper.getFacilityId());
			order.setFacility(facility);
		}
		if(orderWrapper.getServiceId()!=null){
			Service service = new Service();
			service.setId(orderWrapper.getServiceId());
			order.setService(service);
		}
		Tenant tenant = new Tenant();
		tenant.setId(orderWrapper.getTenantId());
		order.setTenant(tenant);
		order.setStatusLookup(Lookup.ORDER_NEW); 
		order.setStatus(Lookup.ORDER_NEW.getLookupName());
		order.setChannel("Mobile");
		order.setNotes(orderWrapper.getNote());
		order.setRequestDate(Utils.getStartOfDay(new Date()));
		return order;
	}
	public static OrderWrapper wrappedOrderObject(Order order){
		OrderWrapper orderWrapper = new OrderWrapper();
		orderWrapper.setFacilityId(order.getFacility().getId());
		orderWrapper.setServiceId(order.getService().getId());
		orderWrapper.setTenantId(order.getTenant().getId());
		orderWrapper.setNote(order.getNotes());
		return orderWrapper;
	}
}