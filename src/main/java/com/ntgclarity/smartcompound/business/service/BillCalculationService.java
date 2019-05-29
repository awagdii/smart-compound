package com.ntgclarity.smartcompound.business.service;

import java.util.List;

import com.ntgclarity.smartcompound.common.entity.BillCalculation;
import com.ntgclarity.smartcompound.common.entity.Compound;
import com.ntgclarity.smartcompound.common.entity.Employee;
import com.ntgclarity.smartcompound.common.entity.Order;
import com.ntgclarity.smartcompound.common.entity.Service;
import com.ntgclarity.smartcompound.common.entity.Tenant;
import com.ntgclarity.smartcompound.common.enums.BillCycleStatus;
import com.ntgclarity.smartcompound.common.exception.SmartCompoundException;

/**Author: Mai**/
public interface BillCalculationService {
	
	List<BillCalculation> getAllBillCalculations();

	BillCalculation updateBillCalculation(BillCalculation billCalculation);

	BillCalculation insertBillCalculation(BillCalculation billCalculation);

	BillCalculation getBillCalculation(Long id);
	
	 List <BillCalculation> startCalculatingBill(Compound compound) throws SmartCompoundException;


	BillCalculation getSpecificBillCalculation(Compound compound,
				Service service, Order order);

	void endBillingCycle(Compound compound,Employee employee) throws SmartCompoundException;
	
	 List<Tenant> getDistinctTenantsFromBillCalculation(Compound compound, String searchParam);

	void updateOrderAndBillCalculation(BillCalculation billCalculation,
			Order order);

	BillCycleStatus getCompoundBillCycleStatus(Compound compound);
	
}
