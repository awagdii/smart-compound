package com.ntgclarity.smartcompound.dataaccess.daoimpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ntgclarity.smartcompound.common.entity.Compound;
import com.ntgclarity.smartcompound.common.entity.Employee;
import com.ntgclarity.smartcompound.common.entity.EmployeeGroup;
import com.ntgclarity.smartcompound.common.entity.Group;
import com.ntgclarity.smartcompound.common.entity.GroupMenuItem;
import com.ntgclarity.smartcompound.common.entity.MenuItem;
import com.ntgclarity.smartcompound.common.entity.Order;
import com.ntgclarity.smartcompound.dataaccess.base.BaseDAO;
import com.ntgclarity.smartcompound.dataaccess.dao.EmployeeDAO;

/** Author: Nazer **/
@Repository
public class EmployeeDAOImpl extends BaseDAO implements EmployeeDAO {

	@Override
	public List<Employee> getAllEmployees() {

		return (List<Employee>) super.getAll(Employee.class);
	}

	@Override
	public Employee getEmployee(Long id) {
		return (Employee) super.get(Employee.class, id);
	}

	/** START HEBA'S WORK **/
	public Employee insertEmployee(Employee employee) {
		return (Employee) super.saveOrUpdate(employee);
	}

	/** END HEBA'S WORK **/

	@Override
	public List<Employee> loadEmployees(int first, int pageSize,
			String sortField, boolean ascending, Map<String, Object> filters) {
		return super.load(Employee.class, first, pageSize, sortField,
				ascending, filters);
	}

	@Override
	public int getNumOfEmployeesRows(Map<String, Object> filters) {
		return super.getNumOfRows(Employee.class, filters);
	}

	// Added by Hend
	@Override
	public Employee getEmployeeByUsername(String username) {
		Query query = getCurrentSession().createQuery(
				"from " + Employee.class.getCanonicalName()
						+ " x where x.username =:username");
		query.setParameter("username", username);
		return (Employee) query.uniqueResult();
	}

	// End of Hend's work

	// Added by reda
	@Override
	public List<Group> getEmployeeGroups(Employee employee) {
		Criteria myCriteria = getCurrentSession().createCriteria(
				EmployeeGroup.class).add(Restrictions.eq("employee", employee));
		Iterator res = myCriteria.list().iterator();

		List<Group> groups = new ArrayList<>();
		while (res.hasNext()) {
			EmployeeGroup employeeGroup = (EmployeeGroup) res.next();

			groups.add(employeeGroup.getGroup());
//			System.out.println(employeeGroup.getGroup().getGroupName());
		}
		return groups;
	}

	@Override
	public void insertEmployeeGroups(Employee employee, List<Group> groups) {
		List<Group> oldGroups = getEmployeeGroups(employee);
		if (oldGroups.isEmpty()) {
			for (int i = 0; i < groups.size(); i++) {
				EmployeeGroup emg = new EmployeeGroup();
				emg.setEmployee(employee);
//				System.out.println("group are :" + groups.get(i).toString());
				emg.setGroup(groups.get(i));
				getCurrentSession().save(emg);
			}
		} else {
			String hql = "delete from EmployeeGroup where employee_id= :employeeId";
			getCurrentSession().createQuery(hql)
					.setEntity("employeeId", employee).executeUpdate();
//			System.out.println("after delete");
			for (int i = 0; i < groups.size(); i++) {
				EmployeeGroup emg = new EmployeeGroup();
				emg.setEmployee(employee);
//				System.out.println("group are :" + groups.get(i).toString());
				emg.setGroup(groups.get(i));
				getCurrentSession().save(emg);

			}
		}

	}

	// End of reda's work

	@Override
	public List<Employee> getEmployeesInGroup(Compound compound, Group group) {
		// TODO Auto-generated method stub

		String hql = "select employee from EmployeeGroup eg where  eg.group = :groupId AND employee_id IN ( from Employee where compound_id =:compoundId)";
		return getCurrentSession().createQuery(hql).setEntity("groupId", group)
				.setEntity("compoundId", compound).list();
	}

	@Override
	public void deleteEmployeesFromGroup(Compound compound, Group group,
			List<Employee> employeeList) {
		// TODO Auto-generated method stub

		for (Employee employee : employeeList) {
			String hql = "delete from EmployeeGroup where employee_id= :employeeId AND group_id = :groupId AND employee_id IN ( from Employee where compound_id =:compoundId)";
			getCurrentSession().createQuery(hql)
					.setEntity("employeeId", employee)
					.setEntity("groupId", group)
					.setEntity("compoundId", compound).executeUpdate();
		}

	}

	@Override
	public void addEmployeesToGroup(Compound compound, Group group,
			List<Employee> employeeList) {
		// TODO Auto-generated method stub
		for (Employee employee : employeeList) {
			EmployeeGroup employeeGroup = new EmployeeGroup();
			employeeGroup.setEmployee(employee);
			employeeGroup.setGroup(group);
			getCurrentSession().save(employeeGroup);
		}

	}

	@Override
	public List<Employee> getAllEmployeesInCompound(Compound compound) {
		String hql = "from Employee where compound_id =:compoundId";
		return getCurrentSession().createQuery(hql)
				.setEntity("compoundId", compound).list();
	}

}
