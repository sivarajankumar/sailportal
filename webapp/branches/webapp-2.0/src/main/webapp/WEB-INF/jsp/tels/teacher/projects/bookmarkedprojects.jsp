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

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html lang="en">
<head>

<link href="../../<spring:theme code="globalstyles"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="../../<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="../../<spring:theme code="teacherprojectstylesheet" />" media="screen" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="../.././javascript/tels/general.js"></script>
<script type="text/javascript" src="../.././javascript/tels/yui/yahoo/yahoo.js"></script>
<script type="text/javascript" src="../.././javascript/tels/yui/event/event.js"></script>
<script type="text/javascript" src="../.././javascript/tels/yui/connection/connection.js"></script>

<script type="text/javascript">
	
	function bookmark(pID){
		var checked = document.getElementById('check_' + pID).checked;
		var callback = {
			success:function(o){alert(o.responseText);document.getElementsByName('table_' + pID)[0].parentNode.removeChild(document.getElementsByName('table_' + pID)[0]);},
			failure:function(o){alert('failed update to server');}
		};
		YAHOO.util.Connect.asyncRequest('GET', 'bookmark.html?projectId=' + pID + '&checked=' + 
			checked, callback);
	};
	
	function bookmarked(){
		var bookmarked = false;
		<c:forEach var='project' items='${projectList}'>
			var bookmarked = false;
			<c:forEach var='bookmarker' items='${project.bookmarkers}'>
				<c:if test='${bookmarker.id==userId}'>
					bookmarked = true;
				</c:if>
			</c:forEach>
			if(bookmarked){
				document.getElementById('check_${project.id}').checked = true;
			};
		</c:forEach>
	};
</script>

<title>My Bookmarked Projects</title>
</head>

<body>

<div id="centeredDiv">

<h2 id="titleBar" class="headerText">My Bookmarked Projects</h2>

<c:forEach var="project" items="${projectList}">
	<table name="table_${project.id}" id="libraryProjectTable">
		<tr>
			<th>project title</th>
			<th id="libraryProjectCol2">project id</th>
			<th id="libraryProjectCol3">project source</th>
			<th id="libraryProjectCol4">subjects/keywords</th>
			<th>grade range</th>
			<th>total</br>time</th>
			<th>computer</br>time</th>
			<th>usage</th>
			<th>bookmark</th>
		</tr>
		<tr id="libraryProjectTableR2">
			<td class="titleCell"><a href="projectinfo.html?projectId=${project.id}">${project.name}</a></td>
			<td class="dataCell">${project.id}</td>   
			<td class="dataCell libraryProjectSmallText">${project.projectInfo.source}</td>       		   
			<td class="dataCell libraryProjectSmallText">${project.projectInfo.subject} ${project.projectInfo.keywords}</td>
			<td class="dataCell">${project.projectInfo.gradeLevel}</td>              
			<td class="dataCell">[6 periods]</td>              
			<td class="dataCell">[5 periods]</td> 
			<td class="dataCell">[27 runs]</td>
			<td class="dataCell"><input type="checkbox" id="check_${project.id}" onclick="javascript:bookmark('${project.id}')"></td>
		</tr>
		<tr id="libraryProjectTableR3">  
			<td colspan="9">${project.projectInfo.description}</td>
		</tr>
	</table>
</c:forEach>

<script>bookmarked();</script>
</div>
</body>
</html>