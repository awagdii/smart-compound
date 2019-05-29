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
@Table(name = "ng_nts_menu")
public class Menu  extends BaseEntity implements Serializable{

	@Id
	@GeneratedValue(generator = "my_gen")
	@SequenceGenerator(name = "my_gen", sequenceName = "menu_recid_seq")
	@Column(name = "recid")
	private Long id;

	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;

	@ManyToOne
	@JoinColumn(name = "status_id", referencedColumnName = "recid")
	private Lookup status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Lookup getStatus() {
		return status;
	}

	public void setStatus(Lookup status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
