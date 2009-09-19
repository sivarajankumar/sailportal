<%@ include file="../include.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" />
<html xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />

<link href="../<spring:theme code="globalstyles"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="../<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet" type="text/css" />
<link href="../<spring:theme code="teacherprojectstylesheet" />" media="screen" rel="stylesheet" type="text/css" />
<link href="../<spring:theme code="teacherhomepagestylesheet" />" media="screen" rel="stylesheet" type="text/css" />
    
<script type="text/javascript" src="../javascript/tels/general.js"></script>
<script type="text/javascript" src="../javascript/tels/effects.js"></script>
    
<title><spring:message code="application.title" /></title>

<script type='text/javascript' src='/webapp/dwr/interface/ChangePasswordParametersValidatorJS.js'></script>
<script type='text/javascript' src='/webapp/dwr/engine.js'></script>

</head>
<body>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="adminheader.jsp"%>

<h5 style="color:#0000CC;"><a href="index.html">Return to Main Menu</a></h5>

<div>Total number of users: ${fn:length(usernames)}</div>
<table id="teachersTable" border="2">
	<c:forEach var="username" items="${usernames}">
		<tr>
			<td>${username}</td>
			<td><a href="#"
				onclick="javascript:popup640('../teacher/management/changestudentpassword.html?userName=${username}');">Change
			Password</a></td>
			<td><a href="../j_acegi_switch_user?j_username=${username}">Log
			in as this user</a></td>
			<td><a href="#"
				onclick="javascript:popup640('../teacherinfo.html?userName=${username}');">info</a></td>
		</tr>
	</c:forEach>
</table></body>
</html>