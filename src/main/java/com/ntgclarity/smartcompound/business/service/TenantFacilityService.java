package com.ntgclarity.smartcompound.business.service;

import java.util.List;

import com.ntgclarity.smartcompound.common.entity.Compound;
import com.ntgclarity.smartcompound.common.entity.Facility;
import com.ntgclarity.smartcompound.common.entity.Tenant;

public interface TenantFacilityService {

	public List<Facility> getTenantCompoundFacilities(Tenant tenant,Compound compound,String searchParam);
}
