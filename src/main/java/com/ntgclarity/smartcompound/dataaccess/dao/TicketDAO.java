package com.ntgclarity.smartcompound.dataaccess.dao;

import java.util.List;
import java.util.Map;

import com.ntgclarity.smartcompound.common.entity.Tenant;
import com.ntgclarity.smartcompound.common.entity.Ticket;
import com.ntgclarity.smartcompound.common.entity.TicketStatus;

/** Author: Heba **/

public interface TicketDAO {

	List<Ticket> getAllTickets();
	List <TicketStatus> getAllTicketStatuses();

	Ticket getTicket(Long id);

	Ticket insertTicket(Ticket ticket);

	Ticket updateTicket(Ticket ticket);

	List<Ticket> loadTickets(int first, int pageSize, String sortField,
			boolean ascending, Map<String, Object> filters);

	int getNumOfTicketsRows(Map<String, Object> filters);

	public List getTicketStatusSequences(TicketStatus ticketStatus);

	TicketStatus getTicketStatus(Long id);
	TicketStatus getCurrentStatus(Ticket ticket);
	
	List <Ticket> getTicketsOfTenant(Tenant tenant);

}
