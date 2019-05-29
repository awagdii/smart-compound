package com.ntgclarity.smartcompound.ws.controller;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.ntgclarity.smartcompound.common.dto.BillDetailsWrapper;
import com.ntgclarity.smartcompound.common.dto.SmartCompoundResponse;
import com.ntgclarity.smartcompound.common.dto.Token;
import com.ntgclarity.smartcompound.common.entity.Bill;
import com.ntgclarity.smartcompound.common.entity.BillDetails;
import com.ntgclarity.smartcompound.common.entity.Tenant;
import com.ntgclarity.smartcompound.common.exception.SmartCompoundException;

@Path("/billdetailscontroller")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BillDetailsController extends AbstractController {
	final static Logger logger = Logger.getLogger(BillDetailsController.class);

	@GET
	@Path("/getbilldetails/{tenantid}-{billid}")
	public SmartCompoundResponse getBillDetails(
			@PathParam("tenantid") Long tenantId,
			@PathParam("billid") Long billId,
			@Context HttpHeaders headers) {
		try {
			if (headers.getRequestHeader("token") == null)
				throw new SmartCompoundException();
			String token = headers.getRequestHeader("token").get(0);
			logger.info("Tenant id : " + tenantId);
			isUserExist(tenantId, new Token(token));
			Tenant tenant = new Tenant();
			tenant.setId(tenantId);
			Bill bill = getSmartCompoundManagement().getBill(billId);
			List<BillDetails> billDetails = getSmartCompoundManagement()
					.getBillDetailsOfBill(bill);
			return SmartCompoundResponse.prepareSuccessResponce("",
					BillDetailsWrapper.wrappedBillDeailesObject(bill,
							billDetails));
		} catch (SmartCompoundException ex) {
			return SmartCompoundResponse
					.prepareFailureResponce(ex.getMessage());
		}
	}
}
