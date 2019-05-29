package com.ntgclarity.smartcompound.common.exception;

/**
*
* @author Mai and karim 
*/
public class SmartCompoundException extends Exception{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String messageKey = null;
	
	
	public SmartCompoundException(){}
	

	public SmartCompoundException(String messageKey) {
		super();
		this.messageKey = messageKey;
	}

	public String getMessageKey() {
		return messageKey;
	}

	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}

	

}