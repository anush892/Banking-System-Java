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
</style>

   </head>
   <body>

     <h1>Account Creation</h1><br/>
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
      <form:form commandName="newUser"  method = "POST" action = "/SpringMVC/newAccountCreated">
      <form:errors path="*" cssClass="errorblock" element="div" />
         <table>
            <tr>
            	<td><form:label path = "username">User Name: </form:label></td>
            	<td><form:input path = "username" /></td>
            	<td><form:errors path="username" cssClass="error" /></td>
           </tr>
            <tr>
               <td><form:label path = "emailID">Email-ID: </form:label></td>
               <td><form:input path = "emailID" /></td>
               <td><form:errors path="emailID" cssClass="error" /><div class="error">${Not_Valid_EMail}</div></td>
            </tr>
            <tr>
               <td><form:label path = "password">Password: </form:label></td>
               <td><form:password path = "password" /></td>
               <td><form:errors path="password" cssClass="error" /></td>
            </tr>  
            
             <tr>
	            <td><form:label path = "address">Address: : </form:label></td>
	            <td><form:textarea path="address" rows="10" cols="30"/></td>
	            <td><form:errors path="address" cssClass="error" /></td>
            </tr>  
            
            <tr>
               <td colspan = "3">
                  <input type = "submit" value = "Create Account"/>
               </td>
            </tr>
                        
         </table>  
      </form:form>
   </body>
</html>