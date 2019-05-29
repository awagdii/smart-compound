package com.ntgclarity.smartcompound.business.service;

import java.util.List;
import java.util.Map;

import com.ntgclarity.smartcompound.common.entity.Compound;
import com.ntgclarity.smartcompound.common.entity.Tenant;
import com.ntgclarity.smartcompound.common.exception.SmartCompoundException;

/**Author: Heba**/

public interface TenantService {

	List<Tenant> getAllTenants(Compound comp);

	Tenant getTenant(Long id);

	Tenant insertTenant(Tenant tenant);

	Tenant updateTenant(Tenant tenant);
	
	List<Tenant> loadTenants(int first, int pageSize, String sortField,
			boolean ascending, Map<String, Object> filters);

	int getNumOfTenantsRows(Map<String, Object> filters);

	List<Tenant> getCompoundTenants(Compound compound,String query);

	Tenant getTenant(String username, String password)throws SmartCompoundException;

	Tenant getTenantByEmail(String email)throws SmartCompoundException;
	Tenant getTenantByUsername(String username);

}
