package com.ntgclarity.smartcompound.common.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.ntgclarity.smartcompound.common.base.BaseEntity;

@Entity
@Table(name = "ng_nts_ticket_statuses")
public class TicketStatus extends BaseEntity implements Serializable {
	

	/**
	 * 
	 */
	public static final TicketStatus NEW = new TicketStatus(1l);

	public static final  TicketStatus OPENED = new TicketStatus(2l);
	
	private static final long serialVersionUID = 1L;

	@Id
//	@GeneratedValue(strategy = GenerationType.SEQUENCE ,generator="ng_nts_ticket_status_recid_seq")
//	@SequenceGenerator(name="ng_nts_ticket_status_recid_seq", sequenceName="ng_nts_ticket_status_recid_seq")
	@Column(name = "recid")
	private Long id;
	@Column(name = "name")
	private String name;
	public TicketStatus(){
		
	}
	public TicketStatus(Long id) {
		super();
		this.id = id;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
