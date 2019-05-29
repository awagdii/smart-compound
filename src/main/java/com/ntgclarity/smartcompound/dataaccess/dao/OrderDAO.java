package com.ntgclarity.smartcompound.dataaccess.dao;

import java.util.List;
import java.util.Map;

import com.ntgclarity.smartcompound.common.entity.Compound;
import com.ntgclarity.smartcompound.common.entity.Facility;
import com.ntgclarity.smartcompound.common.entity.Order;
import com.ntgclarity.smartcompound.common.entity.Tenant;
import com.ntgclarity.smartcompound.common.entity.Ticket;
import com.ntgclarity.smartcompound.common.exception.SmartCompoundException;
import com.ntgclarity.smartcompound.common.entity.Service;

/**Author: Nazer**/
public interface OrderDAO {

	List<Order> getAllOrders();

	Order getOrder(Long id);

	Order insertOrder(Order order) throws SmartCompoundException;

	Order updateOrder(Order order);
	List<Order> loadOrders(int first, int pageSize, String sortField,
			boolean ascending, Map<String, Object> filters);

	int getNumOfOrdersRows(Map<String, Object> filters);
	
	/**Start of  Mai's part**/
	


	List<Order> getAllOrdersRelatedToTenant(Tenant tenant)throws SmartCompoundException;

//	List<Facility> getTenantCompoundFacilities(Tenant tenant,
//			Compound compound, Service service, String searchParam);
	
	 Order getCompoundTenantOrder(Compound compound,Tenant tenant,Service service,Facility facility);

	List<Service> getTenantServices(Compound compound, Tenant tenant);

	List<Service>getTenantServicesRelatedToFacilty(Compound compound,Tenant tenant,Facility facility);
	List<Order> getTenantActiveOrders(Tenant tenant)throws SmartCompoundException;

	List<Service> getTenantCompoundServices(Compound compound, Tenant tenant, Facility facility);

	List<Facility> getTenantCompoundFacilities(Tenant tenant,
			Compound compound);

	//List<Tenant> getOrdersDistinctTenant(Compound compound);

	 /**End of  Mai's part**/
	
	List<Order> getOrdersOfTenant(Tenant tenant);
}
