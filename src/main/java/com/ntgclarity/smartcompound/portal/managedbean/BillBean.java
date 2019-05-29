package com.ntgclarity.smartcompound.portal.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Spliterator;
import java.util.function.Consumer;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.ToggleEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.ntgclarity.smartcompound.business.management.SmartCompoundManagment;
import com.ntgclarity.smartcompound.common.entity.Bill;
import com.ntgclarity.smartcompound.common.entity.BillDetails;
import com.ntgclarity.smartcompound.common.entity.Compound;
import com.ntgclarity.smartcompound.common.entity.Service;
import com.ntgclarity.smartcompound.common.entity.Tenant;
import com.ntgclarity.smartcompound.common.entity.Ticket;
import com.ntgclarity.smartcompound.common.entity.TicketHistory;
import com.ntgclarity.smartcompound.portal.base.BaseBean;
import com.ntgclarity.smartcompound.portal.velocity.BillVelocity;

@ManagedBean
@ViewScoped
public class BillBean extends BaseBean implements Serializable {

	@ManagedProperty(value = "#{smartCompoundManagmentImpl}")
	private SmartCompoundManagment smartCompoundManagment;
	private LazyDataModel<Bill> lazyBillModel;
	private List<BillDetails> billDetailsforSelectedBill;
	private List<Bill> billList = new ArrayList<Bill>();
	private Bill selectedBill;
	private List<String> compoundNamesList = new ArrayList<String>();
	private static final long serialVersionUID = 1L;

	@PostConstruct
	public void init() {
		initiateNewBill();
		LoadData();
	}
	


	private void initiateNewBill() {
		selectedBill = new Bill();

	}

	
	private void LoadData() {
		lazyBillModel = new LazyDataModel<Bill>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			private List<Bill> result;

			@Override
			public Bill getRowData(String rowKey) {
				for (Bill bill : result) {
					if (bill.getId().toString().equals(rowKey))
						return bill;
				}

				return null;
			}

			@Override
			public Object getRowKey(Bill bill) {
				return bill.getId();
			}

			@Override
			public List<Bill> load(int first, int pageSize, String sortField,
					SortOrder sortOrder, Map<String, Object> filters) {

				result = getSmartCompoundManagment().loadBills(first, pageSize,
						sortField, sortOrder == SortOrder.ASCENDING, filters);
				this.setRowCount(getSmartCompoundManagment().getNumOfBillsRows(
						filters));

				return result;
			}

			@Override
			public void forEach(Consumer<? super Bill> action) {
				// TODO Auto-generated method stub

			}

			@Override
			public Spliterator<Bill> spliterator() {
				// TODO Auto-generated method stub
				return null;
			}

		};

	}

	public void billExpanded(ToggleEvent event) {
		Bill bill = (Bill) event.getData();
		billDetailsforSelectedBill= smartCompoundManagment.getBillDetailsOfBill(bill);
	}
	public void printBill() {
		Tenant tenant = new Tenant();
		tenant.setId(40L);
		tenant.setUsername("nessma");
		tenant.setAddress("zayed");

		tenant.setPhoneNumber1("12345789");

		BillDetails billDetails = new BillDetails();
		billDetails.setBill(selectedBill);
		billDetails.setCompound(getCurrentCompound());
		billDetails.setId(1L);
		billDetails.setInstallation(33.45);
		billDetails.setMrcValue(10.12);
		billDetails.setNrcValue(3.2);
		billDetails.setTotalAmount(100.00);
		billDetails.setUsageValue(5.5);
		Service s1=new Service();
		s1.setServiceName("ServiceNumber1");
//		billDetails.setService(s1);
		selectedBill.setTenant(tenant);
		BillDetails bill2=new BillDetails();
		bill2.setBill(selectedBill);
		bill2.setCompound(getCurrentCompound());
		bill2.setId(1L);
		bill2.setInstallation(33.45);
		bill2.setMrcValue(10.12);
		bill2.setNrcValue(3.2);
		Service s2=new Service();
		
		s1.setServiceName("ServiceNumber2");
//		billDetails.setService(s2);
		bill2.setTotalAmount(100.00);
		bill2.setUsageValue(5.5);
		List<BillDetails> b = new ArrayList<BillDetails>();
		b.add(billDetails);
		b.add(bill2);
		selectedBill.setBillDetails(b);

		BillVelocity.initiate(selectedBill);
	}

	public SmartCompoundManagment getSmartCompoundManagment() {
		return smartCompoundManagment;
	}

	public void setSmartCompoundManagment(
			SmartCompoundManagment smartCompoundManagment) {
		this.smartCompoundManagment = smartCompoundManagment;
	}

	public LazyDataModel<Bill> getLazyBillModel() {
		return lazyBillModel;
	}

	public void setLazyBillModel(LazyDataModel<Bill> lazyBillModel) {
		this.lazyBillModel = lazyBillModel;
	}

	public List<Bill> getBillList() {
		return billList;
	}

	public void setBillList(List<Bill> billList) {
		this.billList = billList;
	}

	public Bill getSelectedBill() {
		return selectedBill;
	}

	public void setSelectedBill(Bill selectedBill) {
		this.selectedBill = selectedBill;
	}

	public List<String> getCompoundNamesList() {
		return compoundNamesList;
	}

	public void setCompoundNamesList(List<String> compoundNamesList) {
		this.compoundNamesList = compoundNamesList;
	}

	public List<BillDetails> getBillDetailsforSelectedBill() {
		return billDetailsforSelectedBill;
	}

	public void setBillDetailsforSelectedBill(
			List<BillDetails> billDetailsforSelectedBill) {
		this.billDetailsforSelectedBill = billDetailsforSelectedBill;
	}

}
