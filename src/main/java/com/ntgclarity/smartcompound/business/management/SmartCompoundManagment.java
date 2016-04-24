package com.ntgclarity.smartcompound.business.management;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ntgclarity.smartcompound.common.entity.Department;
import com.ntgclarity.smartcompound.common.entity.Employee;

@Service
public interface SmartCompoundManagment {

	List<Employee> getAllEmployees();

	Employee getEmployee(Long id);

	void saveOrUpdateEmployee(Employee employee);

	List<Department> getAllDepartments();

	Department getDepartment(Long id);

	Department saveOrUpdateDepartment(Department department);

}
