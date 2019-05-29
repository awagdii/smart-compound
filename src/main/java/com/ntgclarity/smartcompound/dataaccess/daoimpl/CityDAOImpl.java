package com.ntgclarity.smartcompound.dataaccess.daoimpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ntgclarity.smartcompound.common.entity.City;
import com.ntgclarity.smartcompound.common.entity.Country;
import com.ntgclarity.smartcompound.dataaccess.base.BaseDAO;
import com.ntgclarity.smartcompound.dataaccess.dao.CityDAO;

@Repository
public class CityDAOImpl extends BaseDAO implements CityDAO {

	@Override
	public List<City> getAllCities() {

		return (List<City>) super.getAll(City.class);
	}

	@Override
	public City getCity(Long id) {
		return (City) super.get(City.class, id);
	}

	@Override
	public City updateCity(City city) {
		// TODO Auto-generated method stub
		return (City) super.saveOrUpdate(city);
	}

	@Override
	public City insertCity(City city) {
		return (City) super.saveOrUpdate(city);
	}

	@Override
	public List<String> getAllCitiesAsString() {
		// TODO Auto-generated method stub
		Iterator<City> iterator = super.getAll(City.class).iterator();
		ArrayList<String> allCitiesAsString = new ArrayList<>();
		while (iterator.hasNext()) {
			allCitiesAsString.add(((City) iterator.next()).getName());
		}
		return allCitiesAsString;
	}

	@Override
	public List<City> getAllCitiesInCountry(Country country) {
		// TODO Auto-generated method stub
		String hql = "from City where   country_id =:countryId";

		return getCurrentSession().createQuery(hql)
				.setEntity("countryId", country).list();

	}

	@Override
	public List<String> getAllCitiesInCountryAsString(Country country) {
		// TODO Auto-generated method stub
		String hql = "from City where   country_id =:countryId";
		Iterator<City> iterator = getCurrentSession().createQuery(hql)
				.setEntity("countryId", country).list().iterator();
		ArrayList<String> allCitiesInCountryAsString = new ArrayList<>();
		while (iterator.hasNext()) {
			allCitiesInCountryAsString.add(((City) iterator.next()).getName());
		}
		return allCitiesInCountryAsString;
	}

}
