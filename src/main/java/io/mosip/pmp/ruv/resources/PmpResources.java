package io.mosip.pmp.ruv.resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class PmpResources {
	
	public static Map<String, ArrayList<String>> GetMispResources(){
		Map<String,ArrayList<String>> mispResources = new HashMap<String, ArrayList<String>>();
		
		ArrayList<String> postResources = new ArrayList<String>();
		postResources.add("/pmp/misps");
		postResources.add("/pmp/misps/1");
		postResources.add("/pmp/misps/1/licenseKey");
		
		ArrayList<String> getResources = new ArrayList<String>();
		getResources.add("/pmp/misps/");
		getResources.add("/pmp/misps/1");
		
		ArrayList<String> putResources = new ArrayList<String>();
		putResources.add("/pmp/misps/1");
		putResources.add("/pmp/misps/1/licenseKey");

		
		mispResources.put("POST",postResources);
		mispResources.put("GET",getResources);		
		mispResources.put("PUT",putResources);
		
		return mispResources;
		
	}

	public Map<String, String> GetPolicyResources(){
		return null;
		
	}
	
	public HashMap<String, String> GetPartnerResources(){
		return null;
		
	}
	
	public HashMap<String, String> GetPManagerResources(){
		return null;
		
	}
}
