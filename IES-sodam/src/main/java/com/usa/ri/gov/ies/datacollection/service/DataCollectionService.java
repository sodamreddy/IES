package com.usa.ri.gov.ies.datacollection.service;

import java.util.List;

public interface DataCollectionService {

	public boolean isAppNoAvailable(int appNo);
	public List<Integer> applicationIds();

}
