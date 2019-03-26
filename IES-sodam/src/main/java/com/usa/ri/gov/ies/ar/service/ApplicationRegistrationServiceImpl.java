package com.usa.ri.gov.ies.ar.service;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.usa.ri.gov.ies.ar.entity.ApplicationEntity;
import com.usa.ri.gov.ies.ar.model.ApplicationModel;
import com.usa.ri.gov.ies.ar.model.SSNProfile;
import com.usa.ri.gov.ies.ar.repository.ApplicationRegRepository;
import com.usa.ri.gov.ies.properties.AppProperties;

@Service
public class ApplicationRegistrationServiceImpl implements ApplicationRegistrationService {
	Logger logger=LoggerFactory.getLogger(ApplicationRegistrationServiceImpl.class);
	@Autowired(required=true)
	ApplicationRegRepository repository;
	@Autowired(required=true)
	AppProperties properties;
	
	@Override
	public String registerApplicationRecord(ApplicationModel appModel) {
		logger.debug("ApplicationRegistrationServiceImpl:registerApplicationRecord() started");
		RestTemplate template =new RestTemplate();
		Map<Boolean, String> map= new HashMap<Boolean,String>();
		ApplicationEntity entity= new ApplicationEntity();
		
		try{
			Optional<ApplicationEntity> optionalEntity = repository.findById(appModel.getSsn());
			if(optionalEntity.isPresent()==false) {
				URI uri= new URI("http://localhost:2122/SSN/validateSSN/"+appModel.getSsn());
				ResponseEntity<SSNProfile> responseEntity= template.getForEntity(uri, SSNProfile.class);
			}
		}
		
		
		
		return null;
	}

}
