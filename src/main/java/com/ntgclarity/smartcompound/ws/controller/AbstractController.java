package com.ntgclarity.smartcompound.ws.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Iterator;
import java.util.Objects;

import org.apache.log4j.Logger;

import com.ntgclarity.smartcompound.business.management.SmartCompoundManagment;
import com.ntgclarity.smartcompound.common.dto.Token;
import com.ntgclarity.smartcompound.common.dto.mail.Mailer;
import com.ntgclarity.smartcompound.common.entity.Tenant;
import com.ntgclarity.smartcompound.common.exception.SmartCompoundException;
import com.ntgclarity.smartcompound.common.spring.applicationcontext.SpringApplicationContext;

/**
*
* @author Karim M.Fadel
*/
public class AbstractController {
	
	final static Logger logger = Logger.getLogger(AbstractController.class);
	
	private static Map<Long,Token> users = new HashMap<Long,Token>();

	protected SmartCompoundManagment getSmartCompoundManagement() {
		SmartCompoundManagment smartCompoundManagment = SpringApplicationContext
				.getApplicationContext().getBean(SmartCompoundManagment.class);
		return smartCompoundManagment;
	}
	
	protected Mailer getMailer() {
		Mailer mailer = (Mailer) SpringApplicationContext
				.getApplicationContext().getBean("mailer");
		return mailer;
	}

	protected Token isUserExist(Long id, Token token) throws SmartCompoundException {
		logger.info("isUserExist(id,token) :(" +id+", "+token+")" );
		logger.info("users.containsKey(id) :" +users.containsKey(id));
		if(!(users.containsKey(id) && Objects.equals(users.get(id), token))) {
				throw new SmartCompoundException();
		}		
		return users.get(id);
	}
	
	protected Token addUser(Tenant tenant) throws SmartCompoundException{
		logger.info("addUser()  id :" +tenant.getId());
		logger.info("users.containsKey(id) :" +users.containsKey(tenant.getId()));
		if(!users.containsKey(tenant.getId())){
			Token token = Token.generateToken();
			token.setCompoundId(tenant.getCompound().getId());
			users.put(tenant.getId(), token);
			logger.info("size of map :" +users.size());
			return token;
		}
		return users.get(tenant.getId());
//		throw new SmartCompoundException(); // user already exit 
	}
	
	protected boolean removeUser(Long id, Token token) {
		logger.info("removeUser(id,token) :(" +id+", "+token+")" );
		return users.remove(id, token);
	}
	

}