package com.ntgclarity.smartcompound.common.dto;

import java.rmi.server.UID;

import javax.xml.bind.annotation.XmlRootElement;

/**
*
* @author Karim M.Fadel
*/
@XmlRootElement
public class Token {

	private String value;
	private Long compoundId;
	
	public static Token generateToken(){
		return new Token((new UID()).toString());
	}
	
	public Token() {
		super();
	}

	public Token(String value) {
		super();
		this.value = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Token other = (Token) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Long getCompoundId() {
		return compoundId;
	}

	public void setCompoundId(Long compoundId) {
		this.compoundId = compoundId;
	}
	

}
