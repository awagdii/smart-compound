package com.ntgclarity.smartcompound.dataaccess.daoimpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.ntgclarity.smartcompound.common.entity.Nationality;
import com.ntgclarity.smartcompound.dataaccess.base.BaseDAO;
import com.ntgclarity.smartcompound.dataaccess.dao.NationalityDAO;

@Repository
public class NationalityDAOImpl extends BaseDAO implements NationalityDAO {

	@Override
	public List<Nationality> getAllNationalities() {

		return (List<Nationality>) super.getAll(Nationality.class);
	}

	@Override
	public Nationality getNationality(Long id) {
		return (Nationality) super.get(Nationality.class, id);
	}

	@Override
	public Nationality updateNationality(Nationality nationality) {
		// TODO Auto-generated method stub
		return (Nationality) super.saveOrUpdate(nationality);
	}

	@Override
	public Nationality insertNationality(Nationality nationality) {
		return (Nationality) super.saveOrUpdate(nationality);
	}

	@Override
	public List<String> getAllNationalitiesAsString() {
		// TODO Auto-generated method stub
		Iterator<Nationality> iterator = super.getAll(Nationality.class).iterator();
		ArrayList<String> allNationalitiesAsString = new ArrayList<>();
		while (iterator.hasNext()) {
			allNationalitiesAsString.add(((Nationality) iterator.next()).getName());
		}
		return allNationalitiesAsString;
	}

}
