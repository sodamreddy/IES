package com.usa.ri.gov.ies.admin.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.usa.ri.gov.ies.admin.model.AppAccountModel;
import com.usa.ri.gov.ies.admin.model.PlanModel;
import com.usa.ri.gov.ies.admin.service.AppAccountService;
import com.usa.ri.gov.ies.constants.ApplicationConstants;
import com.usa.ri.gov.ies.properties.AppProperties;

/**
 * this class is used to handle admin module functionalities
 * 
 * @author sodam
 *
 */
@Controller
public class AdminController {
	Logger logger = LoggerFactory.getLogger(AdminController.class);

	@Autowired(required = true)
	private AppAccountService adminService;

	@Autowired(required = true)
	private AppProperties properties;

	/**
	 * this method is used to load form page for registration
	 * 
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/accReg", method = RequestMethod.GET)
	public String appAccountRegForm(Model model) {
		logger.debug("AdminController: appAccountRegForm Started");
		AppAccountModel accModel = new AppAccountModel();
		// Add AppAccount to Model Scope
		model.addAttribute("accModel", accModel);
		initForm(model);
		logger.debug("AdminController: appAccountRegForm Ended");
		logger.info("AdminController: Registration Form loaded Sucessfully");
		return "accReg";
	}

	/**
	 * this method is use to process the Account Registration Form
	 * 
	 * @param accModel
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/accReg", method = RequestMethod.POST)
	public String appAccountRegForm(@ModelAttribute("accModel") AppAccountModel accModel, Model model) {
		logger.debug("AdminController: accRegFor() POST method started");
		// get properties
		Map<String, String> appProps = properties.getProperties();
		initForm(model);
		try {
			// call registerApplicant method
			boolean isSaved = adminService.registerApplicant(accModel);
			if (isSaved) {
				model.addAttribute(ApplicationConstants.SUCCESS, appProps.get(ApplicationConstants.SUCCESS));
			} else {
				model.addAttribute(ApplicationConstants.FAILED, appProps.get(ApplicationConstants.FAILED));
			} // IF

		} catch (Exception e) {
			logger.warn("Registration failed:" + e.getMessage());
		}
		return "accReg";
	}

	/**
	 * this method is used for loading create plan form page
	 * 
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/crtPln", method = RequestMethod.GET)
	public String createPlan(Model model) {
		logger.debug("AdminController: createPlan() started");
		PlanModel planModel = new PlanModel();
		model.addAttribute(planModel);
		logger.debug("AdminController: createPlan() ended");
		logger.info("AdminController: Create Plan form loaded sucessfully..");
		return "creat_plan";
	}

	/**
	 * this method is used to process create plan Form
	 * 
	 * @param planModel
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/crtPln", method = RequestMethod.POST)
	public String createPlan(@ModelAttribute("planModel") PlanModel planModel, Model model) {
		logger.debug("AdminController: createPlan() POST method started");
		try {
			// call registerApplicant method
			boolean isSaved = adminService.registerPlan(planModel);
			if (isSaved) {
				model.addAttribute(ApplicationConstants.SUCCESS,
						properties.getProperties().get(ApplicationConstants.PLAN_CREATION_SUCCESS));
			} else {
				System.out.println("failed");
				System.out.println(ApplicationConstants.FAILED);
				System.out.println(properties.getProperties().get(ApplicationConstants.PLAN_CREATION_FAILURE));
				model.addAttribute(ApplicationConstants.FAILED,
						properties.getProperties().get(ApplicationConstants.PLAN_CREATION_FAILURE));
			} // IF

		} catch (Exception e) {
			logger.error("Registration failed:" + e.getMessage());
		}
		logger.debug("AdminController: createPlan() POST method ended");
		logger.info("AdminController: plan creation completed");
		return "creat_plan";
	}

	/**
	 * this method gets the plans records and keep them in Model Scope
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/viewPlans", method = RequestMethod.GET)
	public String viewPlans(Model model) {
		logger.debug("AdminController: viewPlans() started");
		List<PlanModel> listPlan;
		listPlan = adminService.viewPlanAccounts();
		// keeping the Plan list in Model
		model.addAttribute(ApplicationConstants.PLAN_RECORDS, listPlan);
		logger.debug("AdminController: viewPlans() ended");
		logger.info("AdminController: viewPlans() executed");
		return "view_plans";
	}
	/**
	 * this method gets Application Account records
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/viewAppAccounts", method = RequestMethod.GET)
	public String viewAppAccounts(Model model) {
		logger.debug("AdminController: viewAppAccounts() started");
		List<AppAccountModel> listAccounts;
		listAccounts = adminService.viewAppAccounts();
		// keeping the Plan list in Model
		model.addAttribute(ApplicationConstants.PLAN_RECORDS, listAccounts);
		logger.debug("AdminController: viewAppAccounts() ended");
		logger.info("AdminController: viewAppAccounts() executed");
		return "view_accounts";
	}

	/**
	 * this method is used to get Roles
	 * 
	 * @param model
	 */
	public void initForm(Model model) {
		List<String> roleList = new ArrayList<String>();
		roleList.add("Admin");
		roleList.add("Case Worker");
		model.addAttribute("roleList", roleList);
	}// initForm(-)

	/**
	 * this method is used to check availability of unique email
	 * 
	 * @param HttpServlerRequest
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/accReg/uniqueMail")
	public @ResponseBody String checkEmailValidity(HttpServletRequest req, Model model) {
		logger.debug("AdminController: checkEmailValidity() started");
		String email = req.getParameter("email");
		String isUnique = adminService.findByEmail(email);
		logger.debug("AdminController: checkEmailValidity() ended");
		logger.info("AdminController: checkEmailValidity() executed");
		System.out.println(isUnique);
		return isUnique;
	}// CheckEmailValidity(-,-)

	/**
	 * this method is used to check the availability of unique plan
	 * 
	 * @param HttpServletRequest
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"/crtPln/validPlan","/editPlan/validPlan"})
	public @ResponseBody boolean planValidate(HttpServletRequest req, Model model) {
		String plan = req.getParameter("planName");
		return adminService.isUniquePlan(plan);
	}

	/**
	 * this method is used to deactivate the  plan 
	 * @param req
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/deletePlan")
	public String deletePlan(HttpServletRequest req, Model model) {
		List<PlanModel> listPlan;
		String planId = req.getParameter("planId");
		boolean isDeleted = adminService.updatePlanActiveSw(planId, ApplicationConstants.IN_ACTIVE_SW);
		listPlan = adminService.viewPlanAccounts();
		model.addAttribute(ApplicationConstants.PLAN_RECORDS, listPlan);
		if (isDeleted) {
			model.addAttribute(ApplicationConstants.SUCCESS,
					properties.getProperties().get(ApplicationConstants.PLAN_DELETE_SUCCESS));
		} else
			model.addAttribute(ApplicationConstants.FAILED,
					properties.getProperties().get(ApplicationConstants.PLAN_DELETE_FAILED));
		return "view_plans";
	}
	/**
	 * this method is used to activate plan 
	 * @param req
	 * @param model
	 * @return
	 */

	@RequestMapping(value = "/activatePlan")
	public String activatePlan(HttpServletRequest req, Model model) {
		List<PlanModel> listPlans;
		String planId = req.getParameter("planId");
		boolean isActivated = adminService.updatePlanActiveSw(planId, ApplicationConstants.ACTIVE_SW);
		listPlans = adminService.viewPlanAccounts();
		model.addAttribute(ApplicationConstants.PLAN_RECORDS, listPlans);
		if (isActivated) {
			model.addAttribute(ApplicationConstants.SUCCESS,
					properties.getProperties().get(ApplicationConstants.PLAN_ACTIVATE_SUCCESS));
		} else
			model.addAttribute(ApplicationConstants.FAILED,
					properties.getProperties().get(ApplicationConstants.PLAN_ACTIVATE_FAILED));
		return "view_plans";
	}
	/**
	 * this method is used to deactivate the  plan 
	 * @param req
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/deleteAcc")
	public String deleteAppAccount(HttpServletRequest req, Model model) {
		List<AppAccountModel> listAppAccounts;
		String appId = req.getParameter("appId");
		boolean isDeleted = adminService.updateAccountActiveSw(appId, ApplicationConstants.IN_ACTIVE_SW);
		listAppAccounts = adminService.viewAppAccounts();
		model.addAttribute(ApplicationConstants.ACCOUNT_RECORDS, listAppAccounts);
		if (isDeleted) {
			model.addAttribute(ApplicationConstants.SUCCESS,
					properties.getProperties().get(ApplicationConstants.ACCOUNT_DELETE_SUCCESS));
		} else
			model.addAttribute(ApplicationConstants.FAILED,
					properties.getProperties().get(ApplicationConstants.ACCOUNT_DELETE_FAILED));
		return "view_accounts";
	}
	/**
	 * this method is used to activate Application account 
	 * @param req
	 * @param model
	 * @return
	 */

	@RequestMapping(value = "/activateAcc")
	public String activateAppAccount(HttpServletRequest req, Model model) {
		List<AppAccountModel> listAppAccounts;
		String planId = req.getParameter("appId");
		boolean isActivated = adminService.updateAccountActiveSw(planId, ApplicationConstants.ACTIVE_SW);
		listAppAccounts = adminService.viewAppAccounts();
		model.addAttribute(ApplicationConstants.ACCOUNT_RECORDS, listAppAccounts);
		if (isActivated) {
			model.addAttribute(ApplicationConstants.SUCCESS,
					properties.getProperties().get(ApplicationConstants.ACCOUNT_ACTIVATE_SUCCESS));
		} else
			model.addAttribute(ApplicationConstants.FAILED,
					properties.getProperties().get(ApplicationConstants.ACCOUNT_ACTIVATE_FAILED));
		return "view_accounts";
	}
	/**
	 * this method is used to load login form
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String loginForm(Model model) {
		logger.debug("AdminController: login() strated");
		AppAccountModel accModel = new AppAccountModel();
		model.addAttribute("accModel", accModel);
		logger.debug("AdminController: login() ended");
		logger.info("AdminController: login form in loaded successfully");
		return "login";
	}// method:login
	
	/**
	 * this method is used to process the login into account
	 * @param accModel
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginValidation(@ModelAttribute("accModel") AppAccountModel accModel, Model model) {
		String message = adminService.verifyLoginCredentials(accModel);
		if (message.equalsIgnoreCase(ApplicationConstants.SUCCESS)) {
			return accModel.getRole().toLowerCase() + "_dashboard";
		} else {
			model.addAttribute(ApplicationConstants.FAILED, message);
			return "login";
		}
	}
	
	/**
	 * this method is used to load forgot password page
	 * @return
	 */
	
	@RequestMapping(value = "/forgot")
	public String forgotPassword() {
		logger.info("AdminService: forgotPassword() form page loaded successfully");
		return "forgot_password";
	}
	
	/**
	 * this method is used to recover paswword
	 * @param model
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/forgot", method = RequestMethod.POST)
	public String forgotPassword(Model model, HttpServletRequest req) {
		logger.info("AdminService: forgotPassword() started");
		String email = req.getParameter("emailId");
		String message = adminService.passwordRecovery(email);
		if (ApplicationConstants.SUCCESS.equalsIgnoreCase(message)) {
			model.addAttribute(ApplicationConstants.SUCCESS,
					properties.getProperties().get(ApplicationConstants.PWD_RECOVERY_SUCCESS));
		} else
			model.addAttribute(ApplicationConstants.SUCCESS,
					properties.getProperties().get(ApplicationConstants.PWD_RECOVERY_FAILED));

		return "forgot_password";
	}
	/**
	 * this method is used to load edit application account form page
	 * @param req
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/editAcc",method=RequestMethod.GET)
	public String editAccount(HttpServletRequest req, Model model){
		logger.info( "AdminController: editAccount() started" );
		AppAccountModel accModel;
		int appId=Integer.parseInt(req.getParameter("appId"));
		accModel= adminService.findByAccountId(appId);
		model.addAttribute(ApplicationConstants.APP_ACCOUNT, accModel);
		initForm(model);
		logger.debug("AdminController: editAccount() ended");
		logger.info("AdminController: editAccount() executed edit form loaded");
		return "account_edit";
	}
	/**
	 * this method is used to procees the edit Application account page
	 * @param model
	 * @param accModel
	 * @return
	 */
	@RequestMapping(value="/editAcc",method=RequestMethod.POST)
	public String editAccount(Model model,@ModelAttribute("accModel") AppAccountModel accModel) {
		logger.debug("AdminController: editAccount() Post Method stared");
		String status= adminService.editAccountRecord(accModel);
		//keeping roles in model Scope
		initForm(model);
		if(ApplicationConstants.SUCCESS.equalsIgnoreCase(status)) {
			model.addAttribute(ApplicationConstants.SUCCESS,
					properties.getProperties().get(ApplicationConstants.ACC_EDIT_SUCCESS));
		}
		else {
			model.addAttribute(ApplicationConstants.SUCCESS,
					properties.getProperties().get(ApplicationConstants.ACC_EDIT_FAILED));
		}
		logger.debug("AdminController: editAccount() Post Method ended");
		return "account_edit";
	}
	/**
	 * this method is used to load edit plan form page
	 * @param req
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/editPlan",method=RequestMethod.GET)
	public String editPlan(HttpServletRequest req, Model model){
		logger.debug( "AdminController: editPlan() started" );
		PlanModel planModel;
		int planId=Integer.parseInt(req.getParameter("planId"));
		planModel= adminService.findByPlanId(planId);
		model.addAttribute(ApplicationConstants.PLAN_ACCOUNT, planModel);
		logger.debug("AdminController: editPlan() ended");
		logger.info("AdminController: editPlan() executed edit form loaded");
		return "plan_edit";
	}
	/**
	 * this method is used to process edit plan form page
	 * @param model
	 * @param planModel
	 * @return
	 */
	@RequestMapping(value="/editPlan",method=RequestMethod.POST)
	public String editPlan(Model model,@ModelAttribute("planModel") PlanModel planModel) {
		logger.debug("AdminController: editPlan() Post Method Started");
		String status= adminService.editPlanAccount(planModel);
		if(ApplicationConstants.SUCCESS.equalsIgnoreCase(status)) {
			model.addAttribute(ApplicationConstants.SUCCESS,
					properties.getProperties().get(ApplicationConstants.PLAN_ACC_EDIT_SUCCESS));
		}
		else {
			model.addAttribute(ApplicationConstants.SUCCESS,
					properties.getProperties().get(ApplicationConstants.PLAN_ACC_EDIT_FAILED));
		}
		logger.debug("AdminController: editPlan() Post Method ended");
		return "plan_edit";
	}

}// class:AdminController
