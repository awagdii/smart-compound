package com.ntgclarity.smartcompound.business.serviceimpl;

import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntgclarity.smartcompound.business.service.ServiceService;
import com.ntgclarity.smartcompound.common.entity.Compound;
import com.ntgclarity.smartcompound.common.entity.ServiceUsageRate;
import com.ntgclarity.smartcompound.common.exception.SmartCompoundException;
import com.ntgclarity.smartcompound.common.entity.Compound;
import com.ntgclarity.smartcompound.dataaccess.dao.ServiceDAO;

/**Author: Nazer**/

@Service
public class ServiceServiceImpl implements ServiceService {

	@Autowired
	private ServiceDAO serviceDAO;

	@Override

	public List<com.ntgclarity.smartcompound.common.entity.Service> getAllServices(
			Compound comp) {
		return serviceDAO.getAllServices(comp);
	}

	@Override
	public com.ntgclarity.smartcompound.common.entity.Service getService(Long id) {
		if (id != null) {
			return serviceDAO.getService(id);
		}
		return null;
	}

	/**
	 * methodCreater:nessma create Service this metohd will call
	 * serviceDAO.insertService
	 **/
	@Override
	public com.ntgclarity.smartcompound.common.entity.Service insertService(
			com.ntgclarity.smartcompound.common.entity.Service service)
			throws SmartCompoundException {

		service = serviceDAO.insertService(service);
		return service;
	}

	@Override
	public com.ntgclarity.smartcompound.common.entity.Service updateService(
			com.ntgclarity.smartcompound.common.entity.Service service) {
		return serviceDAO.updateService(service);

	}

	@Override
	public List<com.ntgclarity.smartcompound.common.entity.Service> loadServices(
			int first, int pageSize, String sortField, boolean ascending,
			Map<String, Object> filters) {
		return serviceDAO.loadServices(first, pageSize, sortField, ascending,
				filters);
	}

	@Override
	public int getNumOfServicesRows(Map<String, Object> filters) {

		return serviceDAO.getNumOfServicesRows(filters);
	}

	@Override
	public List<com.ntgclarity.smartcompound.common.entity.Service> getCompoundServices(
			Compound compound,String query) {
		
		return serviceDAO.getCompoundServices(compound,query);
	}

	@Override
	public List<ServiceUsageRate> insertServiceUsageRates(
			List<ServiceUsageRate> usageRates) {
		return serviceDAO.insertServiceUsageRates(usageRates);
	}

	@Override
	public List<com.ntgclarity.smartcompound.common.entity.Service> getAllActiveServices(
			Compound currentCompound) {
		// TODO Auto-generated method stub
		return serviceDAO.getAllActiveServices(currentCompound);
	}

}