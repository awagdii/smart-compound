package com.ntgclarity.smartcompound.dataaccess.dao;

import java.util.List;

import com.ntgclarity.smartcompound.common.entity.Compound;
import com.ntgclarity.smartcompound.common.entity.Facility;
import com.ntgclarity.smartcompound.common.entity.Tenant;

public interface TenantFacilityDAO {
	
	
	public List<Facility> getTenantCompoundFacilities(Tenant tenant,Compound compound,String searchParam);

}
