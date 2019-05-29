package com.ntgclarity.smartcompound.portal.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DualListModel;

import com.ntgclarity.smartcompound.business.management.SmartCompoundManagment;
import com.ntgclarity.smartcompound.common.constatnt.MessagesKeys;
import com.ntgclarity.smartcompound.common.entity.Compound;
import com.ntgclarity.smartcompound.common.entity.Employee;
import com.ntgclarity.smartcompound.common.entity.Group;
import com.ntgclarity.smartcompound.common.entity.MenuItem;
import com.ntgclarity.smartcompound.common.exception.SmartCompoundException;
import com.ntgclarity.smartcompound.portal.base.BaseBean;

@ManagedBean
@ViewScoped
public class GroupEmployeeBean extends BaseBean implements Serializable {

	@ManagedProperty(value = "#{smartCompoundManagmentImpl}")
	private SmartCompoundManagment smartCompoundManagment;
	private DualListModel<Employee> employees;
	List<Group> allGroup;
	List<Employee> allEmployees;
	Group selectedGroup;
	Compound selectedCompound;

	public void onCompoundChange() {
		allGroup = smartCompoundManagment
				.getAllGroupByCompound(selectedCompound);
		employees = new DualListModel<Employee>();
	}

	public void onGroupChange() {

		List<Employee> groupEmployees = smartCompoundManagment
				.getEmployeesInGroup(selectedCompound, selectedGroup);
		List<Employee> notInGroupEmployees = smartCompoundManagment
				.getAllEmployeesInCompound(selectedCompound);
		notInGroupEmployees.removeAll(groupEmployees);
		employees.setTarget(groupEmployees);
		employees.setSource(notInGroupEmployees);

	}

	@PostConstruct
	public void init() {
		try {
			if (isNotSuperAdmin()) {
				selectedCompound = getCurrentCompound();
			}
			allEmployees = smartCompoundManagment.getAllEmployees();
			allGroup = smartCompoundManagment
					.getAllGroupByCompound(selectedCompound);
			employees = new DualListModel<Employee>();

		} catch (SmartCompoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void onTransfer(TransferEvent event) {
//		System.out.println("============> transfeeer object"
//				+ employees.getSource().size());
		smartCompoundManagment.deleteEmployeesFromGroup(selectedCompound,
				selectedGroup, allEmployees);
		smartCompoundManagment.addEmployeesToGroup(selectedCompound,
				selectedGroup, employees.getTarget());
		addInfoMessage(MessagesKeys.SMART_COMPOUND_SUCCESS_MESSAGE_MESSAGE);
	}

	public void onSelect(SelectEvent event) {

//		System.out.println("============>" + event.getObject());
	}

	public void onUnselect(UnselectEvent event) {

	}

	public void onReorder() {

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

	public void setAllGroup(List<Group> allGroup) {
		this.allGroup = allGroup;
	}

	public Group getSelectedGroup() {
		return selectedGroup;
	}

	public void setSelectedGroup(Group selectedGroup) {
		this.selectedGroup = selectedGroup;
	}

	public DualListModel<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(DualListModel<Employee> employees) {
		this.employees = employees;
	}

	public Compound getSelectedCompound() {
		return selectedCompound;
	}

	public void setSelectedCompound(Compound selectedCompound) {
		this.selectedCompound = selectedCompound;
	}

}
