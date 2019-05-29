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
@Table(name = "ng_nts_tickets_histories")
public class TicketHistory extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_gen")
	@SequenceGenerator(name = "my_gen", sequenceName = "ng_nts_ticket_histories_recid_seq")
	@Column(name = "recid")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "compound_id", referencedColumnName = "recid")
	private Compound compound;
	@ManyToOne
	@JoinColumn(name = "ticket_id", referencedColumnName = "recid")
	private Ticket ticket;

	@Column(name = "action_date")
	private Date actionDate;

	@Column(name = "comment")
	private String comment;

	@Column(name = "previous_status")
	private String previousStatus;

	@Column(name = "currenet_status")
	private String currentStatus;
	@ManyToOne
	@JoinColumn(name = "action_by", referencedColumnName = "recid")
	private Employee actionBy;
	@ManyToOne
	@JoinColumn(name = "opened_by", referencedColumnName = "recid")
	private Employee openedBy;
	@Column(name = "is_completed")
	private Integer isCompleted;

	public Compound getCompound() {
		return compound;
	}

	public void setCompound(Compound compound) {
		this.compound = compound;
	}

	public Date getActionDate() {
		return actionDate;
	}

	public void setActionDate(Date actionDate) {
		this.actionDate = actionDate;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getPreviousStatus() {
		return previousStatus;
	}

	public void setPreviousStatus(String previousStatus) {
		this.previousStatus = previousStatus;
	}

	public String getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	public Employee getActionBy() {
		return actionBy;
	}

	public void setActionBy(Employee actionBy) {
		this.actionBy = actionBy;
	}

	public Employee getOpenedBy() {
		return openedBy;
	}

	public void setOpenedBy(Employee openedBy) {
		this.openedBy = openedBy;
	}

	public Integer getIsCompleted() {
		return isCompleted;
	}

	public void setIsCompleted(Integer isCompleted) {
		this.isCompleted = isCompleted;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "TicketHistory [id=" + id + ", compound=" + compound
				+ ", ticket=" + ticket + ", actionDate=" + actionDate
				+ ", comment=" + comment + ", previousStatus=" + previousStatus
				+ ", currentStatus=" + currentStatus + ", actionBy=" + actionBy
				+ ", openedBy=" + openedBy + ", isCompleted=" + isCompleted
				+ "]";
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

}
