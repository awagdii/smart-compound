package com.ntgclarity.smartcompound.dataaccess.daoimpl;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ntgclarity.smartcompound.common.entity.Compound;
import com.ntgclarity.smartcompound.common.entity.Facility;
import com.ntgclarity.smartcompound.common.entity.Lookup;
import com.ntgclarity.smartcompound.common.entity.Order;
import com.ntgclarity.smartcompound.common.entity.Service;
import com.ntgclarity.smartcompound.common.entity.Tenant;
import com.ntgclarity.smartcompound.common.entity.Ticket;
import com.ntgclarity.smartcompound.common.exception.SmartCompoundException;
import com.ntgclarity.smartcompound.dataaccess.base.BaseDAO;
import com.ntgclarity.smartcompound.dataaccess.dao.OrderDAO;

/** Author: Nazer **/
@Repository
public class OrderDAOImpl extends BaseDAO implements OrderDAO {

	@Override
	public List<Order> getAllOrders() {

		return (List<Order>) super.getAll(Order.class);
	}

	@Override
	public Order getOrder(Long id) {
		return (Order) super.get(Order.class, id);
	}

	@Override
	public Order updateOrder(Order order) {
		// TODO Auto-generated method stub
		return (Order) super.saveOrUpdate(order);
	}

	/**
	 * methodCreater:nessma create Order this metohd will call saveOrUpdate() in
	 * the super class BaseDAO
	 **/
	@Override
	public Order insertOrder(Order order) throws SmartCompoundException{
		Order newOrder = (Order) super.saveOrUpdate(order);
		if(newOrder==null)
			throw new SmartCompoundException();
		return newOrder;
	}

	@Override
	public List<Order> loadOrders(int first, int pageSize, String sortField,
			boolean ascending, Map<String, Object> filters) {
		Class<Order> cls = Order.class;
		return super.load(cls, first, pageSize, sortField, ascending, filters);
	}

	@Override
	public int getNumOfOrdersRows(Map<String, Object> filters) {

		return super.getNumOfRows(Order.class, filters);
	}

	@Override
	public List<Order> getAllOrdersRelatedToTenant(Tenant tenant)
			throws SmartCompoundException {
		Query query = getCurrentSession().createQuery(
				"from " + Order.class.getCanonicalName()
						+ " x where x.tenant =:tenant");
		query.setParameter("tenant", tenant);
		List<Order> orders = query.list();
		if (orders == null)
			throw new SmartCompoundException();
		return orders;
	}

	/** Start of Mai's part **/

	@Override
	public List<Service> getTenantCompoundServices(Compound compound, Tenant tenant,Facility facility) {
		String sql = "Select o.service from Order o where o.compound =:compound AND  o.statusLookup=:statusLookup AND o.service.flatOrUsageLookup=:flatOrUsageLookup AND o.service.activityStatusLookup=:activityStatusLookup";
		if(tenant!=null) 
			sql +=" AND o.tenant=:tenant ";
		if(facility!=null)
			sql +=" AND o.facility=:facility ";
		Query query = getCurrentSession().createQuery(sql);
		
		query.setParameter("compound", compound);
		query.setParameter("flatOrUsageLookup",Lookup.USAGE);
		query.setParameter("statusLookup", Lookup.DONE);
		query.setParameter("activityStatusLookup", Lookup.ACTIVE);
	//	query.setParameter("serviceName", "%" + searchParam + "%");
		if(tenant!=null) 
			query.setParameter("tenant", tenant);
		if(facility!=null)
			query.setParameter("facility", facility);
		List list = query.list();
		return list;
	}

	@Override
	public List<Facility> getTenantCompoundFacilities(Tenant tenant,
			Compound compound) {
		String sql = "Select distinct  o.facility from Order o where o.compound =:compound AND  o.statusLookup=:statusLookup AND o.service.flatOrUsageLookup=:flatOrUsageLookup AND o.service.activityStatusLookup=:activityStatusLookup";
		if(tenant!=null)
			sql += " AND o.tenant=:tenant ";
		Query query = getCurrentSession().createQuery(sql);
		
		query.setParameter("compound", compound);
		query.setParameter("statusLookup", Lookup.DONE);
		query.setParameter("flatOrUsageLookup",Lookup.USAGE);
		query.setParameter("activityStatusLookup", Lookup.ACTIVE);
		//query.setParameter("service", service);
	//	query.setParameter("fullFacilityName", "%" + searchParam.trim() + "%");
		if(tenant!=null)
			query.setParameter("tenant", tenant);
		
		List list = query.list();
		return list;
	}

	@Override
	public Order getCompoundTenantOrder(Compound compound, Tenant tenant,
			Service service, Facility facility) {

		Query query = getCurrentSession()
				.createQuery(
						"from Order o where o.compound =:compound AND o.tenant=:tenant AND  o.service=:service AND o.facility=:facility");
		query.setParameter("tenant", tenant);
		query.setParameter("service", service);
		query.setParameter("facility", facility);
		query.setParameter("compound", compound);
		Order order = (Order) query.uniqueResult();
		return (Order) query.uniqueResult();

	}

	@Override
	public List<Service> getTenantServices(Compound compound, Tenant tenant) {
		Query query = getCurrentSession()
				.createQuery(
						"select o.service from Order o  where  o.compound=:compound AND o.tenant=:tenant");
		query.setParameter("compound", compound);
		query.setParameter("tenant", tenant);
		List<Service> services = query.list();

		return services;

	}
	@Override
	public List<Order> getTenantActiveOrders(Tenant tenant)
			throws SmartCompoundException {
		Query query = getCurrentSession().createQuery(
				"from " + Order.class.getCanonicalName()
						+ " x where x.tenant =:tenant AND x.statusLookup=:statusLookup");
		query.setParameter("tenant", tenant);
		query.setParameter("statusLookup", Lookup.DONE);
		List<Order> orders = query.list();
		if (orders == null)
			throw new SmartCompoundException();
		return orders;
	}

	/** End of Mai's part **/
	
	
	
	@Override
	public List<Order> getOrdersOfTenant(Tenant tenant) {
		Query query = getCurrentSession().createQuery(
				"from " + Order.class.getCanonicalName()
						+ " x where x.tenant =:relatedTenant ");
		query.setParameter("relatedTenant", tenant);
		return  query.list();
	}

	@Override
	public List<Service> getTenantServicesRelatedToFacilty(Compound compound,
			Tenant tenant, Facility facility) {
		
		Query query=getCurrentSession().createQuery("select distinct (o.service) from Order o where o.compound=:compound AND o.tenant=:tenant AND o.facility=:facility");
		query.setParameter("compound",compound);
		query.setParameter("tenant", tenant);
		query.setParameter("facility",facility);
		List<Service>services=query.list();
		return services;
	
	}
	
	
	
	
	
	
	
	
}
