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
            <a href="<%=request.getContextPath()%>/new">Add New Patient</a>
            &nbsp;&nbsp;&nbsp;
            <a href="<%=request.getContextPath()%>/list">List All Patients</a>
             
        </h2>
    </center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>Patient's Info</h2></caption>
            <tr>
                <th>ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Age</th>
                <th>Gender</th>
                <th>Address</th>
                <th>Phone</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="patient" items="${listPatients}">
                <tr>
                    <td><c:out value="${patient.id}" /></td>
                    <td><c:out value="${patient.first}" /></td>
                    <td><c:out value="${patient.last}" /></td>
                    <td><c:out value="${patient.age}" /></td>
                    <td><c:out value="${patient.gender}" /></td>
                    <td><c:out value="${patient.address}" /></td>
                    <td><c:out value="${patient.phone}" /></td>
                    <td>
                        <a href="<%=request.getContextPath()%>/edit?id=<c:out value='${patient.id}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="<%=request.getContextPath()%>/delete?id=<c:out value='${patient.id}' />">Delete</a>                     
                    </td> 
                </tr>
            </c:forEach>
        </table>
    </div>   
</body>
</html>