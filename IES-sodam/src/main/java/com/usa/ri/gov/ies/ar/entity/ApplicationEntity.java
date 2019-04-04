package com.usa.ri.gov.ies.ar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="applications_master")
public class ApplicationEntity {
	
//	@GenericGenerator(name = "seq1", strategy = " com.usa.ri.gov.ies.util.AppRegCustomIdGenerator")
//	@GeneratedValue(generator="seq1")
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="App_No")
	private int appNo;
	@Column(name="First_Name")
	private String firstName;
	@Column(name="Last_Name")
	private String lastName;
	@Column(name="date_of_birth")
	private String dateOfBirth;
	@Column(name="Phone_no")
	private String phoneNo;
	@Column(name="SSN")
	private long ssn;
	@Column(name="email")
	private String emailId;
	@Column(name="gender")
	private String gender;
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
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public long getSsn() {
		return ssn;
	}
	public void setSsn(long ssn) {
		this.ssn = ssn;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getAppNo() {
		return appNo;
	}
	public void setAppNo(int appNo) {
		this.appNo = appNo;
	}
}
