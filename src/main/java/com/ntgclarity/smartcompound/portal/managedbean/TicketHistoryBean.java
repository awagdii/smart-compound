package com.ntgclarity.smartcompound.portal.managedbean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Spliterator;
import java.util.function.Consumer;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.ntgclarity.smartcompound.business.management.SmartCompoundManagment;
import com.ntgclarity.smartcompound.common.entity.Compound;
import com.ntgclarity.smartcompound.common.entity.Ticket;
import com.ntgclarity.smartcompound.common.entity.TicketHistory;
import com.ntgclarity.smartcompound.portal.base.BaseBean;
@ManagedBean
@ViewScoped
public class TicketHistoryBean extends BaseBean implements Serializable {

	@ManagedProperty(value = "#{smartCompoundManagmentImpl}")
	private SmartCompoundManagment smartCompoundManagment;
	private LazyDataModel<TicketHistory> lazyTicketHistoryModel;
	private TicketHistory selectedTicketHistory;
	
	private static final long serialVersionUID = 1L;

	@PostConstruct
	public void init() {
		initiateNewTicketHistory();
	}

	private void initiateNewTicketHistory() {
		selectedTicketHistory=new TicketHistory();
		
	}
	public  void LoadTicketHistoryData(final Ticket selectedTicket) {
		lazyTicketHistoryModel = new LazyDataModel<TicketHistory>() {
			/**
			 * 
			 */
//			Ticket ticket=selectedTicket;
			private static final long serialVersionUID = 1L;
			private List<TicketHistory> result;
			@Override
			public TicketHistory getRowData(String rowKey) {
				for (TicketHistory ticketHistory : result) {
					if (ticketHistory.getId().toString().equals(rowKey))
						return ticketHistory;
				}

				return null;
			}
			@Override
			public Object getRowKey(TicketHistory ticketHistory) {
				return ticketHistory.getId();
			}
			@Override
			public List<TicketHistory> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {
				filters.put("ticket.id", selectedTicket.getId());
				result = getSmartCompoundManagment().loadTicketHistories(first,
						pageSize, sortField, sortOrder == SortOrder.ASCENDING,
						filters);
				this.setRowCount(getSmartCompoundManagment()
						.getNumOfTicketHistoriesRows(filters));

				return result;
			}
			@Override
			public void forEach(Consumer<? super TicketHistory> action) {
				// TODO Auto-generated method stub

			}

			@Override
			public Spliterator<TicketHistory> spliterator() {
				// TODO Auto-generated method stub
				return null;
			}

		};
		}

	public SmartCompoundManagment getSmartCompoundManagment() {
		return smartCompoundManagment;
	}

	public void setSmartCompoundManagment(
			SmartCompoundManagment smartCompoundManagment) {
		this.smartCompoundManagment = smartCompoundManagment;
	}



	public LazyDataModel<TicketHistory> getLazyTicketHistoryModel() {
		return lazyTicketHistoryModel;
	}

	public void setLazyTicketHistoryModel(
			LazyDataModel<TicketHistory> lazyTicketHistoryModel) {
		this.lazyTicketHistoryModel = lazyTicketHistoryModel;
	}

	public TicketHistory getSelectedTicketHistory() {
		return selectedTicketHistory;
	}

	public void setSelectedTicketHistory(TicketHistory selectedTicketHistory) {
		this.selectedTicketHistory = selectedTicketHistory;
	}

	
	

}
