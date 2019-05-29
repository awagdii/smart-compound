
package com.ntgclarity.smartcompound.portal.exceptionhandling;

import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.context.RequestContext;

import com.ntgclarity.smartcompound.common.exception.SmartCompoundException;
import com.ntgclarity.smartcompound.portal.base.BaseBean;

import java.util.Iterator;

/**
 * 
 * @author Mai
 */
public class CustomExceptionHandler extends ExceptionHandlerWrapper {

	static final Logger logger = LogManager
			.getLogger(CustomExceptionHandler.class.getName());

	private ExceptionHandler exceptionHandler;
	private BaseBean baseBean = new BaseBean();

	public CustomExceptionHandler(ExceptionHandler exceptionHandler) {
		this.exceptionHandler = exceptionHandler;
	}

	@Override
	public ExceptionHandler getWrapped() {
		return exceptionHandler;
	}

	@Override
	public void handle() throws FacesException {
		for (Iterator<ExceptionQueuedEvent> i = getUnhandledExceptionQueuedEvents()
				.iterator(); i.hasNext();) {
			ExceptionQueuedEvent event = i.next();
			ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event
					.getSource();
			Throwable t = getRootCause(context.getException());
			//logger.error(context.getException());
			//t.printStackTrace();
//			context.getException().printStackTrace();

			try {
				if (t instanceof SmartCompoundException) {
					baseBean.addErrorMessage(((SmartCompoundException) t)
							.getMessageKey());
					RequestContext.getCurrentInstance().addCallbackParam(
							"success", false);
				}
			} finally {
				i.remove();

			}

		}

	}
}