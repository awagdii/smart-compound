package com.ntgclarity.smartcompound.portal.managedbean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Spliterator;
import java.util.function.Consumer;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.ntgclarity.smartcompound.business.management.SmartCompoundManagment;
import com.ntgclarity.smartcompound.common.constatnt.MessagesKeys;
import com.ntgclarity.smartcompound.common.entity.SystemConfiguration;
import com.ntgclarity.smartcompound.portal.base.BaseBean;

@ManagedBean
@ViewScoped
public class SystemConfigurationBean extends BaseBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{smartCompoundManagmentImpl}")
	private SmartCompoundManagment smartCompoundManagment;
	
	private SystemConfiguration selectedSystemConfiguration;
	
	private LazyDataModel<SystemConfiguration> lazySystemConfigurationModel;

	@PostConstruct
	public void init() {
		 
		loadData();
	}
	
	private void loadData() {
		lazySystemConfigurationModel = new LazyDataModel<SystemConfiguration>() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			private List<SystemConfiguration> result;

			@Override
			public SystemConfiguration getRowData(String rowKey) {
				for (SystemConfiguration systemConfiguration : result) {
					if (systemConfiguration.getId().toString().equals(rowKey))
						return systemConfiguration;
				}

				return null;
			}

			@Override
			public Object getRowKey(SystemConfiguration systemConfiguration) {
				return systemConfiguration.getId();
			}

			@Override
			public List<SystemConfiguration> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {

				result = getSmartCompoundManagment().loadSystemConfigurations(first, pageSize, sortField, sortOrder == SortOrder.ASCENDING , filters);
				this.setRowCount(getSmartCompoundManagment()
						.getNumOfSystemConfigurationsRows(filters));

				return result;
			}

			@Override
			public void forEach(Consumer<? super SystemConfiguration> action) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public Spliterator<SystemConfiguration> spliterator() {
				// TODO Auto-generated method stub
				return null;
			}

		};
		
	}
	public void  initiateNewSystemConfiguration(){
		selectedSystemConfiguration=new SystemConfiguration();
		
	}
	
	public void insertSystemConfiguration(){
		smartCompoundManagment.insertSystemConfiguration(selectedSystemConfiguration);
		addInfoMessage(MessagesKeys.SMART_COMPOUND_SUCCESS_MESSAGE_MESSAGE);
	}
	
	
	public SmartCompoundManagment getSmartCompoundManagment() {
		return smartCompoundManagment;
	}
	public void setSmartCompoundManagment(
			SmartCompoundManagment smartCompoundManagment) {
		this.smartCompoundManagment = smartCompoundManagment;
	}
	public SystemConfiguration getSelectedSystemConfiguration() {
		return selectedSystemConfiguration;
	}
	public void setSelectedSystemConfiguration(
			SystemConfiguration selectedSystemConfiguration) {
		this.selectedSystemConfiguration = selectedSystemConfiguration;
	}

	public LazyDataModel<SystemConfiguration> getLazySystemConfigurationModel() {
		return lazySystemConfigurationModel;
	}

	public void setLazySystemConfigurationModel(
			LazyDataModel<SystemConfiguration> lazySystemConfigurationModel) {
		this.lazySystemConfigurationModel = lazySystemConfigurationModel;
	}

	
}
