package com.ntgclarity.smartcompound.business.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntgclarity.smartcompound.business.service.BillDetailsService;
import com.ntgclarity.smartcompound.common.entity.Bill;
import com.ntgclarity.smartcompound.common.entity.BillDetails;
import com.ntgclarity.smartcompound.dataaccess.dao.BillDetailsDAO;

/**Author: Mai**/

@Service
public class BillDetailsServiceImpl implements BillDetailsService {
	
	@Autowired
	private BillDetailsDAO billDetailsDAO;

	@Override
	public List<BillDetails> getAllBillDetails() {
		// TODO Auto-generated method stub
		return billDetailsDAO.getAllBillDetails();
	}

	@Override
	public BillDetails updateBillDetails(BillDetails billDetails) {
		// TODO Auto-generated method stub
		return billDetailsDAO.updateBillDetails(billDetails);
	}

	@Override
	public BillDetails insertBillDetails(BillDetails billDetails) {
		// TODO Auto-generated method stub
		return billDetailsDAO.insertBillDetails(billDetails);
	}

	@Override
	public BillDetails getBillDetails(Long id) {
		// TODO Auto-generated method stub
		return billDetailsDAO.getBillDetails(id);
	}

	@Override
	public List<BillDetails> getBillDetailsOfBill(Bill bill) {
		return billDetailsDAO.getBillDetailsOfBill(bill);
	}

	@Override
	public List<BillDetails> loadBillDetails(int first, int pageSize,
			String sortField, boolean ascending, Map<String, Object> filters) {
		// TODO Auto-generated method stub
		return billDetailsDAO.loadBillDetails(first, pageSize, sortField, ascending, filters);
	}

	@Override
	public int getNumOfBillDetailsRows(Map<String, Object> filters) {
		// TODO Auto-generated method stub
		return billDetailsDAO.getNumOfBillDetailsRows(filters);
	}

}
