package com.ntgclarity.smartcompound.portal.converter.base;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.ntgclarity.smartcompound.common.entity.Lookup;
import com.ntgclarity.smartcompound.common.utils.Utils;


@ManagedBean
@ApplicationScoped
@FacesConverter(value="lookupConverter")
public class LookupConverter extends BaseConverter implements Converter {

	
	
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String str) {
		
		if(Utils.isNotEmpty(str) && Utils.isNumericValue(str))
		{
			Lookup lookup =getSmartCompoundManagment().getLookups(new Long(str));
			return lookup;
		}
		return null;
		
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object object) {

		//return 	getAsString(object);
		if(object!=null && !(object instanceof String))
		{
			if(object instanceof Lookup)
			{
				Long id = ((Lookup)object).getId();
				return id+"";
			}else
			{
//				throw new IllegalStateException("object is not instaoce of Lookup ");
			}
		}
		return "";
	}

}
