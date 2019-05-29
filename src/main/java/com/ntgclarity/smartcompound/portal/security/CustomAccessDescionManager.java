package com.ntgclarity.smartcompound.portal.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

import com.ntgclarity.smartcompound.common.entity.MenuItem;

@Component
public class CustomAccessDescionManager implements AccessDecisionManager {

	@Override
	public void decide(Authentication authentication, Object object,
			Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {
		
		/**START HEBA'S WORK**/
		//Handling accessing urls

		//Caller
			if(authentication.getName() == "anonymousUser")
				throw new AuthenticationCredentialsNotFoundException("No session");
			else{
				CustomUserDetails customUserDetails = (CustomUserDetails)authentication.getPrincipal();
				List <String> urlList = customUserDetails.getUrl();
			
				
			//Url
				FilterInvocation filterInvocation = (FilterInvocation)object;
				String targetedUrl = filterInvocation.getRequestUrl();
	
				if(!urlList.contains(targetedUrl))
					throw new AccessDeniedException("Access is denied");	     
			}
		/**END HEBA'S WORK**/

	}

	@Override
	public boolean supports(ConfigAttribute attribute) {

		return true;
	}

	@Override
	public boolean supports(Class<?> clazz) {

		return true;
	}

}
