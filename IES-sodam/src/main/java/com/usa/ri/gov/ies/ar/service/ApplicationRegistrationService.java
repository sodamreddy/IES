package com.usa.ri.gov.ies.ar.service;

import java.util.Map;

import com.usa.ri.gov.ies.ar.model.ApplicationModel;

public interface ApplicationRegistrationService {
	public Map<Boolean, String> registerApplicationRecord(ApplicationModel appModel); 

}
