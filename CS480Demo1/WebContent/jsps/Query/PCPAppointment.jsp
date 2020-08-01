<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.sql.*" %>
<% Class.forName("com.mysql.jdbc.Driver"); %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>Primary Physician</title>
</head>
<body>
    <center>
        <h1>Primary Physician</h1>
        <h2>
            <a href="${pageContext.request.contextPath}/jsps/main.jsp">Home</a> 
        </h2>
    </center>
    <% 
            Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost/Hospital_Management?serverTimezone=America/Chicago", "root", "Cps40008713!");

            Statement statement = connection.createStatement();
            String query = "SELECT c.first, \n" + 
            		"	c.last,\n" + 
            		"	p.first,\n" + 
            		"	p.last,\n" + 
            		"	n.first,\n" + 
            		"	n.last\n" + 
            		"FROM Appointment a\n" + 
            		"JOIN Patient c ON a.Patient = c.SSN\n" + 
            		"JOIN Nurse n ON a.NurseAssist = n.EmployeeID\n" + 
            		"JOIN Physician p ON a.Physician = p.EmployeeID\n" + 
            		"WHERE a.Patient IN\n" + 
            		"(Select Patient from Appointment a\n" + 
            		"GROUP BY a.Patient\n" + 
            		"HAVING count(*)>=2)\n" + 
            		"ORDER BY c.last";
            		
            ResultSet resultset = statement.executeQuery(query); 
    %>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>Appointment with Primary Physician w/ more than 1 visit</h2></caption>
            <tr>
                <th>Patient's First Name</th>
                <th>Patient's Last Name</th>
                <th>Physician's First Name</th>
                <th>Physician's Last Name</th>
                <th>Nurse's First Name</th>
                <th>Nurse's Last Name</th>
            </tr>
            <% while(resultset.next()){ %>
            <TR>
                <TD> <%= resultset.getString(1) %></TD>
                <TD> <%= resultset.getString(2) %></TD>
                <TD> <%= resultset.getString(3) %></TD>
                <TD> <%= resultset.getString(4) %></TD>
                <TD> <%= resultset.getString(5) %></TD>
                <TD> <%= resultset.getString(6) %></TD>
            </TR>
            <% } %>
        </table>
    </div>   
</body>
</html>