package com.ntgclarity.smartcompound.common.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.ntgclarity.smartcompound.common.entity.Bill;
import com.ntgclarity.smartcompound.common.entity.BillDetails;

@XmlRootElement
public class BillDetailsWrapper {

	private List<ServiceBillingDetailsWrapper> services;
	private Date collectionDate;
	private Date issueDate;
	private String collectedBy;

	@XmlElement
	public Date getCollectionDate() {
		return collectionDate;
	}

	public void setCollectionDate(Date collectionDate) {
		this.collectionDate = collectionDate;
	}

	@XmlElement
	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	@XmlElement
	public String getCollectedBy() {
		return collectedBy;
	}

	public void setCollectedBy(String collectedBy) {
		this.collectedBy = collectedBy;
	}

	@XmlElement
	public List<ServiceBillingDetailsWrapper> getServices() {
		return services;
	}

	public void setServices(List<ServiceBillingDetailsWrapper> services) {
		this.services = services;
	}	
	
	public static BillDetailsWrapper wrappedBillDeailesObject(Bill bill,
			List<BillDetails> billDetails) {
		BillDetailsWrapper billDetailsWrapper = new BillDetailsWrapper();
		billDetailsWrapper.setIssueDate(bill.getIssueDate());
		billDetailsWrapper.setCollectionDate(bill.getCollectionDate());
		billDetailsWrapper.setCollectedBy(bill.getCollectedBy());
		List<ServiceBillingDetailsWrapper> wrappedServices = new ArrayList<ServiceBillingDetailsWrapper>();
		for (BillDetails b : billDetails) {
			ServiceBillingDetailsWrapper ser = new ServiceBillingDetailsWrapper();
			
			wrappedServices.add(ser.wrappedServiceDetailObject(b.getService(),b.getUsageValue()));
		}
		
		billDetailsWrapper.setServices(wrappedServices);
		return billDetailsWrapper;
	}

}
