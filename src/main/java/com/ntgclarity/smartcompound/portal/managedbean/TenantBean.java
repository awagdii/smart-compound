package com.ntgclarity.smartcompound.portal.managedbean;

import java.io.Serializable;
import java.util.List;
import java.util.Spliterator;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.ViewHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.validation.ValidationException;

import java.util.Map;
import java.util.function.Consumer;

import org.primefaces.event.ToggleEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.ntgclarity.smartcompound.business.management.SmartCompoundManagment;
import com.ntgclarity.smartcompound.common.constatnt.MessagesKeys;
import com.ntgclarity.smartcompound.common.entity.Bill;
import com.ntgclarity.smartcompound.common.entity.BillDetails;
import com.ntgclarity.smartcompound.common.entity.City;
import com.ntgclarity.smartcompound.common.entity.Country;
import com.ntgclarity.smartcompound.common.entity.Lookup;
import com.ntgclarity.smartcompound.common.entity.LookupType;
import com.ntgclarity.smartcompound.common.entity.Order;
import com.ntgclarity.smartcompound.common.entity.Tenant;
import com.ntgclarity.smartcompound.common.entity.Ticket;
import com.ntgclarity.smartcompound.common.entity.TicketHistory;
import com.ntgclarity.smartcompound.common.exception.SmartCompoundException;
import com.ntgclarity.smartcompound.common.utils.Utils;
import com.ntgclarity.smartcompound.portal.base.BaseBean;

@ManagedBean
@ViewScoped
public class TenantBean extends BaseBean implements Serializable {

	@ManagedProperty(value = "#{smartCompoundManagmentImpl}")
	private SmartCompoundManagment smartCompoundManagment;

	private List<Tenant> allTenants;
	private List<Lookup> statusLookup;
	private List<Lookup> genderLookup;
	private List<Lookup> salutationLookup;
	private List<String> identificationTypeLookup;
	private List<String> bestConnectionMethodLookup;
	private List<Country> countries;
	private List<String> cities;
	private List<String> nationalities;
	private Country selectedCountry;

	private Tenant tenant;

	private LazyDataModel<Tenant> lazyTenantModel;

	private List<Ticket> tenantTickets;
	private List<Order> tenantOrders;
	private List<Bill> tenantBills;
	private List<TicketHistory> ticketHistory;
	private List<BillDetails> billDetails;

	@PostConstruct
	public void init() {
		tenant = new Tenant();
		statusLookup = smartCompoundManagment
				.getLookups(LookupType.GENERAL_STATUS);
		genderLookup = smartCompoundManagment.getLookups(LookupType.GENDER);
		salutationLookup = smartCompoundManagment
				.getLookups(LookupType.SALUTATION);
		identificationTypeLookup = Utils
				.getLookupAsString(smartCompoundManagment
						.getLookups(LookupType.IDENTIFICATION_TYPE));
		bestConnectionMethodLookup = Utils
				.getLookupAsString(smartCompoundManagment
						.getLookups(LookupType.BEST_CONNECTION_METHOD));
		countries = smartCompoundManagment.getAllCountries();
		selectedCountry = countries.get(0);
		onCountryChange();
		nationalities = smartCompoundManagment.getAllNationalitiesAsString();
		LoadData();

	}

	public void checkUserName(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {

		if (((String) value).length() < 3 || ((String) value).length() > 20) {
			throw new ValidatorException(
					new FacesMessage(
							FacesMessage.SEVERITY_ERROR,
							getMessageValue("SMART_COMPOUND_TENANT_PAGE_USERNAME_LENGTH_VALIDATION"),
							null));
		}
		if (smartCompoundManagment.getTenantByUsername((String) value + "@"
				+ getCurrentCompound().getDomain()) != null
				&& (tenant.getId() == null || !tenant.getUsername().equals(
						value))) {
			throw new ValidatorException(
					new FacesMessage(
							FacesMessage.SEVERITY_ERROR,
							getMessageValue("SMART_COMPOUND_TENANT_PAGE_USERNAME_EXIST_VALIDATION"),
							null));
		}
	}

	public void trimUsername() {
		tenant.setUsername(tenant.getUsername().replace(
				"@" + tenant.getCompound().getDomain(), ""));
	}

	public void onCountryChange() {
		cities = smartCompoundManagment
				.getAllCitiesInCountryAsString(selectedCountry);
		tenant.setCountry(selectedCountry.getName());
	}

	public void initiateNewTenent() {

		tenant = new Tenant();
		tenant.setCompound(getCurrentCompound());
	}

	private void LoadData() {
		lazyTenantModel = new LazyDataModel<Tenant>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			private List<Tenant> result;

			@Override
			public Tenant getRowData(String rowKey) {
				for (Tenant tenant : result) {
					if (tenant.getId().toString().equals(rowKey))
						return tenant;
				}
				return null;
			}

			@Override
			public Object getRowKey(Tenant tenant) {
				return tenant.getId();
			}

			@Override
			public List<Tenant> load(int first, int pageSize, String sortField,
					SortOrder sortOrder, Map<String, Object> filters) {

				addCompoundFiltrationInCaseOfNoneAdminUser(filters);

				result = getSmartCompoundManagment().loadTenants(first,
						pageSize, sortField, sortOrder == SortOrder.ASCENDING,
						filters);
				this.setRowCount(getSmartCompoundManagment()
						.getNumOfTenantsRows(filters));
				return result;
			}

			private void addCompoundFiltrationInCaseOfNoneAdminUser(
					Map<String, Object> filters) {
				if (isNotSuperAdmin()) {

					filters.put("compound", getCurrentCompound());
				}

			}

			@Override
			public void forEach(Consumer<? super Tenant> action) {
				// TODO Auto-generated method stub

			}

			@Override
			public Spliterator<Tenant> spliterator() {
				// TODO Auto-generated method stub
				return null;
			}

		};
	}

	public void addTenant() {

		tenant.setSalutation(tenant.getSalutationLookup().getLookupName());
		tenant.setGender(tenant.getGenderLookup().getLookupName());
		tenant.setUsername(tenant.getUsername() + "@"
				+ getCurrentCompound().getDomain());
		tenant.setCompound(getCurrentCompound());
		tenant = smartCompoundManagment.insertTenant(tenant);
		addInfoMessage(MessagesKeys.SMART_COMPOUND_SUCCESS_MESSAGE_MESSAGE);

	}

	public void updateTenant() {
		tenant.setSalutation(tenant.getSalutationLookup().getLookupName());
		tenant.setGender(tenant.getGenderLookup().getLookupName());
		tenant.setUsername(tenant.getUsername() + "@"
				+ getCurrentCompound().getDomain());
		smartCompoundManagment.updateTenant(tenant);
		tenant = new Tenant();
		addInfoMessage(MessagesKeys.SMART_COMPOUND_SUCCESS_MESSAGE_MESSAGE);
	}

	public Tenant getTenant() {
		return tenant;
	}

	public void setTenant(Tenant tenant) {

		this.tenant = tenant;
	}

	public List<Tenant> getAllTenants() {
		return allTenants;
	}

	public void setAllTenants(List<Tenant> allTenants) {
		this.allTenants = allTenants;
	}

	public SmartCompoundManagment getSmartCompoundManagment() {
		return smartCompoundManagment;
	}

	public void setSmartCompoundManagment(
			SmartCompoundManagment smartCompoundManagment) {
		this.smartCompoundManagment = smartCompoundManagment;
	}

	public LazyDataModel<Tenant> getLazyTenantModel() {
		return lazyTenantModel;
	}

	public void setLazyTenantModel(LazyDataModel<Tenant> lazyTenantModel) {
		this.lazyTenantModel = lazyTenantModel;
	}

	public List<Lookup> getStatusLookup() {
		return statusLookup;
	}

	public void setStatusLookup(List<Lookup> statusLookup) {
		this.statusLookup = statusLookup;
	}

	public List<Lookup> getGenderLookup() {
		return genderLookup;
	}

	public void setGenderLookup(List<Lookup> genderLookup) {
		this.genderLookup = genderLookup;
	}

	public List<Lookup> getSalutationLookup() {
		return salutationLookup;
	}

	public void setSalutationLookup(List<Lookup> salutationLookup) {
		this.salutationLookup = salutationLookup;
	}

	public List<Country> getCountries() {
		return countries;
	}

	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}

	public List<String> getCities() {
		return cities;
	}

	public void setCities(List<String> cities) {
		this.cities = cities;
	}

	public Country getSelectedCountry() {
		return selectedCountry;
	}

	public void setSelectedCountry(Country selectedCountry) {
		this.selectedCountry = selectedCountry;
	}

	public List<String> getNationalities() {
		return nationalities;
	}

	public void setNationalities(List<String> nationalities) {
		this.nationalities = nationalities;
	}

	public List<String> getIdentificationTypeLookup() {
		return identificationTypeLookup;
	}

	public void setIdentificationTypeLookup(
			List<String> identificationTypeLookup) {
		this.identificationTypeLookup = identificationTypeLookup;
	}

	public List<String> getBestConnectionMethodLookup() {
		return bestConnectionMethodLookup;
	}

	public void setBestConnectionMethodLookup(
			List<String> bestConnectionMethodLookup) {
		this.bestConnectionMethodLookup = bestConnectionMethodLookup;
	}

	public void loadTenantTickets(Tenant tenant) {
		tenantTickets = getSmartCompoundManagment().getTicketsOfTenant(tenant);
	}

	public void loadTenentOrders(Tenant tenent) {
		tenantOrders = getSmartCompoundManagment().getOrdersOfTenant(tenent);
	}

	public void loadTenentBills(Tenant tenent) {
		tenantBills = getSmartCompoundManagment().getBillsOfTenant(tenent);
	}

	public void loadTenantBillDetails(ToggleEvent toggleEvent) {
		Bill bill = (Bill) toggleEvent.getData();
		billDetails = getSmartCompoundManagment().getBillDetailsOfBill(bill);
	}

	public List<Ticket> getTenantTickets() {
		return tenantTickets;
	}

	public void setTenantTickets(List<Ticket> tenantTickets) {
		this.tenantTickets = tenantTickets;
	}

	public List<Order> getTenantOrders() {
		return tenantOrders;
	}

	public void setTenantOrders(List<Order> tenantOrders) {
		this.tenantOrders = tenantOrders;
	}

	public List<Bill> getTenantBills() {
		return tenantBills;
	}

	public void setTenantBills(List<Bill> tenantBills) {
		this.tenantBills = tenantBills;
	}

	public void ticketExpanded(ToggleEvent event) {
		Ticket ticket = (Ticket) event.getData();
		ticketHistory = getSmartCompoundManagment().loadTicketHistories(ticket);
	}

	public List<TicketHistory> getTicketHistory() {
		return ticketHistory;
	}

	public void setTicketHistory(List<TicketHistory> ticketHistory) {
		this.ticketHistory = ticketHistory;
	}

	public List<BillDetails> getBillDetails() {
		return billDetails;
	}

	public void setBillDetails(List<BillDetails> billDetails) {
		this.billDetails = billDetails;
	}

	public void disableButton() {
		tenant=null;
	}
}
