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

<table id="adminManageProjectsTable">
	<tr>
		<th> Project Title </th>
		<th> Project Id</th>
		<th> IsCurrent?</th>
		<th> familytag</th>
		<th> Edit Project with Authoring tool</th>
		<th> Edit Project with Manually</th>		
		<th> Edit Project Metadata</th>		
		<th> Preview Project</th>				
	</tr>
	<c:forEach var="project" items="${projectList}">
	<tr>
		<td>${project.curnit.sdsCurnit.name }</td>
		<td>${project.id }</td>
		<td>${project.current }</td>
		<td>${project.familytag }</td>
		<td><a href="../author/authorproject.html?projectId=${project.id}">Edit Project (Authoring tool)</a></td>		
		<td><a href="../admin/uploadotml.html?projectId=${project.id}">Upload Otml File</a></td>		
		<td><a href="editproject.html?projectId=${project.id}">Edit Project Metadata</a></td>
		<td><a href="../previewproject.html?projectId=${project.id}">Preview</a></td>		
	</tr>
	</c:forEach>
</table>
	

</body>
</html>