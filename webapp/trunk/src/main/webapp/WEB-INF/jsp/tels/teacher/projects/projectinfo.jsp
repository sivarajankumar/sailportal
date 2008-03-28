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

<%@ include file="./L2projects_projectlibrary.jsp"%>

<h2 id="titleBar" class="headerText">Project Information Details</h2> 

<!--<div id="projectInfoInstructions">Click any tab below for more information.</div>-->

<div id="projectInfoTabs" class="yui-navset">
    <ul class="yui-nav" >
        <li style="margin-left:2px;"><a href="#tab1"><em>overview</em></a></li>
        <li style="margin-left:2px;"><a href="#tab2"><em>teacher guide</em></a></li>
        <li style="margin-left:2px;"><a href="#tab3"><em>learning Goals</em></a></li>
        <li style="margin-left:2px;"><a href="#tab4"><em>project credits</em></a></li>
    </ul>            
    <div class="yui-content">
        <div id="tab1">
        
            <div id="projectInfoProjectTitle">${project.curnit.sdsCurnit.name}</div>
            
            <table id="projectInfoActionButtons" >
            	<tr>
            		<td><a href="<c:url value="../../previewproject.html"><c:param name="projectId" value="${project.id}"/></c:url>">
	             			Preview Project</td>
	             	<td class="projectInfoActionButtonsSeprator"></td>
	             	<td><a href="<c:url value="../run/createRun.html"><c:param name="projectId" value="${project.id}"/></c:url>">
	             			Set Up as a Project Run</a></td>
	             	<td class="projectInfoActionButtonsSeprator"></td>
	             	<td><a href="#" style="color:#999999;">Create link in <em>My Bookmarked Projects<em></a></td>
	             	<td class="projectInfoActionButtonsSeprator"></td>
	            	<td><a href="#" style="color:#999999;">Create editable copy in <em>My Customized Projects</em></a></td>
	            </tr>
	         </table>
	    
	    
	    
	         
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
  
	
</div>

<script type="text/javascript">
    var tabView = new YAHOO.widget.TabView('projectInfoTabs');
    tabView.set('activeIndex', 0);
</script>

</body>
</html>
