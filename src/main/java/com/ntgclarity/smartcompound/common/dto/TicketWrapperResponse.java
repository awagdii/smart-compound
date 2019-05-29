package com.ntgclarity.smartcompound.common.dto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.ntgclarity.smartcompound.common.entity.*;

@XmlRootElement
public class TicketWrapperResponse {


	private Long id;
	private String ticketSubject;
	private Long problemDate;
	private String description;
	private Long openDate;
	private String lastStatus;
	private List<TicketImageWrapper> images;   

	@XmlElement
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@XmlElement
	public Long getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Long openDate) {
		this.openDate = openDate;
	}

	@XmlElement
	public String getLastStatus() {
		return lastStatus;
	}

	public void setLastStatus(String lastStatus) {
		this.lastStatus = lastStatus;
	}

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

	public List<TicketImageWrapper> getImages() {
		return images;
	}

	public void setImages(List<TicketImageWrapper> images) {
		this.images = images;
	}

	public static List<TicketWrapperResponse> wrappedTicketsResponseObject(List<Ticket> tickets){
		List<TicketWrapperResponse> tickerWrappers = new ArrayList<>();
		for (Iterator<Ticket> iterator = tickets.iterator(); iterator.hasNext();) {
			tickerWrappers.add(unWrappedTenantResponseObject((Ticket) iterator.next()));
		}
		return tickerWrappers;
	}
	
	public static TicketWrapperResponse unWrappedTenantResponseObject(Ticket ticket) {
		TicketWrapperResponse sentTickerWrapper = new TicketWrapperResponse();

		sentTickerWrapper.setId(ticket.getId());
		sentTickerWrapper.setDescription(ticket.getDescription());
		sentTickerWrapper.setLastStatus(ticket.getLastStatus());
		sentTickerWrapper.setTicketSubject(ticket.getTicketSubject());
		if(ticket.getProblemDate()!=null)
			sentTickerWrapper.setProblemDate(ticket.getProblemDate().getTime());
		if(ticket.getOpenDate()!=null)
			sentTickerWrapper.setOpenDate(ticket.getOpenDate().getTime());
		if(ticket.getImages()!=null)
			sentTickerWrapper.setImages(TicketImageWrapper.wrappedTicketsResponseObject(ticket.getImages()));
		return sentTickerWrapper;
	}

}
