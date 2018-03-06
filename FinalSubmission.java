package com.amazonaws.lambda.demo;

import java.util.Iterator;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.QueryOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;

public class FinalSubmission implements RequestHandler<Object, String> {
	static ClientConfig config;
	static Client client;
	private DynamoDB dynamoDb;
	private Regions REGION = Regions.US_EAST_1;

	@Override
	public String handleRequest(Object input, Context context) {
		config = new DefaultClientConfig();
		client = Client.create(config);
		context.getLogger().log("Input: " + input);
		String result = null;
		
		try {
			JSONObject obj = new JSONObject(input.toString());
			result = validate(obj);
			System.out.println(result);
			
			result = getTemplate(obj);
			System.out.println(result);
			
			result = createJob(obj, result);
			obj.put("jobId", result);
			
			result = createAddress(obj);
			System.out.println(result);
			
			result = returnAddressID(obj,result);
			obj.put("addressId", result);
			
			result = updatejob(obj);
			System.out.println(result);
			
			result = submitjob(obj);
			System.out.println(result);
						
		} catch (JSONException e) {

			e.printStackTrace();
		}
		return result;
	}

	// Authenticates user before submitting Mailing list
	public static String validate(JSONObject obj) {

		try {
			String username = obj.getString("username");
			String password = obj.getString("password");
			// Lambda Rest API URL for Authentication
			String authenticationUrl = "https://j9nbtlj2gl.execute-api.us-east-1.amazonaws.com/Test/authenticate";
			client.addFilter(new HTTPBasicAuthFilter(username, password));
			ClientResponse response = getResponse(authenticationUrl, MediaType.APPLICATION_JSON, false, obj.toString());
			String returnVal = response.getEntity(String.class);
			return returnVal;
		} catch (JSONException e) {
			throw new RuntimeException("Exception in Authentication : " + e.getMessage());
		}

	}

	public String getTemplate(JSONObject obj) {
		String template = null;
		try {

			String v_id = obj.getString("campaignid");
			initDynamoDbClient();

			Table tableToQuery = dynamoDb.getTable("FinalCampaign");
			QuerySpec spec = new QuerySpec()

					.withKeyConditionExpression("id = :v_id")

					.withValueMap(new ValueMap().withString(":v_id", v_id));

			ItemCollection<QueryOutcome> items = tableToQuery.query(spec);

			Iterator<Item> iter2 = items.iterator();

			if (iter2.hasNext()) {
				Item item = iter2.next();
				template = item.getString("jobid");
			}
		} catch (JSONException e) {
			throw new RuntimeException("Exception in Authentication : " + e.getMessage());
		}
		return template;
	}

	private void initDynamoDbClient() {
		AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion(REGION).build();
		this.dynamoDb = new DynamoDB(client);
	}

	private String createJob(JSONObject obj, String jobTemplate) {

		try {
			String username = obj.getString("username");
			String password = obj.getString("password");
			JSONObject jobargs = new JSONObject();
			jobargs.put("username", username);
			jobargs.put("password", password);
			jobargs.put("templateName", jobTemplate);

			String createjoburl = "https://s92t4q8of8.execute-api.us-east-1.amazonaws.com/Test/jobtemplate";
			client.addFilter(new HTTPBasicAuthFilter(username, password));
			ClientResponse response = getResponse(createjoburl, MediaType.APPLICATION_JSON, false, jobargs.toString());
			String returnVal = response.getEntity(String.class);
			return returnVal.replaceAll("\"", "");
		} catch (JSONException e) {
			throw new RuntimeException("Exception in Authentication : " + e.getMessage());
		}

	}

	private String createAddress(JSONObject obj) {

		try {
			String username = obj.getString("username");
			String password = obj.getString("password");
			JSONObject addressargs = new JSONObject();
			addressargs.put("username", username);
			addressargs.put("password", password);
			addressargs.put("eventid", obj.getString("eventid"));
			addressargs.put("campaignid", obj.getString("campaignid"));
			addressargs.put("count", obj.getString("count"));

			String createaddressurl = "https://9g63hjrur7.execute-api.us-east-1.amazonaws.com/Test/returnaddress";
			client.addFilter(new HTTPBasicAuthFilter(username, password));
			ClientResponse response = getResponse(createaddressurl, MediaType.APPLICATION_JSON, false, addressargs.toString());
			String returnVal = response.getEntity(String.class);
			return returnVal.replace("\\", "").replace("\"{","{").replace("}\"", "}");
		} catch (JSONException e) {
			throw new RuntimeException("Exception in Authentication : " + e.getMessage());
		}

	}
	
	private String returnAddressID(JSONObject obj, String addressesJSONStr) {

		try {
			String username = obj.getString("username");
			String password = obj.getString("password");
			
			
			org.json.JSONObject addresses = new org.json.JSONObject(addressesJSONStr);
			org.json.JSONArray addressesArray = addresses.getJSONArray("addresses");
			
			JSONArray addressesArrayArgs = new JSONArray();;
			for (int i = 0; i < addressesArray.length(); i++) {
				org.json.JSONObject addressObj = addressesArray.getJSONObject(i);
				
				JSONObject addressArgs = new JSONObject();
				addressArgs.put("firstName", "Resident");
				addressArgs.put("lastName", "Customer");
				addressArgs.put("organization", "Our Neighborhood");
				addressArgs.put("address1", addressObj.get("address1"));
				addressArgs.put("address2", "XXX");
				addressArgs.put("address3", "XXX");
				addressArgs.put("city", addressObj.get("city"));
				addressArgs.put("state", addressObj.get("state"));
				addressArgs.put("zip", addressObj.get("zip"));
				addressArgs.put("countryNonUS", "US");
				
				addressesArrayArgs.put(addressArgs);
				
				}
			
			JSONObject addressargsid = new JSONObject();
			addressargsid.put("username", username);
			addressargsid.put("password", password);
			addressargsid.put("addresses", addressesArrayArgs);
			
			String addressidurl = "https://hqnfwih6z3.execute-api.us-east-1.amazonaws.com/Test/createaddress";
			client.addFilter(new HTTPBasicAuthFilter(username, password));
			ClientResponse response = getResponse(addressidurl, MediaType.APPLICATION_JSON, false, addressargsid.toString());
			String returnVal = response.getEntity(String.class);
			return returnVal.replace("\\", "").replaceAll("\"", "");
		} catch (JSONException e) {
			throw new RuntimeException("Exception in Authentication : " + e.getMessage());
		}

	}

	private String updatejob(JSONObject obj) {
		
		try {
			String username = obj.getString("username");
			String password = obj.getString("password");
			JSONObject jobargs = new JSONObject();
			jobargs.put("username", username);
			jobargs.put("password", password);
			jobargs.put("jobId",  obj.getString("jobId"));
			jobargs.put("addressId", obj.getString("addressId"));
			
			String createjoburl = "https://vztei6i1pd.execute-api.us-east-1.amazonaws.com/Test/updatejob";
			client.addFilter(new HTTPBasicAuthFilter(username, password));
			ClientResponse response = getResponse(createjoburl, MediaType.APPLICATION_JSON, false, jobargs.toString());
			String returnVal = response.getEntity(String.class);
			return returnVal.replace("\\", "").replaceAll("\"", "");
		} catch (JSONException e) {
			throw new RuntimeException("Exception in Authentication : " + e.getMessage());
		}
	}
	
private String submitjob(JSONObject obj) {
		
		try {
			String username = obj.getString("username");
			String password = obj.getString("password");
			JSONObject jobargs = new JSONObject();
			jobargs.put("username", username);
			jobargs.put("password", password);
			jobargs.put("billingType", "Credit Card");
			jobargs.put("billingName", "Click2Mail");
			jobargs.put("billingCompany", "C2M");
			jobargs.put("billingAddress1", "3013 10th Street N");
			jobargs.put("billingAddress2", "XXX");
			jobargs.put("billingAddress3", "XXX");
			jobargs.put("billingCity", "Arlington");
			jobargs.put("billingState", "VA");
			jobargs.put("billingZip", "22030");
			jobargs.put("billingCcType", "VI");
			jobargs.put("billingNumber", "4111111111111111");
			jobargs.put("billingMonth", "08");
			jobargs.put("billingYear", "2018");
			jobargs.put("billingCvv", "123");
			
			jobargs.put("jobId",  obj.getString("jobId"));
			
			
			String createjoburl = "https://jgzgw5gbdl.execute-api.us-east-1.amazonaws.com/Test/submitjob";
			client.addFilter(new HTTPBasicAuthFilter(username, password));
			ClientResponse response = getResponse(createjoburl, MediaType.APPLICATION_JSON, false, jobargs.toString());
			String returnVal = response.getEntity(String.class);
			return returnVal.replace("\\", "").replaceAll("\"", "");
		} catch (JSONException e) {
			throw new RuntimeException("Exception in Authentication : " + e.getMessage());
		}
	}
	
	
	public static ClientResponse getResponse(String url, String contentType, boolean isGetMethod, Object params) {
		try {

			WebResource resource = client.resource(UriBuilder.fromUri(url).build());
			ClientResponse response = null;
			if (isGetMethod) {
				response = resource.get(ClientResponse.class);
			} else {
				response = resource.type(contentType).post(ClientResponse.class, params);
			}
			return response;
		} catch (Exception e) {
			throw new RuntimeException("Exception in Submit Job rest api call. Error message : " + e.getMessage());
		}

	}
}
