package com.usa.ri.gov.ies.admin.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

/**
 * This class provide users data representing Table data
 * @author sodam
 *
 */

@Data
@Entity
@Table(name="Application_Accounts")
public class AppAccountEntity {
	@Id
	@GeneratedValue
	private int appId;
	
	@Column(nullable=false)
	private String firstName;
	
	@Column(nullable=false)
	private String lastName;
	
	@Column(nullable=false)
	private String gender;
	
	@Column(unique=true)
	private String phoneNO;
	
	@Column(unique=false)
	private String emailId;
	
	@Column(nullable=false)
	private String ssn;
	
	@Column(nullable=false)
	private String password;
	
	@Column(nullable=false)
	private String role;
	
	@CreationTimestamp
	@Column
	private Date created;
	
	@UpdateTimestamp
	@Column
	private Date updated;
	
	
	@Column(nullable=false)
	private String activeSw;
	
	/*@Column(unique=false)
	private String deleteSw;
*/
	

}
