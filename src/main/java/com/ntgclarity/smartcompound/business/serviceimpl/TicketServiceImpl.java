package com.ntgclarity.smartcompound.business.serviceimpl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntgclarity.smartcompound.business.service.TicketService;
import com.ntgclarity.smartcompound.common.entity.Employee;
import com.ntgclarity.smartcompound.common.entity.Tenant;
import com.ntgclarity.smartcompound.common.entity.Ticket;
import com.ntgclarity.smartcompound.common.entity.TicketHistory;
import com.ntgclarity.smartcompound.common.entity.TicketStatus;
import com.ntgclarity.smartcompound.dataaccess.dao.LookupDAO;
import com.ntgclarity.smartcompound.dataaccess.dao.ServiceDAO;
import com.ntgclarity.smartcompound.dataaccess.dao.TicketDAO;
import com.ntgclarity.smartcompound.dataaccess.dao.TicketHistoryDAO;



/**Author: Heba**/

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	private TicketDAO ticketDAO;
	@Autowired
	private TicketHistoryDAO ticketHistoryDao;
	@Autowired
	private ServiceDAO serviceDAO;
	
	@Autowired
	private LookupDAO lookupDAO;
	
	@Override
	public List<Ticket> getAllTickets() {
		return ticketDAO.getAllTickets();
	}

	@Override
	public Ticket getTicket(Long id) {
		if(id !=null)
		{
			return ticketDAO.getTicket(id);
		}
		return null;
	}
	/**
	 *methodCreater:nessma 
	 *create Ticket 
	 *this metohd will call TicketDAO.insertTicket
	 
	  **/
	@Override
	public Ticket insertTicket(Ticket selectedTicket) {
		com.ntgclarity.smartcompound.common.entity.Service service = new com.ntgclarity.smartcompound.common.entity.Service();
		if(selectedTicket.getService()!=null){
			service = serviceDAO.getService(selectedTicket.getService().getId());
			selectedTicket.setService(service);
			selectedTicket.setServiceName(service.getServiceName());
		}
		
		if(selectedTicket.getTicketStatus()==null)
		{
			TicketStatus ticketStatus = ticketDAO.getTicketStatus(TicketStatus.NEW.getId());
			selectedTicket.setTicketStatus(ticketStatus);
			selectedTicket.setLastStatus(ticketStatus.getName());

		}
		selectedTicket.setProblemDate(new Date());
//		System.out.println("the problem date is "+selectedTicket.getProblemDate());

		return ticketDAO.insertTicket(selectedTicket);
	}
	@Override
	public Ticket updateTicket(Ticket selectedTicket,Employee employee) {
		Ticket ticket=ticketDAO.getTicket(selectedTicket.getId());
		if(!(selectedTicket.getTicketStatus().equals(ticket.getTicketStatus()))){
			TicketHistory ticketHistory=new TicketHistory();
			ticketHistory.setActionDate(new Date());
			ticketHistory.setOpenedBy(selectedTicket.getOpenedBy());
			ticketHistory.setPreviousStatus(ticket.getTicketStatus().getName());
			ticketHistory.setCurrentStatus(selectedTicket.getTicketStatus().getName());
			ticketHistory.setComment(selectedTicket.getDescription());
			ticketHistory.setTicket(selectedTicket);
			ticketHistory.setActionBy(employee);
			ticketHistoryDao.insertTicketHistory(ticketHistory);
			
		}
		return ticketDAO.updateTicket(selectedTicket);
		
	}

	@Override
	public List<Ticket> loadTickets(int first, int pageSize,
			String sortField, boolean ascending, Map<String, Object> filters) {
		return ticketDAO.loadTickets(first,pageSize,sortField,ascending,filters);
	}

	@Override
	public int getNumOfTicketsRows(Map<String, Object> filters) {
	
		return  ticketDAO.getNumOfTicketsRows(filters);
	}

	@Override
	public List getTicketStatusSequences(TicketStatus ticketStatus) {
		return ticketDAO.getTicketStatusSequences(ticketStatus);
	}

	@Override
	public TicketStatus getTicketStatusService(Long id) {
		
		return ticketDAO.getTicketStatus(id);
	}

	@Override
	public List<TicketStatus> getAllTicketStatuses() {
		// TODO Auto-generated method stub
		return ticketDAO.getAllTicketStatuses();
	}

	@Override
	public TicketStatus getCurrentStatus(Ticket ticket) {
		// TODO Auto-generated method stub
		return ticketDAO.getCurrentStatus(ticket);
	}

	@Override
	public List<Ticket> getTicketsOfTenant(Tenant tenant) {
		// TODO Auto-generated method stub
		return ticketDAO.getTicketsOfTenant(tenant);
	}


}
