package com.vehicle.rentservice.facade.api;

import com.vehicle.rentservice.model.dto.SearchCriteriaData;
import com.vehicle.rentservice.model.dto.SearchEntity;
import com.vehicle.rentservice.model.dto.SearchResult;

public interface SearchCriteriaFacadeService {
	
	SearchCriteriaData getSearchVariants();
	
	SearchResult searchForVehicles(SearchEntity searchEntity, Integer page);
	
}
