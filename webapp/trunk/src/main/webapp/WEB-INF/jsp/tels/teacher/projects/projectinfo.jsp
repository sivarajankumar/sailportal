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

<title><spring:message code="teacher.pro.projinfo.1"/></title>

</head>

<body class="yui-skin-sam">

<div id="centeredDiv">

<%@ include file="headerteachernohighlight.jsp"%>

<%@ include file="../../L2blankbar.jsp"%>

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
        
            <div id="projectInfoProjectTitle">${project.curnit.sdsCurnit.name}</div>
            
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
	    
	    	<dl id="projectInfo">
	    		<dt>Project ID:</dt>
	    			<dd>${project.id}</dd>
	    		<dt>Project Source:</dt>
	    			<dd>[Type data here]</dd>
	    		<dt style="letter-spacing:-.7px;">Subjects/Keywords:</dt>
	    			<dd>[data here]</dd>
	    		<dt>Grade Range:</dt>
	    			<dd>[data here]</dd>
	    		<dt>Total Time:</dt>
	    			<dd>[data here]</dd>
	    		<dt>Computer Time:</dt>
	    			<dd>[Need Type here]</dd>
	    		<dt>Usage:</dt>
	    			<dd>[Need Type here]</dd>
	    		
	    	</dl>
	    	
	    <div class="projectInfoHeader">Project Summary</div>
	    <div class="projectInfoDataBox">[SAMPLE] Dolore dolore molestie wisi feugait molestie consequat iusto praesent 
	    nostrud zzril tincidunt consequat et zzril. Te ullamcorper volutpat tincidunt minim. Aliquip dolore lobortis 
	    blandit esse suscipit duis magna vel odio dolore ipsum ut at magna iusto et ex ex. 
	    Eros illum, luptatum, ea nulla, in nostrud.</div>
	    
	    <div class="projectInfoHeader">Contact(s)</div>
	    <div class="projectInfoDataBox">[Need one or more email links here]</div>
	    	
		<div class="projectInfoHeader">Technical Requirements</div>
	    <div class="projectInfoDataBox">
			<ul>
				<li>Dolor exerci nisl hendrerit illum vulputate feugait duis, nibh vulputate, veniam odio ad, qui nonummy nulla at blandit ea.</li>
				<li>Dolore dolore molestie wisi feugait molestie consequat iusto praesent nostrud zzril tincidunt consequat et zzril</li>
				<li>Te ullamcorper volutpat tincidunt minim. Aliquip dolore lobortis blandit esse suscipit duis magna vel odio dolore</li>
				<li>ipsum ut at magna iusto et ex ex. Eros illum, luptatum, ea nulla, in nostrud eu consectetuer augue accumsan feugiat qui iusto consequat duis vel nulla. Consequat duis, vero elit suscipit, at in feugait dignissim vero zzril blandit, eum lorem, feugiat erat feugait ut vel nonummy zzril accumsan velit dolor in accumsan.
Aliquip suscipit sit amet vero, enim duis minim in, ut duis minim tation. Sed molestie</li>
				<li>dolor ea ut elit sed wisi et iriure nostrud vulputate illum nulla.</li>
			</ul>
		</div>	

	         
	    </div>       <!--	    End of Tab 1 content-->
        
        <div id="tab2">
            <h5>PENDING, SUMMER 2008</h5>
            <p>This section will display a table of all steps in the project with a Teacher's Guide box per step (editable using the Authoring
            Tool). This table will allow the author of a project to convey detailed, step-specific feedback to a teacher -- including
           classroom setup requirements for the step, common misconceptions/mistakes students may encounter in each step, suggestions for making a step more effective,
           answers to the typical student questions for a particular step, etc etc. </p>
            
        </div>
        
        <div id="tab3">
            <h5>PENDING, SUMMER 2008</h5><p>This section, editable using the Project Authoring Tool, will list all curriculum standards covered by the project, the
            overall learning goals of the project, and the learning goals of each main Activity.</p>
        </div>
        
        <div id="tab4">
            <h5>PENDING, SUMMER 2008</h5>
            <p>This section will cite the original source of the project (either created from scratch by X or copied from library/custom project ID xxxxxx.
            It will also list all registed users who contributed to the authoring of the project, sorted by frequency of involvement. </p>
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
