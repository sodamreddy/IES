package com.usa.ri.gov.ies.admin.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usa.ri.gov.ies.admin.entity.AppAccountEntity;
import com.usa.ri.gov.ies.admin.model.AppAccountModel;
import com.usa.ri.gov.ies.admin.repositary.AppAccountRepository;
import com.usa.ri.gov.ies.constants.ApplicationConstants;
import com.usa.ri.gov.ies.util.PasswordUtil;


@Service("adminService")
public class AppAccountServiceImpl implements AppAccountService {
	private static Logger logger = LoggerFactory.getLogger(AppAccountServiceImpl.class);

	@Autowired(required = true)
	private AppAccountRepository appAccountRepository;

	/**
	 * this method is registers record into app_account table
	 */
	@Override
	public boolean registerApplicant(AppAccountModel AppAccount) {
		logger.debug("AdminServiceImpl: registerApplicant method Started");
		String encrypted = null;
		AppAccountEntity entity = null;
		// copying model obj values into enitity obj
		BeanUtils.copyProperties(AppAccount, entity);
		// encrypt password
		encrypted = PasswordUtil.encrypt(AppAccount.getPassword());
		//set the encrypted password in entity
		entity.setPassword(encrypted);
		//set the status active
		entity.setActiveSw(ApplicationConstants.ACTIVE_SW);
		// save entity record into table
		entity = appAccountRepository.save(entity);
		logger.debug("AdminServiceImpl: registerApplicant method Ended");
		logger.info("AdminService: registerApplicant Executed");
		return (entity.getAppId()>0)?true:false;
	}
}
