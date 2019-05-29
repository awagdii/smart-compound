package com.ntgclarity.smartcompound.common.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.ntgclarity.smartcompound.common.entity.Facility;

/**
*
* @author Karim M.Fadel
*/
@XmlRootElement
public class FacilityName {

	private String block;
	private String street;
	private Long buildingNumber;
	private Long floorNumber;
	private Long facilityNumber;
	
	@XmlElement
	public String getBlock() {
		return block;
	}
	public void setBlock(String block) {
		this.block = block;
	}
	@XmlElement
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	@XmlElement
	public Long getBuildingNumber() {
		return buildingNumber;
	}
	public void setBuildingNumber(Long buildingNumber) {
		this.buildingNumber = buildingNumber;
	}
	
	@XmlElement
	public Long getFloorNumber() {
		return floorNumber;
	}
	public void setFloorNumber(Long floorNumber) {
		this.floorNumber = floorNumber;
	}
	
	@XmlElement
	public Long getFacilityNumber() {
		return facilityNumber;
	}
	public void setFacilityNumber(Long facilityNumber) {
		this.facilityNumber = facilityNumber;
	}
	
	public static FacilityName getFacilityNameObject(Facility facility){
		FacilityName facilityName = new FacilityName();
		facilityName.setBlock(facility.getBlock());
		facilityName.setBuildingNumber(facility.getBuildingNumber());
		facilityName.setFacilityNumber(facility.getFacilityNumber());
		facilityName.setStreet(facility.getStreet());
		facilityName.setFloorNumber(facility.getFloorNumber());
		return facilityName;
		
	}
	
}
