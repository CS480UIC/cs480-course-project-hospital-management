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

public class PhysicianDao {
	private static String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	private static String DB_URL = "jdbc:mysql://localhost/Hospital_Management?serverTimezone=America/Chicago";

	//  Database credentials
	private static String USER = "root";
	private static String PASS = "Cps40008713!";
	private static Connection jdbcconnect;
	private PreparedStatement preparedStatement;
	
	public PhysicianDao() {
		
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
	
	public boolean insertPhysician(Physician physician) throws SQLException, InstantiationException, IllegalAccessException {
        String sql = "INSERT INTO Physician (first, last, position, ssn) values (?, ?, ?, ?)";
        connect();
        
        preparedStatement = jdbcconnect.prepareStatement(sql);
         
		preparedStatement.setString(1, physician.getFirst());
		preparedStatement.setString(2, physician.getLast());
		preparedStatement.setString(3, physician.getPosition());
		preparedStatement.setInt(4, physician.getSsn());
         
        boolean rowInserted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        disconnect();
        return rowInserted;
    }
	
	public List<Physician> listAllPhysician() throws SQLException, InstantiationException, IllegalAccessException {
        List<Physician> listPhysician = new ArrayList<>();
         
        String sql = "SELECT * FROM Physician";
         
        connect();
         
        Statement statement = jdbcconnect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            int physician_id = resultSet.getInt("EmployeeID");
            String first = resultSet.getString("first");
            String last = resultSet.getString("last");
            String position = resultSet.getString("position");
            int ssn = resultSet.getInt("ssn");
            
             
            Physician physician = new Physician(physician_id, first, last, position, ssn);
            listPhysician.add(physician);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return listPhysician;
    }
	
	public boolean deletePhysician(int id) throws SQLException, InstantiationException, IllegalAccessException {
        String sql = "DELETE FROM Physician WHERE EmployeeID=?";
         
        connect();
         
        PreparedStatement statement = jdbcconnect.prepareStatement(sql);
        statement.setInt(1, id);
         
        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;     
    }
	
	public boolean updatePhysician(Physician physician) throws SQLException, InstantiationException, IllegalAccessException {
        String sql = "UPDATE Physician SET first=?, last=?, position=?, ssn=? WHERE EmployeeID=?";
        
        connect();
         
        PreparedStatement statement = jdbcconnect.prepareStatement(sql);
        statement.setString(1, physician.getFirst());
        statement.setString(2, physician.getLast());
        statement.setString(3, physician.getPosition());
        statement.setInt(4, physician.getSsn());
        statement.setInt(5, physician.getId());
         
        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;     
    }
	
	public Physician getPhysician(int id) throws SQLException {
		Physician physician = null;
        String sql = "SELECT * FROM Physician WHERE EmployeeID=?";
         
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
            String position = resultSet.getString("position");
            int ssn = resultSet.getInt("ssn");
             
            physician = new Physician(id, first, last, position, ssn);
        }
         
        resultSet.close();
        statement.close();
        disconnect();
        return physician;
    }
		
	
	

}
