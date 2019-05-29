package com.ntgclarity.smartcompound.dataaccess.dao;

import java.util.List;
import java.util.Map;

import com.ntgclarity.smartcompound.common.entity.Employee;
import com.ntgclarity.smartcompound.common.entity.Menu;

public interface MenuDAO {
	
	List<Menu> getAllMenus();

	Menu getMenu(Long id);

	Menu insertMenu(Menu menu);

	Menu updateMenu(Menu menu);
	
	List<Menu> loadMenus(int first, int pageSize, String sortField,
			boolean ascending, Map<String, Object> filters);

	int getNumOfMenusRows(Map<String, Object> filters);
	
	Menu getMenuByMenuName(String menuName,Long id);


}
