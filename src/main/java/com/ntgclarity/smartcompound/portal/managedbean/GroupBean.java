package com.ntgclarity.smartcompound.portal.managedbean;

import java.io.Serializable;
import java.util.List;
import java.util.Spliterator;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import java.util.Map;
import java.util.function.Consumer;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.ntgclarity.smartcompound.business.management.SmartCompoundManagment;
import com.ntgclarity.smartcompound.common.constatnt.MessagesKeys;
import com.ntgclarity.smartcompound.common.constatnt.SmartCompoundConstant;
import com.ntgclarity.smartcompound.common.entity.Compound;
import com.ntgclarity.smartcompound.common.entity.Employee;
import com.ntgclarity.smartcompound.common.entity.Group;
import com.ntgclarity.smartcompound.common.entity.Lookup;
import com.ntgclarity.smartcompound.common.entity.LookupType;
import com.ntgclarity.smartcompound.common.entity.Menu;
import com.ntgclarity.smartcompound.portal.base.BaseBean;

@ManagedBean
@ViewScoped
public class GroupBean extends BaseBean implements Serializable {
	@ManagedProperty(value = "#{smartCompoundManagmentImpl}")
	private SmartCompoundManagment smartCompoundManagment;

	private static final long serialVersionUID = 1L;

	private Group group;

	private List<Compound> compounds;

	private LazyDataModel<Group> lazyGroupModel;

	@PostConstruct
	public void init() {
		initiateNewGroup();
		compounds = smartCompoundManagment.getALLCompounds();
		LoadData();

	}

	public void initiateNewGroup() {
		group = new Group();
		if (!isSuperAdmin()) {
			group.setCompound(getCurrentCompound());
		}

	}

	public void addGroup() {
		group.setCreatedBy(getCurrentLoggedEmployee());
		smartCompoundManagment.insertGroup(group);
		addInfoMessage(MessagesKeys.SMART_COMPOUND_SUCCESS_MESSAGE_MESSAGE);

	}

	public void updateGroup() {
		smartCompoundManagment.updateGroup(group);
		addInfoMessage(MessagesKeys.SMART_COMPOUND_SUCCESS_MESSAGE_MESSAGE);
	}

	private void LoadData() {
		lazyGroupModel = new LazyDataModel<Group>() {

			private static final long serialVersionUID = 1L;
			private List<Group> result;

			@Override
			public Group getRowData(String rowKey) {
				for (Group group : result) {
					if (group.getId().toString().equals(rowKey))
						return group;
				}

				return null;
			}

			@Override
			public Object getRowKey(Group group) {
				return group.getId();
			}

			@Override
			public List<Group> load(int first, int pageSize, String sortField,
					SortOrder sortOrder, Map<String, Object> filters) {
				if(isSuperAdmin())
				{
					filters.put("createdBy.superAdminFlag", 1);
				}else
				{
					filters.put("compound", getCurrentCompound());

				}
				result = getSmartCompoundManagment().loadGroups(first,
						pageSize, sortField, sortOrder == SortOrder.ASCENDING,
						filters);
				this.setRowCount(getSmartCompoundManagment()
						.getNumOfGroupsRows(filters));
				return result;
			}

			@Override
			public void forEach(Consumer<? super Group> action) {
				// TODO Auto-generated method stub

			}

			@Override
			public Spliterator<Group> spliterator() {
				// TODO Auto-generated method stub
				return null;
			}

		};
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public SmartCompoundManagment getSmartCompoundManagment() {
		return smartCompoundManagment;
	}

	public void setSmartCompoundManagment(
			SmartCompoundManagment smartCompoundManagment) {
		this.smartCompoundManagment = smartCompoundManagment;
	}

	public LazyDataModel<Group> getLazyGroupModel() {
		return lazyGroupModel;
	}

	public void setLazyGroupModel(LazyDataModel<Group> lazyGroupModel) {
		this.lazyGroupModel = lazyGroupModel;
	}

	public List<Compound> getCompounds() {
		return compounds;
	}

	public void setCompounds(List<Compound> compounds) {
		this.compounds = compounds;
	}

	public void disableButton() {
		group = null;
	}
}
