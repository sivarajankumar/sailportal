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

<title>Project Information</title>

</head>

<body class="yui-skin-sam">

<div id="centeredDiv">

<%@ include file="./headerteacherprojects.jsp"%>

<%@ include file="../../L2blankbar.jsp"%>

<h2 id="titleBar" class="headerText">Project Information Details</h2> 

<!--<div id="projectInfoInstructions">Click any tab below for more information.</div>-->

<div id="projectInfoTabs" class="yui-navset">
    <ul class="yui-nav" >
        <li style="margin-left:4px;"><a href="#tab1"><em>overview</em></a></li>
        <li style="margin-left:4px;"><a href="#tab2"><em>teacher guide</em></a></li>
        <li style="margin-left:4px;"><a href="#tab3"><em>learning Goals</em></a></li>
        <li style="margin-left:4px;"><a href="#tab4"><em>project credits</em></a></li>
    </ul>            
    <div class="yui-content">
        <div id="tab1">
        
            <div id="projectInfoProjectTitle">${project.curnit.sdsCurnit.name}</div>
            
            <table id="projectInfoActionButtons"  >
            	<tr>
            		<td><a href="<c:url value="../../previewproject.html"><c:param name="projectId" value="${project.id}"/></c:url>">
	             			Preview <br>Project</a></td>
	             	<td><a href="<c:url value="../run/createRun.html"><c:param name="projectId" value="${project.id}"/></c:url>">
	             			Set Up as a <br>Project Run</a></td>
	             	<td><a href="#" style="color:#999999;">Create link in <em>My Bookmarked Projects</em></a></td>
	            	<td><a href="#" style="color:#999999;">Create editable copy in <em>My Customized Projects</em></a></td>
	            </tr>
	         </table> 
	    
	    	<dl id="projectInfo">
	    		<dt>Project Type:</dt>
	    			<dd>[Type data here]</dd>
	    		<dt>Project ID:</dt>
	    			<dd>[ID data here]</dd>
	    		<dt>Grade Range:</dt>
	    			<dd>[data here]</dd>
	    		<dt>Total Time:</dt>
	    			<dd>[data here]</dd>
	    		<dt>Computer Time:</dt>
	    			<dd>[Need Type here]</dd>
	    		<dt>Usage:</dt>
	    			<dd>[Need Type here]</dd>
	    		<dt>Contact(s):</dt>
	    			<dd>Need one or more email links here</dd>
	    	</dl>
	    	
	    <div class="projectInfoHeader">Project Summary</div>
	    <div class="projectInfoDataBox">[SAMPLE] Dolore dolore molestie wisi feugait molestie consequat iusto praesent 
	    nostrud zzril tincidunt consequat et zzril. Te ullamcorper volutpat tincidunt minim. Aliquip dolore lobortis 
	    blandit esse suscipit duis magna vel odio dolore ipsum ut at magna iusto et ex ex. 
	    Eros illum, luptatum, ea nulla, in nostrud.</div>

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
  
	
</div>

<script type="text/javascript">
    var tabView = new YAHOO.widget.TabView('projectInfoTabs');
    tabView.set('activeIndex', 0);
</script>

</body>
</html>
