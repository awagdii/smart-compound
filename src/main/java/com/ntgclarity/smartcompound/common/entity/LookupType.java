package com.ntgclarity.smartcompound.common.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ntgclarity.smartcompound.common.base.BaseEntity;

@Entity
@Table(name = "ng_nts_lookup_types")
public class LookupType extends BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8041531328826352542L;
	
	public final static LookupType GENERAL_STATUS = new LookupType(3l);
	public final static LookupType SERVICE_STATUS = new LookupType(2l);
	public final static LookupType MEASUREMENT_UNITES = new LookupType(1l);
	public final static LookupType SALUTATION = new LookupType(4l);
	public final static LookupType GENDER = new LookupType(5l);
	/**START HEBA WORK**/
	public final static LookupType IDENTIFICATION_TYPE = new LookupType(7l);
	/**END HEBA WORK**/
	public final static LookupType FACILITY_TYPE = new LookupType(6l);
	public final static LookupType FACILITY_STATUS = new LookupType(11l);
	public final static LookupType ORDER_STATUS = new LookupType(8l);
	public final static LookupType BEST_CONNECTION_METHOD = new LookupType(9l);
	public final static LookupType EMPLOYEE_STATUS = new LookupType(10l);

	
			
			
	public LookupType() {
	}
	public LookupType(Long id) {
		super();
		this.id = id;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "recid")
	private Long id;
	@Column(name="type_name")
	private String typeName;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	@Override
	public String toString() {
		return "LookupType [id=" + id + ", typeName=" + typeName + "]";
	}
	

}
