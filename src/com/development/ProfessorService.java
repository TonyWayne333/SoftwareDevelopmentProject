package com.development;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

@Service("professorService")
@Transactional
public class ProfessorService {
	static String db_name = "softwaredevelopmentproject", db_collection = "professor";
 
    public Boolean add(Professor professor) {
        boolean output = false;
        try {           
            DBCollection coll = MongoFactory.getCollection(db_name, db_collection);
 
            if(findUserById(professor.getEmailId())) {
                BasicDBObject doc = new BasicDBObject();
                doc.put("emailId", professor.getEmailId()); 
                doc.put("firstName", professor.getFirstName());   
                doc.put("lastName", professor.getLastName());   
                doc.put("password", professor.getPassword());   
                doc.put("phone", professor.getPhone());   
     
                coll.insert(doc);
                output = true;
            }

        } catch (Exception e) {
            output = false;
        }
        return output;
    }
    
    private DBObject getDBObject(String id) {
        DBCollection coll = MongoFactory.getCollection(db_name, db_collection);
 
        DBObject where_query = new BasicDBObject();
        where_query.put("emailId", id);
        return coll.findOne(where_query);
    }
    
    public Boolean edit(Professor professor) {
        boolean output = false;
        try {
            BasicDBObject existing = (BasicDBObject) getDBObject(professor.getEmailId());
 
            DBCollection coll = MongoFactory.getCollection(db_name, db_collection);
            BasicDBObject edited = new BasicDBObject();
            edited.put("emailId", professor.getEmailId()); 
            edited.put("firstName", professor.getFirstName());   
            edited.put("lastName", professor.getLastName());   
            edited.put("password", professor.getPassword());   
            edited.put("phone", professor.getPhone());   
            coll.update(existing, edited);
            output = true;
        } catch (Exception e) {
            output = false;
        }
        return output;
    }

    public Professor findUserId(String id) {
        Professor p = new Professor();
        DBCollection coll = MongoFactory.getCollection(db_name, db_collection);
        DBObject where_query = new BasicDBObject();
        where_query.put("emailId", id);
 
        DBObject dbo = coll.findOne(where_query);       
        p.setEmailId(dbo.get("emailId").toString());
        p.setFirstName(dbo.get("firstName").toString());
        p.setLastName(dbo.get("lastName").toString());
        p.setPassword(dbo.get("password").toString());
        p.setPhone(dbo.get("phone").toString());
 
        return p;
    }
    
    public boolean findUserById(String id) {
        DBCollection coll = MongoFactory.getCollection(db_name, db_collection);
        DBObject where_query = new BasicDBObject();
        where_query.put("emailId", id);
 
        DBObject dbo = coll.findOne(where_query);   
        System.out.println("Inside professor search: ");
        
        if(dbo == null) {
        	return true;
        }else {
        	return false;
        }
    }
}
