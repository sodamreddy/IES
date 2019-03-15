package com.usa.ri.gov.ies.admin.service;




import com.usa.ri.gov.ies.admin.model.AppAccountModel;

public interface AppAccountService {
	public boolean registerApplicant(AppAccountModel AppAccount);
	public String findByEmail(String email);

}
