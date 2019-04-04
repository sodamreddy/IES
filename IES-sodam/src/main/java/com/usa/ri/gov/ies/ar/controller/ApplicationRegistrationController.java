package com.usa.ri.gov.ies.ar.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.usa.ri.gov.ies.ar.model.ApplicationModel;
import com.usa.ri.gov.ies.ar.service.ApplicationRegistrationService;
import com.usa.ri.gov.ies.constants.ApplicationConstants;

@Controller
public class ApplicationRegistrationController {
	Logger logger= LoggerFactory.getLogger(ApplicationRegistrationController.class);
	@Autowired(required=true)
	ApplicationRegistrationService service;
	@RequestMapping(value = "/appReg", method = RequestMethod.GET)
	public String appRegForm(Model model) {
		logger.debug("ArController::appRegForm() started");
		// Creating empty model object
		ApplicationModel appModel = new ApplicationModel();

		// add cwModel object to Model scope
		model.addAttribute("appModel", appModel);

		logger.debug("ArController::appRegForm() ended");
		logger.info("Applicant Reg Form loaded Successfully");
		return "application_registration";
	}

	/**
	 * This method is used to register applicant with given values
	 * 
	 * @param appAccModel
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/appReg", method = RequestMethod.POST)
	public String appReg(@ModelAttribute("appModel") ApplicationModel appModel, Model model) {
		try {
			logger.debug("ArController::user account creation started");

			// call Service layer method
			Map<Boolean, String> map = service.registerApplicationRecord(appModel);

			//Map<String, String> map = appProperties.getProperties();
			if (!map.isEmpty()) {
				// Display success message
				if(map.containsKey(true))
					model.addAttribute(ApplicationConstants.SUCCESS, map.get(true));
				else 
					// Display failure message
					model.addAttribute(ApplicationConstants.FAILED, map.get(false));
			}
			
			logger.debug("ArController::user account creation ended");
			logger.info("ArController::User Account creation completed successfully");
		} catch (Exception e) {
			logger.error("User Account Creation Failed :: " + e.getMessage());
		}
		return "application_registration";
}
}
