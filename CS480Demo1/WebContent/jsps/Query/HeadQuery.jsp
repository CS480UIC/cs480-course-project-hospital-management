<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.sql.*" %>
<% Class.forName("com.mysql.jdbc.Driver"); %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>Head Department</title>
</head>
<body>
    <center>
        <h1>Head Department</h1>
        <h2>
            <a href="${pageContext.request.contextPath}/jsps/main.jsp">Home</a> 
        </h2>
    </center>
    <% 
            Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost/Hospital_Management?serverTimezone=America/Chicago", "root", "Cps40008713!");

            Statement statement = connection.createStatement();
            String query = "SELECT c.name, t.first, t.last FROM Department c, Physician t Where c.Head = t.EmployeeID";

            ResultSet resultset = statement.executeQuery(query); 
    %>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>Head Physicians</h2></caption>
            <tr>
                <th>Department Name</th>
                <th>Physician's First Name</th>
                <th>Physician's Last Name</th>
            </tr>
            <% while(resultset.next()){ %>
            <TR>
                <TD> <%= resultset.getString(1) %></TD>
                <TD> <%= resultset.getString(2) %></TD>
                <TD> <%= resultset.getString(3) %></TD>
            </TR>
            <% } %>
        </table>
    </div>   
</body>
</html>