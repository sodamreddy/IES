package com.usa.ri.gov.ies.admin.model;

import lombok.Data;

@Data
public class CaseWorker {
	private String id;
	private String firstName;
	private String lastName;

	private String phone;
	private String email;
	private String password;
	private String activate;
	private String delete;
}
