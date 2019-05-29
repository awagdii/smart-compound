package com.ntgclarity.smartcompound.ws.controller;

import org.apache.log4j.Logger;

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

import com.ntgclarity.smartcompound.common.dto.SmartCompoundResponse;
import com.ntgclarity.smartcompound.common.exception.SmartCompoundException;

/**
*
* @author Karim M.Fadel
*/
@Path("/employeecontroller")
@Produces(MediaType.APPLICATION_JSON)
public class EmployeeController extends AbstractController {

	final static Logger logger = Logger.getLogger(EmployeeController.class);

	@GET
	@Path("/employee/{id}")
	public SmartCompoundResponse getAllEmployees(@PathParam("id") Integer id) {

		try {
			logger.info("id : "+id);
			
			Object result =  getSmartCompoundManagement().getAllEmployees();
			return SmartCompoundResponse.prepareSuccessResponce("","karim");
		} catch (SmartCompoundException e) {
			return SmartCompoundResponse.prepareFailureResponce("");
		}
	}
	
//	@PUT
//	@Path("/employee/put")
//	public SmartCompoundResponse Employe(@PathParam("{id}") Integer id) {
//
//		try {
//			logger.info("id : "+id);
//			Object result =  getSmartCompoundManagement().getAllEmployees();
//			return SmartCompoundResponse.prepareSuccessResponce("","karim");
//		} catch (SmartCompoundException e) {
//			return SmartCompoundResponse.prepareFailureResponce("");
//		}
//	}

	@GET
    @Path("/getcountry")
    @Produces(MediaType.APPLICATION_JSON)
    public SmartCompoundResponse getJSON(@Context HttpHeaders headers) {
		String userAgent = headers.getRequestHeader("user-agent").get(0);
		logger.info("User-agent :  "+ userAgent);
		for(String header : headers.getRequestHeaders().keySet()){
			logger.info("httpHeader :  "+ header);
		}
        return SmartCompoundResponse.prepareSuccessResponce("userAgent","karim");
    }
	
}
