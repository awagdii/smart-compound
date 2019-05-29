package com.ntgclarity.smartcompound.business.managementimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ntgclarity.smartcompound.business.management.SmartCompoundManagment;
import com.ntgclarity.smartcompound.business.service.BillCalculationService;
import com.ntgclarity.smartcompound.business.service.BillDetailsService;
import com.ntgclarity.smartcompound.business.service.BillService;
import com.ntgclarity.smartcompound.business.service.CityService;
import com.ntgclarity.smartcompound.business.service.CompoundService;
import com.ntgclarity.smartcompound.business.service.CountryService;
import com.ntgclarity.smartcompound.business.service.EmployeeService;
import com.ntgclarity.smartcompound.business.service.FacilityService;
import com.ntgclarity.smartcompound.business.service.GroupService;
import com.ntgclarity.smartcompound.business.service.LookupService;
import com.ntgclarity.smartcompound.business.service.MenuItemService;
import com.ntgclarity.smartcompound.business.service.MenuService;
import com.ntgclarity.smartcompound.business.service.NationalityService;
import com.ntgclarity.smartcompound.business.service.OrderService;
import com.ntgclarity.smartcompound.business.service.ServiceService;
import com.ntgclarity.smartcompound.business.service.ServiceUsageRateService;
import com.ntgclarity.smartcompound.business.service.SystemConfigurationService;
import com.ntgclarity.smartcompound.business.service.TenantFacilityService;
import com.ntgclarity.smartcompound.business.service.TenantService;
import com.ntgclarity.smartcompound.business.service.TicketHistoryService;
import com.ntgclarity.smartcompound.business.service.TicketImageService;
import com.ntgclarity.smartcompound.business.service.TicketService;
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
public class SmartCompoundManagmentImpl implements SmartCompoundManagment {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private TicketService ticketService;

	@Autowired
	private CompoundService compoundService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private FacilityService facilityService;

	@Autowired
	private TenantService tenantService;

	@Autowired
	private ServiceService serviceService;
	@Autowired
	private MenuItemService menuItemService;

	@Autowired
	private LookupService lookupService;

	@Autowired
	private SystemConfigurationService systemConfigurationService;

	@Autowired
	private MenuService menuService;

	@Autowired
	private GroupService groupService;

	@Autowired
	private BillCalculationService billCalculationService;

	@Autowired
	private TenantFacilityService tenantFacilityService;

	@Autowired
	private CountryService countryService;

	@Autowired
	private CityService cityService;

	@Autowired
	private NationalityService nationalityService;

	@Autowired
	private ServiceUsageRateService serviceUsageRateService;

	@Autowired
	private BillService billService;

	@Autowired
	private BillDetailsService billDetailsService;
	@Autowired
	private TicketHistoryService ticketHistoryService;

	@Autowired
	private TicketImageService ticketImageService;

	@Override
	public List<Employee> getAllEmployees() throws SmartCompoundException {
		return employeeService.getAllEmployees();
	}

	@Override
	public Employee getEmployee(Long id) {
		return employeeService.getEmployee(id);
	}

	@Override
	public Compound insertCompound(Compound compound)
			throws SmartCompoundException {

		return compoundService.insertCompound(compound);

	}

	@Override
	public Compound updateCompound(Compound compound)
			throws SmartCompoundException {

		return compoundService.updateCompound(compound);

	}

	@Override
	public int getNumOfTicketsRows(Map<String, Object> filters) {
		return ticketService.getNumOfTicketsRows(filters);
	}

	@Override
	public List<Ticket> loadTickets(int first, int pageSize, String sortField,
			boolean ascending, Map<String, Object> filters) {
		return ticketService.loadTickets(first, pageSize, sortField, ascending,
				filters);
		// return null;

	}

	@Override
	public com.ntgclarity.smartcompound.common.entity.Service insertService(
			com.ntgclarity.smartcompound.common.entity.Service service)
			throws SmartCompoundException {
		return serviceService.insertService(service);
	}

	@Override
	public int getNumOfServicesRows(Map<String, Object> filters) {
		return serviceService.getNumOfServicesRows(filters);
	}

	@Override
	public List<ServiceUsageRate> insertServiceRates(
			List<ServiceUsageRate> usageRates) {
		return serviceService.insertServiceUsageRates(usageRates);
	}

	@Override
	public List<BillDetails> getBillDetailsOfBill(Bill bill) {
		return billDetailsService.getBillDetailsOfBill(bill);
	}

	/*end oh Hend's part*/

	@Override
	public List<Order> loadOrders(int first, int pageSize, String sortField,
			boolean ascending, Map<String, Object> filters) {
		return orderService.loadOrders(first, pageSize, sortField, ascending,
				filters);

	}

	@Override
	public int getNumOfOrdersRows(Map<String, Object> filters) {
		return orderService.getNumOfOrdersRows(filters);
	}

	@Override
	public Order insertOrder(Order order) throws SmartCompoundException {
		return orderService.insertOrder(order);
	}

	@Override
	public List<Facility> getCompoundFacilites(Compound compound,
			String searchParam) throws SmartCompoundException {
		return facilityService.getCompoundFacilites(compound, searchParam);
	}

	@Override
	public List<com.ntgclarity.smartcompound.common.entity.Service> getCompoundServices(
			Compound compound, String searchParam) {
		return serviceService.getCompoundServices(compound, searchParam);
	}

	@Override
	public com.ntgclarity.smartcompound.common.entity.Service getService(Long id) {
		return serviceService.getService(id);
	}

	@Override
	public TicketStatus getTicketStatus(Long id) {
		return ticketService.getTicketStatusService(id);
	}

	@Override
	public List<Tenant> getCompoundTenants(Compound compound, String searchParam) {
		return tenantService.getCompoundTenants(compound, searchParam);
	}

	@Override
	public Tenant getTenant(Long id) {
		return tenantService.getTenant(id);
	}

	@Override
	public List<com.ntgclarity.smartcompound.common.entity.Service> loadServices(
			int first, int pageSize, String sortField, boolean b,
			Map<String, Object> filters) {
		return serviceService.loadServices(first, pageSize, sortField, b,
				filters);
	}

	// @Override
	// public List<Ticket> getAllTickets() {
	// // TODO Auto-generated method stub
	// return ticketService.loadTickets(first, pageSize, sortField, ascending,
	// filters);
	// }

	@Override
	public Ticket insertTicket(Ticket ticket) {
		// TODO Auto-generated method stub
		
		return ticketService.insertTicket(ticket);
	}

	@Override
	public Ticket getTicket(Long long1) {

		return null;
	}

	@Override
	public List<Tenant> getAllTenants(Compound comp) {
//		System.out.println("result size in management"
//				+ tenantService.getAllTenants(comp));
		return tenantService.getAllTenants(comp);
	}

	@Override
	public List<com.ntgclarity.smartcompound.common.entity.Service> getAllServices(
			Compound comp) {
		// TODO Auto-generated method stub
		return serviceService.getAllServices(comp);
	}

	@Override
	public List<Facility> getAllFacilities(Compound comp) {
		// TODO Auto-generated method stub
		return facilityService.getAllFacilities(comp);
	}

	@Override
	public Facility getFacility(Long id) {

		try {
			return facilityService.getFacility(id);
		} catch (SmartCompoundException e) {

			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<SystemConfiguration> getAllSystemConfigurations() {
		return systemConfigurationService.getAllSystemConfigurations();
	}

	@Override
	public SystemConfiguration getSystemConfiguration(Long id) {
		return systemConfigurationService.getSystemConfiguration(id);
	}

	@Override
	public SystemConfiguration insertSystemConfiguration(
			SystemConfiguration systemConfiguration) {

		return systemConfigurationService
				.insertSystemConfiguration(systemConfiguration);

	}

	@Override
	public int getNumOfSystemConfigurationsRows(Map<String, Object> filters) {
		return systemConfigurationService
				.getNumOfSystemConfigurationsRows(filters);
	}

	@Override
	public List<SystemConfiguration> loadSystemConfigurations(int first,
			int pageSize, String sortField, boolean ascending,
			Map<String, Object> filters) {
		return systemConfigurationService.loadSystemConfigurations(first,
				pageSize, sortField, ascending, filters);

	}

	

	public Employee insertEmployee(Employee employee) {
		return employeeService.insertEmployee(employee);
	}

	
	@Override
	public List<Employee> loadEmployees(int first, int pageSize,
			String sortField, boolean ascending, Map<String, Object> filters) {
		return employeeService.loadEmployees(first, pageSize, sortField,
				ascending, filters);
	}

	@Override
	public int getNumOfEmployeesRows(Map<String, Object> filters) {
		return employeeService.getNumOfEmployeesRows(filters);
	}

	@Override
	public List<Lookup> getLookups(LookupType lookupType) {
		return lookupService.getLookups(lookupType);

	}

	@Override
	public Facility insertFacility(Facility facility)
			throws SmartCompoundException {
		return facilityService.insertFacility(facility);
	}

	@Override
	public List<Facility> loadFacilities(int first, int pageSize,
			String sortField, boolean ascending, Map<String, Object> filters) {
		return facilityService.loadFacilities(first, pageSize, sortField,
				ascending, filters);
	}

	@Override
	public int getNumOfFacilitiesRows(Map<String, Object> filters) {
		return facilityService.getNumOfFacilitiesRows(filters);
	}

	@Override
	public List<MenuItem> getAllMenuItems() {
		// TODO Auto-generated method stub
		return menuItemService.getAllMenuItems();
	}

	@Override
	public MenuItem getMenuItem(Long id) {
		// TODO Auto-generated method stub
		return menuItemService.getMenuItem(id);
	}

	@Override
	public MenuItem insertMenuItem(MenuItem menuItem) {
		// TODO Auto-generated method stub
		return menuItemService.insertMenuItem(menuItem);
	}

	@Override
	public MenuItem updateMenuItem(MenuItem menuItem) {
		// TODO Auto-generated method stub
		return menuItemService.updateMenuItem(menuItem);
	}

	@Override
	public List<Menu> getAllMenus() {
		// TODO Auto-generated method stub
		return menuService.getAllMenus();
	}

	@Override
	public Menu getMenu(Long id) {
		// TODO Auto-generated method stub
		return menuService.getMenu(id);
	}

	@Override
	public Menu insertMenu(Menu menu) {
		// TODO Auto-generated method stub
		return menuService.insertMenu(menu);
	}

	@Override
	public Menu updateMenu(Menu menu) {
		// TODO Auto-generated method stub
		return menuService.updateMenu(menu);
	}

	@Override
	public List<Group> getAllGroup() {
		// TODO Auto-generated method stub

		return groupService.getAllGroups();
	}

	@Override
	public List<MenuItem> getGroupMenuItem(Group group) {
		// TODO Auto-generated method stub
		return groupService.getGroupMenuItem(group);
	}

	@Override
	public void insertGroupMenuItem(Group group, List<MenuItem> items) {
		// TODO Auto-generated method stub
		groupService.insertGroupMenuItem(group, items);
	}

	@Override
	public Group getGroup(Long id) {
		// TODO Auto-generated method stub
		return groupService.getGroup(id);
	}

	@Override
	public List<Menu> loadMenus(int first, int pageSize, String sortField,
			boolean ascending, Map<String, Object> filters) {
		// TODO Auto-generated method stub
		return menuService.loadMenus(first, pageSize, sortField, ascending,
				filters);
	}

	@Override
	public int getNumOfMenusRows(Map<String, Object> filters) {
		// TODO Auto-generated method stub
		return menuService.getNumOfMenusRows(filters);
	}

	@Override
	public List<MenuItem> loadMenuItems(int first, int pageSize,
			String sortField, boolean ascending, Map<String, Object> filters) {
		// TODO Auto-generated method stub
		return menuItemService.loadMenuItems(first, pageSize, sortField,
				ascending, filters);
	}

	@Override
	public int getNumOfMenuItemsRows(Map<String, Object> filters) {
		// TODO Auto-generated method stub
		return menuItemService.getNumOfMenuItemsRows(filters);
	}

	@Override
	public List<MenuItem> getEmployeeMenuItems(Employee employee) {
		// TODO Auto-generated method stub
		return menuItemService.getAllEmployeeMenuItems(employee);
	}

	@Override
	public Tenant insertTenant(Tenant tenant) {
		// TODO Auto-generated method stub
		return tenantService.insertTenant(tenant);
	}

	@Override
	public Tenant updateTenant(Tenant tenant) {
		// TODO Auto-generated method stub
		return tenantService.updateTenant(tenant);
	}

	@Override
	public List<Tenant> loadTenants(int first, int pageSize, String sortField,
			boolean ascending, Map<String, Object> filters) {
		// TODO Auto-generated method stub
		return tenantService.loadTenants(first, pageSize, sortField, ascending,
				filters);
	}

	@Override
	public int getNumOfTenantsRows(Map<String, Object> filters) {
		// TODO Auto-generated method stub
		return tenantService.getNumOfTenantsRows(filters);
	}

	@Override
	public Lookup getLookups(Long long1) {
		// TODO Auto-generated method stub
		return lookupService.getLookups(long1);
	}

	@Override
	public int resetPassword(String oldPssword, String newPassword, Employee emp) {
		return employeeService.resetPassword(oldPssword, newPassword, emp);

	}

	@Override
	public List<Group> getEmployeeGroups(Employee employee) {
		// TODO Auto-generated method stub
		return employeeService.getEmployeeGroups(employee);
	}

	@Override
	public void insertEmployeeGroups(Employee employee, List<Group> groups) {
		employeeService.insertEmployeeGroups(employee, groups);

	}

	@Override
	public Lookup getLookup(Long id) {
		return lookupService.getLookup(id);
	}

	@Override
	public List getTicketStatusSequences(TicketStatus ticketStatus) {
		return ticketService.getTicketStatusSequences(ticketStatus);
	}

	@Override
	public Group updateGroup(Group group) {
		// TODO Auto-generated method stub
		return groupService.updateGroup(group);
	}

	@Override
	public List<Group> loadGroups(int first, int pageSize, String sortField,
			boolean ascending, Map<String, Object> filters) {
		// TODO Auto-generated method stub
		return groupService.loadGroups(first, pageSize, sortField, ascending,
				filters);
	}

	@Override
	public int getNumOfGroupsRows(Map<String, Object> filters) {
		// TODO Auto-generated method stub		
		return groupService.getNumOfGroupsRows(filters);
	}

	@Override
	public List<Group> getAllGroups() {
		// TODO Auto-generated method stub
		return groupService.getAllGroups();
	}

	@Override
	public Group insertGroup(Group group) {
		// TODO Auto-generated method stub
		return groupService.insertGroup(group);
	}

	@Override
	public List<Employee> getEmployeesInGroup(Compound compound, Group group) {
		// TODO Auto-generated method stub

		return employeeService.getEmployeesInGroup(compound, group);
	}

	@Override
	public void deleteEmployeesFromGroup(Compound compound, Group group,
			List<Employee> employeeList) {
		// TODO Auto-generated method stub

		employeeService.deleteEmployeesFromGroup(compound, group, employeeList);

	}

	@Override
	public void addEmployeesToGroup(Compound compound, Group group,
			List<Employee> employeeList) {
		// TODO Auto-generated method stub
		employeeService.addEmployeesToGroup(compound, group, employeeList);
	}

	@Override
	public Tenant getTenant(String username, String password)
			throws SmartCompoundException {
		return tenantService.getTenant(username, password);
	}

	@Override
	public List<Facility> getAllFacilitiesRelatedToTenant(Tenant tenant)
			throws SmartCompoundException {
		return facilityService.getAllFacilitiesRelatedToTenant(tenant);
	}

	@Override
	public List<Order> getAllOrdersRelatedToTenant(Tenant tenant)
			throws SmartCompoundException {
		return orderService.getAllOrdersRelatedToTenant(tenant);
	}

	@Override
	public List<Compound> loadCompounds(int first, int pageSize,
			String sortField, boolean b, Map<String, Object> filters) {

		return compoundService.loadCompounds(first, pageSize, sortField, b,
				filters);
	}

	@Override
	public int getNumOfCompoundsRows(Map<String, Object> filters) {
		return compoundService.getNumOfCompoundsRows(filters);
	}

	@Override
	public List<Group> getCompoundGroups(Compound compound) {
		// TODO Auto-generated method stub
		return groupService.getCompoundGroups(compound);
	}
	@Transactional
	@Override
	public List<BillCalculation> startCalculatingBill(Compound compound) throws SmartCompoundException {
		// TODO Auto-generated method stub
		return billCalculationService.startCalculatingBill(compound);
	}

	@Override
	public List<Service> getTenantCompoundServices(Compound compound, Tenant tenant, Facility facility) {
		// TODO Auto-generated method stub
		return orderService.getTenantCompoundServices(compound, tenant,
				facility);
	}

	@Override
	public List<Facility> getTenantCompoundFacilities(Tenant tenant,
			Compound compound) {
		// TODO Auto-generated method stub
		return orderService.getTenantCompoundFacilities(tenant, compound);
	}

	@Override
	public Order getCompoundTenantOrder(Compound compound, Tenant tenant,
			Service service, Facility facility) {
		// TODO Auto-generated method stub
		return orderService.getCompoundTenantOrder(compound, tenant, service,
				facility);
	}

	@Override
	public BillCalculation getSpecificBillCalculation(Compound compound,
			Service service, Order order) {
		// TODO Auto-generated method stub
		return billCalculationService.getSpecificBillCalculation(compound,
				service, order);
	}

	@Override
	public List<BillCalculation> getAllBillCalculations() {
		// TODO Auto-generated method stub
		return billCalculationService.getAllBillCalculations();
	}

	@Override
	public BillCalculation updateBillCalculation(BillCalculation billCalculation) {
		// TODO Auto-generated method stub
		return billCalculationService.updateBillCalculation(billCalculation);
	}

	@Override
	public BillCalculation insertBillCalculation(BillCalculation billCalculation) {
		// TODO Auto-generated method stub
		return billCalculationService.insertBillCalculation(billCalculation);
	}

	@Override
	public BillCalculation getBillCalculation(Long id) {
		// TODO Auto-generated method stub
		return billCalculationService.getBillCalculation(id);
	}

	@Override
	public Double getAllServiceUsageRate(Compound compound, Service service,
			Double usageAmount) {
		// TODO Auto-generated method stub
		return serviceUsageRateService.getAllServiceUsageRate(compound,
				service, usageAmount);
	}

	@Override
	public List<Bill> getAllBills() {
		// TODO Auto-generated method stub
		return billService.getAllBills();
	}

	@Override
	public Bill updateBill(Bill bill) {
		// TODO Auto-generated method stub
		return billService.updateBill(bill);
	}

	@Override
	public Bill insertBill(Bill bill) {
		// TODO Auto-generated method stub
		return billService.insertBill(bill);
	}

	@Override
	public Bill getBill(Long id) {
		// TODO Auto-generated method stub
		return billService.getBill(id);
	}

	@Override
	public List<Country> getAllCountries() {
		// TODO Auto-generated method stub
		return countryService.getAllCountries();
	}

	@Override
	public List<String> getAllCountriesAsString() {
		// TODO Auto-generated method stub
		return countryService.getAllCountriesAsString();
	}

	@Override
	public Country getCountry(Long id) {
		// TODO Auto-generated method stub
		return countryService.getCountry(id);
	}

	@Override
	public Country insertCountry(Country country) {
		// TODO Auto-generated method stub
		return countryService.insertCountry(country);
	}

	@Override
	public Country updateCountry(Country country) {
		// TODO Auto-generated method stub
		return countryService.updateCountry(country);
	}

	@Override
	public List<City> getAllCities() {
		// TODO Auto-generated method stub
		return cityService.getAllCities();
	}

	@Override
	public List<City> getAllCitiesInCountry(Country country) {
		// TODO Auto-generated method stub
		return cityService.getAllCitiesInCountry(country);
	}

	@Override
	public List<String> getAllCitiesInCountryAsString(Country country) {
		// TODO Auto-generated method stub
		return cityService.getAllCitiesInCountryAsString(country);
	}

	@Override
	public List<String> getAllCitiesAsString() {
		// TODO Auto-generated method stub
		return cityService.getAllCitiesAsString();
	}

	@Override
	public City getCity(Long id) {
		// TODO Auto-generated method stub
		return cityService.getCity(id);
	}

	@Override
	public City insertCity(City city) {
		// TODO Auto-generated method stub
		return cityService.insertCity(city);
	}

	@Override
	public City updateCity(City city) {
		// TODO Auto-generated method stub
		return cityService.updateCity(city);
	}

	@Override
	public List<Nationality> getAllNationalities() {
		// TODO Auto-generated method stub
		return nationalityService.getAllNationalities();
	}

	@Override
	public List<String> getAllNationalitiesAsString() {
		// TODO Auto-generated method stub
		return nationalityService.getAllNationalitiesAsString();
	}

	@Override
	public Nationality getNationality(Long id) {
		// TODO Auto-generated method stub
		return nationalityService.getNationality(id);
	}

	@Override
	public Nationality insertNationality(Nationality nationality) {
		// TODO Auto-generated method stub
		return nationalityService.insertNationality(nationality);
	}

	@Override
	public Nationality updateNationality(Nationality nationality) {
		// TODO Auto-generated method stub
		return nationalityService.updateNationality(nationality);
	}

	@Override
	public List<Compound> getALLCompounds() {

		return compoundService.getAllCompounds();
	}

	@Override
	public List<TicketStatus> getAllTicketStatuses() {

		return ticketService.getAllTicketStatuses();
	}

	@Override
	public TicketStatus getCurrentStatus(Ticket ticket) {

		return ticketService.getCurrentStatus(ticket);
	}

	@Override
	public List<Service> getTenantServices(Compound compound, Tenant tenant) {
		// TODO Auto-generated method stub
		return orderService.getTenantServices(compound, tenant);
	}

	@Override
	public BillDetails getBillDetails(Long id) {
		// TODO Auto-generated method stub
		return billDetailsService.getBillDetails(id);
	}

	@Override
	public List<BillDetails> getAllBillDetails() {
		// TODO Auto-generated method stub
		return billDetailsService.getAllBillDetails();
	}

	@Override
	public BillDetails updateBillDetails(BillDetails billDetails) {
		// TODO Auto-generated method stub
		return billDetailsService.updateBillDetails(billDetails);
	}

	@Override
	public BillDetails insertBillDetails(BillDetails billDetails) {
		// TODO Auto-generated method stub
		return billDetailsService.insertBillDetails(billDetails);
	}

	@Override
	public List<Bill> loadBills(int first, int pageSize, String sortField,
			boolean ascending, Map<String, Object> filters) {
		return billService.loadBills(first, pageSize, sortField, ascending,
				filters);
	}

	@Override
	public int getNumOfBillsRows(Map<String, Object> filters) {
		return billService.getNumOfBillsRows(filters);
	}

	@Override
	public Tenant getTenantByEmail(String email) throws SmartCompoundException {
		return tenantService.getTenantByEmail(email);
	}

	@Override
	public TicketHistory insertTicketHistory(TicketHistory ticketHistory) {
		return ticketHistoryService.insertTicketHistory(ticketHistory);
	}

	@Override
	public Compound getCompound(Long id) {
		// TODO Auto-generated method stub
		return compoundService.getCompound(id);
	}

	@Override
	public List<TicketHistory> loadTicketHistories(int first, int pageSize,
			String sortField, boolean ascending, Map<String, Object> filters) {
		return ticketHistoryService.loadTicketHistorys(first, pageSize,
				sortField, ascending, filters);
	}

	@Override
	public int getNumOfTicketHistoriesRows(Map<String, Object> filters) {
		return ticketHistoryService.getNumOfTicketHistorysRows(filters);
	}

	@Override
	public Ticket updateTicket(Ticket selectedTicket, Employee employee) {
		return ticketService.updateTicket(selectedTicket, employee);
	}

	@Transactional
	@Override
	public void endBillingCycle(Compound compound,Employee employee)
			throws SmartCompoundException {
		// TODO Auto-generated method stub
		billCalculationService.endBillingCycle(compound,employee);

	}

	@Override
	public Order updateOrder(Order order) {

		return orderService.updateOrder(order);
	}

	@Override
	public List<Employee> getAllEmployeesInCompound(Compound compound) {
		// TODO Auto-generated method stub
		return employeeService.getAllEmployeesInCompound(compound);
	}

	@Override
	public List<TicketImage> getAllTicketImagesByTicket(Ticket ticket)
			throws SmartCompoundException {
		return ticketImageService.getAllTicketImagesByTicket(ticket);
	}

	@Override
	public TicketImage getTicketImage(Long id) throws SmartCompoundException {
		return ticketImageService.getTicketImage(id);
	}

	@Override
	public TicketImage insertTicketImage(TicketImage ticketImage)
			throws SmartCompoundException {
		return ticketImageService.insertTicketImage(ticketImage);
	}

	@Override
	public List<Group> getAllGroupByCompound(Compound selectedCompound) {
		return groupService.getAllGroupsByCompound(selectedCompound);
	}

	@Override
	public List<Service> getTenantServicesRelatedToFacilty(
			Compound currentCompound, Tenant relatedTenant, Facility facility) {
		return orderService.getTenantServicesRelatedToFacilty(currentCompound,relatedTenant,facility);
	}

	@Override
	public Tenant getTenantByUsername(String username) {
		// TODO Auto-generated method stub
		return tenantService.getTenantByUsername(username);
	}

	@Override
	public List<MenuItem> getAllMenuItemsAfterCheckSuperAdminFlag(
			boolean isSuperAdmin) {
		// TODO Auto-generated method stub
		return menuItemService
				.getAllMenuItemsAfterCheckSuperAdminFlag(isSuperAdmin);
	}

	@Override
	public List<Facility> getCompoundFacilitesByTenant(Compound compound,
			Tenant tenant) throws SmartCompoundException {
		return facilityService.getCompoundFacilites(compound, tenant);
	}

	@Override
	public List<Service> getAllActiveServices(Compound currentCompound) {
		// TODO Auto-generated method stub
		return serviceService.getAllActiveServices(currentCompound);
	}

	@Override
	public Employee getEmployeeByUsername(String username) {
		// TODO Auto-generated method stub
		return employeeService.findEmployeeByUsername(username);
	}

	@Override
	public List<Ticket> getTicketsOfTenant(Tenant tenant) {
		// TODO Auto-generated method stub
		return ticketService.getTicketsOfTenant(tenant);
	}

	@Override
	public List<Order> getOrdersOfTenant(Tenant tenant) {
		// TODO Auto-generated method stub
		return orderService.getOrdersOfTenant(tenant);
	}

	@Override
	public List<Bill> getBillsOfTenant(Tenant tenant) {
		// TODO Auto-generated method stub
		return billService.getBillsOfTenant(tenant);
	}

	@Override
	public List<TicketHistory> loadTicketHistories(Ticket ticket) {
		return ticketHistoryService.loadTicketHistories( ticket);
	}

	@Override
	public List<BillDetails> loadBillDetails(int first, int pageSize,
			String sortField, boolean ascending, Map<String, Object> filters) {
		// TODO Auto-generated method stub
		return billDetailsService.loadBillDetails(first, pageSize, sortField, ascending, filters);
	}

	@Override
	public int getNumOfBillDetailsRows(Map<String, Object> filters) {
		// TODO Auto-generated method stub
		return billDetailsService.getNumOfBillDetailsRows(filters);
	}

	@Override
	public Menu getMenuByMenuName(String menuName, Long id) {
		// TODO Auto-generated method stub
		return menuService.getMenuByMenuName(menuName, id);
	}

	@Override
	public MenuItem getMenuItemByName(String name, Long id) {
		// TODO Auto-generated method stub
		return menuItemService.getMenuItemByName(name, id);
	}

	@Override
	public List<Tenant> getDistinctTenantsFromBillCalculation(Compound compound, String searchParam) {
		// TODO Auto-generated method stub
		return billCalculationService.getDistinctTenantsFromBillCalculation(compound,searchParam);
	}
	
	@Transactional
	@Override
	public void updateOrderAndBillCalculation(BillCalculation billCalculation,Order order){
		
		billCalculationService.updateOrderAndBillCalculation(billCalculation, order);
	}
	
	@Override
	public BillCycleStatus  getCompoundBillCycleStatus(Compound compound){
		
		return billCalculationService.getCompoundBillCycleStatus(compound);
	}
}
