<%@ include file="../include.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" />
<html xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />


    
<script type="text/javascript" src="../../javascript/tels/general.js"></script>
<script type="text/javascript" src="../../javascript/tels/effects.js"></script>
<script type="text/javascript" src=".././javascript/tels/general.js"></script>
    
<title><spring:message code="application.title" /></title>

<script type='text/javascript' src='/webapp/dwr/engine.js'></script>
</head>
<body>
<%@ include file="adminheader.jsp"%>
<!-- Support for Spring errors object -->
<spring:bind path="newsItemParameters.*">
  <c:forEach var="error" items="${status.errorMessages}">
    <b>
      <br /><c:out value="${error}"/>
    </b>
  </c:forEach>
</spring:bind>
<br>
<br>
Add a new News Item!

	<form:form method="post" action="addnewsitems.html" commandName="newsItemParameters" id="addnewsitems">
		<dl>
		<dt><label for="titleField"><spring:message code="newsitem.title" /></label></dt>
		<dd><form:input path="title" id="titleField"/> </dd>
		<dt><label for="newsField"><spring:message code="newsitem.news" /></label></dt>
		<dd><form:input path="news" id="newsField"/></dd>
		</dl>
 	
 	   <input type="image" id="save" src="../<spring:theme code="register_save" />" 
    	onmouseover="swapSaveImage('save',1)"onmouseout="swapSaveImage('save',0)"   />

	</form:form>
</body>
</html>