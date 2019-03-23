package com.usa.ri.gov.ies.admin.service;




import java.util.List;

import com.usa.ri.gov.ies.admin.model.AppAccountModel;
import com.usa.ri.gov.ies.admin.model.PlanModel;

public interface AppAccountService {
	public boolean registerApplicant(AppAccountModel AppAccount);
	public String findByEmail(String email);
	public boolean registerPlan(PlanModel planModel);
	public boolean isUniquePlan(String plan);
	public List<PlanModel> viewPlanAccounts();
	public List<AppAccountModel> viewAppAccounts();
	public boolean updatePlanActiveSw(String planId,String sw);
	public boolean updateAccountActiveSw(String planId,String sw);
	public String verifyLoginCredentials(AppAccountModel accModel);
	public String passwordRecovery(String email);
	public String editAccountRecord(AppAccountModel accModel);
	public AppAccountModel findByAccountId(int appId);
	public PlanModel findByPlanId(int planId);
	public String editPlanAccount(PlanModel planModel);
}
