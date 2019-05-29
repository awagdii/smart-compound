package com.ntgclarity.smartcompound.business.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntgclarity.smartcompound.business.service.FacilityService;
import com.ntgclarity.smartcompound.common.entity.Compound;
import com.ntgclarity.smartcompound.common.entity.Facility;
import com.ntgclarity.smartcompound.common.entity.Tenant;
import com.ntgclarity.smartcompound.common.exception.SmartCompoundException;
import com.ntgclarity.smartcompound.dataaccess.dao.FacilityDAO;



/**Author: Heba**/

@Service
public class FacilityServiceImpl implements FacilityService {

	@Autowired
	private FacilityDAO facilityDAO;
	
	@Override
	public List<Facility> getAllFacilities(Compound comp) {
		return facilityDAO.getAllFacilities(comp);
	}

	@Override
	public Facility getFacility(Long id)throws SmartCompoundException {
		if(id !=null)
		{
			return facilityDAO.getFacility(id);
		}
		return null;
	}
	/**
	 *methodCreater:nessma 
	 *create Facility 
	 *this metohd will call FacilityDAO.insertFacility
	 
	  **/
	@Override
	public Facility insertFacility(Facility facility)throws SmartCompoundException {
		
		return facilityDAO.insertFacility(facility);
	}
	@Override
	public Facility updateFacility(Facility facility) throws SmartCompoundException {
		// TODO Auto-generated method stub
		return facilityDAO.updateFacility(facility);
		
	}

	@Override
	public List<Facility> loadFacilities(int first, int pageSize,
			String sortField, boolean ascending, Map<String, Object> filters) {
		return facilityDAO.loadFacilities(first,pageSize,sortField,ascending,filters);
	}

	@Override
	public int getNumOfFacilitiesRows(Map<String, Object> filters){
	
		return  facilityDAO.getNumOfFacilitiesRows(filters);
	}

	@Override
	public List<Facility> getCompoundFacilites(Compound compound,String searchParam) throws SmartCompoundException {
		return facilityDAO.getCompoundFacilites(compound,searchParam);
	}

	@Override
	public List<Facility> getAllFacilitiesRelatedToTenant(Tenant tenant)
			throws SmartCompoundException {
		return facilityDAO.getAllFacilitiesRelatedToTenant(tenant);
	}

	@Override
	public List<Facility> getCompoundFacilites(Compound compound, Tenant tenant)throws SmartCompoundException {
		return facilityDAO.getCompoundFacilites(compound,tenant);
	}


}