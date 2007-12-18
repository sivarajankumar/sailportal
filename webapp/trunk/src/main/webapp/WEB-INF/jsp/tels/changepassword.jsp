<%@ include file="include.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" />
<html xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />

<link href="../<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet" type="text/css" />
    
<script type="text/javascript" src="./javascript/tels/rotator.js"></script>
    
<title><spring:message code="application.title" /></title>
</head>

<body>


<!-- Support for Spring errors object -->
<spring:bind path="changePasswordParameters.*">
  <c:forEach var="error" items="${status.errorMessages}">
    <b>
      <br /><c:out value="${error}"/>
    </b>
  </c:forEach>
</spring:bind>

<div id="centeredDiv">

<div id="popUpWindowViewStudents">

	<div id="studentchangepasswordbox">
	
	<form:form method="post" action="changepassword.html" commandName="changePasswordParameters" id="changepassword">
		<dl>
		<dt><label for="changePasswordField"><spring:message code="changepassword.password1" /></label></dt>
		<dd><form:password path="passwd2" id="changePasswordField"/> </dd>
		<dt><label for="changePasswordField"><spring:message code="changepassword.password2" /></label></dt>
		<dd><form:password path="passwd2" id="changePasswordField"/></dd>
 		</dl>
 	
 	   <input type="image" id="save" src="../<spring:theme code="register_save" />" 
    	onmouseover="swapSaveImage('save',1)"onmouseout="swapSaveImage('save',0)"   />

	</form:form>
 	
 	</div>
 	
</div>	<!--end of popUpWindow1 div-->

</div>
</body>
</html>