package com.guestbook.controllers;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.amazonaws.services.rds.AmazonRDS;
import com.guestbook.models.MessageModel;

public class RDSImplementation {
	
private AmazonRDS rds;
	
	private Connection con=null;
	private Statement stmt = null;
	private ResultSet rs;
	String tablename;
	
	//Please Enter the following details
		private String endpoint = "<ENTER_YOUR_RDS_INSTANCE'S_END_POINT >";
		private String username="<ENTER_YOUR_DB_USERNAME>";
		private String password="<ENTER_YOUR_DB_PASSWORD>";
		private String database="<ENTER_YOUR_DATABASE_NAME>";
		
	
	int flag = 0 ;
	String url= "jdbc:mysql://"+endpoint+"/"+database;
	
	public  RDSImplementation() {
		super();
		
		//Hint: Implement Code for creation of  Guestbook table inside RDS Instance.
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			con = DriverManager.getConnection(url,username,password);
			System.out.println("Connected to the database");
			
			DatabaseMetaData meta = con.getMetaData();
			rs = meta.getTables(null,null,null, new String[] {"TABLE"});
			while(rs.next()){
				tablename = rs.getString(3);
				//System.out.println("Tables are "+tablename);
				
				if (tablename.equals("Guestbook")){
					
					System.out.println("Guestbook table Already exist");
					flag = 1;
				}
						
			}
			if (flag == 0){
				 
					stmt = con.createStatement();
				    String sql = "CREATE TABLE Guestbook " +
			               "(ID INTEGER NOT NULL AUTO_INCREMENT," +
			               " Sender VARCHAR(255) NOT NULL," + 
			               " Message VARCHAR(300) NOT NULL,"+
			               "ObjectUrl TEXT NOT NULL,"+
			               "PostTime  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"+
			               "PRIMARY KEY (ID))";
				    stmt.executeUpdate(sql);
				    System.out.println("Created table in given database...");
				}
			
			
		     
			}
			catch(ClassNotFoundException e){
				System.out.println("Exception occured"+ e.getMessage());
			}
			catch (SQLException e) {
				
				// TODO: handle exception
				System.out.println("Exception occured"+ e.getMessage());
			}
		finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException se2) {
			} // n

			try {
				if (con != null)
					con.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		}

	}
	
	
	public String InsertMessages(MessageModel msgmodel){
		String sender = msgmodel.getSender();
		String msg = msgmodel.getMessage();
		String objurl = msgmodel.getObjecturl();
		System.out.println(" Record to be inserted is: "+sender+"\t message is : "+msg+"\t  Obj URL :\t" +objurl+"\n Please Implement the code in RDSImplementation.InserMessages method.");
		
		//Hint: Implement code for inserting data into the Guestbook table
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url,username,password);
			stmt = con.createStatement();
			String InsertQuery = "INSERT INTO Guestbook(Sender,Message,ObjectUrl) values('"+sender+"','"+msg+"','"+objurl+"');";
			
			stmt.executeUpdate(InsertQuery);

			//System.out.println("Record Inserted successfully");
			
		}catch(SQLException e){
			System.out.println("SQL Exception in insert method  "+e.getMessage());
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException se2) {
			} // n

			try {
				if (con != null)
					con.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		}
		

		
		
		return "success";
	}
	
	public List<MessageModel> retriveMessages(){
		List<MessageModel> messages = new ArrayList<MessageModel>();
		 
		String sender=null,message=null,objurl=null,posttime=null;
	
		//Hint:Implement to get the data from the Guestbook Table
		try{
			MessageModel msg = new MessageModel();//Initializing Message Class 
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url,username,password);
			stmt = con.createStatement();
			//String SelectQuery = "select Sender,Message,ObjectUrl,PostTime from Guestbook";
			String SelectQuery = "select * from Guestbook";
			rs = stmt.executeQuery(SelectQuery);
			while(rs.next()){
				sender=rs.getString("Sender");
				message=rs.getString("Message");
				objurl=rs.getString("ObjectUrl");
				posttime=rs.getString("PostTime");
				System.out.println("Sender value is "+sender +"\t Message is "+ message + "\tObject Url" +objurl+"\tTime "+posttime);
				msg.setSender(sender);
				msg.setMessage(message);
				msg.setObjecturl(objurl);
			}
		
			
			
			messages.add(msg);
			
		}catch(SQLException e){
			System.out.println("SQL Exception in Retrive  method  "+e.getMessage());
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException se2) {
			} // n

			try {
				if (con != null)
					con.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		}

		
		
		
        return messages;
	} 
}
