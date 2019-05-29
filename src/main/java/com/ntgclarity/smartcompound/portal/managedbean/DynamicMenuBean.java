package com.ntgclarity.smartcompound.portal.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import com.ntgclarity.smartcompound.business.management.SmartCompoundManagment;
import com.ntgclarity.smartcompound.common.constatnt.SmartCompoundConstant;
import com.ntgclarity.smartcompound.common.entity.Employee;
import com.ntgclarity.smartcompound.common.entity.Lookup;
import com.ntgclarity.smartcompound.common.entity.Menu;
import com.ntgclarity.smartcompound.common.entity.MenuItem;
import com.ntgclarity.smartcompound.common.exception.SmartCompoundException;
import com.ntgclarity.smartcompound.portal.base.BaseBean;

/*ADDED BY HEBA*/

@ManagedBean
@SessionScoped  
public class DynamicMenuBean extends BaseBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private MenuModel model;
    
    @ManagedProperty(value = "#{smartCompoundManagmentImpl}")
	private SmartCompoundManagment smartCompoundManagment;

	List <MenuItem> menuItemList;	
	
	boolean active;

    @PostConstruct
    public void init()throws SmartCompoundException{
        model = new DefaultMenuModel();
    }
    
    public void constructDynamicMenu()
    {
    	menuItemList = smartCompoundManagment.getEmployeeMenuItems((Employee)getFromSession(SmartCompoundConstant.CURRENT_LOGGED_EMPLOYEE_SESSION_ATTREBUTE));	
    	addMenuItemToMenu(menuItemList);
    }
    
    public void addMenuItemToMenu(List <MenuItem> menuItemUniqueMenu){
        model = new DefaultMenuModel();

    	// Map    	
    	Map<Menu, DefaultSubMenu> menusMap = new HashMap<Menu, DefaultSubMenu>();
    	
    	for(MenuItem menuItem : menuItemUniqueMenu)
    	{		
    		String menuStatusName = menuItem.getMenu().getStatus().getLookupName();
    	   		
    		String menuItemStatusName = menuItem.getStatus().getLookupName();
    		
    		DefaultSubMenu menu = menusMap.get(menuItem.getMenu());
    		
    		//Checking if the menu is active
    		
	    		if(menu == null)
	    		{	    			
	    			menu = new DefaultSubMenu(menuItem.getMenu().getName());
	    			menusMap.put(menuItem.getMenu(), menu);
	    			model.addElement(menu);	    			
	    			
	    		}	
	    			DefaultMenuItem submenu = new DefaultMenuItem(menuItem.getName());
	    			submenu.setUrl(menuItem.getUrl());
	    			menu.addElement(submenu);	    			
	    	}
    		
    	
    }

    public MenuModel getModel() {
        return model;
    }   
     
    public SmartCompoundManagment getSmartCompoundManagment() {
		return smartCompoundManagment;
	}

	public void setSmartCompoundManagment(
			SmartCompoundManagment smartCompoundManagment) {
		this.smartCompoundManagment = smartCompoundManagment;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}