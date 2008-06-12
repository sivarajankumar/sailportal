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
 
<title><spring:message code="teacher.pro.custom.sharepro.1"/></title>

</head>

<body>

<div id="centeredDiv">

<%@ include file="headerteacherprojects.jsp"%>

<%@ include file="L2projects_projectlibrary.jsp"%>

<div style="text-align:center;">   <!--This bad boy ensures centering of block level elements in IE. -->

<h2 id="titleBar" class="headerText"><spring:message code="teacher.pro.custom.sharepro.2"/></h2> 

<div class="sharedprojectHeadline1"><spring:message code="teacher.pro.custom.sharepro.3"/></div>
 
 <table id="customProjectTable" border="1" cellpadding="0" cellspacing="0">
				    <tr>
				        <th><spring:message code="teacher.pro.custom.index.19"/></th>
				        <th><spring:message code="teacher.pro.custom.index.20"/></th>
				        <th><spring:message code="teacher.pro.custom.index.21"/></th>
				        <th><spring:message code="teacher.pro.custom.index.22"/></th>
						<th><spring:message code="teacher.pro.custom.index.23"/></th>
						<th><spring:message code="teacher.pro.custom.index.24"/></th>
						<th><spring:message code="teacher.pro.custom.index.25"/></th>
						<th><spring:message code="teacher.pro.custom.index.26"/></th>
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
				
<div class="sharedprojectHeadline1"><spring:message code="teacher.pro.custom.sharepro.4"/></div>			

<table id="sharedProjectPermissions">

	<tr>
		<th><spring:message code="teacher.pro.custom.sharepro.5"/></th>
		<th><spring:message code="teacher.pro.custom.sharepro.6"/></th> 
	</tr>
	<tr>
		<td class="sharedUserName">[Current Username]</td>
		<td "><spring:message code="teacher.pro.custom.sharepro.7"/></td>
	</tr>
	<tr>
		<td class="sharedUserName">[Name]</td>
		<td>
			<select>
				<option value="view"><spring:message code="teacher.pro.custom.sharepro.8"/></option>
				<option value="edit"><spring:message code="teacher.pro.custom.sharepro.9"/></option>
				<option value="projectrun"><spring:message code="teacher.pro.custom.sharepro.10"/></option>
				<option value="projectrun"><spring:message code="teacher.pro.custom.sharepro.11"/></option>
				
			</select>
		</td>
	</tr>
	
	<tr>
		<td id="sharingSearchBox" colspan=2>
			<div id="sharingSearchBoxHelp"><spring:message code="teacher.pro.custom.sharepro.12"/></div>
				<input type="text" name="userSearch" ></input>
				<input type="submit" value="<spring:message code="teacher.pro.custom.sharepro.13"/>"></input>
		</td>
	</tr>

</table> 

<h5><a href="../customized/index.html#actionsCurrent"><spring:message code="teacher.pro.custom.sharepro.14"/>&nbsp;<em><spring:message code="teacher.pro.custom.sharepro.15"/></em></a></h5>

</div>
</div>

</body>
</html>

