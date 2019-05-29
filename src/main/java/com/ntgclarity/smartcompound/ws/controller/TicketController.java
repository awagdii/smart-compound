package com.ntgclarity.smartcompound.ws.controller;

import java.util.Date;
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

import com.ntgclarity.smartcompound.common.dto.OrderWrapperResponse;
import com.ntgclarity.smartcompound.common.dto.TicketWrapperResponse;
import com.ntgclarity.smartcompound.common.dto.SmartCompoundResponse;
import com.ntgclarity.smartcompound.common.dto.TicketWrapper;
import com.ntgclarity.smartcompound.common.dto.Token;
import com.ntgclarity.smartcompound.common.entity.Compound;
import com.ntgclarity.smartcompound.common.entity.Order;
import com.ntgclarity.smartcompound.common.entity.Tenant;
import com.ntgclarity.smartcompound.common.entity.Ticket;
import com.ntgclarity.smartcompound.common.exception.SmartCompoundException;
import com.ntgclarity.smartcompound.common.utils.Utils;

/**
 * 
 * @author Karim M.Fadel
 */
@Path("/ticketcontroller")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TicketController extends AbstractController {

	final static Logger logger = Logger.getLogger(TicketController.class);

	@POST
	@Path("/createticket")
	public SmartCompoundResponse createTicket(@NotNull TicketWrapper ticketWrapper,
			@Context HttpHeaders headers) {
		try {
			if(headers.getRequestHeader("token")==null)
				throw new SmartCompoundException();
			String strToken = headers.getRequestHeader("token").get(0);
			logger.info("id : " + ticketWrapper.getTenantId());
			
			Token token = isUserExist(ticketWrapper.getTenantId(), new Token(strToken));
			Ticket ticket = TicketWrapper.unWrappedTenantObject(ticketWrapper);
			Compound compound = new Compound();
			compound.setId(token.getCompoundId());
			ticket.setCompound(compound);
			ticket.setChannel("Mobile");
			ticket.setOpenDate(Utils.getStartOfDay(new Date()));
			ticket = getSmartCompoundManagement().insertTicket(ticket);
			
			return SmartCompoundResponse.prepareSuccessResponce("", ticket.getId());
		} catch (SmartCompoundException ex) {
			return SmartCompoundResponse
					.prepareFailureResponce(ex.getMessage());
		}
	}
	
	@GET
	@Path("/getalltickets/{idtenant}-{pagesize}-{pageindex}")
	public SmartCompoundResponse getAllTickets(
			@NotNull @PathParam("idtenant") Long idTenant,@NotNull @PathParam("pagesize") Integer size,
			@NotNull @PathParam("pageindex") Integer index, @Context HttpHeaders headers) {
		try {
			if(headers.getRequestHeader("token")==null)
				throw new SmartCompoundException();
			String token = headers.getRequestHeader("token").get(0);
			logger.info("id : " + idTenant);
			isUserExist(idTenant, new Token(token));
			Tenant tenant = new Tenant();
			tenant.setId(idTenant);
			Map<String,Object> filters= new HashMap<String,Object>();
			filters.put("relatedTenant", tenant);
			List<Ticket> tickets = getSmartCompoundManagement().loadTickets(index, size, null, true, filters);
			return SmartCompoundResponse.prepareSuccessResponce("", TicketWrapperResponse.wrappedTicketsResponseObject(tickets));
		} catch (SmartCompoundException ex) {
			return SmartCompoundResponse.prepareFailureResponce(ex.getMessage());
		}
	}

}
