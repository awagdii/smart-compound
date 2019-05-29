package com.ntgclarity.smartcompound.common.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.List;

import com.ntgclarity.smartcompound.common.entity.Tenant;

/**
*
* @author Karim M.Fadel
*/
@XmlRootElement
public class LoginResponse {

	private TenantWapper tenantWapper;
	private CompoundWrapper compoundWrapper;
	private List<ServiceWrapper> serviceWrappers;
	private String token;
	
	
	public LoginResponse() {
		super();
	}

	
	public LoginResponse(TenantWapper tenantWapper,
			CompoundWrapper compoundWrapper,
			List<ServiceWrapper> serviceWrappers, String token) {
		super();
		this.tenantWapper = tenantWapper;
		this.compoundWrapper = compoundWrapper;
		this.serviceWrappers = serviceWrappers;
		this.token = token;
	}


	@XmlElement
	public List<ServiceWrapper> getServiceWrappers() {
		return serviceWrappers;
	}
	public void setServiceWrappers(List<ServiceWrapper> serviceWrappers) {
		this.serviceWrappers = serviceWrappers;
	}
	@XmlElement
	public CompoundWrapper getCompoundWrapper() {
		return compoundWrapper;
	}
	public void setCompoundWrapper(CompoundWrapper compoundWrapper) {
		this.compoundWrapper = compoundWrapper;
	}
	@XmlElement
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	@XmlElement
	public TenantWapper getTenantWapper() {
		return tenantWapper;
	}
	public void setTenantWapper(TenantWapper tenantWapper) {
		this.tenantWapper = tenantWapper;
	}


	
}
