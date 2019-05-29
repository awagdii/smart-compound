package com.ntgclarity.smartcompound.business.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntgclarity.smartcompound.business.service.TenantFacilityService;
import com.ntgclarity.smartcompound.common.entity.Compound;
import com.ntgclarity.smartcompound.common.entity.Facility;
import com.ntgclarity.smartcompound.common.entity.Tenant;
import com.ntgclarity.smartcompound.dataaccess.dao.TenantFacilityDAO;

@Service
public class TenantFacilityServiceImpl implements TenantFacilityService {

	@Autowired
	private TenantFacilityDAO tenantFacilityDAO;

	@Override
	public List<Facility> getTenantCompoundFacilities(Tenant tenant,
			Compound compound, String searchParam) {
		// TODO Auto-generated method stub
		return tenantFacilityDAO.getTenantCompoundFacilities(tenant, compound,
				searchParam);

	}
}
