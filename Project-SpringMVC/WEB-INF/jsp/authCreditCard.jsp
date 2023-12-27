<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<html>
   <head>
      <title>Project JAVA/j2EE & SOA</title>
      
            <style>
.error {
	color: #ff0000;
}

.errorblock {
	color: #000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}

h3 {
    color: red;
}
h2 {
    color: green;
}
</style>

   </head>
   <body>

     <h1>Authorize Credit Card Transaction.</h1><br/>
     <h4>Current Client: ${user} </h4>
      <table>
         <tr> <td> | </td> 
            <td> <a href="/SpringMVC/LogOut">Log Out</a></td>
            <td> | </td> 
            <td> <a href="/SpringMVC/welcome">welcome</a> </td>
         </tr>
         </table>
     <br/><br/><br/>
     <!--  commandName="newUser"   -->
      <form:form commandName="ccTrsc"  method = "POST" action = "/SpringMVC/newCreditCardTransc">
      <form:errors path="*" cssClass="errorblock" element="div" />  
         <table>
            <tr>
               <td><form:label path = "emailID">Email-ID: </form:label></td>
               <td><form:input path = "emailID" /></td>
         		<td><form:errors path="emailID" cssClass="error" /><div class="error">${Not_Valid_EMail}</div></td> 
            </tr>
            <tr>
               <td><form:label path = "cardNumber">Card Number: </form:label></td>
               <td><form:input path = "cardNumber" /></td>
           		<td><form:errors path="cardNumber" cssClass="error" /></td> 
            </tr>  
            
             <tr>
	            <td><form:label path = "cvv2Code">cvv2 cvc2: </form:label></td>
	            <td><form:input path="cvv2Code" /></td>
	    		<td><form:errors path="cvv2Code" cssClass="error" /></td> 
            </tr>  
            
            <tr>
               <td><form:label path = "amount">Amount </form:label></td>
               <td><form:input path = "amount" /></td>
        		<td><form:errors path="amount" cssClass="error" /></td>
            </tr>
            
            <tr>
               <td colspan = "3">
                  <input type = "submit" value = "Authorize"/>
               </td>
            </tr>                       
         </table>  
      </form:form>
      
      <h3>${statusMessage}</h3>
      <h2>${status_message}</h2>

   </body>
</html>