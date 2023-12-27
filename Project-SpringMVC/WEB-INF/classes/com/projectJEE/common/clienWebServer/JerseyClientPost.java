package com.projectJEE.common.clienWebServer;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import com.projectJEE.common.model.CreditCardDetails;

public class JerseyClientPost {

  public static CreditCardDetails ccCheck(CreditCardDetails cc) {

	  CreditCardDetails retCC = cc ; 
	  System.out.println("Inside JerseyClientPost ... ");
	  System.out.println(cc);

	  try {
	   
		  Response response = ClientBuilder.newClient()
			      .target("http://localhost:8080/CreditCards_Web-Service/rest/isValidCreditCardUser/")
			  //    .request(MediaType.APPLICATION_JSON)
			  //    .post(Entity.entity(cc, MediaType.APPLICATION_JSON));
                  .request()
                  .post(Entity.json(cc));
			      
		  System.out.println(response);  
	      
	      if (response.getStatus() == 200)      {
		      System.out.println("Status response code: " + response.getStatus());
		      CreditCardDetails ccResponse = response.readEntity(CreditCardDetails.class);
		      System.out.println(ccResponse);
		      System.out.println("Is the Credit Card valid ?: " + ccResponse.getCC_validity());
		      retCC =ccResponse;    
	      }else {
	    	  System.out.println("Status response code: " + response.getStatus());
	      }
	      
	      response.close();
	  }
	  catch(Exception e) {
		  System.out.println(e.getMessage());
		  System.out.println(e);
	  }finally{
		 
	  }
	  
	  return retCC;
  }  
}