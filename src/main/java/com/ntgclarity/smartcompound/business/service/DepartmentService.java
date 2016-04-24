package com.ntgclarity.smartcompound.business.service;

import java.util.List;

import com.ntgclarity.smartcompound.common.entity.Department;

public interface DepartmentService {
	List<Department> getAllDepartments();

	Department getDepartment(Long id);

	Department saveOrUpdateDepartment(Department department);

}
