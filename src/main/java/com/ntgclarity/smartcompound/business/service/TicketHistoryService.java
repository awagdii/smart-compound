package com.ntgclarity.smartcompound.business.service;

import java.util.List;
import java.util.Map;

import com.ntgclarity.smartcompound.common.entity.Ticket;
import com.ntgclarity.smartcompound.common.entity.TicketHistory;

/**Author: Nazer**/

public interface TicketHistoryService {

	List<TicketHistory> getAllTicketHistorys();

	TicketHistory getTicketHistory(Long id);

	TicketHistory insertTicketHistory(TicketHistory ticketHistory);

	TicketHistory updateTicketHistory(TicketHistory ticketHistory);
	List<TicketHistory> loadTicketHistorys(int first, int pageSize, String sortField,
			boolean ascending, Map<String, Object> filters);

	int getNumOfTicketHistorysRows(Map<String, Object> filters);

	List<TicketHistory> loadTicketHistories(Ticket ticket);

}
