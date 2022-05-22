package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.ws.rs.GET;
import javax.ws.rs.Path;


public class EmployeManagement {
	
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/electrs?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	//I n s e r t
	public String insertEmp(String employeName, String employePosition, String employeSalary, String employeType, String employePNO )  
	{   
		String output = ""; 	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{return "Error while connecting to the database for inserting."; } 
	 
			// create a prepared statement 
			String query = " insert into employe(`eID`,`employeName`,`employePosition`,`employeSalary`,`employeType`,`employePNO`)"
					+ " values (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, employeName);
			preparedStmt.setString(3, employePosition);
			preparedStmt.setString(4, employeSalary);
			preparedStmt.setString(5, employeType);
			preparedStmt.setString(6, employePNO);
			// execute the statement   
			preparedStmt.execute();    
			con.close(); 
	   
			String newEmp = readEmp(); 
			output =  "{\"status\":\"success\", \"data\": \"" + newEmp + "\"}";    
		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while inserting the Employe. Try again\"}";  
			System.err.println(e.getMessage());   
		} 		
	  return output;  
	} 	
	
	// R e a d
	public String readEmp()  
	{   
		String output = ""; 
		try   
		{    
			Connection con = connect(); 
		
			if (con == null)    
			{
				return "Error while connecting to the database for reading."; 
			} 
	 
			// Prepare the html table to be displayed    
			output = "<table border='1' class='table'><thead class='thead-dark'><th>employe Name</th><th>employe Position</th><th>employe Salary</th><th>employe Type</th><th>employe PNO</th><th>Date</th><th>Update</th><th>Remove</th></thead>";
	 
			String query = "select * from employe";    
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
	 
			// iterate through the rows in the result set    
			while (rs.next())    
			{     
				 String eID = Integer.toString(rs.getInt("eID"));
				 String employeName = rs.getString("employeName");
				 String employePosition = rs.getString("employePosition");
				 String employeSalary = rs.getString("employeSalary");
				 String employeType = rs.getString("employeType");
				 String employePNO = rs.getString("employePNO");
				 String date = rs.getString("date");
				 
				 
				// Add into the html table 
				output += "<tr><td><input id='hidEmpIDUpdate' name='hidEmpIDUpdate' type='hidden' value='" + eID + "'>" + employeName + "</td>"; 
				output += "<td>" + employePosition + "</td>";
				output += "<td>" + employeSalary + "</td>";
				output += "<td>" + employeType + "</td>";
				output += "<td>" + employePNO + "</td>";
				output += "<td>" + date + "</td>";
	 
				// buttons     
				output +="<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"       
						+ "<td><input name='hidItemIDDelete' type='button' value='Remove' class='btnRemove btn btn-danger' data-cid='" + eID + "'>" + "</td></tr>"; 
			
			}
			con.close(); 
	   
			output += "</table>";   
		}   
		catch (Exception e)   
		{    
			output = "Error while reading the Unit.";    
			System.err.println(e.getMessage());   
		} 	 
		return output;  
	}
	//U p d a t e
	public String updateEmp(String eID, String employeName, String employePosition, String employeSalary, String employeType, String employePNO )  
	{   
		String output = "";  
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{return "Error while connecting to the database for updating."; } 
	 
			// create a prepared statement    
			 String query = "UPDATE Employee SET emp_name=?, position=?, salary=?, emp_type=?, phone=? WHERE pay_id=?";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
 
	 
			// binding values    
			 preparedStmt.setString(1, employeName);
			 preparedStmt.setString(2, employePosition);
			 preparedStmt.setString(3, employeSalary);
			 preparedStmt.setString(4, employeType);
			 preparedStmt.setString(5, employePNO);
			 preparedStmt.setInt(6, Integer.parseInt(eID));
	 
			// execute the statement    
			preparedStmt.execute();    
			con.close();  
			String newEmp = readEmp();    
			output = "{\"status\":\"success\", \"data\": \"" + newEmp + "\"}";    
		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while updating the Unit.\"}";   
			System.err.println(e.getMessage());   
		} 	 
	  return output;  
	} 
	
	//D e l e t e
	public String deleteEmp(String eID)   
	{   
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from complaint where eID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(eID));

			// execute the statement
			preparedStmt.execute();
			con.close(); 
	 
			String newEmp = readEmp();    
			output = "{\"status\":\"success\", \"data\": \"" +  newEmp + "\"}";    
		}   
		catch (Exception e)   
		{    
			output = "Error while deleting the Unit.";    
			System.err.println(e.getMessage());   
		} 
	 
		return output;  
	}
	
}


