package com.projectJEE.common.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import com.projectJEE.common.JDBC.JDBCHelper;
import com.projectJEE.common.clienWebServer.JerseyClientPost;
import com.projectJEE.common.model.CreditCardDetails;
import com.projectJEE.common.model.Transaction;
import com.projectJEE.common.model.User;
import com.projectJEE.common.model.displDate;
import com.projectJEE.common.model.listTransactions;
import com.projectJEE.common.model.authCreditCard;


@Controller
@SessionAttributes( {"user","newUser"})
@RequestMapping("/")
public class SignUpController {

	@RequestMapping(value = "/",  method = { RequestMethod.GET, RequestMethod.POST })
	public String displayCustomerForm(ModelMap model) {	
		model.addAttribute("user", new User());
		return "user";
	}
//----------------------------------------------------------------
	@RequestMapping(value = "/connectUser", method = { RequestMethod.GET, RequestMethod.POST }  )
	public String addCustomer( @Valid User user, BindingResult result) {
		
		JDBCHelper helper;

		if (result.hasErrors()) {
			return "user";
		} else {
					helper = new JDBCHelper();			
					boolean flag = helper.loginUser(user);	
	   
					if (flag) {		
					      				return "welcome";
					      }
					else {	
								      return "user";
							}
				}
	}

//-------------------------------------------------------------------------------------------------
	   
	   @RequestMapping(value = "/createAccount", method = RequestMethod.GET)
	   public ModelAndView createAccount(  //@ModelAttribute("newUser")User newUser,   
	      ModelMap model) {
		 	   
			   User newUser = new User();
			   model.addAttribute("newUser",newUser);
			   return  new ModelAndView("createAccount");   
	   }
//-------------------------------------------------------------------------------------------------	   
	   @RequestMapping(value = "/transaction", method = RequestMethod.GET)
	   public ModelAndView transaction(ModelMap model) {
		   
		   Transaction trsc= new Transaction();
		   model.addAttribute("trsc",trsc);
		   return  new ModelAndView("transaction");   
	   }
//-------------------------------------------------------------------------------------------------		   
	   @RequestMapping(value = "/displayStatement", method = RequestMethod.GET)
	   public ModelAndView displayStatement(ModelMap model) {
		   
		   displDate displDat= new displDate();
		   model.addAttribute("displDat",displDat);
		   return  new ModelAndView("displayStatement"); 
	   }
//-------------------------------------------------------------------------------------------------		   
	   @RequestMapping(value = "/authCreditCard", method = RequestMethod.GET)
	   public ModelAndView authCreditCard(ModelMap model) {
		   
		   authCreditCard ccTrsc= new authCreditCard();
		   model.addAttribute("ccTrsc",ccTrsc);
		   return  new ModelAndView("authCreditCard"); 	  
	   }
//-----------------------------------------------------------------------------------------	   
	   @RequestMapping(value = "/newCreditCardTransc",  method = { RequestMethod.GET, RequestMethod.POST } )
	   public ModelAndView newCreditCardTransc(@Valid @ModelAttribute("ccTrsc")authCreditCard ccTrsc,  BindingResult result, HttpServletRequest request,
			    ModelMap model) {
	   
		   System.out.println("Has Format Errors: " + result.hasErrors());
			if (result.hasErrors()) {
				return  new ModelAndView("authCreditCard");
			} 
	
	synchronized (this) {	
		//-------------------------------------------------------------------						
			JDBCHelper helper;
			helper = new JDBCHelper();	
		 	System.out.println("JDBCHelper created...");
			User ccUser = new User();
			ccUser.setEmailID(ccTrsc.getEmailID());
			boolean flag = helper.checkEmailUser(ccUser);	
	  //------------------------------------------------------------------------------	   
		if (!flag) {		// return to the current page 		
			   model.addAttribute("ccTrsc",ccTrsc);
			   model.addAttribute("statusMessage","Credit Card Transaction Message: Please enter a valid Email_ID!");
			   return  new ModelAndView("authCreditCard");
		      }
		else {
	 
			  String ccEmail = ccTrsc.getEmailID(); 
			  String ccNumber =  ccTrsc.getCardNumber();
			  String ccCVV =  ccTrsc.getCvv2Code();
			  int id_ccEmail= helper.getUserID(ccEmail);
			  double dblAmount =  Double.parseDouble(ccTrsc.getAmount());
			  
		      CreditCardDetails cc = new CreditCardDetails();
		      cc.setCreditCardNumber(ccNumber); 
		      cc.setCC_CVV(ccCVV); 
		      cc.setCC_Email(ccEmail); 
		      cc.setCC_validity("false");
			  System.out.println(cc);			  
			  // Check Credit Card validity.
			  CreditCardDetails retCC = JerseyClientPost.ccCheck(cc); // Static method to call the remote RESTful Web Service 
			  System.out.println(retCC);
			  boolean ccFlag = Boolean.valueOf(retCC.getCC_validity());	

			  //boolean ccFlag = helper.checkCC(Integer.toString(id_ccEmail), ccNumber, ccCVV);
			  System.out.println(ccFlag);
				if (!ccFlag) {										
					   model.addAttribute("ccTrsc",ccTrsc);
					   model.addAttribute("statusMessage","Please enter a valid and registered Credit Card!");
					   return  new ModelAndView("authCreditCard");				
				}
				
				double currUserAmount =  Double.parseDouble( helper.getUserBalance(ccEmail));
	//######
				   System.out.println("Current User: "+ ccEmail);
				   System.out.println("Balance User: "+ helper.getUserBalance(ccEmail));	   
	
			      System.out.println("Outside: " + dblAmount ) ;     
			      System.out.println(dblAmount);
			      System.out.println(currUserAmount);
			      System.out.println("Condition: " + (dblAmount > currUserAmount - 50));
			      
			      
			      if ( (dblAmount > currUserAmount - 50)) {		    	  
					   model.addAttribute("ccTrsc",ccTrsc);
					   model.addAttribute("statusMessage","Sorry, you do not have enough in your account!");
					   return  new ModelAndView("authCreditCard");		    	  
			      }
			           
			      if (dblAmount < (currUserAmount - 50) ) { // A threshold of minimum 50 units are required 
			    	  System.out.println("Inside: " + dblAmount ) ;
			    	  System.out.println("id_ccEmail: " +id_ccEmail );
			    		  
					    int intRest = helper.ccTransaction(ccEmail, currUserAmount, dblAmount, id_ccEmail );
					    System.out.println("Email-ID: " + ccEmail + "Credit Card Number: "+  ccNumber + ", Amount: " + dblAmount );	    	  
			      }			
			//------------------------------------------------------------
			   model.addAttribute("ccTrsc",ccTrsc);
			   model.addAttribute("status_message","Credit Card Transaction Message: transaction executed!");
			   return  new ModelAndView("authCreditCard");
			
			}
		
		} // end of synchronized 
		
	   }
	   
//---------------------------------------------------------------------------------------------
	   @RequestMapping(value = "/displayStatement",  method = { RequestMethod.GET, RequestMethod.POST})
	   public ModelAndView displayStatement(@Valid @ModelAttribute("displDat")displDate dspl,  BindingResult result, HttpServletRequest request,
			    ModelMap model) {
	   
		   System.out.println("Has Format Errors: " + result.hasErrors());
			if (result.hasErrors()) {
				return  new ModelAndView("displayStatement");
			} 
		   
		   String dateFrom = dspl.getDateFrom();
		   String dateTo = dspl.getDateTo();
		   User user = (User)request.getSession().getAttribute("user");
		   String emailSend = user.getEmailID(); 
			
		   JDBCHelper helper = new JDBCHelper();	
		   int intID= helper.getUserID(emailSend);
		   String strID = String.valueOf(intID);
		   System.out.println("DateFrom: " + dspl.getDateFrom());
		   System.out.println("DateFrom: " + dspl.getDateTo() );
		   
		   List<listTransactions> listTransactions =   helper.getListTransactions( strID, intID,dateFrom, dateTo);
		   System.out.println("------------------------------------------------");
		   for(listTransactions obj : listTransactions)
			   System.out.println(obj);
		   
		   model.addAttribute("listTrsc", listTransactions);
		   model.addAttribute("displDat",dspl);		   
		   return  new ModelAndView("displayStatement"); 
	   }
	//-------------------------------------------------------------------------------------------------	   
	   
	   @RequestMapping(value = "/execTransaction",  method = { RequestMethod.GET, RequestMethod.POST } )
	   public ModelAndView execTransaction(@Valid @ModelAttribute("trsc")Transaction trsc,  BindingResult result, HttpServletRequest request ,
			    ModelMap model) {
		  
			if (result.hasErrors()) {
				return  new ModelAndView("transaction");
			} 
		   
		   synchronized (this) {
	
				   JDBCHelper helper;
		
				   User user = (User)request.getSession().getAttribute("user");
				   System.out.println(user);
				   
				   String action = request.getParameter("action");
				   System.out.println(action);

					helper = new JDBCHelper();
					String emailSend = user.getEmailID(); 
					double dblAmount =  Double.parseDouble(trsc.getAmount());
					String emailRecv =  trsc.getAccountNumber();
					User remUser = new User();
					remUser.setEmailID(emailRecv);
					boolean flag = helper.checkEmailUser(remUser);	
			  //------------------------------------------------------------------------------	   
				if (!flag) {		// return to the current page 
					   model.addAttribute("trsc",trsc);
					   model.addAttribute("statusMessage","Transaction Message: Please enter a valid Email_ID!");
						return  new ModelAndView("transaction");
				      }
				else {
					   						
						 if (emailSend.equals(emailRecv)) {
							   model.addAttribute("trsc",trsc);
							   model.addAttribute("statusMessage","Transaction Message: Same Account. Please enter another Valid Account Email_ID!");
							   return  new ModelAndView("transaction");
						 }
							 					
						  System.out.println("About to create JDBCHelper...");	
						 System.out.println("JDBCHelper created...");
						double currUserAmount =  Double.parseDouble( helper.getUserBalance(emailSend));
					    double remotUserAmount =  Double.parseDouble( helper.getUserBalance(emailRecv));   
						   System.out.println("Current User: "+ emailSend);
						   System.out.println("Balance User: "+ helper.getUserBalance(emailSend));	   
					      System.out.println("Remote User: " + emailRecv);
			
					      System.out.println("Balance User: "+ helper.getUserBalance(emailRecv));
					      System.out.println("Outside: " + dblAmount ) ;     
					      System.out.println(dblAmount);
					      System.out.println(currUserAmount);
					      System.out.println("Condition: " + (dblAmount > currUserAmount - 50));
					           
					      if (dblAmount < (currUserAmount - 50) ) { // A threshold of minimum 50 units are required 
					    	  System.out.println("Inside: " + dblAmount ) ;
					    	  int id_send= helper.getUserID(emailSend);
					    	  int id_recv=helper.getUserID(emailRecv);
					    	  System.out.println(id_send + " , " +id_recv );
					    	
					    	  int intRest=0;
					   	   if (action.equals("Debit From") ) {  // Debit? 
							    intRest = helper.transaction(emailRecv, remotUserAmount , emailSend, currUserAmount, dblAmount, id_recv, id_send );
							    System.out.println("Debit From " + emailRecv + " To the logged User: " + emailSend + " Amount: " + dblAmount );
						   }else if (action.equals("Credit To") ) {  //Credit??
						   		  intRest = helper.transaction(emailSend, currUserAmount, emailRecv, remotUserAmount , dblAmount, id_send, id_recv );
							      System.out.println("Credit To from the logged User " + emailSend + " To " + emailRecv + " Amount: " + dblAmount );
						   }
					   	  
					    	  
					      }
					       
						   trsc= new Transaction();
						   model.addAttribute("trsc",trsc);
						   model.addAttribute("status_message","Transaction executed. Operation: " + action +  ", " + emailSend + ", " + emailRecv + ",  " + String.valueOf(dblAmount)  );
						   return  new ModelAndView("transaction");
				}
			
		   }	// end of synchronized 
	   }	   
//-------------------------------------------------------------------------------------------------
	   
	   @RequestMapping(value = "/newAccountCreated",  method = { RequestMethod.GET, RequestMethod.POST } )
	   public ModelAndView newAccountCreated(@Valid @ModelAttribute("newUser")User newUser, BindingResult result , 	HttpServletRequest request 
		   , ModelMap model) {

		   synchronized (this) {		   
			   
				if (result.hasErrors()) {
					model.addAttribute("newUser", newUser);
					 return  new ModelAndView("createAccount");
				} else {
					
				   JDBCHelper helper = new JDBCHelper();   
					//Does this email exist in the DB?	
					boolean flag = helper.checkEmailUser(newUser);	
				  //------------------------------------------------------------------------------	   
					if (flag) {		// return to the current page 
						request.setAttribute("Not_Valid_EMail", "E-Mail already registered!!");
						return  new ModelAndView("createAccount");
				      }
				else {	
	   
					int flagReg = helper.registerUser(newUser);	
					
					model.addAttribute("newUser", newUser);
					 return  new ModelAndView("newAccountCreated");
					}
			}			
		   } // end of synchronized 
	   }
//-------------------------------------------------------------------------------------------------

	   @RequestMapping(value = "/welcome", method = RequestMethod.GET)
	   public ModelAndView welcomePage(User user, ModelMap model) {
	/*	   
		   try {
				   int val = (Integer) (Object) user; //clientObj;
				   if (val==-1) {
					   
						model.addAttribute("user", new User());
						return new ModelAndView("user", "command", new User()); 
				   }		   
		   }
		   catch (Exception e) {   
			   		return  new ModelAndView("welcome");
		   }
		   */
		   return  new ModelAndView("welcome");
	   }  

//-------------------------------------------------------------------------------------------	   
	   @RequestMapping(value = "/LogOut", method = RequestMethod.GET)
	   public ModelAndView LogOut(User user,   ModelMap model) {
		   
			model.addAttribute("user", new User());
			return new ModelAndView("user", "command", new User());   
	   }	   
}