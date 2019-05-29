package com.ntgclarity.smartcompound.business.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntgclarity.smartcompound.business.service.SystemConfigurationService;
import com.ntgclarity.smartcompound.common.entity.SystemConfiguration;
import com.ntgclarity.smartcompound.dataaccess.dao.SystemConfigurationDAO;

/** Author: Mai **/

@Service
public class SystemConfigurationServiceImpl implements
		SystemConfigurationService {

	@Autowired
	private SystemConfigurationDAO systemConfigurationDAO;

	@Override
	public List<SystemConfiguration> getAllSystemConfigurations() {
		return systemConfigurationDAO.getAllSystemConfigurations();
	}

	@Override
	public SystemConfiguration getSystemConfiguration(Long id) {
		if (id != null) {
			return systemConfigurationDAO.getSystemConfiguration(id);
		}
		return null;
	}

	@Override
	public SystemConfiguration insertSystemConfiguration(
			SystemConfiguration systemConfiguration) {

		return systemConfigurationDAO
				.insertSystemConfiguration(systemConfiguration);
	}

	@Override
	public SystemConfiguration updateSystemConfiguration(
			SystemConfiguration systemConfiguration) {
		return systemConfigurationDAO
				.updateSystemConfiguration(systemConfiguration);
	}

	@Override
	public List<SystemConfiguration> loadSystemConfigurations(int first,
			int pageSize, String sortField, boolean ascending,
			Map<String, Object> filters) {
		return systemConfigurationDAO.loadSystemConfigurations(first, pageSize,
				sortField, ascending, filters);
	}

	@Override
	public int getNumOfSystemConfigurationsRows(Map<String, Object> filters) {
		return systemConfigurationDAO.getNumOfSystemConfigurationsRows(filters);
	}

}
