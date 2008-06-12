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

<link href="../../<spring:theme code="globalstyles"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="../../<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet"  type="text/css" />

<script src="./javascript/tels/general.js" type="text/javascript" ></script>
<script src="./javascript/tels/effects.js" type="text/javascript" ></script>
<script src="./javascript/tels/prototype.js" type="text/javascript" ></script>
<script src="./javascript/tels/scriptaculous.js" type="text/javascript" ></script>

<title><spring:message code="teacher.setup-project-run-step-two" /></title>

</head>

<body>

<div id="centeredDiv">

<%@ include file="../../projects/headerteacherprojects.jsp"%>

<%@ include file="L2projectsnohighlight.jsp"%>

<h1 id="titleBarSetUpRun" class="blueText"><spring:message code="teacher.setup-project-classroom-run" /></h1>

     	    	    
<div id="setUpRunBox">

<div id="stepNumber"><spring:message code="teacher.run.setup.12"/><span class="blueText">&nbsp;<spring:message code="teacher.run.setup.13"/></span></div>

<form:form method="post" commandName="runParameters">

<h5><spring:message code="teacher.run.setup.14"/></h5>
<h6 class="indent15px"><spring:message code="teacher.run.setup.15"/></h6>

<c:choose>
	<c:when test="${fn:length(existingRunList) == 0}">
      <b><spring:message code="teacher.run.setup.16"/></b>
	</c:when>
	<c:otherwise>
<table  id="setupProjectTable" border="0" style="margin:0 0 0 25px; ">
	<tr id="setupProjectTableR1">
		<td style="border:1px solid #333333;"><spring:message code="teacher.run.setup.17"/></td>
		<td style="border:1px solid #333333;"><spring:message code="teacher.run.setup.18"/></td>
		<td style="border:1px solid #333333;"><spring:message code="teacher.run.setup.19"/></td>
		<td style="border:1px solid #333333;"><spring:message code="teacher.run.setup.20"/></td>
		<td style="border:1px solid #333333;"><spring:message code="teacher.run.setup.21"/></td>
	</tr>
    <c:forEach var="run" items="${existingRunList}">
	    <tr id="setupProjectTableR2">
	     <td class="center" style="border:1px solid #333333;">
	     
	     <!-- CHECKBOXES -->
		    <div id="runcheckboxes">
		       <form:checkbox path="runIdsToArchive" value="${run.id}" /><br/> 
		    </div>
		 <!-- END CHECKBOXES -->
    <!--end of SetUpRunBox -->
	     </td>
		        <td style="border:1px solid #333333;"><strong>${run.sdsOffering.name}</strong></td>
		        <td style="border:1px solid #333333;">${run.id}</td>
		        <td style="border:1px solid #333333;">${run.starttime.month + 1}/${run.starttime.date}/${run.starttime.year + 1900}</td>
		        <td style="border:1px solid #333333;">${run.endtime}</td>
	     </tr>
	</c:forEach>
	</table>
	<h5><spring:message code="teacher.run.setup.22"/>&nbsp;<em><spring:message code="teacher.run.setup.23"/></em>&nbsp;<spring:message code="teacher.run.setup.24"/></h5>
	</c:otherwise>
	
</c:choose>
</div> <!-- /* End setUpRunBox */-->
<div class="center">
<input type="submit" name="_target0" value="<spring:message code="navigate.back" />" />
<input type="submit" name="_cancel" value="<spring:message code="navigate.cancel" />" />
<input type="submit" name="_target2" value="<spring:message code="navigate.next" />" />
</div>  
</form:form>
</div>  <!-- /* End of the CenteredDiv */-->

</body>
</html>

