<%@ include file="include.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" />
<html xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />

<link href="../../<spring:theme code="globalstyles"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="../../<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet" type="text/css" />
<link href="../../<spring:theme code="teacherprojectstylesheet" />" media="screen" rel="stylesheet" type="text/css" />
    
<script type="text/javascript" src="../../javascript/tels/general.js"></script>
<script type="text/javascript" src="../../javascript/tels/effects.js"></script>

    
<title><spring:message code="application.title" /></title>

<script type='text/javascript' src='/webapp/dwr/interface/ChangePasswordParametersValidatorJS.js'></script>
<script type='text/javascript' src='/webapp/dwr/engine.js'></script>
<script>
//alert('hi');
//alert(ChangePasswordParametersValidatorJS.test('hi'))
</script>

</head>
<body>

<br/> <br/>

<h1>Change My Password</h1>

<div id="errorMessageFormat">
	<!-- Support for Spring errors object -->
	<spring:bind path="changeStudentPasswordParameters.*">
  		<c:forEach var="error" items="${status.errorMessages}">
   			 <br /><c:out value="${error}"/>
   		</c:forEach>
	</spring:bind>
</div>

<div id="popUpWindowTeacherPassword">

	<div id="teacherchangepasswordbox">
	<dl>
		<form:form method="post" action="changestudentpassword.html" commandName="changeStudentPasswordParameters" id="changepassword">
		<dt><label for="changepassword1"><spring:message code="changepassword.password1" /></label></dt>
      	<dd><form:password path="passwd1" id="teacherchangePasswordField1"/></dd>

		<dt><label for="changepassword2"><spring:message code="changepassword.password2" /></label></dt>
		<dd><form:password path="passwd2" id="teacherchangePasswordField2"/></dd>
	</dl>
	
    <div id="teacherPasswordButtons">
		    <input type="button" id="save">Save</input>
		    
		    <input type="button" id="cancel">Cancel</input>
		    
 -->
    </div>

	</form:form>
	 	
 	</div>
 	
</div>	<!--end of popUpWindowTeacherPassword div-->

</body>
</html>