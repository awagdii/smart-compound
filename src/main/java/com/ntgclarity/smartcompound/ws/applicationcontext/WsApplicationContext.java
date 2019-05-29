package com.ntgclarity.smartcompound.ws.applicationcontext;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.ntgclarity.smartcompound.ws.controller.BillController;
import com.ntgclarity.smartcompound.ws.controller.BillDetailsController;
import com.ntgclarity.smartcompound.ws.controller.EmployeeController;
import com.ntgclarity.smartcompound.ws.controller.FacilityController;
import com.ntgclarity.smartcompound.ws.controller.OrderController;
import com.ntgclarity.smartcompound.ws.controller.TenantController;
import com.ntgclarity.smartcompound.ws.controller.TicketController;
import com.ntgclarity.smartcompound.ws.controller.TicketUploadImageController;
  

@ApplicationPath("/ws")
public class WsApplicationContext extends Application{

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> classes = new HashSet<Class<?>>();
		classes.add(EmployeeController.class); 
		classes.add(FacilityController.class); 
		classes.add(TenantController.class);
		classes.add(OrderController.class);
		classes.add(TicketController.class);
		classes.add(BillController.class);
		classes.add(BillDetailsController.class);
		classes.add(TicketUploadImageController.class);
		return classes; 
	}

	
	
}
