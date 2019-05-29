package com.ntgclarity.smartcompound.common.entity;

import java.io.Serializable;
import java.util.Date;

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
@Table(name = "ng_nts_tenants")
public class Tenant extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4952759092341764940L;

	@ManyToOne
	@JoinColumn(name = "compound_id", referencedColumnName = "recid")
	private Compound compound;

	@Id

	@GeneratedValue(strategy = GenerationType.SEQUENCE ,generator = "my_gen")
	@SequenceGenerator(name = "my_gen", sequenceName = "ng_nts_tenants_recid_seq")
	@Column(name = "recid")
	private Long id;

	@Column(name = "salutation")
	private String salutation;

	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "middle_name")
	private String middleName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "gender")
	private String gender;

	@Column(name = "nationality")
	private String nationality;

	@Column(name = "date_of_birth")
	private Date dateOfBirth;

	@Column(name = "identification_type")
	private String identificationType;

	@Column(name = "identification_number")
	private String identificationNumber;

	@Column(name = "best_connection_method")
	private String bestConnectionMethod;

	@Column(name = "address")
	private String address;

	@Column(name = "email")
	private String email;

	@Column(name = "postal_code")
	private String postalCode;

	@Column(name = "phone_number1")
	private String phoneNumber1;
	@Column(name = "phone_number2")
	private String phoneNumber2;
	@Column(name = "mobile_number1")
	private String mobileNumber1;
	@Column(name = "mobile_number2")
	private String mobileNumber2;
	@Column(name = "country")
	private String country;
	@Column(name = "whatsapp")
	private String whatsapp;

	@Column(name = "city")
	private String city;

	@Column(name = "facebook")
	private String facebook;
	@Column(name = "job")
	private String job;
	
	@Column(name = "tenant_image")
	private String tenantImage;
	
	@ManyToOne
	@JoinColumn(name = "salutation_lookup_id", referencedColumnName = "recid")
	private Lookup salutationLookup;
	@ManyToOne
	@JoinColumn(name = "gender_lookup_id", referencedColumnName = "recid")
	private Lookup genderLookup;

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

	@Override
	public Long getId() {
		return id;
	}

	@Override
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

	public Lookup getSalutationLookup() {
		return salutationLookup;
	}

	public void setSalutationLookup(Lookup salutationLookup) {
		this.salutationLookup = salutationLookup;
	}

	public Lookup getGenderLookup() {
		return genderLookup;
	}

	public void setGenderLookup(Lookup genderLookup) {
		this.genderLookup = genderLookup;
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

	public String getTenantImage() {
		return tenantImage;
	}

	public void setTenantImage(String tenantImage) {
		this.tenantImage = tenantImage;
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

}
