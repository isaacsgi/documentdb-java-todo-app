package com.microsoft.azure.documentdb.sample.dao;

import com.microsoft.azure.documentdb.ConnectionPolicy;
import com.microsoft.azure.documentdb.ConsistencyLevel;
import com.microsoft.azure.documentdb.DocumentClient;

public class DocumentClientFactory {
    private static final String HOST = "https://gicftodos.documents.azure.com:443/";
    private static final String MASTER_KEY = "yZeOvKUrfbdN6Yu0sXKf4mVe09OQIcNcaTTEMJsba42iEY0uzj0BwU2r6f3JdeKQIpcbn4yQ2sU1Dqu7QUK1NA==";

    private static DocumentClient documentClient;

    public static DocumentClient getDocumentClient() {
        if (documentClient == null) {
            documentClient = new DocumentClient(HOST, MASTER_KEY,
                    ConnectionPolicy.GetDefault(), ConsistencyLevel.Session);
        }
 
        return documentClient;
    }

}
