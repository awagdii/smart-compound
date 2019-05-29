package com.ntgclarity.smartcompound.portal.managedbean;

import java.io.Serializable;
import java.util.List;
import java.util.Spliterator;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import java.util.Map;
import java.util.function.Consumer;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.ntgclarity.smartcompound.business.management.SmartCompoundManagment;
import com.ntgclarity.smartcompound.common.constatnt.MessagesKeys;
import com.ntgclarity.smartcompound.common.entity.Employee;
import com.ntgclarity.smartcompound.common.entity.Lookup;
import com.ntgclarity.smartcompound.common.entity.LookupType;
import com.ntgclarity.smartcompound.common.entity.Menu;
import com.ntgclarity.smartcompound.portal.base.BaseBean;

@ManagedBean
@ViewScoped
public class MenuBean extends BaseBean implements Serializable {

	@ManagedProperty(value = "#{smartCompoundManagmentImpl}")
	private SmartCompoundManagment smartCompoundManagment;
	
	private List<Menu> allMenus;
	private List<Lookup> allLookup;


	private Menu menu;
	
	
	private LazyDataModel<Menu> lazyMenuModel;
	

	@PostConstruct
	public void init() {
		//loadAllMenus();
		menu= new Menu();
		loadAllLookup();
		LoadData();
	}
	
	private void LoadData() {
		lazyMenuModel = new LazyDataModel<Menu>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			private List<Menu> result;

			@Override
			public Menu getRowData(String rowKey) {
				for (Menu menu : result) {
					if (menu.getId().toString().equals(rowKey))
						return menu;
				}

				return null;
			}

			@Override
			public Object getRowKey(Menu menu) {
				return menu.getId();
			}
			@Override
			public List<Menu> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {

				result = getSmartCompoundManagment().loadMenus(first,
						pageSize, sortField, sortOrder == SortOrder.ASCENDING,
						filters);
				this.setRowCount(getSmartCompoundManagment()
						.getNumOfMenusRows(filters));

				return result;
			}

			@Override
			public void forEach(Consumer<? super Menu> action) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public Spliterator<Menu> spliterator() {
				// TODO Auto-generated method stub
				return null;
			}

		};
	}

	public void loadAllMenus() {
		// allMenus = smartCompoundManagment.getAllMenus();
	}
	
	public void loadAllLookup() {
		 allLookup = smartCompoundManagment.getLookups(LookupType.GENERAL_STATUS);
//		 for (Lookup curr: allLookup){
//			 System.out.println(curr);
//		 }
	}


	public void addMenu(){
		smartCompoundManagment.insertMenu(menu);
		addInfoMessage(MessagesKeys.SMART_COMPOUND_SUCCESS_MESSAGE_MESSAGE);
		menu=new Menu();
	}
	
	public void updateMenu(){
		smartCompoundManagment.updateMenu(menu);
		addInfoMessage(MessagesKeys.SMART_COMPOUND_SUCCESS_MESSAGE_MESSAGE);
		menu=new Menu();
		addInfoMessage("NG_NTS_GENERAL_EDIT_SAVED_SUCCESSFULLY");
	}
	
	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public List<Menu> getAllMenus() {
		return allMenus;
	}

	public void setAllMenus(List<Menu> allMenus) {
		this.allMenus = allMenus;
	}

	public List<Lookup> getAllLookup() {
		return allLookup;
	}

	public void setAllLookup(List<Lookup> allLookup) {
		this.allLookup = allLookup;
	}

	public SmartCompoundManagment getSmartCompoundManagment() {
		return smartCompoundManagment;
	}

	public void setSmartCompoundManagment(
			SmartCompoundManagment smartCompoundManagment) {
		this.smartCompoundManagment = smartCompoundManagment;
	}



	public LazyDataModel<Menu> getLazyMenuModel() {
		return lazyMenuModel;
	}

	public void setLazyMenuModel(LazyDataModel<Menu> lazyMenuModel) {
		this.lazyMenuModel = lazyMenuModel;
	}
	
	
    public void disableButton(){
    	menu=null;
    }
    
    public void checkMenuName(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {

		if (smartCompoundManagment.getMenuByMenuName((String) value, menu.getId()) != null) {
//			System.out.println("throws validator exception  "+(String) value);
			throw new ValidatorException(
					new FacesMessage(
							FacesMessage.SEVERITY_ERROR,
							getMessageValue("SMART_COMPOUND_MENU_PAGE_EXIST_VALIDATION"),
							null));
		}
		
		if (((String) value).length() < 3 ||((String) value).length() > 30) {
			throw new ValidatorException(
					new FacesMessage(
							FacesMessage.SEVERITY_ERROR,
							getMessageValue("SMART_COMPOUND_MENU_PAGE_MENU_VALIDATOR_MENU_NAME_ERROR_MESSAGE"),
							null));
		}
//		System.out.println("value of name ______________: "+(String) value);
	}


/* <p:commandButton
		value="#{msgs.SMART_COMPOUND_MENU_PAGE_MENU_CREATE_BUTTON}"
		actionListener="#{menuBean.init()}" update="createMenuDialog" disabled="#{!baseBean.superAdmin}"
		oncomplete="PF('createMenuDialog').show()"></p:commandButton>*/
    
}
