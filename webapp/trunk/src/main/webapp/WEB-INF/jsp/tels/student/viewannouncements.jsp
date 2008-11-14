<%@ include file="../include.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" />
<html xml:lang="en" lang="en">

<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<title>View Announcements</title>

<%@ include file="styles.jsp"%>

<script src=".././javascript/tels/general.js" type="text/javascript" > </script>
<script src=".././javascript/tels/prototype.js" type="text/javascript" > </script>
<script src=".././javascript/tels/effects.js" type="text/javascript" > </script>

</head>

<body class="yui-skin-sam">

<%@ include file="./studentHeader.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div id="centeredDiv" align="center">

<h3>Announcements for ${run.sdsOffering.name}</h3>
<br>
<div id="existingAnnouncements">
	<c:choose>
		<c:when test="${fn:length(run.announcements) > 0}">
			<c:forEach var="announcement" items="${run.announcements}">
				<span id="head"><h4>${announcement.title} </h4> ${announcement.timestamp}</span>
				<br>
				${announcement.announcement}
				<br><br>
			</c:forEach>
		</c:when>
		<c:otherwise>
			No existing announcements found for this run.
		</c:otherwise>
	</c:choose>

</div>
</div>
</body>
</html>