package com.usa.ri.gov.ies.admin.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usa.ri.gov.ies.admin.entity.AppAccountEntity;
import com.usa.ri.gov.ies.admin.model.AppAccountModel;
import com.usa.ri.gov.ies.admin.repositary.AppAccountRepository;
import com.usa.ri.gov.ies.constants.ApplicationConstants;
import com.usa.ri.gov.ies.properties.AppProperties;
import com.usa.ri.gov.ies.util.EmailUtil;
import com.usa.ri.gov.ies.util.PasswordUtil;

@Service("adminService")
public class AppAccountServiceImpl implements AppAccountService {
	private static Logger logger = LoggerFactory.getLogger(AppAccountServiceImpl.class);
	@Autowired(required=true)
	private EmailUtil emailUtil;

	@Autowired(required = true)
	private AppAccountRepository appAccountRepository;
	
	@Autowired(required=true)
	private AppProperties properties;

	/**
	 * this method is registers record into app_account table
	 */
	@Override
	public boolean registerApplicant(AppAccountModel AppAccount) {
		logger.debug("AdminServiceImpl: registerApplicant method Started");
		String encrypted ;
		AppAccountEntity entity= new AppAccountEntity();
		// copying model obj values into enitity obj
		BeanUtils.copyProperties(AppAccount, entity);
		// encrypt password
		encrypted = PasswordUtil.encrypt(AppAccount.getPassword());
		// set the encrypted password in entity
		entity.setPassword(encrypted);
		// set the status active
		entity.setActiveSw(ApplicationConstants.ACTIVE_SW);
		// save entity record into table
		entity = appAccountRepository.save(entity);
		
		try {
			String fileName=properties.getProperties().get(ApplicationConstants.REG_EMAIL_FILE_NAME);
			String subject= properties.getProperties().get(ApplicationConstants.REG_EMAIL_SUBJECT);
			String text=getEmailBodyContent(AppAccount,fileName);
			emailUtil.sendEmail(entity.getEmailId(),subject,text);
		}catch(Exception e) {
			logger.warn(" AdminService: registerApplicant() "+e.getMessage());
		}
		logger.debug("AdminServiceImpl: registerApplicant method Ended");
		logger.info("AdminService: registerApplicant Executed");
		return (entity.getAppId() > 0) ? true : false;
	}// registerApplicant

	public String getEmailBodyContent(AppAccountModel accModel, String fileName) throws IOException {

		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		StringBuffer body = new StringBuffer();
		String line = reader.readLine();
		while (line != null) {
			if (line != null && !"".equals(line) && "<br/>".equals(line)) {
				// process
				if (line.contains("USER_NAME")) {
					line = line.replace("USER_NAME", accModel.getFirstName() + " " + accModel.getLastName());
				}
				if (line.contains("APP_URL")) {
					line = line.replace("APP_URL", "<a href='http://localhost:7070/IES/'>IES URL</a>");
				}
				if (line.contains("APP_USER_EMAIL")) {
					line = line.replace("APP_USER_EMAIL", accModel.getEmailId());
				}
				if (line.contains("APP_USER_PWD")) {
					line = line.replace("APP_USER_PWD", accModel.getPassword());
				}
				// add processed to body
				body.append(line);
			} // if
			line = reader.readLine();
		}
		return body.toString();
	}
	
	@Override
	public String findByEmail(String email) {
		AppAccountEntity entity=appAccountRepository.checkEmail(email);
	
		return (entity.getEmailId()==null)?"Unique":"Duplicate";
	}
}
