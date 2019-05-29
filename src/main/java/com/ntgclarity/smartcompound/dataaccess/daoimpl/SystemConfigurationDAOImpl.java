package com.ntgclarity.smartcompound.dataaccess.daoimpl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ntgclarity.smartcompound.common.entity.SystemConfiguration;
import com.ntgclarity.smartcompound.dataaccess.base.BaseDAO;
import com.ntgclarity.smartcompound.dataaccess.dao.SystemConfigurationDAO;

@Repository
public class SystemConfigurationDAOImpl extends BaseDAO implements
		SystemConfigurationDAO {

	@Override
	public List<SystemConfiguration> getAllSystemConfigurations() {
		return (List<SystemConfiguration>) super
				.getAll(SystemConfiguration.class);
	}

	@Override
	public SystemConfiguration getSystemConfiguration(Long id) {
		return (SystemConfiguration) super.get(SystemConfiguration.class, id);
	}

	@Override
	public SystemConfiguration insertSystemConfiguration(
			SystemConfiguration systemConfiguration) {
		return (SystemConfiguration) super.saveOrUpdate(systemConfiguration);
	}

	@Override
	public SystemConfiguration updateSystemConfiguration(
			SystemConfiguration systemConfiguration) {
		return (SystemConfiguration) super.saveOrUpdate(systemConfiguration);
	}

	@Override
	public List<SystemConfiguration> loadSystemConfigurations(int first,
			int pageSize, String sortField, boolean ascending,
			Map<String, Object> filters) {
		return  super.load(SystemConfiguration.class, first, pageSize, sortField,ascending, filters);
	}

	@Override
	public int getNumOfSystemConfigurationsRows(Map<String, Object> filters) {
		return super.getNumOfRows(SystemConfiguration.class, filters);
	}

}
