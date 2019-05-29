package com.ntgclarity.smartcompound.common.dto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.ntgclarity.smartcompound.common.entity.Tenant;

@XmlRootElement
public class TenantWapper {
	@NotNull
	private Long id;
	@NotNull
	@Size(min=1,max=100)
	private String firstName;
	@NotNull
	@Size(min=1,max=100)
	private String middleName;
	@NotNull
	private String tenantImage;
	@NotNull
	@Pattern(regexp="^[_A-Za-z0-9-\\\\+]+([\\._][A-Za-z0-9-]+)*"
            + "@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
	private String email;
	
	@XmlElement
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@XmlElement
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;

	}

	@XmlElement
	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	
	@XmlElement
	public String getTenantImage() {
		return tenantImage;
	}

	public void setTenantImage(String tenantImage) {
		this.tenantImage = tenantImage;
	}

	@XmlElement
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public static List<TenantWapper> wrappedTenantsObject(List<Tenant> tenants) {
		List<TenantWapper> tenantWappers = new ArrayList<>();
		for (Iterator<Tenant> iterator = tenants.iterator(); iterator.hasNext();) {
			tenantWappers.add(wrappedTenantObject((Tenant) iterator.next()));
		}
		return tenantWappers;
	}

	public static TenantWapper wrappedTenantObject(Tenant tenant) {
		TenantWapper tenantWapper = new TenantWapper();
		tenantWapper.setId(tenant.getId());
//		tenantWapper.setCompoundWrapper(CompoundWrapper.wrappedCompoundObject(tenant.getCompound()));
		tenantWapper.setFirstName(tenant.getFirstName());
		tenantWapper.setEmail(tenant.getEmail());
		tenantWapper.setTenantImage(tenant.getTenantImage());
		tenantWapper.setMiddleName(tenant.getMiddleName());
		return tenantWapper;
	}

}
