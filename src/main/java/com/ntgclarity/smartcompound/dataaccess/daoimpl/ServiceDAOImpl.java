package com.ntgclarity.smartcompound.dataaccess.daoimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ntgclarity.smartcompound.common.entity.Compound;
import com.ntgclarity.smartcompound.common.entity.Lookup;
import com.ntgclarity.smartcompound.common.entity.Service;
import com.ntgclarity.smartcompound.common.entity.ServiceUsageRate;
import com.ntgclarity.smartcompound.common.entity.Tenant;
import com.ntgclarity.smartcompound.dataaccess.base.BaseDAO;
import com.ntgclarity.smartcompound.dataaccess.dao.ServiceDAO;

/** Author: Nazer **/
@Repository
public class ServiceDAOImpl extends BaseDAO implements ServiceDAO {

	public List<Service> getAllServices(Compound comp) {
		Query query = getCurrentSession().createQuery(
				"select o from Service o where o.compound.id=" + comp.getId());
		List<Service> result = query.list();
		return result;
	}

	@Override
	public Service getService(Long id) {
		Service service = (Service) super.get(Service.class, id);
		List<ServiceUsageRate> rates = getServiceUsageRates(service.getId());
		service.setServiceUsageRates(rates);
		return service;
	}

	@Override
	public Service updateService(Service service) {
		// TODO Auto-generated method stub
		return (Service) super.saveOrUpdate(service);
	}

	/**
	 * methodCreater:nessma create Service this metohd will call saveOrUpdate()
	 * in the super class BaseDAO
	 **/
	@Override
	public Service insertService(Service service) {

		
		if (service.getFlatOrUsageLookup()!=null && !service.getFlatOrUsageLookup().equals(Lookup.USAGE)&&!(service.getId()==null)) {
			Query query = getCurrentSession().createQuery(
					"delete from " + ServiceUsageRate.class.getCanonicalName()
							+ " o where o.service =:service");

			query.setParameter("service", service);
			query.executeUpdate();

		}
		Service insertedService = (Service) super.saveOrUpdate(service);
		return insertedService;

	}

	@Override
	public List<Service> loadServices(int first, int pageSize,
			String sortField, boolean ascending, Map<String, Object> filters) {

		return super.load(Service.class, first, pageSize, sortField, ascending,
				filters);
	}

	@Override
	public int getNumOfServicesRows(Map<String, Object> filters) {

		return super.getNumOfRows(Service.class, filters);
	}

	@Override
	public List<Service> getCompoundServices(Compound compound,
			String searchParam) {
		// return (List<Service>) super.getAllByCompound(Service.class,
		// compound);
		Query query = getCurrentSession()
				.createQuery(
						"from "
								+ Service.class.getCanonicalName()
								+ " x where x.compound =:compound AND x.serviceName LIKE :serviceName");
		query.setParameter("compound", compound);
		query.setParameter("serviceName", "%" + searchParam + "%");
		return query.list();
	}

	@Override
	public List<ServiceUsageRate> insertServiceUsageRates(
			List<ServiceUsageRate> usageRates) {

		List<ServiceUsageRate> rates = new ArrayList<ServiceUsageRate>();
		for (ServiceUsageRate rate : usageRates) {
			rates.add((ServiceUsageRate) super.saveOrUpdate(rate));
		}
		return rates;
	}

	public List<ServiceUsageRate> getServiceUsageRates(Long serviceId) {
		Query query = getCurrentSession().createQuery(
				"select o from " + ServiceUsageRate.class.getCanonicalName()
						+ " o where o.service.id=" + serviceId);
		return query.list();
	}

	@Override
	public List<Service> getAllActiveServices(Compound currentCompound) {
		Query query = getCurrentSession().createQuery("select o from Service o where o.compound=:compound and o.activityStatusLookup =:activityStatusLookup ");
		query.setParameter("compound", currentCompound);
		query.setParameter("activityStatusLookup", Lookup.ACTIVE);
		return query.list();
		
	}

}
