<%@ include file="../include.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" >
<html xml:lang="en" lang="en">
<head>

<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<title>Create Announcement</title>

<script src="../javascript/tels/general.js" type="text/javascript"> </script>
<script src="../javascript/tels/prototype.js" type="text/javascript"> </script>
</head>

<body class="yui-skin-sam">
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<spring:bind path="announcementParameters.*">
  <c:forEach var="error" items="${status.errorMessages}">
    <b>
      <br /><c:out value="${error}"/>
    </b>
  </c:forEach>
</spring:bind>

<div id="centeredDiv">

<h3>Create Announcement</h3>
<form:form method="post" action="createannouncement.html" commandName="announcementParameters" id="createannouncement">
	<label for="titleField">Title</label>
	<form:input path="title" id="titleField"/>
	<label for="announcementField">Announcement</label>
	<form:input path="announcement" id="announcementField"/>
	<input type="image" id="save" src="../<spring:theme code="register_save" />" 
    	onmouseover="swapSaveImage('save',1)"onmouseout="swapSaveImage('save',0)"/>
</form:form>

</div>
</body>