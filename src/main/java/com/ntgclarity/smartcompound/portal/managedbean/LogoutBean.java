package com.ntgclarity.smartcompound.portal.managedbean;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

import com.ntgclarity.smartcompound.common.constatnt.MessagesKeys;
import com.ntgclarity.smartcompound.common.entity.Employee;
import com.ntgclarity.smartcompound.common.exception.SmartCompoundException;
import com.ntgclarity.smartcompound.portal.base.BaseBean;
import com.ntgclarity.smartcompound.portal.security.CustomUserDetails;
import com.ntgclarity.smartcompound.portal.utils.WebUtils;

@ManagedBean
@SessionScoped
public class LogoutBean extends BaseBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void logout() throws IOException{

		SecurityContextHolder.clearContext();
		Employee employee = getCurrentLoggedEmployee();
		removeFromSession(employee);
//		FacesContext.getCurrentInstance().getExternalContext().redirect("/smart-compound/login.xhtml");
	}
}
