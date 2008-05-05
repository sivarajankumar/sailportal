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


<form:form method="post" action="editproject.html" 
	commandName="projectInfoParameters" id="editproject">

<dl>
	<dt>Project: ${project.curnit.sdsCurnit.name }</dt>
	<dt>Projecd Id: ${project.id} </dt>
	<dt><label for="iscurrentlabel">New Project State (Current or not Current)</label></dt>
    <dd><form:input path="current" size="30" id="iscurrentinput"/></dd>

	<dt><label for="familytaglabel">New familytag</label></dt>
	<dd><form:input path="familytag" size="30" id="familytaginput"/></dd>
</dl>

    <input type="submit" value="Submit" />
    <a href="manageallprojects.html"><input type="button" value="Cancel"></input></a>
    	

</form:form>


</body>
</html>