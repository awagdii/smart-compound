package com.ntgclarity.smartcompound.portal.managedbean;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import com.ntgclarity.smartcompound.business.management.SmartCompoundManagment;
import com.ntgclarity.smartcompound.common.constatnt.MessagesKeys;
import com.ntgclarity.smartcompound.common.entity.BillCalculation;
import com.ntgclarity.smartcompound.common.exception.SmartCompoundException;
import com.ntgclarity.smartcompound.portal.base.BaseBean;

@ManagedBean
@ViewScoped
public class BillingCycleManagementBean extends BaseBean implements Serializable {

	/**
	 * Author: Mai
	 */
	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{smartCompoundManagmentImpl}")
	private SmartCompoundManagment smartCompoundManagment;

	public SmartCompoundManagment getSmartCompoundManagment() {
		return smartCompoundManagment;
	}

	public void setSmartCompoundManagment(
			SmartCompoundManagment smartCompoundManagment) {
		this.smartCompoundManagment = smartCompoundManagment;
	}

	public void startBillingCycle() throws SmartCompoundException {

		List<BillCalculation> billCalculationList = smartCompoundManagment
				.startCalculatingBill(getCurrentCompound());

		addInfoMessage(MessagesKeys.SMART_COMPOUND_START_BILLING_CYCLE_PAGE_INFO_MESSAGE);
	}

	public void endBillingCycle() throws SmartCompoundException {

		smartCompoundManagment.endBillingCycle(getCurrentCompound(),getCurrentLoggedEmployee());	
		addInfoMessage(MessagesKeys.SMART_COMPOUND_END_BILLING_CYCLE_PAGE_INFO_MESSAGE);

	}

}
