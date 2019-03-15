package com.usa.ri.gov.ies.properties;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;



/**
 * this class store all applications properties(custom) into map object
 * 
 * @author sodam
 *
 */
@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "ies")
public class AppProperties {
	
	private Map<String, String> properties = new HashMap<String, String>();

	public Map<String, String> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}

}