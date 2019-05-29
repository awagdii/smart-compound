package com.ntgclarity.smartcompound.common.entity;

import java.io.Serializable;
import java.util.Date;

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
@Table(name = "ng_nts_bill_calculation")
public class BillCalculation extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE ,generator=" ng_nts_bill_calculation_recid_seq")
	@SequenceGenerator(name=" ng_nts_bill_calculation_recid_seq", sequenceName=" ng_nts_bill_calculation_recid_seq")
	@Column(name = "recid")
	private Long id;	
	@Column(name = "calculation_date")
	private Date calculationDate;
//	@Column(name = "calculation_month_and_year")
//	private Date calculationMonthAndYear;
	@Column(name = "previous_meter_reading")
	private Double previousMeterReading=0d;
	@Column(name = "current_meter_reading")
	private Double currentMeterReading=previousMeterReading;
	@Column(name = "installation_value")
	private Double installationValue=0d;
	@Column(name = "nrc_value")
	private Double nrcValue=0d;
	@Column(name = "mrc_value")
	private Double mrcValue=0d;
	@Column(name = "usage_amount")
	private Double usageAmount=0d;
	@Column(name = "usage_value")
	private Double usageValue=0d;
	@Column(name = "calculation_base")
	private String calculationBase;
	@Column(name = "description")
	private String description;
	
	@Column(name = "calculated_by")
	private String calculatedBy;
	
	@ManyToOne
	@JoinColumn(name ="compound_id" , referencedColumnName="recid")
	private Compound compound;
	
	@ManyToOne
	@JoinColumn(name ="service_id" , referencedColumnName="recid")
	private Service service;
	
	@ManyToOne
	@JoinColumn(name ="order_id" , referencedColumnName="recid")
	private Order order;
	
	@ManyToOne
	@JoinColumn(name ="facility_id" , referencedColumnName="recid")
	private Facility facility;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;

	}

	public Date getCalculationDate() {
		return calculationDate;
	}

	public void setCalculationDate(Date calculationDate) {
		this.calculationDate = calculationDate;
	}

//	public Date getCalculationMonthAndYear() {
//		return calculationMonthAndYear;
//	}
//
//	public void setCalculationMonthAndYear(Date calculationMonthAndYear) {
//		this.calculationMonthAndYear = calculationMonthAndYear;
//	}

	public Double getPreviousMeterReading() {
		return previousMeterReading;
	}

	public void setPreviousMeterReading(Double previousMeterReading) {
		this.previousMeterReading = previousMeterReading;
	}

	public Double getCurrentMeterReading() {
		return currentMeterReading;
	}

	public void setCurrentMeterReading(Double currentMeterReading) {
		this.currentMeterReading = currentMeterReading;
	}

	public Double getInstallationValue() {
		return installationValue;
	}

	public void setInstallationValue(Double installationValue) {
		this.installationValue = installationValue;
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

	public Double getUsageAmount() {
		return usageAmount;
	}

	public void setUsageAmount(Double usageAmount) {
		this.usageAmount = usageAmount;
	}

	public Double getUsageValue() {
		return usageValue;
	}

	public void setUsageValue(Double usageValue) {
		this.usageValue = usageValue;
	}

	public String getCalculationBase() {
		return calculationBase;
	}

	public void setCalculationBase(String calculationBase) {
		this.calculationBase = calculationBase;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCalculatedBy() {
		return calculatedBy;
	}

	public void setCalculatedBy(String calculatedBy) {
		this.calculatedBy = calculatedBy;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Compound getCompound() {
		return compound;
	}

	public void setCompound(Compound compound) {
		this.compound = compound;
	}

	
	public Facility getFacility() {
		return facility;
	}

	public void setFacility(Facility facility) {
		this.facility = facility;
	}

	@Override
	public String toString() {
		return "BillCalculation [id=" + id + ", calculationDate="
				+ calculationDate + ", previousMeterReading="
				+ previousMeterReading + ", currentMeterReading="
				+ currentMeterReading + ", installationValue="
				+ installationValue + ", nrcValue=" + nrcValue + ", mrcValue="
				+ mrcValue + ", usageAmount=" + usageAmount + ", usageValue="
				+ usageValue + ", calculationBase=" + calculationBase
				+ ", description=" + description + ", calculatedBy="
				+ calculatedBy + ", compound=" + compound + ", service="
				+ service + ", order=" + order + ", facility=" + facility + "]";
	}

	
}
