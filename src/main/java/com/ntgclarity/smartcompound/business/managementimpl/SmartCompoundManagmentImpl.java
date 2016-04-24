package com.ntgclarity.smartcompound.business.managementimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntgclarity.smartcompound.business.management.SmartCompoundManagment;
import com.ntgclarity.smartcompound.business.service.DepartmentService;
import com.ntgclarity.smartcompound.business.service.EmployeeService;
import com.ntgclarity.smartcompound.common.entity.Department;
import com.ntgclarity.smartcompound.common.entity.Employee;

@Service
public class SmartCompoundManagmentImpl implements SmartCompoundManagment{

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private DepartmentService departmentService;
	
	
	@Override
	public List<Employee> getAllEmployees() {
		return employeeService.getAllEmployees();
	}


	@Override
	public Employee getEmployee(Long id) {
		return employeeService.getEmployee(id);
	}


	@Override
	public void saveOrUpdateEmployee(Employee employee) {
		// TODO Auto-generated method stub	
		employeeService.saveOrUpdateEmployee(employee);
		
	}


	@Override
	public List<Department> getAllDepartments() {
		// TODO Auto-generated method stub
		return departmentService.getAllDepartments();
	}


	@Override
	public Department getDepartment(Long id) {
		// TODO Auto-generated method stub
		return departmentService.getDepartment(id);
	}

	@Override
	public Department saveOrUpdateDepartment(Department department) {
		// TODO Auto-generated method stub
		return departmentService.saveOrUpdateDepartment(department);
	}

}
