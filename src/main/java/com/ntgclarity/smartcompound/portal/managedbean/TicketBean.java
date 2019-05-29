package com.ntgclarity.smartcompound.portal.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Spliterator;
import java.util.function.Consumer;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.ntgclarity.smartcompound.business.management.SmartCompoundManagment;
import com.ntgclarity.smartcompound.common.constatnt.MessagesKeys;
import com.ntgclarity.smartcompound.common.entity.Compound;
import com.ntgclarity.smartcompound.common.entity.Employee;
import com.ntgclarity.smartcompound.common.entity.Facility;
import com.ntgclarity.smartcompound.common.entity.Group;
import com.ntgclarity.smartcompound.common.entity.Service;
import com.ntgclarity.smartcompound.common.entity.Tenant;
import com.ntgclarity.smartcompound.common.entity.Ticket;
import com.ntgclarity.smartcompound.common.entity.TicketHistory;
import com.ntgclarity.smartcompound.common.entity.TicketStatus;
import com.ntgclarity.smartcompound.common.exception.SmartCompoundException;
import com.ntgclarity.smartcompound.portal.base.BaseBean;

/** Author: Nazer **/

@ManagedBean
@ViewScoped
public class TicketBean extends BaseBean implements Serializable {

	@ManagedProperty(value = "#{smartCompoundManagmentImpl}")
	private SmartCompoundManagment smartCompoundManagment;
	private Boolean disabled = true;
	private List<Service>facilityServices;
	// private String lastTicketHistory;
	private LazyDataModel<TicketHistory> lazyTicketHistoryModel;
	private TicketHistoryBean ticketHistoryBean;
	private List<TicketStatus> allTicketStatuses;
	/**
	 * public Boolean getDisabled() { return disabled; }
	 * 
	 * public void setDisabled(Boolean disabled) { this.disabled = disabled; }
	 */
	private List<Facility> tenantFacilities;
	private List<Group> groupList;
	private Boolean isSelected;
	private List<TicketStatus> ticketStatusList;
	private Ticket selectedTicket;
	private LazyDataModel<Ticket> lazyTicketModel;
	
	private List<TicketHistory> selectedTicketHistoryList;

	public LazyDataModel<Ticket> getLazyTicketModel() {
		return lazyTicketModel;
	}

	public List<TicketStatus> getTicketStatusList() {
		return ticketStatusList;
	}

	public void setTicketStatusList(List<TicketStatus> ticketStatusList) {
		this.ticketStatusList = ticketStatusList;
	}

	public void setLazyTicketModel(LazyDataModel<Ticket> lazyTicketModel) {
		this.lazyTicketModel = lazyTicketModel;
	}

	private List<Tenant> tenants;

	private List<Facility> facilites;
	private List<Service> services;

	private Compound compound;

	public Compound getCompound() {
		return compound;
	}

	public void setCompound(Compound compound) {
		this.compound = compound;
	}

	public void initiateListOfTenant() {
		tenants = new ArrayList<>();
	}

	public void initiateTicketStatusList() throws SmartCompoundException {

		if (selectedTicket == null)
			initiateNewTicket();
		ticketStatusList = smartCompoundManagment
				.getTicketStatusSequences(selectedTicket.getTicketStatus());
		
		tenantFacilities = getSmartCompoundManagment().getAllFacilitiesRelatedToTenant(selectedTicket.getRelatedTenant());

		if(selectedTicket.getIsFacilityBased()==1)
		{
			facilityServices=smartCompoundManagment.getTenantServicesRelatedToFacilty(getCurrentCompound(),selectedTicket.getRelatedTenant(),selectedTicket.getFacility());
		}

	}

	public void initiateListOfGroup() {
		groupList = new ArrayList<>();
	
		groupList = smartCompoundManagment.getCompoundGroups(getCurrentCompound());
	}

	public List<Group> getGroupList() {
		return groupList;
	}

	public void setGroupList(List<Group> groupList) {
		this.groupList = groupList;
	}

	public void initiateListOfFacility() {
		facilites = new ArrayList<>();
	}

	public void initiateListOfService() {
		services = new ArrayList<>();
	}

	public Ticket getSelectedTicket() {
		return selectedTicket;
	}

	public void setSelectedTicket(Ticket selectedTicket) {
		this.selectedTicket = selectedTicket;
	}

	public List<Tenant> getTenants() {
		return tenants;
	}

	public void setTenants(List<Tenant> tenants) {
		this.tenants = tenants;
	}

	public List<Facility> getFacilites() {
		return facilites;
	}

	public void setFacilites(List<Facility> facilites) {
		this.facilites = facilites;
	}

	public List<Service> getServices() {
		return services;
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Ticket> allTickets;

	@PostConstruct
	public void init() throws SmartCompoundException {
		// initiateTicketHistoryBean();

		initiateListOfService();
		initiateListOfFacility();
		LoadData();
		
		LoadTicketHistoryData();
		// ticketHistoryBean.LoadTicketHistoryData(this.selectedTicket);
		loadAllTickets();
		initiateListOfGroup();

		initiateTicketStatusList();
		getAllTenants();  
		loadAllTicketStatuses();
		
	}

	private void loadAllTicketStatuses() {
		allTicketStatuses = getSmartCompoundManagment().getAllTicketStatuses();		
	}

	public List<TicketStatus> getAllTicketStatuses() {
		return allTicketStatuses;
	}

	public void setAllTicketStatuses(List<TicketStatus> allTicketStatuses) {
		this.allTicketStatuses = allTicketStatuses;
	}

	private void initiateTicketHistoryBean() {
		ticketHistoryBean = new TicketHistoryBean();
		//
	}

	public void initiateNewTicket() {
		selectedTicket = new Ticket();
		selectedTicket.setTicketStatus(TicketStatus.NEW);
		selectedTicket.setOpenDate(new Date());
		selectedTicket.setCompound(getCurrentCompound());
		selectedTicket.setChannel("Portal");
	}

	public List<Facility> completeFacilities(String searchParam)
			throws SmartCompoundException {
		Compound compound =getCurrentCompound();
		
		facilites = smartCompoundManagment.getCompoundFacilites(compound,
				searchParam.trim());
		return facilites;
	}
	public void getAllTenantFacilities(Tenant tenant ) throws SmartCompoundException{
//		System.out.println("this tenant is -----------------"+tenant.getId());
		tenantFacilities= smartCompoundManagment.getAllFacilitiesRelatedToTenant(tenant);
	}

	public void getAllTenants() {
//		System.out.println("this Compound is -------------------"+getCurrentCompound().getId());
		tenants =smartCompoundManagment.getAllTenants(getCurrentCompound());
//		System.out.println("number of tenent-----------------"+tenants.size());
		
		
	}

	public List<Service> completeServices(String query) {
		
		services = smartCompoundManagment.getCompoundServices(getCurrentCompound(), query);
		return services;
	}

	public void loadAllTickets() {
		allTickets = null; // smartCompoundManagment.getAllTickets();
	}

	public void testMethod() {

		loadAllTickets();
	}

	public void printTicket() {
//		System.out.println(selectedTicket);
	}

	public List<Ticket> getAllTickets() {
		return allTickets;
	}

	public void setAllTickets(List<Ticket> allTickets) {
		this.allTickets = allTickets;
	}

	public SmartCompoundManagment getSmartCompoundManagment() {
		return smartCompoundManagment;
	}

	public void setSmartCompoundManagment(
			SmartCompoundManagment smartCompoundManagment) {
		this.smartCompoundManagment = smartCompoundManagment;
	}

	public Ticket updateTicket() {
		Employee employee = getCurrentLoggedEmployee();

		return getSmartCompoundManagment().updateTicket(selectedTicket,
				employee);

	}

	public Ticket insertTicket() {
		// Employee employee=getCurrentLoggedEmployee();
//		System.out.println("facilty related is ================"
//				+ selectedTicket.getIsFacilityBased());
		if (selectedTicket.getIsFacilityBased() == 1) {
			isSelected = true;
		} else {
			isSelected = false;
		}
		Ticket ticket = getSmartCompoundManagment().insertTicket(selectedTicket);
		addInfoMessage(MessagesKeys.SMART_COMPOUND_SUCCESS_MESSAGE_MESSAGE);
		return ticket;
		
	}

	public void onRowSelect(SelectEvent event) {
		disabled = false;
	}

	private void LoadData() {
		lazyTicketModel = new LazyDataModel<Ticket>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			private List<Ticket> result;

			@Override
			public Ticket getRowData(String rowKey) {
				for (Ticket ticket : result) {
					if (ticket.getId().toString().equals(rowKey))
						return ticket;
				}

				return null;
			}

			@Override
			public Object getRowKey(Ticket ticket) {
				return ticket.getId();
			}

			@Override
			public List<Ticket> load(int first, int pageSize, String sortField,
					SortOrder sortOrder, Map<String, Object> filters) {
				if(isNotSuperAdmin()){
				filters.put("compound", getCurrentCompound());
				}
				result = getSmartCompoundManagment().loadTickets(first,
						pageSize, sortField, sortOrder == SortOrder.ASCENDING,
						filters);
				this.setRowCount(getSmartCompoundManagment()
						.getNumOfTicketsRows(filters));

				return result;
			}

			@Override
			public void forEach(Consumer<? super Ticket> action) {
				// TODO Auto-generated method stub

			}

			@Override
			public Spliterator<Ticket> spliterator() {

				return null;
			}

		};
	}

	public void getTicketStatusSequences() {
//		System.out.println("in get sequence*******************************");

		TicketStatus s = new TicketStatus();
		s.setId(new Long(1));

		List<TicketStatus> result = smartCompoundManagment
				.getTicketStatusSequences(s);

//		for (TicketStatus ticketStatus : result) {
//
//			System.out.println(ticketStatus.getId().intValue());
//		}
	}

	public TicketStatus getCurrentStatus(Ticket ticket) {
		TicketStatus ticketStatus = smartCompoundManagment
				.getCurrentStatus(ticket);
		return ticketStatus;
	}

	public LazyDataModel<TicketHistory> getLazyTicketHistoryModel() {
		return lazyTicketHistoryModel;
	}

	public void setLazyTicketHistoryModel(
			LazyDataModel<TicketHistory> lazyTicketHistoryModel) {
		this.lazyTicketHistoryModel = lazyTicketHistoryModel;
	}

	private void LoadTicketHistoryData() {
		lazyTicketHistoryModel = new LazyDataModel<TicketHistory>() {
			/**
			 * 
			 */
			// Ticket ticket=selectedTicket;
			private static final long serialVersionUID = 1L;
			private List<TicketHistory> result;

			@Override
			public TicketHistory getRowData(String rowKey) {
				for (TicketHistory ticketHistory : result) {
					if (ticketHistory.getId().toString().equals(rowKey))
						return ticketHistory;
				}

				return null;
			}

			@Override
			public Object getRowKey(TicketHistory ticketHistory) {
				return ticketHistory.getId();
			}

			@Override
			public List<TicketHistory> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {
				
				
				
				filters.put("ticket.id", selectedTicket.getId());
				result = getSmartCompoundManagment().loadTicketHistories(first,
						pageSize, sortField, sortOrder == SortOrder.ASCENDING,
						filters);
				this.setRowCount(getSmartCompoundManagment()
						.getNumOfTicketHistoriesRows(filters));

				return result;
			}

			@Override
			public void forEach(Consumer<? super TicketHistory> action) {
				// TODO Auto-generated method stub

			}

			@Override
			public Spliterator<TicketHistory> spliterator() {
				// TODO Auto-generated method stub
				return null;
			}

		};
	}

	public TicketHistoryBean getTicketHistoryBean() {
		return ticketHistoryBean;
	}

	public void setTicketHistoryBean(TicketHistoryBean ticketHistoryBean) {
		this.ticketHistoryBean = ticketHistoryBean;
	}

	public void display(Ticket selectedTicket) {
//		initiateNewTicket();
//		selectedTicket.setIsFacilityBased(1);
//		System.out.println("in display -----------------");
		if (selectedTicket.getIsFacilityBased() == 1) {
			isSelected = true;
//			System.out.println("is selected is --------------------"
//					+ isSelected);
		} else {
			isSelected = false;
		}
	}

	public Boolean getIsSelected() {
		return isSelected;
	}

	public void setIsSelected(Boolean isSelected) {
		this.isSelected = isSelected;
	}

	public List<Facility> getTenantFacilities() {
		return tenantFacilities;
	}

	public void setTenantFacilities(List<Facility> tenantFacilities) {
		this.tenantFacilities = tenantFacilities;
	}
	

	public void setFacilityServices(List<Service> facilityServices) {
		this.facilityServices = facilityServices;
	}

	public List<Service> getFacilityServices() {
		return facilityServices;
	}
	public void getAllFacilityServices(){
		facilityServices=smartCompoundManagment.getTenantServicesRelatedToFacilty(getCurrentCompound(),selectedTicket.getRelatedTenant(),selectedTicket.getFacility());
		
	}

	public List<TicketHistory> getSelectedTicketHistoryList() {
		return selectedTicketHistoryList;
	}

	public void setSelectedTicketHistoryList(
			List<TicketHistory> selectedTicketHistoryList) {
		this.selectedTicketHistoryList = selectedTicketHistoryList;
	} 
	
	public void loadSelectedTicketStatusHistoryList() throws SmartCompoundException
	{
		selectedTicketHistoryList = getSmartCompoundManagment().loadTicketHistories(selectedTicket);
		
	}
	
	public void ticketExpanded(ToggleEvent event) {
		Ticket ticket = (Ticket) event.getData();
		selectedTicketHistoryList = getSmartCompoundManagment().loadTicketHistories(ticket);
	}
}
