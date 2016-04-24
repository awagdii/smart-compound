package com.ntgclarity.smartcompound.dataaccess.dao;
import java.util.List;
import com.ntgclarity.smartcompound.common.entity.Department;

public interface DepartmentDAO {
	List<Department> getAllDepartments();

	Department getDepartment(Long id);

	Department saveOrUpdateDepartment(Department department);
	
	
}
