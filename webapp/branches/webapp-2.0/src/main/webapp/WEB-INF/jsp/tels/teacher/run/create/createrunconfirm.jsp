<%@ include file="include.jsp"%>
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

<!-- $Id: setupRun1.jsp 357 2007-05-03 00:49:48Z archana $ -->

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" >
<html xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />

<link href="../../<spring:theme code="globalstyles"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="../../<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet"  type="text/css" />

<script src="./javascript/tels/general.js" type="text/javascript" ></script>
<script src="./javascript/tels/effects.js" type="text/javascript" ></script>
<script src="./javascript/tels/prototype.js" type="text/javascript" ></script>
<script src="./javascript/tels/scriptaculous.js" type="text/javascript" ></script>

<title><spring:message code="teacher.setup-project-run-step-one" /></title>

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

<!--USED TO SHOW/HIDE A DIV ELEMENT-->
<script type="text/javascript">
	/**
	 * Toggles the summary div
	 * projectId: id of project whose summary div to toggle
	 */
	function toggleDetails(projectId){
		var searchDiv = document.getElementById('details_'+projectId);
		if(searchDiv.style.display=='none'){
			searchDiv.style.display = 'block';
		} else {
			searchDiv.style.display = 'none';
		};
	};
</script>

</head>

<body>

<!-- Support for Spring errors object -->
<spring:bind path="runParameters.project">
  <c:forEach var="error" items="${status.errorMessages}">
    <c:choose>
      <c:when test="${fn:length(error) > 0}" >
        <script type="text/javascript">
            alert("${error}");
        </script>
      </c:when>
    </c:choose>
  </c:forEach>
</spring:bind>

<div id="centeredDiv">

<%@ include file="../../headerteacher.jsp"%> 

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div id="navigationSubHeader2">Project Run Setup<span id="navigationSubHeader1">projects</span></div> 

<h1 id="titleBarSetUpRun" class="blueText"><spring:message code="teacher.setup-project-classroom-run" /></h1>
     	    	    
<div id="setUpRunBox">

<div id="stepNumber"><spring:message code="teacher.run.setup.1"/><span class="blueText">&nbsp;<spring:message code="teacher.run.setup.2"/></span></div>

<h5>This process will help you set up a <em>Project Run</em> for your students to explore. You can cancel this process at any time.<br/>
You have selected:</h5>
 
<table id="projectOverviewTable">
							<tr id="row1">
							<td id="titleCell" colspan="3">
									<a href="../projectinfo.html?projectId=${project.id}">${project.name}</a>
									<c:if test="${fn:length(project.sharedowners) > 0}">
										<div id="sharedNamesContainer">
											This project is shared with:
											<div id="sharedNames">
												<c:forEach var="sharedowner" items="${project.sharedowners}">
												  <c:out value="${sharedowner.userDetails.firstname}"/>
												  <c:out value="${sharedowner.userDetails.lastname}"/>
												  <c:out value=",  "/>
												</c:forEach>
												</c:if>
											</div>
										</div>
							</td>
							<td class="actions" colspan="6"> 
									<ul>
									</ul>
							</tr>
					<tr id="row2">
								<th id="title1" style="width:60px;">Project ID</th>
								<th id="title2" style="width:90px;">Project Family</th>
								<th id="title3" style="width:280px;" >Subject</th>
								<th id="title4" style="width:70px;">Grade Level</th>
								<th id="title5" style="width:105px;">Total Hours</th>
								<th id="title6" style="width:110px;">Computer Hours</th>
								<th id="title7" style="width:72px;">Language</th>
								<th id="title8" style="width:82px;">Tech Needs</th>
								<th id="title9" style="width:60px;">Usage</th>
							</tr>
							<tr id="row3">
								<td class="dataCell libraryProjectSmallText">${project.id}</td>       		   
								<td class="dataCell libraryProjectSmallText">${project.familytag}</td>       		   
								<td class="dataCell libraryProjectSmallText">${project.metadata.subject}</td>
								<td class="dataCell">${project.metadata.gradeRange}</td>              
								<td class="dataCell">${project.metadata.totalTime}</td>              
								<td class="dataCell">${project.metadata.compTime}</td> 
								<td class="dataCell">[English]</td> 
								<td class="dataCell">[Flash, Java]</td> 
								<td class="dataCell">${usageMap[project.id]}</td>
							</tr>
							<tr id="row4">  
								<td colspan="9">
									<a id="hideShowLink" href="#" onclick="toggleDetails(${project.id})">Hide/Show project details</a>
									<div id="details_${project.id}" style="display:none;">
										<table id="detailsTable">
											<tr>
												<th>Summary:</th>
												<td class="summary">${project.metadata.summary}</td>
											</tr>
											<tr>
												<th>Keywords:</th>
												<td class="keywords">[List of comma-separated keywords go here]</td>
											</tr>

											<tr>
												<th>Tech Details:</th>
												<td>[This project requires Flash for Steps x,y,z and requires Java for steps a,b,c.]</td>
											</tr>
											<tr>
												<th>Created On:</th>
												<td class="keywords"><fmt:formatDate value="${project.dateCreated}" type="both" dateStyle="short" timeStyle="short" /></td>
											</tr>
											<tr> 
												<th>Original Author:</th>
												<td>[Name goes here]</td>
											</tr>
											<tr>
												<th>Contact Info:</th>
												<td>[Name and Email goes here]</td>
											</tr>
										</table>
									</div>									
								</td>
							</tr>
						</table>
			
<h5><spring:message code="teacher.run.setup.9"/>&nbsp;<em><spring:message code="teacher.run.setup.10"/></em>&nbsp;<spring:message code="teacher.run.setup.11"/></h5>

</div> <!-- /* End setUpRunBox */-->

</div>
<div align="center">
<form method="post" align="center">
<input type="submit" name="_target0" disabled value="<spring:message code="navigate.back" />" />
<input type="submit" name="_cancel" value="<spring:message code="navigate.cancel" />" />
<input type="submit" name="_target1" value="<spring:message code="navigate.next" />" />
</form>

</div>  <!-- /* End of the CenteredDiv */-->

</body>
</html>