package com.ntgclarity.smartcompound.business.service;

import java.util.List;
import java.util.Map;

import com.ntgclarity.smartcompound.common.entity.Compound;
import com.ntgclarity.smartcompound.common.entity.Group;
import com.ntgclarity.smartcompound.common.entity.MenuItem;
import com.ntgclarity.smartcompound.common.entity.Nationality;



public interface NationalityService {

	List<Nationality> getAllNationalities();

	List<String> getAllNationalitiesAsString();

	Nationality getNationality(Long id);

	Nationality insertNationality(Nationality nationality);

	Nationality updateNationality(Nationality nationality);

}
