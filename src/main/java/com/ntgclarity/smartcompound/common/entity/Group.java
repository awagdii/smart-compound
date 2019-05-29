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

import org.springframework.security.core.GrantedAuthority;

import com.ntgclarity.smartcompound.common.base.BaseEntity;

@Entity
@Table(name = "ng_nts_groups")
public class Group  extends BaseEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Id
	@GeneratedValue(generator = "my_gen")
	@SequenceGenerator(name = "my_gen", sequenceName = "ng_nts_groups_recid_seq")
	@Column(name = "recid")
	private Long id;
	@Column (name="group_name")
	private String groupName;
	
	@ManyToOne
	@JoinColumn(name = "compound_id", referencedColumnName = "recid")
	private Compound compound;
	
	@ManyToOne
	@JoinColumn(name = "created_by", referencedColumnName = "recid")
	private Employee createdBy;
	
	public Compound getCompound() {
		return compound;
	}

	public void setCompound(Compound compound) {
		this.compound = compound;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	@Override
	public String toString() {
		return "Group [id=" + id + ", groupName=" + groupName + "]";
	}

	public Employee getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Employee createdBy) {
		this.createdBy = createdBy;
	}

	
}
