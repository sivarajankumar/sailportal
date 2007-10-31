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

<!-- $Id: setupRun3.jsp 357 2007-05-03 00:49:48Z archana $ -->

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" >
<html xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />

<link href="<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet"  type="text/css" />

<script src="./javascript/tels/general.js" type="text/javascript" ></script>
<script src="./javascript/tels/effects.js" type="text/javascript" ></script>
<script src="./javascript/tels/prototype.js" type="text/javascript" ></script>
<script src="./javascript/tels/scriptaculous.js" type="text/javascript" ></script>

<title><spring:message code="teacher.setup-project-run-step-six" /></title>
</head>
<body>

<div id="centeredDiv">

<%@ include file="../../../headerteacherprojects.jsp"%>

<%@ include file="../../../L2projectsnohighlight.jsp"%>

<div id="titleBarSetUpRun">
    	<h1 class="blueText"><spring:message code="teacher.setup-project-classroom-run" /></h1></div>

<div id="setUpRunBox">

	<div id="stepNumber">Step 6 of 6:<span class="blueText">&nbsp Help Documentation</span></div>

	<h4>First time running a classroom project?  If so check out the 
		<a href="#" onclick="javascript:alert('Project Preview Not Available Yet')">Guide to Using WISE 3.0 in the Classroom</a>.</h4>
	
   	<h5 class="indent15px">
   		<ul>
   		<li>This guide contains everything you need to know about setting up your classroom computers and having your students 
   				register, form groups, and load projects. 
   				It also offers tips if you encounter difficulty during a project run (for instance, if a student loses a password).</li>
   		<li>These help documents are accessible at any time by clicking the HELP button at the top of the screen.</li>
   		</ul>
   	</h5>

	<h4>To complete the creation of your project run click <em>Done</em> below. </h4>
		
</div>

<form class="center" method="post">
<input type="submit" name="_target4" value="<spring:message code="navigate.back" />" />
<input type="submit" name="_cancel" value="<spring:message code="navigate.cancel" />" />
<input type="submit" name="_finish" value="<spring:message code="navigate.done" />" />
</form>

</div>

</body>
</html>



