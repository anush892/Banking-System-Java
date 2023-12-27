package com.projectJEE.common.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.service.ccModel.CreditCardDetails;


// DAO - Data Access Object
public class JDBCHelper {
	
	Connection con;
	PreparedStatement pStmt;

	//1. Load the Driver
	public JDBCHelper(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("--Driver Loaded--");
		} catch (Exception e) {
			System.out.println("Exception is: "+e);
		}
	}
	
	//2. Create the Connection
	private void createConnection(){
		try {
			String url = "jdbc:mysql://localhost/credit_card_service";
			String user = "root";
			String password = "Secure112358";
			
			con = DriverManager.getConnection(url, user, password);
			System.out.println("--Connection Created--");
			
		} catch (Exception e) {
			System.out.println("Exception is: "+e);
		}
	}
	
	//3. Write SQL Statement
	//4. Execute SQL Statement
	public int registerUser(CreditCardDetails u){
		
		int i = 0;
		
		try {
			//3. Write SQL Statement
			String sql = "INSERT INTO credit_cards VALUES(null,?,?,?)"; 
			
			
			createConnection();	
			
			con.setAutoCommit(false);
			pStmt = con.prepareStatement(sql);
			pStmt.setString(1, u.getCC_Email());
			pStmt.setString(2, u.getCreditCardNumber() );
			pStmt.setString(3, u.getCC_CVV());
			
			i = pStmt.executeUpdate();
			
			System.out.println(u + "  New Credit Card inserted ...");
			con.commit();
			
		} catch (Exception e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				System.out.println("rollback ...");
				e1.printStackTrace();
			}
			System.out.println("Exception is: "+e);
		} finally {
			closeConnection();
		}
		
		return i;
	}



	public boolean checkCreditCard(CreditCardDetails u){
		boolean flag = false;
		
		try {
			//3. Write SQL Statement
			String sql = "SELECT * FROM credit_cards WHERE email_id = ? AND card_numb = ? AND cvv_cvc = ?"; 
			createConnection();	
			pStmt = con.prepareStatement(sql);
			
			pStmt.setString(1, u.getCC_Email());
			pStmt.setString(2, u.getCreditCardNumber());
			pStmt.setString(3, u.getCC_CVV());
			
			ResultSet rs = pStmt.executeQuery();
			
			flag = rs.next();
			
		} catch (Exception e) {
			System.out.println("Exception is: "+e);
		}finally {
			closeConnection();
		}
		
		return flag;
	}
	
	
	//-----------------------------------------------------
	
	//5. Close the Connection
	private void closeConnection(){
		try {
			con.close();
			System.out.println("--Connection Closed--");
		} catch (Exception e) {
			System.out.println("Exception is: "+e);
		}
	}
	
	
}
