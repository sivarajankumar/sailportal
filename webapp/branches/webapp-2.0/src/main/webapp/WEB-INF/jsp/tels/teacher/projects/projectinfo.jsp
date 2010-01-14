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

<title><spring:message code="teacher.pro.projinfo.1"/></title>

</head>

<body class="yui-skin-sam">

<div id="centeredDiv">

<%@ include file="../headerteacher.jsp"%> 

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h2 id="titleBar" class="headerText"><spring:message code="teacher.pro.projinfo.1"/></h2> 

<!--<div id="projectInfoInstructions">Click any tab below for more information.</div>-->

<div id="projectInfoTabs" class="yui-navset">
    <ul class="yui-nav" >
        <li style="margin-left:4px;"><a href="#tab1"><em><spring:message code="teacher.pro.projinfo.2"/></em></a></li>
        <li style="margin-left:4px;"><a href="#tab3"><em>Lesson Plan & Learning Goals</em></a></li>
		<li style="margin-left:4px;"><a href="#tab2"><em>Teacher Step-By-Step Guide</em></a></li>
        <li style="margin-left:4px;"><a href="#tab4"><em><spring:message code="teacher.pro.projinfo.5"/></em></a></li>
    </ul>            
    <div class="yui-content">
        <div id="tab1">
  			<br/>
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
										<li><a href="<c:url value="../../previewproject.html"><c:param name="projectId" value="${project.id}"/></c:url>">Preview</a></li>
										<li><a href="<c:url value="../run/createRun.html"><c:param name="projectId" value="${project.id}"/></c:url>">Set up as Project Run</a></li>
										<li><a href="../../author/authorproject.html?projectId=${project.id}">Edit Content</a></li>
										<li><a href="customized/shareproject.html?projectId=${project.id}">Share Project</a>
										<!-- input type='checkbox' id='public_${project.id}' onclick='changePublic("${project.id}")'/> Is Public</li>-->
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
									<a id="hideShowLink" href="#" onclick="toggleProjectSummaryCurrent()">Hide/Show project details</a>
									<div id="toggleAllCurrent">
									<div id="toggleProjectSummaryCurrent">
										<table id="detailsTable">
											<tr>
												<th>Created On:</th>
												<td class="keywords"><fmt:formatDate value="${project.dateCreated}" type="both" dateStyle="short" timeStyle="short" /></td>
											</tr>
											<tr>
												<th>Summary:</th>
												<td class="summary">${project.metadata.summary}</td>
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
									</div>
								</td>
							</tr>
						</table>
	         
	    </div>       <!--	    End of Tab 1 content-->
        
        <div id="tab2">
            
            <div id="projectInfoProjectTitle">${project.curnit.sdsCurnit.name}</div>
            
            <div id="teacherGuideIntro">The Teacher Guide offers feedback per each Step of the project, including any technical or classroom requirements for the step, 
            common misconceptions/mistakes students may encounter in the step, and suggestions for making the step more effective with students.</div>
            
            <div id="editInfoLink"><a href="#">edit teacher guide</a></div>
              
            <table id="teacherGuideTable">
            	<tr class="rowTwo">
            		<td class="column1">step</td>
            		<td>feedback</td>
            	</tr>
            	<tr>
            		<td>[Activity X, Step Y]</td>
            		<td>[sample feedback goes here <br/>Aliquip dolore lobortis blandit esse suscipit duis magna vel odio dolore ipsum ut at magna iusto et ex ex. Eros illum, luptatum, ea nulla, in nostrud eu consectetuer augue accumsan feugiat qui iusto consequat duis vel nulla. Consequat duis, vero elit suscipit, at in feugait dignissim vero zzril blandit, eum lorem, feugiat erat feugait ut vel nonummy zzril accumsan velit dolor in accumsan.
Aliquip suscipit sit amet vero, enim duis minim in, ut duis minim tation. </td>
				</tr>
				<tr>
            		<td>[Activity X, Step Y]</td>
            		<td>[sample feedback goes here</td>
				</tr>
				<tr>
            		<td>[Activity X, Step Y]</td>
            		<td>[sample feedback goes here</td>
				</tr>
			</table>
			
        </div>
        
        <div id="tab3">
           
		<div id="projectInfoProjectTitle">${project.curnit.sdsCurnit.name}</div>
		<div id="teacherGuideIntro">This section describes all curriculum standards covered by the project, the
            overall learning goals of the project, and the learning goals of each main Activity in the project.</div>
        
        <div id="editInfoLink"><a href="#">edit learning goals</a></div> 
                    
        <div id="teacherGuideIntro" style="font-weight:bold; color:#FF0000;">Content follows here...</div>
            
        </div>
        
        <div id="tab4">
            
            <div id="projectInfoProjectTitle">${project.curnit.sdsCurnit.name}</div>
            
            <div id="teacherGuideIntro">The following people contribute to this WISE project:</div>
            
            <div id="editInfoLink"><a href="#">edit credits information</a></div>
            
            <table id="projectCreditsTable">
            <tr>
            	<td class="col1">Project Last Edited On:</td>
            	<td>last revised data value</td>
            </tr>
            <tr> 
            	<td class="col1">Original Author:</td>
            	<td>data value</td>
			</tr>
			<tr>
				<td class="col1">Current Author:</td>
				<td>data value</td>
			</tr>
			<tr>
			<td class="col1">Contributors:</td>
			<td>
				<ul>
					<li>data value</li>
					<li>data value</li>
					<li>data value</li>
					<li>data value</li>
					<li>data value</li>
					<li>data value</li>
				</ul>
			</td>
			</tr>
			</table>
        </div>
    </div>
</div>
  
	
</div>

<script type="text/javascript">
    var tabView = new YAHOO.widget.TabView('projectInfoTabs');
    tabView.set('activeIndex', 0);
</script>

</body>
</html>
