<%@ include file="include.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" />
<html xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />

<link href="<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet"
    type="text/css" />
    
<script type="text/javascript" src="./javascript/tels/rotator.js"></script>
    
<title><spring:message code="application.title" /></title>
<script type='text/javascript' src='/webapp/dwr/interface/ChangePasswordParametersValidatorJS.js'></script>
<script type='text/javascript' src='/webapp/dwr/engine.js'></script>
<script>
//alert('hi');
//alert(ChangePasswordParametersValidatorJS.test('hi'))
</script>

</head>
<body>
<h2>Change Password</h2>

<!-- Support for Spring errors object -->
<spring:bind path="changeStudentPasswordParameters.*">
  <c:forEach var="error" items="${status.errorMessages}">
    <b>
      <br /><c:out value="${error}"/>
    </b>
  </c:forEach>
</spring:bind>

	<form:form method="post" action="changestudentpassword.html" commandName="changeStudentPasswordParameters" id="changestudentpassword">
	<div><label for="changestudentpassword"><spring:message code="changepassword.password1" /></label>
      <form:password path="passwd1" id="changestudentpassword"/>
	</div>
	
	<div><label for="changestudentpassword"><spring:message code="changepassword.password2" /></label>
		<form:password path="passwd2" id="changestudentpassword"/>
	</div>

    <div><input type="image" id="save" src="../../<spring:theme code="register_save" />" 
    onmouseover="swapSaveImage('save',1)" 
    onmouseout="swapSaveImage('save',0)"
    />
    <a href="index.html" onclick="javascript:window.close()"><input type="image" id="cancel" src="../../<spring:theme code="register_cancel" />" 
    onmouseover="swapCancelImage('cancel',1)"
    onmouseout="swapCancelImage('cancel',0)"
    /> </a>
    </div>

</form:form>

</body>
</html>