package io.mosip.pmp.ruv.validate;

import java.util.ArrayList;
import java.util.Map;

public class Validator {
	
	public boolean valiadte(){
		Map<ResultSet.resultSet,ArrayList<String>> resourcesResults = 
    			ApiUrlValidation.ValidateMispResources();
    	
    	if(resourcesResults.containsKey(ResultSet.resultSet.notValid)){
    		return false;
    	}
    	
    	return true;
	}

}
