package com.ntgclarity.smartcompound.business.serviceimpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ntgclarity.smartcompound.business.service.CountryService;
import com.ntgclarity.smartcompound.common.entity.Country;
import com.ntgclarity.smartcompound.dataaccess.dao.CountryDAO;


@Service
public class CountryServiceImpl implements CountryService {

	@Autowired
	private CountryDAO countryDAO;
	
	@Override
	public List<Country> getAllCountries() {
		return countryDAO.getAllCountries();
	}

	@Override
	public Country getCountry(Long id) {
		if(id !=null)
		{
			return countryDAO.getCountry(id);
		}
		return null;
	}

	
	@Override
	public Country insertCountry(Country country) {
		
		return countryDAO.insertCountry(country);
	}
	@Override
	public Country updateCountry(Country country) {
		// TODO Auto-generated method stub
		return countryDAO.updateCountry(country);
		
	}

	@Override
	public List<String> getAllCountriesAsString() {
		// TODO Auto-generated method stub
		return countryDAO.getAllCountriesAsString();
	}
}
