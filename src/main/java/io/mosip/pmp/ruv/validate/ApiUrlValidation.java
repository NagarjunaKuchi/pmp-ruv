package io.mosip.pmp.ruv.validate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;

import io.mosip.pmp.ruv.resources.PmpResources;
import io.mosip.pmp.ruv.validate.ResultSet.resultSet;


@SuppressWarnings("deprecation")
public class ApiUrlValidation {

	public static Map<ResultSet.resultSet, ArrayList<String>> ValidateMispResources(){

		Map<ResultSet.resultSet,ArrayList<String>> resourcesResults = new HashMap<ResultSet.resultSet,ArrayList<String>>();		

		ArrayList<String> validResourcesMessages = new ArrayList<String>();
    	ArrayList<String> notValidResourcesMessages = new ArrayList<String>();
		
		Iterator<Entry<String, ArrayList<String>>> pmpResources = PmpResources.GetMispResources().entrySet().iterator();

		while(pmpResources.hasNext()){
			Map.Entry<String, ArrayList<String>> resource = (Map.Entry<String, ArrayList<String>>) pmpResources.next();
			Map<ResultSet.resultSet, ArrayList<String>> result = validateResource(resource.getKey(), resource.getValue());			
			if(result.containsKey(ResultSet.resultSet.valid)){
				validResourcesMessages.addAll(result.get(ResultSet.resultSet.valid));
			}
			
			if(result.containsKey(ResultSet.resultSet.notValid)){
				notValidResourcesMessages.addAll(result.get(ResultSet.resultSet.notValid));				
			}
		}
		
		if(!validResourcesMessages.isEmpty()){
			resourcesResults.put(resultSet.valid, validResourcesMessages);
		}
		
		if(!notValidResourcesMessages.isEmpty()){
			resourcesResults.put(resultSet.notValid, notValidResourcesMessages);			
		}
		
      return resourcesResults;
	}
	
	@SuppressWarnings("resource")
	private static Map<ResultSet.resultSet, ArrayList<String>> validateResource(String httpMethod, ArrayList<String> api_urls){
		
        Map<ResultSet.resultSet, ArrayList<String>> results = new HashMap<ResultSet.resultSet,ArrayList<String>>();
        ArrayList<String> validResourcesMessage = new ArrayList<String>();
    	ArrayList<String> notValidResourcesMessage = new ArrayList<String>();
    	
        HttpClient  client = new DefaultHttpClient();
        
        try{        	
    		
            if(httpMethod.toUpperCase().equals("GET")){
            	for(String api_url : api_urls){
            		client = new DefaultHttpClient();
            		HttpGet httpGet = new HttpGet("http://localhost:8888" + api_url);
            		HttpResponse  resp = client.execute(httpGet);

            		int statusCode = resp.getStatusLine().getStatusCode();
            		
            		Map<ResultSet.resultSet, String> result = log(statusCode, httpMethod, api_url);
	            	
	            	if(result.containsKey(ResultSet.resultSet.valid)){
	            		validResourcesMessage.add(result.get(ResultSet.resultSet.valid));
	            	}
	            	if(result.containsKey(ResultSet.resultSet.notValid)){
	            		notValidResourcesMessage.add(result.get(ResultSet.resultSet.notValid));
	            	}
            	}
            }
            
            if(httpMethod.toUpperCase().equals("HEAD")){
            	for(String api_url : api_urls){            		
            		client = new DefaultHttpClient();
            		HttpHead httpHead = new HttpHead("http://localhost:8888" + api_url);
            		HttpResponse resp = client.execute(httpHead);

            		int statusCode = resp.getStatusLine().getStatusCode();

	            	
	            	Map<ResultSet.resultSet, String> result = log(statusCode, httpMethod, api_url);
	            	
	            	if(result.containsKey(ResultSet.resultSet.valid)){
	            		validResourcesMessage.add(result.get(ResultSet.resultSet.valid));
	            	}
	            	if(result.containsKey(ResultSet.resultSet.notValid)){
	            		notValidResourcesMessage.add(result.get(ResultSet.resultSet.notValid));
	            	}
            	}	
            }

            if(httpMethod.toUpperCase().equals("POST")){
            	for(String api_url : api_urls){            		
            		client = new DefaultHttpClient();
            		HttpPost httpPost = new HttpPost("http://localhost:8888" + api_url);
            		HttpResponse  resp = client.execute(httpPost);

            		int statusCode = resp.getStatusLine().getStatusCode();
            		Map<ResultSet.resultSet, String> result = log(statusCode, httpMethod, api_url);
	            	
	            	if(result.containsKey(ResultSet.resultSet.valid)){
	            		validResourcesMessage.add(result.get(ResultSet.resultSet.valid));
	            	}
	            	if(result.containsKey(ResultSet.resultSet.notValid)){
	            		notValidResourcesMessage.add(result.get(ResultSet.resultSet.notValid));
	            	}
            	}
            	
            }

            if(httpMethod.toUpperCase().equals("PUT")){
            	for(String api_url : api_urls){
            		client = new DefaultHttpClient(); 
            		HttpPut httpPut = new HttpPut("http://localhost:8888" + api_url);
            		HttpResponse  resp = client.execute(httpPut);

            		int statusCode = resp.getStatusLine().getStatusCode();
            		Map<ResultSet.resultSet, String> result = log(statusCode, httpMethod, api_url);
	            	
	            	if(result.containsKey(ResultSet.resultSet.valid)){
	            		validResourcesMessage.add(result.get(ResultSet.resultSet.valid));
	            	}
	            	if(result.containsKey(ResultSet.resultSet.notValid)){
	            		notValidResourcesMessage.add(result.get(ResultSet.resultSet.notValid));
	            	}
            	}
            }

            if(httpMethod.toUpperCase().equals("PATCH")){
            	for(String api_url : api_urls){
            		client = new DefaultHttpClient();
            		HttpPatch httpPatch = new HttpPatch("http://localhost:8888" + api_url);
            		HttpResponse resp = client.execute(httpPatch);

            		int statusCode = resp.getStatusLine().getStatusCode();
            		Map<ResultSet.resultSet, String> result = log(statusCode, httpMethod, api_url);
	            	
	            	if(result.containsKey(ResultSet.resultSet.valid)){
	            		validResourcesMessage.add(result.get(ResultSet.resultSet.valid));
	            	}
	            	if(result.containsKey(ResultSet.resultSet.notValid)){
	            		notValidResourcesMessage.add(result.get(ResultSet.resultSet.notValid));
	            	}
            	}
            }
            
            if(httpMethod.toUpperCase().equals("DELETE")){
            	for(String api_url : api_urls){            		
            		client = new DefaultHttpClient();
            		HttpDelete httpDelete = new HttpDelete("http://localhost:8888" + api_url);
            		HttpResponse resp = client.execute(httpDelete);
            		//client.close();
            		int statusCode = resp.getStatusLine().getStatusCode();
            		
            		Map<ResultSet.resultSet, String> result = log(statusCode, httpMethod, api_url);
	            	
	            	if(result.containsKey(ResultSet.resultSet.valid)){
	            		validResourcesMessage.add(result.get(ResultSet.resultSet.valid));
	            	}
	            	if(result.containsKey(ResultSet.resultSet.notValid)){
	            		notValidResourcesMessage.add(result.get(ResultSet.resultSet.notValid));
	            	}
            	}
            }
            
            if(httpMethod.toUpperCase().equals("OPTIONS")){
            	for(String api_url : api_urls){
            		client = new DefaultHttpClient();
            		HttpOptions httpOptions = new HttpOptions("http://localhost:8888" + api_url);
            		HttpResponse resp = client.execute(httpOptions);

            		int statusCode = resp.getStatusLine().getStatusCode();

            		Map<ResultSet.resultSet, String> result = log(statusCode, httpMethod, api_url);
	            	
	            	if(result.containsKey(ResultSet.resultSet.valid)){
	            		validResourcesMessage.add(result.get(ResultSet.resultSet.valid));
	            	}
	            	if(result.containsKey(ResultSet.resultSet.notValid)){
	            		notValidResourcesMessage.add(result.get(ResultSet.resultSet.notValid));
	            	}
            	}
            }
            
        }catch(Exception e){
        	try {
        		System.out.println(e.getMessage());
        		Map<ResultSet.resultSet, String> result = log(404, httpMethod, e.getMessage());
        		notValidResourcesMessage.add(result.get(ResultSet.resultSet.notValid));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }finally{
        	try {
        		results.put(resultSet.valid, validResourcesMessage);
        		results.put(resultSet.notValid, notValidResourcesMessage);
				client.getConnectionManager().closeExpiredConnections();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
        
		return results;
	}

	
	private static Map<ResultSet.resultSet, String> log(int statusCode, String httpMethod, String api_url){
		
		Map<ResultSet.resultSet, String> results = new HashMap<ResultSet.resultSet, String>();
		
		if (statusCode != 404) {
			results.put(ResultSet.resultSet.valid,  "Resource with uri - " + httpMethod.toUpperCase()+ " : "   + api_url  + " is valid");
        	System.out.println( "Resource with uri - " + httpMethod.toUpperCase()+ " : " +  api_url  + " is valid" );
        }
        
        if (statusCode == 404) {
        	results.put(ResultSet.resultSet.notValid,  "Resource with uri - " + httpMethod.toUpperCase()+ " : " + api_url  + " is not valid");
        	System.out.println( "Resource with uri - " + httpMethod.toUpperCase()+ " : " +  api_url  + " is not valid" );
        }
        
        return results;
	}
}

	
