package com.ntgclarity.smartcompound.dataaccess.daoimpl;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ntgclarity.smartcompound.common.entity.Employee;
import com.ntgclarity.smartcompound.common.entity.Menu;
import com.ntgclarity.smartcompound.common.entity.Order;
import com.ntgclarity.smartcompound.dataaccess.base.BaseDAO;
import com.ntgclarity.smartcompound.dataaccess.dao.MenuDAO;

@Repository
public class MenuDAOImpl extends BaseDAO implements MenuDAO {

	@Override
	public List<Menu> getAllMenus() {

		return (List<Menu>) super.getAll(Menu.class);
	}

	@Override
	public Menu getMenu(Long id) {
		return  (Menu) super.get(Menu.class , id);
	}
	@Override
	public Menu updateMenu(Menu menu) {
		// TODO Auto-generated method stub
		return (Menu) super.saveOrUpdate(menu);
	}

	@Override
	public Menu insertMenu(Menu menu) {
		
		return (Menu) super.saveOrUpdate(menu);
		
		
	}

	@Override
	public List<Menu> loadMenus(int first, int pageSize, String sortField,
			boolean ascending, Map<String, Object> filters) {
		// TODO Auto-generated method stub
		 return super.load(Menu.class,first,pageSize,sortField,ascending,filters);
	}

	@Override
	public int getNumOfMenusRows(Map<String, Object> filters) {
		// TODO Auto-generated method stub
		return super.getNumOfRows(Menu.class, filters);
	}

	@Override
	public Menu getMenuByMenuName(String menuName, Long id) {
		Query query = getCurrentSession().createQuery(
				"from " + Menu.class.getCanonicalName()
						+ " x where x.name =:menuName and x.id!=:id");
		query.setString("menuName", menuName);
		query.setLong("id", id);
		return (Menu) query.uniqueResult();
	}
	
}
