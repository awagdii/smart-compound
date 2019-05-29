package com.ntgclarity.smartcompound.business.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntgclarity.smartcompound.business.service.CompoundService;
import com.ntgclarity.smartcompound.business.service.LookupService;
import com.ntgclarity.smartcompound.common.entity.Compound;
import com.ntgclarity.smartcompound.common.entity.Lookup;
import com.ntgclarity.smartcompound.common.entity.LookupType;
import com.ntgclarity.smartcompound.dataaccess.dao.CompoundDAO;
import com.ntgclarity.smartcompound.dataaccess.dao.LookupDAO;

/** Author: Heba **/

@Service
public class LookupServiceImpl implements LookupService {
	
	private Map<Long,Lookup> lookupsMap = new HashMap();
	
	public Map<LookupType, List<Lookup>> lookupTypeLookupMap = new HashMap();
	
	@PostConstruct
	public void init()
	{
		List<LookupType> lookupTypes = getAllLookupTypes();
		for(LookupType lookupType: lookupTypes)
		{
			List<Lookup> lookups = lookupDAO.getLookups(lookupType);
			lookupTypeLookupMap.put(lookupType, lookups);
			for(Lookup lookup : lookups)
			{
				lookupsMap.put(lookup.getId() , lookup);
			}
		}
	}
	
	
	@Autowired
	private LookupDAO lookupDAO;

	@Override
	public List<Lookup> getLookups(LookupType lookupTypes) {
		return lookupTypeLookupMap.get(lookupTypes);
	}

	@Override
	public Lookup getLookups(Long id) {
		// TODO Auto-generated method stub
		return lookupDAO.getLookups(id);
	}

		
	@Override
	public Lookup getLookup(Long id) {
		return lookupsMap.get(id);
	}

	@Override
	public List<Lookup> getAllLookups() {
		return lookupDAO.getAllLookups();
	}

	@Override
	public List<LookupType> getAllLookupTypes() {
		return lookupDAO.getAllLookupTypes();
	}

}
