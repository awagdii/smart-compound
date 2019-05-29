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
@Table(name = "ng_nts_employee_groups")
public class EmployeeGroup extends BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "my_gen",strategy =GenerationType.SEQUENCE )
	@SequenceGenerator(name = "my_gen", sequenceName = "ng_nts_employee_groups_recid_seq")
	@Column(name = "recid")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "group_id", referencedColumnName = "recid")
	private Group group;
	
	@ManyToOne
	@JoinColumn(name = "employee_id", referencedColumnName = "recid")
	private Employee employee;

	
	
	
	public Group getGroup() {
		return group;
	}

	/**
	 * @param group
	 */
	public void setGroup(Group group) {
		this.group = group;
	}



	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	

	


}
