package com.ntgclarity.smartcompound.business.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntgclarity.smartcompound.business.service.BillService;
import com.ntgclarity.smartcompound.common.entity.Bill;
import com.ntgclarity.smartcompound.common.entity.Tenant;
import com.ntgclarity.smartcompound.dataaccess.dao.BillDAO;

/**Author: Mai**/

@Service
public class BillServiceImpl implements BillService{
	
	@Autowired
	private BillDAO billDAO;

	@Override
	public List<Bill> getAllBills() {
		// TODO Auto-generated method stub
		return billDAO.getAllBills();
	}

	@Override
	public Bill updateBill(Bill bill) {
		// TODO Auto-generated method stub
		return billDAO.updateBill(bill);
	}

	@Override
	public Bill insertBill(Bill bill) {
		// TODO Auto-generated method stub
		return billDAO.insertBill(bill);
	}

	@Override
	public Bill getBill(Long id) {
		// TODO Auto-generated method stub
		return billDAO.getBill(id);
	}

	@Override
	public List<Bill> loadBills(int first, int pageSize, String sortField,
			boolean ascending, Map<String, Object> filters) {
		// TODO Auto-generated method stub
		return billDAO.loadBills(first,pageSize,sortField,ascending,filters);
	}

	@Override
	public int getNumOfBillsRows(Map<String, Object> filters) {
		// TODO Auto-generated method stub
		return  billDAO.getNumOfBillsRows(filters);
	}

	@Override
	public List<Bill> getBillsOfTenant(Tenant tenant) {
		// TODO Auto-generated method stub
		return billDAO.getBillsOfTenant(tenant);
	}

}
