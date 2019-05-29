package com.ntgclarity.smartcompound.portal.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.model.SortOrder;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import java.util.Map;
import java.util.function.Consumer;

import com.ntgclarity.smartcompound.common.constatnt.MessagesKeys;
import com.ntgclarity.smartcompound.common.entity.*;
import com.ntgclarity.smartcompound.business.management.SmartCompoundManagment;
import com.ntgclarity.smartcompound.common.entity.Order;
import com.ntgclarity.smartcompound.common.exception.SmartCompoundException;
import com.ntgclarity.smartcompound.common.utils.Utils;
import com.ntgclarity.smartcompound.portal.base.BaseBean;

@ManagedBean
@ViewScoped
public class OrderBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{smartCompoundManagmentImpl}")
	private SmartCompoundManagment smartCompoundManagment;

	private List<Facility> facilities;
	private List<Service> services;
	private List<Tenant> tenants;
	private Order selectedOrder;
	private LazyDataModel<Order> lazyOrderModel;
	private List<Lookup> orderStatusLookups;
	private MapModel simpleModel;
	

	@PostConstruct
	public void init() throws SmartCompoundException {
		simpleModel = new DefaultMapModel();
		initiateNewOrder();
		LoadData();
		initiateOrderStatusLookup();
		initialFacilitiesByTenant();
		initialServices();
	}
	
	public void initialFacilitiesByTenant()
			throws SmartCompoundException {
		Compound compound = getCurrentCompound();
		facilities = smartCompoundManagment.getCompoundFacilitesByTenant(compound,
				selectedOrder.getTenant());
	}

	private void initiateOrderStatusLookup() {
		orderStatusLookups = smartCompoundManagment.getLookups(LookupType.ORDER_STATUS);
	}

	public void initiateNewOrder() {
		selectedOrder = new Order();
	}
	
	public void initialServices() {
		 services = smartCompoundManagment.getAllActiveServices(getCurrentCompound());
		
	}

	private void LoadData() {
		lazyOrderModel = new LazyDataModel<Order>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			private List<Order> result;

			@Override
			public Order getRowData(String rowKey) {
				for (Order order : result) {
					if (order.getId().toString().equals(rowKey))
						return order;
				}

				return null;
			}

			@Override
			public Object getRowKey(Order order) {
				return order.getId();
			}

			@Override
			public List<Order> load(int first, int pageSize, String sortField,
					SortOrder sortOrder, Map<String, Object> filters) {
				if(isNotSuperAdmin()){
					filters.put("compound", getCurrentCompound());
				}
 				result = getSmartCompoundManagment().loadOrders(first,
						pageSize, sortField, sortOrder == SortOrder.ASCENDING,
						filters);
				this.setRowCount(getSmartCompoundManagment()
						.getNumOfOrdersRows(filters));

				return result;
			}

			@Override
			public void forEach(Consumer<? super Order> action) {
			}

			@Override
			public Spliterator<Order> spliterator() {
				return null;
			}
		};
	}
	
	public void addMarker(){
		if(selectedOrder!=null){//36.88,30.67
			simpleModel.getMarkers().clear();
		 LatLng coord1 = new LatLng(selectedOrder.getServiceLocationlattitude(), selectedOrder.getServiceLocationlongtude());   
	        //Basic marker
	        simpleModel.addOverlay(new Marker(coord1, selectedOrder.getServiceName()));
//	       System.out.println("addMarker my service is "+selectedOrder.getServiceName()+" cords: "+selectedOrder.getServiceLocationlattitude()+" , "+selectedOrder.getServiceLocationlongtude());
		}
	}
	
//	public List<Facility> completeFacilities(String searchParam)
//			throws SmartCompoundException {
//		Compound compound = getCurrentCompound();
//		facilities = smartCompoundManagment.getCompoundFacilites(compound,
//				searchParam.trim());
//		return facilities;
//	} 
	

	public List<Tenant> completeTenant(String query){
		Compound compound = getCurrentCompound();
		tenants = smartCompoundManagment.getCompoundTenants(compound, query);
		return tenants;
	}

	
	public List<Service> completeServices(String query) {
		Compound compound = getCurrentCompound();
		services = smartCompoundManagment.getCompoundServices(compound, query);
		List<Service> serviceActive = new ArrayList<Service>();
		for (Iterator iterator = services.iterator(); iterator.hasNext();) {
			Service service = (Service) iterator.next();
			if(service.getActivityStatusLookup().equals(Lookup.ACTIVE))
				serviceActive.add(service);
		}
		return serviceActive;
	}

	public void insertOrder() throws SmartCompoundException {
		
		if(selectedOrder.getCompound()==null) { // insert new order
			selectedOrder.setStatusLookup(Lookup.ORDER_NEW); 
			selectedOrder.setStatus(Lookup.ORDER_NEW.getLookupName());
			selectedOrder.setRequestDate(new Date());
			selectedOrder.setChannel("Portal");
			selectedOrder.setCreatedBy(getCurrentLoggedEmployee()); // session
			selectedOrder.setCompound(getCurrentCompound()); //session
		}
		if(selectedOrder.getStatusLookup().equals(Lookup.ORDER_DONE)){  // update Order
			selectedOrder.setAcceptanceDate(Utils.getStartOfDay(new Date()));
		}
//	    if( filter == null ) {
//	        return true;
//	    }
//
//	    if( value == null ) {
//	        return false;
//	    }
//
//	    return DateUtils.truncateEquals((Date) filter, (Date) value, Calendar.DATE);
//	}
		if(selectedOrder.getService().getIsFacilityRelated())
		{
			if(selectedOrder.getFacility()==null)
			{
			    RequestContext.getCurrentInstance().execute("showGrowlMessage('"+ getMessageValue(MessagesKeys.SMART_COMPOUND_PLEASE_SELECT_FACILITY_MESSAGE)+"');");
			    
				RequestContext.getCurrentInstance().addCallbackParam(
						"success", false);
			}
		}
		smartCompoundManagment.insertOrder(selectedOrder);
	}

	public SmartCompoundManagment getSmartCompoundManagment() {
		return smartCompoundManagment;
	}

	public void setSmartCompoundManagment(
			SmartCompoundManagment smartCompoundManagment) {
		this.smartCompoundManagment = smartCompoundManagment;
	}

	public Order getSelectedOrder() {
		
		return selectedOrder;
	}

	public void setSelectedOrder(Order selectedOrder) {
		if(selectedOrder == null)initiateNewOrder();
		this.selectedOrder = selectedOrder;
	}

	public LazyDataModel<Order> getLazyOrderModel() {
		return lazyOrderModel;
	}

	public void setLazyOrderModel(LazyDataModel<Order> lazyOrderModel) {
		this.lazyOrderModel = lazyOrderModel;
	}

	public List<Facility> getFacilities() {
		return facilities;
	}

	public void setFacilities(List<Facility> facilities) {
		this.facilities = facilities;
	}

	public List<Service> getServices() {
		return services;
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}

	public List<Tenant> getTenants() {
		return tenants;
	}

	public void setTenants(List<Tenant> tenants) {
		this.tenants = tenants;
	}

	public MapModel getSimpleModel() {
		return simpleModel;
	}

	public void setSimpleModel(MapModel simpleModel) {
		this.simpleModel = simpleModel;
	}

	public List<Lookup> getOrderStatusLookups() {
		return orderStatusLookups;
	}

	public void setOrderStatusLookups(List<Lookup> orderStatusLookups) {
		this.orderStatusLookups = orderStatusLookups;
	}

}
