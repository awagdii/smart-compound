package com.ntgclarity.smartcompound.business.service;

import java.util.List;
import java.util.Map;

import com.ntgclarity.smartcompound.common.entity.Compound;
import com.ntgclarity.smartcompound.common.entity.Country;
import com.ntgclarity.smartcompound.common.entity.Group;
import com.ntgclarity.smartcompound.common.entity.MenuItem;


public interface CountryService {

	List<Country> getAllCountries();

	List<String> getAllCountriesAsString();

	Country getCountry(Long id);

	Country insertCountry(Country country);

	Country updateCountry(Country country);

}
