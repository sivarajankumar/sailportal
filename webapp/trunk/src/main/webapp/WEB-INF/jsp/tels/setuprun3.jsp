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
	   form.options[i].checked=false;
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
<script src="./javascript/tels/rotator.js" type="text/javascript" ></script>

<title><spring:message code="teacher.setup-project-run-step-three" /></title>
</head>
<%@ include file="teacherHeader.jsp"%>
<!-- Support for Spring errors object -->
<spring:bind path="runParameters.periodNames">
  <c:forEach var="error" items="${status.errorMessages}">
    <c:choose>
      <c:when test="${fn:length(error) > 0}" >
        <script type="text/javascript">
          <!--
            alert("${error}");
          //-->
        </script>
      </c:when>
    </c:choose>
  </c:forEach>
</spring:bind>

<body>
<p class="userinfo"> 
<authz:authentication operation="username" />
 </p>
<div id="navigation" class="north2 widthAdj4">
<ul class="bigFont1">
<li> <a href="teacher/index.html"> <img src="<spring:theme code="home" />" style="border:0px;"/> </a> </li>
<li> <a href="#" onclick="javascript:displayNothingYet('notexisting.html')"> <img src="<spring:theme code="projects_selected" />" style="border:0px;"/> </a> </li>
<li> <a href="#" onclick="javascript:displayNothingYet('notexisting.html')"> <img src="<spring:theme code="management" />" style="border:0px;"/> </a> </li>
<li> <a href="#" onclick="javascript:displayNothingYet('notexisting.html')"> <img src="<spring:theme code="help" />" style="border:0px;"/> </a> </li>
</ul>
</div>

<!--  
<ul id="tabnav" class="north_0 widthAdj4">
<li> <a href="#" onclick="javascript:displayNothingYet('notexisting.html')"><spring:message code="teacher.overview" /></a></li>
<li> <a href="#" onclick="javascript:displayNothingYet('notexisting.html')"><spring:message code="teacher.project-library" /></a></li>
<li> <a href="#" onclick="javascript:displayNothingYet('notexisting.html')"><spring:message code="teacher.project-runs" /></a></li>
<li> <a href="#" onclick="javascript:displayNothingYet('notexisting.html')"><spring:message code="teacher.bookmarked-projects" /></a></li>
<li> <a href="#" onclick="javascript:displayNothingYet('notexisting.html')"><spring:message code="teacher.customized-projects" /></a></li>
</ul><br />  
-->


<h2 id="right2" class="widthAdj6 maroon"> <spring:message code="teacher.setup-project-classroom-run" /></h2>
<h3 class="widthAdj6 blueColor"><b class="blackColor"><spring:message code="teacher.setup-project-run-step3" /> </b>
<spring:message code="teacher.setup-project-run-step3-desc" />
</h3>
<p class="bigFont1 widthAdj6">
<spring:message code="teacher.select-classroom-periods" />
<i><spring:message code="navigate.next" /></i>
</p>

<div>
<form:form method="post" commandName="runParameters">

<!--  
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
-->

    <div class="widthAdj6">
      <label for="periods"><spring:message code="periods" /></label><br />
          <c:forEach items="${periodNames}" var="periodName">
            <form:checkbox path="periodNames" value="${periodName}" />
            <spring:message code="defaultPeriodNames.${periodName}" /><br/>
          </c:forEach>      
    </div>
<!--  
OR <br />

<input type="checkbox" name="manualCheckbox" value="Manual" onclick="checkIfTextAreaEmpty(this.form)" /><spring:message code="classroom.manually-named-periods"/>

<textarea name="manualPeriods" cols="100" rows="5" onclick="checkIfTextAreaEmpty(this.form)" onkeypress="checkIfTextAreaEmpty(this.form)" ></textarea> 
<br />
<p class="center">
<spring:message code="classroom.separate-periods" />
</p>
-->
<div id="right" class="widthAdj6">
<input type="submit" name="_target1" value="<spring:message code="navigate.back"/>" />
<input type="submit" name="_cancel" value="<spring:message code="navigate.cancel"/>" />
<input type="submit" name="_target3" value="<spring:message code="navigate.next"/>" />
</div>
</form:form>
</div>
</body>
</html>
