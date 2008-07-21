<%@ include file="../include.jsp"%>
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

<!-- $Id$ -->

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html lang="en">
<head>

<link href="../../<spring:theme code="globalstyles"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="../../<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="../../<spring:theme code="teacherprojectstylesheet" />" media="screen" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="../.././javascript/tels/general.js"></script>

<title><spring:message code="curnitlist.project.library" /></title>
</head>

<body>

<div id="centeredDiv">

<%@ include file="./headerteacherprojects.jsp"%>
	
<%@ include file="./L2projects_projectlibrary.jsp"%>

<h2 id="titleBar" class="headerText"><spring:message code="curnitlist.project.library" /></h2>
 
<div id="searchResultsHeading">Search Results: ${fn:length(projectList) } projects found 
	<div class="searchResultsButtons"><a href="#">Show/Hide Descriptions</a></div>
	<div class="searchResultsButtons"><a href="#">New Search</a></div>
</div>

<div id="searchResultsInstructions">Click any project title below to review its <em>Project Overview</em>.</div>
  
<c:forEach var="project" items="${projectList}">

	<table id="libraryProjectTable">
		<tr>
			<th>project title</th>
			<th id="libraryProjectCol2">project id</th>
			<th id="libraryProjectCol3">project source</th>
			<th id="libraryProjectCol4">subjects/keywords</th>
			<th>grade range</th>
			<th>total</br>time</th>
			<th>computer</br>time</th>
			<th>usage</th>
		</tr>
		<tr id="libraryProjectTableR2">
			<td class="titleCell"><a href="projectinfo.html?projectId=${project.id}">${project.curnit.sdsCurnit.name}</a></td>
			<td class="dataCell">${project.id}</td>   
			<td class="dataCell libraryProjectSmallText">UC Berkeley library project</td>       		   
			<td class="dataCell libraryProjectSmallText">${project.projectInfo.subject} ${project.projectInfo.keywords}</td>
			<td class="dataCell">${project.projectInfo.gradeLevel}</td>              
			<td class="dataCell">[6 periods]</td>              
			<td class="dataCell">[5 periods]</td> 
			<td class="dataCell">[27 runs]
		</tr>
		<tr id="libraryProjectTableR3">  
			<td colspan="8">${project.projectInfo.description}</td>
		</tr>
	</table>
	
</c:forEach>	
	
</div>

</body>
</html>
