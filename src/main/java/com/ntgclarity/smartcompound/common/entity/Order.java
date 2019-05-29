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
@Table(name = "ng_nts_orders")
public class Order extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6489027984427925289L;
	@ManyToOne
	@JoinColumn(name = "compound_id", referencedColumnName = "recid")
	private Compound compound;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="ng_nts_orders_recid_seq")
	@SequenceGenerator(name="ng_nts_orders_recid_seq", sequenceName="ng_nts_orders_recid_seq")
	@Column(name = "recid")
	private Long id;
	@ManyToOne
	@JoinColumn(name = "channel_lookup_id", referencedColumnName = "recid")
	private Lookup channelLookup;
	@ManyToOne
	@JoinColumn(name = "service_id", referencedColumnName = "recid")
	private Service service;

	@Column(name = "service_name")
	private String serviceName;

	// @Column(name = "order_id")
	// private Integer orderId;

	@Column(name = "request_date")
	private Date requestDate;

	@Column(name = "status")
	private String status;

	@ManyToOne
	@JoinColumn(name = "created_by", referencedColumnName = "recid")
	private Employee createdBy;

	@Column(name = "acceptence_date")
	private Date acceptanceDate;

	@ManyToOne
	@JoinColumn(name = "accepted_by", referencedColumnName = "recid")
	private Employee acceptedBy;

	@Column(name = "channel")
	private String channel;

	@Column(name = "notes")
	private String notes;

	@Column(name = "service_location_longtude")
	private Double serviceLocationLongtude;

	@Column(name = "service_location_Lattitude")
	private Double serviceLocationlattitude;

	@Column(name = "service_location_desc")
	private String serviceLocationDesc;

	@ManyToOne
	@JoinColumn(name = "facility_id", referencedColumnName = "recid")
	private Facility facility;

	@ManyToOne
	@JoinColumn(name = "tenant_id", referencedColumnName = "recid")
	private Tenant tenant;
	
	@ManyToOne
	@JoinColumn(name ="status_lookup_id" , referencedColumnName="recid")
	private Lookup statusLookup;

	@Column(name = "meter_last_reading")
	private  Double meterLastReading=0d;
	
	@Column(name = "meter_last_reading_date")
	private Date meterLastReadingDate;
	
	@Column(name="is_biled_before")
	private Integer isBiledBefore=0;
	
	public Compound getCompound() {
		return compound;
	}

	public void setCompound(Compound compound) {
		this.compound = compound;
	}

	public Lookup getChannelLookup() {
		return channelLookup;
	}

	public void setChannelLookup(Lookup channelLookup) {
		this.channelLookup = channelLookup;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public Tenant getTenant() {
		return tenant;
	}

	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Employee getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Employee createdBy) {
		this.createdBy = createdBy;
	}

	public Employee getAcceptedBy() {
		return acceptedBy;
	}

	public void setAcceptedBy(Employee acceptedBy) {
		this.acceptedBy = acceptedBy;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public Double getServiceLocationlongtude() {
		return serviceLocationLongtude;
	}

	public void setServiceLocationlongtude(Double serviceLocationlongtude) {
		this.serviceLocationLongtude = serviceLocationlongtude;
	}

	public Double getServiceLocationlattitude() {
		return serviceLocationlattitude;
	}

	public void setServiceLocationlattitude(Double serviceLocationlattitude) {
		this.serviceLocationlattitude = serviceLocationlattitude;
	}

	public Date getAcceptanceDate() {
		return acceptanceDate;
	}

	public void setAcceptanceDate(Date acceptanceDate) {
		this.acceptanceDate = acceptanceDate;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getServiceLocationDesc() {
		return serviceLocationDesc;
	}

	public void setServiceLocationDesc(String serviceLocationDesc) {
		this.serviceLocationDesc = serviceLocationDesc;
	}

	

	@Override
	public Long getId() {

		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;

	}
	public Facility getFacility() {
		return facility;
	}

	public void setFacility(Facility facility) {
		this.facility = facility;
	}
	
	public Lookup getStatusLookup() {
		return statusLookup;
	}

	public void setStatusLookup(Lookup statusLookup) {
		this.statusLookup = statusLookup;
	}

	public Double getMeterLastReading() {
		return meterLastReading;
	}

	public void setMeterLastReading(Double meterLastReading) {
		this.meterLastReading = meterLastReading;
	}

	public Date getMeterLastReadingDate() {
		return meterLastReadingDate;
	}

	public void setMeterLastReadingDate(Date meterLastReadingDate) {
		this.meterLastReadingDate = meterLastReadingDate;
	}

	public Double getServiceLocationLongtude() {
		return serviceLocationLongtude;
	}

	public void setServiceLocationLongtude(Double serviceLocationLongtude) {
		this.serviceLocationLongtude = serviceLocationLongtude;
	}

	public Integer getIsBiledBefore() {
		return isBiledBefore;
	}

	public void setIsBiledBefore(Integer isBiledBefore) {
		this.isBiledBefore = isBiledBefore;
	}

	@Override
	public String toString() {
		return "Order [compound=" + compound + ", id=" + id
				+ ", channelLookup=" + channelLookup + ", service=" + service
				+ ", serviceName=" + serviceName + ", requestDate="
				+ requestDate + ", status=" + status + ", createdBy="
				+ createdBy + ", acceptanceDate=" + acceptanceDate
				+ ", acceptedBy=" + acceptedBy + ", channel=" + channel
				+ ", notes=" + notes + ", serviceLocationLongtude="
				+ serviceLocationLongtude + ", serviceLocationlattitude="
				+ serviceLocationlattitude + ", serviceLocationDesc="
				+ serviceLocationDesc + ", facility=" + facility + ", tenant="
				+ tenant + ", statusLookup=" + statusLookup
				+ ", meterLastReading=" + meterLastReading
				+ ", meterLastReadingDate=" + meterLastReadingDate
				+ ", isBiledBefore=" + isBiledBefore + "]";
	}

	

	
	
}