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
import javax.persistence.Table;

import com.ntgclarity.smartcompound.common.base.BaseEntity;

@Entity
@Table(name = "ng_nts_tenant_facilities")
public class TenantFacility extends BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8080684629958627888L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "recid")
	private Long id;
	@ManyToOne
	@JoinColumn(name = "tenant_id", referencedColumnName = "recid")
	private Tenant tenant;
	@ManyToOne
	@JoinColumn(name = "facility_id", referencedColumnName = "recid")
	private Facility facility;
	@ManyToOne
	@JoinColumn(name = "compound_id", referencedColumnName = "recid")
	private Compound compound;
	@Column(name = "is_leased")
	private Integer isLeased;
	@Column(name = "leasing_or_ownership_start_date")
	private Date leasingOrOwnershipStartDate;
	@Column(name = "leasing_or_ownership_end_date")
	private Date leasingOrOwnershipEndDate;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Integer getIsLeased() {
		return isLeased;
	}
	public void setIsLeased(Integer isLeased) {
		this.isLeased = isLeased;
	}
	public Date getLeasingOrOwnershipStartDate() {
		return leasingOrOwnershipStartDate;
	}
	public void setLeasingOrOwnershipStartDate(Date leasingOrOwnershipStartDate) {
		this.leasingOrOwnershipStartDate = leasingOrOwnershipStartDate;
	}
	public Date getLeasingOrOwnershipEndDate() {
		return leasingOrOwnershipEndDate;
	}
	public void setLeasingOrOwnershipEndDate(Date leasingOrOwnershipEndDate) {
		this.leasingOrOwnershipEndDate = leasingOrOwnershipEndDate;
	}
	
	public Tenant getTenant() {
		return tenant;
	}
	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}
	public Facility getFacility() {
		return facility;
	}
	public void setFacility(Facility facility) {
		this.facility = facility;
	}
	public Compound getCompound() {
		return compound;
	}
	public void setCompound(Compound compound) {
		this.compound = compound;
	}
	@Override
	public String toString() {
		return "TenantFacility [id=" + id + ", tenant=" + tenant
				+ ", facility=" + facility + ", compound=" + compound
				+ ", isLeased=" + isLeased + ", leasingOrOwnershipStartDate="
				+ leasingOrOwnershipStartDate + ", leasingOrOwnershipEndDate="
				+ leasingOrOwnershipEndDate + "]";
	}
	

}
