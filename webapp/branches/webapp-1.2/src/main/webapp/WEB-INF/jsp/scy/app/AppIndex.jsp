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
    <h1>Welcome to SCY!</h1>
    ${currentUser.userDetails.username} <img src="/webapp/${currentUser.userDetails.profilePictureUrl}"/>
</div>

</body>
</html>

