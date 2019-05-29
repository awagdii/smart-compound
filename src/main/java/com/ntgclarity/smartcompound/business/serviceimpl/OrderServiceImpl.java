package com.ntgclarity.smartcompound.business.serviceimpl;

import java.util.List;
import java.util.Map;

import javax.jws.WebService;

//import org.springframework.stereotype.Service;
//import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.ntgclarity.smartcompound.business.service.OrderService;
import com.ntgclarity.smartcompound.common.entity.Compound;
import com.ntgclarity.smartcompound.common.entity.Facility;
import com.ntgclarity.smartcompound.common.entity.Order;
import com.ntgclarity.smartcompound.common.entity.Service;
import com.ntgclarity.smartcompound.common.entity.Tenant;
import com.ntgclarity.smartcompound.common.entity.Ticket;
import com.ntgclarity.smartcompound.common.exception.SmartCompoundException;
import com.ntgclarity.smartcompound.dataaccess.dao.OrderDAO;
import com.ntgclarity.smartcompound.dataaccess.dao.ServiceDAO;

@org.springframework.stereotype.Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDAO orderDAO;
	
	@Autowired
	private ServiceDAO serviceDAO;
	
	@Override
	public List<Order> getAllOrders() {
		return orderDAO.getAllOrders();
	}

	@Override
	public Order getOrder(Long id) {
		if(id !=null)
		{
			return orderDAO.getOrder(id);
		}
		return null;
	}
	/**
	 *methodCreater:nessma 
	 *create Order 
	 *this metohd will call orderDAO.insertOrder
	  **/
	@Override
	public Order insertOrder(Order order) throws SmartCompoundException{
		Service service = new Service();
		if(order.getService()!=null){
			service = serviceDAO.getService(order.getService().getId());
			order.setService(service);
			order.setServiceName(service.getServiceName());
		}
		return orderDAO.insertOrder(order);
	}
	@Override
	public Order updateOrder(Order order) {
		return orderDAO.updateOrder(order);
	}

	@Override
	public List<Order> loadOrders(int first, int pageSize,
			String sortField, boolean ascending, Map<String, Object> filters) {
		return orderDAO.loadOrders(first,pageSize,sortField,ascending,filters);
	}

	@Override
	public int getNumOfOrdersRows(Map<String, Object> filters) {
	
		return  orderDAO.getNumOfOrdersRows(filters);
	}
	
	@Override
	public List<Order> getAllOrdersRelatedToTenant(Tenant tenant) throws SmartCompoundException{
		return orderDAO.getAllOrdersRelatedToTenant(tenant);
	}
	
	/**Start of  Mai's part**/
	
	
	@Override
	public List<Service> getTenantCompoundServices(
			Compound compound, Tenant tenant,Facility facility) {
		// TODO Auto-generated method stub
		return orderDAO.getTenantCompoundServices(compound,tenant,facility);
	}

	@Override
	public List<Facility> getTenantCompoundFacilities(Tenant tenant,
			Compound compound) {
		// TODO Auto-generated method stub
		return orderDAO.getTenantCompoundFacilities(tenant,compound);
	}

	@Override
	public Order getCompoundTenantOrder(Compound compound, Tenant tenant,
			Service service, Facility facility) {
		// TODO Auto-generated method stub
		return orderDAO.getCompoundTenantOrder(compound, tenant, service, facility);
	}

	@Override
	public List<Service> getTenantServices(Compound compound, Tenant tenant) {
		// TODO Auto-generated method stub
		return orderDAO.getTenantServices(compound, tenant);
	}

	@Override
	public List<Order> getOrdersOfTenant(Tenant tenant) {
		// TODO Auto-generated method stub
		return orderDAO.getOrdersOfTenant(tenant);
	}
	@Override
	public List<Service> getTenantServicesRelatedToFacilty(
			Compound currentCompound, Tenant relatedTenant, Facility facility) {
		return orderDAO.getTenantServicesRelatedToFacilty(currentCompound, relatedTenant, facility);
	}

//	@Override
//	public List<Tenant> getOrdersDistinctTenant(Compound compound) {
//		// TODO Auto-generated method stub
//		return orderDAO.getOrdersDistinctTenant(compound);
//	}

	/**End of  Mai's part**/
	


}
