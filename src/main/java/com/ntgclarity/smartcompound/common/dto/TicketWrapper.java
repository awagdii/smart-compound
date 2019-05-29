package com.ntgclarity.smartcompound.common.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.ntgclarity.smartcompound.common.utils.Utils;
import com.ntgclarity.smartcompound.common.entity.*;

@XmlRootElement
public class TicketWrapper {
	private String ticketSubject;
	@NotNull
	private Long problemDate;
	private String description;
	private Long facilityId;
	@NotNull
	private Long serviceId;
	@NotNull
	private Long tenantId;

	@XmlElement
	public String getTicketSubject() {
		return ticketSubject;
	}

	public void setTicketSubject(String ticketSubject) {
		this.ticketSubject = ticketSubject;
	}

	@XmlElement
	public Long getProblemDate() {
		return problemDate;
	}

	public void setProblemDate(Long problemDate) {
		this.problemDate = problemDate;
	}

	@XmlElement
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@XmlElement
	public Long getFacilityId() {
		return facilityId;
	}

	public void setFacilityId(Long facilityId) {
		this.facilityId = facilityId;
	}

	@XmlElement
	public Long getServiceId() {
		return serviceId;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	@XmlElement
	public Long getTenantId() {
		return tenantId;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}

	public static Ticket unWrappedTenantObject(TicketWrapper ticketWrapper) {
		Ticket ticket = new Ticket();

		Tenant tenant = new Tenant();
		tenant.setId(ticketWrapper.getTenantId());
		ticket.setRelatedTenant(tenant);
		if (ticketWrapper.getFacilityId() != null) {
			Facility facility = new Facility();
			facility.setId(ticketWrapper.getFacilityId());
			facility.setTenant(tenant);
			ticket.setFacility(facility);
		}
		if (ticketWrapper.getServiceId() != null) {
			Service service = new Service();
			service.setId(ticketWrapper.getServiceId());
			ticket.setService(service);
		}

		ticket.setDescription(ticketWrapper.getDescription());
		// ticket.setLastStatus();
		ticket.setTicketSubject(ticketWrapper.getTicketSubject());
		if(ticketWrapper.getProblemDate()!=null)
			ticket.setProblemDate(new Date(ticketWrapper.getProblemDate()));
		ticket.setOpenDate(Utils.getStartOfDay(new Date()));

		return ticket;
	}

}
