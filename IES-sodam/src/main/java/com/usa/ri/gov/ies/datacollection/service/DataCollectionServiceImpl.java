package com.usa.ri.gov.ies.datacollection.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usa.ri.gov.ies.ar.entity.ApplicationEntity;
import com.usa.ri.gov.ies.ar.repository.ApplicationRegRepository;
@Service
public class DataCollectionServiceImpl implements DataCollectionService {
	Logger logger = LoggerFactory.getLogger(DataCollectionServiceImpl.class);
	@Autowired(required = true)
	ApplicationRegRepository applicationRegRepository;

	@Override
	public boolean isAppNoAvailable(int appNo) {
		logger.debug("DataCollectionServiceImpl: isAppNoAvailable() has bigin");
		Optional<ApplicationEntity> entity;
		entity=applicationRegRepository.findById(appNo);
		logger.debug("DataCollectionServiceImpl: isAppNoAvailable() ended");
		return entity.isPresent();
	}

	@Override
	public List<Integer> applicationIds() {
		logger.debug("DataCollectionServiceImpl: applicationIds() has bigin");
		List<ApplicationEntity> listEntity;
		List<Integer> listAppNos=new ArrayList<Integer>();
		listEntity=applicationRegRepository.findAll();
		for (ApplicationEntity entity : listEntity) {
			listAppNos.add(entity.getAppNo());
		}
		return listAppNos;
	}

}
