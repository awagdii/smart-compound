package com.ntgclarity.smartcompound.business.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntgclarity.smartcompound.business.service.MenuService;
import com.ntgclarity.smartcompound.common.entity.Menu;
import com.ntgclarity.smartcompound.dataaccess.dao.MenuDAO;

@Service
public class MenuServiceImpl implements MenuService{
	
	@Autowired
	MenuDAO menuDAO;

	@Override
	public List<Menu> getAllMenus() {
		// TODO Auto-generated method stub
		return menuDAO.getAllMenus();
	}

	@Override
	public Menu getMenu(Long id) {
		// TODO Auto-generated method stub
		if(id !=null)
		{
			return menuDAO.getMenu(id);
		}
		return null;
	}

	@Override
	public Menu insertMenu(Menu menu) {
		// TODO Auto-generated method stub
		return menuDAO.insertMenu(menu);
	}

	@Override
	public Menu updateMenu(Menu menu) {
		// TODO Auto-generated method stub
		return menuDAO.updateMenu(menu);
	}

	@Override
	public List<Menu> loadMenus(int first, int pageSize, String sortField,
			boolean ascending, Map<String, Object> filters) {
		// TODO Auto-generated method stub
		return menuDAO.loadMenus(first, pageSize, sortField, ascending, filters);
	}

	@Override
	public int getNumOfMenusRows(Map<String, Object> filters) {
		// TODO Auto-generated method stub
		return menuDAO.getNumOfMenusRows(filters);
	}

	@Override
	public Menu getMenuByMenuName(String menuName, Long id) {
		// TODO Auto-generated method stub
		return menuDAO.getMenuByMenuName(menuName, id);
	}

}
