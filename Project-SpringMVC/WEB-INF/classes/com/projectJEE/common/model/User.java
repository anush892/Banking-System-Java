package com.projectJEE.common.model;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

public class User {
	
	private final String EMAIL_PATTERN =
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
   private Integer id;
   private String username;
   @Size(min=5, max=25)
   @NotEmpty
   private String password;
   private String state;
   @NotEmpty
   @Pattern(regexp=EMAIL_PATTERN)
   private String emailID;
   private String address;
   private double amount;
   
   public User() {

}
   public User(Integer id, String username, String password) {
			this.id = id;
			this.username = username;
			this.password = password;
			this.amount = 0;
}
   
public User(Integer id, String username, String password, String state, String emailID, String address) {
	this.id = id;
	this.username = username;
	this.password = password;
	this.state = state;
	this.emailID = emailID;
	this.address = address;
	this.amount = 0;
}

public String getEmailID() {
	return emailID;
}

public void setEmailID(String emailID) {
	this.emailID = emailID;
}

public String getAddress() {
	return address;
}

public void setAddress(String address) {
	this.address = address;
}

public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getUsername() {
      return username;
   }
   public void setUsername(String username) {
      this.username = username;
   }

   public String getPassword() {
      return password;
   }
   public void setPassword(String password) {  
      this.password = password;
   }

public String getState() {
	return state;
}

public void setState(String state) {
	this.state = state;
}

public double getAmount() {
	return amount;
}

public void setAmount(double amount) {
	this.amount = amount;
}

@Override
public String toString() {
	return "Email_ID : " + emailID;
}  
}