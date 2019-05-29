package com.ntgclarity.smartcompound.business.service;

import java.util.List;
import java.util.Map;

import com.ntgclarity.smartcompound.common.entity.Employee;
import com.ntgclarity.smartcompound.common.entity.Tenant;
import com.ntgclarity.smartcompound.common.entity.Ticket;
import com.ntgclarity.smartcompound.common.entity.TicketStatus;

public interface TicketService {

	List<Ticket> getAllTickets();
	List<TicketStatus> getAllTicketStatuses();
	Ticket getTicket(Long id);

	Ticket insertTicket(Ticket ticket);

	Ticket updateTicket(Ticket selectedTicket,Employee employee);
	TicketStatus getTicketStatusService(Long id);
	List<Ticket> loadTickets(int first, int pageSize, String sortField,
			boolean ascending, Map<String, Object> filters);

	int getNumOfTicketsRows(Map<String, Object> filters);

	public List getTicketStatusSequences(TicketStatus ticketStatus);
	TicketStatus getCurrentStatus(Ticket ticket);
	List <Ticket> getTicketsOfTenant(Tenant tenant);

}