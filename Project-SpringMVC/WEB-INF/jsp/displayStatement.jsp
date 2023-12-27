<%@page import="com.projectJEE.common.model.User"%>
<%@ page import="com.projectJEE.common.model.listTransactions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
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
			
		table#custom {
					    border-collapse: collapse;
					    border-spacing: 0;
					    width: 70%;
					    border: 1px solid #ddd;
					}
					
			table#custom	th {
					    text-align: left; padding: 8px;
					    background-color: black;
					    color: white;
					}
					
			table#custom	td {
					    text-align: left; padding: 8px;
					}
					
			table#custom	tr:nth-child(even){background-color: #f2f2f2}
						
	</style> 
   </head>
   <body>

     <h1>Display Statement</h1><br/>
     <h4>Current Client: ${user} </h4>
     
      <table>
         <tr> <td> | </td> 
            <td> <a href="/SpringMVC/LogOut">Log Out</a></td>
            <td> | </td> 
            <td> <a href="/SpringMVC/welcome">welcome</a> </td>
         </tr>
         </table>
     <br/><br/><br/>
     
      <form:form commandName="displDat" method = "POST" action = "/SpringMVC/displayStatement">
      <form:errors path="*" cssClass="errorblock" element="div" />
         <table>
           <tr>
            	<td  colspan = "3"><label>Date Range: </label></td>
           </tr>
            <tr>
            	<td><form:label path = "dateFrom">From: </form:label></td>
            	<td><form:input path = "dateFrom" ></form:input></td>
            	<td><form:errors path="dateFrom" cssClass="error" /></td>
            </tr>       
            <tr>
               <td><form:label path = "dateTo">   To: </form:label></td>
               <td><form:input path = "dateTo"></form:input></td>
               <td><form:errors path="dateTo" cssClass="error" /></td>
            </tr>       
            <tr>
               <td  colspan = "3"> <input type = "submit" name="action" value = "Display"/>  </td>
            </tr>
                       
         </table>  
      </form:form>
      
  <br/><br/><br/>    
  
 <table id="custom">
  <tr>
    <th>N�</th>
    <th>Date</th>
    <th>Description</th>
    <th>Amount</th>
    <th>Available Balance</th>
  </tr>

	 <c:forEach items="${listTrsc}" var="listTransactions">
		  <tr>
			   <td><c:out value="${listTransactions.getNumb()}" /></td>
			   <td><c:out value="${listTransactions.getStrDate()}" /></td>
			   <td><c:out value="${listTransactions.getStrTrsrc()}" /></td>
			   <td><c:out value="${listTransactions.getStrTsrcAmount()}" /></td>
			   <td><c:out value="${listTransactions.getStrAvAmount()}" /></td>
		  </tr>
	 </c:forEach>

</table> 

<!--  <h4>List: ${listTrsc} </h4>  -->     
      
   </body>
</html>