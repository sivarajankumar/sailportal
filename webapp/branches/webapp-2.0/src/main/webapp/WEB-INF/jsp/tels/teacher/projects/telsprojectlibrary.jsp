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

<!-- $Id: projectlibrary.jsp 2478 2009-09-19 00:36:07Z honchikun@gmail.com $ -->

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
</script>

<script type="text/javascript">

	/**
	 * Toggles the search div
	 */
	function toggleDetails(){
		var searchDiv = document.getElementById('details');
		if(searchDiv.style.display=='none'){
			searchDiv.style.display = 'block';
		} else {
			searchDiv.style.display = 'none';
		};
	};
</script>

<!-- SuperFish drop-down menu from http://www.electrictoolbox.com/jquery-superfish-menus-plugin/  -->

<link rel="stylesheet" type="text/css" href="../../themes/tels/default/styles/teacher/superfish.css" media="screen">
<script type="text/javascript" src="../../javascript/tels/jquery-1.2.6.min.js"></script>
<script type="text/javascript" src="../../javascript/tels/superfish.js"></script>

<script type="text/javascript">
    
            // initialise plugins
            jQuery(function(){
                jQuery('ul.sf-menu').superfish();
            });
    
</script>

<title><spring:message code="curnitlist.project.library" /></title>
</head>

<body>

<div id="centeredDiv">

<%@ include file="../headerteacher.jsp"%>

<div id="navigationSubHeader2">Project Library<span id="navigationSubHeader1">projects</span></div> 

<!--<h2 id="titleBar" class="headerText"><spring:message code="curnitlist.tels.project.library" /></h2>-->
 
<div id="libraryHeader">
	<div id="libraryButtons"><a href="#" onclick="toggleDetails()">Show/Hide All Project Details</a></div>
	<div id="libraryResults">Search Results: ${fn:length(projectList) } projects found</div> 
</div>

<div id="libraryInstructions">Click any project title below for additional information and lesson plans.</div>
  
<c:forEach var="project" items="${projectList}">

	<table id="projectOverviewTable">
		<tr id="row1">
		<td id="titleCell" colspan="3"><a href="projectinfo.html?projectId=${project.id}">${project.projectInfo.name}</a></td>
		<td class="actions" colspan="6"> 
				<ul>
					<li><input type="checkbox" id="check_${project.id}" onclick="javascript:bookmark('${project.id}')"/><label for="check_${project.id}"><a href="#">Bookmark</a></label></li>
					<li><a href="<c:url value="../../previewproject.html"><c:param name="projectId" value="${project.id}"/></c:url>">Preview</a></li>
					<li><a href="<c:url value="../run/createRun.html"><c:param name="projectId" value="${project.id}"/></c:url>">Set up as Project Run</a></li>
					<li><a href="#" onclick="copy('${project.id}','${project.projectType}','${project.name}','${filenameMap[project.id]}','${urlMap[project.id]}','${curriculumBaseDir}')" >Create copy in <i>My Custom-Authored</i></a></li>
					<li><c:if test="${project.projectType=='ROLOO'}"><a href="../vle/vle.html?runId=${project.previewRun.id}&summary=true">Project Summary</a></c:if></li>
				</ul>
		</tr>
		<tr id="row2">
			<th id="title1" style="width:60px;">Project ID</th>
			<th id="title1" style="width:90px;">Project Family</th>
			<th id="title2" style="width:292px;" >Subject(s)</th>
			<th id="title3" style="width:100px;">Grades</th>
			<th id="title4" style="width:110px;">Total Time (hrs)</th>
			<th id="title5" style="width:110px;">Computer Time (hrs)</th>
			<th id="title6" style="width:92px;">Language</th>
			<th id="title7" style="width:90px;">Usage</th>
		</tr>
		<tr id="row3">
			<td class="dataCell libraryProjectSmallText">${project.id}</td>       		   
			<td class="dataCell libraryProjectSmallText">${project.familytag}</td>       		   
			<td class="dataCell libraryProjectSmallText">${project.metadata.subject}</td>
			<td class="dataCell">${project.metadata.gradeRange}</td>              
			<td class="dataCell">${project.metadata.totalTime}</td>              
			<td class="dataCell">${project.metadata.compTime}</td> 
			<td class="dataCell">[English]</td> 
			<td class="dataCell">${usageMap[project.id]} runs</td>

		</tr>
		<tr id="row4">  
			<td colspan="8">
				<a id="hideShowLink" href="#" onclick="toggleDetails()">Hide/Show project details</a>
				<div id="details" style="display:none;">
					<table id="detailsTable">
						<tr>
							<th>Summary:</th>
							<td class="summary">Consequat tincidunt veniam elit molestie in vel ullamcorper duis autem ipsum, aliquip nostrud delenit feugait, dolore dolore, dolor feugiat 
t veniam elit molestie in vel ullamcorper duis autem ipsum, aliquip nostrud delenit feugait, dolore dolore, dolor feugiat consequat accumsan te illum eum.</td>
						</tr>
						<tr>
							<th>Keywords:</th>
							<td class="keywords">[List of comma-separated keywords go here]</td>
						</tr>
<tr>
							<th>Original Author:</th>
							<td>[Name goes here]</td>
						</tr>
						<tr>
							<th>Tech Needs:</th>
							<td>[Tech Requirements go here]</td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
	</table>
	
</c:forEach>	
<script>bookmarked();</script>
</div>
<h5 class="center"><spring:message code="teacher.pro.lib.index.11"/> &nbsp; <a href="languagetranslations.html"><spring:message code="teacher.pro.lib.index.12"/></a></h5>

</body>
</html>
