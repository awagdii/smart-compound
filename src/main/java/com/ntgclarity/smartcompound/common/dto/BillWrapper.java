package com.ntgclarity.smartcompound.common.dto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.ntgclarity.smartcompound.common.entity.Bill;

/**
*
* @author Karim M.Fadel
*/
@XmlRootElement
public class BillWrapper {
	@NotNull
	private Long id;
	private Long billNumber;
	private Long billFrom;
	private Long billTo;
	private Double totalAmount;
	private Integer collected;

	@XmlElement
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;

	}
	@XmlElement
	public Long getBillNumber() {
		return billNumber;
	}

	public void setBillNumber(Long billNumber) {
		this.billNumber = billNumber;
	}

	@XmlElement
	public Long getBillFrom() {
		return billFrom;
	}

	public void setBillFrom(Long billFrom) {
		this.billFrom = billFrom;
	}

	@XmlElement
	public Long getBillTo() {
		return billTo;
	}

	public void setBillTo(Long billTo) {
		this.billTo = billTo;
	}

	@XmlElement
	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	@XmlElement
	public Integer getCollected() {
		return collected;
	}

	public void setCollected(Integer collected) {
		this.collected = collected;
	}
	
	public static List<BillWrapper> wrappedBillsObject(List<Bill> bills){
		List<BillWrapper> billWrappers = new ArrayList<>();
		for (Iterator<Bill> iterator = bills.iterator(); iterator.hasNext();) {
			billWrappers.add(wrappedBillObject((Bill) iterator.next()));
		}
		return billWrappers;
	}

	public static BillWrapper wrappedBillObject(Bill bill){
		BillWrapper billWrapper = new BillWrapper();
		billWrapper.setBillFrom(bill.getBillFrom().getTime());
		billWrapper.setBillNumber(bill.getBillNumber());
		billWrapper.setBillTo(bill.getBillTo().getTime());
		billWrapper.setCollected(bill.getCollected());
		billWrapper.setId(bill.getId());
		billWrapper.setTotalAmount(bill.getTotalAmount());
		return billWrapper;
	}
}
