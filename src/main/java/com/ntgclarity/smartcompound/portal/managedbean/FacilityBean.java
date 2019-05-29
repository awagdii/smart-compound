package com.ntgclarity.smartcompound.portal.managedbean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Spliterator;
import java.util.function.Consumer;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.ntgclarity.smartcompound.business.management.SmartCompoundManagment;
import com.ntgclarity.smartcompound.common.constatnt.MessagesKeys;
import com.ntgclarity.smartcompound.common.entity.Compound;
import com.ntgclarity.smartcompound.common.entity.Facility;
import com.ntgclarity.smartcompound.common.entity.Lookup;
import com.ntgclarity.smartcompound.common.entity.LookupType;
import com.ntgclarity.smartcompound.common.entity.Tenant;
import com.ntgclarity.smartcompound.common.exception.SmartCompoundException;
import com.ntgclarity.smartcompound.portal.base.BaseBean;

@ManagedBean
@ViewScoped
public class FacilityBean extends BaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	final static Logger logger = Logger.getLogger(FacilityBean.class);

	@ManagedProperty(value = "#{smartCompoundManagmentImpl}")
	private SmartCompoundManagment smartCompoundManagment;

	private Facility selectedFacility;
	private LazyDataModel<Facility> lazyFacilityModel;
	private List<Tenant> tenants;
	private List<Lookup> facilityTypeLookups;
	private List<Lookup> facilityStatusLookups;
	private boolean facilityForRent = false;

	@PostConstruct
	public void init() {
		initiateFacility();
		LoadData();
		initiateFacilityTypeLookup();
		initiateFacilityStatusLookup();
	}

	private void initiateFacilityStatusLookup() {
		facilityStatusLookups = smartCompoundManagment
				.getLookups(LookupType.FACILITY_STATUS);
	}

	private void initiateFacilityTypeLookup() {
		facilityTypeLookups = smartCompoundManagment
				.getLookups(LookupType.FACILITY_TYPE);
	}

	public void initiateFacility() {
		selectedFacility = new Facility();
	}

	private void LoadData() {
		lazyFacilityModel = new LazyDataModel<Facility>() {

			private List<Facility> result;

			@Override
			public Facility getRowData(String rowKey) {
				for (Facility employee : result) {
					if (employee.getId().toString().equals(rowKey))
						return employee;
				}

				return null;
			}

			@Override
			public Object getRowKey(Facility facility) {
				return facility.getId();
			}

			@Override
			public List<Facility> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {
				filters.put("compound", getCurrentCompound());
				result = getSmartCompoundManagment().loadFacilities(first,
						pageSize, sortField, sortOrder == SortOrder.ASCENDING,
						filters);
				this.setRowCount(getSmartCompoundManagment()
						.getNumOfFacilitiesRows(filters));

				return result;
			}

			@Override
			public void forEach(Consumer<? super Facility> action) {

			}

			@Override
			public Spliterator<Facility> spliterator() {
				return null;
			}
		};
	}

	public List<Tenant> completeTenant(String query) {
		Compound compound = getCurrentCompound();
		tenants = smartCompoundManagment.getCompoundTenants(compound, query);
		return tenants;
	}

	public void insertFacility() throws SmartCompoundException {
		selectedFacility.setCompound(getCurrentCompound());
		if (selectedFacility.getStatusLookup() != null)
			selectedFacility.setStatus(selectedFacility.getStatusLookup()
					.getLookupName());
		if (facilityForRent == true) {
			selectedFacility.setIsForRent(1);
		} else
			selectedFacility.setIsForRent(0);
		smartCompoundManagment.insertFacility(selectedFacility);
		addInfoMessage("NG_NTS_GENERAL_SAVED_SUCCESSFULLY");
		selectedFacility = new Facility();
	}

	public SmartCompoundManagment getSmartCompoundManagment() {
		return smartCompoundManagment;
	}

	public void setSmartCompoundManagment(
			SmartCompoundManagment smartCompoundManagment) {
		this.smartCompoundManagment = smartCompoundManagment;
	}

	public Facility getSelectedFacility() {
		return selectedFacility;
	}

	public void setSelectedFacility(Facility selectedFacility) {
		this.selectedFacility = selectedFacility;
	}

	public LazyDataModel<Facility> getLazyFacilityModel() {
		return lazyFacilityModel;
	}

	public void setLazyFacilityModel(LazyDataModel<Facility> lazyFacilityModel) {
		this.lazyFacilityModel = lazyFacilityModel;
	}

	public List<Lookup> getFacilityTypeLookups() {
		return facilityTypeLookups;
	}

	public void setFacilityTypeLookups(List<Lookup> facilityTypeLookups) {
		this.facilityTypeLookups = facilityTypeLookups;
	}

	public List<Lookup> getFacilityStatusLookups() {
		return facilityStatusLookups;
	}

	public void setFacilityStatusLookups(List<Lookup> facilityStatusLookups) {
		this.facilityStatusLookups = facilityStatusLookups;
	}

	public List<Tenant> getTenants() {
		return tenants;
	}

	public void setTenants(List<Tenant> tenants) {
		this.tenants = tenants;
	}

	public boolean isFacilityForRent() {
		return facilityForRent;
	}

	public void setFacilityForRent(boolean facilityForRent) {
		this.facilityForRent = facilityForRent;
	}
	
	public void disableButton() {
		selectedFacility=null;
	}

}
