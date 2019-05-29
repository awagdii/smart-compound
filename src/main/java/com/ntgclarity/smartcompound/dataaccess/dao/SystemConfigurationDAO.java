package com.ntgclarity.smartcompound.dataaccess.dao;

import java.util.List;
import java.util.Map;

import com.ntgclarity.smartcompound.common.entity.SystemConfiguration;

/**Author: Mai**/
public interface SystemConfigurationDAO {

	List<SystemConfiguration> getAllSystemConfigurations();

	SystemConfiguration getSystemConfiguration(Long id);

	SystemConfiguration insertSystemConfiguration(SystemConfiguration systemConfiguration);

	SystemConfiguration updateSystemConfiguration(SystemConfiguration systemConfiguration);

	List<SystemConfiguration> loadSystemConfigurations(int first, int pageSize, String sortField,
			boolean ascending, Map<String, Object> filters);

	int getNumOfSystemConfigurationsRows(Map<String, Object> filters);
}
