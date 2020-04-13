package com.development;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;	
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
	
	@Resource(name="professorService")
	private ProfessorService professorService;
	
	@Resource(name="studentService")
	private StudentService studentService;
		
	Map<String,Object> model = new HashMap<String,Object>();
	
	@RequestMapping("/signUp")
	public ModelAndView signUp(HttpServletRequest request,HttpServletResponse response,HttpSession session) {
	    
	    Professor professor = new Professor();
	    professor.setFirstName(request.getParameter("firstName"));
	    professor.setLastName(request.getParameter("lastName"));
	    professor.setEmailId(request.getParameter("emailId"));
	    professor.setPassword(request.getParameter("password"));
	    professor.setPhone(request.getParameter("phone"));
		    
		addSession(professor,session);
		
		if(professorService.add(professor)) {
			System.out.println("Success");
			return new ModelAndView("classlist", "professor", professor);
		}else {
			return new ModelAndView("signup", "success", false);
		}
		
	}
	
	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest request,HttpServletResponse response,HttpSession session) {

		if(professorService.findUserById(request.getParameter("userName"))) {
			model.put("first", false);
			model.put("success", false);
			model.put("message", "Email ID is not registered");
			return new ModelAndView("login", "model", model);
		}else {
			Professor professor = professorService.findUserId(request.getParameter("userName"));
		    
			if(professor.getPassword().equals(request.getParameter("password"))) {
				addSession(professor,session);
				return new ModelAndView("classlist", "professor", professor);
			}else {
				model.put("first", false);
				model.put("success", false);
				model.put("message", "Password is wrong");
				return new ModelAndView("login", "model", model);
			}
		}	       
	}
	
	@RequestMapping("/classlist")
	public ModelAndView classlist(HttpServletRequest request,HttpServletResponse response,HttpSession session) {
		
		Professor professor = professorService.findUserId(session.getValue("professorId").toString());
		    
		return new ModelAndView("classlist", "professor", professor);
	}
	@RequestMapping("/resetClassList")
	public ModelAndView resetClassList(HttpServletRequest request,HttpServletResponse response,HttpSession session) {
		
		Professor professor = professorService.findUserId(session.getValue("professorId").toString());
		
		return new ModelAndView("classlist", "professor", professor);
	}
	@RequestMapping("/Update")
	public ModelAndView update(HttpServletRequest request,HttpServletResponse response,HttpSession session) {
		  
		if(session.getValue("professorId").equals(null)) {
			return new ModelAndView("login");
		}
		
		Professor professor = new Professor();
		professor.setEmailId(session.getAttribute("professorId").toString());
		professor.setFirstName(request.getParameter("firstName"));
		professor.setLastName(request.getParameter("lastName"));
		professor.setEmailId(request.getParameter("emailId"));
		professor.setPassword(request.getParameter("password"));
		professor.setPhone(request.getParameter("phone"));
		        
		if(professorService.edit(professor)) {
			System.out.print("Edited Successfully");
		}
		
	    return new ModelAndView("accountinfo","professor",professor); 
	}
	
	@RequestMapping("/register")
	public ModelAndView register(@RequestParam CommonsMultipartFile file,@RequestParam String studentId,
			   @RequestParam String firstName,@RequestParam String lastName,@RequestParam String emailId,@RequestParam String phone,HttpSession session) throws IOException {
	
		Student student = new Student();
		student.setStudentId(studentId);
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setEmailId(emailId);
		student.setPhone(phone);
	      
		ServletContext context = session.getServletContext();  
		final String UPLOAD_DIRECTORY ="/images";  
		String path = context.getRealPath(UPLOAD_DIRECTORY);  
		String filename = file.getOriginalFilename();  
	    
		System.out.println(path+" "+filename);        
	    
		byte[] bytes = file.getBytes();  
		System.out.println("bytes.size="+ bytes.length);
		
		filename = studentId + filename.substring(filename.lastIndexOf('.'));
					
		student.setImageName(filename);
		System.out.println("File Name: " + filename);
	    String accessKeyId = "YOUR_ACCESS_KEY";
		String secretAccessKey =  "YOUR_SECRET_KEY";
		String region = "us-east-2";
		String bucketName = "neelbucket1";
		
		BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKeyId, secretAccessKey);
		AmazonS3 s3client = AmazonS3ClientBuilder.standard().withRegion(region)
			 		.withCredentials(new AWSStaticCredentialsProvider(awsCreds)).build();
			    
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentLength(bytes.length);

		InputStream targetStream = new ByteArrayInputStream(bytes);
		PutObjectRequest uploadRequest = new PutObjectRequest(bucketName, filename, targetStream, metadata);
		uploadRequest.setCannedAcl(CannedAccessControlList.PublicRead);
		s3client.putObject(uploadRequest);
			  
		System.out.println("Photo uploaded");
		    
		if(studentService.add(student)) {
			return new ModelAndView("student", "success", true); 
		}else {
			return new ModelAndView("student", "success", false); 
		}
		
	}
	
	@RequestMapping("/photoupload")
	public RedirectView photoUpload(@RequestParam CommonsMultipartFile file, HttpServletResponse response) throws IOException {
		  
		RedirectView redirectView = new RedirectView();
		
		String filename = file.getOriginalFilename();
		filename = "test" + filename.substring(filename.lastIndexOf('.'));

	    
	    byte[] bytes = file.getBytes();  
	    System.out.println("bytes.size="+ bytes.length);
			
	    String accessKeyId = "YOUR_ACCESS_KEY";
		String secretAccessKey =  "YOUR_SECRET_KEY";
		String region = "us-east-2";
		String bucketName = "neelbucket2";
		
		BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKeyId, secretAccessKey);
		AmazonS3 s3client = AmazonS3ClientBuilder.standard().withRegion(region)
			    		.withCredentials(new AWSStaticCredentialsProvider(awsCreds)).build();
			    
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentLength(bytes.length);

		InputStream targetStream = new ByteArrayInputStream(bytes);
		PutObjectRequest uploadRequest = new PutObjectRequest(bucketName, filename, targetStream, metadata);
		uploadRequest.setCannedAcl(CannedAccessControlList.PublicRead);
		    
		s3client.putObject(uploadRequest);
		System.out.println("Photo uploaded");
			  
		redirectView.setUrl("http://127.0.0.1:5000/image_recognition/");
		return redirectView;
	}
	
	@RequestMapping("/logout")
	public ModelAndView logout(HttpServletRequest request,HttpServletResponse response,HttpSession session) {

		session.invalidate();
		model.put("first", true);
		return new ModelAndView("login","model",model);
	}
	
	@RequestMapping("/student")
	public ModelAndView student(HttpServletRequest request,HttpServletResponse response) {
		return new ModelAndView("student","first",true);
	}
	
	@RequestMapping("/professor")
	public ModelAndView professor(HttpServletRequest request,HttpServletResponse response) {
		model.put("first", true);
		return new ModelAndView("login","model",model);
	}
	
	
	@RequestMapping("/newProfessor")
	public ModelAndView newProfessor(HttpServletRequest request) {
		return new ModelAndView("signup","first",true);
	}
	
	@RequestMapping("/uploadphoto")
	public ModelAndView uploadPhoto(HttpServletRequest request,HttpServletResponse response,HttpSession session) {
		if(session.getValue("professorId").equals(null)) {
			return new ModelAndView("login");
		}
		
		Professor professor = professorService.findUserId(session.getAttribute("professorId").toString());
	    return new ModelAndView("uploadphoto","professor",professor); 
	}
	
	@RequestMapping("/accountinfo")
	public ModelAndView accountInfo(HttpServletRequest request,HttpServletResponse response,HttpSession session) {
		if(session.getValue("professorId").equals(null)) {
			return new ModelAndView("login");
		}
		
		Professor professor = professorService.findUserId(session.getAttribute("professorId").toString());
	    return new ModelAndView("accountinfo","professor",professor); 
	}
	
	@RequestMapping("/aboutus")
	public ModelAndView aboutUs(HttpServletRequest request,HttpServletResponse response) {
		return new ModelAndView("aboutus");
	}
	
	@RequestMapping("/contactus")
	public ModelAndView contactUs(HttpServletRequest request,HttpServletResponse response) {
		return new ModelAndView("contactus");
	}
	
	public void addSession(Professor p, HttpSession session) {
		session.setAttribute("professorId", p.getEmailId());
	}
}
