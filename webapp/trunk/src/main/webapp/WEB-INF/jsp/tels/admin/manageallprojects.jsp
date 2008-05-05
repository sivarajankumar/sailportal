<%@ include file="../include.jsp"%>

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
<%@ include file="adminheader.jsp"%>
If you see this, you have admin privileges.<br>

<c:out value="${message}" />

<table>
	<tr>
		<th> Project Title </th>
		<th> Project Id</th>
		<th> IsCurrent?</th>
		<th> familytag</th>
		<th> Edit Project</th>
	</tr>
	<c:forEach var="project" items="${projectList}">
	<tr>
		<td>${project.curnit.sdsCurnit.name }</td>
		<td>${project.id }</td>
		<td>${project.current }</td>
		<td>${project.familytag }</td>
		<td><a href="editproject.html?projectId=${project.id}">Edit</a></td>
	</tr>
	</c:forEach>
</table>
	

</body>
</html>