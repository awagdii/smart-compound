package com.ntgclarity.smartcompound.dataaccess.daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ntgclarity.smartcompound.common.entity.Compound;
import com.ntgclarity.smartcompound.common.entity.Facility;
import com.ntgclarity.smartcompound.common.entity.Tenant;
import com.ntgclarity.smartcompound.dataaccess.base.BaseDAO;
import com.ntgclarity.smartcompound.dataaccess.dao.TenantFacilityDAO;

@Repository
public class TenantFacilityDAOImpl extends BaseDAO implements TenantFacilityDAO {

	@Override
	public List<Facility> getTenantCompoundFacilities(Tenant tenant,Compound compound,String searchParam) {
		Query query = getCurrentSession().createQuery(
				"select x.facility from TenantFacility x where x.compound =:compound AND x.tenant=:tenant AND "
						+ " CONCAT(LOWER(STR(x.facility.buildingNumber)),LOWER(STR(x.facility.floorNumber)),LOWER(STR(x.facility.facilityNumber)))  LIKE :fullFacilityName");
		query.setParameter("compound", compound);
		query.setParameter("tenant", tenant);
		query.setParameter("fullFacilityName",  "%" + searchParam.trim() + "%");
		List result = query.list();
		return result;
	}

}
