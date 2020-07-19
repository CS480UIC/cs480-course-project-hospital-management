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

public class NurseDao {
	private static String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	private static String DB_URL = "jdbc:mysql://localhost/Hospital_Management?";

	//  Database credentials
	private static String USER = "root";
	private static String PASS = "Cps40008713!";
	private static Connection jdbcconnect;
	private PreparedStatement preparedStatement;
	
	public NurseDao() {
		
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
	
	public boolean insertNurse(Nurse nurse) throws SQLException, InstantiationException, IllegalAccessException {
        String sql = "INSERT INTO Nurse (first, last, position) values (?, ?, ?)";
        connect();
        
        preparedStatement = jdbcconnect.prepareStatement(sql);
         
		preparedStatement.setString(1, nurse.getFirst());
		preparedStatement.setString(2, nurse.getLast());
		preparedStatement.setString(3, nurse.getPosition());
         
        boolean rowInserted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        disconnect();
        return rowInserted;
    }
	
	public List<Nurse> listAllNurse() throws SQLException, InstantiationException, IllegalAccessException {
        List<Nurse> listNurse = new ArrayList<>();
         
        String sql = "SELECT * FROM Nurse";
         
        connect();
         
        Statement statement = jdbcconnect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            int nurse_id = resultSet.getInt("nurse_id");
            String first = resultSet.getString("first");
            String last = resultSet.getString("last");
            String position = resultSet.getString("position");
            
             
            Nurse nurse = new Nurse(nurse_id, first, last, position);
            listNurse.add(nurse);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return listNurse;
    }
	
	public boolean deleteNurse(int id) throws SQLException, InstantiationException, IllegalAccessException {
        String sql = "DELETE FROM Nurse WHERE nurse_id=?";
         
        connect();
         
        PreparedStatement statement = jdbcconnect.prepareStatement(sql);
        statement.setInt(1, id);
         
        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;     
    }
	
	public boolean updateNurse(Nurse nurse) throws SQLException, InstantiationException, IllegalAccessException {
        String sql = "UPDATE Nurse SET first=?, last=?, position=? WHERE nurse_id=?";
        
        connect();
         
        PreparedStatement statement = jdbcconnect.prepareStatement(sql);
        statement.setString(1, nurse.getFirst());
        statement.setString(2, nurse.getLast());
        statement.setString(3, nurse.getPosition());
        statement.setInt(4, nurse.getId());
         
        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;     
    }
	
	public Nurse getNurse(int id) throws SQLException {
		Nurse nurse = null;
        String sql = "SELECT * FROM Nurse WHERE nurse_id=?";
         
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
             
            nurse = new Nurse(id, first, last, position);
        }
         
        resultSet.close();
        statement.close();
        disconnect();
        return nurse;
    }
		
	
	

}
