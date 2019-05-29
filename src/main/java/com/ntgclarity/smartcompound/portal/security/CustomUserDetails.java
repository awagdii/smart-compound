package com.ntgclarity.smartcompound.portal.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ntgclarity.smartcompound.common.entity.Employee;
import com.ntgclarity.smartcompound.common.entity.Group;

public class CustomUserDetails implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Employee employee;
	private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;
    private List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
	
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	

	
	public void setAuthorities(List<SimpleGrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public String getPassword() {
		return employee.getPassword();
	}

	@Override
	public String getUsername() {
		return employee.getUsername();
	}

	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	/**START HEBA'S WORK **/
    //List urls
	private List<String> url = new ArrayList<String> ();

	public List<String> getUrl() {
		return url;
	}

	public void setUrl(List<String> url) {
		this.url = url;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

    /**END HEBA'S WORK **/ 
   
}