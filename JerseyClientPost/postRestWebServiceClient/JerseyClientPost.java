package postRestWebServiceClient;

/**
 * 
 * 
 * Client RESTful Web Service Jersey framework
 * Post request, JSON Objects
 * https://jersey.github.io/
 * 
 * @Programmer: Mansi Mishra
 * @Date : 20-12-2023
 * 
 */

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import model.CreditCardDetails;

public class JerseyClientPost {

  public static void main(String[] args) {

	  try {
		 /*
		  ClientConfig clientConfig = new ClientConfig();
		  Client client = ClientBuilder.newClient(clientConfig);
		  WebTarget webTarget = client.target("http://localhost:8080/CreditCards_Web-Service/");
		  WebTarget resourceWebTarget = webTarget.path("rest");
		  WebTarget helloworldWebTarget = resourceWebTarget.path("isValidCreditCardUser");
		  */
		  
	      CreditCardDetails cc = new CreditCardDetails();
	      cc.setCreditCardNumber("4012345678912124");
	      cc.setCC_CVV("349");
	      cc.setCC_Email("mansi@gmail.com");
	      cc.setCC_validity("false");
		  	      
		  Response response = ClientBuilder.newClient()
			      .target("http://localhost:8080/CreditCards_Web-Service/rest/isValidCreditCardUser/")
			     // .request(MediaType.APPLICATION_JSON)
			     // .post(Entity.entity(cc, MediaType.APPLICATION_JSON));
                  .request()
                  .post(Entity.json(cc));
				  
		  System.out.println("Status response code: " + response.getStatus());
	      
	      if (response.getStatus() == 200) {

		      CreditCardDetails ccResponse = response.readEntity(CreditCardDetails.class);
		      System.out.println(ccResponse);
		      System.out.println("Is the Credit Card valid ?: " + ccResponse.getCC_validity());
	     
	      }else {
	    	  System.out.println("Status response code: " + response.getStatus());
	      }
	      response.close();
	  }
	  catch(Exception e) {
		  System.out.println(e);
		  System.out.println(e.getMessage());
		  //e.printStackTrace(new PrintStream(yourOutputStream));
		  e.printStackTrace(System.out);
	  }finally {
		  
	  }
  }
}