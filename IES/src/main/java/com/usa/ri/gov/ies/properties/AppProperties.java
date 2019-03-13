package com.usa.ri.gov.ies.properties;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

import lombok.Getter;
import lombok.Setter;

/**
 * this class store all applications properties(custom) into map object
 * 
 * @author sodam
 *
 */
@ComponentScan
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "ies")
public class AppProperties {
	@Getter
	@Setter
	private Map<String, String> properties = new HashMap<String, String>();

	public Map<String, String> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}

}
