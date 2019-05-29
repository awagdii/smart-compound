package com.ntgclarity.smartcompound.common.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;




import com.ntgclarity.smartcompound.common.base.BaseEntity;

@Entity
@Table(name = "ng_nts_facilities")
public class Facility extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="ng_nts_facilities_recid_seq")
	@SequenceGenerator(name="ng_nts_facilities_recid_seq",sequenceName="ng_nts_facilities_recid_seq")
	@Column(name = "recid")
	private Long id;
	@Column(name="building_number")
	private Long buildingNumber;
	@Column(name="floor_number")
	private Long floorNumber;
	@Column (name="facility_number")
	private Long facilityNumber;
	@Column(name="area")
	private Double area;
	@Column(name="block")
	private String block;
	@Column(name="is_for_rent")
	private Integer isForRent;
	@Column (name="facility_type")
	private String facilityType;
	@Column (name="picture_url")
	private String pictureUrl;
	@Column(name="longtude")
	private Double longtude;
	@Column(name="lattitude")
	private Double lattitude;
	@Column(name="street")
	private String street;
	@Column(name="status")
	private String status;
	@Column(name="description")
	private String description;
	@ManyToOne
	@JoinColumn(name ="tenant_id" , referencedColumnName="recid")
	private Tenant tenant;
	
	@ManyToOne
	@JoinColumn(name ="compound_id" , referencedColumnName="recid")
	private Compound compound;
	@ManyToOne
	@JoinColumn(name ="facility_type_lookup_id" , referencedColumnName="recid")
	private Lookup facilityTypeLookup;
	
	@ManyToOne
	@JoinColumn(name ="status_lookup_id" , referencedColumnName="recid")
	private Lookup statusLookup;
	
	
	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id=id;
		
	}


	public Double getArea() {
		return area;
	}

	public void setArea(Double area) {
		this.area = area;
	}

	public String getBlock() {
		return block;
	}

	public Long getBuildingNumber() {
		return buildingNumber;
	}

	public void setBuildingNumber(Long buildingNumber) {
		this.buildingNumber = buildingNumber;
	}

	public Long getFloorNumber() {
		return floorNumber;
	}

	public void setFloorNumber(Long floorNumber) {
		this.floorNumber = floorNumber;
	}

	public Long getFacilityNumber() {
		return facilityNumber;
	}

	public void setFacilityNumber(Long facilityNumber) {
		this.facilityNumber = facilityNumber;
	}

	public Integer getIsForRent() {
		return isForRent;
	}

	public void setIsForRent(Integer isForRent) {
		this.isForRent = isForRent;
	}

	public String getFacilityType() {
		return facilityType;
	}

	public void setFacilityType(String facilityType) {
		this.facilityType = facilityType;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public Double getLongtude() {
		return longtude;
	}

	public void setLongtude(Double longtude) {
		this.longtude = longtude;
	}

	public Double getLattitude() {
		return lattitude;
	}

	public void setLattitude(Double lattitude) {
		this.lattitude = lattitude;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setBlock(String block) {
		this.block = block;
	}
	

	public Tenant getTenant() {
		return tenant;
	}

	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}

	public Compound getCompound() {
		return compound;
	}

	public void setCompound(Compound compound) {
		this.compound = compound;
	}

	public Lookup getFacilityTypeLookup() {
		return facilityTypeLookup;
	}

	public void setFacilityTypeLookup(Lookup facilityTypeLookup) {
		this.facilityTypeLookup = facilityTypeLookup;
	}

	public Lookup getStatusLookup() {
		return statusLookup;
	}

	public void setStatusLookup(Lookup statusLookup) {
		this.statusLookup = statusLookup;
	}

	@Override
	public String toString() {
		return "Facility [id=" + id + ", buildingNumber=" + buildingNumber
				+ ", floorNumber=" + floorNumber + ", facilityNumber="
				+ facilityNumber + ", area=" + area + ", block=" + block
				+ ", isForRent=" + isForRent + ", facilityType=" + facilityType
				+ ", pictureUrl=" + pictureUrl + ", longtude=" + longtude
				+ ", lattitude=" + lattitude + ", street=" + street
				+ ", status=" + status + ", description=" + description
				+ ", tenant=" + tenant + ", compound=" + compound+"]";
				 
	}

}
