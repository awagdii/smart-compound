package com.ntgclarity.smartcompound.dataaccess.daoimpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ntgclarity.smartcompound.common.entity.Compound;
import com.ntgclarity.smartcompound.common.entity.Group;
import com.ntgclarity.smartcompound.common.entity.GroupMenuItem;
import com.ntgclarity.smartcompound.common.entity.MenuItem;
import com.ntgclarity.smartcompound.dataaccess.base.BaseDAO;
import com.ntgclarity.smartcompound.dataaccess.dao.GroupDAO;

@Repository
public class GroupDAOImpl extends BaseDAO implements GroupDAO {

	@Override
	public List<Group> getAllGroups() {

		return (List<Group>) super.getAll(Group.class);
	}

	@Override
	public Group getGroup(Long id) {
		return (Group) super.get(Group.class, id);
	}

	@Override
	public Group updateGroup(Group group) {
		// TODO Auto-generated method stub
		return (Group) super.saveOrUpdate(group);
	}

	/**
	 * methodCreater:nessma create Group this metohd will call saveOrUpdate() in
	 * the super class BaseDAO
	 **/
	@Override
	public Group insertGroup(Group group) {

		return (Group) super.saveOrUpdate(group);

	}

	@Override
	public List<Group> loadGroups(int first, int pageSize, String sortField,
			boolean ascending, Map<String, Object> filters) {

		return super.load(Group.class, first, pageSize, sortField, ascending,
				filters);
	}

	@Override
	public int getNumOfGroupsRows(Map<String, Object> filters) {

		return super.getNumOfRows(Group.class, filters);
	}

	@Override
	public List<MenuItem> getGroupMenuItem(Group group) {
		// TODO Auto-generated method stub

		Criteria myCriteria = getCurrentSession().createCriteria(
				GroupMenuItem.class).add(Restrictions.eq("group", group));
		Iterator res = myCriteria.list().iterator();

		List<MenuItem> menuItems = new ArrayList<>();
		while (res.hasNext()) {
			GroupMenuItem groupMenuItem = (GroupMenuItem) res.next();

			menuItems.add(groupMenuItem.getMenuItem());
//			System.out.println(groupMenuItem.getMenuItem().getName());
		}

		return menuItems;
	}

	@Override
	public void insertGroupMenuItem(Group group, List<MenuItem> items) {
		// TODO Auto-generated method stub
		List<MenuItem> oldMenuItems = getGroupMenuItem(group);
		if (oldMenuItems.isEmpty()) {
			for (int i = 0; i < items.size(); i++) {
				GroupMenuItem gmi = new GroupMenuItem();
				gmi.setGroup(group);
				gmi.setMenuItem(items.get(i));
				getCurrentSession().save(gmi);
			}
		} else {
			String hql = "delete from GroupMenuItem where group_id= :groupId";
			getCurrentSession().createQuery(hql)
					.setLong("groupId", group.getId()).executeUpdate();

			for (int i = 0; i < items.size(); i++) {
				GroupMenuItem gmi = new GroupMenuItem();
				gmi.setGroup(group);
				gmi.setMenuItem(items.get(i));
				getCurrentSession().save(gmi);

			}
		}
	}

	@Override
	public List<Group> getCompoundGroups(Compound compound) {

		String hql = "from Group where   compound =:compoundId";

		return getCurrentSession().createQuery(hql)
				.setParameter("compoundId", compound).list();
	}

	@Override
	public List<Group> getAllGroupsByCompound(Compound selectedCompound) {
		String hql = "from Group where compound =:compound";
		return getCurrentSession().createQuery(hql)
				.setParameter("compound", selectedCompound).list();
	}

}
