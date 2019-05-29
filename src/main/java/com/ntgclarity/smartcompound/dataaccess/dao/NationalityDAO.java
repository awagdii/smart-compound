package com.ntgclarity.smartcompound.dataaccess.dao;

import java.util.List;
import java.util.Map;

import com.ntgclarity.smartcompound.common.entity.Nationality;
import com.ntgclarity.smartcompound.common.entity.Compound;
import com.ntgclarity.smartcompound.common.entity.Group;
import com.ntgclarity.smartcompound.common.entity.MenuItem;


public interface NationalityDAO {

	List<Nationality> getAllNationalities();

	List<String> getAllNationalitiesAsString();

	Nationality getNationality(Long id);

	Nationality insertNationality(Nationality nationality);

	Nationality updateNationality(Nationality nationality);

}
