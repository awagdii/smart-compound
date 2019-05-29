package com.ntgclarity.smartcompound.dataaccess.dao;

import java.util.List;
import java.util.Map;

import com.ntgclarity.smartcompound.common.entity.Compound;
import com.ntgclarity.smartcompound.common.entity.Group;
import com.ntgclarity.smartcompound.common.entity.MenuItem;

/**Author: Nazer**/
public interface GroupDAO {

	List<Group> getAllGroups();

	Group getGroup(Long id);

	Group insertGroup(Group group);

	Group updateGroup(Group group);
	
	List<MenuItem> getGroupMenuItem(Group group);
    void insertGroupMenuItem(Group group, List<MenuItem> items);
	
	List<Group> loadGroups(int first, int pageSize, String sortField,
			boolean ascending, Map<String, Object> filters);

	int getNumOfGroupsRows(Map<String, Object> filters);

	List<Group> getCompoundGroups(Compound compound);

	List<Group> getAllGroupsByCompound(Compound selectedCompound);

}
