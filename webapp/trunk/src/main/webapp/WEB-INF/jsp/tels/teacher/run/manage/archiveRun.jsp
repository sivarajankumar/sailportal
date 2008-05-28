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

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" />
<html xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />

<link href="../../../<spring:theme code="globalstyles"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="../../../<spring:theme code="teacherrunstylesheet"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="../../../<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet"  type="text/css" />
    
<script type="text/javascript" src="../../.././javascript/tels/general.js"></script>
<script type="text/javascript" src="../../.././javascript/tels/effects.js"></script>

    
<title><spring:message code="teacher.run.manage.archiverun.1"/></title>
</head>

<body>


<div style="text-align:center;">   <!--This bad boy ensures centering of block level elements in IE (avoiding margin:auto bug).  Oh how I hate IE-->

<div id="popUpBoxBoundary">

<div id="largeHeader"><spring:message code="teacher.run.manage.archiverun.2"/></div>

<div id="blockHighlight" >
	<div id="runTitle">[VARIABLE: Project Name Here]</div>
	<div id="titleSubHeader">[VARIABLE: project type, ID]  Ex: "Library project, Project ID 21351" </div>
	<div id="titleSubHeader">[VARIABLE: run creation date]  Ex "Created 4/12/07"</div>
</div>			    	

	<div id="popUpNotice1"><spring:message code="teacher.run.manage.archiverun.3"/></div>
	<div id="popUpNotice2"><spring:message code="teacher.run.manage.archiverun.4"/></div>

<!-- Support for Spring errors object -->
<spring:bind path="endRunParameters.*">
  <c:forEach var="error" items="${status.errorMessages}">
    <b>
      <br /><c:out value="${error}"/>
    </b>
  </c:forEach>
</spring:bind>

<form:form method="post" action="archiveRun.html" commandName="endRunParameters" id="archiveRun" >
  <div style="visibility:hidden;"><label for="runId"><spring:message code="teacher.run.manage.archiverun.5"/></label>
      <form:input disabled="true" path="runId" id="runId"/>
      <form:errors path="runId" />
  </div>


<div id="responseButtons">
    <input class="center" type="image" id="savebutton" src="../../../<spring:theme code="register_save" />" 
    onmouseover="swapImage('savebutton','../../../<spring:theme code="register_save_roll" />');" 
    onmouseout="swapImage('savebutton','../../../<spring:theme code="register_save" />');" />

    <a href="#" onclick="javascript:window.close()"
    onmouseout="MM_swapImgRestore()" 
    onmouseover="MM_swapImage('cancelbutton','','../../../themes/tels/default/images/Cancel-Reg-Roll.png',1)">
    <img src="../../../themes/tels/default/images/Cancel-Reg.png" 
    alt="Cancel Button" border="0" id="cancelbutton" /></a>
</div>

</form:form>

</div>
</div>    <!--    End of popUpTextBoundary -->

</body>
</html>