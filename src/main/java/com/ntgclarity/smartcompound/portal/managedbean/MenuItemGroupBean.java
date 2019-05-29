package com.ntgclarity.smartcompound.portal.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import com.ntgclarity.smartcompound.business.management.SmartCompoundManagment;
import com.ntgclarity.smartcompound.common.constatnt.MessagesKeys;
import com.ntgclarity.smartcompound.common.entity.Compound;
import com.ntgclarity.smartcompound.common.entity.Group;
import com.ntgclarity.smartcompound.common.entity.MenuItem;
import com.ntgclarity.smartcompound.portal.base.BaseBean;

@ManagedBean
@ViewScoped
public class MenuItemGroupBean extends BaseBean implements Serializable {

	@ManagedProperty(value = "#{smartCompoundManagmentImpl}")
	private SmartCompoundManagment smartCompoundManagment;

	List<Group> allGroup;
	List<MenuItem> allMenuItem;

	Group selectedGroup;
	List<MenuItem> selectedMenuItem;

	Boolean check = true;
	List<MenuItem> selectedItems;

	private List<Compound> compounds;

	Compound selectCompound;

	public void onGroupChange() {
		selectedItems = smartCompoundManagment.getGroupMenuItem(selectedGroup);
	}

	public void onCompoundChange() {
		allGroup = smartCompoundManagment.getCompoundGroups(selectCompound);
		selectedItems = null;
		selectedGroup = null;
	}

	@PostConstruct
	public void init() {
		if (isSuperAdmin()) {
			compounds = smartCompoundManagment.getALLCompounds();
		}
		if (isNotSuperAdmin()) {
			allGroup = smartCompoundManagment
					.getCompoundGroups(getCurrentCompound());
		}
		allMenuItem = smartCompoundManagment.getAllMenuItemsAfterCheckSuperAdminFlag(isSuperAdmin());

	}

	public SmartCompoundManagment getSmartCompoundManagment() {
		return smartCompoundManagment;
	}

	public void setSmartCompoundManagment(
			SmartCompoundManagment smartCompoundManagment) {
		this.smartCompoundManagment = smartCompoundManagment;
	}

	public List<Group> getAllGroup() {
		return allGroup;
	}

	public void saveSelectedMenus() {

		smartCompoundManagment
				.insertGroupMenuItem(selectedGroup, selectedItems);
		addInfoMessage(MessagesKeys.SMART_COMPOUND_SUCCESS_MESSAGE_MESSAGE);
	}

	public void setAllGroup(List<Group> allGroup) {
		// System.out.println("allGroup : " + allGroup);
		this.allGroup = allGroup;
	}

	public List<MenuItem> getAllMenuItem() {
		return allMenuItem;
	}

	public void setAllMenuItem(List<MenuItem> allMenuItem) {
		this.allMenuItem = allMenuItem;
	}

	public Group getSelectedGroup() {
		return selectedGroup;
	}

	public void setSelectedGroup(Group selectedGroup) {
		this.selectedGroup = selectedGroup;
	}

	public List<MenuItem> getSelectedMenuItem() {
		return selectedMenuItem;
	}

	public void setSelectedMenuItem(List<MenuItem> selectedMenuItem) {
		this.selectedMenuItem = selectedMenuItem;
	}

	public Boolean getCheck() {
		return check;
	}

	public void setCheck(Boolean check) {
		this.check = check;
	}

	public List<MenuItem> getSelectedItems() {
		return selectedItems;
	}

	public void setSelectedItems(List<MenuItem> selectedItems) {
		this.selectedItems = selectedItems;
	}

	public List<Compound> getCompounds() {
		return compounds;
	}

	public void setCompounds(List<Compound> compounds) {
		this.compounds = compounds;
	}

	public Compound getSelectCompound() {
		return selectCompound;
	}

	public void setSelectCompound(Compound selectCompound) {
		this.selectCompound = selectCompound;
	}

}
