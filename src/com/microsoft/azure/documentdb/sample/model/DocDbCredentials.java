package com.microsoft.azure.documentdb.sample.model;

public class DocDbCredentials {
    private String host;
    private String masterKey;
    private String documentDbName;
    private String documentDbResourceId;
    
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getMasterKey() {
		return masterKey;
	}
	public void setMasterKey(String masterKey) {
		this.masterKey = masterKey;
	}
	public String getDocumentDbName() {
		return documentDbName;
	}
	public void setDocumentDbName(String documentDbName) {
		this.documentDbName = documentDbName;
	}
	public String getDocumentDbResourceId() {
		return documentDbResourceId;
	}
	public void setDocumentDbResourceId(String documentDbResourceId) {
		this.documentDbResourceId = documentDbResourceId;
	}
    
    
}
