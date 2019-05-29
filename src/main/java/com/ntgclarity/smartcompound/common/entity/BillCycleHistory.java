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

/** Author: Mai **/

@Entity
@Table(name = "ng_nts_bill_cycle_history")
public class BillCycleHistory extends BaseEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE ,generator=" ng_nts_bill_cycle_history_recid_seq")
	@SequenceGenerator(name=" ng_nts_bill_cycle_history_recid_seq", sequenceName=" ng_nts_bill_cycle_history_recid_seq")
	@Column(name = "recid")
	private Long id;	
	
	@Column(name="month")
	private Integer month;
	
	@Column(name="year")
	private Integer year;
	
	@Column(name="has_ended")
	private Integer hasEnded=0;
	

	@ManyToOne
	@JoinColumn(name ="compound_id" , referencedColumnName="recid")
	private Compound compound;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Integer getMonth() {
		return month;
	}


	public void setMonth(Integer month) {
		this.month = month;
	}


	public Integer getYear() {
		return year;
	}


	public void setYear(Integer year) {
		this.year = year;
	}


	public Compound getCompound() {
		return compound;
	}


	public void setCompound(Compound compound) {
		this.compound = compound;
	}


	public Integer getHasEnded() {
		return hasEnded;
	}


	public void setHasEnded(Integer hasEnded) {
		this.hasEnded = hasEnded;
	}


	@Override
	public String toString() {
		return "BillCycleHistory [id=" + id + ", month=" + month + ", year="
				+ year + ", hasEnded=" + hasEnded + ", compound=" + compound
				+ "]";
	}





	

}
