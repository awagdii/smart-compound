package com.ntgclarity.smartcompound.portal.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.ntgclarity.smartcompound.common.entity.Group;

@Component
public class CustomAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	@Autowired
	private CustomUserService userService;

	
	public boolean supports(Class<?> arg0) {
		return true;
	}

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
//	String currentUsername =authentication.getPrincipal().toString();
	String currentPassword = authentication.getCredentials().toString();
	if (currentPassword == null) {
		logger.debug("Authentication failed: no credentials provided");

		throw new BadCredentialsException(messages.getMessage(
				"AbstractUserDetailsAuthenticationProvider.badCredentials",
				"Bad credentials"));
	}
	if (!currentPassword.equals(userDetails.getPassword())) {
		logger.debug("Authentication failed: password is incorrect");

		throw new BadCredentialsException(messages.getMessage(
				"AbstractUserDetailsAuthenticationProvider.badCredentials",
				"Bad credentials"));
	}
//		System.out.println("in additional authetication");
	}
  
	@Override
	protected UserDetails retrieveUser(String username,
			UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		 
//		userDetails.setAuthorities(new ArrayList<Group>());
//		Group group = new Group();
//		group.setGroupName("admin");
//		userDetails.getAuthorities().add(group);
		UserDetails userDetails = userService.loadUserByUsername(username);
		if(userDetails==null){
			throw new UsernameNotFoundException("User Not Found");
		}
		return userDetails;
	}

}