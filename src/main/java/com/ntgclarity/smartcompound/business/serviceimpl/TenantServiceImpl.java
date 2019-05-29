package com.ntgclarity.smartcompound.business.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntgclarity.smartcompound.business.service.TenantService;
import com.ntgclarity.smartcompound.common.entity.Compound;
import com.ntgclarity.smartcompound.common.entity.Tenant;
import com.ntgclarity.smartcompound.common.exception.SmartCompoundException;
import com.ntgclarity.smartcompound.dataaccess.dao.TenantDAO;


/**Author: Heba**/

@Service
public class TenantServiceImpl implements TenantService {

	@Autowired
	private TenantDAO tenantDAO;
	
	@Override
	public List<Tenant> getAllTenants(Compound comp) {
		return tenantDAO.getAllTenants(comp);
	}

	@Override
	public Tenant getTenant(Long id) {
		if(id !=null)
		{
			return tenantDAO.getTenant(id);
		}
		return null;
	}
	/**
	 *methodCreater:nessma 
	 *create Tenant 
	 *this metohd will call TenantDAO.insertTenant
	 
	  **/
	
	@Override
	public Tenant insertTenant(Tenant tenant) {
		
		return tenantDAO.insertTenant(tenant);
	}
	
	@Override
	public Tenant updateTenant(Tenant tenant) {
		return tenantDAO.updateTenant(tenant);
		
	}

	@Override
	public List<Tenant> loadTenants(int first, int pageSize,
			String sortField, boolean ascending, Map<String, Object> filters) {
		return tenantDAO.loadTenants(first,pageSize,sortField,ascending,filters);
	}

	@Override
	public int getNumOfTenantsRows(Map<String, Object> filters) {
	
		return  tenantDAO.getNumOfTenantsRows(filters);
	}

	@Override
	public List<Tenant> getCompoundTenants(Compound compound,String query) {
		return tenantDAO.getCompoundTenants(compound,query);
	}

	@Override
	public Tenant getTenant(String username, String password) throws SmartCompoundException {
		return tenantDAO.getTenant(username,password);
	}

	@Override
	public Tenant getTenantByEmail(String email) throws SmartCompoundException {
		return tenantDAO.getTenantByEmail(email);
	}

	@Override
	public Tenant getTenantByUsername(String username) {
		// TODO Auto-generated method stub
		return tenantDAO.getTenantByUsername(username);
	}


}
