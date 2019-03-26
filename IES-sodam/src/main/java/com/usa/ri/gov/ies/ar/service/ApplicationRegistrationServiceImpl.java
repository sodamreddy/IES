package com.usa.ri.gov.ies.ar.service;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.usa.ri.gov.ies.ar.entity.ApplicationEntity;
import com.usa.ri.gov.ies.ar.model.ApplicationModel;
import com.usa.ri.gov.ies.ar.model.SSNProfile;
import com.usa.ri.gov.ies.ar.repository.ApplicationRegRepository;
import com.usa.ri.gov.ies.constants.ApplicationConstants;
import com.usa.ri.gov.ies.properties.AppProperties;

@Service
public class ApplicationRegistrationServiceImpl implements ApplicationRegistrationService {
	Logger logger = LoggerFactory.getLogger(ApplicationRegistrationServiceImpl.class);
	@Autowired(required = true)
	ApplicationRegRepository repository;
	@Autowired(required = true)
	AppProperties properties;

	@Override
	public Map<Boolean, String> registerApplicationRecord(ApplicationModel appModel) {
		logger.debug("ApplicationRegistrationServiceImpl:registerApplicationRecord() started");
		RestTemplate template = new RestTemplate();
		Map<Boolean, String> map = new HashMap<Boolean, String>();
		ApplicationEntity entity = new ApplicationEntity();

		try {
			entity = repository.findById(appModel.getSsn()).get();
			if (entity == null) {
				URI uri = new URI("http://localhost:2122/SSN/validateSSN/" + appModel.getSsn());
				ResponseEntity<SSNProfile> responseEntity = template.getForEntity(uri, SSNProfile.class);
				int statusCode = responseEntity.getStatusCodeValue();
				SSNProfile profile = responseEntity.getBody();
				if (statusCode == 200) {
					if (profile.getState().equalsIgnoreCase(ApplicationConstants.STATE_RI))
						;
					// covert Model to Entity
					entity = new ApplicationEntity();
					BeanUtils.copyProperties(appModel, entity);
					entity = repository.save(entity);
					if (entity.getAppNo() != null) {
						map.put(true, (properties.getProperties().get(ApplicationConstants.APPLICANT_REG_SUCCESS)
								+ entity.getAppNo()));
						logger.debug("***ArService::registerApplicant() method ended***");
						logger.info("***ArService::Registration Successful***");
						return map;
					}
				} else {
					map.put(false, properties.getProperties().get(ApplicationConstants.UN_AUTHORISED_APPLICANT));
					logger.debug("***ArService::registerApplicant() method ended***");
					logger.info("***ArService::Applicant is UnAuthorised to apply***");
					return map;
				}
			} else {
				map.put(true,
						properties.getProperties().get(ApplicationConstants.APPLICANT_ALREADY_REG) + entity.getAppNo());
				logger.debug("***ArService::registerApplicant() method ended***");
				logger.info("***ArService::Applicant is Already Registered***");
				return map;
			}
		} catch (Exception e) {
			if (e instanceof HttpClientErrorException.BadRequest) {
				logger.error("***ArService::registerApplicant() method error:", e);
				map.put(false, properties.getProperties().get(ApplicationConstants.INVALID_SSN_MSG));
				return map;
			}
		}

		logger.debug("***ArService::registerApplicant() method ended with error***");
		map.put(false, properties.getProperties().get(ApplicationConstants.INTERNAL_PROBLEM));
		return map;
	}
}
