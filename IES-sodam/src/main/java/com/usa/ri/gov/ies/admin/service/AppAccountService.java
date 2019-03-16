package com.usa.ri.gov.ies.admin.service;




import java.util.List;

import com.usa.ri.gov.ies.admin.model.AppAccountModel;
import com.usa.ri.gov.ies.admin.model.PlanModel;

public interface AppAccountService {
	public boolean registerApplicant(AppAccountModel AppAccount);
//	public String findByEmail(String email);
	public boolean registerPlan(PlanModel planModel);
	public boolean isUniquePlan(String plan);
	public List<PlanModel> viewPlanAccounts();
}
