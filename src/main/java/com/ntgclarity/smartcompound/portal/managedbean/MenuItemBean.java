package com.ntgclarity.smartcompound.portal.managedbean;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.ServletContext;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.ntgclarity.smartcompound.business.management.SmartCompoundManagment;
import com.ntgclarity.smartcompound.common.constatnt.MessagesKeys;
import com.ntgclarity.smartcompound.common.entity.Lookup;
import com.ntgclarity.smartcompound.common.entity.LookupType;
import com.ntgclarity.smartcompound.common.entity.Menu;
import com.ntgclarity.smartcompound.common.entity.MenuItem;
import com.ntgclarity.smartcompound.common.utils.Utils;
import com.ntgclarity.smartcompound.portal.base.BaseBean;

@ManagedBean
@ViewScoped
public class MenuItemBean extends BaseBean implements Serializable {

	@ManagedProperty(value = "#{smartCompoundManagmentImpl}")
	private SmartCompoundManagment smartCompoundManagment;

	private List<Menu> allMenus;
	private List<Lookup> allLookup;

	private MenuItem menuItem;
	private LazyDataModel<MenuItem> lazyMenuItemModel;

	@PostConstruct
	public void init() {
		menuItem = new MenuItem();
		allMenus = smartCompoundManagment.getAllMenus();
		allLookup = smartCompoundManagment.getLookups(LookupType.GENERAL_STATUS);
		LoadData();
	}

	public List<String> completePagesURL(String pageUrl) {

		List<String> result = new ArrayList<>();
		String string = ".*" + pageUrl+ ".*index.xhtml";
		Pattern pattern = Pattern.compile(string);
		ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance()
				.getExternalContext().getContext();

		String realPath = ctx.getRealPath("/");
//		System.out.println(realPath);
		File directory = new File(realPath);
		List<String> urlList = (List<String>) Utils.getResourcesFromDirectory(
				directory, pattern);
		for (String currUrl : urlList) {
			result.add(currUrl.substring(currUrl.lastIndexOf(".war") + 4));
		}
		return result;
	}

	private void LoadData() {
		lazyMenuItemModel = new LazyDataModel<MenuItem>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			private List<MenuItem> result;

			@Override
			public MenuItem getRowData(String rowKey) {
				for (MenuItem menuItem : result) {
					if (menuItem.getId().toString().equals(rowKey))
						return menuItem;
				}

				return null;
			}

			@Override
			public Object getRowKey(MenuItem menuItem) {
				return menuItem.getId();
			}

			@Override
			public List<MenuItem> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {

				result = getSmartCompoundManagment().loadMenuItems(first,
						pageSize, sortField, sortOrder == SortOrder.ASCENDING,
						filters);
				this.setRowCount(getSmartCompoundManagment()
						.getNumOfMenuItemsRows(filters));

				return result;
			}
		
			@Override
			public void forEach(Consumer<? super MenuItem> action) {
				// TODO Auto-generated method stub

			}

			@Override
			public Spliterator<MenuItem> spliterator() {
				// TODO Auto-generated method stub
				return null;
			}

		};
	}

	public void addMenuItem() {
		smartCompoundManagment.insertMenuItem(menuItem);
		addInfoMessage(MessagesKeys.SMART_COMPOUND_SUCCESS_MESSAGE_MESSAGE);
		menuItem = new MenuItem();
	}

	public void updateMenuItem() {
		smartCompoundManagment.updateMenuItem(menuItem);
		addInfoMessage(MessagesKeys.SMART_COMPOUND_SUCCESS_MESSAGE_MESSAGE);
		menuItem = new MenuItem();
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

	public MenuItem getMenuItem() {
		return menuItem;
	}

	public void setMenuItem(MenuItem menuItem) {
//		System.out.println(menuItem);
		this.menuItem = menuItem;
	}

	public LazyDataModel<MenuItem> getLazyMenuItemModel() {
		return lazyMenuItemModel;
	}

	public void setLazyMenuItemModel(LazyDataModel<MenuItem> lazyMenuItemModel) {
		this.lazyMenuItemModel = lazyMenuItemModel;
	}
	
	
    public void disableButton(){
       menuItem=null;
    }
    
    public void checkMenuItemName(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {

		if (smartCompoundManagment.getMenuItemByName((String) value,menuItem.getId()) != null) {
//			System.out.println("throws validator exception  "+(String) value);
			throw new ValidatorException(
					new FacesMessage(
							FacesMessage.SEVERITY_ERROR,
							getMessageValue("SMART_COMPOUND_MENUITEM_PAGE_EXIST_VALIDATION"),
							null));
		}
		
		if (((String) value).length() < 3 ||((String) value).length() > 30) {
			throw new ValidatorException(
					new FacesMessage(
							FacesMessage.SEVERITY_ERROR,
							getMessageValue("SMART_COMPOUND_MENU_PAGE_MENU_VALIDATOR_MENU_NAME_ERROR_MESSAGE"),
							null));
		}
	}

	/* <p:commandButton
	value="#{msgs.SMART_COMPOUND_MENUITEM_PAGE_MENUITEM_BUTTON}"
	actionListener="#{menuItemBean.init()}" update="createMenuItemDialog"
	oncomplete="PF('createMenuItemDialog').show()" disabled="#{!baseBean.superAdmin}"></p:commandButton> */
    
}
