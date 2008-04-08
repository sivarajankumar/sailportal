<%@ include file="../../include.jsp" %> 

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

<!-- $Id: overview.jsp 997 2007-09-05 16:52:39Z archana $ -->

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" >

<html lang="en">
<head><meta http-equiv="Content-Type" content="text/html; charset=utf-8"></meta>

<link href="../../../<spring:theme code="globalstyles"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="../../../<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="../../../<spring:theme code="teacherprojectstylesheet" />" media="screen" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="../../.././javascript/tels/general.js"></script>
 
<title>Sharing Permissions for Customized Projects</title>

</head>

<body>

<div id="centeredDiv">

<%@ include file="headerteacherprojects.jsp"%>

<%@ include file="L2projects_projectlibrary.jsp"%>

<div style="text-align:center;">   <!--This bad boy ensures centering of block level elements in IE. -->

<h2 id="titleBar" class="headerText">Sharing Permissions</h2> 

<div class="sharedprojectHeadline1">project selected:</div>
 
 <table id="customProjectTable" border="1" cellpadding="0" cellspacing="0">
				    <tr>
				        <th>project<br>title</th>
				        <th>Main<br>Topic(s)</th>
				        <th>project<br>id</th>
				        <th>created<br>on</th>
						<th>project<br>source</th>
						<th>grade<br>range</th>
						<th>total<br>time</th>
						<th>computer<br>time</th>
				    </tr>
				  
				  <tr id="customProjectR2">
				    <td class="customProjectTitle">Space Colony! Meiosis and Sexual Reproduction</td>
				    <td class="dataText">Biology, Evolution</td>
				    <td class="dataText">10324</td>
				    <td class="dataText">12/14/07</td>
				    <td class="smallText1">UC Berkeley library project</td>
				    <td class="dataText">6,7,8,9</td>
				    <td class="dataTime">6 hours</td>
				    <td class="dataTime">5 hours</td>
				  </tr>
				   
				</table>
				
<div class="sharedprojectHeadline1">permissions for this customized project:</div>			

<table id="sharedProjectPermissions">

	<tr>
		<th>USERNAME</th>
		<th>PERMISSION LEVEL</th> 
	</tr>
	<tr>
		<td class="sharedUserName">[Current Username]</td>
		<td ">Owner of project. Full access.</td>
	</tr>
	<tr>
		<td class="sharedUserName">[Name]</td>
		<td>
			<select>
				<option value="view">Can VIEW the project</option>
				<option value="edit">Can VIEW + EDIT the project</option>
				<option value="projectrun">CAN VIEW + EDIT + SET UP PROJECT RUNS</option>
				<option value="projectrun">Remove this User</option>
				
			</select>
		</td>
	</tr>
	
	<tr>
		<td id="sharingSearchBox" colspan=2>
			<div id="sharingSearchBoxHelp">To share this project with another user, enter some or all of their Username below:</div>
				<input type="text" name="userSearch" ></input>
				<input type="submit" value="Search for Username"></input>
		</td>
	</tr>

</table> 

<h5><a href="../customized/index.html#actionsCurrent">Return to <em>My Customized Projects</em></a></h5>

</div>
</div>

</body>
</html>

