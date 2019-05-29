package com.ntgclarity.smartcompound.business.serviceimpl;


import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ntgclarity.smartcompound.business.service.BillCalculationService;
import com.ntgclarity.smartcompound.common.constatnt.MessagesKeys;
import com.ntgclarity.smartcompound.common.entity.Bill;
import com.ntgclarity.smartcompound.common.entity.BillCalculation;
import com.ntgclarity.smartcompound.common.entity.BillCycleHistory;
import com.ntgclarity.smartcompound.common.entity.BillDetails;
import com.ntgclarity.smartcompound.common.entity.Compound;
import com.ntgclarity.smartcompound.common.entity.Employee;
import com.ntgclarity.smartcompound.common.entity.Lookup;
import com.ntgclarity.smartcompound.common.entity.Order;
import com.ntgclarity.smartcompound.common.entity.Tenant;
import com.ntgclarity.smartcompound.common.enums.BillCycleStatus;
import com.ntgclarity.smartcompound.common.exception.SmartCompoundException;
import com.ntgclarity.smartcompound.dataaccess.dao.BillCalculationDAO;
import com.ntgclarity.smartcompound.dataaccess.dao.BillCycleHistoryDAO;
import com.ntgclarity.smartcompound.dataaccess.dao.BillDAO;
import com.ntgclarity.smartcompound.dataaccess.dao.BillDetailsDAO;
import com.ntgclarity.smartcompound.dataaccess.dao.OrderDAO;
import com.ntgclarity.smartcompound.dataaccess.dao.TenantDAO;

/** Author: Mai **/

@org.springframework.stereotype.Service
public class BillCalculationServiceImpl implements BillCalculationService {

	@Autowired
	private BillCalculationDAO billCalculationDAO;

	@Autowired
	private OrderDAO orderDAO;

	@Autowired
	private BillDAO billDAO;

	@Autowired
	private BillDetailsDAO billDetailsDAO;

	@Autowired
	private TenantDAO tenantDAO;
	
	@Autowired
	private BillCycleHistoryDAO billCycleHistoryDAO;
	
	@Override
	public List<BillCalculation> startCalculatingBill(Compound compound) throws SmartCompoundException{
		
		Calendar calendar = Calendar.getInstance();
		int currentMonth=calendar.get(Calendar.MONTH)+1;
		int currentYear=calendar.get(Calendar.YEAR);
		BillCycleHistory billCycleHistory=billCycleHistoryDAO.billCycleHistoryExists(compound, currentMonth, currentYear);
		if(billCycleHistory ==null){
			
			billCycleHistory=new BillCycleHistory();
			billCycleHistory.setCompound(compound);
			billCycleHistory.setMonth(currentMonth);
			billCycleHistory.setYear(currentYear);
			
			billCycleHistoryDAO.insertBillCycleHistory(billCycleHistory);
			
			return startBillingCycle(compound);
		}else if(billCycleHistory.getHasEnded()==0){
			throw new SmartCompoundException(MessagesKeys.SMART_COMPOUND_START_BILLING_CYCLE_PAGE_ALREADY_STARTED_MESSAGE);
		}else{
			throw new  SmartCompoundException(MessagesKeys.SMART_COMPOUND_START_BILLING_CYCLE_PAGE_ALREADY_ENDED_MESSAGE);
		}	
	}

	//@Override
	private List<BillCalculation> startBillingCycle(Compound compound) {
		// TODO Auto-generated method stub
			
		List<Order> orders = billCalculationDAO.startCalculatingBill(compound);

		for (Order order : orders) {

			BillCalculation billCalculation = new BillCalculation();
			billCalculation.setOrder(order);
			billCalculation.setService(order.getService());
			billCalculation
					.setPreviousMeterReading(order.getMeterLastReading());
			billCalculation.setCompound(compound);
			billCalculation.setFacility(order.getFacility());
			
			if (order.getIsBiledBefore() == 0) {
				billCalculation.setInstallationValue(order.getService()
						.getInstallationPrice());
				billCalculation.setNrcValue((double) order.getService()
						.getNrc());
				order.setIsBiledBefore(1);
				orderDAO.updateOrder(order); //update Order IsBiledBefore column
			}
			billCalculation.setMrcValue((double) order.getService().getMrc());
	
			updateBillCalculation(billCalculation);
		}
		return getAllBillCalculations();
	}

	@Override
	public void endBillingCycle(Compound compound,Employee employee) throws SmartCompoundException{
		
		Calendar calendar = Calendar.getInstance();
		int currentMonth=calendar.get(Calendar.MONTH )+1;
		int currentYear=calendar.get(Calendar.YEAR);
		BillCycleHistory billCycleHistory=billCycleHistoryDAO.billCycleHistoryExists(compound, currentMonth, currentYear);
		
		if(billCycleHistory ==null){
			throw new SmartCompoundException(MessagesKeys.SMART_COMPOUND_START_BILLING_CYCLE_PAGE_NOT_STARTED_MESSAGE);	
		}else if(billCycleHistory.getHasEnded()==0) {
			billCycleHistory.setHasEnded(1);
			billCycleHistoryDAO.updateBillCycleHistory(billCycleHistory);
			continueEndBillingCycle(compound,employee);	
		}else{
			
			throw new SmartCompoundException(MessagesKeys.SMART_COMPOUND_START_BILLING_CYCLE_PAGE_ALREADY_ENDED_MESSAGE);
		}
		
		
	}
	
	
	//@Override
	private void continueEndBillingCycle(Compound compound,Employee employee)
			throws SmartCompoundException {
		Date currentDate = new Date();
		List<Tenant> tenants = tenantDAO.getAllTenants(compound);
		for (Tenant tenant : tenants) {
			Bill bill = new Bill();
			bill.setCompound(compound);
			bill.setTenant(tenant);
		//	bill.setCollectionDate(new Date());
			
			Calendar calendar = Calendar.getInstance();
		    int lastDate = calendar.getActualMaximum(Calendar.DATE);
		    calendar.set(Calendar.DATE, lastDate);
		    Date billTo=calendar.getTime();
		    bill.setBillTo(billTo);  
		    calendar.set(Calendar.DATE, 1);
		    Date billFrom=calendar.getTime();
		    bill.setBillFrom(billFrom);
		  
		    bill.setIssueDate(currentDate);
			billDAO.insertBill(bill);
			Double billTotalAmount = 0.0;

			List<Order> orders = orderDAO.getTenantActiveOrders(tenant);
			for (Order order : orders) {

				BillDetails billDetails = new BillDetails();
				billDetails.setBill(bill);
				billDetails.setCompound(compound);
				billDetails.setService(order.getService());
				billDetails.setFacility(order.getFacility());
				if (order.getService().getFlatOrUsageLookup()
						.equals(Lookup.USAGE)) {

					BillCalculation billCalculation = billCalculationDAO
							.getSpecificBillCalculationForOrder(order);
					billDetails.setUsageValue(billCalculation.getUsageValue());
					billDetails.setNrcValue(billCalculation.getNrcValue());
					billDetails.setInstallation(billCalculation
							.getInstallationValue());
					billDetails.setMrcValue(billCalculation.getMrcValue());
				} else if (order.getService().getFlatOrUsageLookup()
						.equals(Lookup.FLAT))

				{
					if (order.getIsBiledBefore() == 0) {
						billDetails.setInstallation(order.getService()
								.getInstallationPrice());
						billDetails.setNrcValue((double) order.getService()
								.getNrc());
						order.setIsBiledBefore(1);
						orderDAO.updateOrder(order);
					}
					billDetails.setMrcValue((double) order.getService()
							.getMrc());

				} else if (order.getService().getFlatOrUsageLookup()
						.equals(Lookup.ONE_TIME)) {
					billDetails.setNrcValue((double) order.getService()
							.getNrc());
					order.setStatusLookup(Lookup.DONE_AND_BILLED);
					orderDAO.updateOrder(order);

				}
				Double totalAmount = billDetails.getMrcValue()
						+ billDetails.getNrcValue()
						+ billDetails.getInstallation()
						+ billDetails.getUsageValue();
				billDetails.setTotalAmount(totalAmount);

				billDetailsDAO.insertBillDetails(billDetails);

				billTotalAmount += totalAmount;
			}
			bill.setTotalAmount(billTotalAmount);
			billDAO.updateBill(bill);
		}

		billCalculationDAO.emptyBillCalculationTable();
	}

	@Override
	public BillCalculation getSpecificBillCalculation(Compound compound,
			com.ntgclarity.smartcompound.common.entity.Service service,
			Order order) {
		// TODO Auto-generated method stub
		return billCalculationDAO.getSpecificBillCalculation(compound, service,
				order);
	}

	@Override
	public List<BillCalculation> getAllBillCalculations() {
		// TODO Auto-generated method stub
		return billCalculationDAO.getAllBillCalculations();
	}

	@Override
	public BillCalculation updateBillCalculation(BillCalculation billCalculation) {
		// TODO Auto-generated method stub
		return billCalculationDAO.updateBillCalculation(billCalculation);
	}

	@Override
	public BillCalculation insertBillCalculation(BillCalculation billCalculation) {
		// TODO Auto-generated method stub
		return billCalculationDAO.insertBillCalculation(billCalculation);
	}

	@Override
	public BillCalculation getBillCalculation(Long id) {
		// TODO Auto-generated method stub
		return billCalculationDAO.getBillCalculation(id);
	}

	@Override
	public List<Tenant> getDistinctTenantsFromBillCalculation(Compound compound, String searchParam) {
		// TODO Auto-generated method stub
		return billCalculationDAO.getDistinctTenantsFromBillCalculation(compound, searchParam);
	}
	
	@Override
	public void updateOrderAndBillCalculation(BillCalculation billCalculation,
			Order order){
		billCalculationDAO.updateBillCalculation(billCalculation);
		orderDAO.updateOrder(order);
		
	}
	
	@Override
	public BillCycleStatus  getCompoundBillCycleStatus(Compound compound){
		
		Calendar calendar = Calendar.getInstance();
		int currentMonth=calendar.get(Calendar.MONTH)+1;
		int currentYear=calendar.get(Calendar.YEAR);
		
		BillCycleHistory billCycleHistory=billCycleHistoryDAO.billCycleHistoryExists(compound, currentMonth, currentYear);
		if(billCycleHistory ==null){
			
				return BillCycleStatus.BILL_CYCLE_HAS_NOT_STARTED;
		}else if(billCycleHistory.getHasEnded()==0){
			return BillCycleStatus.BILL_CYCLE_HAS_STARTED;
		}else{
			return BillCycleStatus.BILL_CYCLE_HAS_ENDED;
		}	
		
	} 

}
