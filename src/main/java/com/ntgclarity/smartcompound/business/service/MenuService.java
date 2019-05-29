package com.ntgclarity.smartcompound.business.service;

import java.util.List;
import java.util.Map;

import com.ntgclarity.smartcompound.common.entity.Menu;
import com.ntgclarity.smartcompound.common.entity.Order;

public interface MenuService {
	
	List<Menu> getAllMenus();

	Menu getMenu(Long id);

	Menu insertMenu(Menu menu);

	Menu updateMenu(Menu menu);
	
	List<Menu> loadMenus(int first, int pageSize, String sortField,
			boolean ascending, Map<String, Object> filters);

	int getNumOfMenusRows(Map<String, Object> filters);
	
    Menu getMenuByMenuName(String menuName, Long id);

}
