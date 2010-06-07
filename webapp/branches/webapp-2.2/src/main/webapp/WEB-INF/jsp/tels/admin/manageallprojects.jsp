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
<script type="text/javascript" src="../javascript/tels/jquery-1.4.1.min.js"></script>
<script type="text/javascript" src="../javascript/tels/projecttags.js"></script>

    
<title><spring:message code="application.title" /></title>

<script type='text/javascript' src='/webapp/dwr/interface/ChangePasswordParametersValidatorJS.js'></script>
<script type='text/javascript' src='/webapp/dwr/engine.js'></script>
<script type='text/javascript'>
<c:forEach var='project' items="${internal_project_list}">
	<c:forEach var='tag' items="${project.tags}">
		tagNameMap['${tag.id}'] = '${tag.name}';
	</c:forEach>
</c:forEach>

<c:forEach var='project' items="${external_project_list}">
	<c:forEach var='tag' items="${project.tags}">
		tagNameMap['${tag.id}'] = '${tag.name}';
	</c:forEach>
</c:forEach>
</script>

</head>
<body>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div id="centeredDiv">

<%@ include file="adminheader.jsp"%>
<%@page import="org.telscenter.sail.webapp.domain.project.impl.ProjectType" %>
<% pageContext.setAttribute("potrunk", ProjectType.POTRUNK); %>


<h5 style="color:#0000CC;"><a href="index.html">Return to Main Menu</a></h5>

<c:out value="${message}" />

<h4>Internal Projects (Projects that can be run completely within the portal without the aid of outside portals)</h4>
<table id="adminManageProjectsTable">
	<tr>
		<th> Project Title </th>
		<th> Project Id</th>
		<th> IsCurrent?</th>
		<th> familytag</th>
		<th> Tags </th>
		<th> Edit Project with Authoring tool</th>
		<th> Upload Otml File</th>
		<th> Edit Project Metadata</th>		
		<th> Other Actions </th>				
	</tr>
	<c:forEach var="project" items="${internal_project_list}">
	<tr>
		<td>${project.name}</td>
		<td>${project.id }</td>
		<td>${project.current }</td>
		<td>${project.familytag} (${project.projectType})</td>
		<td>
			<div class="existingTagsDiv">
				<div>Existing Tags</div>
				<div id="existingTagsDiv_${project.id}">
					<c:forEach var="tag" items="${project.tags}">
						<table id="tagTable_${project.id}_${tag.id}">
							<tbody>
								<tr>
									<td><input id="tagEdit_${project.id}_${tag.id}" type='text' value='${tag.name}'/></td>
									<td><input id="updateTag_${project.id}_${tag.id}" type="button" value="update" onclick="tagChanged($(this).attr('id'))"/><br/><input id="removeTag_${project.id}_${tag.id}" type='button' value='remove' onclick="removeTag($(this).attr('id'))"/></td>
								</tr>
							</tbody>
						</table>
					</c:forEach>
				</div>
			</div>
			<div class="createTagsDiv">
				<div id='createTagMsgDiv_${project.id}' class='tagMessage'></div>
				<div>Create A New Tag</div>
				<div><input id="createTagInput_${project.id}" type="text"/><input type="button" value="create" onclick="createTag('${project.id}')"/></div>
			</div>
		</td>
		<td><a href="../author/authorproject.html?projectId=${project.id}">Edit Project (Authoring tool)</a></td>		
		<c:choose>
		    	<c:when test="${project.projectType == potrunk}">  <!--  if this is a POTrunk project, we can get the otml -->
		    	   <td><a href="../admin/uploadotml.html?projectId=${project.id}">Upload Otml File</a></td>
		    	</c:when>
		    	<c:otherwise>
		    	    <td>Not available for this project.</td>
		    	</c:otherwise>
		</c:choose>
		</td>				
		<td><a href="editproject.html?projectId=${project.id}">Edit Project Metadata</a></td>
		<td>
		    <ul>
		        <li><a href="../previewproject.html?projectId=${project.id}">Preview</a></li>
		        <li><a href="../teacher/projects/customized/shareproject.html?projectId=${project.id}">Manage Ownership/Shared Teachers</a></li>
		    </ul>
		</td>		
	</tr>
	</c:forEach>
</table>


<h4>External Projects (Projects that require the aid of outside portals and outside project services in some capacity to function)</h4>
<table id="adminManageProjectsTable">
	<tr>
		<th> Project Title </th>
		<th> Project Id</th>
		<th> IsCurrent?</th>
		<th> familytag</th>
		<th> Edit Project with Authoring tool</th>
		<th> Upload Otml File</th>
		<th> Edit Project Metadata</th>		
		<th> Preview Project</th>				
	</tr>
	<c:forEach var="project" items="${external_project_list}">
	<tr>
		<td>${project.name}</td>
		<td>${project.id }</td>
		<td>${project.current }</td>
		<td>${project.familytag} (${project.projectType})</td>
		<td><a href="../author/authorproject.html?projectId=${project.id}">Edit Project (Authoring tool)</a></td>		
				<c:choose>
		    	<c:when test="${project.projectType == potrunk}">
		    	   <td><a href="../admin/uploadotml.html?projectId=${project.id}">Upload Otml File</a></td>
		    	</c:when>
		    	<c:otherwise>
		    	    <td>Not available for this project.</td>
		    	</c:otherwise>
		</c:choose>
		<td><a href="editproject.html?projectId=${project.id}">Edit Project Metadata</a></td>
		<td><a href="../previewproject.html?projectId=${project.id}">Preview</a></td>		
	</tr>
	</c:forEach>
</table>
</body>
</html>