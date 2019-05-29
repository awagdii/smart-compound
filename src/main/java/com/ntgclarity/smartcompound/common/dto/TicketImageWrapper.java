package com.ntgclarity.smartcompound.common.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.ntgclarity.smartcompound.common.entity.TicketImage;
/**
*
* @author Karim M.Fadel
*/

public class TicketImageWrapper implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6344103690626600369L;
	
	private String imageUrl;

	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}	

	public static List<TicketImageWrapper> wrappedTicketsResponseObject(List<TicketImage> ticketImages){
		List<TicketImageWrapper> ticketImageWrappers = new ArrayList<>();
		for (Iterator<TicketImage> iterator = ticketImages.iterator(); iterator.hasNext();) {
			ticketImageWrappers.add(unWrappedTenantResponseObject((TicketImage) iterator.next()));
		}
		return ticketImageWrappers;
	}
	
	public static TicketImageWrapper unWrappedTenantResponseObject(TicketImage ticketImage) {
		TicketImageWrapper sentTickerWrapper = new TicketImageWrapper();
		sentTickerWrapper.setImageUrl(ticketImage.getImageUrl());
		return sentTickerWrapper;
	}
}
