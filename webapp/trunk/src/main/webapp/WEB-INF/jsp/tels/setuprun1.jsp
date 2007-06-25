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

<!-- $Id: setupRun1.jsp 357 2007-05-03 00:49:48Z archana $ -->

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" >
<html xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<link href="<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet"
  type="text/css" />
<script src="./javascript/tels/prototype.js" type="text/javascript" ></script>
<script src="./javascript/tels/scriptaculous.js" type="text/javascript" ></script>
<title><spring:message code="teacher.setup-project-run" /></title>
</head>
<body>
<%@ include file="teacher/header.jsp"%>

<div id="navigation" class="center north2 widthAdj4">
<ul class="bigFont1">
<li style="background-color:#FFFFFF;"> <a href="#"> <spring:message code="banner.home" /> </a> </li>
<li class="bgColorLightBlue"  class="border"> <a href="#"><spring:message code="banner.projects" /> </a> </li>
<li style="background-color:#FFFFFF;"> <a href="#"> <spring:message code="banner.management" /> </a> </li>
<li style="background-color:#FFFFFF;"> <a href="#"> <spring:message code="banner.community" /> </a> </li>
<li style="background-color:#FFFFFF;"> <a href="#"> <spring:message code="banner.help" /> </a> </li>
</ul>
</div>
<ul id="tabnav" class="north_0">
<li> <a href="#"><spring:message code="teacher.overview" /></a></li>
<li> <a href="#"><spring:message code="teacher.project-library" /></a></li>
<li> <a href="#"><spring:message code="teacher.project-runs" /></a></li>
<li> <a href="#"><spring:message code="teacher.bookmarked-projects" /></a></li>
<li> <a href="#"><spring:message code="teacher.customized-projects" /></a></li>
</ul><br />
<h2 class="center north0"> <spring:message code="teacher.setup-project-classroom-run" /> </h2>
<p class="bigFont1 north0"> <spring:message code="teacher.setup-run-six-steps" /> </p>
<h3 class="north0"> <spring:message code="teacher.tips-project-runs" /> </h3>
<ol class="bigFont1 north0">
<li> <spring:message code="teacher.remember" /> <b> <spring:message code="wise.preview-the-project" /> </b> <spring:message code="teacher.preview-project-message" /> </li>
<li> <spring:message code="teacher.review" /> <b> <spring:message code="teacher.lesson-plans" /></b> <spring:message code="teacher.lesson-plans-cont" /></li>
<li> <spring:message code="teacher.lesson-plans-more-info" /> <b><spring:message code="wise.guide-to-running" /></b>
<spring:message code="teacher.lesson-plans-cont" /> </li>
</ol>

<ul class="center none bigFont1 north0">
<li> <spring:message code="teacher.get-started" /> </li>
<li><spring:message code="teacher.cancel-setup-steps" /></li>
</ul>

<ul class="none bigFont1 north0">
<li class="center"> <a href="setuprun2.html" style="text-decoration:none;" class="border bigFont1 bgColorLightBlue"> <spring:message code="teacher.setup-project-run-start" /> </a> </li>
</ul>

<div>
<a href="javascript:Effect.toggle('waiting', 'appear')">click me</a>
</div>
<%@ include file="footer.jsp"%>

</body>
</html>