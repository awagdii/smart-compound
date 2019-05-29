package com.ntgclarity.smartcompound.common.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

import org.hibernate.annotations.OptimisticLockType;

import com.ntgclarity.smartcompound.common.base.BaseEntity;

@Entity
@Table(name = "ng_nts_services")
public class Service extends BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ng_nts_services_recid_seq")
	@SequenceGenerator(name = "ng_nts_services_recid_seq", sequenceName = "ng_nts_services_recid_seq")
	@Column(name = "recid")
	private Long id;
	@Column(name = "service_name")
	private String serviceName;
	@Column(name = "description")
	private String description;
	@Column(name = "is_active")
	private Integer isActive;
	@Column(name = "severity")
	private Integer severity;
	@Column(name = "flat_or_usage")
	private String flatOrUsage;
	@Column(name = "is_facility_related")
	private Integer isFacilityRelated;

	@Column(name = "estimated_delivery_days")
	private Integer estimatedDeliveryDays=0;
	@Column(name = "nrc")
	private Integer nrc=0;
	@Column(name = "mrc")
	private Integer mrc=0;
	@Column(name = "installation_price")
	private Double installationPrice=0d;
	@Column(name = "is_prepaid")
	private Integer isPrepaid;
	@Column(name = "creation_date")
	private Date creationDate;
	@Column(name = "measuring_unit")
	private String measuringUnit;
	@ManyToOne
	@JoinColumn(name = "compound_id", referencedColumnName = "recid")
	private Compound compound;
	@ManyToOne
	@JoinColumn(name = "measuring_unit_lookup_id", referencedColumnName = "recid")
	private Lookup measuringUnitLookupId;

	@ManyToOne
	@JoinColumn(name = "flat_or_usage_lookup", referencedColumnName = "recid")
	private Lookup flatOrUsageLookup;

	@ManyToOne
	@JoinColumn(name = "activity_status_lookup", referencedColumnName = "recid")
	private Lookup activityStatusLookup;

	@Transient
	private List<ServiceUsageRate> serviceUsageRates = new ArrayList<ServiceUsageRate>();

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

	public Integer getSeverity() {
		return severity;
	}

	public void setSeverity(Integer severity) {
		this.severity = severity;
	}

	public String getFlatOrUsage() {
		return flatOrUsage;
	}

	public void setFlatOrUsage(String flatOrUsage) {
		this.flatOrUsage = flatOrUsage;
	}

	public Integer getEstimatedDeliveryDays() {
		return estimatedDeliveryDays;
	}

	public void setEstimatedDeliveryDays(Integer estimatedDeliveryDays) {
		this.estimatedDeliveryDays = estimatedDeliveryDays;
	}

	public Integer getNrc() {
		return nrc;
	}

	public void setNrc(Integer nrc) {
		this.nrc = nrc;
	}

	public Integer getMrc() {
		return mrc;
	}

	public void setMrc(Integer mrc) {
		this.mrc = mrc;
	}

	public Double getInstallationPrice() {
		return installationPrice;
	}

	public void setInstallationPrice(Double installationPrice) {
		this.installationPrice = installationPrice;
	}

	public Integer getIsPrepaid() {
		return isPrepaid;
	}

	public void setIsPrepaid(Integer isPrepared) {
		this.isPrepaid = isPrepared;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getMeasuringUnit() {
		return measuringUnit;
	}

	public void setMeasuringUnit(String measuringUnit) {
		this.measuringUnit = measuringUnit;
	}

	public Lookup getMeasuringUnitLookupId() {
		return measuringUnitLookupId;
	}

	public void setMeasuringUnitLookupId(Lookup measuringUnitLookupId) {
		this.measuringUnitLookupId = measuringUnitLookupId;
	}

	public Compound getCompound() {
		return compound;
	}

	public void setCompound(Compound compound) {
		this.compound = compound;
	}

	@Override
	public String toString() {
		return serviceName;
	}

	public List<ServiceUsageRate> getServiceUsageRates() {
		return serviceUsageRates;
	}

	public void setServiceUsageRates(List<ServiceUsageRate> serviceUsageRates) {
		this.serviceUsageRates = serviceUsageRates;
	}

	public Lookup getFlatOrUsageLookup() {
		return flatOrUsageLookup;
	}

	public void setFlatOrUsageLookup(Lookup flatOrUsageLookup) {
		this.flatOrUsageLookup = flatOrUsageLookup;
	}

	public boolean isUsage() {

		return flatOrUsageLookup != null
				&& flatOrUsageLookup.equals(Lookup.USAGE);
	}

	public Lookup getActivityStatusLookup() {
		return activityStatusLookup;
	}

	public void setActivityStatusLookup(Lookup activityStatusLookup) {
		this.activityStatusLookup = activityStatusLookup;
	}

	public boolean getIsFacilityRelated() {
		if (isFacilityRelated == null)
			return false;
		else if(isFacilityRelated == 0)
			return false;
		else
			return true;

	}

	public void setIsFacilityRelated(boolean isFacRelated) {
		if(isFacRelated==true) 
			isFacilityRelated=1;
		else isFacilityRelated=0;
	}

}
