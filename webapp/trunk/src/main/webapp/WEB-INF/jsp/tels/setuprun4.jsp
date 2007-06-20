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
<link href="<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet"
  type="text/css" />
<script src="./javascript/tels/prototype.js" type="text/javascript" ></script>
<script src="./javascript/tels/scriptaculous.js" type="text/javascript" ></script>
<script type="text/javascript">
function checkIfTextAreaEmpty (form) {
if(form.manualCheckbox.checked==true){
	form.manualPeriods.disabled=false;
	for(i=0;i<form.options.length;i++){
	   form.options[i].disabled=true;
	}
	
}else{
	form.manualPeriods.disabled=true;
	for(i=0;i<form.options.length;i++){
	   form.options[i].disabled=false;
	}
	
}

}


// End -->
</script>


<title><spring:message code="teacher.setup-project-run-step-three" /></title>
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
<ul id="tabnav" class="north_0 widthAdj4">
<li> <a href="#"><spring:message code="teacher.overview" /></a></li>
<li> <a href="#"><spring:message code="teacher.project-library" /></a></li>
<li> <a href="#"><spring:message code="teacher.project-runs" /></a></li>
<li> <a href="#"><spring:message code="teacher.bookmarked-projects" /></a></li>
<li> <a href="#"><spring:message code="teacher.customized-projects" /></a></li>
</ul><br />  

<h2 class="center north0"> <spring:message code="teacher.setup-project-classroom-run" /></h2>
<h2 class="north0"><b> <spring:message code="teacher.setup-project-run-step3" /> </b></h2>
<p class="bigFont1 north0"><spring:message code="teacher.select-classroom-periods" /></p>

<div class="north0">
<form>
<input type="checkbox" name="options" value="Period 1" /> <spring:message code="classroom.period1" /><br />
<input type="checkbox" name="options" value="Period 2" /> <spring:message code="classroom.period2" /><br />
<input type="checkbox" name="options" value="Period 3" /> <spring:message code="classroom.period3" /><br />
<input type="checkbox" name="options" value="Period 4" /> <spring:message code="classroom.period4" /><br />
<input type="checkbox" name="options" value="Period 5" /> <spring:message code="classroom.period5" /><br />
<input type="checkbox" name="options" value="Period 6" /> <spring:message code="classroom.period6" /><br />
<input type="checkbox" name="options" value="Period 7" /> <spring:message code="classroom.period7" /><br />
<input type="checkbox" name="options" value="Period 8" /> <spring:message code="classroom.period8" /><br />
<input type="checkbox" name="options" value="Period 9" /> <spring:message code="classroom.period9" /><br />
<input type="checkbox" name="options" value="Period 10" /> <spring:message code="classroom.period10" /><br />

OR <br />

<input type="checkbox" name="manualCheckbox" value="Manual" onclick="checkIfTextAreaEmpty(this.form)" /><spring:message code="classroom.manually-named-periods"/>

<textarea name="manualPeriods" cols="100" rows="5" onclick="checkIfTextAreaEmpty(this.form)" onkeypress="checkIfTextAreaEmpty(this.form)" ></textarea> 
<br />
<p class="center">
<spring:message code="classroom.separate-periods" />
</p>

</form>
</div>

<div class="center north0">
<a href="setuprun3.html" style="text-decoration:none;font-size:1.5em;" class="border bigFont1 bgColorLightBlue"> Back </a>
<a href="#" style="text-decoration:none;font-size:1.5em;" class="marginAdj2 border bgColorLightBlue"> Cancel </a> 
<a href="setuprun5.html" style="text-decoration:none;font-size:1.5em;" class="border bgColorLightBlue"> Next </a> 
</div>

</body>
</html>