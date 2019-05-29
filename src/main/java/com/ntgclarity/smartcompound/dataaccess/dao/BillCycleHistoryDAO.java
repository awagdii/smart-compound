package com.ntgclarity.smartcompound.dataaccess.dao;

import java.util.List;
import com.ntgclarity.smartcompound.common.entity.BillCycleHistory;
import com.ntgclarity.smartcompound.common.entity.Compound;

/**Author: Mai**/
public interface BillCycleHistoryDAO {
	
	BillCycleHistory updateBillCycleHistory(BillCycleHistory billCycleHistory);

	BillCycleHistory insertBillCycleHistory(BillCycleHistory billCycleHistory);
	
	BillCycleHistory getBillCycleHistory(Long id);

	List <BillCycleHistory>  getAllBillCycleHistorys();
	
	BillCycleHistory billCycleHistoryExists(Compound compound,Integer month, Integer year);

}
