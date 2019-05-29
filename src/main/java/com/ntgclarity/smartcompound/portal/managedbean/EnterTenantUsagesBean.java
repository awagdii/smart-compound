package com.ntgclarity.smartcompound.portal.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;
import org.springframework.transaction.annotation.Transactional;

import com.ntgclarity.smartcompound.business.management.SmartCompoundManagment;
import com.ntgclarity.smartcompound.common.constatnt.MessagesKeys;
import com.ntgclarity.smartcompound.common.entity.BillCalculation;
import com.ntgclarity.smartcompound.common.entity.Compound;
import com.ntgclarity.smartcompound.common.entity.Facility;
import com.ntgclarity.smartcompound.common.entity.Order;
import com.ntgclarity.smartcompound.common.entity.Service;
import com.ntgclarity.smartcompound.common.entity.Tenant;
import com.ntgclarity.smartcompound.common.enums.BillCycleStatus;
import com.ntgclarity.smartcompound.common.exception.SmartCompoundException;
import com.ntgclarity.smartcompound.portal.base.BaseBean;

@ManagedBean
@ViewScoped
public class EnterTenantUsagesBean extends BaseBean implements Serializable {

	/**
	 * Author: Mai
	 */
	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{smartCompoundManagmentImpl}")
	private SmartCompoundManagment smartCompoundManagment;

	private List<Facility> facilities;
	private List<Service> services;
	private List<Tenant> tenants;
	 private Tenant tenant;
	 private Service service;
	 private Facility facility;
	private Order order;
	private BillCalculation billCalculation;
	private Boolean flag=false;
	@PostConstruct
	public void init() {
		
		initateBill();	
	}
	
	public void checkCompoundBillCycleStatus(){
		
		BillCycleStatus billCycleStatus=smartCompoundManagment.getCompoundBillCycleStatus(getCurrentCompound());
		if(billCycleStatus.equals(BillCycleStatus.BILL_CYCLE_HAS_STARTED)){
		
			flag=true;
			
		}else if(billCycleStatus.equals(BillCycleStatus.BILL_CYCLE_HAS_NOT_STARTED)){
			
			addInfoMessage(MessagesKeys.SMART_COMPOUND_START_BILLING_CYCLE_PAGE_NOT_STARTED_MESSAGE);
			
		}else if(billCycleStatus.equals(BillCycleStatus.BILL_CYCLE_HAS_ENDED)){
			
			addInfoMessage(MessagesKeys.SMART_COMPOUND_START_BILLING_CYCLE_PAGE_ALREADY_ENDED_MESSAGE);
			
		}
	}
	
	public void initateBill(){
		tenant = new Tenant();
		tenant.setUsername("");
		service = new Service();
		facility = new Facility();
		 order = new Order();
		billCalculation = new BillCalculation();
		facilities=new ArrayList<>();
		services=new ArrayList<>();
	}

	public List<Tenant> completeTenant(String query) {
		tenants = smartCompoundManagment.getDistinctTenantsFromBillCalculation(getCurrentCompound(), query.trim());
		return tenants;
	}
	
	public List<Facility> completeFacilities()throws SmartCompoundException {
		
		facilities = smartCompoundManagment.getTenantCompoundFacilities(
				tenant, getCurrentCompound());
		return facilities;
	}

	public List<Service> completeServices() {
		
		services = smartCompoundManagment.getTenantCompoundServices(getCurrentCompound(),tenant,facility);
		return services;
	}
	public void getTenantOrder(){
		
		Compound compound=getCurrentCompound();
		order=smartCompoundManagment.getCompoundTenantOrder(getCurrentCompound(), tenant, service, facility);
		billCalculation=smartCompoundManagment.getSpecificBillCalculation(getCurrentCompound(), service, order);
	}
	
	public void updateBillCalculation() throws SmartCompoundException{
		
		
		Double 	usageAmount=billCalculation.getCurrentMeterReading()-billCalculation.getPreviousMeterReading();
		if(usageAmount<0){throw new SmartCompoundException(MessagesKeys.SMART_COMPOUND_CALC_BILLING_PAGE_CURRENT_METER_READING_INVALID_VALUE_MESSAGE);}
		Double usageValue=smartCompoundManagment.getAllServiceUsageRate(getCurrentCompound(), service,usageAmount);
		billCalculation.setUsageAmount(usageAmount);
		billCalculation.setUsageValue(usageValue);
		billCalculation.setCalculationDate(new Date());
	//	billCalculation=smartCompoundManagment.updateBillCalculation(billCalculation);
		
		//update order meterLastReading 
		 order.setMeterLastReading(billCalculation.getCurrentMeterReading());
		 order.setMeterLastReadingDate(billCalculation.getCalculationDate());
		// smartCompoundManagment.updateOrder(order);
		 
		 smartCompoundManagment.updateOrderAndBillCalculation(billCalculation,order);

		 addInfoMessage(MessagesKeys.SMART_COMPOUND_CALC_BILLING_PAGE_CURRENT_BILL_UPDATED_INFO_MESSAGE);
		 initateBill();
	}
	public void resetBill(){
		
		initateBill();
	}

	public SmartCompoundManagment getSmartCompoundManagment() {
		return smartCompoundManagment;
	}

	public void setSmartCompoundManagment(
			SmartCompoundManagment smartCompoundManagment) {
		this.smartCompoundManagment = smartCompoundManagment;
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

	public BillCalculation getBillCalculation() {
		return billCalculation;
	}

	public void setBillCalculation(BillCalculation billCalculation) {
		this.billCalculation = billCalculation;
	}

	public Tenant getTenant() {
		return tenant;
	}

	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public Facility getFacility() {
		return facility;
	}

	public void setFacility(Facility facility) {
		this.facility = facility;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}
	

}
