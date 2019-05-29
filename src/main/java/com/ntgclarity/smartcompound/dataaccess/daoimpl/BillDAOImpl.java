package com.ntgclarity.smartcompound.dataaccess.daoimpl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.RandomStringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ntgclarity.smartcompound.common.entity.Bill;
import com.ntgclarity.smartcompound.common.entity.Compound;
import com.ntgclarity.smartcompound.common.entity.Order;
import com.ntgclarity.smartcompound.common.entity.Tenant;
import com.ntgclarity.smartcompound.dataaccess.base.BaseDAO;
import com.ntgclarity.smartcompound.dataaccess.dao.BillDAO;

/** Author: Mai **/
@Repository
public class BillDAOImpl extends BaseDAO implements BillDAO {

	@Override
	public List<Bill> getAllBills() {
		// TODO Auto-generated method stub
		return (List<Bill>) super.getAll(Bill.class);
	}

	@Override
	public Bill updateBill(Bill bill) {
		// TODO Auto-generated method stub
		return (Bill) super.saveOrUpdate(bill);
	}

	@Override
	public Bill insertBill(Bill bill) {
		// TODO Auto-generated method stub
		bill.setBillNumber(Long.valueOf(RandomStringUtils.randomNumeric(7)));
		return (Bill) super.saveOrUpdate(bill);
	}

	@Override
	public Bill getBill(Long id) {
		// TODO Auto-generated method stub
		return (Bill) super.get(Bill.class, id);
	}

	@Override
	public List<Bill> loadBills(int first, int pageSize, String sortField,
			boolean ascending, Map<String, Object> filters) {
		// TODO Auto-generated method stub
		return (List<Bill>) super.load(Bill.class, first, pageSize, sortField,
				ascending, filters);
	}

	@Override
	public int getNumOfBillsRows(Map<String, Object> filters) {
		// TODO Auto-generated method stub
		return super.getNumOfRows(Bill.class, filters);
	}

	@Override
	public List<Bill> getBillsOfTenant(Tenant tenant) {
		Query query = getCurrentSession().createQuery(
				"from " + Bill.class.getCanonicalName()
						+ " x where x.tenant =:relatedTenant ");
		query.setParameter("relatedTenant", tenant);
		return query.list();
	}

}
