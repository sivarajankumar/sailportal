<%@ include file="../../include.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" />

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html lang="en">
<title>Total time spent per step graph</title>
<head></head>

<body>
Total time spent per step for Team with members:
<c:forEach var="student" varStatus="studentStatus" items="${workgroup.members}">
	${student.userDetails.firstname} ${student.userDetails.lastname}
	<c:if test="${studentStatus.last=='false'}">&</c:if>
</c:forEach>
<br><br>${url}<br>

<img src="${url}"/>

</body>
</html>