<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>Patient Hospital Application</title>
</head>
<body>
    <center>
        <h1>Patient Management</h1>
        <h2>
        	<a href="${pageContext.request.contextPath}/new">Add New Patient</a>
            &nbsp;&nbsp;&nbsp;
            <a href="${pageContext.request.contextPath}/list">List All Patient</a>
            &nbsp;&nbsp;&nbsp;
            <a href="${pageContext.request.contextPath}/jsps/main.jsp">Home</a>
             
        </h2>
    </center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption>
            	<h1>Delete Patient</h1>
            	<h2>Are you sure?</h2>
            
            </caption>
            <tr>
                <th>SSN</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Age</th>
                <th>Gender</th>
                <th>Address</th>
                <th>Phone</th>
                <th>Physician ID</th>
                <th>Actions</th>
            </tr>
                <tr>
                    <td><c:out value="${patient.id}" /></td>
                    <td><c:out value="${patient.first}" /></td>
                    <td><c:out value="${patient.last}" /></td>
                    <td><c:out value="${patient.age}" /></td>
                    <td><c:out value="${patient.gender}" /></td>
                    <td><c:out value="${patient.address}" /></td>
                    <td><c:out value="${patient.phone}" /></td>
                    <td><c:out value="${patient.physician}" /></td>
                    <td>
                        <a href="${pageContext.request.contextPath}/delete?id=<c:out value='${patient.id}' />">Delete</a>                    
                    </td> 
                </tr>
        </table>
    </div>   
</body>
</html>