package com.ntgclarity.smartcompound.business.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntgclarity.smartcompound.business.service.MenuItemService;
import com.ntgclarity.smartcompound.common.entity.Employee;
import com.ntgclarity.smartcompound.common.entity.MenuItem;
import com.ntgclarity.smartcompound.dataaccess.dao.MenuItemDAO;

@Service
public class MenuItemServiceImpl implements MenuItemService{
	
	@Autowired
	MenuItemDAO menuItemDAO;

	@Override
	public List<MenuItem> getAllMenuItems() {
		// TODO Auto-generated method stub
		return menuItemDAO.getAllMenuItems();
	}

	@Override
	public MenuItem getMenuItem(Long id) {
		// TODO Auto-generated method stub
		if(id !=null)
		{
			return menuItemDAO.getMenuItem(id);
		}
		return null;
	}

	@Override
	public MenuItem insertMenuItem(MenuItem menuItem) {
		// TODO Auto-generated method stub
		return menuItemDAO.insertMenuItem(menuItem);
	}

	@Override
	public MenuItem updateMenuItem(MenuItem menuItem) {
		// TODO Auto-generated method stub
		return menuItemDAO.updateMenuItem(menuItem);
	}

	@Override
	public List<MenuItem> loadMenuItems(int first, int pageSize,
			String sortField, boolean ascending, Map<String, Object> filters) {
		// TODO Auto-generated method stub
		return menuItemDAO.loadMenuItems(first, pageSize, sortField, ascending, filters);
	}

	@Override
	public int getNumOfMenuItemsRows(Map<String, Object> filters) {
		// TODO Auto-generated method stub
		return menuItemDAO.getNumOfMenuItemsRows(filters);
	}

	@Override
	public List<MenuItem> getAllEmployeeMenuItems(Employee employee) {
		// TODO Auto-generated method stub
		return menuItemDAO.getAllEmployeeMenuItems(employee);
	}

	@Override
	public List<MenuItem> getAllMenuItemsAfterCheckSuperAdminFlag(
			boolean isSuperAdmin) {
		// TODO Auto-generated method stub
		return menuItemDAO.getAllMenuItemsAfterCheckSuperAdminFlag(isSuperAdmin);
	}

	@Override
	public MenuItem getMenuItemByName(String name, Long id) {
		// TODO Auto-generated method stub
		return menuItemDAO.getMenuItemByName(name, id);
	}

}
