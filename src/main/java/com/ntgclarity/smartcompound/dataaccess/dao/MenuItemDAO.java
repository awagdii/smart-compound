package com.ntgclarity.smartcompound.dataaccess.dao;

import java.util.List;
import java.util.Map;

import com.ntgclarity.smartcompound.common.entity.Employee;
import com.ntgclarity.smartcompound.common.entity.Menu;
import com.ntgclarity.smartcompound.common.entity.MenuItem;

public interface MenuItemDAO {
	
	List<MenuItem> getAllMenuItems();

	MenuItem getMenuItem(Long id);

	MenuItem insertMenuItem(MenuItem menuItem);

	MenuItem updateMenuItem(MenuItem menuItem);
	
	List<MenuItem> loadMenuItems(int first, int pageSize, String sortField,
			boolean ascending, Map<String, Object> filters);

	int getNumOfMenuItemsRows(Map<String, Object> filters);

	List<MenuItem> getAllEmployeeMenuItems(Employee employee);
	List<MenuItem> getAllMenuItemsAfterCheckSuperAdminFlag(boolean isSuperAdmin);

	MenuItem getMenuItemByName(String menuName,Long id);
}
