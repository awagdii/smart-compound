package com.ntgclarity.smartcompound.common.dto;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.ntgclarity.smartcompound.common.entity.Compound;
/**
*
* @author Karim M.Fadel
*/
@XmlRootElement
public class CompoundWrapper {
	@NotNull
	private Long id;
	private String compoundName;
	private String compoundImage;
	private String firstEmail;
	private String secondEmail;
	private String thirdEmail;
	private String firstPhoneNumber;
	private String secondPhoneNumber;
	private String thirdPhoneNumber;
	
	@XmlElement
	public String getCompoundImage() {
		return compoundImage;
	}

	public void setCompoundImage(String compoundImage) {
		this.compoundImage = compoundImage;
	}
	
	@XmlElement
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;

	}

	@XmlElement
	public String getCompoundName() {
		return compoundName;
	}

	public void setCompoundName(String compoundName) {
		this.compoundName = compoundName;
	}

	@XmlElement
	public String getFirstEmail() {
		return firstEmail;
	}

	public void setFirstEmail(String firstEmail) {
		this.firstEmail = firstEmail;
	}

	@XmlElement
	public String getSecondEmail() {
		return secondEmail;
	}

	public void setSecondEmail(String secondEmail) {
		this.secondEmail = secondEmail;
	}

	@XmlElement
	public String getThirdEmail() {
		return thirdEmail;
	}

	public void setThirdEmail(String thirdEmail) {
		this.thirdEmail = thirdEmail;
	}

	@XmlElement
	public String getFirstPhoneNumber() {
		return firstPhoneNumber;
	}

	public void setFirstPhoneNumber(String firstPhoneNumber) {
		this.firstPhoneNumber = firstPhoneNumber;
	}

	@XmlElement
	public String getSecondPhoneNumber() {
		return secondPhoneNumber;
	}

	public void setSecondPhoneNumber(String secondPhoneNumber) {
		this.secondPhoneNumber = secondPhoneNumber;
	}

	@XmlElement
	public String getThirdPhoneNumber() {
		return thirdPhoneNumber;
	}

	public void setThirdPhoneNumber(String thirdPhoneNumber) {
		this.thirdPhoneNumber = thirdPhoneNumber;
	}
	
	public static CompoundWrapper wrappedCompoundObject(Compound compound) {
		CompoundWrapper compoundWrapper = new CompoundWrapper();
		compoundWrapper.setCompoundImage(compound.getCompoundImage());
		compoundWrapper.setCompoundName(compound.getCompoundName());
		compoundWrapper.setFirstEmail(compound.getFirstEmail());
		compoundWrapper.setFirstPhoneNumber(compound.getFirstPhoneNumber());
		compoundWrapper.setId(compound.getId());
		compoundWrapper.setSecondEmail(compound.getSecondEmail());
		compoundWrapper.setSecondPhoneNumber(compound.getSecondPhoneNumber());
		compoundWrapper.setThirdEmail(compound.getThirdEmail());
		compoundWrapper.setThirdPhoneNumber(compound.getThirdPhoneNumber());
		return compoundWrapper;
	}


}
