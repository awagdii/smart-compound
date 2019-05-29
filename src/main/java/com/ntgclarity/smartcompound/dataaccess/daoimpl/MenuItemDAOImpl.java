package com.ntgclarity.smartcompound.dataaccess.daoimpl;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ntgclarity.smartcompound.common.entity.Employee;
import com.ntgclarity.smartcompound.common.entity.GroupMenuItem;
import com.ntgclarity.smartcompound.common.entity.Lookup;
import com.ntgclarity.smartcompound.common.entity.Menu;
import com.ntgclarity.smartcompound.common.entity.MenuItem;
import com.ntgclarity.smartcompound.dataaccess.base.BaseDAO;
import com.ntgclarity.smartcompound.dataaccess.dao.MenuItemDAO;

@Repository
public class MenuItemDAOImpl extends BaseDAO implements MenuItemDAO {

	@Override
	public List<MenuItem> getAllMenuItems() {

		return (List<MenuItem>) super.getAll(MenuItem.class);
	}

	@Override
	public MenuItem getMenuItem(Long id) {
		return (MenuItem) super.get(MenuItem.class, id);
	}

	@Override
	public MenuItem updateMenuItem(MenuItem menuItem) {
		// TODO Auto-generated method stub
		return (MenuItem) super.saveOrUpdate(menuItem);
	}

	@Override
	public MenuItem insertMenuItem(MenuItem menuItem) {

		return (MenuItem) super.saveOrUpdate(menuItem);

	}

	@Override
	public List<MenuItem> loadMenuItems(int first, int pageSize,
			String sortField, boolean ascending, Map<String, Object> filters) {
		// TODO Auto-generated method stub
		return super.load(MenuItem.class, first, pageSize, sortField,
				ascending, filters);
	}

	@Override
	public int getNumOfMenuItemsRows(Map<String, Object> filters) {
		// TODO Auto-generated method stub
		return super.getNumOfRows(MenuItem.class, filters);
	}

	@Override
	public List<MenuItem> getAllEmployeeMenuItems(Employee employee) {
		Query query = getCurrentSession()
				.createQuery(
						"select distinct o.menuItem from GroupMenuItem o where o.menuItem.menu.status =:active and o.menuItem.status =:active   and o.group in (select x.group from EmployeeGroup x where x.employee =:employee) order by o.menuItem.menu.id");
		query.setParameter("employee", employee);
		query.setParameter("active", Lookup.ACTIVE);
		List result = query.list();

		return result;
	}

	@Override
	public List<MenuItem> getAllMenuItemsAfterCheckSuperAdminFlag(
			boolean isSuperAdmin) {
		// TODO Auto-generated method stub
		Query query;
		query = getCurrentSession().createQuery(
				"from MenuItem  where superAdminFlag IS  NULL");
		List result = query.list();
		return result;
	}

	@Override
	public MenuItem getMenuItemByName(String name, Long id) {
		Query query = getCurrentSession().createQuery(
				"from " + MenuItem.class.getCanonicalName()
						+ " x where x.name =:menuName and x.id!=:id");
		query.setString("menuName", name);
		query.setLong("id", id);
		return (MenuItem) query.uniqueResult();
	}

}
