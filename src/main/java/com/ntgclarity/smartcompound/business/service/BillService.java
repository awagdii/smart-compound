package com.ntgclarity.smartcompound.business.service;

import java.util.List;
import java.util.Map;

import com.ntgclarity.smartcompound.common.entity.Bill;
import com.ntgclarity.smartcompound.common.entity.Tenant;

/** Author: Mai **/

public interface BillService {

	List<Bill> getAllBills();

	Bill updateBill(Bill bill);

	Bill insertBill(Bill bill);

	Bill getBill(Long id);

	List<Bill> loadBills(int first, int pageSize, String sortField,
			boolean ascending, Map<String, Object> filters);

	int getNumOfBillsRows(Map<String, Object> filters);

	List<Bill> getBillsOfTenant(Tenant tenant);
}
