package com.ntgclarity.smartcompound.dataaccess.dao;

import java.util.List;

import com.ntgclarity.smartcompound.common.entity.Compound;
import com.ntgclarity.smartcompound.common.entity.Service;
import com.ntgclarity.smartcompound.common.entity.ServiceUsageRate;

public interface ServiceUsageRateDAO {

	List<ServiceUsageRate> getAllServiceUsageRate(Compound compound,
			Service service);

}
