<%@ include file="../../include.jsp" %> 

<!--
  * Copyright (c) 2006 Encore Research Group, University of Toronto
  * 
  * This library is free software; you can redistribute it and/or
  * modify it under the terms of the GNU Lesser General Public
  * License as published by the Free Software Foundation; either
  * version 2.1 of the License, or (at your option) any later version.
  *
  * This library is distributed in the hope that it will be useful,
  * but WITHOUT ANY WARRANTY; without even the implied warranty of
  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
  * Lesser General Public License for more details.
  *
  * You should have received a copy of the GNU Lesser General Public
  * License along with this library; if not, write to the Free Software
  * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
-->

<!-- $Id: overview.jsp 997 2007-09-05 16:52:39Z archana $ -->

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" >

<html lang="en">
<head><meta http-equiv="Content-Type" content="text/html; charset=utf-8"></meta>

<link href="../../<spring:theme code="globalstyles"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="../../<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="../../<spring:theme code="teacherprojectstylesheet" />" media="screen" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="../../.././javascript/tels/general.js"></script>
 
<title>Sharing Permissions for Project Runs</title>

</head>

<body>

<div id="centeredDiv">

<%@ include file="headerteacherprojects.jsp"%>

<%@ include file="L2projects_myprojectruns.jsp"%>

<div style="text-align:center;">   <!--This bad boy ensures centering of block level elements in IE. -->

<h2 id="titleBar" class="headerText">Sharing Permissions</h2> 
				<c:out value="${message}" />

<div class="sharedprojectHeadline1">project run selected:</div>
 
    
				        <table id="runTitleTable">
				      			<tr>
				      				<td class="runTitleTableHeader">Title:</td>
				      				<td>${run.project.curnit.sdsCurnit.name}</td>
				      			</tr>
				      			<tr>
				      				<td class="runTitleTableHeader">Project ID:</td>
				      				<td>${run.project.id}</td>
				      			</tr>
				      			<tr> 
				      				<td class="runTitleTableHeader">Project Source:</td>
				      				<td>UC Berkeley library project</td>
				      			</tr>
				      			<tr>
				      				<td class="runTitleTableHeader">Run Created:</td>
				      				<td><fmt:formatDate value="${run.starttime}" type="date" dateStyle="short" /></td>
				      			</tr>
						</table>
				
<div class="sharedprojectHeadline1">permissions for this project run:</div>			

<table id="sharedProjectPermissions">

	<tr>
		<th>USERNAME</th>
		<th>PERMISSION LEVEL</th> 
		<th>OPTIONS</th>
	</tr>
	<!--  display owners of the run -->
	<c:choose>
		<c:when test="${fn:length(run.owners) == 0}">
		</c:when>
		<c:otherwise>
			<c:forEach var="owner" items="${run.owners}">
				<tr>
				    <td>${owner.userDetails.firstname} ${owner.userDetails.lastname}</td>
					<td>Owner of Project Run. Full access.</td>
					<td>NONE</td>
			    </tr>
			</c:forEach>
		</c:otherwise>
	</c:choose>
	
	<!--  display shared owners of the run -->
	<c:forEach var="sharedowner" items="${run.sharedowners}">
        <form:form method="post" id="${sharedowner.userDetails.username}" commandName="${sharedowner.userDetails.username}">
            <form:hidden path="sharedOwnerUsername" />
		    <tr>
		        <td>${sharedowner.userDetails.username}</td>
			    <td align="left">
			        <form:radiobutton path="permission" onclick="javscript:this.form.submit();" value="ROLE_RUN_READ" /> Can VIEW the project run<br />
			        <form:radiobutton path="permission" onclick="javscript:this.form.submit();" value="ROLE_RUN_GRADE" />Can VIEW + GRADE the project run
				</td>
				<td><!-- <a href='#' onclick="return confirm('Proceed with removing this shared teacher?');">Remove this User</a> -->
				    <a href='#' onclick="alert('Remove Shared Teacher is not yet implemented.');">Remove this User</a>
				</td>
			</tr>
		</form:form>
	</c:forEach>
	
	<tr>
		<td id="sharingSearchBox" colspan=3>
			<div id="sharingSearchBoxHelp">To share this project run with another user, enter their Username below:</div>
			    <form:form method="post" commandName="addSharedTeacherParameters">
					<form:input path="sharedOwnerUsername" id="sharedOwnerUsernameInput" size="25"/>
				    <input type="submit" value="Add this User" />
				</form:form>
		</td>
	</tr>

</table> 

<h5><a href="../run/myprojectruns.html">Return to <em>My Project Runs</em></a></h5>

</div>
</div>

</body>
</html>

