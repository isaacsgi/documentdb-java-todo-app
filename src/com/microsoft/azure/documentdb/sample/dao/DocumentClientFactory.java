package com.microsoft.azure.documentdb.sample.dao;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.azure.documentdb.ConnectionPolicy;
import com.microsoft.azure.documentdb.ConsistencyLevel;
import com.microsoft.azure.documentdb.DocumentClient;
import com.microsoft.azure.documentdb.sample.model.DocDbCredentials;

public class DocumentClientFactory {
    
    private static DocumentClient documentClient;
    private static DocDbCredentials docDbCredentials = new DocDbCredentials();

    public static DocumentClient getDocumentClient() {
    	
    	try {
    		String vcap_services = System.getenv("VCAP_SERVICES");
    		if (vcap_services != null && vcap_services.length() > 0) {
    			ObjectMapper mapper = new ObjectMapper();
    		    JsonParser jp = mapper.getFactory().createParser(vcap_services);
    		    JsonNode rootNode = mapper.readTree(jp);
    		    JsonNode documentDbNode = rootNode.get("cs-documentdb");
    		    if(documentDbNode != null){
    		    	JsonNode credentials = documentDbNode.get(0).get("credentials");
    		    	docDbCredentials.setHost(credentials.get("documentdb_host").asText());
    		    	docDbCredentials.setMasterKey(credentials.get("documentdb_key").asText());
    		    } else if(rootNode.get("user-provided") != null){
     		    	documentDbNode = rootNode.get("user-provided");
     		    	JsonNode credentials = documentDbNode.get(0).get("credentials"); 
     		    	docDbCredentials.setHost(credentials.get("documentdb_host").asText());
    		    	docDbCredentials.setMasterKey(credentials.get("documentdb_key").asText()); 
    		    }    		    
    		    if (documentClient == null) {
                    documentClient = new DocumentClient(docDbCredentials.getHost(), docDbCredentials.getMasterKey(),
                            ConnectionPolicy.GetDefault(), ConsistencyLevel.Session);
                } 
                
                return documentClient;
    		}
    		
        } catch (Exception e) {
            e.printStackTrace();
        }

        return documentClient;
    }
    
    public static DocDbCredentials getDocumentDbCredentials() {
    	
    	try {
    		String vcap_services = System.getenv("VCAP_SERVICES");
    		if (vcap_services != null && vcap_services.length() > 0) {
    			ObjectMapper mapper = new ObjectMapper();
    		    JsonParser jp = mapper.getFactory().createParser(vcap_services);
    		    JsonNode rootNode = mapper.readTree(jp);
    		    JsonNode documentDbNode = rootNode.get("documentdb");
    		    if(documentDbNode != null){
    		    	System.out.println("brokered service");
    		    	JsonNode credentials = documentDbNode.get(0).get("credentials");
    		    	docDbCredentials.setHost(credentials.get("documentdb_host").asText());
    		    	docDbCredentials.setMasterKey(credentials.get("documentdb_key").asText());
    		    	docDbCredentials.setDocumentDbName(credentials.get("documentdb_database").asText());
    		    	docDbCredentials.setDocumentDbResourceId(credentials.get("documentdb_resource_id").asText());
    		    } else if(rootNode.get("user-provided") != null){
     		    	System.out.println("user provided service");
     		    	documentDbNode = rootNode.get("user-provided");
     		    	JsonNode credentials = documentDbNode.get(0).get("credentials");
     		    	docDbCredentials.setHost(credentials.get("documentdb_host").asText());
    		    	docDbCredentials.setMasterKey(credentials.get("documentdb_key").asText());
    		    	docDbCredentials.setDocumentDbName(credentials.get("documentdb_database").asText());
    		    	docDbCredentials.setDocumentDbResourceId(credentials.get("documentdb_resource_id").asText());  		    	
    		    }
    		    System.out.println(docDbCredentials.getHost());
    		    System.out.println(docDbCredentials.getMasterKey());
    		    System.out.println(docDbCredentials.getDocumentDbName());
    		    System.out.println(docDbCredentials.getDocumentDbResourceId());
    		    
    		    if (documentClient == null) {
                    documentClient = new DocumentClient(docDbCredentials.getHost(), docDbCredentials.getMasterKey(),
                            ConnectionPolicy.GetDefault(), ConsistencyLevel.Session);
                } 
                
                return docDbCredentials;
    		}
    		
        } catch (Exception e) {
            e.printStackTrace();
        }

        return docDbCredentials;
    }

}
