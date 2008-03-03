<%@ include file="../include.jsp" %>

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

<head>

<link href="../../<spring:theme code="globalstyles"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="../../<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="../../<spring:theme code="teacherprojectstylesheet" />" media="screen" rel="stylesheet" type="text/css" />
<link href="../../<spring:theme code="teacherhelpstylesheet"/>" media="screen" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="../../javascript/tels/general.js"></script>
 
<title>Teacher Help Overview</title>

<!--NOTE: the following scripts has CONDITIONAL items that only apply to IE (MattFish)-->
<!--[if lt IE 7]>
<script defer type="text/javascript" src="../../javascript/tels/iefixes.js"></script>
<![endif]-->

</head>

<body>

<div id="centeredDiv">

<%@ include file="headerteacherhelp.jsp"%>
<%@ include file="../../L2helpbar.jsp"%>


<div id="overviewContent"> 

	<div id="overviewHeader">help: overview</div>
	
	<div class="helpSection">
		<div class="helpHeader">New Users</div>
			<ul>
			<li><a href="http://docs.telscenter.org/display/WPSD/Guide+for+New+Users">Guide for New Ssers</a></li>
			<li><a href="http://docs.telscenter.org/display/WPSD/WISE+FAQ">WISE 3.0 FAQ  (Frequently Asked Questions)</a></li>
			<li><a href="http://docs.telscenter.org/display/WPSD/Guide+to+the+Teacher%27s+PET">Guide to the Teacher Dashboard</a></li>
			<li><a href="guides/runGuide.php">Using WISE in your Classroom</a></li>
			</ul>
	</div>

	<div class="helpSection">
		<div class="helpHeader">Technical Requirements</div>
			<ul>
			<li><a href="http://docs.telscenter.org/display/WPSD/Software+Requirements">Minimum Software Requirements</a></li>
			<li><a href="http://docs.telscenter.org/display/WPSD/Classroom+computer+lab+requirements+to+run+projects">Other Classroom Computer Lab Requirements</a></li>
			<li><a href="http://docs.telscenter.org/display/WPSD/Recommended+Software">Recommended Software</a></li>
			<li><a href="http://docs.telscenter.org/display/WPSD/Compatibility">Compatibility</a></li>
			<li><a href="support/classroomSetup.php">Classroom Setup Assistant</a></li>
			</ul>
	</div>

	<div class="helpSection">
		<div class="helpHeader">Technical Problems</div>
			<ul>
			<li><a href="/check/">Automated Software Test</a></li>
			<li><a href="#">Java Loading Issues</a></li>
			<li><a href="http://docs.telscenter.org/display/WPSD/Troubleshooting">Troubleshooting</a></li>
			<li><a href="/pages/contact/contactWISE.php">Contact Us</a></li>
			</ul>
	</div>
	
</div>
</div>

</body>
</html>

