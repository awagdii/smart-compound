package com.ntgclarity.smartcompound.dataaccess.dao;

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

public interface TicketImageDAO {

	List<TicketImage> getAllTicketImagesByTicket(Ticket ticket)throws SmartCompoundException;

	TicketImage getTicketImage(Long id)throws SmartCompoundException;

	TicketImage insertTicketImage(TicketImage ticketImage) throws SmartCompoundException ;
}
