package com.ntgclarity.smartcompound.common.dto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.ntgclarity.smartcompound.common.entity.Order;

@XmlRootElement
public class OrderWrapperResponse {
	
	private String serviceName;
	private FacilityName facilityName;
	private String notes;
	private Long date;
	
	@XmlElement
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	
	@XmlElement
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	@XmlElement
	public Long getDate() {
		return date;
	}
	public void setDate(Long date) {
		this.date = date;
	}
	
	@XmlElement
	public FacilityName getFacilityName() {
		return facilityName;
	}
	public void setFacilityName(FacilityName facilityName) {
		this.facilityName = facilityName;
	}
	
	public static List<OrderWrapperResponse> wrappedOrdersResponceObject(List<Order> orders){
		List<OrderWrapperResponse> orderWrappers = new ArrayList<>();
		for (Iterator<Order> iterator = orders.iterator(); iterator.hasNext();) {
			orderWrappers.add(wrappedOrderResponceObject((Order) iterator.next()));
		}
		return orderWrappers;
	}
	
	public static OrderWrapperResponse wrappedOrderResponceObject(Order order){
		OrderWrapperResponse sentOrderWrapper =new OrderWrapperResponse();
		sentOrderWrapper.setServiceName(order.getService().getServiceName());
		if(order.getFacility()!=null)
			sentOrderWrapper.setFacilityName(FacilityName.getFacilityNameObject(order.getFacility()));
		sentOrderWrapper.setNotes(order.getNotes());
		if(order.getRequestDate()!=null)
			sentOrderWrapper.setDate(order.getRequestDate().getTime());
		return sentOrderWrapper;
	}


}
