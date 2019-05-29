package com.ntgclarity.smartcompound.common.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.ntgclarity.smartcompound.common.base.BaseEntity;

@Entity
@Table(name = "ng_nts_employees")
public class Employee extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="ng_nts_employees_recid_seq")
	@SequenceGenerator(name="ng_nts_employees_recid_seq",sequenceName="ng_nts_employees_recid_seq")
	@Column(name = "recid")
	private Long id;
	@Column(name = "salutation")
	private String salutaion;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "middle_name")
	private String middleName;
	@Column(name = "job")
	private String job;
	@Column(name = "gender")
	private String gender;
	@Column(name = "identification_type")
	private String identificationType;
	@Column(name = "identification_number")
	private String identificationNumber;
	@Column(name = "address")
	private String address;
	@Column(name = "hire_date")
	private Date hireDate;

	@Column(name = "email")
	private String email;
	@Column(name = "group_name")
	private String groupName;
	@Column(name = "username")
	private String username;
	@Column(name = "password")
	private String password;
	@Column(name = "status")
	private String status;
	@Column(name = "phone_number1")
	private String phoneNumber1;
	@Column(name = "phone_number2")
	private String phoneNumber2;
	@Column(name = "mobile_number1")
	private String mobileNumber1;
	@Column(name = "mobile_number2")
	private String mobileNumber2;

	@ManyToOne
	@JoinColumn(name = "compound_id", referencedColumnName = "recid")
	private Compound compound;
	
	@ManyToOne
	@JoinColumn(name = "created_by", referencedColumnName = "recid")
	private Employee createdBy;
	@ManyToOne
	@JoinColumn(name = "salutation_lookup_id", referencedColumnName = "recid")
	private Lookup salutationLookup;
	@ManyToOne
	@JoinColumn(name = "gender_lookup_id", referencedColumnName = "recid")
	private Lookup genderLookup;
	@ManyToOne
	@JoinColumn(name = "status_lookup_id", referencedColumnName = "recid")
	private Lookup statusLookup;
;
	@ManyToOne
	@JoinColumn(name = "identificationtype_lookup_id", referencedColumnName = "recid")
	private Lookup identificationTypeLookup;
	

	
	
	@Column(name = "super_admin_flag")
	private Integer superAdminFlag;
	
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

	public Lookup getStatusLookup() {
		return statusLookup;
	}

	public void setStatusLookup(Lookup statusLookup) {
		this.statusLookup = statusLookup;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getSalutaion() {
		return salutaion;
	}

	public void setSalutaion(String salutaion) {
		this.salutaion = salutaion;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
	

	public Lookup getIdentificationTypeLookup() {
		return identificationTypeLookup;
	}

	public void setIdentificationTypeLookup(Lookup identificationTypeLookup) {
		this.identificationTypeLookup = identificationTypeLookup;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", salutaion=" + salutaion
				+ ", firstName=" + firstName + ", lastName=" + lastName
				+ ", middleName=" + middleName + ", job=" + job + ", gender="
				+ gender + ", identificationType=" + identificationType
				+ ", identificationNumber=" + identificationNumber
				+ ", address=" + address + ", hireDate=" + hireDate
				+ ", email=" + email + ", groupName=" + groupName
				+ ", username=" + username + ", password=" + password
				+ ", status=" + status + ", phoneNumber1=" + phoneNumber1
				+ ", phoneNumber2=" + phoneNumber2 + ", mobileNumber1="
				+ mobileNumber1 + ", mobileNumber2=" + mobileNumber2

				+ "]";
	}

	public Integer getSuperAdminFlag() {
		return superAdminFlag;
	}

	public void setSuperAdminFlag(Integer superAdminFlag) {
		this.superAdminFlag = superAdminFlag;
	}
	
	public boolean isSuperAdmin()
	{
		return superAdminFlag !=null && superAdminFlag == 1;
	}

	public Employee getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Employee createdBy) {
		this.createdBy = createdBy;
	}



}
