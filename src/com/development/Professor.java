package com.development;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

@Entity
public class Professor implements Serializable {

	   
	@Id
	private String emailId;
	private String firstName;
	private String lastName;
	private String password;
	private String phone;
	private static final long serialVersionUID = 1L;

	public Professor() {
		super();
	}   
	public String getEmailId() {
		return this.emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}   
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}   
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}   
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}   
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
   
}
