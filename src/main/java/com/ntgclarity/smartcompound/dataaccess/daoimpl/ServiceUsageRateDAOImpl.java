package com.ntgclarity.smartcompound.dataaccess.daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ntgclarity.smartcompound.common.entity.Compound;
import com.ntgclarity.smartcompound.common.entity.Service;
import com.ntgclarity.smartcompound.common.entity.ServiceUsageRate;
import com.ntgclarity.smartcompound.dataaccess.base.BaseDAO;
import com.ntgclarity.smartcompound.dataaccess.dao.ServiceUsageRateDAO;

@Repository
public class ServiceUsageRateDAOImpl extends BaseDAO implements ServiceUsageRateDAO {
	
	@Override
	public List<ServiceUsageRate> getAllServiceUsageRate(Compound compound,Service service){
		

		Query query = getCurrentSession()
				.createQuery(
						"from ServiceUsageRate s where s.compound =:compound AND s.service=:service");
		query.setParameter("compound", compound);
		query.setParameter("service", service);
		
		List<ServiceUsageRate> serviceUsageRates=query.list();
		return serviceUsageRates;
		
	}
	
	

}
