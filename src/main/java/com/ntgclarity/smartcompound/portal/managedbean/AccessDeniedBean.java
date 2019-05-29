package com.ntgclarity.smartcompound.portal.managedbean;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.springframework.security.core.context.SecurityContextHolder;

import com.ntgclarity.smartcompound.portal.base.BaseBean;

@ManagedBean
@ViewScoped
public class AccessDeniedBean extends BaseBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void goToIndex() throws IOException{
		
		FacesContext.getCurrentInstance().getExternalContext().redirect("/smart-compound/index.xhtml");
	}

}
