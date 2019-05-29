package com.ntgclarity.smartcompound.dataaccess.daoimpl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.RandomStringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ntgclarity.smartcompound.common.entity.Compound;
import com.ntgclarity.smartcompound.common.entity.Tenant;
import com.ntgclarity.smartcompound.common.exception.SmartCompoundException;
import com.ntgclarity.smartcompound.dataaccess.base.BaseDAO;
import com.ntgclarity.smartcompound.dataaccess.dao.TenantDAO;

/**Author: Heba**/

@Repository
public class TenantDAOImpl extends BaseDAO implements TenantDAO {

	@Override
	public List<Tenant> getAllTenants(Compound comp) {

	Query  query=getCurrentSession().createQuery("select o from Tenant o where o.compound.id= :x");
	query.setParameter("x", comp.getId());
	List<Tenant> result = query.list();
//	System.out.println("result size in dao"+result.size());
	return result;
	}

	@Override
	public Tenant getTenant(Long id) {
		return  (Tenant) super.get(Tenant.class , id);
	}
	@Override
	public Tenant updateTenant(Tenant tenant) {
		// TODO Auto-generated method stub
		return (Tenant) super.saveOrUpdate(tenant);
	}

	/**
	 *methodCreater:nessma 
	 *create Tenant 
	 *this metohd will call saveOrUpdate() in the super class BaseDAO
	 
	  **/
	@Override
	public Tenant insertTenant(Tenant tenant) {
		
		return (Tenant) super.saveOrUpdate(tenant);
		
		
	}
	@Override
	public List<Tenant> loadTenants(int first, int pageSize,
			String sortField, boolean ascending, Map<String, Object> filters) {
			
		return super.load(Tenant.class,first,pageSize,sortField,ascending,filters);
	}

	@Override
	public int getNumOfTenantsRows(Map<String, Object> filters) {
		
		return super.getNumOfRows(Tenant.class,filters);
	}

	@Override
	public List<Tenant> getCompoundTenants(Compound compound,String searchParam) {
//		return (List<Tenant>) super.getAllByCompound(Tenant.class, compound);
		Query query = getCurrentSession().createQuery(
				"from " + Tenant.class.getCanonicalName()
						+ " x where x.compound =:compound AND x.username LIKE :searchParam");
		//x.email LIKE :searchParam OR
		query.setParameter("compound", compound);
		query.setParameter("searchParam", "%" + searchParam + "%");
		return  query.list();
	}

	@Override
	public Tenant getTenant(String username, String password) throws SmartCompoundException {
		Query query = getCurrentSession().createQuery(
				"from " + Tenant.class.getCanonicalName()
						+ " x where x.password =:password AND x.username =:username");
		query.setParameter("password", password);
		query.setParameter("username", username);
		Tenant tenant= (Tenant)query.uniqueResult();
		if(tenant == null)
			throw new SmartCompoundException("couldn't get tenant");
		return  tenant;
	}

	@Override
	public Tenant getTenantByEmail(String email) throws SmartCompoundException {
		Query query = getCurrentSession().createQuery(
				"from " + Tenant.class.getCanonicalName()
						+ " x where x.email =:email");
		query.setParameter("email", email);
		Tenant tenant= (Tenant)query.uniqueResult();
		if(tenant == null)
			throw new SmartCompoundException();
		tenant.setPassword(RandomStringUtils.randomAlphanumeric(8));
		super.saveOrUpdate(tenant);
		return tenant;
	}

	@Override
	public Tenant getTenantByUsername(String username) {
		// TODO Auto-generated method stub
		Query query = getCurrentSession().createQuery(
				"from " + Tenant.class.getCanonicalName()
						+ " x where x.username =:username");
		query.setParameter("username", username);
		Tenant tenant= (Tenant)query.uniqueResult();
		return tenant;
	}	

}
