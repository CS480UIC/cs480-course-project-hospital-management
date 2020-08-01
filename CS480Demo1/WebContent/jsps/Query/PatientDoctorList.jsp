<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.sql.*" %>
<% Class.forName("com.mysql.jdbc.Driver"); %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>Patient's physician total</title>
</head>
<body>
    <center>
        <h1>More than one Physician </h1>
        <h2>
            <a href="${pageContext.request.contextPath}/jsps/main.jsp">Home</a> 
        </h2>
    </center>
    <% 
            Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost/Hospital_Management?serverTimezone=America/Chicago", "root", "Cps40008713!");

            Statement statement = connection.createStatement();
            String query = "SELECT t.last, count(c.Patient) FROM Appointment c JOIN Patient t ON c.Patient = t.SSN GROUP BY t.last HAVING count(c.Patient)>=2";

            ResultSet resultset = statement.executeQuery(query); 
    %>
    <div align="center">
        <table border="1" cellpadding="5">
            <tr>
                <th>Patient's Last Name</th>
                <th>Number of Physicians</th>
            </tr>
            <% while(resultset.next()){ %>
            <TR>
                <TD> <%= resultset.getString(1) %></TD>
                <TD> <%= resultset.getString(2) %></TD>
            </TR>
            <% } %>
        </table>
    </div>   
</body>
</html>