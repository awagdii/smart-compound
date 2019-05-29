package com.ntgclarity.smartcompound.ws.controller;

import java.util.List;

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

import com.ntgclarity.smartcompound.common.dto.FacilityWapper;
import com.ntgclarity.smartcompound.common.dto.SmartCompoundResponse;
import com.ntgclarity.smartcompound.common.dto.Token;
import com.ntgclarity.smartcompound.common.entity.Compound;
import com.ntgclarity.smartcompound.common.entity.Facility;
import com.ntgclarity.smartcompound.common.entity.Tenant;
import com.ntgclarity.smartcompound.common.exception.SmartCompoundException;

/**
*
* @author Karim M.Fadel
*/
@Path("/facilitycontroller")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FacilityController extends AbstractController {

	final static Logger logger = Logger.getLogger(FacilityController.class);

	@GET
	@Path("/getallFacilities/{idtenant}")
	public SmartCompoundResponse getAllFacility(
			@NotNull @PathParam("idtenant") Long idTenant, @Context HttpHeaders headers) {
		try {
			if(headers.getRequestHeader("token")==null)
				throw new SmartCompoundException();
			String token = headers.getRequestHeader("token").get(0);
			logger.info("id : " + idTenant);
			logger.info("token : " + token);
			isUserExist(idTenant, new Token(token));
			Tenant tenant = new Tenant();
			tenant.setId(idTenant);
			List<Facility> facilities = getSmartCompoundManagement().getAllFacilitiesRelatedToTenant(tenant);
			
			return SmartCompoundResponse.prepareSuccessResponce("", 
					FacilityWapper.wrappedFacilitiesObject(facilities));
		} catch (SmartCompoundException ex) {
			return SmartCompoundResponse.prepareFailureResponce(ex.getMessage());
		}
	}

}
