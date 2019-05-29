package com.ntgclarity.smartcompound.business.service;

import java.util.List;
import java.util.Map;

import com.ntgclarity.smartcompound.common.entity.Compound;
import com.ntgclarity.smartcompound.common.entity.Facility;
import com.ntgclarity.smartcompound.common.entity.Tenant;
import com.ntgclarity.smartcompound.common.exception.SmartCompoundException;

/** Author: Heba **/

public interface FacilityService {

	List<Facility> getAllFacilities(Compound comp);

	Facility getFacility(Long id) throws SmartCompoundException;

	Facility insertFacility(Facility facility) throws SmartCompoundException;

	Facility updateFacility(Facility facility) throws SmartCompoundException;

	List<Facility> loadFacilities(int first, int pageSize, String sortField,
			boolean ascending, Map<String, Object> filters);

	int getNumOfFacilitiesRows(Map<String, Object> filters);

	List<Facility> getCompoundFacilites(Compound compound, String searchParam)
			throws SmartCompoundException;

	List<Facility> getAllFacilitiesRelatedToTenant(Tenant tenant) throws SmartCompoundException;

	List<Facility> getCompoundFacilites(Compound compound, Tenant tenant)throws SmartCompoundException;

}