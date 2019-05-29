package com.ntgclarity.smartcompound.dataaccess.daoimpl;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ntgclarity.smartcompound.common.entity.Bill;
import com.ntgclarity.smartcompound.common.entity.BillDetails;
import com.ntgclarity.smartcompound.dataaccess.base.BaseDAO;
import com.ntgclarity.smartcompound.dataaccess.dao.BillDetailsDAO;


/**Author: Mai**/
@Repository
public class BillDetailsDAOImpl extends BaseDAO implements BillDetailsDAO {

	@Override
	public List<BillDetails> getAllBillDetails() {
		// TODO Auto-generated method stub
		return (List<BillDetails>) super.getAll(BillDetails.class);
	}

	@Override
	public BillDetails updateBillDetails(BillDetails billDetails) {
		// TODO Auto-generated method stub
		return (BillDetails) super.saveOrUpdate(billDetails);
	}

	@Override
	public BillDetails insertBillDetails(BillDetails billDetails) {
		// TODO Auto-generated method stub
		return (BillDetails) super.saveOrUpdate(billDetails);
	}

	@Override
	public BillDetails getBillDetails(Long id) {
		// TODO Auto-generated method stub
		return (BillDetails) super.get(BillDetails.class, id);
	}

	@Override
	public List<BillDetails> getBillDetailsOfBill(Bill bill) {
		Query query = getCurrentSession().createQuery(
				"from " + BillDetails.class.getCanonicalName()+" x where x.bill =:bill") ;
		query.setParameter("bill", bill);
		List<BillDetails> billDetails = (List<BillDetails>) query.list();
		return  billDetails;
	}

	@Override
	public List<BillDetails> loadBillDetails(int first, int pageSize,
			String sortField, boolean ascending, Map<String, Object> filters) {
		// TODO Auto-generated method stub
		return super.load(BillDetails.class, first, pageSize, sortField, ascending, filters);
	}

	@Override
	public int getNumOfBillDetailsRows(Map<String, Object> filters) {
		// TODO Auto-generated method stub
		return super.getNumOfRows(BillDetails.class, filters);
	}

}
