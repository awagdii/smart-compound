package com.ntgclarity.smartcompound.common.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.ntgclarity.smartcompound.common.entity.Compound;
import com.ntgclarity.smartcompound.common.entity.Tenant;

@XmlRootElement
public class TenantWrapperProfile {

	private Compound compound;
	private Long id;
	private String salutation;
	private String firstName;
	private String middleName;
	private String lastName;
	private String username;
	private String password;
	private String gender;
	private String nationality;
	private Date dateOfBirth;
	private String identificationType;
	private String identificationNumber;
	private String bestConnectionMethod;
	private String address;
	private String email;
	private String postalCode;
	private String phoneNumber1;
	private String phoneNumber2;
	private String mobileNumber1;
	private String mobileNumber2;
	private String country;
	private String whatsapp;
	private String city;
	private String facebook;
	private String job;


	public String getSalutation() {
		return salutation;
	}

	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getIdentificationType() {
		return identificationType;
	}

	public void setIdentificationType(String identificationType) {
		this.identificationType = identificationType;
	}

	public String getIdentificationNumber() {
		return identificationNumber;
	}

	public void setIdentificationNumber(String identificationNumber) {
		this.identificationNumber = identificationNumber;
	}

	public String getBestConnectionMethod() {
		return bestConnectionMethod;
	}

	public void setBestConnectionMethod(String bestConnectionMethod) {
		this.bestConnectionMethod = bestConnectionMethod;
	}

	public String getPhoneNumber1() {
		return phoneNumber1;
	}

	public void setPhoneNumber1(String phoneNumber1) {
		this.phoneNumber1 = phoneNumber1;
	}

	public String getPhoneNumber2() {
		return phoneNumber2;
	}

	public void setPhoneNumber2(String phoneNumber2) {
		this.phoneNumber2 = phoneNumber2;
	}

	public String getMobileNumber1() {
		return mobileNumber1;
	}

	public void setMobileNumber1(String mobileNumber1) {
		this.mobileNumber1 = mobileNumber1;
	}

	public String getMobileNumber2() {
		return mobileNumber2;
	}

	public void setMobileNumber2(String mobileNumber2) {
		this.mobileNumber2 = mobileNumber2;
	}

	public String getWhatsapp() {
		return whatsapp;
	}

	public void setWhatsapp(String whatsapp) {
		this.whatsapp = whatsapp;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public Compound getCompound() {
		return compound;
	}

	public void setCompound(Compound compound) {
		this.compound = compound;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	@Override
	public String toString() {
		return "Tenant [compound=" + compound + ", id=" + id + ", salutation="
				+ salutation + ", firstName=" + firstName + ", lastName="
				+ lastName + ", username=" + username + ", password="
				+ password + ", gender=" + gender + ", nationality="
				+ nationality + ", dateOfBirth=" + dateOfBirth
				+ ", identificationType=" + identificationType
				+ ", identificationNumber=" + identificationNumber
				+ ", bestConnectionMethod=" + bestConnectionMethod + ", email="
				+ email + ", phoneNumber1=" + phoneNumber1 + ", phoneNumber2="
				+ phoneNumber2 + ", mobileNumber1=" + mobileNumber1
				+ ", mobileNumber2=" + mobileNumber2 + ", country=" + country
				+ ", whatsapp=" + whatsapp + ", city=" + city + ", facebook="
				+ facebook + ", job=" + job + "]";
	}
	
	public static List<TenantWrapperProfile> wrappedTenantsObject(List<Tenant> tenants) {
		List<TenantWrapperProfile> tenantWappers = new ArrayList<>();
		for (Iterator<Tenant> iterator = tenants.iterator(); iterator.hasNext();) {
			tenantWappers.add(wrappedTenantObject((Tenant) iterator.next()));
		}
		return tenantWappers;
	}

	public static TenantWrapperProfile wrappedTenantObject(Tenant tenant) {
		TenantWrapperProfile tenantWapper = new TenantWrapperProfile();
		tenantWapper.setId(tenant.getId());
		tenantWapper.setAddress(tenant.getAddress());
		tenantWapper.setCity(tenant.getCity());
		tenantWapper.setCompound(tenant.getCompound());
		tenantWapper.setCountry(tenant.getCountry());
		tenantWapper.setDateOfBirth(tenant.getDateOfBirth());
		tenantWapper.setEmail(tenant.getEmail());
		tenantWapper.setFacebook(tenant.getFacebook());
		tenantWapper.setFirstName(tenant.getFirstName());
		tenantWapper.setGender(tenant.getGender());
		tenantWapper.setIdentificationNumber(tenant.getIdentificationNumber());
		tenantWapper.setIdentificationType(tenant.getIdentificationType());
		tenantWapper.setJob(tenant.getJob());
		tenantWapper.setLastName(tenant.getLastName());
		tenantWapper.setMiddleName(tenant.getMiddleName());
		tenantWapper.setMobileNumber1(tenant.getMobileNumber1());
		tenantWapper.setMobileNumber2(tenant.getMobileNumber2());
		tenantWapper.setNationality(tenant.getNationality());
		tenantWapper.setPassword(tenant.getPassword());
		tenantWapper.setPhoneNumber1(tenant.getPhoneNumber1());
		tenantWapper.setPhoneNumber2(tenant.getPhoneNumber2());
		tenantWapper.setPostalCode(tenant.getPostalCode());
		tenantWapper.setSalutation(tenant.getSalutation());
		tenantWapper.setUsername(tenant.getUsername());
		tenantWapper.setWhatsapp(tenant.getWhatsapp());
		return tenantWapper;
	}

}
