package user.dao;

import user.domain.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PatientDao {
	private static String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	private static String DB_URL = "jdbc:mysql://localhost/Hospital_Management?serverTimezone=America/Chicago";

	//  Database credentials
	private static String USER = "root";
	private static String PASS = "Cps40008713!";
	private static Connection jdbcconnect;
	private PreparedStatement preparedStatement;
	
	public PatientDao() {
		
	}
	
	public static void connect() throws InstantiationException, IllegalAccessException  {
		
		try {
			//Register JDBC driver
			Class.forName(JDBC_DRIVER);
			
			//Open a connection
		    System.out.println("Connecting to database...");
		    jdbcconnect = DriverManager.getConnection(DB_URL, USER, PASS);
		    
		} catch(SQLException e) {
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void disconnect() throws SQLException {
		if(jdbcconnect != null && !jdbcconnect.isClosed()) {
			jdbcconnect.close();
		}
	}
	
	public boolean insertPatient(Patient patient) throws SQLException, InstantiationException, IllegalAccessException {
        String sql = "INSERT INTO Patient (first, last, age, gender, address, phone, physician) values (?, ?, ?, ?, ?, ?, ?)";
        connect();
        
        preparedStatement = jdbcconnect.prepareStatement(sql);
         
		preparedStatement.setString(1, patient.getFirst());
		preparedStatement.setString(2, patient.getLast());
		preparedStatement.setInt(3, patient.getAge());
		preparedStatement.setString(4, patient.getGender());
		preparedStatement.setString(5, patient.getAddress());
		preparedStatement.setString(6, patient.getPhone());
		preparedStatement.setInt(7, patient.getPhysician());
         
        boolean rowInserted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        disconnect();
        return rowInserted;
    }
	
	public List<Patient> listAllPatients() throws SQLException, InstantiationException, IllegalAccessException {
        List<Patient> listPatient = new ArrayList<>();
         
        String sql = "SELECT * FROM Patient";
         
        connect();
         
        Statement statement = jdbcconnect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            int patient_id = resultSet.getInt("SSN");
            String first = resultSet.getString("first");
            String last = resultSet.getString("last");
            int age = resultSet.getInt("age");
            String gender = resultSet.getString("gender");
            String address = resultSet.getString("address");
            String phone = resultSet.getString("phone");
            int physician = resultSet.getInt("physician");
            
             
            Patient patient = new Patient(patient_id, first, last, age, gender, address, phone, physician);
            listPatient.add(patient);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return listPatient;
    }
	
	public boolean deletePatient(int id) throws SQLException, InstantiationException, IllegalAccessException {
        String sql = "DELETE FROM Patient where SSN = ?";
         
        connect();
         
        PreparedStatement statement = jdbcconnect.prepareStatement(sql);
        statement.setInt(1, id);
         
        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;     
    }
	
	public boolean updatePatient(Patient patient) throws SQLException, InstantiationException, IllegalAccessException {
        String sql = "UPDATE Patient SET first=?, last=?, age=?, gender=?, address=?, phone=?, physician=? WHERE SSN=?";
        
        connect();
         
        PreparedStatement statement = jdbcconnect.prepareStatement(sql);
        statement.setString(1, patient.getFirst());
        statement.setString(2, patient.getLast());
        statement.setInt(3, patient.getAge());
        statement.setString(4, patient.getGender());
        statement.setString(5, patient.getAddress());
        statement.setString(6, patient.getPhone());
        statement.setInt(7, patient.getPhysician());
        statement.setInt(8, patient.getId());
         
        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;     
    }
	
	public Patient getPatient(int id) throws SQLException {
        Patient patient = null;
        String sql = "SELECT * FROM Patient WHERE SSN = ?";
         
        try {
			connect();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
        PreparedStatement statement = jdbcconnect.prepareStatement(sql);
        statement.setInt(1, id);
         
        ResultSet resultSet = statement.executeQuery();
         
        if (resultSet.next()) {
            String first = resultSet.getString("first");
            String last = resultSet.getString("last");
            int age = resultSet.getInt("age");
            String gender = resultSet.getString("gender");
            String address = resultSet.getString("address");
            String phone = resultSet.getString("phone");
            int physician = resultSet.getInt("physician");
             
            patient = new Patient(id, first, last, age, gender, address, phone, physician);
        }
         
        resultSet.close();
        statement.close();
        disconnect();
        return patient;
    }
		
	
	

}
