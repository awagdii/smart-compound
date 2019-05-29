package com.ntgclarity.smartcompound.business.service;

import java.util.List;
import java.util.Map;

import com.ntgclarity.smartcompound.common.entity.Ticket;
import com.ntgclarity.smartcompound.common.entity.TicketImage;
import com.ntgclarity.smartcompound.common.entity.TicketStatus;
import com.ntgclarity.smartcompound.common.exception.SmartCompoundException;

/**
*
* @author Karim M.Fadel
*/

public interface TicketImageService {

	public List<TicketImage> getAllTicketImagesByTicket(Ticket ticket)throws SmartCompoundException;

	public TicketImage getTicketImage(Long id)throws SmartCompoundException;

	public TicketImage insertTicketImage(TicketImage ticketImage) throws SmartCompoundException ;
}
