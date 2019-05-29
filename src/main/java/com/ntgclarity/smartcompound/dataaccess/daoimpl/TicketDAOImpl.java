package com.ntgclarity.smartcompound.dataaccess.daoimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.ntgclarity.smartcompound.common.entity.Tenant;
import com.ntgclarity.smartcompound.common.entity.Ticket;
import com.ntgclarity.smartcompound.common.entity.TicketStatus;
import com.ntgclarity.smartcompound.dataaccess.base.BaseDAO;
import com.ntgclarity.smartcompound.dataaccess.dao.TicketDAO;

/** Author: Heba **/

@Repository
public class TicketDAOImpl extends BaseDAO implements TicketDAO {
	Query query;
	String queryString;
	Logger logger = Logger.getLogger(TicketDAOImpl.class);

	private static Map<Long, TicketStatus> ticketStatusMap = new HashMap();

	@Override
	public List<Ticket> getAllTickets() {

		return (List<Ticket>) super.getAll(Ticket.class);
	}

	@Override
	public Ticket getTicket(Long id) {
		return (Ticket) super.get(Ticket.class, id);
	}

	@Override
	public Ticket updateTicket(Ticket ticket) {
		// TODO Auto-generated method stub
		return (Ticket) super.saveOrUpdate(ticket);
	}

	@Override
	public List getTicketStatusSequences(TicketStatus ticketStatus) {

		queryString = "select s.toTicketStatus from TicketStatusSequences s where s.fromTicketStatus = :x order by s.toTicketStatus.id";
		query = getCurrentSession().createQuery(queryString);
		query.setParameter("x", ticketStatus);
		List<Object> result = query.list();
		return result;
	}

	/**
	 * methodCreater:nessma create Ticket this metohd will call saveOrUpdate()
	 * in the super class BaseDAO
	 **/
	@Override
	public Ticket insertTicket(Ticket ticket) {
		// logger.info("service name is "+ticket.getService().getServiceName());
		// System.out.println("service name is "+ticket.getService().getServiceName());
		// ticket.setServiceName(ticket.getService().getServiceName());
		return (Ticket) super.saveOrUpdate(ticket);

	}

	@Override
	public List<Ticket> loadTickets(int first, int pageSize, String sortField,
			boolean ascending, Map<String, Object> filters) {

		return super.load(Ticket.class, first, pageSize, sortField, ascending,
				filters);
	}

	@Override
	public int getNumOfTicketsRows(Map<String, Object> filters) {

		return super.getNumOfRows(Ticket.class, filters);
	}

	@Override
	public TicketStatus getTicketStatus(Long id) {
		if (ticketStatusMap.size()==0) { 
			List<TicketStatus> allTicketStatuses = getAllTicketStatuses();
			for (TicketStatus ticketStatus : allTicketStatuses) {
				ticketStatusMap.put(ticketStatus.getId(), ticketStatus);
			}
		}
		return ticketStatusMap.get(id);

	}

	@Override
	public List<TicketStatus> getAllTicketStatuses() {

		return (List<TicketStatus>) super.getAll(TicketStatus.class);
	}

	@Override
	public TicketStatus getCurrentStatus(Ticket ticket) {

		queryString = "select s from TicketStatus t where s = :x";
		query = getCurrentSession().createQuery(queryString);
		query.setParameter("x", ticket.getTicketStatus());
		TicketStatus result = (TicketStatus) query.uniqueResult();
		return result;
	}

	@Override
	public List<Ticket> getTicketsOfTenant(Tenant tenant) {
		Query query = getCurrentSession().createQuery(
				"from " + Ticket.class.getCanonicalName()
						+ " x where x.relatedTenant =:relatedTenant ");
		query.setParameter("relatedTenant", tenant);
		return query.list();
	}

}
