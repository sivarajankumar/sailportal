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

<!-- $Id: index.jsp 888 2007-08-06 23:47:19Z archana $ -->

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" >
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />  

<script type="text/javascript" src="../../javascript/tels/general.js"></script>

<title>Project Picker Screen 1</title>

<!--  styles.jsp needs to be separated...right now it has a bunch of files that we don't need -->

<%@ include file="../grading/styles.jsp"%>

<link href="../../<spring:theme code="globalstyles"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="../../<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="../../<spring:theme code="teacherprojectstylesheet" />" media="screen" rel="stylesheet" type="text/css" />
<link href="../../<spring:theme code="teachermanagementstylesheet" />" media="screen" rel="stylesheet" type="text/css" />

<script>
// this is for the tabView
    var tabView
	function init() {
   		tabView = new YAHOO.widget.TabView('tabSystem');
		tabView.set('activeIndex', 0);
    }
    YAHOO.util.Event.onDOMReady(init);
</script>

</head>

<body class="yui-skin-sam">

<div id="centeredDiv">

<%@ include file="headerteachermanagement.jsp"%>

<%@ include file="L2management_managestudents.jsp"%>

<div id="pickerHeader">
		<div id="mainHeader">Select a Project</div>
		<div id="subHeader">(click any Project Title below)</div>
</div>

<div id="tabSystem" class="yui-navset">
<ul class="yui-nav">
  <li id="tabPicker"><a href="Current Runs"><em>My Current Runs</em></a></li>	
  <li id="tabPicker"><a href="Shared Runs"><em>My Shared Runs</em></a></li>	
  <li id="tabPicker"><a href="Archived Runs"><em>My Archived Runs</em></a></li>	
</ul>
<div class="yui-content" style="background-color:#FFFFFF;">
<div>

<table style="margin-bottom:20px; margin-top:10px;" summary="project picker screen for management area">
	<thead>
		<tr>
			<th style="width:50%; text-align:left;" scope="col">Project Title</th>
			<th scope="col">Project ID</th>
			<th scope="col">Run <br/>Started On</th>
			<th scope="col">Run <br/>Ended On</th>
			<th scope="col">Period(s)</th>
			<th scope="col">Subject(s)</th>
			<th scope="col">Items <br/>to Grade</th>
		</tr>
	</thead>
	<tbody>	
	    <c:forEach var="currentRun" varStatus="currentRunVarStatus" items="${current_run_list}" >
	    <c:choose>
	      <c:when test="${currentRunVarStatus.index % 2 == 1}">
      		<tr class="odd">
	      </c:when>
	      <c:otherwise>
	        <tr>
	      </c:otherwise>
	    </c:choose>
	    <tr>
			<th scope="row"><a href="viewmystudents.html?runId=${currentRun.id}">${currentRun.sdsOffering.name}</a></th>
			<td>[45345]</td>
			<td><fmt:formatDate value="${currentRun.starttime}" dateStyle="short" /></td>
			<td>[ongoing]</td>
			<td>[1,2,4,6]</td>
			<td>[Physics]</td>
			<td>[12]</td>
		</tr>
		</c:forEach>
	</tbody>
</table>
</div>
<div>

<table style="margin-bottom:20px; margin-top:10px;" summary="project picker screen for management area">
	<thead>
		<tr>
			<th style="width:50%;" scope="col">Project Title</th>
			<th scope="col">Project #</th>
			<th scope="col">Started</th>
			<th scope="col">Ended</th>
			<th scope="col">Periods</th>
			<th scope="col">Subject</th>
			<th scope="col">Ungraded Items</th>
		</tr>
	</thead>
	<tbody>	
		<tr>
			<th scope="row"><a href="#">Sample Title 1</a></th>
			<td>45345</td>
			<td>12/13/07</td>
			<td>ongoing</td>
			<td>1,2,4,6</td>
			<td>Physics</td>
			<td>12</td>
		</tr>
		<tr class="odd">
			<th scope="row"><a href="#">Sample Title 2</a></th>
			<td>45721</td>
			<td>12/22/07</td>
			<td>ongoing</td>
			<td>5,6,7,8</td>
			<td>Biology</td>
			<td>3</td>
		</tr>
	</tbody>
</table>
</div>

<div>

<table style="margin-bottom:20px; margin-top:10px;" summary="project picker screen for management area">
	<thead>
		<tr>
			<th style="width:50%;" scope="col">Title</th>
			<th scope="col">Project #</th>
			<th scope="col">Started</th>
			<th scope="col">Ended</th>
			<th scope="col">Periods</th>
			<th scope="col">Subject</th>
			<th scope="col">Ungraded Items</th>
		</tr>
	</thead>
	<tbody>	
	    <c:forEach var="archivedRun" varStatus="archivedRunVarStatus" items="${archived_run_list}" >
	    <c:choose>
	      <c:when test="${archivedRunVarStatus.index % 2 == 1}">
      		<tr class="odd">
	      </c:when>
	      <c:otherwise>
	        <tr>
	      </c:otherwise>
	    </c:choose>
		<tr>
			<th scope="row"><a href="viewmystudents.html?runId=${archivedRun.id}">${archivedRun.sdsOffering.name}</a></th>
			<td>45345</td>
			<td><fmt:formatDate value="${archivedRun.starttime}" dateStyle="short" /></td>
			<td><fmt:formatDate value="${archivedRun.endtime}" dateStyle="short" /></td>
			<td>1,2,4,6</td>
			<td>Physics</td>
			<td>12</td>
		</tr>
		</c:forEach>
	</tbody>
</table>
</div>
</div> <!--  end of yui-content Div -->

</div> <!-- end of tabSystem Div -->

<div id="returnToTopLink">
	<a href="../management/managemystudents.html">Return to <em>Manage My Students</em> Section</a>
</div>

</div>   <!--End of Centered Div-->

</body>
</html>
