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
<script type="text/javascript" src="../.././javascript/tels/yui/yahoo/yahoo.js"></script>
<script type="text/javascript" src="../.././javascript/tels/yui/event/event.js"></script>
<script type="text/javascript" src="../.././javascript/tels/yui/connection/connection.js"></script>

<script type="text/javascript"><!--
	
	function bookmark(pID){
		var checked = document.getElementById('check_' + pID).checked;
		var callback = {
			success:function(o){alert(o.responseText);},
			failure:function(o){alert('bookmark: failed update to server');}
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
	
	function copy(pID, type, name, filename, url, base){
		var yes = confirm("Copying a project may take some time. If you proceed, please" +
			" do not click the 'make copy' button again. A message will be displayed when" +
			" the copy has completed.");
		if(yes){
			if(type=='LD'){
				var callback = {
					success:function(o){
						var fullPath = o.responseText;
						var portalPath = fullPath.substring(base.length, fullPath.length) + '/' + filename;
						var callback = {
							success:function(o){
								alert('The LD project has been successfully copied with the name Copy of ' + name + '. The project can be found in the My Customized Projects section.');
							},
							failure:function(o){alert('Project files were copied but the project was not successfully registered in the portal.');},
							scope:this
						};

						YAHOO.util.Connect.asyncRequest('POST', "/webapp/author/authorproject.html", callback, 'command=createProject&param1=' + portalPath + '&param2=Copy of ' + name);
					},
					failure:function(o){alert('Could not copy project folder, aborting copy.');},
					scope:this
				};
				
				YAHOO.util.Connect.asyncRequest('POST', '/vlewrapper/vle/filemanager.html', callback, 'command=copyProject&param1=' + url + '&param2=' + base);
			} else {
				var callback = {
					success:function(o){alert(o.responseText);},
					failure:function(o){alert('copy: failed update to server');}
				};
				YAHOO.util.Connect.asyncRequest('GET', 'copyproject.html?projectId=' + pID, callback);
			};
		};
	};

	/**
	 * Toggles the search div
	 */
	function toggleSearch(){
		var searchDiv = document.getElementById('searchDiv');
		if(searchDiv.style.display=='none'){
			searchDiv.style.display = 'block';
		} else {
			searchDiv.style.display = 'none';
		};
	};
--></script>

<title><spring:message code="curnitlist.project.library" /></title>
</head>

<body>

<div id="centeredDiv">

<%@ include file="./headerteacherprojects.jsp"%>
	
<%@ include file="./L2projects_projectlibrary.jsp"%>

<h2 id="titleBar" class="headerText"><spring:message code="curnitlist.project.library" /></h2>
 
<div id="searchResultsHeading">Search Results: ${fn:length(projectList) } projects found 
	<div class="searchResultsButtons"><a href="#">Show/Hide Descriptions</a></div>
	<div class="searchResultsButtons"><a href='#' onclick='toggleSearch()'>Show/Hide Search Options</a></div>
</div>

<div id="searchResultsInstructions">Click any project title below to review its <em>Project Overview</em>.</div>

<div id='searchDiv'>
	<div id='searchTextDiv'>Enter your search criteria below. Enter more information for a more restrictive search. Searches are case insensitive.</div><br/>
	<form:form commandName='searchProjectLibraryParameters' id='searchLibraryForm' method='post' action='projectlibrary.html'>
	<table id='searchTable'>
		<thead></thead>
		<tbody>
			<tr>
				<td id='familyTD'>
					<div id='familyDiv'>
						Family: <br/>
						<form:radiobutton id='telsRadio' path='family' value='0'/> TELS<br/>
						<form:radiobutton id='uccpRadio' path='family' value='1'/> UCCP<br/>
						<form:radiobutton id='otherRadio' path='family' value='2'/> Other<br/>
						<form:radiobutton id='allRadio' path='family' value='-1'/> All<br/>
					</div>
				</td>
				<td id='currentTD'>
					<div id='currentDiv'>
						Project status: <br/>
						<form:radiobutton id='currentRadio' path='status' value='1'/> Current projects only<br/>
						<form:radiobutton id='endedRadio' path='status' value='0'/> Closed projects only<br/>
						<form:radiobutton id='bothRadio' path='status' value='-1'/> Both current and closed projects<br/>
					</div>
				</td>
				<td id='searchTypeTD'>
					<div id='searchTypeDiv'>
						Search should: <br/>
						<form:radiobutton id='exactRadio' path='searchtype' value='matches'/> Match text exactly<br/>
						<form:radiobutton id='containsRadio' path='searchtype' value='contains'/> Contain text<br/>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					Title: <form:input id='titleText' path='title'/>
				</td>
				<td>
					Author: <form:input id='authorText' path='author'/>
				</td>
				<td>
					Subject: <form:input id='subjectText' path='subject'/>
				</td>
			</tr>
			<tr>
				<td>
					Project Summary: <form:textarea id='summaryText' path='summary'></form:textarea>
				</td>
				<td>
					Grade Range: <form:input id='gradeRangeText' path='gradeRange'/>
				</td>
				<td>
					Contact: <form:input id='contactText' path='contact'/>
				</td>
			</tr>
			<tr>
				<td>
					Total time (in mins): <form:input id='totalTimeText' path='totalTime'/>
				</td>
				<td>
					Computer time (in mins): <form:input id='compTimeText' path='compTime'/>
				</td>
				<td>
					Technical Requirements: <form:textarea id='techReqsText' path='techReqs'></form:textarea>
				</td>
			</tr>
			<tr>
				<td><input type='submit' value='execute search'/></td>
			</tr>
		</tbody>
	</table>
	</form:form>
</div><br/><br/>

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
			<th>actions</th>
		</tr>
		<tr id="libraryProjectTableR2">
			<td class="titleCell"><a href="projectinfo.html?projectId=${project.id}">${project.projectInfo.name}</a></td>
			<td class="dataCell">${project.id}</td>   
			<td class="dataCell libraryProjectSmallText">${project.familytag}</td>       		   
			<td class="dataCell libraryProjectSmallText">${project.metadata.subject}</td>
			<td class="dataCell">${project.metadata.gradeRange}</td>              
			<td class="dataCell">${project.metadata.totalTime}</td>              
			<td class="dataCell">${project.metadata.compTime}</td> 
			<td class="dataCell">${usageMap[project.id]} runs</td>
			<td class="dataCell" >
				<ul>
					<li class="list1"><span><input type="checkbox" id="check_${project.id}" onclick="javascript:bookmark('${project.id}')"/>Bookmark</span></li>
					<li class="list2"><c:if test="${project.projectType=='ROLOO'}"><a href="../vle/vle.html?runId=${project.previewRun.id}&summary=true">Project Summary</a></c:if></li>
					<li class="list3"><input type="button" onclick="copy('${project.id}','${project.projectType}','${project.name}','${filenameMap[project.id]}','${urlMap[project.id]}','${curriculumBaseDir}')" value="Make Copy"/></li>
				</ul>
			</td>
		</tr>
		<tr id="libraryProjectTableR3">  
			<td colspan="9">${project.metadata.summary}</td>
		</tr>
	</table>
	
</c:forEach>	
<script>bookmarked();</script>
</div>
</body>
</html>
