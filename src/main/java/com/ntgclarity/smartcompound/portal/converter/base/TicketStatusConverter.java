package com.ntgclarity.smartcompound.portal.converter.base;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import com.ntgclarity.smartcompound.common.entity.Service;
import com.ntgclarity.smartcompound.common.entity.TicketStatus;
import com.ntgclarity.smartcompound.common.utils.Utils;

/** @Author:Nessma **/
@ManagedBean
@ApplicationScoped
public class TicketStatusConverter extends BaseConverter implements Converter {
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String str) {

		if (Utils.isNotEmpty(str) && Utils.isNumericValue(str)) {
			TicketStatus ticketStatus = getSmartCompoundManagment().getTicketStatus(
					new Long(str));
			return ticketStatus;
		}
		return null;

	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object object) {
		if (object != null && !(object instanceof String)) {
			if (object instanceof TicketStatus) {
				Long id = ((TicketStatus) object).getId();
				return id + "";
			} else {
				throw new IllegalStateException(
						"object is not instance of TicketStatus ");
			}
		}
		return "";
	}
}
