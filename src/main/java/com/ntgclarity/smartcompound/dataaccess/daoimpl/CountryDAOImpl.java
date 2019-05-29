package com.ntgclarity.smartcompound.dataaccess.daoimpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.ntgclarity.smartcompound.common.entity.Country;
import com.ntgclarity.smartcompound.dataaccess.base.BaseDAO;
import com.ntgclarity.smartcompound.dataaccess.dao.CountryDAO;

@Repository
public class CountryDAOImpl extends BaseDAO implements CountryDAO {

	@Override
	public List<Country> getAllCountries() {

		return (List<Country>) super.getAll(Country.class);
	}

	@Override
	public Country getCountry(Long id) {
		return (Country) super.get(Country.class, id);
	}

	@Override
	public Country updateCountry(Country country) {
		// TODO Auto-generated method stub
		return (Country) super.saveOrUpdate(country);
	}

	@Override
	public Country insertCountry(Country country) {
		return (Country) super.saveOrUpdate(country);
	}

	@Override
	public List<String> getAllCountriesAsString() {
		// TODO Auto-generated method stub
		Iterator<Country> iterator = super.getAll(Country.class).iterator();
		ArrayList<String> allCountriesAsString = new ArrayList<>();
		while (iterator.hasNext()) {
			allCountriesAsString.add(((Country) iterator.next()).getName());
		}
		return allCountriesAsString;
	}

}
