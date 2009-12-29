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

<%@ include file="../headerteachersub.jsp"%> 

<div id="navigationSubHeader2">Project Library<span id="navigationSubHeader1">projects</span></div>
 
<div class="searchContainer">
	<div class="header">Browse by Topic</div>
	<div id='searchInstructions'>Hold your mouse over any topic to learn more about it.  Click to find the associated projects.</div>

	<div>contents go here</div>
</div>
	
<div class="searchContainer">
	<div class='header'>Search by Category</div>
	<div id='searchInstructions'>Refine your search by selecting one or more categories using the drop-down menus. Then click <i>Search By Category</i> to find matching projects.</div>
	<div id='projectOverviewSearchContainer'>
	
		<form:form commandName='searchProjectLibraryParameters' id='searchLibraryForm' method='post' action='projectlibrary.html'>
		<table id="projectOverviewTable">
			
				<tr id="row2">
						<th id="title1" style="width: 90px;">Project Family</th>
						<th id="title2" style="width: 222px;">Subject</th>
						<th id="title3" style="width: 100px;">Grades</th>
						<th id="title4" style="width: 110px;">Total Time (hrs)</th>
						<th id="title5" style="width: 110px;">Computer Time (hrs)</th>
						<th id="title6" style="width: 92px;">Language</th>
						<th id="title7" style="width: 120px;">Tech Requirements</th>
				</tr>
				<tr id="row3">
						<td class="dataCell">
							<select name="searchFamily">
							<option id='telsRadio' path='family' value='0'>TELS</option>
							<option id='uccpRadio' path='family' value='1'>VISUAL</option>
							<option id='allRadio' path='family' value='2'>UCCP</option>
							<option selected id='allRadio' path='family' value='-1'>All</option>
							</select>
						</td>
						<td class="dataCell">
							<select name="searchSubject">
							<option id='subjectAllBiology' path='subject' value='subjectBio'>Biology</option>
							<option id='subjectAllChemistry' path='subject' value='subjectChemistry'>Chemistry</option>
							<option id='subjectAllPhysics' path='subject' value='subjectPhysics'>Physics</option>
							<option id='subjectAllEarthScience' path='subject' value='subjectEarth'>Earth Science</option>
							<option selected id='subjectAll' path='subject' value='subjectAll'>All</option>
							</select>
						</td>
						<td class="dataCell">
							<select name="searchGrade">
							<option id='grade1' path='grade' value='grade1'>Grades 3-5</option>
							<option id='grade2' path='grade' value='grade2'>Grades 6-8</option>
							<option id='grade3' path='grade' value='grade3'>Grades 9-12</option>
							<option id='grade4' path='grade' value='grade4'>Grades 6-12</option>
							<option selected id='gradeAll' path='grade' value='gradeAll'>All</option>
							</select>
						</td>
						<td class="dataCell">
							<select name="searchTotalTime">
							<option id='total1' path='totalTime' value='total1'>2-3 hours</option>
							<option id='total2' path='totalTime' value='total2'>4-5 hours</option>
							<option id='total3' path='totalTime' value='total3'>6-7 hours</option>
							<option id='total4' path='totalTime' value='total4'>8-9 hours</option>
							<option id='total5' path='totalTime' value='total5'>10-11 hours</option>
							<option id='total6' path='totalTime' value='total6'>12+ hours</option>
							<option selected id='totalAll' path='totalTime' value='totalAll'>All</option>
							</select>
						</td>
						<td class="dataCell">
							<select name="searchTotalTime">
							<option id='computer1' path='computerTime' value='computer1'>2-3 hours</option>
							<option id='computer2' path='computerTime' value='computer2'>4-5 hours</option>
							<option id='computer3' path='computerTime' value='computer3'>6-7 hours</option>
							<option id='computer4' path='computerTime' value='computer4'>8-9 hours</option>
							<option id='computer5' path='computerTime' value='computer5'>10-11 hours</option>
							<option id='computer6' path='computerTime' value='computer6'>12+ hours</option>
							<option selected id='computerAll' path='computerTime' value='computerAll'>All</option>
							</select>
						</td>
						<td class="dataCell">
							<select name="searchLanguage">
							<option id='language1' path='language' value='language1'>English</option>
							<option id='language2' path='language' value='language2'>Spanish</option>
							<option id='language3' path='language' value='language3'>Hebrew</option>
							<option id='language4' path='language' value='language4'>German</option>
							<option id='language5' path='language' value='language5'>U.K. English</option>
							<option id='language6' path='language' value='language6'>French</option>
							<option selected id='languageAll' path='language' value='languageAll'>All</option>
							</select>
						</td>
						<td class="dataCell">
							<select name="searchTech">
							<option id='tech1' path='tech' value='tech1'>Browser</option>
							<option id='tech2' path='tech' value='tech2'>Browser + Flash</option>
							<option id='tech3' path='tech' value='tech3'>Browser + Flash + Java</option>
							<option selected id='techAll' path='tech' value='techAll'>All</option>
							</select>
						</td>

				</tr>
		</table>
		<input type='submit' value='Search by Category'/>
		</form:form>
		</div>
</div>

<div class="searchContainer">
	<div class="header">Search by Keyword</div>
	<div id='searchInstructions'>Type one or more keywords to search for matching projects.  Keywords can be from Project Name, Project ID (5 digits), Topic, or words within a Project Summary.  Click <i>Search By Keyword</i> to find matching projects.</div>
	<form name="myform" action="http://www.mydomain.com/myformhandler.cgi" method="POST">
		<input type="text" size="25" value="Enter your name here!"> <br>
		<input type="submit" value="Send me your name!"><br>
	</form>
</div>

<div>
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
