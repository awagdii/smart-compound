package com.ntgclarity.smartcompound.common.entity;

import java.io.Serializable;




//import java.util.Date;

//import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.ntgclarity.smartcompound.common.base.BaseEntity;

@Entity
@Table(name = "ng_nts_tickets")
public class Ticket extends BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManyToOne
	@JoinColumn(name = "compound_id", referencedColumnName = "recid")
	private Compound compound;
	
	
	@ManyToOne
	@JoinColumn(name = "channel_lookup_id", referencedColumnName = "recid")
	private Lookup channelLookup;
	
	
	@Id

	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="ng_nts_tickets_recid_seq")
	@SequenceGenerator(name="ng_nts_tickets_recid_seq", sequenceName="ng_nts_tickets_recid_seq")
	@Column(name = "recid")
	private Long id;
	
	@Column(name = "ticket_subject")
	private String ticketSubject;
	
	@Column(name = "problem_date")
	private Date problemDate;
	
	@Column(name = "last_status")
	private String lastStatus;
	
	@Column(name = "feedback")
	private String feedback;
	// @Column(name="ticket_id")
	// private Integer ticketId;

	@Column(name = "open_date")
	private Date openDate;

	@ManyToOne
	@JoinColumn(name = "service_id", referencedColumnName = "recid")
	private Service service;
	
	
	@Column(name = "service_name")
	private String serviceName;

	@ManyToOne
	@JoinColumn(name = "related_tenant_id", referencedColumnName = "recid")
	private Tenant relatedTenant;

	@ManyToOne
	@JoinColumn(name = "opened_by", referencedColumnName = "recid")
	private Employee openedBy;

	@Column(name = "description")
	private String description;

	@Column(name = "severity")
	private Integer severity;

	@ManyToOne
	@JoinColumn(name = "facility_id", referencedColumnName = "recid")
	private Facility facility;

	@Column(name = "channel")
	private String channel;

	@ManyToOne
	@JoinColumn(name = "assigned_group_id", referencedColumnName="recid")
	private Group assignedGroup;

	@Column(name = "rate")
	private Double rate;
	
	
	@ManyToOne
	@JoinColumn(name="ticket_last_status_id" , referencedColumnName="recid")
	private TicketStatus ticketStatus;
	
	@Column(name = "is_facility_based")
	private Integer isFacilityBased =0;
	

	@Column(name = "group_name")
	private String groupName;
	
	
	@OneToMany(mappedBy="ticket" , fetch=FetchType.EAGER)
	private List<TicketImage> images;      

	public String getChannel() { 
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;

	}


	public String getTicketSubject() {
		return ticketSubject;
	}

	public void setTicketSubject(String ticketSubject) {
		this.ticketSubject = ticketSubject;
	}

	public Date getProblemDate() {
		return problemDate;
	}

	public void setProblemDate(Date problemDate) {
		this.problemDate = problemDate;
	}

	public String getLastStatus() {
		return lastStatus;
	}

	public void setLastStatus(String lastStatus) {
		this.lastStatus = lastStatus;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public Employee getOpenedBy() {
		return openedBy;
	}

	public void setOpenedBy(Employee openedBy) {
		this.openedBy = openedBy;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getSeverity() {
		return severity;
	}

	public void setSeverity(Integer severity) {
		this.severity = severity;
	}




	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	

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

	public Tenant getRelatedTenant() {
		return relatedTenant;
	}

	public void setRelatedTenant(Tenant relatedTenant) {
		this.relatedTenant = relatedTenant;
	}

	public Facility getFacility() {
		return facility;
	}

	public void setFacility(Facility facility) {
		this.facility = facility;
	}

	public Group getAssignedGroup() {
		return assignedGroup;
	}

	public void setAssignedGroup(Group assignedGroup) {
		this.assignedGroup = assignedGroup;
	}

	@Override
	public String toString() {
		return "Ticket [compound=" + compound + ", channelLookup="
				+ channelLookup + ", id=" + id + ", ticketSubject="
				+ ticketSubject + ", problemDate=" + problemDate
				+ ", lastStatus=" + lastStatus + ", feedback=" + feedback
				+ ", openDate=" + openDate + ", service=" + service
				+ ", serviceName=" + serviceName + ", relatedTenant="
				+ relatedTenant + ", openedBy=" + openedBy + ", description="
				+ description + ", severity=" + severity + ", facility="
				+ facility + ", channel=" + channel + ", assignedGroup="
				+ assignedGroup + ", rate=" + rate + ", groupName="
				+ groupName + "]";
	}

	public TicketStatus getTicketStatus() {
		return ticketStatus;
	}

	public void setTicketStatus(TicketStatus ticketStatus) {
		this.ticketStatus = ticketStatus;
	}

	public Integer getIsFacilityBased() {
		return isFacilityBased;
	}

	public void setIsFacilityBased(Integer isFacilityBased) {
		this.isFacilityBased = isFacilityBased;
	}

	

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public List<TicketImage> getImages() {
		return images;
	}

	public void setImages(List<TicketImage> images) {
		this.images = images;
	}

}
