package com.ntgclarity.smartcompound.business.management;

import java.util.List;
import java.util.Map;

import com.ntgclarity.smartcompound.common.entity.Bill;
import com.ntgclarity.smartcompound.common.entity.BillCalculation;
import com.ntgclarity.smartcompound.common.entity.BillDetails;
import com.ntgclarity.smartcompound.common.entity.City;
import com.ntgclarity.smartcompound.common.entity.Compound;
import com.ntgclarity.smartcompound.common.entity.Country;
import com.ntgclarity.smartcompound.common.entity.Employee;
import com.ntgclarity.smartcompound.common.entity.Facility;
import com.ntgclarity.smartcompound.common.entity.Group;
import com.ntgclarity.smartcompound.common.entity.Lookup;
import com.ntgclarity.smartcompound.common.entity.LookupType;
import com.ntgclarity.smartcompound.common.entity.Menu;
import com.ntgclarity.smartcompound.common.entity.MenuItem;
import com.ntgclarity.smartcompound.common.entity.Nationality;
import com.ntgclarity.smartcompound.common.entity.Order;
import com.ntgclarity.smartcompound.common.entity.Service;
import com.ntgclarity.smartcompound.common.entity.ServiceUsageRate;
import com.ntgclarity.smartcompound.common.entity.SystemConfiguration;
import com.ntgclarity.smartcompound.common.entity.Tenant;
import com.ntgclarity.smartcompound.common.entity.Ticket;
import com.ntgclarity.smartcompound.common.entity.TicketHistory;
import com.ntgclarity.smartcompound.common.entity.TicketImage;
import com.ntgclarity.smartcompound.common.entity.TicketStatus;
import com.ntgclarity.smartcompound.common.enums.BillCycleStatus;
import com.ntgclarity.smartcompound.common.exception.SmartCompoundException;

@org.springframework.stereotype.Service
public interface SmartCompoundManagment {

	List<Employee> getAllEmployees() throws SmartCompoundException;

	List<TicketStatus> getAllTicketStatuses();

	Employee getEmployee(Long id);

	List<MenuItem> getAllMenuItems();

	public Compound insertCompound(Compound compound)
			throws SmartCompoundException;

	/* added by Hend */

	com.ntgclarity.smartcompound.common.entity.Service insertService(
			com.ntgclarity.smartcompound.common.entity.Service service)
			throws SmartCompoundException;

	List<com.ntgclarity.smartcompound.common.entity.Service> loadServices(
			int first, int pageSize, String sortField, boolean b,
			Map<String, Object> filters);

	int getNumOfServicesRows(Map<String, Object> filters);

	List<BillDetails> getBillDetailsOfBill(Bill bill);

	List<ServiceUsageRate> insertServiceRates(List<ServiceUsageRate> usageRates);

	/* end oh Hend's part */

	Order insertOrder(Order order) throws SmartCompoundException;

	List<Facility> getCompoundFacilites(Compound compound, String searchParam)
			throws SmartCompoundException;

	int getNumOfTicketsRows(Map<String, Object> filters);

	List<Ticket> loadTickets(int first, int pageSize, String sortField,
			boolean b, Map<String, Object> filters);

	List<Order> loadOrders(int first, int pageSize, String sortField,
			boolean b, Map<String, Object> filters);

	int getNumOfOrdersRows(Map<String, Object> filters);

	List<Service> getCompoundServices(Compound compound, String searchParam);

	com.ntgclarity.smartcompound.common.entity.Service getService(Long id);

	List<Tenant> getCompoundTenants(Compound compound, String searchParam);

	Tenant getTenant(Long id);

	public List getTicketStatusSequences(TicketStatus ticketStatus);

	// List<Ticket> getAllTickets();

	Ticket insertTicket(Ticket ticket);

	Ticket getTicket(Long long1);

	List<Tenant> getAllTenants(Compound comp);

	List<com.ntgclarity.smartcompound.common.entity.Service> getAllServices(
			Compound comp);

	List<Facility> getAllFacilities(Compound comp);

	Facility getFacility(Long long1);

	/* added by Mai */
	public List<SystemConfiguration> getAllSystemConfigurations();

	public SystemConfiguration getSystemConfiguration(Long id);

	public SystemConfiguration insertSystemConfiguration(
			SystemConfiguration systemConfiguration);

	public int getNumOfSystemConfigurationsRows(Map<String, Object> filters);

	public List<SystemConfiguration> loadSystemConfigurations(int first,
			int pageSize, String sortField, boolean b,
			Map<String, Object> filters);

	/* End of Mai's part */

	/** START HEBA'S WORK **/
	public Employee insertEmployee(Employee employee);

	/** END HEBA'S WORK **/

	/**
	 * @author KFadel
	 **/
	List<Employee> loadEmployees(int first, int pageSize, String sortField,
			boolean b, Map<String, Object> filters);

	int getNumOfEmployeesRows(Map<String, Object> filters);

	List<Lookup> getLookups(LookupType lookupType);

	public Facility insertFacility(Facility facility)
			throws SmartCompoundException;

	List<Facility> loadFacilities(int first, int pageSize, String sortField,
			boolean b, Map<String, Object> filters);

	int getNumOfFacilitiesRows(Map<String, Object> filters);

	Compound updateCompound(Compound compound) throws SmartCompoundException;

	MenuItem getMenuItem(Long id);

	MenuItem insertMenuItem(MenuItem menuItem);

	MenuItem updateMenuItem(MenuItem menuItem);

	List<Menu> getAllMenus();

	Menu getMenu(Long id);

	Menu insertMenu(Menu menu);

	Menu updateMenu(Menu menu);

	List<Group> getAllGroup();

	List<MenuItem> getGroupMenuItem(Group group);

	void insertGroupMenuItem(Group group, List<MenuItem> items);

	Group getGroup(Long id);

	Group insertGroup(Group group);

	Group updateGroup(Group group);

	List<Group> loadGroups(int first, int pageSize, String sortField,
			boolean ascending, Map<String, Object> filters);

	int getNumOfGroupsRows(Map<String, Object> filters);

	List<Menu> loadMenus(int first, int pageSize, String sortField,
			boolean ascending, Map<String, Object> filters);

	int getNumOfMenusRows(Map<String, Object> filters);

	List<MenuItem> loadMenuItems(int first, int pageSize, String sortField,
			boolean ascending, Map<String, Object> filters);

	int getNumOfMenuItemsRows(Map<String, Object> filters);

	List<MenuItem> getEmployeeMenuItems(Employee employee);

	List<Group> getEmployeeGroups(Employee employee);

	void insertEmployeeGroups(Employee employee, List<Group> groups);

	Tenant insertTenant(Tenant tenant);

	Lookup getLookup(Long id);

	Tenant updateTenant(Tenant tenant);

	Tenant getTenant(String username, String password)
			throws SmartCompoundException;

	List<Facility> getAllFacilitiesRelatedToTenant(Tenant tenant)
			throws SmartCompoundException;

	List<Order> getAllOrdersRelatedToTenant(Tenant tenant)
			throws SmartCompoundException;

	List<Tenant> loadTenants(int first, int pageSize, String sortField,
			boolean ascending, Map<String, Object> filters);

	int getNumOfTenantsRows(Map<String, Object> filters);

	Lookup getLookups(Long long1);

	List<Group> getAllGroups();

	List<Employee> getEmployeesInGroup(Compound compound, Group group);

	void deleteEmployeesFromGroup(Compound compound, Group group,
			List<Employee> employeeList);

	void addEmployeesToGroup(Compound compound, Group group,
			List<Employee> employeeList);

	int resetPassword(String oldPssword, String newPassword, Employee emp);

	List<Compound> loadCompounds(int first, int pageSize, String sortField,
			boolean b, Map<String, Object> filters);

	int getNumOfCompoundsRows(Map<String, Object> filters);

	List<Group> getCompoundGroups(Compound compound);

	List<Compound> getALLCompounds();

	TicketStatus getTicketStatus(Long id);

	TicketStatus getCurrentStatus(Ticket ticket);

	/* added by Mai */

	List<BillCalculation> startCalculatingBill(Compound compoud) throws SmartCompoundException;

	List<Service> getTenantCompoundServices(Compound compound,Tenant tenant, Facility facility);

	public List<Facility> getTenantCompoundFacilities(Tenant tenant,
			Compound compound);

	public Order getCompoundTenantOrder(Compound compound, Tenant tenant,
			Service service, Facility facility);

	BillCalculation getSpecificBillCalculation(Compound compound,
			Service service, Order order);

	List<BillCalculation> getAllBillCalculations();

	BillCalculation updateBillCalculation(BillCalculation billCalculation);

	BillCalculation insertBillCalculation(BillCalculation billCalculation);

	BillCalculation getBillCalculation(Long id);

	Double getAllServiceUsageRate(Compound compound, Service service,
			Double usageAmount);

	List<Service> getTenantServices(Compound compound, Tenant tenant);

	public void endBillingCycle(Compound compound,Employee employee)
			throws SmartCompoundException;

	List<Bill> getAllBills();

	Bill updateBill(Bill bill);

	Bill insertBill(Bill bill);

	Bill getBill(Long id);

	List<BillDetails> getAllBillDetails();

	BillDetails updateBillDetails(BillDetails billDetails);

	BillDetails insertBillDetails(BillDetails billDetails);

	BillDetails getBillDetails(Long id);

	Order updateOrder(Order order);
	 List<Tenant> getDistinctTenantsFromBillCalculation(Compound compound, String searchParam);
	/* End of Mai's part */

	Tenant getTenantByEmail(String email) throws SmartCompoundException;

	List<Country> getAllCountries();

	List<String> getAllCountriesAsString();

	Country getCountry(Long id);

	Country insertCountry(Country country);

	Country updateCountry(Country country);

	List<City> getAllCities();

	List<City> getAllCitiesInCountry(Country country);

	List<String> getAllCitiesInCountryAsString(Country country);

	List<String> getAllCitiesAsString();

	City getCity(Long id);

	City insertCity(City city);

	City updateCity(City city);

	List<Nationality> getAllNationalities();

	List<String> getAllNationalitiesAsString();

	Nationality getNationality(Long id);

	Nationality insertNationality(Nationality nationality);

	Nationality updateNationality(Nationality nationality);

	List<Bill> loadBills(int first, int pageSize, String sortField, boolean b,
			Map<String, Object> filters);

	int getNumOfBillsRows(Map<String, Object> filters);

	TicketHistory insertTicketHistory(TicketHistory ticketHistory);

	Compound getCompound(Long id);

	List<TicketHistory> loadTicketHistories(int first, int pageSize,
			String sortField, boolean b, Map<String, Object> filters);

	int getNumOfTicketHistoriesRows(Map<String, Object> filters);

	Ticket updateTicket(Ticket selectedTicket, Employee employee);

	List<Employee> getAllEmployeesInCompound(Compound compound);

	Tenant getTenantByUsername(String username);

	public List<TicketImage> getAllTicketImagesByTicket(Ticket ticket)
			throws SmartCompoundException;

	public TicketImage getTicketImage(Long id) throws SmartCompoundException;

	public TicketImage insertTicketImage(TicketImage ticketImage)
			throws SmartCompoundException;

	List<Group> getAllGroupByCompound(Compound selectedCompound);

	List<Service> getTenantServicesRelatedToFacilty(Compound currentCompound,
			Tenant relatedTenant, Facility facility);

	List<MenuItem> getAllMenuItemsAfterCheckSuperAdminFlag(boolean isSuperAdmin);

	List<Facility> getCompoundFacilitesByTenant(Compound compound, Tenant tenant)
			throws SmartCompoundException;

	Employee getEmployeeByUsername(String username);

	List<Service> getAllActiveServices(Compound currentCompound);

	List<Ticket> getTicketsOfTenant(Tenant tenant);

	List<Order> getOrdersOfTenant(Tenant tenant);

	List<Bill> getBillsOfTenant(Tenant tenant);
	
	List<TicketHistory> loadTicketHistories(Ticket ticket);
	
	List<BillDetails> loadBillDetails(int first, int pageSize, String sortField,
			boolean ascending, Map<String, Object> filters);

	int getNumOfBillDetailsRows(Map<String, Object> filters);
	
	 MenuItem getMenuItemByName(String name, Long id);
	 
	 Menu getMenuByMenuName(String menuName, Long id);

	void updateOrderAndBillCalculation(BillCalculation billCalculation,
			Order order);

	BillCycleStatus getCompoundBillCycleStatus(Compound compound);
}
