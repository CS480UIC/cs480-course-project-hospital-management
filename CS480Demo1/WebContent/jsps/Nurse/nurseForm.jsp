<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>Nurse Management Application</title>
</head>
<body>
    <center>
        <h1>Nurse Management</h1>
        <h2>
            <a href="${pageContext.request.contextPath}/nurseServlet?nursing=1">Add New Nurse</a>
            &nbsp;&nbsp;&nbsp;
            <a href="${pageContext.request.contextPath}/nurseServlet?nursing=6">List All Nurse</a>
             
        </h2>
    </center>
    <div align="center">
        <c:if test="${nurse != null}">
            <form action="${pageContext.request.contextPath}/nurseServlet?nursing=5" method="post">
        </c:if>
        <c:if test="${nurse == null}">
            <form action="${pageContext.request.contextPath}/nurseServlet?nursing=2" method="post">
        </c:if>
        <table border="1" cellpadding="5">
            <caption>
                <h2>
                    <c:if test="${nurse != null}">
                        Edit Nurse
                    </c:if>
                    <c:if test="${nurse == null}">
                        Add New Nurse
                    </c:if>
                </h2>
            </caption>
                <c:if test="${nurse != null}">
                    <input type="hidden" name="id" value="<c:out value='${nurse.id}' />" />
                </c:if>           
            <tr>
                <th>First: </th>
                <td>
                    <input type="text" name="first" size="45"
                            value="<c:out value='${nurse.first}' />"
                        />
                </td>
            </tr>
             <tr>
                <th>Last: </th>
                <td>
                    <input type="text" name="last" size="45"
                            value="<c:out value='${nurse.last}' />"
                        />
                </td>
            </tr>
            <tr>
                <th>Position: </th>
                <td>
                    <input type="text" name="position" size="45"
                            value="<c:out value='${nurse.position}' />"
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