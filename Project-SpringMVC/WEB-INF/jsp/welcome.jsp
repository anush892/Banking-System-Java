<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<html>
   <head>
      <title>Banking System</title>
   </head>
   <body>
      <h1>Banking System </h1><br/>
        <h4>Current Client: ${user} </h4>
     <br/><br/><br/>
      <table>
         <tr>
            <td></td>
            <td> <a href="/SpringMVC/createAccount">Create Account</a> </td>
         </tr>
         <tr>
            <td></td>
            <td><a href="/SpringMVC/transaction">Transaction</a> </td>
         </tr>   
         <tr>
            <td></td>
            <td><a href="/SpringMVC/displayStatement">Display Statement</a> </td>
         </tr>    
         <tr>
            <td></td>
            <td><a href="/SpringMVC/authCreditCard">Authorize Credit Card</a> </td>
         </tr>  
         <tr/>
      </table> 
      
        <br/>
        <a href="/SpringMVC/LogOut">Log Out</a>
       
   </body>
</html>