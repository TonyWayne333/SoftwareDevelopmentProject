package com.development;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

@Service("studentService")
@Transactional
public class StudentService {
	static String db_name = "softwaredevelopmentproject", db_collection = "student";
 
    // Fetch all users from the mongo database.
    public List<Student> getAll() {
        List<Student> student_list = new ArrayList<Student>();
        DBCollection coll = MongoFactory.getCollection(db_name, db_collection);
 
        // Fetching cursor object for iterating on the database records.
        DBCursor cursor = coll.find();  
        while(cursor.hasNext()) {           
            DBObject dbObject = cursor.next();
 
            Student student = new Student();
            student.setStudentId(dbObject.get("studentId").toString());
            student.setFirstName(dbObject.get("firstName").toString());
            student.setLastName(dbObject.get("lastName").toString());
            student.setPresence(dbObject.get("presence").toString());
            student.setImageName(dbObject.get("imageName").toString());
            student.setEmailId(dbObject.get("emailId").toString());
            student.setPhone(dbObject.get("phone").toString());
 
            // Adding the user details to the list.
            student_list.add(student);
        }
        return student_list;
    }
 
    public List<Student> getAllPresent() {
        List<Student> student_list = new ArrayList<Student>();
        DBCollection coll = MongoFactory.getCollection(db_name, db_collection);
 
        DBObject where_query = new BasicDBObject();
        where_query.put("presence", "True");
        // Fetching cursor object for iterating on the database records.
        DBCursor cursor = coll.find(where_query);  
        while(cursor.hasNext()) {           
            DBObject dbObject = cursor.next();
 
            Student student = new Student();
            student.setStudentId(dbObject.get("studentId").toString());
            student.setFirstName(dbObject.get("firstName").toString());
            student.setLastName(dbObject.get("lastName").toString());
            student.setPresence(dbObject.get("presence").toString());
            student.setImageName(dbObject.get("imageName").toString());
            student.setEmailId(dbObject.get("emailId").toString());
            student.setPhone(dbObject.get("phone").toString());
 
            // Adding the user details to the list.
            student_list.add(student);
        }
        return student_list;
    }
    // Add a new user to the mongo database.
    public Boolean add(Student student) {
        boolean output = false;
        try {           
            DBCollection coll = MongoFactory.getCollection(db_name, db_collection);
            System.out.println("Inside student add");
            if(findUserId(student.getStudentId())) {
                // Create a new object and add the new user details to this object.
                BasicDBObject doc = new BasicDBObject();
                doc.put("studentId", student.getStudentId()); 
                doc.put("firstName", student.getFirstName());   
                doc.put("lastName", student.getLastName());
                doc.put("presence", student.getPresence());
                doc.put("imageName", student.getImageName());
                doc.put("emailId", student.getEmailId());
                doc.put("phone", student.getPhone());
     
                // Save a new user to the mongo collection.
                coll.insert(doc);
                output = true;
            }

        } catch (Exception e) {
            output = false;
            System.out.println("An error occurred while saving a new user to the mongo database: " + e);            
        }
        return output;
    }
    public boolean findUserId(String id) {
        DBCollection coll = MongoFactory.getCollection(db_name, db_collection);
 
        // Fetching the record object from the mongo database.
        DBObject where_query = new BasicDBObject();
        where_query.put("studentId", id);
 
        DBObject dbo = coll.findOne(where_query);   
        System.out.println("Inside student search: ");
        
        if(dbo == null) {
        	return true;
        }else {
        	return false;
        }
    }
    public boolean update() {
        DBCollection coll = MongoFactory.getCollection(db_name, db_collection);
 
        // Fetching the record object from the mongo database.
        DBObject update_query = new BasicDBObject();
        update_query.put("presence", false);
        coll.update(new BasicDBObject(), update_query, false, true);
        
        return true;
    }

}
