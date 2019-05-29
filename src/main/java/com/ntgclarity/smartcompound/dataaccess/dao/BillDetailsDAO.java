package com.ntgclarity.smartcompound.dataaccess.dao;

import java.util.List;
import java.util.Map;

import com.ntgclarity.smartcompound.common.entity.Bill;
import com.ntgclarity.smartcompound.common.entity.BillDetails;
import com.ntgclarity.smartcompound.common.entity.TicketHistory;

/**Author: Mai**/
public interface BillDetailsDAO {

	List<BillDetails> getAllBillDetails();

	BillDetails updateBillDetails(BillDetails billDetails);

	BillDetails insertBillDetails(BillDetails billDetails);

	BillDetails getBillDetails(Long id);

	List<BillDetails> getBillDetailsOfBill(Bill bill);

	List<BillDetails> loadBillDetails(int first, int pageSize, String sortField,
			boolean ascending, Map<String, Object> filters);

	int getNumOfBillDetailsRows(Map<String, Object> filters);
}