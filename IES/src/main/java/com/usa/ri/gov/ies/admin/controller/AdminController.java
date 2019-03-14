package com.usa.ri.gov.ies.admin.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.usa.ri.gov.ies.admin.model.AppAccountModel;
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
	 * @return logicalViewName
	 */
	@RequestMapping(value="/accReg",method=RequestMethod.GET)
	public String appAccountRegForm(Model model) {
		logger.debug("AdminController: appAccountRegForm Started");
		AppAccountModel accModel= new AppAccountModel();
		//Add AppAccount to Model Scope
		model.addAttribute("accModel",accModel);
		initForm(model);
		logger.debug("AdminController: appAccountRegForm Ended");
		logger.info("AdminController: appAccountRegForm executed");
		return "accReg";
	}
	
	@RequestMapping(value="/accReg",method=RequestMethod.POST)
	public String appAccountRegForm(@ModelAttribute("accModel") AppAccountModel accModel,Model model) {
		System.out.println(accModel.toString());
		//get properties 
		Map<String,String> appProps= properties.getProperties();
		try {
			System.out.println(".....................................");
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
	
	public void initForm(Model model){
		List<String> roleList=new ArrayList<String>();
		roleList.add("Admin");
		roleList.add("Case Worker");
		model.addAttribute("roleList", roleList);
	}

}
