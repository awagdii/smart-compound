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
@Table(name = "ng_nts_system_configuration")
public class SystemConfiguration extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE ,generator="ng_nts_system_configuration_id_seq")
	@SequenceGenerator(name="ng_nts_system_configuration_id_seq", sequenceName="ng_nts_system_configuration_id_seq")
	@Column(name = "id")
	private Long id;
	
	@Column(name = "key")
	private String key;
	
	@Column(name = "value")
	private String value;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "SystemConfiguration [id=" + id + ", key=" + key + ", value="
				+ value + "]";
	}

	

}
