<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>Patient Management Application</title>
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
        <c:if test="${patient != null}">
            <form action="update" method="post">
        </c:if>
        <c:if test="${patient == null}">
            <form action="insert" method="post">
        </c:if>
        <table border="1" cellpadding="5">
            <caption>
                <h2>
                    <c:if test="${patient != null}">
                        Edit Patient
                    </c:if>
                    <c:if test="${patient == null}">
                        Add New Patient
                    </c:if>
                </h2>
            </caption>
                <c:if test="${patient != null}">
                    <input type="hidden" name="id" value="<c:out value='${patient.id}' />" />
                </c:if>           
            <tr>
                <th>First: </th>
                <td>
                    <input type="text" name="first" size="45"
                            value="<c:out value='${patient.first}' />"
                        />
                </td>
            </tr>
             <tr>
                <th>Last: </th>
                <td>
                    <input type="text" name="last" size="45"
                            value="<c:out value='${patient.last}' />"
                        />
                </td>
            </tr>
            <tr>
                <th>Age: </th>
                <td>
                    <input type="text" name="age" size="45"
                            value="<c:out value='${patient.age}' />"
                        />
                </td>
            </tr>
            <tr>
                <th>Gender: </th>
                <td>
                    <input type="text" name="gender" size="45"
                            value="<c:out value='${patient.gender}' />"
                        />
                </td>
            </tr>
            <tr>
                <th>Address: </th>
                <td>
                    <input type="text" name="address" size="45"
                            value="<c:out value='${patient.address}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Phone: </th>
                <td>
                    <input type="text" name="phone" size="45"
                            value="<c:out value='${patient.phone}' />"
                    />
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Save" />
                </td>
            </tr>
        </table>
        </form>
    </div>   
</body>
</html>