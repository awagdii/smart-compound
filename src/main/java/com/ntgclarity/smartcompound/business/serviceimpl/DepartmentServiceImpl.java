package com.ntgclarity.smartcompound.business.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntgclarity.smartcompound.business.service.DepartmentService;
import com.ntgclarity.smartcompound.common.entity.Department;
import com.ntgclarity.smartcompound.dataaccess.dao.DepartmentDAO;
import com.ntgclarity.smartcompound.dataaccess.daoimpl.DepartmentDAOImpl;

@Service
public class DepartmentServiceImpl  implements DepartmentService{

	
	@Autowired
	DepartmentDAO departmentDAO;
	
	@Override
	public List<Department> getAllDepartments() {
		// TODO Auto-generated method stub
		return departmentDAO.getAllDepartments();
	}

	@Override
	public Department getDepartment(Long id) {
		// TODO Auto-generated method stub
		return departmentDAO.getDepartment(id);
	}

	@Override
	public Department saveOrUpdateDepartment(Department department) {
		// TODO Auto-generated method stub
		return departmentDAO.saveOrUpdateDepartment(department);
	}
	

}
