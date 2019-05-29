package com.ntgclarity.smartcompound.portal.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Spliterator;
import java.util.function.Consumer;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.ViewHandler;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import org.hibernate.Query;
import org.primefaces.event.FlowEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.ntgclarity.smartcompound.business.management.SmartCompoundManagment;
import com.ntgclarity.smartcompound.common.constatnt.MessagesKeys;
import com.ntgclarity.smartcompound.common.entity.Compound;
import com.ntgclarity.smartcompound.common.entity.Employee;
import com.ntgclarity.smartcompound.common.entity.Group;
import com.ntgclarity.smartcompound.common.entity.Lookup;
import com.ntgclarity.smartcompound.common.entity.LookupType;
import com.ntgclarity.smartcompound.common.entity.Group;
import com.ntgclarity.smartcompound.common.exception.SmartCompoundException;
import com.ntgclarity.smartcompound.portal.base.BaseBean;

@ManagedBean
@ViewScoped
public class EmployeeBean extends BaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{smartCompoundManagmentImpl}")
	private SmartCompoundManagment smartCompoundManagment;
	private List<Employee> allEmployees;
	private String enteredPassword;
	private String newPasswordConfirmation;

	private String newPassword;
	private Employee selectedEmployee;
	private LazyDataModel<Employee> lazyEmployeeModel;
	private List<Compound> compounds;
	private Compound selectedCompound;
	private List<Group> groups;
	private List<Group> selectedGroups;
	private List<Group> employeeGroups;

	@PostConstruct
	public void init() throws SmartCompoundException {
		// loadAllEmployees();

		if (isSuperAdmin()) {
			compounds = smartCompoundManagment.getALLCompounds();
		}
		if (isNotSuperAdmin()) {
			selectedCompound = getCurrentCompound();
			groups = smartCompoundManagment.getCompoundGroups(selectedCompound);
		}
		initiateEmployee();
		LoadData();
	}

	public void trimUsername() {
		selectedEmployee.setUsername(selectedEmployee.getUsername().replace(
				"@" + selectedEmployee.getCompound().getDomain(), ""));
	}

	public void checkUserName(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {

		if (((String) value).length() < 3 || ((String) value).length() > 20) {
			throw new ValidatorException(
					new FacesMessage(
							FacesMessage.SEVERITY_ERROR,
							getMessageValue("SMART_COMPOUND_EMPLOYEE_PAGE_LENGTH_VALIDATION"),
							null));
		}
		if (smartCompoundManagment.getEmployeeByUsername((String) value + "@"
				+ selectedCompound.getDomain()) != null
				&& (selectedEmployee.getId() == null || !selectedEmployee
						.getUsername().equals(value))) {
			throw new ValidatorException(
					new FacesMessage(
							FacesMessage.SEVERITY_ERROR,
							getMessageValue("SMART_COMPOUND_EMPLOYEE_PAGE_EXIST_VALIDATION"),
							null));
		}
	}

	// **start of Nazer work**

	public String getNewPasswordConfirmation() {
		return newPasswordConfirmation;
	}

	public void setNewPasswordConfirmation(String newPasswordConfirmation) {
		this.newPasswordConfirmation = newPasswordConfirmation;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	private void LoadData() {
		lazyEmployeeModel = new LazyDataModel<Employee>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			private List<Employee> result;

			@Override
			public Employee getRowData(String rowKey) {
				for (Employee employee : result) {
					if (employee.getId().toString().equals(rowKey))
						return employee;
				}

				return null;
			}

			@Override
			public Object getRowKey(Employee employee) {
				return employee.getId();
			}

			@Override
			public List<Employee> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {
				addFiltration(filters);
				result = getSmartCompoundManagment().loadEmployees(first,
						pageSize, sortField, sortOrder == SortOrder.ASCENDING,
						filters);
				this.setRowCount(getSmartCompoundManagment()
						.getNumOfEmployeesRows(filters));

				return result;
			}

			private void addFiltration(Map<String, Object> filters) {
				if (isNotSuperAdmin()) {
					filters.put("compound", getCurrentCompound());
				}
				if (isSuperAdmin()) {
					filters.put("createdBy", getCurrentLoggedEmployee());
				}
			}

			@Override
			public void forEach(Consumer<? super Employee> action) {
				// TODO Auto-generated method stub

			}

			@Override
			public Spliterator<Employee> spliterator() {
				// TODO Auto-generated method stub
				return null;
			}

		};

	}

	public List<Employee> getAllEmployees() {
		return allEmployees;
	}

	public void setAllEmployees(List<Employee> allEmployees) {
		this.allEmployees = allEmployees;
	}

	public SmartCompoundManagment getSmartCompoundManagment() {
		return smartCompoundManagment;
	}

	public void setSmartCompoundManagment(
			SmartCompoundManagment smartCompoundManagment) {
		this.smartCompoundManagment = smartCompoundManagment;
	}

	public Employee getSelectedEmployee() {
		return selectedEmployee;
	}

	public void setSelectedEmployee(Employee selectedEmployee) {
		this.selectedEmployee = selectedEmployee;
	}

	public LazyDataModel<Employee> getLazyEmployeeModel() {
		return lazyEmployeeModel;
	}

	public String getEnteredPassword() {
		return enteredPassword;
	}

	public void setEnteredPassword(String enteredPassword) {
		this.enteredPassword = enteredPassword;
	}

	public int resetPassword() {
		if (newPassword.equals(newPasswordConfirmation)) {
			Employee emp = new Employee();
			emp.setPassword("1234");
			if (smartCompoundManagment.resetPassword(enteredPassword,
					newPassword, emp) == 0) {
				addMessage("please,check your old password !!",
						FacesMessage.SEVERITY_WARN);

				return 1;
			} else {
				addMessage("password updated successfully",
						FacesMessage.SEVERITY_INFO);

				return 1;
			}
		} else {

			addMessage("New Password must match Confirm Password",
					FacesMessage.SEVERITY_FATAL);
			return 0;
		}

	}

	public void addMessage(String summary, Severity type) {

		FacesMessage message = new FacesMessage(type, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	// **end of Nazer work **

	/** START HEBA'S WORK **/
	public void initiateEmployee() {
		selectedEmployee = new Employee();
		selectedGroups = new ArrayList<>();

		// return selectedEmployee;
	}

	public void setLazyEmployeeModel(LazyDataModel<Employee> lazyEmployeeModel) {
		this.lazyEmployeeModel = lazyEmployeeModel;
	}

	public void insertEmployee() {

		selectedEmployee.setCompound(selectedCompound);
		selectedEmployee.setCreatedBy(getCurrentLoggedEmployee());
		selectedEmployee.setUsername(selectedEmployee.getUsername() + "@"
				+ selectedCompound.getDomain());
		smartCompoundManagment.insertEmployee(selectedEmployee);
		smartCompoundManagment.insertEmployeeGroups(selectedEmployee,
				selectedGroups);
		addInfoMessage(MessagesKeys.SMART_COMPOUND_EMPLOYEE_PAGE_EMPLOYEE_INSERTION_MESSAGE);
	}

	private boolean skip;

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

	public String onFlowProcess(FlowEvent event) {

		if (skip) {
			skip = false; // reset in case user goes back
			return "confirm";
		} else {
			return event.getNewStep();
		}
	}

	private List<Lookup> identificationTypeLookup;

	public List<Lookup> getIdentificationTypeLookup() {
		identificationTypeLookup = smartCompoundManagment
				.getLookups(LookupType.IDENTIFICATION_TYPE);
		return identificationTypeLookup;
	}

	public void setIdentificationTypeLookup(
			List<Lookup> identificationTypeLookup) {
		this.identificationTypeLookup = identificationTypeLookup;
	}

	private List<Lookup> genderLookup;

	public List<Lookup> getGenderLookup() {
		genderLookup = smartCompoundManagment.getLookups(LookupType.GENDER);
		return genderLookup;
	}

	public void setGenderLookup(List<Lookup> genderLookup) {
		this.genderLookup = genderLookup;
	}

	private List<Lookup> statusLookup;

	public List<Lookup> getStatusLookup() {
		statusLookup = smartCompoundManagment
				.getLookups(LookupType.EMPLOYEE_STATUS);
		return statusLookup;
	}

	public void setStatusLookup(List<Lookup> statusLookup) {
		this.statusLookup = statusLookup;
	}

	private List<Lookup> salutationLookup;

	public List<Lookup> getSalutationLookup() {
		salutationLookup = smartCompoundManagment
				.getLookups(LookupType.SALUTATION);
		return salutationLookup;
	}

	public void setSalutationLookup(List<Lookup> salutationLookup) {
		this.salutationLookup = salutationLookup;
	}

	public void refreshPage() {
		FacesContext fc = FacesContext.getCurrentInstance();
		String refreshpage = fc.getViewRoot().getViewId();
		ViewHandler ViewH = fc.getApplication().getViewHandler();
		UIViewRoot UIV = ViewH.createView(fc, refreshpage);
		UIV.setViewId(refreshpage);
		fc.setViewRoot(UIV);
	}

	/** END HEBA'S WORK **/

	/** END HEBA'S WORK **/

	/** start reda's work **/
	// public void loadGroups() {
	// if (isNotSuperAdmin()) {
	// groups = smartCompoundManagment
	// .getCompoundGroups(getCurrentCompound());
	// }
	// }

	public void onCompoundChange() {
		groups = smartCompoundManagment.getCompoundGroups(selectedCompound);
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	public List<Group> getSelectedGroups() {
		return selectedGroups;
	}

	public void setSelectedGroups(List<Group> selectedGroups) {
		this.selectedGroups = selectedGroups;
	}

	public List<Group> getEmployeeGroups() {
		return employeeGroups;
	}

	public void setEmployeeGroups(List<Group> employeeGroups) {
		this.employeeGroups = employeeGroups;
	}

	public void loadEmployeeGroups() {
		loadSelectedEmployeeCompound();
		selectedGroups = smartCompoundManagment
				.getEmployeeGroups(selectedEmployee);
		if(!selectedEmployee.isSuperAdmin())
		{
			groups = smartCompoundManagment.getCompoundGroups(selectedEmployee.getCompound());

		}
	}

	public List<Compound> getCompounds() {
		return compounds;
	}

	public void setCompounds(List<Compound> compounds) {
		this.compounds = compounds;
	}

	public Compound getSelectedCompound() {
		return selectedCompound;
	}

	public void setSelectedCompound(Compound selectedCompound) {
		this.selectedCompound = selectedCompound;
	}

	public void loadSelectedEmployeeCompound() {
		selectedCompound = selectedEmployee.getCompound();
	}

	public void disableButton() {
		selectedEmployee=null;
	}

}