package com.usa.ri.gov.ies.admin.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;



/**
 * This class provide users data representing Table data
 * @author sodam
 *
 */


@Entity
@Table(name="Application_Accounts")
public class AppAccountEntity {
	@Id
	@GeneratedValue
	private int appId;
	
	@Column(name="First_Name")
	private String firstName;
	
	@Column(name="Last_Name")
	private String lastName;
	
	@Column(name="Date_Of_Birth")
	private String dateOfBirth;
	
	@Column(name="Gender")
	private String gender;
	
	@Column(name="Phone_No")
	private String phoneNo;
	
	@Column(unique=true,name="Email_Id")
	private String emailId;
	
	@Column(name="SSN",unique=true)
	private String ssn;
	
	@Column(name="Password")
	private String password;
	
	@Column(name="Role")
	private String role;
	
	@CreationTimestamp
	@Column
	private Date created;
	
	@UpdateTimestamp
	@Column
	private Date updated;
	
	
	@Column(name="Active_Sw")
	private String activeSw;


	public int getAppId() {
		return appId;
	}


	public void setAppId(int appId) {
		this.appId = appId;
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


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getPhoneNO() {
		return phoneNo;
	}


	public void setPhoneNO(String phoneNO) {
		this.phoneNo = phoneNO;
	}


	public String getEmailId() {
		return emailId;
	}


	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}


	public String getSsn() {
		return ssn;
	}


	public void setSsn(String ssn) {
		this.ssn = ssn;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public Date getCreated() {
		return created;
	}


	public void setCreated(Date created) {
		this.created = created;
	}


	public Date getUpdated() {
		return updated;
	}


	public void setUpdated(Date updated) {
		this.updated = updated;
	}


	public String getActiveSw() {
		return activeSw;
	}


	public void setActiveSw(String activeSw) {
		this.activeSw = activeSw;
	}


	public String getDateOfBirth() {
		return dateOfBirth;
	}


	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	
	

}
