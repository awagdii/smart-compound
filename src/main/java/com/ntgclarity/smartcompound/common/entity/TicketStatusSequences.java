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
@Table(name = "ng_nts_ticket_status_sequences")
public class TicketStatusSequences extends BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ng_nts_ticket_status_sequences_recid_seq")
//	@SequenceGenerator(name = "ng_nts_ticket_status_sequences_recid_seq", sequenceName = "ng_nts_ticket_status_sequences_recid_seq")
	@Column(name = "recid")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "from_ticket_status", referencedColumnName = "recid")
	private TicketStatus fromTicketStatus;

	
	@ManyToOne
	@JoinColumn(name = "to_ticket_status", referencedColumnName = "recid")
	private TicketStatus toTicketStatus;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;

	}

	public TicketStatus getFromTicketStatus() {
		return fromTicketStatus;
	}

	public void setFromTicketStatus(TicketStatus fromTicketStatus) {
		this.fromTicketStatus = fromTicketStatus;
	}

	public TicketStatus getToTicketStatus() {
		return toTicketStatus;
	}

	public void setToTicketStatus(TicketStatus toTicketStatus) {
		this.toTicketStatus = toTicketStatus;
	}



}
