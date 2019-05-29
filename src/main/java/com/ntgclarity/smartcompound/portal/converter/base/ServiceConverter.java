package com.ntgclarity.smartcompound.portal.converter.base;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import com.ntgclarity.smartcompound.common.entity.Service;
import com.ntgclarity.smartcompound.common.utils.Utils;

/** Author: Nazer**/
@ManagedBean
@ApplicationScoped
public class ServiceConverter extends BaseConverter implements Converter {

	
	
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String str) {
		
		if(Utils.isNotEmpty(str) && Utils.isNumericValue(str))
		{
			Service service =getSmartCompoundManagment().getService(new Long(str));
			return service;
		}
		return null;
		
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object object) {
		if(object!=null && !(object instanceof String))
		{
			if(object instanceof Service)
			{
				Long id = ((Service)object).getId();
				return id+"";
			}else
			{
				throw new IllegalStateException("object is not instaoce of Services ");
			}
		}
		return "";
	}

}
