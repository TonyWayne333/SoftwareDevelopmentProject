package com.development;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Controller
public class ProjectController {
	private static EntityManagerFactory factory;
	private static EntityManager em;
	
	//mapping with signup.jsp
	@RequestMapping("/signUp")
	//method to process registration request
	   public ModelAndView signUp(HttpServletRequest request,HttpServletResponse response,HttpSession session) {
		  
		factory = Persistence.createEntityManagerFactory("SoftwareDevelopmentProject");
	    em = factory.createEntityManager();
	    em.getTransaction().begin(); 
	      Professor professor = new Professor();
	      professor.setFirstName(request.getParameter("firstName"));
	      professor.setLastName(request.getParameter("lastName"));
	      professor.setEmailId(request.getParameter("emailId"));
	      professor.setPassword(request.getParameter("password"));
	      professor.setPhone(request.getParameter("phone"));
		    em.persist(professor);
		    em.getTransaction().commit();		   
		    em.close();
		    
		    addSession(professor,session);
		
		    return new ModelAndView("classlist", "professor", professor);
	}
	
	//mapping with login.jsp,index.jsp
	@RequestMapping("/login")
	//method to process registration request
	   public ModelAndView login(HttpServletRequest request,HttpServletResponse response,HttpSession session) {
		  
		factory = Persistence.createEntityManagerFactory("SoftwareDevelopmentProject");
	    em = factory.createEntityManager();
	    em.getTransaction().begin(); 
	      
	      Query query = em.createQuery("select p from Professor p where p.emailId = '" + request.getParameter("userName")+"'");
	      Professor professor = (Professor) query.getSingleResult();
		    em.getTransaction().commit();
		    em.close();
		    if(professor.getPassword().equals(request.getParameter("password"))) {
			    addSession(professor,session);
			    return new ModelAndView("classlist", "professor", professor);
		    }else {
		    	return new ModelAndView("login");
		    }
	       
	}
	
	@RequestMapping("/classlist")
	//method to process registration request
	   public ModelAndView classlist(HttpServletRequest request,HttpServletResponse response,HttpSession session) {
		
			factory = Persistence.createEntityManagerFactory("SoftwareDevelopmentProject");
			em = factory.createEntityManager();
			em.getTransaction().begin(); 
	      
			Query query = em.createQuery("select p from Professor p where p.emailId = '" + session.getValue("professorId") +"'");
			Professor professor = (Professor) query.getSingleResult();
		    em.getTransaction().commit();
		    em.close();
		    
			return new ModelAndView("classlist", "professor", professor);
	}
	
	@RequestMapping("/Update")
	
	//method to process registration request
	   public ModelAndView update(HttpServletRequest request,HttpServletResponse response,HttpSession session) {
		  
		if(session.getValue("professorId").equals(null)) {
			return new ModelAndView("login");
		}
		factory = Persistence.createEntityManagerFactory("SoftwareDevelopmentProject");
	    em = factory.createEntityManager();
	    em.getTransaction().begin(); 

		      Professor professor = em.find(Professor.class, session.getAttribute("professorId").toString());
		      professor.setFirstName(request.getParameter("firstName"));
		      professor.setLastName(request.getParameter("lastName"));
		      professor.setEmailId(request.getParameter("emailId"));
		      professor.setPassword(request.getParameter("password"));
		      professor.setPhone(request.getParameter("phone"));
		        
			    em.persist(professor);
			    em.getTransaction().commit();
			    em.close();
	      return new ModelAndView("accountinfo","professor",professor); 
	}
	
	@RequestMapping("/register")
	//method to process registration request
	   public ModelAndView register(@RequestParam CommonsMultipartFile file,@RequestParam String studentId,
			   @RequestParam String firstName,@RequestParam String lastName,@RequestParam String emailId,@RequestParam String phone,HttpSession session) throws IOException {
		  
		
		factory = Persistence.createEntityManagerFactory("SoftwareDevelopmentProject");
	    em = factory.createEntityManager();
	    em.getTransaction().begin(); 
	      Student student = new Student();
	      student.setStudentId(studentId);
	      student.setFirstName(firstName);
	      student.setLastName(lastName);
	      student.setEmailId(emailId);
	      student.setPhone(phone);
	      //student.setPresence("false");
	      
	      ServletContext context = session.getServletContext();  
	      final String UPLOAD_DIRECTORY ="/images";  
	      String path = context.getRealPath(UPLOAD_DIRECTORY);  
	      String filename = file.getOriginalFilename();  
	    
	      System.out.println(path+" "+filename);        
	    
	      byte[] bytes = file.getBytes();  
	      System.out.println("bytes.size="+ bytes.length);
			
			if(filename.endsWith(".jpg") || filename.endsWith(".png") || filename.endsWith(".JPG") || filename.endsWith(".PNG") || filename.endsWith(".JEPG") || filename.endsWith(".jpeg")){
			    filename = studentId + filename.substring(filename.length() - 4);			
			    student.setImageName(filename);
			    System.out.println("File Name: " + filename);
			    String accessKeyId = "YOUR_ACCESS_KEY";
			    String secretAccessKey =  "YOUR_SECRET_KEY";
			    String region = "us-east-2";
			    String bucketName = "neelbucket3";
			    
			    //AWS Access Key ID and Secret Access Key
			    BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKeyId, secretAccessKey);
			   
			    //This class connects to AWS S3 for us
			    AmazonS3 s3client = AmazonS3ClientBuilder.standard().withRegion(region)
			    		.withCredentials(new AWSStaticCredentialsProvider(awsCreds)).build();
			    
			    //Specify the file's size
			    ObjectMetadata metadata = new ObjectMetadata();
			    metadata.setContentLength(bytes.length);

			    InputStream targetStream = new ByteArrayInputStream(bytes);
			    //Create the upload request, giving it a bucket name, subdirectory, filename, input stream, and metadata
			    PutObjectRequest uploadRequest = new PutObjectRequest(bucketName, filename, targetStream, metadata);
			    //Make it public so we can use it as a public URL on the internet
			    uploadRequest.setCannedAcl(CannedAccessControlList.PublicRead);
			    
			    //Upload the file. This can take a while for big files!
			    s3client.putObject(uploadRequest);
			    System.out.println("Photo uploaded");
			    	
			}
	      	      
		    em.persist(student);
		    em.getTransaction().commit();		   
		    em.close();	 
	      return new ModelAndView("login"); 
	}
	
	@RequestMapping("/photoupload")
	//method to process registration request
	   public RedirectView photoUpload(@RequestParam CommonsMultipartFile file, HttpServletResponse response) throws IOException {
		  
		RedirectView redirectView = new RedirectView();
		
		String filename = file.getOriginalFilename();   
	    
	    byte[] bytes = file.getBytes();  
	      System.out.println("bytes.size="+ bytes.length);
			
			if(filename.endsWith(".jpg") || filename.endsWith(".png") || filename.endsWith(".JPG") || filename.endsWith(".PNG") || filename.endsWith(".JEPG") || filename.endsWith(".jpeg")){
			    String accessKeyId = "YOUR_ACCESS_KEY";
			    String secretAccessKey =  "YOUR_SECRET_KEY";
			    String region = "us-east-2";
			    String bucketName = "neelbucket4";
			    
			    //AWS Access Key ID and Secret Access Key
			    BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKeyId, secretAccessKey);
			   
			    //This class connects to AWS S3 for us
			    AmazonS3 s3client = AmazonS3ClientBuilder.standard().withRegion(region)
			    		.withCredentials(new AWSStaticCredentialsProvider(awsCreds)).build();
			    
			    //Specify the file's size
			    ObjectMetadata metadata = new ObjectMetadata();
			    metadata.setContentLength(bytes.length);

			    InputStream targetStream = new ByteArrayInputStream(bytes);
			    //Create the upload request, giving it a bucket name, subdirectory, filename, input stream, and metadata
			    PutObjectRequest uploadRequest = new PutObjectRequest(bucketName, filename, targetStream, metadata);
			    //Make it public so we can use it as a public URL on the internet
			    uploadRequest.setCannedAcl(CannedAccessControlList.PublicRead);
			    
			    //Upload the file. This can take a while for big files!
			    s3client.putObject(uploadRequest);
			    System.out.println("Photo uploaded");
			   
			    redirectView.setUrl("http://127.0.0.1:5000/image_recognition/");
			    return redirectView;
			    	
			}
	      	      
	      //return new ModelAndView("uploadphoto"); 
		    redirectView.setUrl("http://127.0.0.1:9090/");
		    return redirectView;
	}
	@RequestMapping("/logout")
	   public ModelAndView logout(HttpServletRequest request,HttpServletResponse response,HttpSession session) {

		session.invalidate();
		return new ModelAndView("login");
	}
	@RequestMapping("/student")
	   public ModelAndView student(HttpServletRequest request,HttpServletResponse response) {
		return new ModelAndView("student");
	}
	@RequestMapping("/professor")
	   public ModelAndView professor(HttpServletRequest request,HttpServletResponse response) {
		return new ModelAndView("login");
	}
	
	@RequestMapping("/newProfessor")
	   public ModelAndView newProfessor(HttpServletRequest request) {
		return new ModelAndView("signup");
	}
	@RequestMapping("/uploadphoto")
	   public ModelAndView uploadPhoto(HttpServletRequest request,HttpServletResponse response,HttpSession session) {
		if(session.getValue("professorId").equals(null)) {
			return new ModelAndView("login");
		}
		factory = Persistence.createEntityManagerFactory("SoftwareDevelopmentProject");
	    em = factory.createEntityManager();
	    em.getTransaction().begin(); 

		      Professor professor = em.find(Professor.class, session.getAttribute("professorId").toString());		        
		      em.close();
	      return new ModelAndView("uploadphoto","professor",professor); 
	}
	@RequestMapping("/accountinfo")
	   public ModelAndView accountInfo(HttpServletRequest request,HttpServletResponse response,HttpSession session) {
		if(session.getValue("professorId").equals(null)) {
			return new ModelAndView("login");
		}
		factory = Persistence.createEntityManagerFactory("SoftwareDevelopmentProject");
	    em = factory.createEntityManager();
	    em.getTransaction().begin(); 

		      Professor professor = em.find(Professor.class, session.getAttribute("professorId").toString());		        
		      em.close();
	      return new ModelAndView("accountinfo","professor",professor); 
	}
	public void addSession(Professor p, HttpSession session) {
		session.setAttribute("professorId", p.getEmailId());
	}
}
