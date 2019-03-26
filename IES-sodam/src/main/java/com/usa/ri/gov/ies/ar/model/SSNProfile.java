package com.usa.ri.gov.ies.ar.model;

import java.util.Date;

public class SSNProfile {
	private long ssn;
	private Date dob;
	private String state;
	public long getSsn() {
		return ssn;
	}
	public void setSsn(long ssn) {
		this.ssn = ssn;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
