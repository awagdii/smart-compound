package com.ntgclarity.smartcompound.business.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntgclarity.smartcompound.business.service.CityService;
import com.ntgclarity.smartcompound.common.entity.City;
import com.ntgclarity.smartcompound.common.entity.Country;
import com.ntgclarity.smartcompound.dataaccess.dao.CityDAO;


@Service
public class CityServiceImpl implements CityService {

	@Autowired
	private CityDAO cityDAO;
	
	@Override
	public List<City> getAllCities() {
		return cityDAO.getAllCities();
	}

	@Override
	public City getCity(Long id) {
		if(id !=null)
		{
			return cityDAO.getCity(id);
		}
		return null;
	}

	
	@Override
	public City insertCity(City city) {
		
		return cityDAO.insertCity(city);
	}
	@Override
	public City updateCity(City city) {
		// TODO Auto-generated method stub
		return cityDAO.updateCity(city);
		
	}

	@Override
	public List<String> getAllCitiesAsString() {
		// TODO Auto-generated method stub
		return cityDAO.getAllCitiesAsString();
	}

	@Override
	public List<City> getAllCitiesInCountry(Country country) {
		// TODO Auto-generated method stub
		return cityDAO.getAllCitiesInCountry(country);
	}

	@Override
	public List<String> getAllCitiesInCountryAsString(Country country) {
		// TODO Auto-generated method stub
		return cityDAO.getAllCitiesInCountryAsString(country);
	}
}
