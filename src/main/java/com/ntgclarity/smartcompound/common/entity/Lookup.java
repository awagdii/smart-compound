package com.ntgclarity.smartcompound.common.entity;

import java.io.Serializable;

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
@Table(name = "ng_nts_lookup")
public class Lookup  extends BaseEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6344103690626600369L;
	
	public static final Lookup USAGE = new Lookup(13l);
	public static final Lookup FLAT = new Lookup(12l);
	public static final Lookup HOUSE = new Lookup(6l);
	public static final Lookup PLAYGROUND = new Lookup(6l);
	
	public static final Lookup ONE_TIME = new Lookup(14l);
	public static final Lookup ORDER_NEW = new Lookup(18l);
	public static final Lookup ORDER_IN_PROGRESS = new Lookup(19l);
	public static final Lookup ORDER_DONE = new Lookup(20l);
	
	
	//Order status
	public static final Lookup DONE_AND_BILLED = new Lookup(28L);
	public static final Lookup DONE = new Lookup(20L);
	public static final Lookup IN_PROGRESS = new Lookup(19L);
	public static final Lookup NEW = new Lookup(18L);
	
	public static final Lookup ACTIVE = new Lookup(1L);
	public static final Lookup NOT_ACTIVE = new Lookup(2L);
	
	public Lookup() {
		super();
	}
	
	public Lookup(Long id) {
		super();
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "recid")
	private Long id;
	@Column (name="lookup_name")
	private String lookupName;
	@ManyToOne
	@JoinColumn(name = "type_id", referencedColumnName = "recid")
	private LookupType lookupType;
	@Column(name="type_name")
	private String typeName;
	
	
	public LookupType getLookupType() {
		return lookupType;
	}

	public void setLookupType(LookupType lookupType) {
		this.lookupType = lookupType;
	}

	public String getLookupName() {
		return lookupName;
	}

	public void setLookupName(String lookupName) {
		this.lookupName = lookupName;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Override
	public Long getId() {
		
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id=id;
		
	}

	@Override
	public String toString() {
		return "LookUp [id=" + id + ", lookupName=" + lookupName + ", lookupType="
				+ lookupType + ", typeName=" + typeName + "]";
	}
	

}
