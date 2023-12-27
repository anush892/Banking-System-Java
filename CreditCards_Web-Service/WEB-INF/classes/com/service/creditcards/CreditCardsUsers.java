
package com.service.creditcards;

import com.projectJEE.common.JDBC.JDBCHelper;
import com.service.ccModel.CreditCardDetails;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;   // JAXRS API
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/isValidCreditCardUser")
public class CreditCardsUsers {
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public CreditCardDetails verifyCardDetailsPostJson(CreditCardDetails cCard) {
		
		JDBCHelper helper;
		helper = new JDBCHelper();
		boolean flag = helper.checkCreditCard(cCard); 	
		
		if (flag) {  // if found correctly
			cCard.setCC_validity("true");  // set as valid credit card transaction 
		}
		
		return cCard; 
	}
	
}
