package com.ntgclarity.smartcompound.portal.managedbean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import com.ntgclarity.smartcompound.business.management.SmartCompoundManagment;
import com.ntgclarity.smartcompound.common.entity.Department;
import com.ntgclarity.smartcompound.common.entity.Employee;
import com.ntgclarity.smartcompound.portal.base.BaseBean;

@ManagedBean
@ViewScoped
public class DepartmentBean extends BaseBean implements Serializable {
	@ManagedProperty(value = "#{smartCompoundManagmentImpl}")
	private SmartCompoundManagment smartCompoundManagment;
	private static final long serialVersionUID = 1L;
	private Department department;
	private List<Department> allDepartments;
	
	@PostConstruct
	public void init() {
		loadAllDepartment();
		initiateNewDepartment();
	}
	private void initiateNewDepartment() {
		department = new Department();
		
	}
	private void loadAllDepartment() {
		
		System.out.println("==============>  :"+smartCompoundManagment);
		allDepartments = smartCompoundManagment.getAllDepartments();
		
	}

	
	
	public SmartCompoundManagment getSmartCompoundManagment() {
		return smartCompoundManagment;
	}
	public void setSmartCompoundManagment(
			SmartCompoundManagment smartCompoundManagment) {
		this.smartCompoundManagment = smartCompoundManagment;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public List<Department> getAllDepartments() {
		return allDepartments;
	}
	public void setAllDepartments(List<Department> allDepartments) {
		this.allDepartments = allDepartments;
	}
	
	

}
