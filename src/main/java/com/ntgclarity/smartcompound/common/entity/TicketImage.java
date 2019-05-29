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

/**
*
* @author Karim M.Fadel
*/

@Entity
@Table(name = "ng_nts_ticket_image")
public class TicketImage  extends BaseEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6344103690626600369L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "ng_nts_ticket_image_recid_seq")
	@SequenceGenerator(name = "ng_nts_ticket_image_recid_seq", sequenceName = "ng_nts_ticket_image_recid_seq")
	@Column(name = "recid")
	private Long id;	
	@Column (name="image_url")
	private String imageUrl;
	@ManyToOne
	@JoinColumn(name = "ticket_id", referencedColumnName = "recid")
	private Ticket ticket;

	@Override
	public Long getId() {
		return id;
	}
	@Override
	public void setId(Long id) {
		this.id = id;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public Ticket getTicket() {
		return ticket;
	}
	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}
	

}
