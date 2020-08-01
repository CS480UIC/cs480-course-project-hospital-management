<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>Physician Management Application</title>
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
        <c:if test="${physician != null}">
            <form action="${pageContext.request.contextPath}/physicianServlet?physiciannew=5" method="post">
        </c:if>
        <c:if test="${physician == null}">
            <form action="${pageContext.request.contextPath}/physicianServlet?physiciannew=2" method="post">
        </c:if>
        <table border="1" cellpadding="5">
            <caption>
                <h2>
                    <c:if test="${physician != null}">
                        Edit Physician
                    </c:if>
                    <c:if test="${physician == null}">
                        Add New Physician
                    </c:if>
                </h2>
            </caption>
                <c:if test="${physician != null}">
                    <input type="hidden" name="id" value="<c:out value='${physician.id}' />" />
                </c:if>           
            <tr>
                <th>First: </th>
                <td>
                    <input type="text" name="first" size="45"
                            value="<c:out value='${physician.first}' />"
                        />
                </td>
            </tr>
             <tr>
                <th>Last: </th>
                <td>
                    <input type="text" name="last" size="45"
                            value="<c:out value='${physician.last}' />"
                        />
                </td>
            </tr>
            <tr>
                <th>Position: </th>
                <td>
                    <input type="text" name="position" size="45"
                            value="<c:out value='${physician.position}' />"
                        />
                </td>
            </tr>
            <tr>
                <th>SSN: </th>
                <td>
                    <input type="text" name="ssn" size="45"
                            value="<c:out value='${physician.ssn}' />"
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