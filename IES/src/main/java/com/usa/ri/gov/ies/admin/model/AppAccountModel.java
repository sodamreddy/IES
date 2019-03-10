package com.usa.ri.gov.ies.admin.model;

import java.util.Date;

import lombok.Data;

@Data
public class AppAccountModel {

	private String firstName;
	private String lastName;
	private Date dateOfBirth;
	private String phoneNo;
	private String ssn;
	private String emailId;
	private String password;
	private String gender;
	private String role;
	
}
