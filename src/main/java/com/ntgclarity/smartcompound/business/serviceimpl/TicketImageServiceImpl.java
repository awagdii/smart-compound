package com.ntgclarity.smartcompound.business.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntgclarity.smartcompound.business.service.TicketImageService;
import com.ntgclarity.smartcompound.common.entity.Ticket;
import com.ntgclarity.smartcompound.common.entity.TicketImage;
import com.ntgclarity.smartcompound.common.entity.TicketStatus;
import com.ntgclarity.smartcompound.common.exception.SmartCompoundException;
import com.ntgclarity.smartcompound.dataaccess.dao.LookupDAO;
import com.ntgclarity.smartcompound.dataaccess.dao.TicketImageDAO;

/**
*
* @author Karim M.Fadel
*/
@Service
public class TicketImageServiceImpl implements TicketImageService {
	
	@Autowired
	private TicketImageDAO ticketImageDAO;

	@Override
	public List<TicketImage> getAllTicketImagesByTicket(Ticket ticket)throws SmartCompoundException{
		return ticketImageDAO.getAllTicketImagesByTicket(ticket);
	}

	@Override
	public TicketImage getTicketImage(Long id)throws SmartCompoundException{
		return ticketImageDAO.getTicketImage(id);
	}

	@Override
	public TicketImage insertTicketImage(TicketImage ticketImage) throws SmartCompoundException {
		return ticketImageDAO.insertTicketImage(ticketImage);
	}
}
