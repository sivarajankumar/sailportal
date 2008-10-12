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
<script>
//alert('hi');
//alert(ChangePasswordParametersValidatorJS.test('hi'))
</script>

</head>
<body>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div id="centeredDiv">

<%@ include file="adminheader.jsp"%>

<h5 style="color:#0000CC;"><a href="index.html">Return to Main Menu</a></h5>

<c:out value="${message}" />

<h2>External Projects</h2>
<table id="adminManageProjectsTable">
	<tr>
		<th> Project Title </th>
		<th> Preview Project </th>
		<th> Import Project to Library </th>		
	</tr>
	<c:forEach var="project" items="${externalProjectList}">
	<tr>
		<td>${project.name}</td>
		<td><a href="../previewproject.html?projectType=diy&externalId=${project.externalId}&projectCommunicatorId=${project.projectCommunicator.id}">Preview</a></td>		
		<td><a href="importexternalproject.html?projectType=diy&externalId=${project.externalId}&projectCommunicatorId=${project.projectCommunicator.id}">Import</a></td>				
	</tr>
	</c:forEach>
</table>
	

</body>
</html>