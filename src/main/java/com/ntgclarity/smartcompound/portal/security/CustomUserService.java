package com.ntgclarity.smartcompound.portal.security;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ntgclarity.smartcompound.business.management.SmartCompoundManagment;
import com.ntgclarity.smartcompound.business.service.EmployeeService;
import com.ntgclarity.smartcompound.business.service.MenuItemService;
import com.ntgclarity.smartcompound.common.entity.Employee;
import com.ntgclarity.smartcompound.common.entity.Group;
import com.ntgclarity.smartcompound.common.entity.MenuItem;

@Service
public class CustomUserService implements UserDetailsService {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private MenuItemService menuItemService;

	List<SimpleGrantedAuthority> authorities;
	
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		Employee employee = employeeService.findEmployeeByUsername(username);
		if (employee == null) {
			return null;
		} else {
			
			CustomUserDetails customUserDetails = new CustomUserDetails();
			customUserDetails.setEmployee(employee);
		
			/**START HEBA'S WORK**/
			// Hit and get employee group
			authorities = new ArrayList<SimpleGrantedAuthority>();
			List<Group> groups = employeeService.getEmployeeGroups(employee);
			addGroupToAuthority(groups);
			customUserDetails.setAuthorities(authorities);
			
			// Hit and get List urls 
			List<MenuItem> menuItemList = menuItemService.getAllEmployeeMenuItems(employee);
			Set<String>urlSet= returnListUrl(menuItemList);
			List<String> urlList = new ArrayList<String>(urlSet);
			customUserDetails.setUrl(urlList);
			/**END HEBA'S WORK**/
			
			return customUserDetails;
		}
	}
	
	public void addGroupToAuthority(List<Group> groups){
		for(Group group : groups){
			SimpleGrantedAuthority authority = new SimpleGrantedAuthority(group.getGroupName());
			authorities.add(authority);
		}
	}
	
	public Set<String> returnListUrl(List<MenuItem> menuItemList){
		Set<String> urlSet = new HashSet<String>();
		for(MenuItem menuItem : menuItemList){
			String url = menuItem.getUrl();
			urlSet.add(url);
		}
		return urlSet;
	}
	
}
