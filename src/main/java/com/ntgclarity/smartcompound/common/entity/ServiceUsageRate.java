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
import javax.persistence.Transient;

import com.ntgclarity.smartcompound.common.base.BaseEntity;

@Entity
@Table(name = "ng_nts_service_usage_rates")
public class ServiceUsageRate extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -379438260367340798L;
	@ManyToOne
	@JoinColumn(name = "compound_id", referencedColumnName = "recid")
	private Compound compound;
	@ManyToOne
	@JoinColumn(name = "service_id", referencedColumnName = "recid")
	private Service service;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE ,generator="ng_nts_service_usage_rates_recid_seq")
	@SequenceGenerator(name="ng_nts_service_usage_rates_recid_seq", sequenceName="ng_nts_service_usage_rates_recid_seq")
	@Column(name = "recid")
	private Long id;
	@Column(name = "service_name")
	private String serviceName;
	@Column(name = "usage_amount_from")
	private Double usageAmountFrom;
	@Column(name = "usage_amount_to")
	private Double usageAmountTo;
	@Column(name = "unit_price")
	private Double unitPrice;
	
	@Transient
	private boolean editMode;

	@Override
	public Long getId() {

		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;

	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public Double getUsageAmountFrom() {
		return usageAmountFrom;
	}

	public void setUsageAmountFrom(Double usageAmmountFrom) {
		this.usageAmountFrom = usageAmmountFrom;
	}

	public Double getUsageAmountTo() {
		return usageAmountTo;
	}

	public void setUsageAmountTo(Double usageAmmountTo) {
		this.usageAmountTo = usageAmmountTo;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	
	public Compound getCompound() {
		return compound;
	}

	public void setCompound(Compound compound) {
		this.compound = compound;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	@Override
	public String toString() {
		return "ServiceUsageRate [compound=" + compound + ", service="
				+ service + ", id=" + id + ", serviceName=" + serviceName
				+ ", usageAmmountFrom=" + usageAmountFrom
				+ ", usageAmmountTo=" + usageAmountTo + ", unitPrice="
				+ unitPrice + "]";
	}



	public boolean isEditMode() {
		return editMode;
	}

	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}


	

}
