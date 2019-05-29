package com.ntgclarity.smartcompound.common.dto;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

/**
*
* @author Karim M.Fadel
*/
@XmlRootElement
public class SmartCompoundResponse {

	private String code;
	
	private String message;
	
	private Object result;

	private SmartCompoundResponse(String code, String message, Object result) {
		super();
		this.code = code;
		this.message = message;
		this.result = result;
	}
	
	private SmartCompoundResponse() {
		super();
	}
	
	
	public static SmartCompoundResponse prepareSuccessResponce(String msg,Object obj){
		return new SmartCompoundResponse("0000",msg,obj);
	}
	
	public static SmartCompoundResponse prepareFailureResponce(String msg){
		return new SmartCompoundResponse("-1111",msg,null);
	}

	@XmlElement
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@XmlElement
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@XmlElement
	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}
	
}
