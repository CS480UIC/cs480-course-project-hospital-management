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

public class QueriesDao {
	private static String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	private static String DB_URL = "jdbc:mysql://localhost/Hospital_Management?serverTimezone=America/Chicago";

	//  Database credentials
	private static String USER = "root";
	private static String PASS = "Cps40008713!";
	private static Connection jdbcconnect;
	
	public QueriesDao() {
		
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
	
	
	public List<head> listAllHead() throws SQLException, InstantiationException, IllegalAccessException {
        List<head> listHead = new ArrayList<>();
         
        String sql = "SELECT c.name, t.first, t.last FROM Department c, Physician t Where c.Head = t.EmployeeID";
         
        connect();
         
        Statement statement = jdbcconnect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            String headName = resultSet.getString(1);
            String first = resultSet.getString(2);
            String last = resultSet.getString(3);
            
            System.out.println(headName);
            System.out.println(first);
            System.out.println(last);
             
            head Head = new head(headName, first, last);
            listHead.add(Head);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return listHead;
    }

}
