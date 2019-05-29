package com.ntgclarity.smartcompound.ws.controller;

import org.apache.log4j.Logger;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.ntgclarity.smartcompound.common.entity.Service;
import com.ntgclarity.smartcompound.common.dto.CompoundWrapper;
import com.ntgclarity.smartcompound.common.dto.LoginResponse;
import com.ntgclarity.smartcompound.common.dto.ServiceWrapper;
import com.ntgclarity.smartcompound.common.dto.SmartCompoundResponse;
import com.ntgclarity.smartcompound.common.dto.TenantWapper;
import com.ntgclarity.smartcompound.common.dto.Token;
import com.ntgclarity.smartcompound.common.dto.UsernamePasswordWapper;
import com.ntgclarity.smartcompound.common.dto.mail.Mailer;
import com.ntgclarity.smartcompound.common.entity.Tenant;
import com.ntgclarity.smartcompound.common.exception.SmartCompoundException;

/**
 * 
 * @author Karim M.Fadel
 */
@Path("/tenantcontroller")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TenantController extends AbstractController {

	final static Logger logger = Logger.getLogger(TenantController.class);

	@POST
	@Path("/login")
	public SmartCompoundResponse login(
			@NotNull 
			UsernamePasswordWapper usernamePasswordWapper) {

		try {
			logger.info("username : " + usernamePasswordWapper.getUsername());
			logger.info("password : " + usernamePasswordWapper.getPassword());

			Tenant tenant = getSmartCompoundManagement().getTenant(
					usernamePasswordWapper.getUsername(),
					usernamePasswordWapper.getPassword());
			List<Service> services = getSmartCompoundManagement()
					.getAllServices(tenant.getCompound());
			Token token = addUser(tenant);
			return SmartCompoundResponse.prepareSuccessResponce("",
					initiateLoginResponceObject(tenant, services, token));
		} catch (SmartCompoundException e) {
			return SmartCompoundResponse.prepareFailureResponce(e.getMessage());
		}
	}

	private LoginResponse initiateLoginResponceObject(Tenant tenant,
			List<Service> services, Token token) {
		LoginResponse loginResponse = new LoginResponse();
		loginResponse.setCompoundWrapper(CompoundWrapper
				.wrappedCompoundObject(tenant.getCompound()));
		loginResponse.setServiceWrappers(ServiceWrapper
				.wrappedServicesObject(services));
		loginResponse.setTenantWapper(TenantWapper.wrappedTenantObject(tenant));
		loginResponse.setToken(token.getValue());
		return loginResponse;
	}

	@GET
	@Path("/logout/{id}")
	public SmartCompoundResponse logout(
			@NotNull
			@PathParam("id") Long idTenant,
			@Context HttpHeaders headers) {
		try {
			if(headers.getRequestHeader("token")==null)
				throw new SmartCompoundException();
			String token = headers.getRequestHeader("token").get(0);
			logger.info("remove id : " + idTenant);
			isUserExist(idTenant, new Token(token));
			removeUser(idTenant, new Token(token));
			return SmartCompoundResponse.prepareSuccessResponce("success", "");
		} catch (SmartCompoundException e) {
			return SmartCompoundResponse.prepareFailureResponce(e.getMessage());
		}
	}

	@GET
	@Path("/forgetpassword")
	public SmartCompoundResponse forgetPassword(
			@NotNull 
			@QueryParam("email") String email) {
		try {
			logger.info("ForgetPassword ==> email : " + email);
			Tenant tenant = getSmartCompoundManagement()
					.getTenantByEmail(email);

			Mailer mailer = getMailer();
			mailer.sendMail(email, tenant);
			return SmartCompoundResponse.prepareSuccessResponce("success", "");
		} catch (SmartCompoundException e) {
			return SmartCompoundResponse.prepareFailureResponce(e.getMessage());
		}
	}

}
