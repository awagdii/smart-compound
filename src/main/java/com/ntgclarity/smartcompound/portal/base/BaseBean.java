package com.ntgclarity.smartcompound.portal.base;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.ntgclarity.smartcompound.common.constatnt.SmartCompoundConstant;
import com.ntgclarity.smartcompound.common.entity.Compound;
import com.ntgclarity.smartcompound.common.entity.Employee;

@ManagedBean
public class BaseBean implements Serializable {

	/**  
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void addErrorMessage(String key) {
		String message = getMessageValue(key);
		addMessage(FacesMessage.SEVERITY_ERROR, message);

	}

	public void addInfoMessage(String key) {
		String message = getMessageValue(key);
		addMessage(FacesMessage.SEVERITY_INFO, message);

	}

	protected String getMessageValue(String key) {
		try
		{
		
		ResourceBundle bundle = ResourceBundle
				.getBundle(
						"com.ntgclarity.smartcompound.portal.internationalization.ApplicationResources",
						FacesContext.getCurrentInstance().getViewRoot()
								.getLocale());
		String value = bundle.getString(key);
		return value;
		}catch(Exception ex)
		{
			return key;
		}
	}

	public void addMessage(FacesMessage.Severity severity, String summary,
			String detail) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(severity, summary, detail));
	}

	public void addMessage(FacesMessage.Severity severity, String summary) {
		addMessage(severity, summary, "");
	}
	
	public void addToSession(String key , Object value)
	{		
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(key, value);
	}
	
	public void removeFromSession(Object value) throws IOException{
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		FacesContext.getCurrentInstance().getExternalContext().redirect("/smart-compound/login.xhtml");
	}
	
	public Object getFromSession(String key)
	{
		Employee employee = (Employee) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(key);
		
		return employee;
	}

	
	public Compound getCurrentCompound()
	{ // get current loggedUser compound fromm sessiun
		
		return getCurrentLoggedEmployee().getCompound();
	}
	
	public Employee getCurrentLoggedEmployee()
	{// get current loggedUser  fromm sessiun
		return (Employee) getFromSession(SmartCompoundConstant.CURRENT_LOGGED_EMPLOYEE_SESSION_ATTREBUTE);
		
	}
	
	public boolean isSuperAdmin() {
		// TODO Auto-generated method stub
		return getCurrentLoggedEmployee().isSuperAdmin();
	}
	
	public boolean isNotSuperAdmin()
	{
		return  !isSuperAdmin();
	}
	
	public void redirect(String url)
	{
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getRequestParam(String paramName)
	{
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		return params.get(paramName);
	}
	
}
