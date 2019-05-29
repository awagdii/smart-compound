package com.ntgclarity.smartcompound.business.service;

import java.util.List;
import java.util.Map;

import com.ntgclarity.smartcompound.common.entity.Compound;
import com.ntgclarity.smartcompound.common.entity.Service;
import com.ntgclarity.smartcompound.common.entity.ServiceUsageRate;
import com.ntgclarity.smartcompound.common.exception.SmartCompoundException;

/**Author: Nazer**/

public interface ServiceService {

	List<Service> getAllServices(Compound comp);

	Service getService(Long id);

	Service insertService(Service service) throws SmartCompoundException;

	Service updateService(Service service) throws SmartCompoundException;
	List<Service> loadServices(int first, int pageSize, String sortField,
			boolean ascending, Map<String, Object> filters);

	int getNumOfServicesRows(Map<String, Object> filters);

	List<com.ntgclarity.smartcompound.common.entity.Service> getCompoundServices(
			Compound compound,String query);

	List<ServiceUsageRate> insertServiceUsageRates(
			List<ServiceUsageRate> usageRates);

	List<Service> getAllActiveServices(Compound currentCompound);

}
