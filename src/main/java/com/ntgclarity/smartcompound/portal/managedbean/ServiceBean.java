package com.ntgclarity.smartcompound.portal.managedbean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Spliterator;
import java.util.function.Consumer;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.ntgclarity.smartcompound.business.management.SmartCompoundManagment;
import com.ntgclarity.smartcompound.common.constatnt.MessagesKeys;
import com.ntgclarity.smartcompound.common.entity.Compound;
import com.ntgclarity.smartcompound.common.entity.Lookup;
import com.ntgclarity.smartcompound.common.entity.LookupType;
import com.ntgclarity.smartcompound.common.entity.Service;
import com.ntgclarity.smartcompound.common.entity.ServiceUsageRate;
import com.ntgclarity.smartcompound.common.exception.SmartCompoundException;
import com.ntgclarity.smartcompound.common.utils.Utils;
import com.ntgclarity.smartcompound.portal.base.BaseBean;

@ManagedBean
@ViewScoped
public class ServiceBean extends BaseBean implements Serializable {

	/**
	 * 
	 */
	@ManagedProperty(value = "#{smartCompoundManagmentImpl}")
	private SmartCompoundManagment smartCompoundManagment;

	private static final long serialVersionUID = 1L;
	private Service createdService;
	private LazyDataModel<Service> lazyServiceModel;
	private List<Service> allCompoundServices;
	private List<Lookup> statusesList, generalStatusList;
	private List<Lookup> measuringUnits;
	private boolean usageIsSelected = false, oneTimeIsSelected = false,
			flatIsSelected = false;
	private boolean facilityRelated = true;
	private ServiceUsageRate createdUsageRate;
	private Double to;
	private ServiceUsageRate selectedRate;

	@PostConstruct
	public void init() {
		allCompoundServices = smartCompoundManagment
				.getAllServices(getCurrentCompound());
		loadMeasuringUnits();
		LoadData();
		loadStatusList();
		loadGeneralStatusList();
		createdUsageRateInitialization();

	}

	public void initiateNewService() {
		createdService = new Service();
		createdService.setCompound(getCurrentCompound());

	}

	private void loadStatusList() {
		statusesList = smartCompoundManagment
				.getLookups(LookupType.SERVICE_STATUS);

	}

	private void loadGeneralStatusList() {
		generalStatusList = smartCompoundManagment
				.getLookups(LookupType.GENERAL_STATUS);

	}

	public void loadMeasuringUnits() {
		measuringUnits = smartCompoundManagment
				.getLookups(LookupType.MEASUREMENT_UNITES);
	}

	public void loadCompound(Service service) {

	}

	private void LoadData() {
		lazyServiceModel = new LazyDataModel<Service>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			private List<Service> result;

			@Override
			public Service getRowData(String rowKey) {
				for (Service service : result) {
					if (service.getId().toString().equals(rowKey))
						return service;
				}
				return null;
			}

			@Override
			public Object getRowKey(Service service) {
				return service.getId();
			}

			@Override
			public List<Service> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {
				if (isNotSuperAdmin()) {
					filters.put("compound", getCurrentCompound());
				}
				result = getSmartCompoundManagment().loadServices(first,
						pageSize, sortField, sortOrder == SortOrder.ASCENDING,
						filters);
				this.setRowCount(getSmartCompoundManagment()
						.getNumOfServicesRows(filters));
				return result;
			}

			@Override
			public void forEach(Consumer<? super Service> action) {
				// TODO Auto-generated method stub

			}

			@Override
			public Spliterator<Service> spliterator() {
				// TODO Auto-generated method stub
				return null;
			}
		};
	}

	public void saveUsageRate(ServiceUsageRate serviceUsageRate) {
		// is valid usage Rate
		// save it

		serviceUsageRate.setEditMode(false);
	}

	public void addUsageRateRow() {

		// validateLastAddedRow
		// if valid addNewOne

		ServiceUsageRate lastlyAddedUsageRate = createdService
				.getServiceUsageRates().get(
						createdService.getServiceUsageRates().size() - 1);
		to = lastlyAddedUsageRate.getUsageAmountTo();
		lastlyAddedUsageRate.setEditMode(false);
		ServiceUsageRate serviceUsageRate = new ServiceUsageRate();
		serviceUsageRate.setUsageAmountFrom(lastlyAddedUsageRate
				.getUsageAmountTo());
		serviceUsageRate.setEditMode(true);
		createdService.getServiceUsageRates().add(serviceUsageRate);
	}

	public void insertService() throws SmartCompoundException {
		createdService.setIsFacilityRelated(facilityRelated);
		createdService.setCreationDate(new Date());
		createdService.setFlatOrUsage(createdService.getFlatOrUsageLookup()
				.getLookupName());
		if (createdService.getFlatOrUsageLookup() != null
				&& createdService.getFlatOrUsageLookup().equals(Lookup.USAGE)) {
			setServiceInServiceRateUsage(createdService,
					createdService.getServiceUsageRates());
		}
		if (createdService.getId() == null) {
			createdService.setActivityStatusLookup(Lookup.ACTIVE);
			createdService.setIsActive(1);

		}
		if (createdService.getMeasuringUnitLookupId() != null) {
			createdService.setMeasuringUnit(createdService
					.getMeasuringUnitLookupId().getLookupName());
		}
		createdService = smartCompoundManagment.insertService(createdService);
		addInfoMessage(MessagesKeys.SMART_COMPOUND_SUCCESS_MESSAGE_MESSAGE);
		allCompoundServices.add(createdService);

		if (createdService.getFlatOrUsageLookup() != null
				&& createdService.getFlatOrUsageLookup().equals(Lookup.USAGE)) {
			smartCompoundManagment.insertServiceRates(createdService
					.getServiceUsageRates());
		}

	}

	public void setServiceInServiceRateUsage(Service service,
			List<ServiceUsageRate> rates) {
		for (ServiceUsageRate rate : rates) {
			rate.setCompound(getCurrentCompound());
			rate.setService(service);
			rate.setServiceName(service.getServiceName());
		}
	}

	public void updateServiceType(AjaxBehaviorEvent e) {
		Lookup lookup = (Lookup) ((SelectOneMenu) e.getSource()).getValue();
		if (lookup != null) {
			if (lookup.equals(Lookup.USAGE)) {
				usageIsSelected = true;
				oneTimeIsSelected = false;
				flatIsSelected = false;
				createdService.setFlatOrUsageLookup(lookup);
				// check if the first row is exsit
				List<ServiceUsageRate> alreadyExsistUsage = createdService
						.getServiceUsageRates();
				if (Utils.isEmpty(alreadyExsistUsage)) {
					ServiceUsageRate serviceUsageRate = new ServiceUsageRate();
					serviceUsageRate.setUsageAmountFrom(0D);
					serviceUsageRate.setEditMode(true);
					alreadyExsistUsage.add(serviceUsageRate);

				}

			}
			if (lookup.equals(Lookup.FLAT)) {
				usageIsSelected = false;
				oneTimeIsSelected = false;
				flatIsSelected = true;

			}
			if (lookup.equals(Lookup.ONE_TIME)) {
				oneTimeIsSelected = true;
				usageIsSelected = false;
				flatIsSelected = false;
			}
		}

	}

	public void getCompleteService() {
		createdService = smartCompoundManagment.getService(createdService
				.getId());
	}

	public void deleteRate(int index) {
		createdService.getServiceUsageRates().remove(index);
	}

	public void createdUsageRateInitialization() {
		createdUsageRate = new ServiceUsageRate();

	}

	public void checkServiceNameUniqueness(FacesContext context,
			UIComponent comp, Object value) {

		for (Service s : allCompoundServices) {
			if (((String) value).equalsIgnoreCase(s.getServiceName())) {
				if ((!s.getId().equals(createdService.getId()))) {
					((UIInput) comp).setValid(false);
					FacesMessage message = new FacesMessage(
							"This name already exists");
					message.setSeverity(FacesMessage.SEVERITY_ERROR);
					context.addMessage(comp.getClientId(context), message);
					break;
				}
			}
		}
	}

	public SmartCompoundManagment getSmartCompoundManagment() {
		return smartCompoundManagment;
	}

	public void setSmartCompoundManagment(
			SmartCompoundManagment smartCompoundManagment) {
		this.smartCompoundManagment = smartCompoundManagment;
	}

	public Service getCreatedService() {
		return createdService;
	}

	public void setCreatedService(Service createdService) {
		this.createdService = createdService;
	}

	public List<Lookup> getMeasuringUnits() {
		return measuringUnits;
	}

	public void setMeasuringUnits(List<Lookup> measuringUnits) {
		this.measuringUnits = measuringUnits;
	}

	public LazyDataModel<Service> getLazyServiceModel() {
		return lazyServiceModel;
	}

	public void setLazyServiceModel(LazyDataModel<Service> lazyServiceModel) {
		this.lazyServiceModel = lazyServiceModel;
	}

	public List<Lookup> getStatusesList() {
		return statusesList;
	}

	public void setStatusesList(List<Lookup> statusesList) {
		this.statusesList = statusesList;
	}

	public boolean isUsageIsSelected() {
		return usageIsSelected;
	}

	public void setUsageIsSelected(boolean usageIsSelected) {
		this.usageIsSelected = usageIsSelected;
	}

	public ServiceUsageRate getCreatedUsageRate() {
		return createdUsageRate;
	}

	public void setCreatedUsageRate(ServiceUsageRate createdUsageRate) {
		this.createdUsageRate = createdUsageRate;
	}

	public Double getTo() {
		return to;
	}

	public void setTo(Double to) {
		this.to = to;
	}

	public ServiceUsageRate getSelectedRate() {
		return selectedRate;
	}

	public void setSelectedRate(ServiceUsageRate selectedRate) {
		this.selectedRate = selectedRate;
	}

	public boolean isOneTimeIsSelected() {
		return oneTimeIsSelected;
	}

	public void setOneTimeIsSelected(boolean oneTimeIsSelected) {
		this.oneTimeIsSelected = oneTimeIsSelected;
	}

	public boolean isFlatIsSelected() {
		return flatIsSelected;
	}

	public void setFlatIsSelected(boolean flatIsSelected) {
		this.flatIsSelected = flatIsSelected;
	}

	public List<Lookup> getGeneralStatusList() {
		return generalStatusList;
	}

	public void setGeneralStatusList(List<Lookup> generalStatusList) {
		this.generalStatusList = generalStatusList;
	}

	public boolean isFacilityRelated() {
		return facilityRelated;
	}

	public void setFacilityRelated(boolean facilityRelated) {
		this.facilityRelated = facilityRelated;
	}

}
