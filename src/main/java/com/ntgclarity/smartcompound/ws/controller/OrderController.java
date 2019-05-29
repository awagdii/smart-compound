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

import com.ntgclarity.smartcompound.common.dto.OrderWrapper;
import com.ntgclarity.smartcompound.common.dto.OrderWrapperResponse;
import com.ntgclarity.smartcompound.common.dto.SmartCompoundResponse;
import com.ntgclarity.smartcompound.common.dto.Token;
import com.ntgclarity.smartcompound.common.entity.Compound;
import com.ntgclarity.smartcompound.common.entity.Order;
import com.ntgclarity.smartcompound.common.entity.Tenant;
import com.ntgclarity.smartcompound.common.exception.SmartCompoundException;

/**
*
* @author Hend Muhammed
*/
@Path("/ordercontroller")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderController extends AbstractController {

	final static Logger logger = Logger.getLogger(OrderController.class);

	@GET
	@Path("/getallorders/{idtenant}-{pagesize}-{pageindex}")
	public SmartCompoundResponse getAllOrder(
			@NotNull @PathParam("idtenant") Long idTenant,@NotNull @PathParam("pagesize") Integer size,
			@NotNull @PathParam("pageindex") Integer index, @Context HttpHeaders headers) {
		try {
			if(headers.getRequestHeader("token")==null)
				throw new SmartCompoundException();
			
			String strToken = headers.getRequestHeader("token").get(0);
			logger.info("id : " + idTenant);
			
			isUserExist(idTenant, new Token(strToken));
			
			Map<String,Object> filters= new HashMap<String,Object>();
			
			filters.put("tenant.id", idTenant);
			List<Order> orders   = getSmartCompoundManagement().loadOrders(index, size, null, true, filters);
			return SmartCompoundResponse.prepareSuccessResponce("", OrderWrapperResponse.wrappedOrdersResponceObject(orders));
		} catch (SmartCompoundException ex) {
			return SmartCompoundResponse.prepareFailureResponce(ex.getMessage());
		}
	}
	
	@POST
	@Path("/createorder")
	public SmartCompoundResponse createOrder(@NotNull OrderWrapper orderWrapper,@Context HttpHeaders headers) {
		try {
			if(headers.getRequestHeader("token")==null)
				throw new SmartCompoundException();
			String strToken = headers.getRequestHeader("token").get(0);
			logger.info("tenant id : " + orderWrapper.getTenantId());
			Token token = isUserExist(orderWrapper.getTenantId(), new Token(strToken));
			Order order = OrderWrapper.unWrappedOrderObject(orderWrapper);
			Compound compound = new Compound();
			compound.setId(token.getCompoundId());
			order.setCompound(compound);
			getSmartCompoundManagement().insertOrder(order);
			return SmartCompoundResponse.prepareSuccessResponce("success", "");
		} catch (SmartCompoundException ex) {
			return SmartCompoundResponse.prepareFailureResponce(ex.getMessage());
		}
	}

}
