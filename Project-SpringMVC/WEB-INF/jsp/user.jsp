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

      <h2>User Banking Information</h2>
      <form:form commandName="user" method = "POST" action = "/SpringMVC/connectUser">
      <form:errors path="*" cssClass="errorblock" element="div" />
         <table>
            <tr>
               <td><form:label path = "emailID">User Name</form:label></td>
               <td><form:input path = "emailID" /></td>
               <td><form:errors path="emailID" cssClass="error" /></td>
            </tr>
            <tr>
               <td><form:label path = "password">Password</form:label></td>
               <td><form:password path = "password" /></td>
               <td><form:errors path="password" cssClass="error" /></td>
            </tr>  
            <tr>
               <td colspan = "3">
                  <input type = "submit" value = "Submit"/>
               </td>
            </tr>
            
                        <tr>
               <td colspan = "3">${state}</td>
            </tr>
            
         </table>  
      </form:form>
   </body>
</html>