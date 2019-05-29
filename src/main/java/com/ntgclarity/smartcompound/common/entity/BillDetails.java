package com.ntgclarity.smartcompound.common.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.ntgclarity.smartcompound.common.base.BaseEntity;

@Entity
@Table(name = "ng_nts_bill_details")
public class BillDetails extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE ,generator="ng_nts_bill_details_recid_seq")
	@SequenceGenerator(name="ng_nts_bill_details_recid_seq", sequenceName="ng_nts_bill_details_recid_seq")
	@Column(name = "recid")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name ="service_id" , referencedColumnName="recid")
	private Service service;
	@Column (name="nrc_value")
	private Double nrcValue=0d;
	@Column(name="mrc_value")
	private Double mrcValue=0d;
	@Column (name="installation")
	private Double installation=0d;
	@Column(name="usage_value")
	private Double usageValue=0d;	
	@Column (name="total_amount")
	private Double totalAmount=0d;
	
	@ManyToOne
	@JoinColumn(name ="bill_id" , referencedColumnName="recid")
	private Bill bill;
	@ManyToOne
	@JoinColumn(name ="compound_id" , referencedColumnName="recid")
	private Compound compound;
	
	@ManyToOne
	@JoinColumn(name ="facility_id" , referencedColumnName="recid")
	private Facility facility;
	
	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id=id;
		
	}
	

	public Double getNrcValue() {
		return nrcValue;
	}

	public void setNrcValue(Double nrcValue) {
		this.nrcValue = nrcValue;
	}

	public Double getMrcValue() {
		return mrcValue;
	}

	public void setMrcValue(Double mrcValue) {
		this.mrcValue = mrcValue;
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}



	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public Compound getCompound() {
		return compound;
	}

	public void setCompound(Compound compound) {
		this.compound = compound;
	}

	public Double getInstallation() {
		return installation;
	}

	public void setInstallation(Double installation) {
		this.installation = installation;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

	public Double getUsageValue() {
		return usageValue;
	}

	public void setUsageValue(Double usageValue) {
		this.usageValue = usageValue;
	}

	public Facility getFacility() {
		return facility;
	}

	public void setFacility(Facility facility) {
		this.facility = facility;
	}

	@Override
	public String toString() {
		return "BillDetails [id=" + id + ", service=" + service + ", nrcValue="
				+ nrcValue + ", mrcValue=" + mrcValue + ", installation="
				+ installation + ", usageValue=" + usageValue
				+ ", totalAmount=" + totalAmount + ", bill=" + bill
				+ ", compound=" + compound + ", facility=" + facility + "]";
	}




	
	

}
