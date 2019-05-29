package com.ntgclarity.smartcompound.business.service;

import java.util.List;

import com.ntgclarity.smartcompound.common.entity.Compound;
import com.ntgclarity.smartcompound.common.entity.Service;
import com.ntgclarity.smartcompound.common.entity.ServiceUsageRate;

public interface ServiceUsageRateService {
	
	public Double getAllServiceUsageRate(Compound compound,
			Service service,Double usageAmount);

}
