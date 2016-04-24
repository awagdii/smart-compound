package com.ntgclarity.smartcompound.dataaccess.daoimpl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ntgclarity.smartcompound.common.entity.Department;
import com.ntgclarity.smartcompound.common.entity.Employee;
import com.ntgclarity.smartcompound.dataaccess.base.BaseDAO;
import com.ntgclarity.smartcompound.dataaccess.dao.DepartmentDAO;


@Repository
public class DepartmentDAOImpl extends BaseDAO implements DepartmentDAO {

	@Override
	public List<Department> getAllDepartments() {
		return (List<Department>)super.getAll(Department.class);
	}

	@Override
	public Department getDepartment(Long id) {
		return (Department) super.get(Department.class, id);
	}

	@Override
	public Department saveOrUpdateDepartment(Department department) {
		// TODO Auto-generated method stub
		return (Department) super.saveOrUpdate(department);
	}

}
