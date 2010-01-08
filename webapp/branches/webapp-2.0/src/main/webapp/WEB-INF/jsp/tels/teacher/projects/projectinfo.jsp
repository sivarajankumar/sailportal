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

<h2 id="titleBar" class="headerText"><spring:message code="teacher.pro.projinfo.1"/></h2> 

<!--<div id="projectInfoInstructions">Click any tab below for more information.</div>-->

<div id="projectInfoTabs" class="yui-navset">
    <ul class="yui-nav" >
        <li style="margin-left:4px;"><a href="#tab1"><em><spring:message code="teacher.pro.projinfo.2"/></em></a></li>
        <li style="margin-left:4px;"><a href="#tab2"><em><spring:message code="teacher.pro.projinfo.3"/></em></a></li>
        <li style="margin-left:4px;"><a href="#tab3"><em><spring:message code="teacher.pro.projinfo.4"/></em></a></li>
        <li style="margin-left:4px;"><a href="#tab4"><em><spring:message code="teacher.pro.projinfo.5"/></em></a></li>
    </ul>            
    <div class="yui-content">
        <div id="tab1">
        
            <div id="projectInfoProjectTitle">${project.projectInfo.name}</div>
            
            <table id="projectInfoActionButtons"  >
            	<tr>
            		<td><a href="<c:url value="../../previewproject.html"><c:param name="projectId" value="${project.id}"/></c:url>">
	             			PREVIEW <br>PROJECT</a></td>
	             	<td><a href="<c:url value="../run/createRun.html"><c:param name="projectId" value="${project.id}"/></c:url>">
	             			SET UP AS A<br>PROJECT RUN</a></td>
	             	<td><a href="#" style="color:#999999;">CREATE LINK IN <br/><em>MY BOOKMARKED PROJECTS</em></a></td>
	            	<td><a href="#" style="color:#999999;">CREATE EDTIABLE COPY IN <em>MY CUSTOMIZED PROJECTS</em></a></td>
	            </tr>
	         </table> 
	        
	        <div id="editInfoLink"><a href="#">edit overview information</a></div>
	    
	    	<dl id="projectInfo">
	    		<dt>Project Name:</dt>
	    			<dd>${project.projectInfo.name}</dd>
	    		<dt>Project ID:</dt>
	    			<dd>${project.id}</dd>
	    		<dt>Project Source:</dt>
	    			<dd>${project.familytag}</dd>
	    		<dt style="letter-spacing:-.7px;">Subjects/Keywords:</dt>
	    			<dd>${project.metadata.subject}</dd>
	    		<dt>Grade Range:</dt>
	    			<dd>${project.metadata.gradeRange}</dd>
	    		<dt>Total Time:</dt>
	    			<dd>${project.metadata.totalTime }</dd>
	    		<dt>Computer Time:</dt>
	    			<dd>${project.metadata.compTime }</dd>
	    		<dt>Usage:</dt>
	    			<dd>${usage}</dd>
	    		<dt>Created On:</dt>
	    			<dd>${project.dateCreated}</dd>
	    		
	    	</dl>
	    	
	    <div class="projectInfoHeader">Project Summary</div>
	    <div class="projectInfoDataBox">${project.metadata.summary}</div>
	    
	    <div class="projectInfoHeader">Contact(s)</div>
	    <div class="projectInfoDataBox">${project.metadata.contact }</div>
	    	
		<div class="projectInfoHeader">Technical Requirements</div>
	    <div class="projectInfoDataBox">
			${project.metadata.techReqs }
		</div>	

		<div class="projectInfoHeader">Lesson Plan</div>
		<div class="projectInfoDataBox">${project.metadata.lessonPlan}</div>
	         
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
