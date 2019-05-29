package com.ntgclarity.smartcompound.dataaccess.daoimpl;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.ntgclarity.smartcompound.common.entity.Ticket;
import com.ntgclarity.smartcompound.common.entity.TicketImage;
import com.ntgclarity.smartcompound.common.entity.TicketStatus;
import com.ntgclarity.smartcompound.common.exception.SmartCompoundException;
import com.ntgclarity.smartcompound.dataaccess.base.BaseDAO;
import com.ntgclarity.smartcompound.dataaccess.dao.TicketDAO;
import com.ntgclarity.smartcompound.dataaccess.dao.TicketImageDAO;

/**
*
* @author Karim M.Fadel
*/

@Repository
public class TicketImageDAOImpl extends BaseDAO implements TicketImageDAO {

	Logger logger = Logger.getLogger(TicketImageDAOImpl.class);

	@Override
	public List<TicketImage> getAllTicketImagesByTicket(Ticket ticket) throws SmartCompoundException{
		String queryString = "from TicketImage xx where xx.ticket = :ticket";
		Query query = getCurrentSession().createQuery(queryString);
		query.setParameter("ticket", ticket);
		List<TicketImage> result = (List<TicketImage>) query.list();
		return result;
	}

	@Override
	public TicketImage getTicketImage(Long id) throws SmartCompoundException{
		return (TicketImage) super.get(TicketImage.class, id);
	}

	@Override
	public TicketImage insertTicketImage(TicketImage ticketImage) throws SmartCompoundException {
		TicketImage resultTicketImage = (TicketImage) super.saveOrUpdate(ticketImage);
		if(resultTicketImage==null)
			throw new SmartCompoundException();
		return resultTicketImage;
	}

}
