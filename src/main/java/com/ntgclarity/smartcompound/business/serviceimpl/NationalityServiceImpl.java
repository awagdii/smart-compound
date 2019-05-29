package com.ntgclarity.smartcompound.business.serviceimpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ntgclarity.smartcompound.business.service.NationalityService;
import com.ntgclarity.smartcompound.common.entity.Nationality;
import com.ntgclarity.smartcompound.dataaccess.dao.NationalityDAO;


@Service
public class NationalityServiceImpl implements NationalityService {

	@Autowired
	private NationalityDAO nationalityDAO;
	
	@Override
	public List<Nationality> getAllNationalities() {
		return nationalityDAO.getAllNationalities();
	}

	@Override
	public Nationality getNationality(Long id) {
		if(id !=null)
		{
			return nationalityDAO.getNationality(id);
		}
		return null;
	}

	
	@Override
	public Nationality insertNationality(Nationality nationality) {
		
		return nationalityDAO.insertNationality(nationality);
	}
	@Override
	public Nationality updateNationality(Nationality nationality) {
		// TODO Auto-generated method stub
		return nationalityDAO.updateNationality(nationality);
		
	}

	@Override
	public List<String> getAllNationalitiesAsString() {
		// TODO Auto-generated method stub
		return nationalityDAO.getAllNationalitiesAsString();
	}
}
