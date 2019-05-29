package com.ntgclarity.smartcompound.portal.converter.base;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import com.ntgclarity.smartcompound.common.entity.Menu;
import com.ntgclarity.smartcompound.common.utils.Utils;

@ManagedBean
@ApplicationScoped
public class MenuConverter extends BaseConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String str) {

		if (Utils.isNotEmpty(str) && Utils.isNumericValue(str)) {
			Menu menu =getSmartCompoundManagment().getMenu(new Long(str));
			return menu;
			
		}
		return null;

	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object object) {
		if (object != null && !(object instanceof String)) {
			if (object instanceof Menu) {
				Long id = ((Menu) object).getId();
				return id + "";
			} else {
				throw new IllegalStateException(
						"object is not instaoce of Menu");
			}
		}
		return "";
	}

}
