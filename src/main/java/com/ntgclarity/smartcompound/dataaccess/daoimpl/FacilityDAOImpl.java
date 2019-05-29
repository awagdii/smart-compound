package com.ntgclarity.smartcompound.dataaccess.daoimpl;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ntgclarity.smartcompound.common.entity.Compound;
import com.ntgclarity.smartcompound.common.entity.Facility;
import com.ntgclarity.smartcompound.common.entity.Tenant;
import com.ntgclarity.smartcompound.common.exception.SmartCompoundException;
import com.ntgclarity.smartcompound.dataaccess.base.BaseDAO;
import com.ntgclarity.smartcompound.dataaccess.dao.FacilityDAO;

/**Author: Heba**/

@Repository
public class FacilityDAOImpl extends BaseDAO implements FacilityDAO {

	@Override
	public List<Facility> getAllFacilities(Compound comp) {
		Query  query=getCurrentSession().createQuery("select o from Facility o where o.compound.id=:compound");
		query.setParameter("compound", comp);
		List<Facility> result = query.list();
		return result;
	}

	@Override
	public Facility getFacility(Long id) {
		return  (Facility) super.get(Facility.class , id);
	}
	@Override
	public Facility updateFacility(Facility facility) {
		// TODO Auto-generated method stub
		return (Facility) super.saveOrUpdate(facility);
	}

	/**
	 *methodCreater:nessma 
	 *create Facility 
	 *this metohd will call saveOrUpdate() in the super class BaseDAO
	 
	  **/
	@Override
	public Facility insertFacility(Facility facility) {
		facility.setFacilityType(facility.getFacilityTypeLookup().getLookupName()); // added by Karim M.Fadel 
		return (Facility) super.saveOrUpdate(facility);
	}
	
	@Override
	public List<Facility> loadFacilities(int first, int pageSize,
			String sortField, boolean ascending, Map<String, Object> filters) {
			
		return super.load(Facility.class,first,pageSize,sortField,ascending,filters);
	}

	@Override
	public int getNumOfFacilitiesRows(Map<String, Object> filters) {
		
		return super.getNumOfRows(Facility.class,filters);
	}

	@Override
	public List<Facility> getCompoundFacilites(Compound compound,String searchParam) {
		Query query = getCurrentSession().createQuery
				("from "+Facility.class.getCanonicalName()
						+" x where x.compound =:compound AND "
						+ "CONCAT(LOWER(STR(x.buildingNumber)),LOWER(STR(x.floorNumber)),LOWER(STR(x.facilityNumber)))  LIKE :fullFacilityName");
		query.setParameter("compound", compound);
		query.setParameter("fullFacilityName", "%" + searchParam.trim() + "%");
		List list = query.list();
		return list;
	}

	@Override
	public List<Facility> getAllFacilitiesRelatedToTenant(Tenant tenant)
			throws SmartCompoundException {
		Query query = getCurrentSession().createQuery(
				"from " + Facility.class.getCanonicalName()
						+ " x where x.tenant =:tenant");
		query.setParameter("tenant", tenant);
		return  query.list();
	}

	@Override
	public List<Facility> getCompoundFacilites(Compound compound, Tenant tenant)throws SmartCompoundException {
		String sql = "from "+Facility.class.getCanonicalName()
				+" x where x.compound =:compound ";
		if(tenant!=null)
			sql += " AND x.tenant =:tenant";
		Query query = getCurrentSession().createQuery(sql);
		query.setParameter("compound", compound);
		if(tenant!=null)
			query.setParameter("tenant",tenant);
		List list = query.list();
		return list;
	}

}
