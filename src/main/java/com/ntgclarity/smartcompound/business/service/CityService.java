package com.ntgclarity.smartcompound.business.service;

import java.util.List;
import java.util.Map;

import com.ntgclarity.smartcompound.common.entity.City;
import com.ntgclarity.smartcompound.common.entity.Compound;
import com.ntgclarity.smartcompound.common.entity.Country;
import com.ntgclarity.smartcompound.common.entity.Group;
import com.ntgclarity.smartcompound.common.entity.MenuItem;


public interface CityService {

	List<City> getAllCities();

	List<City> getAllCitiesInCountry(Country country);
	
	List<String> getAllCitiesInCountryAsString(Country country);

	List<String> getAllCitiesAsString();

	City getCity(Long id);

	City insertCity(City city);

	City updateCity(City city);

}
