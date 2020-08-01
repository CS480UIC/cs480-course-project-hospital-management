<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>Physician Hospital Application</title>
</head>
<body>
    <center>
        <h1>Physician Management</h1>
        <h2>
        	<a href="${pageContext.request.contextPath}/physicianServlet?physiciannew=1">Add New Physician</a>
            &nbsp;&nbsp;&nbsp;
            <a href="${pageContext.request.contextPath}/physicianServlet?physiciannew=6">List All Physician</a>
            &nbsp;&nbsp;&nbsp;
            <a href="${pageContext.request.contextPath}/jsps/main.jsp">Home</a>
             
        </h2>
    </center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption>
            	<h1>Delete Physician</h1>
            	<h2>Are you sure?</h2>
            
            </caption>
            <tr>
                <th>ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Position</th>
                <th>SSN</th>
                <th>Actions</th>
            </tr>
                <tr>
                    <td><c:out value="${physician.id}" /></td>
                    <td><c:out value="${physician.first}" /></td>
                    <td><c:out value="${physician.last}" /></td>
                    <td><c:out value="${physician.position}" /></td>
                    <td><c:out value="${physician.ssn}" /></td>
                    <td>
                        <a href="${pageContext.request.contextPath}/physicianServlet?physiciannew=7&id=<c:out value='${physician.id}' />">Delete</a>                     
                    </td> 
                </tr>
        </table>
    </div>   
</body>
</html>