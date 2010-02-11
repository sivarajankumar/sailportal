<%@ include file="../include.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" />
<html xml:lang="en" lang="en">
<head>
    <%@ include file="../admin/adminhead.jsp" %>

</head>
<body>
<%@ include file="../admin/adminheader.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div id="centeredDiv">

    <c:choose>
        <c:when test="${fn:length(students) > 0}">
            <table id="teachersTable" border="2">
                <h5>Students</h5>
                <tr>
                    <th></th>
                    <th>User name</th>
                    <th>First name</th>
                    <th>Last name</th>
                </tr>
                <c:forEach var="student" items="${students}">
                    <tr>
                        <td><img src="/webapp/common/filestreamer.html?username=${student.userDetails.username}&showIcon"/></td>
                        <td>${student.userDetails.username}</td>
                        <td>${student.userDetails.firstname} </td>
                        <td>${student.userDetails.lastname}</td>
                    </tr>
                </c:forEach>
            </table>
            <br>
        </c:when>
    </c:choose>


</div>

</div>

</body>

</html>




