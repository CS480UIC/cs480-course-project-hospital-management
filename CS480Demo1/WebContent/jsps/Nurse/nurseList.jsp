<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>Nurse Hospital Application</title>
</head>
<body>
    <center>
        <h1>Nurse Management</h1>
        <h2>
        	<a href="${pageContext.request.contextPath}/nurseServlet?nursing=1">Add New Nurse</a>
            &nbsp;&nbsp;&nbsp;
            <a href="${pageContext.request.contextPath}/nurseServlet?nursing=6">List All Nurse</a>
            &nbsp;&nbsp;&nbsp;
            <a href="${pageContext.request.contextPath}/jsps/main.jsp">Home</a>
             
        </h2>
    </center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2> Nurse's Info</h2></caption>
            <tr>
                <th>ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Position</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="nurse" items="${listNurses}">
                <tr>
                    <td><c:out value="${nurse.id}" /></td>
                    <td><c:out value="${nurse.first}" /></td>
                    <td><c:out value="${nurse.last}" /></td>
                    <td><c:out value="${nurse.position}" /></td>
                    <td>
                        <a href="${pageContext.request.contextPath}/nurseServlet?nursing=4&id=<c:out value='${nurse.id}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="${pageContext.request.contextPath}/nurseServlet?nursing=7&id=<c:out value='${nurse.id}' />">Delete</a>                     
                    </td> 
                </tr>
            </c:forEach>
        </table>
    </div>   
</body>
</html>