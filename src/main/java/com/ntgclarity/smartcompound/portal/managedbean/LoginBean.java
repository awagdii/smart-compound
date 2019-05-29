package com.ntgclarity.smartcompound.portal.managedbean;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.ViewHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

import ch.qos.logback.classic.Logger;

import com.ntgclarity.smartcompound.common.constatnt.MessagesKeys;
import com.ntgclarity.smartcompound.common.constatnt.SmartCompoundConstant;
import com.ntgclarity.smartcompound.common.entity.Employee;
import com.ntgclarity.smartcompound.common.exception.SmartCompoundException;
import com.ntgclarity.smartcompound.portal.base.BaseBean;
import com.ntgclarity.smartcompound.portal.exceptionhandling.CustomExceptionHandler;
import com.ntgclarity.smartcompound.portal.security.CustomUserDetails;
import com.ntgclarity.smartcompound.portal.utils.WebUtils;

@ManagedBean
@SessionScoped
public class LoginBean extends BaseBean implements Serializable {

	@ManagedProperty(value = "#{customAuthenticationProvider}")
	private AuthenticationProvider authenticationProvider;
	@ManagedProperty(value="#{dynamicMenuBean}")
	private DynamicMenuBean dynamicMenuBean;
	private String userName;
	private String password;
	private String rememberMe;
	private String logoutParameter;

	public String getLogoutParameter() {
		return logoutParameter;
	}

	public void setLogoutParameter(String logoutParameter) {
		this.logoutParameter = logoutParameter;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(String rememberMe) {
		this.rememberMe = rememberMe;
	}

	private static final long serialVersionUID = 1L;

	Authentication authentcation;

	CustomUserDetails customUserDetails;

	Employee employee;

	public void login() throws IOException, SmartCompoundException {

		try {
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				userName, password);

		if (userName == null || userName.isEmpty())
			throw new SmartCompoundException(
					MessagesKeys.SMART_COMPOUND_LOGIN_PAGE_USER_NAME_REQUIRED);

		if (password == null || password.isEmpty())
			throw new SmartCompoundException(
					MessagesKeys.SMART_COMPOUND_LOGIN_PAGE_PASSWORD_REQUIRED);
		authentcation = authenticationProvider
				.authenticate(usernamePasswordAuthenticationToken);
		     
		
		customUserDetails = (CustomUserDetails) authentcation.getPrincipal();

		employee = customUserDetails.getEmployee();

		if(getCurrentLoggedEmployee() == null){
			addToSession(
					SmartCompoundConstant.CURRENT_LOGGED_EMPLOYEE_SESSION_ATTREBUTE,
					employee);
			dynamicMenuBean.constructDynamicMenu();

			SecurityContextHolder.getContext().setAuthentication(authentcation);
			WebUtils.injectIntoSession("SPRING_SECURITY_CONTEXT",
					SecurityContextHolder.getContext());

			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("/smart-compound/index.xhtml");
		}
		else{
			throw new SmartCompoundException(
					MessagesKeys.SMART_COMPOUND_LOGIN_LOGGED_IN);
		}
		} catch (AuthenticationException ex) {
			throw new SmartCompoundException(
					MessagesKeys.SMART_COMPOUND_LOGIN_INFO_MSG);

		}

	}

	public void logout() throws IOException {

		logoutParameter = "logout";
		SecurityContextHolder.clearContext();
		removeFromSession(employee);
	}
	
	public void refreshPage() {
		FacesContext fc = FacesContext.getCurrentInstance();
		String refreshpage = fc.getViewRoot().getViewId();
		ViewHandler ViewH =fc.getApplication().getViewHandler();
		UIViewRoot UIV = ViewH.createView(fc,refreshpage);
		UIV.setViewId(refreshpage);
		fc.setViewRoot(UIV);
		}

	public void isLoggedIn() throws IOException, SmartCompoundException {
	    
		if (employee == null) {
			refreshPage();
		}

		else if (employee != null) {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("/smart-compound/index.xhtml");
		}
	}

	public void isSessionInvalidated() throws IOException{
		if (employee == null) {
			FacesContext.getCurrentInstance().getExternalContext()
			.redirect("/smart-compound/login.xhtml");
		}
	}
	
	public AuthenticationProvider getAuthenticationProvider() {
		return authenticationProvider;
	}

	public void setAuthenticationProvider(
			AuthenticationProvider authenticationProvider) {
		this.authenticationProvider = authenticationProvider;
	}

	public DynamicMenuBean getDynamicMenuBean() {
		return dynamicMenuBean;
	}

	public void setDynamicMenuBean(DynamicMenuBean dynamicMenuBean) {
		this.dynamicMenuBean = dynamicMenuBean;
	}

}