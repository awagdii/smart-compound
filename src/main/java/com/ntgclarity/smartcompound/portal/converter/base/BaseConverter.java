package com.ntgclarity.smartcompound.portal.converter.base;

import com.ntgclarity.smartcompound.business.management.SmartCompoundManagment;
import com.ntgclarity.smartcompound.common.base.BaseEntity;
import com.ntgclarity.smartcompound.common.entity.Employee;
import com.ntgclarity.smartcompound.common.spring.applicationcontext.SpringApplicationContext;
import com.ntgclarity.smartcompound.common.utils.Utils;

public class BaseConverter {


	public SmartCompoundManagment getSmartCompoundManagment() {
		return SpringApplicationContext.getApplicationContext().getBean(SmartCompoundManagment.class);
	}


	
}
