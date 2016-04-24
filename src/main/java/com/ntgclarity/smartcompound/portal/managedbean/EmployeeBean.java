package com.ntgclarity.smartcompound.portal.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import com.ntgclarity.smartcompound.business.management.SmartCompoundManagment;
import com.ntgclarity.smartcompound.common.entity.Employee;
import com.ntgclarity.smartcompound.portal.base.BaseBean;

@ManagedBean
@SessionScoped
public class EmployeeBean extends BaseBean implements Serializable {

	@ManagedProperty(value = "#{smartCompoundManagmentImpl}")
	private SmartCompoundManagment smartCompoundManagment;
	private Employee selectedEmployee;
	private static final long serialVersionUID = 1L;
	private List<Employee> allEmployees;

	@PostConstruct
	public void init() {
		loadAllEmployees();
	}

	public void loadAllEmployees() {
		allEmployees = smartCompoundManagment.getAllEmployees();

	}

	public void testMethod() {

		loadAllEmployees();
	}

	public void printEmployee() {
		System.out.println(selectedEmployee);
	}
	
	/**
	 *methodCreater:nessma 
	 *create Employee 
	 *this metohd will call smartCompoundManagment.insertEmployee
	 
	  **/
	public void createEmployee() {
		
//		selectedEmployee= new Employee();
		smartCompoundManagment.saveOrUpdateEmployee(selectedEmployee);
		addInfoMessage("Employee has been created successfully");
	
}

	public void editEmployee() {
		System.out.println("in edit Emp");
		if (selectedEmployee != null) {
			Map<String, Object> options = new HashMap<String, Object>();
			options.put("resizable", false);
			options.put("draggable", false);
			options.put("modal", true);

			RequestContext.getCurrentInstance().openDialog("edit", options,
					null);
		}
	}

	public void updateEmployee() {
		System.err.println("in Update emp");
		smartCompoundManagment.saveOrUpdateEmployee(selectedEmployee);
		loadAllEmployees();
	}
	
	public void initilaizeSelectedEmployee(){
		selectedEmployee= new Employee();
	}

	public List<Employee> getAllEmployees() {
		return allEmployees;
	}

	public void setAllEmployees(List<Employee> allEmployees) {
		this.allEmployees = allEmployees;
	}

	public SmartCompoundManagment getSmartCompoundManagment() {
		return smartCompoundManagment;
	}

	public void setSmartCompoundManagment(
			SmartCompoundManagment smartCompoundManagment) {
		this.smartCompoundManagment = smartCompoundManagment;
	}

	public Employee getSelectedEmployee() {
		return selectedEmployee;
	}

	public void setSelectedEmployee(Employee selectedEmployee) {
		this.selectedEmployee = selectedEmployee;
	}

}
