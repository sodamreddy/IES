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
 * @author sodam
 *
 */
@Controller
public class AdminController {
	Logger logger=LoggerFactory.getLogger(AdminController.class);
	
	@Autowired(required=true)
	private AppAccountService adminService;
	
	@Autowired(required=true)
	private AppProperties properties;
	/**
	 * this method is used to load form page for registration
	 * @param model
	 * @return String
	 */
	@RequestMapping(value="/accReg",method=RequestMethod.GET)
	public String appAccountRegForm(Model model) {
		logger.debug("AdminController: appAccountRegForm Started");
		AppAccountModel accModel= new AppAccountModel();
		//Add AppAccount to Model Scope
		model.addAttribute("accModel",accModel);
		initForm(model);
		logger.debug("AdminController: appAccountRegForm Ended");
		logger.info("AdminController: Registration Form loaded Sucessfully");
		return "accReg";
	}
	
	
	/**
	 * this method is use to process the Account Registration Form 
	 * @param accModel
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/accReg",method=RequestMethod.POST)
	public String appAccountRegForm(@ModelAttribute("accModel") AppAccountModel accModel,Model model) {
		logger.debug("AdminController: accRegFor() POST method started");
		//get properties 
		Map<String,String> appProps= properties.getProperties();
		try {
			//call registerApplicant method
			boolean isSaved=adminService.registerApplicant(accModel);
			if(isSaved) {
				model.addAttribute(ApplicationConstants.SUCCESS,appProps.get(ApplicationConstants.SUCCESS) );
			}
			else {
				model.addAttribute(ApplicationConstants.FAILED ,appProps.get(ApplicationConstants.FAILED) );
			}//IF
			
		}catch(Exception e) {
			logger.warn("Registration failed:"+e.getMessage());
		}
		return "accReg";
	}
	
	/**
	 * this method  is used for loading create plan form page
	 * @param model
	 * @return String
	 */
	@RequestMapping(value="/crtPln",method=RequestMethod.GET)
	public String createPlan(Model model) {
		logger.debug("AdminController: createPlan() started");
		PlanModel planModel= new PlanModel();
		model.addAttribute(planModel);
		logger.debug("AdminController: createPlan() ended");
		logger.info("AdminController: Create Plan form loaded sucessfully..");
		return "creat_plan";
	}
	
	/**
	 * this method is used to process create plan Form 
	 * @param planModel
	 * @param model
	 * @return String
	 */
	@RequestMapping(value="/crtPln",method=RequestMethod.POST)
	public String createPlan(@ModelAttribute("planModel") PlanModel planModel,Model model) {
		logger.debug("AdminController: createPlan() POST method started");
		try {
			//call registerApplicant method
			boolean isSaved=adminService.registerPlan(planModel);
			if(isSaved) {
				model.addAttribute(ApplicationConstants.SUCCESS,properties.getProperties().get(ApplicationConstants.PLAN_CREATION_SUCCESS) );
			}
			else {
				model.addAttribute(ApplicationConstants.FAILED ,properties.getProperties().get(ApplicationConstants.PLAN_CREATION_FAILURE) );
			}//IF
			
		}catch(Exception e) {
			logger.error("Registration failed:"+e.getMessage());
		}
		logger.debug("AdminController: createPlan() POST method ended");
		logger.info("AdminController: plan cration completed");
		return "creat_plan";
	}
	
	/**
	 * this method is used to get Roles
	 * @param model
	 */
	public void initForm(Model model){
		List<String> roleList=new ArrayList<String>();
		roleList.add("Admin");
		roleList.add("Case Worker");
		model.addAttribute("roleList", roleList);
	}//initForm(-)
	
	/**
	 * this method is used to check availability  of unique email
	 * @param HttpServlerRequest
	 * @param model
	 * @return
	 */
	
//	public String checkEmailValidity(HttpServletRequest req,Model model) {
//		String email=req.getParameter("emailId");
//		return adminService.findByEmail(email);
//	}//CheckEmailValidity(-,-)
	
	/**
	 * this method is used to check the availability of unique plan
	 * @param HttpServletRequest
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/crtPln/validPlan")
	public @ResponseBody String planValidate(HttpServletRequest req,Model model) {
		String plan=req.getParameter("planName");
		return adminService.checkPlan(plan);
	}

}
