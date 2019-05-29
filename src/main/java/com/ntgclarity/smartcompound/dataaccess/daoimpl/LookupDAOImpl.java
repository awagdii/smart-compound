package com.ntgclarity.smartcompound.dataaccess.daoimpl;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ntgclarity.smartcompound.common.entity.Compound;
import com.ntgclarity.smartcompound.common.entity.Employee;
import com.ntgclarity.smartcompound.common.entity.Facility;
import com.ntgclarity.smartcompound.common.entity.Lookup;
import com.ntgclarity.smartcompound.common.entity.LookupType;
import com.ntgclarity.smartcompound.dataaccess.base.BaseDAO;
import com.ntgclarity.smartcompound.dataaccess.dao.CompoundDAO;
import com.ntgclarity.smartcompound.dataaccess.dao.LookupDAO;

/** Author: Heba **/

@Repository
public class LookupDAOImpl extends BaseDAO implements LookupDAO {

	@Override
	public List<Lookup> getLookups(LookupType lookupTypes) {

		Query query = getCurrentSession().createQuery(
				"from " + Lookup.class.getCanonicalName()
						+ " o where o.lookupType =:lookupType");

		query.setParameter("lookupType", lookupTypes);

		List<Lookup>  result = (List<Lookup>) query.list();
		return result ;
	}

	@Override
	public Lookup getLookups(Long id) {
		// TODO Auto-generated method stub
		return (Lookup) super.get(Lookup.class, id);
	}

	@Override
	public Lookup getLookup(Long id) {
		return (Lookup) super.get(Lookup.class, id);
	}

	@Override
	public List<Lookup> getAllLookups() {
		// TODO Auto-generated method stub
		return super.getAll(Lookup.class);
	}

	@Override
	public List<LookupType> getAllLookupTypes() {
		// TODO Auto-generated method stub
		return super.getAll(LookupType.class);
	}

}
