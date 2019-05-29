package com.ntgclarity.smartcompound.dataaccess.dao;

import java.util.List;

import com.ntgclarity.smartcompound.common.entity.BillCalculation;
import com.ntgclarity.smartcompound.common.entity.Compound;
import com.ntgclarity.smartcompound.common.entity.Facility;
import com.ntgclarity.smartcompound.common.entity.Order;
import com.ntgclarity.smartcompound.common.entity.Service;
import com.ntgclarity.smartcompound.common.entity.Tenant;

/**Author: Mai**/
public interface BillCalculationDAO {
	
	public  List <Order> startCalculatingBill(Compound compound);

	List <BillCalculation>  getAllBillCalculations();

	BillCalculation updateBillCalculation(BillCalculation billCalculation);

	BillCalculation insertBillCalculation(BillCalculation billCalculation);

	BillCalculation getBillCalculation(Long id);

	BillCalculation getSpecificBillCalculation(Compound compound,
			Service service, Order order);

	Double getTotalAmountPerService(Compound compound, Service service);

	int emptyBillCalculationTable();

	List<BillCalculation> getBillCalculationsForTenant(Compound compound,
			Tenant tenant);

	BillCalculation getSpecificBillCalculationForOrder(Order order);

	List<Tenant> getDistinctTenantsFromBillCalculation(Compound compound, String searchParam);

//	List<Facility> getTenantFacilities(Tenant tenant, Compound compound);

}
