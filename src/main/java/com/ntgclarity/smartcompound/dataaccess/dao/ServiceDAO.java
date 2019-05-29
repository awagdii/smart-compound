package com.ntgclarity.smartcompound.dataaccess.dao;

import java.util.List;
import java.util.Map;

import com.ntgclarity.smartcompound.common.entity.Compound;
import com.ntgclarity.smartcompound.common.entity.Service;
import com.ntgclarity.smartcompound.common.entity.ServiceUsageRate;
/**Author: Nazer**/

public interface ServiceDAO {

	List<Service> getAllServices(Compound comp);

	Service getService(Long id);

	Service insertService(Service service);

	Service updateService(Service service);
	List<Service> loadServices(int first, int pageSize, String sortField,
			boolean ascending, Map<String, Object> filters);

	int getNumOfServicesRows(Map<String, Object> filters);

	List<com.ntgclarity.smartcompound.common.entity.Service> getCompoundServices(
			Compound compound,String query);

	List<ServiceUsageRate> insertServiceUsageRates(
			List<ServiceUsageRate> usageRates);

	List<com.ntgclarity.smartcompound.common.entity.Service> getAllActiveServices(
			Compound currentCompound);
	
	

}
