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

<h1 id="titleBar" class="headerText"><spring:message code="curnitlist.project.library" /></h1> 
    	
<table id="libraryTempTable">
  <tr>
    <th><spring:message code="curnitlist.title" /></th>
    <th><spring:message code="curnitlist.options" /></th>
  </tr>
<c:forEach var="project" items="${projectList}">
  <tr >
  <td class="libraryTitleStyle"><c:out value="${project.curnit.sdsCurnit.name}"/></td>
  <td class="libraryActionColumn">
  	<ul id="libraryActionButtons">
  		<li><a href="<c:url value="../run/createRun.html"><c:param name="projectId" value="${project.id}"/></c:url>">
	       Set Up as a Project Run</a></li>
	    <li><a href="<c:url value="../../previewproject.html"><c:param name="projectId" value="${project.id}"/></c:url>">
	       Preview this Project</a></li> 
        <li><a href="<c:url value="http://tels-develop.soe.berkeley.edu:8080/maven-jnlp-snapshot/jnlp-tests/jardiff/javachecker-1.1.jnlp"></c:url>">
           Computer Compatibility Check</a></li>
    </ul>
  </td>
  </tr>
</c:forEach>
</table>

<h5>Click any project title below to review its detailed <em>Project Information</em> display.</h5> 
  
<c:forEach var="project" items="${projectList}">
	<table id="libraryProjectTable">
		<tr>
			<th>project title</th>
			<th id="libraryProjectIDHeader">project id</th>
			<th id="libraryKeywordHeader">keywords</th>
			<th>grade</th>
			<th>total</br>time</th>
			<th>computer</br>time</th>
			<th>usage</th>
		</tr>
		<tr id="libraryProjectTableR2">
			<td class="libraryProjectTitle"><a href="#">${project.curnit.sdsCurnit.name}</a></td>
			<td>[10321]</td>       		   <!--		""${project.curnit.id}""-->
			<td id="libraryKeywordData">[BIOLOGY, mendel, meiosis, evolution, selection pressure, Darwin, etc, etc.]</td>
			<td>[6-8]</td>              <!--		"${project.curnit.totalTime}"-->
			<td>[6 periods]</td>              <!--		"${project.curnit.computerTime}"-->
			<td>[5 periods]</td>
			<td>[27 runs]
		</tr>
		<tr id="libraryProjectTableR3">
			<td colspan="7">Eros illum, luptatum, ea nulla, in nostrud eu 
			consectetuer augue accumsan feugiat qui iusto consequat duis vel nulla. Consequat duis, vero elit suscipit, at in 
			feugait dignissim vero zzril blandit, eum lorem, feugiat erat feugait ut vel nonummy zzril accumsan velit dolor in 
			accumsan. Unt, ea nulla, in nostrud eu consectetuer augue accumsan feugiat qui iusto consequat duis vel nulla. Consequat duis, vero elit suscipit, at in feugait dignissim vero zzril blandit, eum lorem, feugiat erat feugait ut vel nonummy zzril accumsan velit dolor in
			</td>
		</tr>
	</table>
</c:forEach>	
	
</div>

</body>
</html>
