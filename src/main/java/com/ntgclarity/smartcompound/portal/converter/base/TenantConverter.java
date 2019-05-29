package com.ntgclarity.smartcompound.portal.converter.base;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import com.ntgclarity.smartcompound.common.entity.Tenant;
import com.ntgclarity.smartcompound.common.utils.Utils;

/** Author: Nazer**/
@ManagedBean
@ApplicationScoped
public class TenantConverter extends BaseConverter implements Converter {

	
	
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String str) {
		
		if(Utils.isNotEmpty(str) && Utils.isNumericValue(str))
		{
			Tenant tenant =getSmartCompoundManagment().getTenant(new Long(str));
			return tenant;
		}
		return null;
		
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object object) {
		if(object!=null && !(object instanceof String))
		{
			if(object instanceof Tenant)
			{
				Long id = ((Tenant)object).getId();
				return id+"";
			}else
			{
				throw new IllegalStateException("object is not instaoce of Tenant ");
			}
		}
		return "";
	}

}
