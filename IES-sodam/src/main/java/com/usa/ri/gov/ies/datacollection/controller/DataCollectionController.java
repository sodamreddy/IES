package com.usa.ri.gov.ies.datacollection.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.usa.ri.gov.ies.datacollection.model.ApplicantDetailsModel;
import com.usa.ri.gov.ies.datacollection.service.DataCollectionService;

@Controller("dcConctoller")
public class DataCollectionController {
	Logger logger=LoggerFactory.getLogger(DataCollectionController.class);
	
	@Autowired(required=true)
	DataCollectionService dataCollectionService;
	
	@RequestMapping(value="/caseCreation",method=RequestMethod.GET)
	public String applicantDetails(Model model) {
		logger.debug("DataCollectionController:Application Search page loaded sussefully");	
		return "case_creation";
	}
	
	@RequestMapping(value="/caseCreation/appNoIsAvailable")
	public boolean applicationNo(HttpServletRequest req) {
		logger.debug("DataCollectionController: applicationNo() has begin");
		int appNo= Integer.parseInt(req.getParameter("appNo"));
		boolean status=dataCollectionService.isAppNoAvailable( appNo);	
		logger.debug("DataCollectionController: applicationNo() has ended");
		return status;
	}
	
	@RequestMapping(value="/caseCreation/getAppNos")
	public List<Integer> getApplicationIds(HttpServletRequest req) {
		logger.debug("DataCollectionController: getApplicationIds() has begin");
		List<Integer> listAppNos = dataCollectionService.applicationIds();
		logger.debug("DataCollectionController: getApplicationIds() has ended");
		return listAppNos;
	}
}
