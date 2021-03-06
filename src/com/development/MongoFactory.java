package com.development;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;

public class MongoFactory {
	private static MongoClient mongo;
	 
    private MongoFactory() {
    	
    }
 
    public static MongoClient getMongo() {    
        if (mongo == null) {
        	
            try {
            	System.out.println("Inside getMongo");
                mongo = new MongoClient(new MongoClientURI("mongodb://admin:adminpassword@neelmongoserver-shard-00-00-kwu6r.mongodb.net:27017,neelmongoserver-shard-00-01-kwu6r.mongodb.net:27017,neelmongoserver-shard-00-02-kwu6r.mongodb.net:27017/test?ssl=true&replicaSet=NeelMongoServer-shard-0&authSource=admin&retryWrites=true&w=majority"));  
            	System.out.println("Database connected");
            } catch (MongoException ex) {
            	System.out.println("Inside Exception");
                System.out.println(ex);
            }
        }
        return mongo;
    }
 
    public static DB getDB(String db_name) { 
    	System.out.println("Inside getDB");
        return getMongo().getDB(db_name);
    }

    public static DBCollection getCollection(String db_name, String db_collection) {
    	System.out.println("inside getCollection");
        return getDB(db_name).getCollection(db_collection);
    }
}
