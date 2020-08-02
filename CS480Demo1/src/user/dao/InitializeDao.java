package user.dao;

import java.sql.Connection;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;




import java.util.ArrayList;
import java.util.List;

import user.domain.User;
import java.sql.PreparedStatement;

/**
 * DDL functions performed in database
 * @author changxin bai
 *
 */
public class InitializeDao {
	

	public void initDB() throws InstantiationException, IllegalAccessException  {
		// JDBC driver name and database URL
		final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
		final String DB_URL = "jdbc:mysql://localhost/?serverTimezone=America/Chicago";

		//  Database credentials
		final String USER = "root";
		final String PASS = "Cps40008713!";
		String DB;
		
		
		Statement statement;
		
		try {
			//Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");
			
			//Open a connection
		    System.out.println("Connecting to database...");
		    Connection connect = DriverManager.getConnection(DB_URL, USER, PASS);
		    
		    //Create hospital management database
		    statement = connect.createStatement();
		    statement.executeUpdate("DROP DATABASE IF EXISTS Hospital_Management");
		    
		    String sql = "CREATE DATABASE Hospital_Management";
		    statement.executeUpdate(sql);
		    
		    DB = "Hospital_Management";
		    initTables(DB);
		    
		    
		} catch(SQLException e) {
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void initTables(String DB) {
		Statement statement;
		PreparedStatement preparedStatement;
		
		final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
		final String DB_URL = "jdbc:mysql://localhost/" + DB + "?serverTimezone=America/Chicago";

		//  Database credentials
		final String USER = "root";
		final String PASS = "Cps40008713!";
		
	
	    try {
			//Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");
			
			//Open a connection
		    Connection connect = DriverManager.getConnection(DB_URL, USER, PASS);
		    
		    // Second table: Physician
		 	statement = connect.createStatement();
		 	statement.executeUpdate("DROP TABLE IF EXISTS Physician");

		 	String sqlstmt = "CREATE TABLE Physician" +
		 	           "(EmployeeID INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT, " +
		 	           " first TEXT NOT NULL, " + 
		 	           " last TEXT NOT NULL, " + 
		 	           " position TEXT NOT NULL, " + 
		 	           " ssn INTEGER NOT NULL )"; 
		 		    
		 	statement.executeUpdate(sqlstmt);  // RUN THE 2ND TRANSACTION 
		    
		    // First table: Patient
			statement = connect.createStatement();
		    statement.executeUpdate("DROP TABLE IF EXISTS Patient");

		    sqlstmt = "CREATE TABLE Patient" +
	                  "(SSN INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT, " +
	                  " first VARCHAR(255), " + 
	                  " last VARCHAR(255), " + 
	                  " age INTEGER, " + 
	                  " gender VARCHAR(255), " + 
	                  " address VARCHAR(255), " +
	                  " phone TEXT NOT NULL, " +
	                  " physician INTEGER NOT NULL, " + 
	                  " CONSTRAINT k_Physician_EmployeeID FOREIGN KEY (physician) REFERENCES Physician(EmployeeID) ON DELETE CASCADE)"; 
		    
		    statement.executeUpdate(sqlstmt);  // RUN THE 1ST TRANSACTION 

		    
		 	// Third table: Nurse
		 	statement = connect.createStatement();
		 	statement.executeUpdate("DROP TABLE IF EXISTS Nurse");

		 	sqlstmt = "CREATE TABLE Nurse" +
		 	           "(EmployeeID INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT, " +
		 	           " first VARCHAR(255), " + 
		 	           " last VARCHAR(255), " + 
		 	           " position VARCHAR(255))"; 
		 		    
		 	statement.executeUpdate(sqlstmt);  // RUN THE 2ND TRANSACTION 
//		 	
		 	// fourth table: department
		 	statement = connect.createStatement();
		 	statement.executeUpdate("DROP TABLE IF EXISTS Department");

		 	sqlstmt = "CREATE TABLE Department" +
		 	           "(DepartmentID INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT, " +
		 	           " name TEXT NOT NULL, " + 
		 	           " Head INTEGER NOT NULL, " +
		 	           " CONSTRAINT fk_Physician_EmployeeID FOREIGN KEY (Head) REFERENCES Physician(EmployeeID) ON DELETE CASCADE)"; 
		 		    
		 	statement.executeUpdate(sqlstmt);  // RUN THE 2ND TRANSACTION 
		    
		 	// fifth table: appointment
		 	statement = connect.createStatement();
		 	statement.executeUpdate("DROP TABLE IF EXISTS Appointment");

		 	sqlstmt = "CREATE TABLE Appointment" +
		 	           "(app_id INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT, " +
		 	           " Patient INTEGER NOT NULL, " + 
		 	           " CONSTRAINT fk_Patient_SSN FOREIGN KEY (PATIENT) REFERENCES Patient(SSN), " +
		 	           " NurseAssist INTEGER, " + 
		 	           " CONSTRAINT fk_Nurse_EmployeeID FOREIGN KEY (NurseAssist) REFERENCES Nurse(EmployeeID) ON DELETE CASCADE, " +
		 	           " Physician INTEGER NOT NULL, " + 
		 	           " CONSTRAINT Physician_EmployeeID FOREIGN KEY (Physician) REFERENCES Physician(EmployeeID) ON DELETE CASCADE, " +
		 	           " Start DATETIME NOT NULL, " + 
		 	           " End DATETIME NOT NULL)"; 
		 		    
		 	statement.executeUpdate(sqlstmt);  // RUN THE 2ND TRANSACTION 
		    
		 	// last table: user
		 	statement = connect.createStatement();
		 	statement.executeUpdate("DROP TABLE IF EXISTS tb_user");

		 	sqlstmt = "CREATE TABLE tb_user " +
		 	           "(username VARCHAR(50) not NULL, " +
		 	           " password VARCHAR(50) not NULL, " +
		 	           " email VARCHAR(50) not NULL, " +
		 	           " PRIMARY KEY ( username ))"; 
		 		    
		 	statement.executeUpdate(sqlstmt);  // RUN THE 2ND TRANSACTION 
		 	
		 	// last table: user
		 	statement = connect.createStatement();
		 	statement.executeUpdate("DROP TABLE IF EXISTS Works_with");

		 	sqlstmt = "CREATE TABLE Works_with" +
		 	           "(Physician INTEGER NOT NULL, " +
		 	           " CONSTRAINT fk_Phys_EmployeeID FOREIGN KEY (Physician) REFERENCES Physician(EmployeeID) ON DELETE CASCADE, " +
		 	           " Department INTEGER NOT NULL, " +
		 	           " CONSTRAINT fk_Department_DepartmentID FOREIGN KEY (Department) REFERENCES Department(DepartmentID) ON DELETE CASCADE, " +
		 	           " PRIMARY KEY(Physician, Department))"; 
		 		    
		 	statement.executeUpdate(sqlstmt);  // RUN THE 2ND TRANSACTION
//		 	
//		 	
		 	
//		 	/* Insert Appointment */
//		    preparedStatement = connect
//		    		.prepareStatement("insert into Appointment(app_id, physician_id, nurse_id, app_date) values (?, ?, ?, ?)");
//		    preparedStatement.setInt(1, 01);
//		    preparedStatement.setInt(2, 01);
//		    preparedStatement.setInt(3, 01);
//			preparedStatement.setString(4, "2020-11-11");
//			preparedStatement.executeUpdate(); // RUN A TRANSACTION	
//
//		    preparedStatement = connect
//		    		.prepareStatement("insert into Appointment(app_id, physician_id, nurse_id, app_date) values (?, ?, ?, ?)");
//		    preparedStatement.setInt(1, 02);
//		    preparedStatement.setInt(2, 01);
//		    preparedStatement.setInt(3, 01);
//			preparedStatement.setString(4, "2020-08-08");
//			preparedStatement.executeUpdate(); // RUN A TRANSACTION
//
//		    
//		    preparedStatement = connect
//		    		.prepareStatement("insert into Appointment(app_id, physician_id, nurse_id, app_date) values (?, ?, ?, ?)");
//		    preparedStatement.setInt(1, 03);
//		    preparedStatement.setInt(2, 01);
//		    preparedStatement.setInt(3, 01);
//			preparedStatement.setString(4, "2020-01-09");
//			preparedStatement.executeUpdate(); // RUN A TRANSACTION
//		 	
//		 	
//		 	
//		 	/* Insert Department */
//		    preparedStatement = connect
//		    		.prepareStatement("insert into Department(department_id, name) values (?, ?)");
//		    preparedStatement.setInt(1, 01);
//			preparedStatement.setString(2, "General");
//			preparedStatement.executeUpdate(); // RUN A TRANSACTION	
//
//		    preparedStatement = connect
//		    		.prepareStatement("insert into Department(department_id, name) values (?, ?)");
//		    preparedStatement.setInt(1, 03);
//			preparedStatement.setString(2, "Cardio");
//			preparedStatement.executeUpdate(); // RUN A TRANSACTION	
//
//		    
//		    preparedStatement = connect
//		    		.prepareStatement("insert into Department(department_id, name) values (?, ?)");
//		    preparedStatement.setInt(1, 02);
//			preparedStatement.setString(2, "Plastics");
//			preparedStatement.executeUpdate(); // RUN A TRANSACTION	
//			
//			
//			
//		 	/* Insert Nurse */
//		    preparedStatement = connect
//		    		.prepareStatement("insert into Nurse(nurse_id, first, last, position) values (?, ?, ?, ?)");
//		    preparedStatement.setInt(1, 01);
//			preparedStatement.setString(2, "Shair");
//			preparedStatement.setString(3, "Nades");
//			preparedStatement.setString(4, "Assistant Nurse");
//			preparedStatement.executeUpdate(); // RUN A TRANSACTION	
//
//		    preparedStatement = connect
//		    		.prepareStatement("insert into Nurse(nurse_id, first, last, position) values (?, ?, ?, ?)");
//		    preparedStatement.setInt(1, 03);
//			preparedStatement.setString(2, "Missael");
//			preparedStatement.setString(3, "Lopez");
//			preparedStatement.setString(4, "Head Nurse");
//			preparedStatement.executeUpdate(); // RUN A TRANSACTION	
//
//		    
//		    preparedStatement = connect
//		    		.prepareStatement("insert into Nurse(nurse_id, first, last, position) values (?, ?, ?, ?)");
//		    preparedStatement.setInt(1, 02);
//			preparedStatement.setString(2, "Amy");
//			preparedStatement.setString(3, "Low");
//			preparedStatement.setString(4, "Nurse");
//			preparedStatement.executeUpdate(); // RUN A TRANSACTION	
//			
//			
//			
//		 	/* Insert Physician */
//		    preparedStatement = connect
//		    		.prepareStatement("insert into  Physician(physician_id, first, last, position, department_id) values (?, ?, ?, ?, ?)");
//		    preparedStatement.setInt(1, 01);
//			preparedStatement.setString(2, "Maria");
//			preparedStatement.setString(3, "Gates");
//			preparedStatement.setString(4, "General Surgeon");
//			preparedStatement.setInt(5, 02);
//			preparedStatement.executeUpdate(); // RUN A TRANSACTION	
//
//		    preparedStatement = connect
//		    		.prepareStatement("insert into  Physician(physician_id, first, last, position, department_id) values (?, ?, ?, ?, ?)");
//		    preparedStatement.setInt(1, 03);
//			preparedStatement.setString(2, "John");
//			preparedStatement.setString(3, "Garcia");
//			preparedStatement.setString(4, "General Doctor");
//			preparedStatement.setInt(5, 22);
//			preparedStatement.executeUpdate(); // RUN A TRANSACTION	
//
//		    
//		    preparedStatement = connect
//		    		.prepareStatement("insert into  Physician(physician_id, first, last, position, department_id) values (?, ?, ?, ?, ?)");
//		    preparedStatement.setInt(1, 02);
//			preparedStatement.setString(2, "Jesus");
//			preparedStatement.setString(3, "Low");
//			preparedStatement.setString(4, "Neurosurgeon");
//			preparedStatement.setInt(5, 01);
//			preparedStatement.executeUpdate(); // RUN A TRANSACTION	
//
//		 	
//		 	/* Insert Patients */
//		    
//		    preparedStatement = connect
//		    		.prepareStatement("insert into  Patient(patient_id, first, last, age, gender, address, phone) values (?, ?, ?, ?, ?, ?, ?)");
//		    preparedStatement.setInt(1, 01);
//			preparedStatement.setString(2, "Cynthia");
//			preparedStatement.setString(3, "Lopez");
//			preparedStatement.setInt(4, 23);
//			preparedStatement.setString(5, "Female");
//			preparedStatement.setString(6, "123 N. Wabash");
//			preparedStatement.setInt(7, 555555555);
//			preparedStatement.executeUpdate(); // RUN A TRANSACTION	
//			      
//			preparedStatement = connect
//		    		.prepareStatement("insert into  Patient(patient_id, first, last, age, gender, address, phone) values (?, ?, ?, ?, ?, ?, ?)");
//			preparedStatement.setInt(1, 02);
//			preparedStatement.setString(2, "Jane");
//			preparedStatement.setString(3, "Doe");
//			preparedStatement.setInt(4, 15);
//			preparedStatement.setString(5, "NA");
//			preparedStatement.setString(6, "123 Elmo st.");
//			preparedStatement.setInt(7, 123456789);
//			preparedStatement.executeUpdate(); // RUN A TRANSACTION	
//			
//			preparedStatement = connect
//		    		.prepareStatement("insert into  Patient(patient_id, first, last, age, gender, address, phone) values (?, ?, ?, ?, ?, ?, ?)");
//			preparedStatement.setInt(1, 03);
//			preparedStatement.setString(2, "John");
//			preparedStatement.setString(3, "Doe");
//			preparedStatement.setInt(4, 11);
//			preparedStatement.setString(5, "Male");
//			preparedStatement.setString(6, "123 N. Wabash");
//			preparedStatement.setInt(7, 123456789);
//			preparedStatement.executeUpdate(); // RUN A TRANSACTION	
			
		    
		} catch(SQLException e) {
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    
	    		
	}
	

		
}
