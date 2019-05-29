package com.ntgclarity.smartcompound.business.serviceimpl;

import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntgclarity.smartcompound.business.service.GroupService;
import com.ntgclarity.smartcompound.common.entity.Compound;
import com.ntgclarity.smartcompound.common.entity.Group;
import com.ntgclarity.smartcompound.common.entity.MenuItem;
import com.ntgclarity.smartcompound.dataaccess.dao.GroupDAO;

/**Author: Nazer**/

@Service
public class GroupServiceImpl implements GroupService {

	@Autowired
	private GroupDAO groupDAO;
	
	@Override
	public List<Group> getAllGroups() {
		return groupDAO.getAllGroups();
	}

	@Override
	public Group getGroup(Long id) {
		if(id !=null)
		{
			return groupDAO.getGroup(id);
		}
		return null;
	}
	/**
	 *methodCreater:nessma 
	 *create Group 
	 *this metohd will call groupDAO.insertGroup
	 
	  **/
	@Override
	public Group insertGroup(Group group) {
		
		return groupDAO.insertGroup(group);
	}
	@Override
	public Group updateGroup(Group group) {
		// TODO Auto-generated method stub
		return groupDAO.updateGroup(group);
		
	}

	@Override
	public List<Group> loadGroups(
			int first, int pageSize, String sortField, boolean ascending,
			Map<String, Object> filters) {
	return groupDAO.loadGroups(first,pageSize,sortField,ascending,filters);

	}

	@Override
	public int getNumOfGroupsRows(Map<String, Object> filters) {
		return  groupDAO.getNumOfGroupsRows(filters);
	}

	@Override
	public List<MenuItem> getGroupMenuItem(Group group) {
		// TODO Auto-generated method stub
		return groupDAO.getGroupMenuItem(group);
	}

	@Override
	public void insertGroupMenuItem(Group group, List<MenuItem> items) {
		// TODO Auto-generated method stub
		groupDAO.insertGroupMenuItem(group, items);
	}

	@Override
	public List<Group> getCompoundGroups(Compound compound) {
		// TODO Auto-generated method stub
		return groupDAO.getCompoundGroups(compound);
	}

	@Override
	public List<Group> getAllGroupsByCompound(Compound selectedCompound) {
		return groupDAO.getAllGroupsByCompound(selectedCompound);
	}




}
