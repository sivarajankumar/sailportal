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

<link href="../../../<spring:theme code="teacherrunstylesheet"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="../../../<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet"  type="text/css" />
    
<script type="text/javascript" src="../../.././javascript/tels/general.js"></script>
<script type="text/javascript" src="../../.././javascript/tels/effects.js"></script>

    
<title>Archive Run Pop Up</title>
</head>

<body>

<div id="centeredDiv">

<div id="popUpBoxBoundary">

<div id="largeHeader">Archive Project Run</div>

<div id="blockHighlight" >
	<div id="runTitle">[DATA NEEDED: Project Name Here]</div>
	<div id="titleSubHeader">&middot; [DATA NEEDED: project type,project ID]  "library project, ID 21351" &middot;</div>
	<div id="titleSubHeader">&middot; [DATA NEEDED: run creation date]  "created 4/12/07" &middot;</div>
</div>			    	

	<div id="popUpNotice1">Are you sure you want to archive this project?</div>
	<div id="popUpNotice2">Note that archived information is not deleted, just stored in the "Archived Runs" folder. 
You can move an archived project run back to the "Current Runs" folder at any time.</div>

<!-- Support for Spring errors object -->
<spring:bind path="endRunParameters.*">
  <c:forEach var="error" items="${status.errorMessages}">
    <b>
      <br /><c:out value="${error}"/>
    </b>
  </c:forEach>
</spring:bind>

<form:form method="post" action="archiveRun.html" commandName="endRunParameters" id="archiveRun" >
  <div style="visibility:hidden;"><label for="runId">Run ID:</label>
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

</div>    <!--    End of popUpTextBoundary -->
</div>    <!--    End of centeredDiv -->

</body>
</html>