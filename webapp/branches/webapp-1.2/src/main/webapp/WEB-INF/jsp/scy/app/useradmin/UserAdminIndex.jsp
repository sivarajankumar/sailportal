<%@ include file="/WEB-INF/jsp/scy/include.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" />
<html xml:lang="en" lang="en">
<head>
    <%@ include file="/WEB-INF/jsp/scy/admin/adminhead.jsp" %>

</head>
<body>
<%@ include file="/WEB-INF/jsp/scy/admin/adminheader.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div id="centeredDiv">
    <h1>User Administration</h1>
    <table>
        <tr>
            <th>
                Username
            </th>
            <th>
                First name
            </th>
            <th>
                Last name
            </th>
        </tr>
    <c:forEach items="${users}" var="user">
        <tr>
            <td>
                <a href="EditStudent.html?username=${user.userDetails.username}">
                    <c:out value="${user.userDetails.username}"/>
                </a>
            </td>
            <td>

            </td>
            <td>

            </td>
        </tr>
    </c:forEach>
    </table>


</div>

</body>
</html>

