package com.ntgclarity.smartcompound.business.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntgclarity.smartcompound.business.service.EmployeeService;
import com.ntgclarity.smartcompound.common.entity.Compound;
import com.ntgclarity.smartcompound.common.entity.Employee;
import com.ntgclarity.smartcompound.common.entity.Group;
import com.ntgclarity.smartcompound.dataaccess.dao.EmployeeDAO;

/**Author: Nazer**/

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDAO employeeDAO;
	
	@Override
	public List<Employee> getAllEmployees() {
		return employeeDAO.getAllEmployees();
	}

	@Override
	public Employee getEmployee(Long id) {
		if(id !=null)
		{
			return employeeDAO.getEmployee(id);
		}
		return null;
	}
	
	/**START HEBA'S WORK**/
	public Employee insertEmployee(Employee employee){
		return employeeDAO.insertEmployee(employee);
	}
	/**END HEBA'S WORK**/

	@Override
	public List<Employee> loadEmployees(int first, int pageSize, String sortField,
			boolean ascending, Map<String, Object> filters) {
		return employeeDAO.loadEmployees(first,pageSize,sortField,ascending,filters);
	}

	@Override
	public int getNumOfEmployeesRows(Map<String, Object> filters) {
		return  employeeDAO.getNumOfEmployeesRows(filters);
	}

	//Added by Hend
	@Override
	public Employee findEmployeeByUsername(String username) {
		return employeeDAO.getEmployeeByUsername(username);
	}
	//End of Hend's work

	@Override
	public int resetPassword(String oldPssword, String newPassword,
			Employee emp) {
		if(emp.getPassword().equals(oldPssword)){
			emp.setPassword(newPassword);
			employeeDAO.insertEmployee(emp);
			return 1;
		}else{
			return 0;	
		}
	}

	@Override
	public List<Group> getEmployeeGroups(Employee employee) {
		// TODO Auto-generated method stub
		return employeeDAO.getEmployeeGroups(employee);
	}

	@Override
	public void insertEmployeeGroups(Employee employee, List<Group> groups) {
		// TODO Auto-generated method stub
		employeeDAO.insertEmployeeGroups(employee, groups);
	}

	@Override
	public List<Employee> getEmployeesInGroup(Compound compound, Group group) {
		// TODO Auto-generated method stub
		return employeeDAO.getEmployeesInGroup(compound, group);
	}

	@Override
	public void deleteEmployeesFromGroup(Compound compound, Group group,
			List<Employee> employeeList) {
		// TODO Auto-generated method stub
		employeeDAO.deleteEmployeesFromGroup(compound, group, employeeList);
	}

	@Override
	public void addEmployeesToGroup(Compound compound, Group group,
			List<Employee> employeeList) {
		// TODO Auto-generated method stub
		employeeDAO.addEmployeesToGroup(compound, group, employeeList);
		
	}

	@Override
	public List<Employee> getAllEmployeesInCompound(Compound compound) {
		// TODO Auto-generated method stub
		return employeeDAO.getAllEmployeesInCompound(compound);
	}

}
