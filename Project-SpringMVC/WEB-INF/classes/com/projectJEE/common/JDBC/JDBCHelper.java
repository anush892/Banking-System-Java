package com.projectJEE.common.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.projectJEE.common.model.User;
import com.projectJEE.common.model.listTransactions;

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
			String url = "jdbc:mysql://localhost/projectJEE";
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
	public int registerUser(User u){
		
		int i = 0;
		
		try {
			//3. Write SQL Statement
			String sql = "INSERT INTO user_passw VALUES(null,?,?,?,?, ?)"; 
			
			
			createConnection();	
			
			con.setAutoCommit(false);
			pStmt = con.prepareStatement(sql);
			pStmt.setString(1, u.getUsername());
			pStmt.setString(2, u.getPassword() );
			pStmt.setString(3, u.getEmailID());
			pStmt.setString(4, u.getAddress());
			pStmt.setDouble(5, 50000);
			
			i = pStmt.executeUpdate();
			
			System.out.println(u + "  New user inserted ...");
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

	//---------------------------------------------------------------------------------------
	public int ccTransaction(String ccEmail , double currUserAmount , double dblAmount , int id_ccEmail ) {
				
		int i = 0;
		
		try {

			System.out.println("New Amount:  " + String.valueOf(currUserAmount - dblAmount));
			String sql1 ="UPDATE user_passw SET currentBalance=\'" + String.valueOf(currUserAmount - dblAmount)  + "\' WHERE email=\'" + ccEmail+"\'" ;
		
			String str_cc_id =String.valueOf( id_ccEmail);
			String strAmount = String.valueOf(dblAmount);
			String strNegAmount = String.valueOf(-dblAmount);
			String cc_av_amount = String.valueOf(currUserAmount - dblAmount);

			DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"); 
		
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			System.out.println(dateFormat.format(date)); 

			System.out.println("str_CC_ID: "+ str_cc_id);
			String sql2 = "INSERT INTO transactions VALUES(null,\""+ str_cc_id +"\",\""+ df.format(date) +"\",\"Credit_Card\",\""+strNegAmount+"\",\""+cc_av_amount +"\")"; 
				
			System.out.println(sql2); System.out.println("-------------------------------");
					
			createConnection();	
			
			con.setAutoCommit(false);
			
			Statement stm = con.createStatement();
			stm.addBatch(sql1);
			stm.addBatch(sql2);
						
			stm.executeBatch();
			
			System.out.println( "Batch executed...");
			con.commit();
			
		} catch (Exception e) {
			try {
				con.rollback();
				System.out.println("Batch cancelled, rollback ...");
				e.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			System.out.println("Exception is: "+e);
		} finally {
			closeConnection();
		}
		
		return i;		
	}
	
//---------------------------------------------------------------------------------------------------	
	public int transaction(String emailSend, double currUserAmount, String emailRecv, double remotUserAmount , double dblAmount, int id_send, int id_recv ){
		
		int i = 0;
		
		try {

			System.out.println("New Amount:  " + String.valueOf(currUserAmount - dblAmount));
			String sql1 ="UPDATE user_passw SET currentBalance=\'" + String.valueOf(currUserAmount - dblAmount)  + "\' WHERE email=\'" + emailSend+"\'" ;
			String sql2 = "UPDATE user_passw SET currentBalance=\'"+ String.valueOf(remotUserAmount + dblAmount) + "\' WHERE email=\'" + emailRecv+"\'" ;
			
			String send_id =String.valueOf( id_send);
			String recv_id =String.valueOf( id_recv);
			String strAmount = String.valueOf(dblAmount);
			String strNegAmount = String.valueOf(-dblAmount);
			String send_av_amount = String.valueOf(currUserAmount - dblAmount);
			String recv_av_amount = String.valueOf(remotUserAmount + dblAmount);

			DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"); 
		
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			System.out.println(dateFormat.format(date)); 

			System.out.println("Send_ID: "+ send_id);
			String sql3 = "INSERT INTO transactions VALUES(null,\""+ send_id +"\",\""+ df.format(date) +"\",\"Debit\",\""+strNegAmount+"\",\""+send_av_amount +"\")"; 
				
			System.out.println(sql3); System.out.println("-------------------------------");
			String sql4 = "INSERT INTO transactions VALUES(null,\""+ recv_id +"\",\""+ df.format(date) +"\",\"Credit\",\""+strAmount+"\",\""+recv_av_amount +"\")"; 
			System.out.println(sql4);
					
			createConnection();	
			
			con.setAutoCommit(false);
			
			Statement stm = con.createStatement();
			stm.addBatch(sql1);
			stm.addBatch(sql2);
			stm.addBatch(sql3);
			stm.addBatch(sql4);
						
			stm.executeBatch();
			
			System.out.println( "Batch executed...");
			con.commit();
			
		} catch (Exception e) {
			try {
				con.rollback();
				System.out.println("Batch cancelled, rollback ...");
				e.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			System.out.println("Exception is: "+e);
		} finally {
			closeConnection();
		}
		
		return i;
	}

	public String getUserBalance(String email){
		String strBalance= null;
		
		try {
			//3. Write SQL Statement
			String sql = "SELECT currentBalance FROM user_passw WHERE email = ?"; 
			createConnection();	
			pStmt = con.prepareStatement(sql);
			pStmt.setString(1, email);
			ResultSet rs = pStmt.executeQuery();
			if(rs.next()) {
					strBalance = rs.getString(1);
					//System.out.println("Amount is: "+ strBalance);	
			} else {
				System.out.println("Issue with the amount value field. ");	
			}
			
		} catch (Exception e) {
			System.out.println("Exception is: "+e);
		}finally {
			closeConnection();
		}
		
		return strBalance;
	}

	public List<listTransactions>  getListTransactions(String strID, int id, String dateFrom, String dateTo){
		
		List<listTransactions> listTrsc = new ArrayList<listTransactions>();
		
		try {
			//3. Write SQL Statement
			String sql2 = "SELECT currentBalance FROM user_passw WHERE email = ?"; 
			String sql = " select * from  transactions where user_id=? and trsc_date > ? and trsc_date <  ? " ;
			createConnection();	
			pStmt = con.prepareStatement(sql);
			pStmt.setString(1, strID);
			pStmt.setString(2, dateFrom);
			pStmt.setString(3, dateTo);
			ResultSet rs = pStmt.executeQuery();
			int intCount=0;
			while(rs.next()) {
				intCount++;
				listTrsc.add(new listTransactions(String.valueOf(intCount), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6))); 
					System.out.println( rs.getString(1) + " : "+ rs.getString(2)+ " : "+ rs.getString(3)+ " : "+ rs.getString(4)+ " : "+ rs.getString(5)+ " : "+ rs.getString(6));	
			} 
			
		} catch (Exception e) {
			System.out.println("Exception is: "+e);
		}finally {
			closeConnection();
		}
		
		return listTrsc;
	}
	

	public int getUserID(String email){
		int intID=0;
		
		try {
			//3. Write SQL Statement
			String sql = "SELECT id_user FROM user_passw WHERE email = ?"; 
			createConnection();	
			pStmt = con.prepareStatement(sql);
			pStmt.setString(1, email);
			ResultSet rs = pStmt.executeQuery();
			if(rs.next()) {
				intID = rs.getInt(1);
			} else {
				System.out.println("Issue with the amount value field. ");	
			}
			
		} catch (Exception e) {
			System.out.println("Exception is: "+e);
		}finally {
			closeConnection();
		}
		
		return intID;
	}
	
	public boolean loginUser(User u){
		boolean flag = false;
		
		try {
			//3. Write SQL Statement
			String sql = "SELECT * FROM user_passw WHERE email = ? AND password = ?"; 
			createConnection();	
			pStmt = con.prepareStatement(sql);
			pStmt.setString(1, u.getEmailID());
			pStmt.setString(2, u.getPassword());
			
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
	
	public boolean checkCC(String id_ccEmail,String ccNumber, String ccCVV) {
		boolean flag = false;
		
		try {
			//3. Write SQL Statement
			String sql = "SELECT * FROM credit_cards WHERE id = ? and card_numb = ? and cvv_cvc = ?"; 
			createConnection();	
			pStmt = con.prepareStatement(sql);

			pStmt.setString(1, id_ccEmail);
			pStmt.setString(2, ccNumber);
			pStmt.setString(3, ccCVV);
						
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
	public boolean checkEmailUser(User u){
		boolean flag = false;
		
		try {
			//3. Write SQL Statement
			String sql = "SELECT * FROM user_passw WHERE email = ?"; 
			createConnection();	
			pStmt = con.prepareStatement(sql);
			pStmt.setString(1, u.getEmailID());
						
			ResultSet rs = pStmt.executeQuery();
			
			flag = rs.next();
			
		} catch (Exception e) {
			System.out.println("Exception is: "+e);
		}finally {
			closeConnection();
		}
		
		return flag;
	}
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