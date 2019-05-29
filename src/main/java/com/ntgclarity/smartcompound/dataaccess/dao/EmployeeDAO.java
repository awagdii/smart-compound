package com.ntgclarity.smartcompound.dataaccess.dao;

import java.util.List;
import java.util.Map;

import com.ntgclarity.smartcompound.common.entity.Compound;
import com.ntgclarity.smartcompound.common.entity.Employee;
import com.ntgclarity.smartcompound.common.entity.Group;

/** Author: Nazer **/
public interface EmployeeDAO {

	List<Employee> getAllEmployees();

	Employee getEmployee(Long id);

	/** START HEBA'S WORK **/
	Employee insertEmployee(Employee employee);

	/** END HEBA'S WORK **/

	List<Employee> loadEmployees(int first, int pageSize, String sortField,
			boolean ascending, Map<String, Object> filters);

	int getNumOfEmployeesRows(Map<String, Object> filters);

	// Added by Hend
	Employee getEmployeeByUsername(String username);

	// End of Hend's work

	List<Employee> getEmployeesInGroup(Compound compound, Group group);

	void deleteEmployeesFromGroup(Compound compound, Group group,
			List<Employee> employeeList);

	void addEmployeesToGroup(Compound compound, Group group,
			List<Employee> employeeList);

	// Added by reda
	List<Group> getEmployeeGroups(Employee employee);

	void insertEmployeeGroups(Employee employee, List<Group> groups);

	// End of reda's work

	List<Employee> getAllEmployeesInCompound(Compound compound);


}
