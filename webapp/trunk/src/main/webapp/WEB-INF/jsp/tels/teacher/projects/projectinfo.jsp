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
<%@ include file="styles.jsp"%>

<link href="../../<spring:theme code="globalstyles"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="../../<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="../../<spring:theme code="teacherprojectstylesheet" />" media="screen" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="../.././javascript/tels/general.js"></script>

<title><spring:message code="curnitlist.project.library" /></title>
</head>

<body class="yui-skin-sam">
<div id="centeredDiv">

<%@ include file="./headerteacherprojects.jsp"%>

<%@ include file="./L2projects_projectlibrary.jsp"%>

<h1 id="titleBar" class="headerText">Project Information</h1> 
    	  
<div id="projectInfoTabs" class="yui-navset">
    <ul class="yui-nav">
        <li><a href="#tab1"><em>Overview</em></a></li>
        <li><a href="#tab2"><em>Teacher Guide</em></a></li>
        <li><a href="#tab3"><em>Learning Goals</em></a></li>
        <li><a href="#tab4"><em>Project Credits</em></a></li>
    </ul>            
    <div class="yui-content">
        <div id="tab1">
            <p>project title:${project.curnit.sdsCurnit.name}</p>
            <p><a href="<c:url value="../../previewproject.html"><c:param name="projectId" value="${project.id}"/></c:url>">
	             Preview this Project</a>
	        </p>
	        <p><a href="<c:url value="../run/createRun.html"><c:param name="projectId" value="${project.id}"/></c:url>">
	             Set Up as a Project Run</a>
	        </p>
        </div>
        <div id="tab2">
            <p>Tab Two Content</p>
        </div>
        <div id="tab3">
            <p>Tab Three Content</p>
        </div>
        <div id="tab4">
            <p>Tab Four Content</p>
        </div>
    </div>
</div>
    	  
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
			<td class="libraryProjectTitle"><a href="projectinfo.html?projectId=${project.id}">${project.curnit.sdsCurnit.name}</a></td>
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
<script type="text/javascript">
    var tabView = new YAHOO.widget.TabView('projectInfoTabs');
    tabView.set('activeIndex', 0);
</script>

</body>
</html>
