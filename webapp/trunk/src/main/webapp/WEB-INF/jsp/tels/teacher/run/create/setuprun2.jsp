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

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" >
<html xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />

<link href="<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet"  type="text/css" />

<script src="./javascript/tels/general.js" type="text/javascript" ></script>
<script src="./javascript/tels/effects.js" type="text/javascript" ></script>
<script src="./javascript/tels/prototype.js" type="text/javascript" ></script>
<script src="./javascript/tels/scriptaculous.js" type="text/javascript" ></script>

<title><spring:message code="teacher.setup-project-run-step-two" /></title>

</head>

<body>

<div id="centeredDiv">

<%@ include file="../../../headerteacherprojects.jsp"%>

<%@ include file="../../../L2projectsnohighlight.jsp"%>

<div id="titleBarSetUpRun">
    	<h1 class="blueText"><spring:message code="teacher.setup-project-classroom-run" /></h1></div>
     	    	    
<div id="setUpRunBox">

<div id="stepNumber">Step 2 of 6:<span class="blueText">&nbsp Archive Existing Project Runs</span></div>

<h4>Your currently active project runs are listed below.  If these project runs are now complete, you can archive them using the ARCHIVE boxes.</h4>
<h5 class="indent15px">Note:  when you archive a project run no information is lost.  The project is simply moved to the an Archive area for storage. 
Previous student work and teacher feedback in archived projects can be viewed again at any time. </h5>

<table  id="projectTable" border="1" cellpadding="3" cellspacing="2" style="margin:0 0 0 25px;">
	<tr id="projectTableR1">
		<td>CHECK TO ARCHIVE</td>
		<td>Project Title</td>
		<td>Project ID</td>
		<td>Run Created On</td>
		<td>Last Revision On</td>
	</tr>
	<tr id="projectTableR2">
		<td class="center">{ }</td>
		<td><strong>An Airbag Knocked Off My Baby's Head</strong></td>
		<td>27134</td>
		<td>1/23/07</td>
		<td>3/4/07</td>
	</tr>
	<tr id="projectTableR2">
		<td class="center">{ }</td>
		<td><strong>Dingo Got My Baby</strong></td>
		<td>30281</td>
		<td>7/15/07</td>
		<td>7/27/07</td>
	</tr>
	<tr id="projectTableR2">
		<td class="center">{ }</td>
		<td><strong>Baby, Baby, What I'd Do For You</strong></td>
		<td>23999</td>
		<td>4/1/07</td>
		<td>4/14/07</td>
	</tr>
	</table>
	
	<!--<p style="width:950px;"><c:forEach var="run" items="${existingRunList}">
		<tr>
			<td>${run.sdsOffering.name}</td>
		</tr>
			</c:forEach>
	</p>-->
			
<h4>If you do not wish to archive any project runs, leave the boxes unchecked and click <em>Next</em> to continue.</h4>

</div> <!-- /* End setUpRunBox */-->

</div>
<div class="center">
<form method="post" class="center">
<input type="submit" name="_target0" value="<spring:message code="navigate.back" />" />
<input type="submit" name="_cancel" value="<spring:message code="navigate.cancel" />" />
<input type="submit" name="_target1" value="<spring:message code="navigate.next" />" />
</form>

</div>  <!-- /* End of the CenteredDiv */-->

</body>
</html>

