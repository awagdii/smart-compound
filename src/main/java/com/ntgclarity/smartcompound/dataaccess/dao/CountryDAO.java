package com.ntgclarity.smartcompound.dataaccess.dao;

import java.util.List;
import java.util.Map;

import com.ntgclarity.smartcompound.common.entity.Country;
import com.ntgclarity.smartcompound.common.entity.MenuItem;

public interface CountryDAO {

	List<Country> getAllCountries();

	List<String> getAllCountriesAsString();

	Country getCountry(Long id);

	Country insertCountry(Country country);

	Country updateCountry(Country country);
}
