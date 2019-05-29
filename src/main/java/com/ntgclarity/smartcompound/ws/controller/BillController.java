package com.ntgclarity.smartcompound.ws.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
 
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam; 
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
  
import com.ntgclarity.smartcompound.common.dto.BillWrapper;
import com.ntgclarity.smartcompound.common.dto.OrderWrapperResponse;
import com.ntgclarity.smartcompound.common.dto.TicketWrapperResponse;
import com.ntgclarity.smartcompound.common.dto.SmartCompoundResponse;
import com.ntgclarity.smartcompound.common.dto.TicketWrapper;
import com.ntgclarity.smartcompound.common.dto.Token;
import com.ntgclarity.smartcompound.common.entity.Bill;
import com.ntgclarity.smartcompound.common.entity.Compound;
import com.ntgclarity.smartcompound.common.entity.Order;
import com.ntgclarity.smartcompound.common.entity.Tenant;
import com.ntgclarity.smartcompound.common.entity.Ticket;
import com.ntgclarity.smartcompound.common.exception.SmartCompoundException;

/**
 * 
 * @author Karim M.Fadel
 */
@Path("/billcontroller")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BillController extends AbstractController {

	final static Logger logger = Logger.getLogger(BillController.class);

	@GET
	@Path("/getallbills/{idtenant}-{pagesize}-{pageindex}")
	public SmartCompoundResponse getAllBills(
			@NotNull @PathParam("idtenant") Long idTenant,@NotNull  @PathParam("pagesize") Integer size,
			@NotNull @PathParam("pageindex") Integer index, @Context HttpHeaders headers) {
		try {
			if(headers.getRequestHeader("token")==null)
				throw new SmartCompoundException();
			String token = headers.getRequestHeader("token").get(0);
			logger.info("Bill id : " + idTenant);
			isUserExist(idTenant, new Token(token));
			
			Map<String,Object> filters= new HashMap<String,Object>();
			filters.put("tenant.id", idTenant);
			List<Bill> Bills = getSmartCompoundManagement().loadBills(index, size, null, true, filters);
			return SmartCompoundResponse.prepareSuccessResponce("", BillWrapper.wrappedBillsObject(Bills));
		} catch (SmartCompoundException ex) {
			return SmartCompoundResponse.prepareFailureResponce(ex.getMessage());
		}
	}

}
