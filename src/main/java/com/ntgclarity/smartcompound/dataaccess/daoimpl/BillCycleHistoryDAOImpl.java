package com.ntgclarity.smartcompound.dataaccess.daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ntgclarity.smartcompound.common.entity.BillCycleHistory;
import com.ntgclarity.smartcompound.common.entity.Compound;
import com.ntgclarity.smartcompound.dataaccess.base.BaseDAO;
import com.ntgclarity.smartcompound.dataaccess.dao.BillCycleHistoryDAO;

/**Author: Mai**/
@Repository
public class BillCycleHistoryDAOImpl extends BaseDAO implements BillCycleHistoryDAO {

	@Override
	public BillCycleHistory updateBillCycleHistory(
			BillCycleHistory billCycleHistory) {
		// TODO Auto-generated method stub
		return (BillCycleHistory) super.saveOrUpdate(billCycleHistory);
	}

	@Override
	public BillCycleHistory insertBillCycleHistory(
			BillCycleHistory billCycleHistory) {
		// TODO Auto-generated method stub
		return (BillCycleHistory) super.saveOrUpdate(billCycleHistory);
	}

	@Override
	public BillCycleHistory getBillCycleHistory(Long id) {
		// TODO Auto-generated method stub
		return (BillCycleHistory) super.get(BillCycleHistory.class, id);
	}

	@Override
	public List<BillCycleHistory> getAllBillCycleHistorys() {
		// TODO Auto-generated method stub
		
		return (List<BillCycleHistory>)super.getAll(BillCycleHistory.class);
	}
	
	@Override
	public BillCycleHistory billCycleHistoryExists(Compound compound,Integer month, Integer year){
		
		Query query = getCurrentSession()
				.createQuery(
						"from BillCycleHistory b where b.compound=:compound AND b.month=:month AND b.year=:year");
		query.setParameter("compound", compound);
		query.setParameter("month", month);
		query.setParameter("year", year);
		BillCycleHistory billCycleHistory = (BillCycleHistory) query.uniqueResult();
		return billCycleHistory;
	}

}
