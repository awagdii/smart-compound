package com.ntgclarity.smartcompound.business.serviceimpl;

import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntgclarity.smartcompound.business.service.TicketHistoryService;
import com.ntgclarity.smartcompound.common.entity.Ticket;
import com.ntgclarity.smartcompound.common.entity.TicketHistory;
import com.ntgclarity.smartcompound.dataaccess.dao.TicketHistoryDAO;

/**Author: Nazer**/

@Service
public class TicketHistoryServiceImpl implements TicketHistoryService {

	@Autowired
	private TicketHistoryDAO ticketHistoryDAO;
	
	@Override
	public List<TicketHistory> getAllTicketHistorys() {
		return ticketHistoryDAO.getAllTicketHistorys();
	}

	@Override
	public TicketHistory getTicketHistory(Long id) {
		if(id !=null)
		{
			return ticketHistoryDAO.getTicketHistory(id);
		}
		return null;
	}
	/**
	 *methodCreater:nessma 
	 *create TicketHistory 
	 *this metohd will call ticketHistoryDAO.insertTicketHistory
	 
	  **/
	@Override
	public TicketHistory insertTicketHistory(TicketHistory ticketHistory) {
		
		return ticketHistoryDAO.insertTicketHistory(ticketHistory);
	}
	@Override
	public TicketHistory updateTicketHistory(TicketHistory ticketHistory) {
		// TODO Auto-generated method stub
		return ticketHistoryDAO.updateTicketHistory(ticketHistory);
		
	}

	@Override
	public List<TicketHistory> loadTicketHistorys(int first, int pageSize,
			String sortField, boolean ascending, Map<String, Object> filters) {
		return ticketHistoryDAO.loadTicketHistorys(first,pageSize,sortField,ascending,filters);
	}

	@Override
	public int getNumOfTicketHistorysRows(Map<String, Object> filters) {
	
		return  ticketHistoryDAO.getNumOfTicketHistorysRows(filters);
	}

	@Override
	public List<TicketHistory> loadTicketHistories(Ticket ticket) {
		return ticketHistoryDAO.loadTicketHistories( ticket);
	}


}
