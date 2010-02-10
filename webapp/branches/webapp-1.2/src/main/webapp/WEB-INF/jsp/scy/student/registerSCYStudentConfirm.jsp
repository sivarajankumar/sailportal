<%@ include file="../include.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" />
<html xml:lang="en" lang="en">
<head>
<%@ include file="../admin/adminhead.jsp" %>

</head>
<body>
<%@ include file="../admin/adminheader.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div id="centeredDiv">

    <table>
        <tr>
            <td>First name</td>
            <td>${firstname}</td>
        </tr>
        <tr>
            <td>Last name</td>
            <td>${lastname}</td>
        </tr>
        <tr>
            <td>Username</td>
            <td>${username}</td>
        </tr>
        <tr>
            <td>Password</td>
            <td>${password}</td>
        </tr>
    </table>

    <a href="/webapp/index.html">Click to log in</a>


</body>
</html>

