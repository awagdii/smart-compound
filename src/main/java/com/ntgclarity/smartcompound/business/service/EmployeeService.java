package com.ntgclarity.smartcompound.business.service;

import java.util.List;
import java.util.Map;

import com.ntgclarity.smartcompound.common.entity.Compound;
import com.ntgclarity.smartcompound.common.entity.Employee;
import com.ntgclarity.smartcompound.common.entity.Group;
import com.ntgclarity.smartcompound.common.entity.MenuItem;
import com.ntgclarity.smartcompound.common.entity.Ticket;

/** Author: Nazer **/

public interface EmployeeService {

	List<Employee> getAllEmployees();

	Employee getEmployee(Long id);

	/** START HEBA'S WORK **/
	Employee insertEmployee(Employee employee);

	/** END HEBA'S WORK **/
	List<Employee> loadEmployees(int first, int pageSize, String sortField,
			boolean ascending, Map<String, Object> filters);

	// Added by reda
	List<Group> getEmployeeGroups(Employee employee);

	void insertEmployeeGroups(Employee employee, List<Group> groups);

	// End of reda's work

	int getNumOfEmployeesRows(Map<String, Object> filters);

	// Added by Hend
	Employee findEmployeeByUsername(String username);

	// End of Hend's work
	int resetPassword(String oldPssword, String newPassword, Employee emp);

	List<Employee> getEmployeesInGroup(Compound compound, Group group);

	void deleteEmployeesFromGroup(Compound compound, Group group,
			List<Employee> employeeList);

	void addEmployeesToGroup(Compound compound, Group group,
			List<Employee> employeeList);

	List<Employee> getAllEmployeesInCompound(Compound compound);


}
