package com.ntgclarity.smartcompound.common.dto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.ntgclarity.smartcompound.common.entity.Facility;

/**
*
* @author Karim M.Fadel
*/
@XmlRootElement
public class FacilityWapper {

	@NotNull
	private Long id;
	private Double longtude;
	private Double lattitude;
	@NotNull
	private FacilityName facilityName;
	private String facilityType;
	private String facilityImage; 
	@Min(0)
	@Max(1)
	private Integer isForRent;
	private String description;
	private String status;
	private Double area;
	
	@XmlElement
	public String getFacilityType() {
		return facilityType;
	}
	public void setFacilityType(String facilityType) {
		this.facilityType = facilityType;
	}
	@XmlElement
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@XmlElement
	public Double getLongtude() {
		return longtude;
	}
	public void setLongtude(Double longtude) {
		this.longtude = longtude;
	}
	@XmlElement
	public Double getLattitude() {
		return lattitude;
	}
	public void setLattitude(Double lattitude) {
		this.lattitude = lattitude;
	}

	@XmlElement
	public FacilityName getFacilityName() {
		return facilityName;
	}
	public void setFacilityName(FacilityName facilityName) {
		this.facilityName = facilityName;
	}
	@XmlElement
	public String getFacilityImage() {
		return facilityImage;
	}
	public void setFacilityImage(String facilityImage) {
		this.facilityImage = facilityImage;
	}
	
	@XmlElement
	public Integer getIsForRent() {
		return isForRent;
	}
	public void setIsForRent(Integer isForRent) {
		this.isForRent = isForRent;
	}
	@XmlElement
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@XmlElement
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@XmlElement
	public Double getArea() {
		return area;
	}
	public void setArea(Double area) {
		this.area = area;
	}
	public static List<FacilityWapper> wrappedFacilitiesObject(List<Facility> facilities) {
		List<FacilityWapper>facilityWappers = new ArrayList<>();
		for (Iterator<Facility> iterator = facilities.iterator(); iterator.hasNext();) {
			facilityWappers.add(wrappedFacilityObject((Facility) iterator.next()));
		}
		return facilityWappers;
	}
	private static FacilityWapper wrappedFacilityObject(Facility facility) {
		FacilityWapper facilityWapper = new FacilityWapper();
		facilityWapper.setId(facility.getId());
		facilityWapper.setFacilityName(FacilityName.getFacilityNameObject(facility));
		facilityWapper.setLattitude(facility.getLattitude());
		facilityWapper.setLongtude(facility.getLongtude());
		facilityWapper.setFacilityType(facility.getFacilityType());
		facilityWapper.setFacilityImage(facility.getPictureUrl());
		facilityWapper.setArea(facility.getArea());
		facilityWapper.setIsForRent(facility.getIsForRent());
		facilityWapper.setStatus(facility.getStatus());
		facilityWapper.setDescription(facility.getDescription());
		return facilityWapper;
	}
	
}
