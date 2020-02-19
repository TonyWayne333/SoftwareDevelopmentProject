package com.development;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

@Service("professorService")
@Transactional
public class ProfessorService {
	static String db_name = "softwaredevelopmentproject", db_collection = "professor";
    
    // Fetch all users from the mongo database.
    public List getAll() {
        List professor_list = new ArrayList();
        DBCollection coll = MongoFactory.getCollection(db_name, db_collection);
 
        // Fetching cursor object for iterating on the database records.
        DBCursor cursor = coll.find();  
        while(cursor.hasNext()) {           
            DBObject dbObject = cursor.next();
 
            Professor professor = new Professor();
            professor.setEmailId(dbObject.get("emailId").toString());
            professor.setFirstName(dbObject.get("firstName").toString());
            professor.setLastName(dbObject.get("lastName").toString());
            professor.setPassword(dbObject.get("password").toString());
            professor.setPhone(dbObject.get("phone").toString());
            // Adding the user details to the list.
            professor_list.add(professor);
        }
        return professor_list;
    }
 
    // Add a new user to the mongo database.
    public Boolean add(Professor professor) {
        boolean output = false;
        try {           
            DBCollection coll = MongoFactory.getCollection(db_name, db_collection);
 
            // Create a new object and add the new user details to this object.
            BasicDBObject doc = new BasicDBObject();
            doc.put("emailId", professor.getEmailId()); 
            doc.put("firstName", professor.getFirstName());   
            doc.put("lastName", professor.getLastName());   
            doc.put("password", professor.getPassword());   
            doc.put("phone", professor.getPhone());   
 
            // Save a new user to the mongo collection.
            coll.insert(doc);
            output = true;
        } catch (Exception e) {
            output = false;
        }
        return output;
    }
    
    // Fetching a particular record from the mongo database.
    private DBObject getDBObject(String id) {
        DBCollection coll = MongoFactory.getCollection(db_name, db_collection);
 
        // Fetching the record object from the mongo database.
        DBObject where_query = new BasicDBObject();
 
        // Put the selected user_id to search.
        where_query.put("emailId", id);
        return coll.findOne(where_query);
    }
    
    // Update the selected user in the mongo database.
    public Boolean edit(Professor professor) {
        boolean output = false;
        try {
            // Fetching the user details.
            BasicDBObject existing = (BasicDBObject) getDBObject(professor.getEmailId());
 
            DBCollection coll = MongoFactory.getCollection(db_name, db_collection);
 
            // Create a new object and assign the updated details.
            BasicDBObject edited = new BasicDBObject();
            edited.put("emailId", professor.getEmailId()); 
            edited.put("firstName", professor.getFirstName());   
            edited.put("lastName", professor.getLastName());   
            edited.put("password", professor.getPassword());   
            edited.put("phone", professor.getPhone());   
 
            // Update the existing user to the mongo database.
            coll.update(existing, edited);
            output = true;
        } catch (Exception e) {
            output = false;
        }
        return output;
    }

    // Fetching a single user details from the mongo database.
    public Professor findUserId(String id) {
        Professor p = new Professor();
        DBCollection coll = MongoFactory.getCollection(db_name, db_collection);
 
        // Fetching the record object from the mongo database.
        DBObject where_query = new BasicDBObject();
        where_query.put("emailId", id);
 
        DBObject dbo = coll.findOne(where_query);       
        p.setEmailId(dbo.get("emailId").toString());
        p.setFirstName(dbo.get("firstName").toString());
        p.setLastName(dbo.get("lastName").toString());
        p.setPassword(dbo.get("password").toString());
        p.setPhone(dbo.get("phone").toString());
 
        // Return user object.
        return p;
    }
}
