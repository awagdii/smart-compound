package com.ntgclarity.smartcompound.dataaccess.daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ntgclarity.smartcompound.common.entity.BillCalculation;
import com.ntgclarity.smartcompound.common.entity.Compound;
import com.ntgclarity.smartcompound.common.entity.Facility;
import com.ntgclarity.smartcompound.common.entity.Lookup;
import com.ntgclarity.smartcompound.common.entity.LookupType;
import com.ntgclarity.smartcompound.common.entity.Order;
import com.ntgclarity.smartcompound.common.entity.Service;
import com.ntgclarity.smartcompound.common.entity.Tenant;
import com.ntgclarity.smartcompound.dataaccess.base.BaseDAO;
import com.ntgclarity.smartcompound.dataaccess.dao.BillCalculationDAO;

/** Author: Mai **/

@Repository
public class BillCalculationDAOImpl extends BaseDAO implements
		BillCalculationDAO {

	@Override
	public List<BillCalculation> getAllBillCalculations() {

		return (List<BillCalculation>) super.getAll(BillCalculation.class);
	}

	@Override
	public BillCalculation updateBillCalculation(BillCalculation billCalculation) {
		// TODO Auto-generated method stub
		return (BillCalculation) super.saveOrUpdate(billCalculation);
	}

	@Override
	public BillCalculation insertBillCalculation(BillCalculation billCalculation) {

		return (BillCalculation) super.saveOrUpdate(billCalculation);

	}

	@Override
	public BillCalculation getBillCalculation(Long id) {
		return (BillCalculation) super.get(BillCalculation.class, id);
	}

	@Override
	public List<Order> startCalculatingBill(Compound compound) {
		// TODO Auto-generated method stub

		Query query = getCurrentSession()
				.createQuery(
						"from Order o where o.compound=:compound AND o.statusLookup=:statusLookup  AND o.service.activityStatusLookup=:activityStatusLookup AND o.service.flatOrUsageLookup=:flatOrUsageLookup");
		query.setParameter("compound", compound);
		query.setParameter("statusLookup", Lookup.DONE);
		query.setParameter("flatOrUsageLookup", Lookup.USAGE);
		query.setParameter("activityStatusLookup", Lookup.ACTIVE);
		List<Order> orders = query.list();
		return orders;
	}

	@Override
	public BillCalculation getSpecificBillCalculation(Compound compound,
			Service service, Order order) {

		Query query = getCurrentSession()
				.createQuery(
						"from BillCalculation b where  b.compound=:compound AND b.order=:order AND b.service=:service ");
		query.setParameter("compound", compound);
		query.setParameter("order", order);
		query.setParameter("service", service);

		BillCalculation billCalculation = (BillCalculation) query
				.uniqueResult();

		return billCalculation;

	}

	@Override
	public Double getTotalAmountPerService(Compound compound, Service service) {

		Query query = getCurrentSession()
				.createQuery(
						"select sum(b.usageValue) from BillCalculation b where  b.compound=:compound AND b.service=:service");
		query.setParameter("compound", compound);
		query.setParameter("service", service);

		Double total = (Double) query.uniqueResult();

		return total;

	}

	@Override
	public int emptyBillCalculationTable() {

		Query query = getCurrentSession().createQuery("delete BillCalculation");
		int result = query.executeUpdate();
		return result;

	}

	@Override
	public List<BillCalculation> getBillCalculationsForTenant(
			Compound compound, Tenant tenant) {

		Query query = getCurrentSession()
				.createQuery(
						"from BillCalculation b where  b.compound=:compound AND  b.order.tenant=:tenant");
		query.setParameter("compound", compound);
		query.setParameter("tenant", tenant);

		List<BillCalculation> billCalculations = query.list();

		return billCalculations;

	}
	
	@Override
	public BillCalculation getSpecificBillCalculationForOrder( Order order) {

		Query query = getCurrentSession()
				.createQuery(
						"from BillCalculation b where b.order=:order  ");
		query.setParameter("order", order);

		BillCalculation billCalculation = (BillCalculation) query
				.uniqueResult();

		return billCalculation;

	}
	
	@Override
	public List<Tenant> getDistinctTenantsFromBillCalculation(Compound compound, String searchParam) {
		//x.email LIKE :searchParam OR
		Query query = getCurrentSession()
				.createQuery(
						"select distinct b.order.tenant from BillCalculation b where  b.compound=:compound AND b.order.tenant.username LIKE :searchParam");
		query.setParameter("compound", compound);
		query.setParameter("searchParam", "%" + searchParam + "%");
		List<Tenant> tenants = query.list();

		return tenants;
	}
}
